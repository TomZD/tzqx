package cn.movinginfo.tztf.sys.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.IpAddressUtil;
import cn.movinginfo.tztf.sem.domain.LoginUser;
import cn.movinginfo.tztf.sys.domain.*;
import cn.movinginfo.tztf.sys.service.*;
import cn.movinginfo.tztf.sys.shiro.ShiroDBRealm;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.DigestUtils;
import net.ryian.commons.EncodeUtils;
import net.ryian.commons.StringUtils;
import net.ryian.orm.domain.BaseEntity;
import org.apache.shiro.codec.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @description:User action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/sys/user")
@SuppressWarnings("serial")
public class UserAction extends MagicAction<User, UserService> {
    
	public static final int HASH_INTERATIONS = 1024;
	
	@Autowired
	private ShiroDBRealm shiroDBRealm;

	// 数据字
	@Autowired
	private DictService dictService;

	// 部门
	@Autowired
	private DeptService deptService;

	// 角色
	@Autowired
	private RoleService roleService;

	// 人员和角色关联
	@Autowired
	private RUserRoleService rUserRoleService;
	
	@Autowired
	private LoginUserService  loginUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RecordService recordService;
	/**
	 * 设置是否可登录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/isUse")
	public void isUse(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			Long departmentId = entityService.get(Long.parseLong(id)).getDepartmentId();
			List<User> users = entityService.getByDepartmentId(departmentId);
			for(User u:users){
				if(u.getId().toString().equals(id)){
					u.setIsUse("1");
					u.setUpdator(this.getCurrentUser().id);
					u.setUpdateDate(new Date());
					entityService.saveOrUpdate(u);
				}else{
					u.setIsUse("0");
					u.setUpdator(this.getCurrentUser().id);
					u.setUpdateDate(new Date());
					entityService.saveOrUpdate(u);
				}
			}
		}
		boolean data = true;
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(data));
	}
	
	/**
	 * 密码修改功能
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/editPassword")
	public void editPassword(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Record record = new Record();//日志
		Long userIds = entityService.get(this.getCurrentUser().id).getId();
		String userName = entityService.get(this.getCurrentUser().id).getName();
		String id = request.getParameter("id");
		String newPassword = request.getParameter("thePassword");
		User user = entityService.get(Long.parseLong(id));
		String name = user.getName();
		user.encryptUserPassword(newPassword);
		user.setUpdator(this.getCurrentUser().id);
		user.setUpdateDate(new Date());
		entityService.saveOrUpdate(user);
		record.setUserName(userName);
		record.setUserId(userIds);
		record.setContent(userName+"用户修改了("+name+")的密码");
		record.setOperateDate(new Date());
		record.setIpAddress(IpAddressUtil.getIpAddr(request));
		recordService.saveRcord(record);
		boolean data = true;
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(data));
	}

	/**
	 * 判断用户名是否可用
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "userNameIsValid")
	public void userNameIsValid(HttpServletRequest request,
			HttpServletResponse response){
		String userName=request.getParameter("userName");
		User user=userService.findUserByUserName(userName);	
		try {
			if(user==null){
				printText(response, messageSuccuseWrap("用户名可用！"));
				return;
			}else{
				printText(response, messageFailureWrap("用户名已存在"));
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * 人员新增、修改方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdateUser")
	public void saveUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userIds = entityService.get(this.getCurrentUser().id).getId();
		String userName = entityService.get(this.getCurrentUser().id).getName();
		User user = bindEntity(request, entityClass);
		Long deptId = user.getDepartmentId();
		String roleId = request.getParameter("role");
		String password = request.getParameter("thePassword");
		Record record = new Record();//日志
		// 向用户表保存用户
		if (user.getId() == null) {
			// 密码加密
			if (!StringUtils.isEmpty(password)) {
				user.encryptUserPassword(password);
			}
			user.setUserName(user.getName());
			user.setCreator(getCurrentUser().id);
			user.setCreateDate(new Date());
			Long userId = entityService.saveOrUpdate(user);
			RUserRole rUserRole = new RUserRole();
			rUserRole.setUserId(userId.intValue());
			rUserRole.setRoleId(Integer.parseInt(roleId));
			rUserRole.setCreator(this.getCurrentUser().id);
			rUserRole.setCreateDate(new Date());
			rUserRoleService.saveOrUpdate(rUserRole);
		} else {
			User user1=entityService.findUserById2(user.getId());
			String name = user1.getName();
			User user2=entityService.findUserByUserName(user.getName());
			if(user2==null||user1.getUserName().equals(user.getName())){
				user.setUserName(user.getName());
			user.setUpdator(this.getCurrentUser().id);
			user.setUpdateDate(new Date());
			entityService.saveOrUpdate(user);
			record.setUserName(userName);
			record.setUserId(userIds);
			record.setContent(userName+"用户修改了("+name+")用户");
			record.setOperateDate(new Date());
			record.setIpAddress(IpAddressUtil.getIpAddr(request));
			recordService.saveRcord(record);
			RUserRole rUserRole = rUserRoleService.getByUserId(user.getId()
					.intValue());
			rUserRole.setRoleId(Integer.parseInt(roleId));
			rUserRole.setUpdator(this.getCurrentUser().id);
			rUserRole.setUpdateDate(new Date());
			rUserRoleService.saveOrUpdate(rUserRole);
			printText(response, messageSuccuseWrap("修改成功"));
			return;
			}else{
				printText(response, messageFailureWrap("用户名已存在"));
				return;
			}
			
			
		}
		boolean data = true;
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(data)); 
	}
	
	
	@RequestMapping(value = "/saveUser")
	public void saveUser2(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userIds = entityService.get(this.getCurrentUser().id).getId();
		String userName = entityService.get(userIds).getName();
		User user = bindEntity(request, entityClass);
		String name = user.getName();
		Long deptId = user.getDepartmentId();
		Depart dept = deptService.findById(deptId);
		String areaId = dept.getAreaId();
		String roleId = request.getParameter("role");
		String password = request.getParameter("thePassword");
		Record record = new Record();//日志
		// 向用户表保存用户
		if (user.getId() == null) {
			// 密码加密
			if (!StringUtils.isEmpty(password)) {
				user.encryptUserPassword(password);
			}
			User user1 = new User();
			user1=userService.findUserByUserName(user.getUserName());
			if(user1!=null){
				printText(response, messageFailureWrap("用户名已存在"));
				return;
			}
			user.setUserName(user.getUserName());
			user.setCreator(getCurrentUser().id);
			user.setCreateDate(new Date());
			user.setAreaId(areaId);
			user.setIsUse(user.getIsUse());
			record.setUserName(userName);
			record.setUserId(userIds);
			record.setContent(userName+"用户添加了("+name+")用户");
			record.setIpAddress(IpAddressUtil.getIpAddr(request));
			record.setOperateDate(new Date());
			recordService.saveRcord(record);
			//user.setIsUse("0");
			Long userId = entityService.saveOrUpdate(user);
			RUserRole rUserRole = new RUserRole();
			rUserRole.setUserId(userId.intValue());
			rUserRole.setRoleId(Integer.parseInt(roleId));
			rUserRole.setCreator(this.getCurrentUser().id);
			rUserRole.setCreateDate(new Date());
			rUserRoleService.saveOrUpdate(rUserRole);
		} else {
			user.setUserName(user.getName());
			user.setUpdator(this.getCurrentUser().id);
			user.setUpdateDate(new Date());
			//user.setAreaId(areaId);
			entityService.saveOrUpdate(user);
			RUserRole rUserRole = rUserRoleService.getByUserId(user.getId()
					.intValue());
			rUserRole.setRoleId(Integer.parseInt(roleId));
			rUserRole.setUpdator(this.getCurrentUser().id);
			rUserRole.setUpdateDate(new Date());
			rUserRoleService.saveOrUpdate(rUserRole);
		}
		printText(response, messageSuccuseWrap("增加成功"));
		return;
	}

	/**
	 * 修改人员，获取人员信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/editUser")
	public void editUser(HttpServletRequest request,
			HttpServletResponse response ) {
		
		String id = request.getParameter("id");
		User user = entityService.get(Long.parseLong(id));
		RUserRole rUserRole = rUserRoleService.getByUserId(user.getId()
				.intValue());
		user.setrUserRole(rUserRole);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(user));
	}
    
	@RequestMapping(value = "/lookUser")
	public void lookUser(HttpServletRequest request,
			HttpServletResponse response ) {
		
		String username = request.getParameter("username");
		User user = entityService.findUserByUserName(username);
		RUserRole rUserRole = rUserRoleService.getByUserId(user.getId()
				.intValue());
		user.setrUserRole(rUserRole);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(user));
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteUser")
	public void deleteDepartment(HttpServletRequest request,HttpServletResponse response , Model model)
			throws Exception {
		//boolean falg = true;
		String id = request.getParameter("id");
		Long logicId =this.getCurrentUser().id;
		if (!StringUtils.isEmpty(id)) {
			User user = entityService.get(Long.parseLong(id));
			Long depatId = user.getDepartmentId();
			List<User> userList = entityService.getByDepartmentId(depatId);
			
			if(userList.size() == 1) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("用户至少存在一个"));
			}else {
				
//				Long rUserRoleId = rUserRoleService.getByUserId(Integer.parseInt(id)).getId();
//				rUserRoleService.logicRemove(rUserRoleId);
				entityService.logicRemove(Long.parseLong(id));
				if(logicId == Long.parseLong(id)) {
					printJson(response, com.alibaba.fastjson.JSON.toJSONString("删除当前用户"));
				}
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("删除成功"));
			}
			
		}
		//return "redirect:/sys/user/userQuery";
	}

	/**
	 * 获取新增角色下拉框
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getRoles")
	public void getRoles(HttpServletRequest request,
			HttpServletResponse response) {
		List<Role> roles = roleService.getAll();
		//System.out.println(roles);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(roles));
	}
	
	/**
	 * 获取修改角色下拉框
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/getEditRoles")
	public void getEditRoles(HttpServletRequest request,
			HttpServletResponse response, Long id) {
	
		List<Role> roles = roleService.getRolesByUser(id);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(roles));
	}
    
	@RequestMapping(value = "/getLookRoles")
	public void getLookRoles(HttpServletRequest request,
			HttpServletResponse response, Long id) {
		List<Role> roles = roleService.getRolesByUser(id);//id是用户id
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(roles));
	}
	
	@RequestMapping(value = "/getLookReceiveMassages")
	public void getLookReceiveMassages(HttpServletRequest request,
			HttpServletResponse response, Long id) {
	    User user = entityService.findByUserId(id);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(user));
	}
	/**
	 * 获取新增部门下拉框
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getDepartment")
	public void getDepartment(HttpServletRequest request,
			HttpServletResponse response) {
		List<Depart> depts = deptService.getAll();
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(depts));
	}
    
	/**
	 * 获取修改部门下拉框
	 * 
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/getEditDepartments")
	public void getEditDepartments(HttpServletRequest request,
			HttpServletResponse response,Long id) {
		User user = entityService.findByUserId(id);
		Long departId = user.getDepartmentId();
		Depart depart = deptService.getByDepartmentId(departId);
		List<Depart> depts = new ArrayList<Depart>();
		depts.add(depart);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(depts));
	}
	
	@RequestMapping(value = "/getLookDepartments")
	public void getLookDepartments(HttpServletRequest request,
			HttpServletResponse response,Long id) {
		Depart depart = deptService.getByDepartmentId(id);
		List<Depart> depts = new ArrayList<Depart>();
		depts.add(depart);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(depts));
	}
	/**
	 * 人员列表页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userData")
	public String userData(HttpServletRequest request, Model model)
			throws Exception {
		/*String departmentId = request.getParameter("departmentId");
		User user = entityService.get(this.getCurrentUser().id);
		if(user.getDepartmentId() == 18){
			PageInfo<?> pageInfo = entityService
					.queryPaged(getParameterMap(request));
			JSONObject page = (JSONObject) JSONObject.parse(JSONObject
					.toJSONString(pageInfo, new DictFilter()));
			model.addAttribute("page", page);
			
		}else{
			PageInfo<?> pageInfo = entityService
					.queryPaged(getParameterMap(request));
			JSONObject page = (JSONObject) JSONObject.parse(JSONObject
					.toJSONString(pageInfo, new DictFilter()));
			model.addAttribute("page", page);
		}*/
		
