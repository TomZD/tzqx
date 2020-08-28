package cn.movinginfo.tztf.sys.action;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sys.domain.Decision;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DecisionService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;


@Controller
@RequestMapping("/sys/decision")
public class DecisionAction extends MagicAction<Decision, DecisionService>{
     
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("decisionQuery")
	public String indexMenu(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return "/sys/decision/decisionQuery";
	}
	
	/**
	 * 获取列表数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("decisionData")
	public String decisionData(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		
		User user = userService.get(this.getCurrentUser().id);
		String areaId = user.getAreaId();
		String deptType = deptService.getDeptById(user.getDepartmentId()).getDeptType();
//		Long userId = user.getId();
		Map<String,String> map = new HashMap<String,String>();
		if(areaId.equals("1")) {
			if(deptType.equals("2")) {
			map = getParameterMap(request);
			}else {
				map = getParameterMap(request);
				map.put("deptId", String.valueOf(user.getDepartmentId()));
			}
		}else {
			if(deptType.equals("2")) {
			map = getParameterMap(request);
			map.put("areaId", areaId);
			}else {
				map = getParameterMap(request);
				map.put("deptId", String.valueOf(user.getDepartmentId()));
			}
		}
		
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter())); 
		model.addAttribute("page", page);
		model.addAttribute("deptType", deptType);
		return getNameSpace() + "decisionData";
	}
	
	/**
	 * 新增修改保存
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Decision decision = bindEntity(request, entityClass);
		User user = userService.get(this.getCurrentUser().id);
		String areaId = user.getAreaId();
		String deptType = deptService.getDeptById(user.getDepartmentId()).getDeptType();
		String deptId = decision.getDeptId();
		decision.setAreaId(areaId);
		if("2".equals(deptType)) {
			decision.setDeptType(deptService.get(Long.parseLong(deptId)).getDeptType());
		}else {
			decision.setDeptType(deptType);
			decision.setDeptId(String.valueOf(user.getDepartmentId()));
		}
		
		Long id = decision.getId();
		if(id != null) {
			decision.setUpdateDate(new Date());
			decision.setUpdator(this.getCurrentUser().id);
		}else {
			decision.setCreateDate(new Date());
			decision.setCreator(this.getCurrentUser().id);
		}
		entityService.saveOrUpdate(decision);
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteDecision")
	public String deleteDecision(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/sys/decision/decisionQuery";
	}
	
	/**
	 * 获取部门下拉框
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getDepts")
	public void getDepts(HttpServletRequest request, HttpServletResponse response) {
		List<Depart> list = deptService.getZtList();
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(list));
		
	}
	/**
	 * 获取编辑部门下拉框
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getEditDepts")
	public void getEditDepts(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Decision decision = entityService.get(Long.parseLong(id));
		String deptId = decision.getDeptId();
		Depart depart = deptService.get(Long.parseLong(deptId));
		decision.setDeptName(depart.getName());
		List<Depart> deptList = deptService.getDepartNotId(Long.parseLong(deptId));
		decision.setDeptList(deptList);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(decision));
		
	}
	
	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editDecision")
	public void editDecision(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Decision decision = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(decision));
	}
	

    /**
     * 导入数据
     * @param file
     * @param request
     * @throws Exception
     */
	@RequestMapping(value = "importDecision")
	@ResponseBody
	public void uploadPage(@RequestParam("file")  CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		User user = userService.get(this.getCurrentUser().id);
		String areaId = user.getAreaId();
		String deptType = deptService.getDeptById(user.getDepartmentId()).getDeptType();
		Long userId = user.getId();
		ServletContext servletCtx = request.getSession().getServletContext();
		String root = servletCtx.getRealPath("/upload");
		File tmpFile = new File(root);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		String newFileName = UUID.randomUUID().toString();
		String page = file.getOriginalFilename();
		String[] xx = page.split("\\.");
		String ext = xx[xx.length - 1];
		root = root + "/" + newFileName + "." + ext;
		File newFile = new File(root);
		file.transferTo(newFile);
		List<Decision> list = entityService.readXls(root);
		List<String> li = new ArrayList<String>();
		   for(int x=0;x<list.size();x++) {
			   String phone = list.get(x).getPhone();
			   boolean isPhone = entityService.validatemobile(phone);
			   Decision oldDe = entityService.findDecisionByPhone(phone);
			   if(oldDe != null || isPhone != true) {
				   li.add("第"+ (x+1) +"条数据导入失败");
			   }else {
				   Decision decision = new Decision();
				   decision.setName(list.get(x).getName());
				   decision.setDepartment(list.get(x).getDepartment());
				   decision.setPhone(list.get(x).getPhone());
				   decision.setAreaId(areaId);
				   decision.setDeptType(deptType);
				   decision.setUserId(String.valueOf(userId));
				   entityService.saveOrUpdate(decision); 
			   }
		   }
		   entityService.deleteExcel(root);
		   printJson(response, com.alibaba.fastjson.JSON.toJSONString(li));
	}
	
	/**
	 * 获取所有的电话号码
	 * @return
	 */
	@RequestMapping(value = "allPhones")
	@ResponseBody
	public List<String> getAllPhones() {
		List<Decision> decisions = entityService.getAllDecision();
		List<String> phones = new ArrayList<String>();
		for(Decision de :decisions) {
			phones.add(de.getPhone());
		}
		return phones;
	}
	

}
