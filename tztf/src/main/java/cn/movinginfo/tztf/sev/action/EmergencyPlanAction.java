package cn.movinginfo.tztf.sev.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.IpAddressUtil;
import cn.movinginfo.tztf.sev.domain.EmPersonResult;
import cn.movinginfo.tztf.sev.domain.EmergencyPlan;
import cn.movinginfo.tztf.sev.domain.Emperson;
import cn.movinginfo.tztf.sev.domain.Page;
import cn.movinginfo.tztf.sev.mapper.AreaResult;
import cn.movinginfo.tztf.sev.service.EmPersonService;
import cn.movinginfo.tztf.sev.service.EmergencyPlanService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.domain.Record;
import cn.movinginfo.tztf.sys.service.AreaService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;
import cn.movinginfo.tztf.sys.service.RecordService;
import cn.movinginfo.tztf.sys.service.UserService;
import net.ryian.orm.domain.BaseEntity;

@Controller
@RequestMapping("/sev/emergency-plan")
public class EmergencyPlanAction extends MagicAction<EmergencyPlan, EmergencyPlanService> {
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
	@Autowired
	private EmPersonService emPersonService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private RecordService recordService;
	
	@RequestMapping(value = "data")
	public String data(String alarmId, HttpServletRequest request, Model model) {
		// 获取数据
		Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
		Depart depart=deptService.get(deptId);
		String deptType = depart.getDeptType();
		String areaId = depart.getAreaId();
		Map<String, String> map = new HashMap<String, String>();
		if(areaId.equals("1")) {
			if("18".equals(String.valueOf(deptId))) {
				map=getParameterMap(request);
				}else {
					map=getParameterMap(request);	
					map.put("departmentId", String.valueOf(deptId));
				}		
		}else {
			if("2".equals(deptType)) {
				map=getParameterMap(request);
				map.put("areaId", areaId);
			}else {
				map=getParameterMap(request);
				map.put("areaId", areaId);	
				map.put("deptType", deptType);
				map.put("departmentId", String.valueOf(deptId));
			}
		}
//		PageInfo<?> pageInfo = entityService.queryPaged(map);
//		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		PageInfo<EmergencyPlan> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo));
		model.addAttribute("areaId",areaId);
		model.addAttribute("deptType", deptType);
		model.addAttribute("departmentId", deptId);
		model.addAttribute("page", page);
		model.addAttribute("pageInfo", pageInfo);
		return getNameSpace() + "data";
	}

	@RequestMapping(value = "query")
	public String query(HttpServletRequest request, Model model) {
		// 上报部门
		Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
		Depart depart=deptService.get(deptId);
		String type = depart.getDeptType();
		String areaId = depart.getAreaId();
		if(areaId.equals("1")) {
			if("2".equals(type)) {
				List<Depart> depts = deptService.queryList();
				model.addAttribute("depts", depts);
			}else {
				//List<Depart> depts = deptService.queryListByAreaAndType(areaId);
				List<Depart> depts = deptService.getSelfDepts(deptId);
				model.addAttribute("depts", depts);
			}
		}else {
			if("2".equals(type)) {
				List<Depart> depts = deptService.queryListByAreaAndType(areaId,type);
				model.addAttribute("depts", depts);
			}else {
				List<Depart> depts = deptService.getSelfDepts(deptId);
				//List<Depart> depts = deptService.queryListByAreaAndTypeAndId(areaId,type,deptId);
				model.addAttribute("depts", depts);
			}
			
		}
//		if(areaId.equals("1")) {
//			List<Depart> depts = deptService.queryListByType(type);
//			model.addAttribute("depts", depts);	
//			}else {
//				List<Depart> depts = deptService.queryListByAreaAndType(areaId,type);
//				model.addAttribute("depts", depts);
//			}
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return getNameSpace() + "query";
	}

