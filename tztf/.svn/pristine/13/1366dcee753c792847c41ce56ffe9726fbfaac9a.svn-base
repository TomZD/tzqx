package cn.movinginfo.tztf.sev.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.Personroup;
import cn.movinginfo.tztf.sev.domain.Temperson;
import cn.movinginfo.tztf.sev.service.PersonGroupService;
import cn.movinginfo.tztf.sev.service.TempersonService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;

@Controller
@RequestMapping("/sev/em")
public class TempersonAction extends MagicAction<Temperson, TempersonService>{
      
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private PersonGroupService personGropService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("tempersonQuery")
	public String indexMenu(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return getNameSpace() + "tempersonQuery";
	}
	
	/**
	 * 获取列表数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "tempersonData")
	public String tempersonData(HttpServletRequest request, Model model)
			throws Exception {
		Long departmentId = userService.get(this.getCurrentUser().id).getDepartmentId();
		String areaId = deptService.getByDepartmentId(departmentId).getAreaId();
		Map<String, String> map = new HashMap<String, String>();
		if(areaId.equals("1")) {
		map=getParameterMap(request);
		}else {
			map=getParameterMap(request);
			map.put("areaId", areaId);
		}
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter())); 
		model.addAttribute("page", page);
		 return getNameSpace() + "tempersonData";
	}
	
	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editTemperson")
	public void editTemperson(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Temperson temperson = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(temperson));
	}
	
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteTemperson")
	public String deleteTemperson(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
				entityService.logicRemove(Long.parseLong(id));
			
		}
		return "redirect:/sev/em/tempersonQuery";
	}
	
	/**
	 * 获取区域
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAreas")
	public void getAreas(HttpServletRequest request, HttpServletResponse response) {
		Long departmentId = userService.get(this.getCurrentUser().id).getDepartmentId();
		String areaId = deptService.getByDepartmentId(departmentId).getAreaId();
		List<Dict> areas = new ArrayList<Dict>();
		if(areaId.equals("1")) {
		List<Personroup> person = personGropService.selectAll();
		Set<String> set = new HashSet<String>();
		for(Personroup per : person) {
			set.add(per.getAreaId());
		}
		for(String s :set) {
			String content = dictService.getContent("area_id", s);
			 Dict dict =new Dict();
			 dict.setValue(s);
			dict.setContent(content);
			areas.add(dict);
		   }
		}else {
			String content = dictService.getContent("area_id", areaId);
			Dict dict =new Dict();
			 dict.setValue(areaId);
			dict.setContent(content);
			areas.add(dict);
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(areas));
	}
	
	/**
	 * 获取编辑区域
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "getEditArea")
	public void getEditArea(HttpServletRequest request, HttpServletResponse response,Long id) {
		Temperson temperson = entityService.findTempersonById(id);
		String areaId = temperson.getAreaId();
		String content = dictService.getContent("area_id", areaId);
		Dict dict =new Dict();
		dict.setValue(areaId);
		dict.setContent(content);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(dict));
		
	}
	/**
	 * 获取相对应的区域部门
	 * @param request
	 * @param response
	 * @param areaId
	 */
	@RequestMapping(value = "getDepts")
	public void getDepts(HttpServletRequest request, HttpServletResponse response,String areaId) {
		List<Depart> departs = deptService.findListByAreaId(areaId);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(departs));
	}
	/**
	 * 获取编辑部门
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "getEditDept")
	public void getEditDept(HttpServletRequest request, HttpServletResponse response,Long id) {
		Temperson temperson = entityService.findTempersonById(id);
		Long deptId = temperson.getDepartmentId();
		Depart de = deptService.getByDepartmentId(deptId);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(de));
		
	}
	
	/**
	 * 新增保存
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Temperson temperson = bindEntity(request, entityClass);
		Long id = temperson.getId();
		String phone = temperson.getPhone();
		if(id != null) {
		Temperson temper = entityService.findTempersonByNotIdAndPhone(id, phone);
		if(temper !=null) {
			printJson(response, com.alibaba.fastjson.JSON.toJSONString("该电话号码已存在！"));
			return;
		}
		   temperson.setUpdateDate(new Date());
		   temperson.setUpdator(this.getCurrentUser().id);
		}else {
			Temperson tem = entityService.findTempersonByPhone(phone);
			if(tem != null) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该电话号码已存在！"));
				return;
			     }
			temperson.setCreateDate(new Date());
			temperson.setCreator(this.getCurrentUser().id);
		}
		String areaId = temperson.getAreaName();
		String areaName = dictService.getContent("area_id", areaId);
		String deptId = temperson.getDeptName();
		String deptName = deptService.findById(Long.parseLong(deptId)).getName();
		temperson.setDepartmentId(Long.parseLong(deptId));
		temperson.setDeptName(deptName);
		temperson.setAreaId(areaId);
		temperson.setAreaName(areaName);
		entityService.saveOrUpdate(temperson);
	}
}
