package cn.movinginfo.tztf.sys.action;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.IpAddressUtil;
import cn.movinginfo.tztf.sys.domain.*;
import cn.movinginfo.tztf.sys.service.*;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.*;

/**
 *
 * @description:Reference action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/sys/dept")
@SuppressWarnings("serial")
public class DeptAction extends MagicAction<Depart, DeptService> {

	public static final String KEY_CAPTCHA = "SE_KEY_MM_CODE";
	@Autowired
	private UserService userService;
	@Autowired
	private DictService dictService;
	@Autowired
	private RecordService recordService;
	// 人员和角色关联
	@Autowired
	private RUserRoleService rUserRoleService;
	
	@Autowired
	private	AreaService areaService;

	@RequestMapping(value = "/addDepartment")
	public String addDepartment(HttpServletRequest request, Model model) throws Exception {
		return getNameSpace() + "addDepartment";
	}

	/**
	 * 责任单位联系人数据展示及分页界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "departmentLinkerData")
	public String departmentLinkerData(HttpServletRequest request, Model model) throws Exception {
		// 获取数据
		PageInfo<?> pageInfo = entityService.queryPaged(getParameterMap(request));
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page", page);
		return getNameSpace() + "department_linker_data";
	}

	/**
	 * 责任单位联系人查询栏界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/departmentLinkerQuery")
	public String departmentLinkerQuery(HttpServletRequest request, Model model) {
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return getNameSpace() + "department_linker_query";
	}

	/**
	 * 确认新增/修改保存
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, String areas, Model model) throws Exception {
		Depart department = bindEntity(request, entityClass);
		Long userIds = userService.get(this.getCurrentUser().id).getId();
		String loginName = userService.findByUserId(userIds).getName();
		String userName=request.getParameter("username");
		String name = department.getName();
		String code = department.getCode();
		Long depid = userService.get(this.getCurrentUser().id).getDepartmentId();
		Depart d = entityService.get(depid);
		User user = new User();
		Record record = new Record();//日志
		User u = userService.get(this.getCurrentUser().id);
		if(StringUtils.isEmpty(areas) || " ".equals(areas) ) {

			Depart depart = entityService.findByName(name);
			Depart dept = entityService.findByCode(code);
			if(department.getId() == null) {
				if(depart != null) {
					printJson(response, com.alibaba.fastjson.JSON.toJSONString("该部门名称已存在！"));
					return;
				}
				if(dept != null) {
					printJson(response, com.alibaba.fastjson.JSON.toJSONString("该单位名称缩写已存在！"));
					return;
				}
				areas = d.getAreaId();
				department.setAreaId(d.getAreaId());
				user.setAreaId(d.getAreaId());
				user.setIsReceiveMassages("1");
			}else {
				if(depart != null && !department.getId().equals(depart.getId())) {
					if(depart != null) {
						printJson(response, com.alibaba.fastjson.JSON.toJSONString("该部门名称已存在！"));
						return;
					}
					if(dept != null) {
						printJson(response, com.alibaba.fastjson.JSON.toJSONString("该单位名称缩写已存在！"));
						return;
					}
				}

			}

		}else {
			List<Depart> depts=entityService.getByArea(areas);
			if(department.getId() != null){
				List<Depart> result = entityService.getAllValueByDeptName(name,department.getId());
				if (CollectionUtils.isNotEmpty(result)){
					printJson(response, com.alibaba.fastjson.JSON.toJSONString("该部门名称已存在！"));
					return;
				}
				List<Depart> result1 = entityService.getAllValueByCode(code,department.getId());
				if (CollectionUtils.isNotEmpty(result1)){
					printJson(response, com.alibaba.fastjson.JSON.toJSONString("该单位名称缩写已存在！"));
					return;
				}
			}
			
			if(!depts.isEmpty() && department.getId() == null) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该区域发布中心已存在！"));
				return;
			}
			department.setAreaId(areas);
		}
		boolean isAdd = false;
		if (department.getId() != null) {
			department.setUpdator(this.getCurrentUser().id);
			department.setUpdateDate(new Date());
//			u = userService.findUserByDepartment(department.getId());
			record.setUserName(loginName);
			record.setUserId(userIds);
			record.setOperateDate(new Date());
			record.setContent(loginName+"用户修改了("+name+")部门");
			record.setIpAddress(IpAddressUtil.getIpAddr(request));//添加本机的ip地址
			recordService.saveRcord(record);
		} else {
			department.setCreator(this.getCurrentUser().id);
			department.setCreateDate(new Date());
			isAdd = true;
			// 向R表中保存
		}
		Long departmentId = entityService.saveOrUpdate(department);
		if (isAdd) {
			user.setDepartmentId(departmentId);
			// 向用户表保存用户
			// 密码加密
			user.encryptUserPassword("111111");
			user.setName(department.getName());
			user.setUserName(department.getName());
			user.setCreator(getCurrentUser().id);
			user.setCreateDate(new Date());
			user.setIsUse("1");
			user.setAreaId(areas);
			user.setIsReceiveMassages("1");
			record.setUserName(loginName);
			record.setUserId(userIds);
			record.setContent(loginName+"部门添加了("+department.getName()+")部门");
			record.setOperateDate(new Date());
			record.setIpAddress(IpAddressUtil.getIpAddr(request));
			recordService.saveRcord(record);
			Long userId = userService.saveOrUpdate(user);
			RUserRole rUserRole = new RUserRole();
			rUserRole.setUserId(userId.intValue());
			if(department.getDeptType()!=null&&department.getDeptType().equals("1")){
				rUserRole.setRoleId(2);
			}else if(department.getDeptType()!=null&&department.getDeptType().equals("3")){
				rUserRole.setRoleId(6);
			}else if(department.getDeptType()!=null&&department.getDeptType().equals("2")) {
				rUserRole.setRoleId(3);
			}
			rUserRole.setCreator(this.getCurrentUser().id);
			rUserRole.setCreateDate(new Date());
			rUserRoleService.saveOrUpdate(rUserRole);
		}else {
//			u.setUserName(userName);
//			u.setName(department.getName());
			userService.updateByUser(u);
		}

		boolean data = true;
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(data));
	}

	/**
	 * 获取修改部门类型传入前端页面生成下拉框
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "getDeptType")
	public void getDeptType(HttpServletRequest request, HttpServletResponse response ,Long id) {
		Depart depart = entityService.getByDepartmentId(id);
		String deptType=depart.getDeptType();
		String content = dictService.getContentNotValid("dept_type", deptType);
		depart.setTypeName(content);
		String townId = depart.getTownId();
		if(!StringUtils.isEmpty(townId)) {
			Area area = areaService.get(Long.parseLong(townId));
			String townName = area.getTown();
			depart.setTownName(townName);
			List<Area> list = areaService.getAll();
			depart.setAreaList(list);
		}
		
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(depart));
	}
	/**
	 * 获取新增部门类型传入前端页面生成下拉框
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getDeptType2")
	public void getDeptType2(HttpServletRequest request, HttpServletResponse response) {
		List<Dict> deptTypes = dictService.getDicList("dept_type");
	    printJson(response, com.alibaba.fastjson.JSON.toJSONString(deptTypes));
	}
	
	/**
	 * 获取区域
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAreas2")
	public void getAreas2(HttpServletRequest request, HttpServletResponse response) {
		List<Dict> areas = dictService.getDicList("area_id");
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(areas));
	}
    
	@RequestMapping(value = "getAreas")
	public void getAreas(HttpServletRequest request, HttpServletResponse response,Long id) {
		Depart depart = entityService.getByDepartmentId(id);
		String areaId = depart.getAreaId();
		String content = dictService.getContent("area_id", areaId);
		Dict areas =new Dict();
		areas.setValue(areaId);
		areas.setContent(content);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(areas));
	}
	
	@RequestMapping(value = "getAllTown")
	@ResponseBody
	public void getAllTown(HttpServletRequest request, HttpServletResponse response) {
		List<Area> result = areaService.getAll();
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(result));
	}
	
	
	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editDepartment")
	public void editDepartment(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Depart department = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(department));
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteDepartment")
	public String deleteDepartment(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		Record record = new Record();//日志
		Long userIds = userService.get(this.getCurrentUser().id).getId();
		String userName = userService.get(this.getCurrentUser().id).getName();
		if (!StringUtils.isEmpty(id)) {
			Depart depart = entityService.get(Long.parseLong(id));
			entityService.logicRemove(Long.parseLong(id));
			userService.delete(id);
			//userService.deleteByName(depart.getName());
			record.setUserName(userName);
			record.setUserId(userIds);
			record.setContent(userName+"部门删除了("+depart.getName()+")部门");
			record.setOperateDate(new Date());
			record.setIpAddress(IpAddressUtil.getIpAddr(request));
			recordService.saveRcord(record);
		}
		return "redirect:/sys/dept/departmentQuery";
	}
	
	/**
	 * 判断此部门类型是否存在
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/judgeDeptType")
//	@ResponseBody
//	public boolean  judgeDeptType(HttpServletRequest request, Model model, String area) throws Exception {
//		List<Depart> department = entityService.getByArea(area);
//		System.out.println(department.size());
//		return net.ryian.commons.CollectionUtils.isNotEmpty(department);
//	}
	
	
	
	

	/**
	 * 进入列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "departmentData")
	public String departmentIndex(HttpServletRequest request, Model model) throws Exception {
		// 获取数据
		Long departmentId = userService.get(this.getCurrentUser().id).getDepartmentId();
		Depart depart = entityService.getByDepartmentId(departmentId);
		String deptType = depart.getDeptType();
		String areaId = depart.getAreaId();
		String publishCenter=ConfigHelper.getProperty("publishCenter");
		if(!publishCenter.equals(deptType)){
			//非发布中心，只显示本单位
			model.addAttribute("department", depart);
			model.addAttribute("departId", departmentId);
			model.addAttribute("departType", deptType);
			model.addAttribute("areaId", areaId);
			request.getSession().setAttribute("dp", deptType);
			return getNameSpace() + "departmentData";
		}
		Map<String, String> map = new HashMap<String, String>();
		if (areaId.equals("1")){
			map=getParameterMap(request);
		}else{
			map=getParameterMap(request);
			map.put("areaId", areaId);	
		}
		
		// 获取数据
		request.getSession().setAttribute("dp", deptType);
		
		map.put("id", entityService.findByName(ConfigHelper.getProperty("warningReleaseCenter")).getId().toString());
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page", page);
		model.addAttribute("departType", deptType);
		model.addAttribute("departId", departmentId);
		model.addAttribute("areaId", areaId);
		return getNameSpace() + "departmentData";
	}

	/**
	 * 加载搜索分页页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/departmentQuery")
	public String departmentQuery(HttpServletRequest request, Model model) {
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		Long departmentId = userService.get(this.getCurrentUser().id).getDepartmentId();
		//Depart depart = entityService.getByDepartmentId(departmentId);
      Depart depart = entityService.getByDepartmentId(departmentId);
		String deptType = depart.getDeptType();
		request.getSession().setAttribute("dp", deptType);

		//区域选择
		Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
		String areaId = depart.getAreaId();
		model.addAttribute("detId", deptId);
		if(areaId.equals("1")) {
			System.out.println(ConfigHelper.getProperty("warningReleaseCenter")+","+depart.getName());
			if(ConfigHelper.getProperty("warningReleaseCenter").equals(depart.getName())) {
		model.addAttribute("areas", dictService.getDictsByKey(DictKeys.AREA_ID).values());
			}else {
				String area = depart.getAreaId();
				List<Dict> areas = dictService.getDicList("area_id");
				List<Dict> areass =new ArrayList<Dict>();
				areass.add(areas.get(1-Integer.parseInt(area)));
				model.addAttribute("areas",areass);
			}
		}else {
			String area = depart.getAreaId();
			List<Dict> areas = dictService.getDicList("area_id");
			List<Dict> areass =new ArrayList<Dict>();
			areass.add(areas.get(1-Integer.parseInt(area)));
			model.addAttribute("areas",areass);
		}
		return getNameSpace() + "departmentQuery";
	}

	@RequestMapping(value = "depart")
	public void getDepartList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		List<Depart> departList = entityService.queryList();
		JSONObject obj = new JSONObject();
		List<String> li = new ArrayList<String>();
		for (int i = 0; i < departList.size(); i++) {
			li.add(departList.get(i).getName());
		}
		obj.put("departList", li);
		printJson(response, obj.toString());

	}
	
	/**
	 * 主体信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "departzt")
	public void getDepartztList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		List<Depart> departList = entityService.getZtList();
		JSONObject obj = new JSONObject();
		List<String> li = new ArrayList<String>();
		for (int i = 0; i < departList.size(); i++) {
			li.add(departList.get(i).getName());
		}
		obj.put("departList", li);
		printJson(response, obj.toString());
		
	}
	/**
	 * 单位信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "departdw")
	public void getDepartdwList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		List<Depart> departList = entityService.getdwList();
		JSONObject obj = new JSONObject();
		List<String> li = new ArrayList<String>();
		for (int i = 0; i < departList.size(); i++) {
			li.add(departList.get(i).getName());
		}
		obj.put("departList", li);
		printJson(response, obj.toString());
		
	}
	/**
	 * 发布中心信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "departzx")
	public void getDepartzxList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		List<Depart> departList = entityService.getZxList();
		JSONObject obj = new JSONObject();
		List<String> li = new ArrayList<String>();
		for (int i = 0; i < departList.size(); i++) {
			li.add(departList.get(i).getName());
		}
		obj.put("departList", li);
		printJson(response, obj.toString());
		
	}
	/**
	 * 预警办
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "departyjb")
	public void getDepartyjbList(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		List<Depart> departList = entityService.getYjbList();
		JSONObject obj = new JSONObject();
		List<String> li = new ArrayList<String>();
		for (int i = 0; i < departList.size(); i++) {
			li.add(departList.get(i).getName());
		}
		obj.put("departList", li);
		printJson(response, obj.toString());
		
	}

	@RequestMapping(value = "imageCode")
	public String getImageCode(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		try {
			int width = 100;
			int height = 42;
			// 取得一个4位随机字母数字字符串
			String s = RandomStringUtils.random(4, false, true);

			// 保存入session,用于与用户的输入进行比较.
			// 注意比较完之后清除session.
			HttpSession session = request.getSession();
			session.setAttribute(KEY_CAPTCHA, s);

			response.setContentType("images/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream out = response.getOutputStream();
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			// 设定背景色
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);

			// 设定字体
			Font mFont = new Font("Times New Roman", Font.BOLD, 24);// 设置字体
			g.setFont(mFont);

			// 画边框
			// g.setColor(Color.BLACK);
			// g.drawRect(0, 0, width - 1, height - 1);

			// 随机产生干扰线，使图象中的认证码不易被其它程序探测到
			g.setColor(getRandColor(160, 200));
			// 生成随机类
			Random random = new Random();
			for (int i = 0; i < 155; i++) {
				int x2 = random.nextInt(width);
				int y2 = random.nextInt(height);
				int x3 = random.nextInt(12);
				int y3 = random.nextInt(12);
				g.drawLine(x2, y2, x2 + x3, y2 + y3);
			}

			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(s, 20, 30);

			// 图象生效
			g.dispose();
			// 输出图象到页面
			ImageIO.write((BufferedImage) image, "JPEG", out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@RequestMapping(value = "/login")
	public void loginSure(HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = new User();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String departName = request.getParameter("departname"); // userName
			String password = request.getParameter("password"); // password
			String imageCode = request.getParameter("imageCode"); // 验证码
			HttpSession session = request.getSession();
			String code = (String) session.getAttribute("authCode");
			Depart depa = entityService.findByName(departName);
			// User user = new User();
			JSONObject obj = new JSONObject();
			user = userService.validateByDepa(depa.getId(), password);
			// List<User> users =new ArrayList<User>();
			// user.setId(1L);
			// users.add(user);
			if (user == null) {
				// TODO 这里要返回false；
				map.put("result", "false");
				map.put("msg", "用户名/密码不匹配");
			} else if (!code.equals(imageCode)) {
				map.put("result", "false");
				map.put("msg", "验证码错误");
			} else {
				map.put("result", "true");
				map.put("name", user.getUserName());
				map.put("password", user.getPassword());
				session.removeAttribute("authCode");
			}
			obj.put("map", map);
			printJson(response, obj.toString());
		} catch (Exception e) {

		}
		// 记录操作日志
		// logManager.insertLog(user.getId(), "登录系统", 1, null, IP);
		// TODO 这里返回的是要json格式的数据

	}





}