		// 获取数据
		User user = entityService.get(this.getCurrentUser().id);
		Long departId = user.getDepartmentId();
		String areaId = user.getAreaId();
		//System.out.println(deptType);
		Map<String,String> map = new HashMap<String,String>();
		if (areaId.equals("1")){
			map=getParameterMap(request);
		}else{
			map=getParameterMap(request);
			map.put("areaId", areaId);	
		}
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("departId",departId);
		model.addAttribute("page", page);
		model.addAttribute("areaId", areaId);
		// 所属部门
		List<Depart> depts = deptService.queryList();
		model.addAttribute("depts", depts);
		
//		//部门用户
//		Depart department=deptService.findById(departId);
//		String departName=department.getName();
//		User departUser=entityService.findUserByName(departName);
//
//		
//		Long loginDepartmentId = entityService.get(this.getCurrentUser().id).getDepartmentId();
//		User user1 = entityService.findUserByDepartment(loginDepartmentId);
//		Long userId = user1.getId();
		RUserRole rUserRole = rUserRoleService.getByUserId(user.getId().intValue());
		int roleId = rUserRole.getRoleId();
		model.addAttribute("roleIds",roleId);
		model.addAttribute("loginDepartmentId", departId);
		return getNameSpace() + "userData";
	}

	/**
	 * 人员查询页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userQuery")
	public String userQuery(HttpServletRequest request, Model model,String id) {
//		System.out.println(id);
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		// 所属部门
		List<Depart> depts = new ArrayList<Depart>();
		User user = entityService.get(this.getCurrentUser().id);
//		if(user.getDepartmentId()== 18){
//			Depart depart = new Depart();
//			depart.setId(null);
//			depart.setName("——请选择——");
//			depts.add(depart);
//			depts.addAll(deptService.getAll());
//			model.addAttribute("departmentId", "");
//		}else if(user.getDepartmentId() == 127) {
//			Depart depart = new Depart();
//			depart.setId(null);
//			depart.setName("——请选择——");
//			depts.add(depart);
//			String areaId = user.getAreaId();
//			List<Depart> dets = deptService.getByAreas(areaId);
//			depts.addAll(dets);
//			model.addAttribute("departmentId", "");
//		}else{
//			Depart depart = deptService.get(user.getDepartmentId());
//			depts.add(depart);
//			model.addAttribute("departmentId", user.getDepartmentId());
//			//depts = deptService.getAll();
//		}
		
		if(user.getName()!=ConfigHelper.getProperty("warningReleaseCenter") ) {
			Long deptId = user.getDepartmentId();
			Depart dept = deptService.findById(deptId);
			String type = dept.getDeptType();
			if(type.equals("2")) {
				Depart depart = new Depart();
				depart.setId(null);
				depart.setName("——请选择——");
				depts.add(depart);
				String areaId = user.getAreaId();
				List<Depart> dets = deptService.getByAreas(areaId);
				depts.addAll(dets);
				model.addAttribute("departmentId", "");
			}else {
				Depart depart = deptService.get(user.getDepartmentId());
				depts.add(depart);
				model.addAttribute("departmentId", user.getDepartmentId());
			}
		}else {
			Depart depart = new Depart();
			depart.setId(null);
			depart.setName("——请选择——");
			depts.add(depart);
			depts.addAll(deptService.getAll());
			model.addAttribute("departmentId", "");
		}
		model.addAttribute("depts", depts);
		return getNameSpace() + "userQuery";
	}

	/**
	 * 进入修改页面
	 */
	@Override
	protected void beforeEdit(HttpServletRequest request, Model model,
			BaseEntity entity) {
		List<Dict> isReceiveMassageses = new ArrayList<Dict>();
		isReceiveMassageses.addAll(dictService
				.getDictsByKey(DictKeys.BOOL_TYPE).values());
		model.addAttribute("isReceiveMassageses", isReceiveMassageses);
		List<Depart> departmentList = deptService.getAll();
		List<Dict> departments = new ArrayList<Dict>();
		for (Depart d : departmentList) {
			Dict dict = new Dict();
			dict.setValue(d.getId().toString());
			dict.setContent(d.getName());
			departments.add(dict);
		}
		model.addAttribute("departments", departments);
		Long departmentId = entityService.get(entity.getId()).getDepartmentId();
		model.addAttribute("departmentId", departmentId.toString());
		super.beforeEdit(request, model, entity);
	}

	/**
	 * 进入新增页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/beforeAddUser")
	public String beforeAddUser(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		List<Dict> isReceiveMassageses = new ArrayList<Dict>();
		isReceiveMassageses.addAll(dictService
				.getDictsByKey(DictKeys.BOOL_TYPE).values());
		model.addAttribute("isReceiveMassageses", isReceiveMassageses);
		List<Depart> departmentList = deptService.getAll();
		List<Dict> departments = new ArrayList<Dict>();
		for (Depart d : departmentList) {
			Dict dict = new Dict();
			dict.setValue(d.getId().toString());
			dict.setContent(d.getName());
			departments.add(dict);
		}
		model.addAttribute("departments", departments);
		return this.getNameSpace() + "add";
	}

	/**
	 * 获取人员管理列表页面信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserPaged")
	public void queryUserPaged(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
//			User user = entityService.get(this.getCurrentUser().id);
//			Long deptId = user.getDepartmentId();
//			Depart dept = deptService.getByDepartmentId(deptId);
//			String areaId = dept.getAreaId();
//			Map<String,String> map = new HashMap<String,String>();
//			if (areaId.equals("1")){
//				map=getParameterMap(request);
//			}else{
//				map=getParameterMap(request);
//				map.put("areaId", areaId);	
//			}
			PageInfo<List<User>> page = entityService
					.queryUserPaged(getParameterMap(request));
			JSONObject o = (JSONObject) JSONObject.parse(JSONObject
					.toJSONString(page, new DictFilter()));
			o.put("rows", o.get("list"));
			o.remove("list");
			o.put("totalPageCount", o.get("lastPage"));
			o.put("countPerPage", o.get("pageSize"));
			o.put("currentPage", o.get("pageNum"));
			printJson(response, o.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 人员管理页面
	 */
	@Override
	public String index(HttpServletRequest request, Model model) {
		return super.index(request, model);
	}

	@RequestMapping(value = "selectRoles")
	public String selectRoles(Model model,
			@RequestParam(value = "userId") String userId) {
		model.addAttribute("userId", userId);
		return "/sys/user/selectRoles";
	}

	@RequestMapping(value = "changePwd")
	public String changePwd(Model model) {
		model.addAttribute("userId", getCurrentUser().id);
		return "/sys/user/changePwd";
	}

	/*@RequestMapping(value = "savePwd")
	@ResponseBody
	public void savePwd(HttpServletResponse response,
			String password,HttpServletRequest request) throws IOException {
		User user = entityService.findUserByDepartment((Long)request.getSession().getAttribute("departmentId"));
		if (user != null) {
			user.encryptUserPassword(password);
			entityService.saveOrUpdate(user);
		} else {
			printText(response, messageFailureWrap("密码不正确"));
			return;
		}
		printText(response, messageSuccuseWrap());
	}*/
	@RequestMapping(value = "savePwd")
	@ResponseBody
	public void savePwd(HttpServletResponse response,
			HttpServletRequest request,@RequestParam Long id,@RequestParam String password) throws IOException {
		System.out.println(id);
		System.out.println(password);
		User user = entityService.findUserById2(id);
		if (user != null) {
			user.encryptUserPassword(password);
			entityService.saveOrUpdate(user);
		} else {
			printText(response, messageFailureWrap("密码不正确"));
			return;
		}
		printText(response, messageSuccuseWrap());
	}
	
	@RequestMapping(value = "resetPwd")
	public void savePwd(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		String userId = request.getParameter("userId");
		User user = entityService.findUserById2(Long.valueOf(userId));
		if (user != null) {
			user.encryptUserPassword("111111");
			entityService.saveOrUpdate(user);
		} else {
			printText(response, messageFailureWrap("用户不存在"));
			return;
		}
		printText(response, messageSuccuseWrap());
	}
	
	@RequestMapping(value = "userData1")
	@ResponseBody
	public void getUserData(HttpServletResponse response,
			HttpServletRequest request ,@RequestParam Long department_id) throws IOException {
		List<User> users = entityService.getByDepartmentId1( department_id);
		JSONArray objs = new JSONArray();
    	for(User user:users){
    		JSONObject obj = new JSONObject();
    		obj.put("name", user.getUserName());
    		obj.put("realname", user.getName());
    		obj.put("phone", user.getPhone());
    		obj.put("id", user.getId());
    		objs.add(obj);
    	}
    	
    	printJson(response, objs.toString());
    	
		
		
		
		
		
	}
	
	@RequestMapping(value = "deluser")
	@ResponseBody
	public void deluser(HttpServletResponse response,
			HttpServletRequest request ,@RequestParam String id) throws IOException {
		if (!StringUtils.isEmpty(id)) {
			/*Long rUserRoleId = rUserRoleService.getByUserId(Integer.parseInt(id)).getId();
			rUserRoleService.logicRemove(rUserRoleId);*/
			entityService.logicRemove(Long.parseLong(id));
		}else {
			printText(response, messageFailureWrap("用户不存在"));
			return;
		}
		printText(response, messageSuccuseWrap());
		
		
		
		
		
	}
	
	
	@RequestMapping(value = "addUser")
	@ResponseBody
	public void adduser(HttpServletResponse response,
			HttpServletRequest request ) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		System.out.println(phone);
			User user1 = new User();
			user1 = entityService.findUserByName(username);
			if(user1==null){
				User user = new User();
				user.setDepartmentId(id);
				user.setUserName(username);
				user.setName(name);
				user.setPhone(phone);
				user.encryptUserPassword(password);
				Long i=entityService.saveOrUpdate(user);
				//int i=entityService.addUser(user);
				if(i>0){
					user1 = entityService.findUserByName(username);
					Depart dept = deptService.findById(id);
					User user2 = entityService.findUserByUserName(dept.getName());
					RUserRole rUserRole =rUserRoleService.getByUserId(user2.getId().intValue());
					rUserRoleService.setByUserId(user1.getId(),rUserRole.getRoleId());
					
					printText(response, messageSuccuseWrap("增加成功"));
					return;
				}else{
					printText(response, messageFailureWrap("增加失败"));
					return;
				}

				
			}else{
				printText(response, messageFailureWrap("用户名已存在"));
				
				return;
			}

			
		
		
		
		
		
	}
	@RequestMapping(value = "getUser")
	@ResponseBody
	public void getUser(HttpServletResponse response,
			HttpServletRequest request ) throws IOException {
		String id = request.getParameter("id");
		User user = entityService.get(Long.parseLong(id));
		
		
		
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(user));
		
		
		
		
		
	}
	
	@RequestMapping(value = "update")
	@ResponseBody
	public void updateUser(HttpServletResponse response,
			HttpServletRequest request ) throws IOException {
		Long uid = Long.parseLong(request.getParameter("id"));
		String username = request.getParameter("username");
		User user =entityService.findUserByUserName(username);
		String phone = request.getParameter("phone");
		String realname = request.getParameter("realname");
		if(user==null||(user!=null&&(user.getId()==uid))){
			User user1 = new User();
			user1.setId(uid);
			user1.setUserName(username);
			user1.setName(realname);
			user1.setPhone(phone);
			Long i=entityService.saveOrUpdate(user1);
			if(i>0){
				printText(response, messageSuccuseWrap("修改成功"));
			}else{
				printText(response, messageFailureWrap("修改失败"));
			}
		}else{
			printText(response, messageFailureWrap("用户名已存在"));
		}	
	}
    
	@RequestMapping(value = "/logins")
    @ResponseBody
    public Map<String,Object> logins(String name, String password ,HttpSession httpSession) throws UnsupportedEncodingException {
    	   LoginUser user = null;
    	   //name = new String( name.getBytes("ISO-8859-1"),"utf-8");
    	   String message = "";
    	   String result = "";
    	   Map<String, Object> map = new HashMap<>();
    	   if(net.ryian.commons.StringUtils.isEmpty(name) || net.ryian.commons.StringUtils.isEmpty(password)) {
    		   result = "failed";
    		   message = "用户名和密码不能为空"; 
    	   }else {
    		   user = loginUserService.selectUserByName(name);
    		  
    		   if(null ==user) {
    			   result = "failed";
    			   message = "用户不存在";
    		   }else {
    			   String salt = user.getSalt();
    			   byte[] hashPassword = DigestUtils.sha1(password.getBytes(), Hex.decode(salt), 1024);
    			   
    			   String passwd = user.getPassword();
    			   if(passwd.equals(EncodeUtils.encodeHex(hashPassword))) {
    				   Long id = (long)user.getId();
    				   String type = deptService.get((long)user.getDepartmentId()).getDeptType();
    	    		   map.put("userid", id);
    	    		   map.put("type", type);
    	    		   map.put("username", name);
    				   result = "success";
    				   message = "登录成功";
    			   }else {
    				   result = "failed";
    				   message = "用户名或密码有误";
    			   }
    		   }
    	   }
    	   
       	   map.put("result", result);
       	   map.put("message", message);
       	   
           return map;   
    }
	
	
	@RequestMapping("loginout")
	@ResponseBody
	public Map<String,Object> loginout(HttpSession httpSession){
		Map<String, Object> map = new HashMap<>();
		map.put("userid", 0);
		map.put("result", "退出成功！");
		return map;
	}
	
//	@RequestMapping(value = "/logins")
//	@ResponseBody
//    public Map<String,Object> login1(HttpServletRequest req,HttpServletResponse response) {
//    	String exceptionClassName = (String)req.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
//        String error = null;
//        if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
//            error = "用户名/密码错误";
//        } else if(AccountException.class.getName().equals(exceptionClassName)) {
//            error = "用户名/密码错误";
//        } 
//        String username=req.getParameter("username");
//        if ((username!=null)&&!(username.equals(""))){
//        	User user = userService.findUserByUserName(username);
//        	if(user==null){
//        		error = "用户名/密码错误";
//        	}else{
//        		
//            }
//        	}
//        if((req.getParameter("password")!=null)&&(error!=null)){
//        	Map<String, Object> map = new HashMap<>();
//        	map.put("error", error);
//        	return map;
//        	
//        }
//
//        if(this.getCurrentUser() !=null){
//             error = "登录成功"; 
//            Map<String, Object> map = new HashMap<>();
//         	map.put("error", error);
//         	return map;
//        }
//        Map<String, Object> map = new HashMap<>();
//    	map.put("error", error);
//    	return map;
//    }
}
