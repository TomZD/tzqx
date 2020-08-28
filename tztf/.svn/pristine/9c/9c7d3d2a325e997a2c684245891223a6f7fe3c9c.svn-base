package cn.movinginfo.tztf.sen.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.sen.domain.DeptPermission;
import cn.movinginfo.tztf.sen.domain.KeyPlace;
import cn.movinginfo.tztf.sen.domain.SenPermission;
import cn.movinginfo.tztf.sen.domain.Tabstation;
import cn.movinginfo.tztf.sen.service.DeptPermissionService;
import cn.movinginfo.tztf.sen.service.SenPermissionService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.UserXt;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.UserXtService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;

/**
*
* @description:DeptPermission action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/yzt/deptPermission")
@SuppressWarnings("serial")
public class DeptPermissionAction extends MagicAction<DeptPermission,DeptPermissionService> {
	
	@Autowired
	private DeptService deptService;
	@Autowired
	private SenPermissionService senPermissionService;
	@Autowired
	private UserXtService userXtService;

	/**
	 * 权限管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("deptPermissionQuery")
	public String deptPermissionQuery(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return "/yzt/deptPermission/deptPermissionQuery";
	}
	
	/**
	 * 获取列表数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deptPermissionData")
	public String deptPermissionData(HttpServletRequest request, Model model)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map=getParameterMap(request);
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter())); 
		model.addAttribute("page", page);
		
		 String maxDeptId =ConfigHelper.getProperty("dept_id");
		 model.addAttribute("maxDeptId",maxDeptId);
		 return getNameSpace() + "deptPermissionData";
	}
	
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletedeptPermission")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/yzt/deptPermission/deptPermissionQuery";
	}
	/**
	 * 获取所有部门
	 * @return
	 */
	@RequestMapping(value = "/getAllDept")
	@ResponseBody
	public List<UserXt> getAllDept() {
		//return deptService.getAll();
		return userXtService.getAll();
	}
	/**
	 * 获取所有管理的菜单
	 * @return
	 */
	@RequestMapping(value = "/getAllMenu")
	@ResponseBody
	public List<SenPermission> getAllMenu() {
		return senPermissionService.getOnePermission();//获取code为1的
	}
	
	/**
	 * 获取下级菜单
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getLowMenu")
	public void getLowMenu(HttpServletRequest request, HttpServletResponse response,int id) {
		List<SenPermission> list = senPermissionService.getPermissionByPid(id);
		JSONArray json = new JSONArray();
		for (SenPermission sp : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", sp.getId());
			jo.put("text", sp.getName());
			json.put(jo);
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(json.toString()));
	}
	
	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "edit")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		DeptPermission deptPermission = entityService.get(Long.parseLong(id));
		Long deptID = deptPermission.getDeptId();
    	Long permissionId = deptPermission.getPermissionId();
    	//Depart dept = deptService.get(deptID);
		UserXt userXt= userXtService.get(deptID);
    	SenPermission senPermission = senPermissionService.get(permissionId);
    	//deptPermission.setDept(dept);
		deptPermission.setUserXt(userXt);
    	deptPermission.setSenPermission(senPermission);
    	//List<Depart> deptList =  deptService.getDepartByNotId(deptID);
		List<UserXt> userXtList =  userXtService.getUserXtByNotId(deptID);
    	List<SenPermission> permissionList = senPermissionService.getPermissionByNotId(permissionId);//code为1的
    	deptPermission.setUserXtList(userXtList);
    	deptPermission.setPermissionList(permissionList);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(deptPermission));
	}
	
	
	/**
	 * 新增保存和修改
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		DeptPermission deptPermission = bindEntity(request, entityClass);
		Long deptId = deptPermission.getDeptId();
		Long permissionId = deptPermission.getPermissionId();
		List<DeptPermission> list = entityService.getPermissionByDeptIdAndPermissId(deptId, permissionId);
			if(list.size() != 0) {
				Long deptPermissionId = list.get(0).getId();
				if(deptPermission.getId() != null) {
					if(!deptPermission.getId().equals(deptPermissionId) ) {
						printJson(response, com.alibaba.fastjson.JSON.toJSONString("该权限已拥有！"));
					}else {
						entityService.saveOrUpdate(deptPermission);
					}
				}else {
					printJson(response, com.alibaba.fastjson.JSON.toJSONString("该权限已拥有！"));
				}
			}else {
				entityService.saveOrUpdate(deptPermission);
			}
		
	}

}
