package cn.movinginfo.tztf.sm.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ryian.commons.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sm.domain.EventType;
import cn.movinginfo.tztf.sm.domain.RDepartmentEnventType;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sm.service.RDepartmentEnventTypeService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.RUserRole;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.UserService;

/**
*
* @description:RDepartmentEnventType action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/sm/r-department-envent-type")
@SuppressWarnings("serial")
public class RDepartmentEnventTypeAction extends MagicAction<RDepartmentEnventType,RDepartmentEnventTypeService> {
	
	@Autowired
	private EventTypeService eventTypeService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private UserService userService;

	
	/**
	 * 选择部门获取对应的关联数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getChoicedType")
	public void getChoicedType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String departmentId = request.getParameter("departmentId");
		List<RDepartmentEnventType> rDepartmentEnventTypeList = new ArrayList<RDepartmentEnventType>();
		if(!StringUtils.isEmpty(departmentId)){
			rDepartmentEnventTypeList = entityService.getByDepartmentId(departmentId);
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(rDepartmentEnventTypeList));
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdateTypes")
	public void saveUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String department = request.getParameter("department");
		String types = request.getParameter("typeIds");
		if(!StringUtils.isEmpty(types)){ //如果存在类别编号
			String[] typeIds = types.split(",");
			List newTypeIdList = new ArrayList();  //新的类别数组
			//遍历数组往集合里存元素
			for(int i=0;i<typeIds.length;i++){
				//如果集合里面没有相同的元素才往里存
				if(!newTypeIdList.contains(typeIds[i])){
					newTypeIdList.add(typeIds[i]);
				}
			}
			List oldTypeIdList = new ArrayList(); //原类别数组
			if(!StringUtils.isEmpty(department)){
				List<RDepartmentEnventType> rDepartmentEnventTypeList = entityService.getByDepartmentId(department);
				if(rDepartmentEnventTypeList.size()>0){
					for(RDepartmentEnventType rdet:rDepartmentEnventTypeList){
						oldTypeIdList.add(rdet.getEventTypeId());
					}
				}
				List deleteList = new ArrayList(); //需要删除的
				for(int n=0;n<oldTypeIdList.size();n++){
					if(!newTypeIdList.contains(oldTypeIdList.get(n).toString())){
						deleteList.add(oldTypeIdList.get(n));
					}
				}
				List insertList = new ArrayList(); //需要新增的
				for(int m=0;m<newTypeIdList.size();m++){
					if(!oldTypeIdList.contains(Long.parseLong((String) newTypeIdList.get(m)))){
						insertList.add(newTypeIdList.get(m));
					}
				}
				if(deleteList.size()>0){ //删除需要删除的
					for(int d=0;d<deleteList.size();d++){
						RDepartmentEnventType rDepartmentEnventType = entityService.getByDeptIdAndType(department,deleteList.get(d).toString());
						if(rDepartmentEnventType != null){
							Long id = rDepartmentEnventType.getId();
							entityService.deleteById(id);
						}
					}
				}
				if(insertList.size()>0){ //新增需要新增的
					for(int a=0;a<insertList.size();a++){
						RDepartmentEnventType rDepartmentEnventType = new RDepartmentEnventType();
						rDepartmentEnventType.setDepartmentId(Long.parseLong(department));
						rDepartmentEnventType.setEventTypeId(Long.parseLong((String) insertList.get(a)));
						rDepartmentEnventType.setCreator(this.getCurrentUser().id);
						rDepartmentEnventType.setCreateDate(new Date());
						entityService.saveOrUpdate(rDepartmentEnventType);
					}
				}
			}
		}else{ //部门下无类别
			List<RDepartmentEnventType> rDepartmentEnventTypeList = entityService.getByDepartmentId(department);
			for(RDepartmentEnventType r:rDepartmentEnventTypeList){
				Long id = r.getId();
				entityService.deleteById(id);
			}
		}
		boolean data = true;
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(data));
	}
	
	/**
	 * 进入类别管理界面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/choiceType")
	public String choiceType(HttpServletRequest request,Model model) {
		Long userId =userService.get(this.getCurrentUser().id).getId();
		//第一层类别
		List<EventType> eventTypeFirsts = eventTypeService.getByOperateType("1");
		model.addAttribute("eventTypeFirsts", eventTypeFirsts);
		//第二层类别
		List<EventType> eventTypeSeconds = eventTypeService.getByOperateType("2");
		model.addAttribute("eventTypeSeconds", eventTypeSeconds);
		//第三层类别
		List<EventType> eventTypeThirds = eventTypeService.getByOperateType("3");
		model.addAttribute("eventTypeThirds", eventTypeThirds);
		//部门
		/*List<Depart> depts = deptService.getAll();
		model.addAttribute("depts", depts);*/
		//获取登录部门
		Long departmentId = userService.get(this.getCurrentUser().id).getDepartmentId();
		model.addAttribute("departmentId", departmentId);
		model.addAttribute("userId",userId);
        return getNameSpace() + "envent_type_department";
    }
	
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteType")
	public void deleteDepartment(HttpServletRequest request,HttpServletResponse response, Model model)
			throws Exception {
		boolean data = true;
		String[] id = request.getParameterValues("typeId[]");
		for (String ids : id) {
			if (!StringUtils.isEmpty(ids)) {
				eventTypeService.logicRemove(Long.parseLong(ids));
				data = false;
			}
		}
		if (data == false) {
			printJson(response, com.alibaba.fastjson.JSON.toJSONString("删除成功"));
		}else{
			printJson(response, com.alibaba.fastjson.JSON.toJSONString("删除失败"));
	
		}
	}
	
	/**
	 * 添加类别管理
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addOrUpdateTypes")
	public void addOrUpdateTypes(HttpServletRequest request, HttpServletResponse response){
		String eventTypeFirsts  = request.getParameter("eventTypeFirsts");
		String eventTypeSeconds = request.getParameter("eventTypeSeconds");
		String eventTypeThirds = request.getParameter("eventTypeThirds");
		String nationalCode = request.getParameter("nationalCode");
		String data =eventTypeService.getAddOrUpdateTypes(eventTypeFirsts,eventTypeSeconds,eventTypeThirds,nationalCode);
		
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(data));
	}
	
	
	/**
	 * 类别管理添加中的二级联动
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/clickTypes")
	public String clickTypes(HttpServletRequest request,HttpServletResponse response,Model model){
		String eventTypeFirsts  = request.getParameter("eventTypeFirsts");
		if (StringUtils.isEmpty(eventTypeFirsts)) {
			List<EventType> clickTypeFirst = eventTypeService.getByCode("11000");
			printJson(response, com.alibaba.fastjson.JSON.toJSONString(clickTypeFirst));
			return getNameSpace() + "envent_type_department";
		}
		List<EventType> clickTypes =eventTypeService.getClickTypes(eventTypeFirsts);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(clickTypes));
		return getNameSpace() + "envent_type_department";
	}
	
	
}
