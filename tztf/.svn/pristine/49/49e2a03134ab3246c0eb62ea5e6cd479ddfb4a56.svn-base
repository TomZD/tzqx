package cn.movinginfo.tztf.sys.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.AfterFilter;

import cn.movinginfo.tztf.common.action.BaseMagicAction;
import cn.movinginfo.tztf.common.utils.IpAddressUtil;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.AlarmNotice;
import cn.movinginfo.tztf.sev.domain.Publish;
import cn.movinginfo.tztf.sev.service.AlarmNoticeService;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.PublishService;
import cn.movinginfo.tztf.sys.domain.*;
import cn.movinginfo.tztf.sys.service.*;
import cn.movinginfo.tztf.sys.shiro.ShiroDBRealm;
import net.ryian.core.SystemConfig;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@Controller
@RequestMapping("/sys/index")
public class IndexAction extends BaseMagicAction{

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private AlarmService alarmService;
	@Autowired
	private ShiroDBRealm shiroDBRealm;
	@Autowired
	private AlarmNoticeService alarmNoticeService;
	@Autowired
	private FooterContactService footerService;
	@Autowired
	private PublishService publishService;
	@Autowired
	private RecordService recordService;

	private void loadPermission() {
		if(shiroDBRealm.getAuthorizationInfo(this.getCurrentUser()) == null) {
			shiroDBRealm.clearCachedAuthorizationInfo(this.getCurrentUser());
			shiroDBRealm.isPermitted(SecurityUtils.getSubject().getPrincipals(), Long.toString(System.currentTimeMillis()));
		}
	}
	