	@Override
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			Record record = new Record();//日志
			Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
			Long userIds = userService.get(this.getCurrentUser().id).getId();
			String userName = userService.get(this.getCurrentUser().id).getName();
			Depart depart=deptService.get(deptId);
			String deptType = depart.getDeptType();
			String areaId = depart.getAreaId();
			EmergencyPlan emergencyPlan = bindEntity(request, entityClass);
			String title = emergencyPlan.getTitle();
			emergencyPlan.setDepartmentId((Long) request.getSession().getAttribute("departmentId"));
			emergencyPlan.setAreaId(areaId);
			emergencyPlan.setDeptType(deptType);
			if (emergencyPlan.getId() == null) {
				emergencyPlan.setCreateDate(new Date());
				emergencyPlan.setCreator(this.getCurrentUser().id);
				record.setUserName(userName);
				record.setUserId(userIds);
				record.setContent(userName+"用户添加了("+title+")的应急预案");
				record.setIpAddress(IpAddressUtil.getIpAddr(request));//添加本机的ip地址
				record.setOperateDate(new Date());
				recordService.saveRcord(record);
			} else {
				emergencyPlan.setUpdateDate(new Date());
				emergencyPlan.setUpdator(this.getCurrentUser().id);
				record.setUserName(userName);
				record.setUserId(userIds);
				record.setContent(userName+"用户修改了("+title+")的应急预案");
				record.setIpAddress(IpAddressUtil.getIpAddr(request));
				record.setOperateDate(new Date());
				recordService.saveRcord(record);
			}
			entityService.saveOrUpdate(emergencyPlan);
			printJson(response, messageSuccuseWrap());
		} catch (Exception e) {
			logger.error("save", e);
			printJson(response, messageFailureWrap("保存失败！"));
		}
	}

	@RequestMapping(value = "editEmergencyPlan/{id}")
	public void edit2(HttpServletRequest request, @PathVariable("id") Long id, Model model,
			HttpServletResponse response) throws Exception {
		if (id != null) {
			BaseEntity entity = entityService.get(id);
			model.addAttribute("model", entity);
			beforeEdit(request, model, entity);
			printJson(response, com.alibaba.fastjson.JSON.toJSONString(entity));
		}
	}
	
	
	
	/**
	 * 钉钉端开始
	 * @param request
	 * @param model
	 * @return
	 * 钉钉首页
	 */
	@RequestMapping("index")
	public String indexDD(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return "/sev/em/emperson";
	}
	
	
	
	
	/**
	 * 根据登录名       获得钉钉所有责任人
	 * @param 	   request
	 * @return     所有钉钉人员信息
	 */
	@RequestMapping("personlist")
	@ResponseBody
	public List<Emperson> getAllPersonDD(HttpServletRequest request,String keyword, String sex, Long userid){
		List<Emperson> result = new ArrayList<Emperson>();
		if(userid ==0 || userid == null) {
			userid = this.getCurrentUser().id;
		}
		Long deptId = userService.get(userid).getDepartmentId();  //部门id
		String areaId = userService.get(userid).getAreaId();		//区域id
		Depart depart = deptService.getByDepartmentId(deptId);
		String type = depart.getDeptType();
		if("2".equals(type)) {		  //发布中心
			if("1".equals(areaId)) {  //预警发布中心（老大）
				result = emPersonService.getAllValid(keyword, sex);
				getNameList(result);
			}else {					  // 其他区县发布中心
				result = emPersonService.getByAreaId(areaId,keyword,sex);
				getNameList(result);
			}
		}else {							// 非发布中心
			result = emPersonService.getSameDeptId(deptId);
			getNameList(result);
		}
		return result;
	}

	private void getNameList(List<Emperson> result) {
		for (Emperson e : result) {
			e.setAreaname(dictService.findByCond(e.getAreaId()).getContent());
			e.setDeptname(deptService.get(e.getDepartmentid()).getName());
		}
	}
	
	/**
	 * @param ids   主键，多个逗号分割
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public boolean delDD(String ids){
		List<Integer> str2int = new ArrayList<Integer>();
		try {
			List<String> result = Arrays.asList(ids.split(","));  
			for (String str : result) {
				str2int.add(Integer.parseInt(str));
			}
			emPersonService.deleteByIds(str2int);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * @param num    页码
	 * @param size   条数
	 * @return
	 */
	@RequestMapping("search")
	@ResponseBody
	public EmPersonResult  getPersonWEB(int num, int size,String sex, String keyword){
		PageHelper.startPage(num,size);
		List<Emperson> result = new ArrayList<Emperson>();
			Long userid = this.getCurrentUser().id;
		Long deptId = userService.get(userid).getDepartmentId();  //部门id
		String areaId = userService.get(userid).getAreaId();		//区域id
		Depart depart = deptService.getByDepartmentId(deptId);
		String type = depart.getDeptType();
		if("2".equals(type)) {		  //发布中心
			if("1".equals(areaId)) {  //预警发布中心（老大）
				result = emPersonService.getAllValid(keyword,sex);
				getNameList(result);
			}else {					  // 其他区县发布中心
				result = emPersonService.getByAreaId(areaId,keyword,sex);
				getNameList(result);
			}
		}else {							// 非发布中心
			result = emPersonService.getSameDeptId(deptId);
			getNameList(result);
		}
		PageInfo<Emperson> page = new PageInfo<Emperson>(result);
		EmPersonResult pr = getPageResult(page);
		return pr;
		
	}
	
	private EmPersonResult getPageResult(PageInfo<Emperson> page) {
		EmPersonResult pr = new EmPersonResult();
		pr.setList(page.getList());
		Page p = new Page();
		p.setFirstItemOnPage(page.getStartRow());
		p.setHasNextPage(page.isHasNextPage());
		p.setHasPreviousPage(page.isHasPreviousPage());
		p.setIsFirstPage(page.isIsFirstPage());
		p.setIsLastPage(page.isIsLastPage());
		p.setLastItemOnPage(page.getEndRow());
		p.setPageCount(page.getPages());  // 总页数
		p.setPageNumber(page.getPageNum());
		p.setPageSize(page.getPageSize());
		p.setTotalItemCount(page.getTotal());
		pr.setPage(p);
		return pr;
	}

	/**
	 * 新增或修改
	 * @param p   实体类
	 * @return
	 */
	@RequestMapping("saveorupdate")
	@ResponseBody
	public boolean add(Integer id ,String name,String phone,String areaId,String sex,String ps,Long departmentid ){
		Emperson p = new Emperson();
		System.out.println("saveorupdate method --------");
		p.setId(id);
		p.setName(name);
		p.setAreaId(areaId);
		p.setSex(sex);
		p.setPs(ps);
		p.setDepartmentid(departmentid);
		p.setPhone(phone);
		try {
			p.setCreateTime(new Date());
			p.setValid(1);
			if(p.getId() != null && p.getId() != 0) {    	//更新
				emPersonService.update(p);  
			}else{  						//保存
				emPersonService.save(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	
	/**
	 * @param id   主键
	 * @return
	 */
	@RequestMapping("detail")
	@ResponseBody
	public Emperson getDetail(String id){
		Emperson e = emPersonService.findById(Integer.parseInt(id));
		Dict dict = dictService.findByCond(e.getAreaId());
		e.setAreaname(dict.getContent());
		e.setDeptname(deptService.get(e.getDepartmentid()).getName());
		return e;
	}
	
	
	/**
	 * @param id   获取区域
	 * @return
	 */
	@RequestMapping("getArea")
	@ResponseBody
	public List<AreaData> getArea(HttpServletRequest request){
		long  userid = this.getCurrentUser().id;
		
		Long deptId = userService.get(userid).getDepartmentId();  //部门id
		String areaId = userService.get(userid).getAreaId();		//区域id
		Depart depart = deptService.getByDepartmentId(deptId);
		String type = depart.getDeptType();		
		List<AreaData> arealist = new ArrayList<AreaData>();
		List<AreaResult> list = null;
		List<Depart> depts = new ArrayList<Depart>();
		//类型
		if("2".equals(type)) {		  //发布中心
			if("1".equals(areaId)) {  //预警发布中心（老大）
				list = emPersonService.getDistinctAreaId();
				for (AreaResult a : list) {
					getData(arealist, a);
				}
			}else {					  // 其他区县发布中心
				Dict area = dictService.findByCond(areaId);
				getOtherArea(arealist, area);
			}
		}else {						  // 非发布中心
			AreaData data = new AreaData();
			//根据部门id获得Dict
			getAreaByDept(deptId, arealist, depts, data);
		}
		return  arealist;
	}

	private void getAreaByDept(Long deptId, List<AreaData> arealist,
			List<Depart> depts, AreaData data) {
		AreaResult area = emPersonService.getByDeptId(deptId);
		depts.add(deptService.getByDepartmentId(deptId));
		data.setDepart(depts);
		data.setResult(area);
		arealist.add(data);
	}

	private void getOtherArea(List<AreaData> arealist, Dict area) {
		List<Depart> depts;
		AreaData data = new AreaData();
		AreaResult r = new AreaResult();
		r.setContent(area.getContent());
		r.setValue(area.getValue());
		depts = deptService.getByAreas(area.getValue());
		data.setResult(r);
		data.setDepart(depts);
		arealist.add(data);
	}

	private void getData(List<AreaData> arealist, AreaResult a) {
		List<Depart> depts;
		AreaData data = new AreaData();
		data.setResult(a);
		depts = deptService.getByAreas(a.getValue());
		data.setDepart(depts);
		arealist.add(data);
	}
	
	
	
	
}