	private Alarm alrrm = new Alarm();
	@RequestMapping(value = "getData")
	public void getData(HttpServletResponse response) {
		Alarm alarm = new Alarm();
		if(getCurrentUser()!=null){
			Long userId = getCurrentUser().id;
			User user = userService.get(userId);
			Long deptId = user.getDepartmentId();
			Alarm alrm = alarmService.getNewOne(deptId);
			if(alrm!=null && alrm.getId() !=null){
				if(alrrm.getId() !=null){
					if(alrrm.getId() !=alrm.getId() &&alrm.getCompleteDate().getTime()>alrrm.getCompleteDate().getTime()){
						alrrm = alrm;
						alarm = alrm;
					}
				}else{
					alarm = alrm;
					alrrm = alrm;
				}
			}
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(alarm));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(HttpServletRequest request) throws Exception {
		loadPermission();
		
		List<Permission> permissions = permissionService.getPermissionsByUser(getCurrentUser().id);
		//气象台用户不需要类别管理
		Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
    	String deptName = deptService.get(deptId).getName();
//		if(deptName.contains("气象台")){
//			for(int i=0;i<permissions.size();i++){
//				if(permissions.get(i).getName().equals("类别管理")){
//					permissions.remove(i);
//					break;
//				}
//			}
//		}
		//环保局不需要传真管理
		if(deptName.contains("环保局")){
			for(int i=0;i<permissions.size();i++){
				if(permissions.get(i).getName().equals("传真管理")){
					System.out.println(permissions.get(i).getName().equals("传真管理"));
					permissions.remove(i);
					break;
				}
			}
		}
		for (Permission permission : permissions) {


			
		}
		int level = SystemConfig.INSTANCE.getValue("menu_level", 2);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(level == 2) {
			JSONObject o = new JSONObject();
			o.put("menus",permissions);
			request.setAttribute("menus", o.toJSONString());
		} else if(level == 3) {
			List<Permission> modules = getCurrentUser().permissions;
			request.setAttribute("modules", modules);
			if(!CollectionUtils.isEmpty(modules)) {
				request.setAttribute("menus", JSON.toJSONString(getCurrentUser().permissions.get(0)));
			} else {
				request.setAttribute("menus", "[]");
			}
		}
		request.setAttribute("level", level);
		request.setAttribute("time", df.format(new Date()));
		request.setAttribute("username", getCurrentUser().getName());
		//2017.11.21
		Record record = new Record();
		Long userId = getCurrentUser().id;
		String userName = userService.findByUserId(userId).getName();
		record.setUserName(userName);
		record.setUserId(userId);
		record.setOperateDate(new Date());
		record.setContent(userName+"用户登录");
		record.setIpAddress(IpAddressUtil.getIpAddr(request));//添加本机的ip地址
		recordService.saveRcord(record);
		User user = userService.get(userId);
		if(user.getName().indexOf("发布中心")>-1){
			List<Alarm>  listAlarm = alarmService.getwaitAudit();
			request.setAttribute("needLength",listAlarm.size());
		}
//		Long deptId = user.getDepartmentId();
		Depart dept = deptService.get(deptId);
		String deptType1 = dept.getDeptType();
		List<AlarmNotice> alarmNoticeList = alarmNoticeService.getAlarmNoticeList(deptId);
		if(alarmNoticeList!=null&&alarmNoticeList.size()>0){
			request.setAttribute("hasNotice", "1");
		}else{
			request.setAttribute("hasNotice", "0");
		}
		List<Publish> publishList = publishService.getPublishList(deptId);
		if(publishList!=null&&publishList.size()>0){
			request.setAttribute("hasPublish", "1");
		}else{
			request.setAttribute("hasPublish", "0");
		}
		String type ="alarm";
//		String deptName =dept.getName();
		if(!(deptName.indexOf("气象")>-1||deptName.indexOf("应急办")>-1||deptName.indexOf("发布中心")>-1)){
			type="event";
		}
		request.setAttribute("deptType1",deptType1);
		request.setAttribute("type",type);
		request.getSession().setAttribute("departmentId", deptId);
		
		//-----cxj:20180316-----
	/*	if(getCurrentUser().getName().equals("预警发布中心")){
			request.setAttribute("isPubCenter", 1);	//允许修改底部联系人
		}else{
			request.setAttribute("isPubCenter", 0);
		}
		*/
		Depart depart = deptService.get(deptId);
		String deptType = depart.getDeptType();
		String areaId = depart.getAreaId();
		if("2".equals(deptType)) {
			request.setAttribute("isPubCenter", 1);	//允许修改底部联系人
		}else{
			request.setAttribute("isPubCenter", 0);
		}
		FooterContact footer = footerService.getFootValue(areaId);
		request.setAttribute("footVal", JSON.toJSONString(footer));   
		//-----cxj:20180316-----
		
		return "sys/index";
	}

	private JSONArray getJSONArrayByPid(List<Permission> permissions,Long pid) {
		JSONArray arr = new JSONArray();
		for(Permission permission : permissions) {
			if(pid.equals(permission.getPid())) {
				JSONObject o = JSON.parseObject(JSON.toJSONString(permission,new MenuFilter()));
				o.put("menus",getJSONArrayByPid(permissions,permission.getId()));
				arr.add(o);
			}
		}
		return arr;
	}

	public class MenuFilter extends AfterFilter {
		@Override
		public void writeAfter(Object o) {
			Permission permission = (Permission) o;
			writeKeyValue("menuId",permission.getId());
			writeKeyValue("menuname",permission.getName());
		}
	}


	
//	@RequestMapping(value = "getSubMenus",method = RequestMethod.POST)
//	public void getSubMenus(String moduleId,HttpServletRequest request,HttpServletResponse response) {
//		printJson(response,getMenus(moduleId).toString());
//	}
	
//	private JSONObject getMenus(String moduleId) {
//		JSONObject menusObj = new JSONObject();
//		JSONArray menusArr = new JSONArray();
//		List<ModuleBean> modules = MenuTreeUtil.getChildMenu(moduleId);
//		for(ModuleBean m : modules) {
//			JSONObject moduleObj = new JSONObject();
//			moduleObj.put("menuId", m.getId().toString());
//			moduleObj.put("icon", "icon-sys");
//			moduleObj.put("menuname", m.getName());
//			JSONArray subModuleArr = new JSONArray();
//			for(ModuleBean sm : MenuTreeUtil.getChildMenu(m.getId().toString())) {
//				JSONObject subModuleObj = new JSONObject();
//				subModuleObj.put("menuId", sm.getId().toString());
//				subModuleObj.put("icon", "icon-nav");
//				subModuleObj.put("menuname", sm.getName());
//				subModuleObj.put("url", sm.getUrl());
//				subModuleArr.add(subModuleObj);
//			}
//			moduleObj.put("menus", subModuleArr);
//			menusArr.add(moduleObj);
//		}
//		menusObj.put("menus", menusArr);
//		return menusObj;
//	}

}
