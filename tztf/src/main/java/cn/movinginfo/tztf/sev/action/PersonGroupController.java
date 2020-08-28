package cn.movinginfo.tztf.sev.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aspose.words.Paragraph;

import cn.movinginfo.tztf.sev.domain.Emperson;
import cn.movinginfo.tztf.sev.domain.Personroup;
import cn.movinginfo.tztf.sev.service.EmPersonService;
import cn.movinginfo.tztf.sev.service.PersonGroupService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.sys.shiro.ShiroDBRealm;

@Controller
@RequestMapping("/sev/pg")
public class PersonGroupController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private PersonGroupService personGroupService;
	
	@Autowired
	private EmPersonService emPersonService;
	
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	public ShiroDBRealm.ShiroUser user;

	protected ShiroDBRealm.ShiroUser getCurrentUser() {
		if(SecurityUtils.getSubject().getPrincipal()==null){
			return user;
		}else{
			return (ShiroDBRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
		}
	}
	
	
	/**
	 * 新增或更新
	 * @param request
	 * @return
	 */
	@RequestMapping("saveorupdate")
	@ResponseBody
	public boolean index(HttpServletRequest request,String personids , String name , String ps ,int id, Long userid){
		Personroup p =new Personroup();
		p.setCreateTime(new Date());
		p.setName(name);
		p.setPs(ps);
		p.setPersonIds(personids);
		p.setId(id);
	//	Long userid = this.getCurrentUser().id;
		Long deptId = userService.get(userid).getDepartmentId();
		String userName = userService.get(userid).getUserName();
		Depart depart = deptService.get(deptId);
		String areaId = depart.getAreaId();
		String deptname = depart.getName();
		String areaname = dictService.findByCond(areaId).getContent();
		try {
			if(p.getId() != null && p.getId() != 0) {		//更新
				p.setUpdateTime(new Date());
				personGroupService.updatePersonGroup(p);
			}else{
				p.setAreaName(areaname);
				p.setDeptname(deptname);
				p.setValid(1);
				p.setAreaId(areaId);
				p.setDeptId(deptId);
				p.setCreator(userName);
				personGroupService.insert(p);
				
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * 获取成员列表
	 * @param id 主键id
	 * @return
	 */
	@RequestMapping("personlist")
	@ResponseBody
	public List<Emperson> getpeopleList(String id){
		List<Integer> ids = new ArrayList<Integer>();
		Personroup group = personGroupService.getById(Integer.parseInt(id));
		String personIds = group.getPersonIds();
		List<String> idList = Arrays.asList(personIds.split(","));
		for (String str : idList) {
			ids.add(Integer.parseInt(str));
		}
		List<Emperson> ulist = emPersonService.getByIds(ids);
		for (Emperson p : ulist) {
			p.setAreaname(dictService.findByCond(p.getAreaId()).getContent());
			p.setDeptname(deptService.get(p.getDepartmentid()).getName());
		}
		return ulist;
	}

	/**
	 * 获取群组列表
	 * @return
	 */
	@RequestMapping("grouplist")
	@ResponseBody
	public List<Personroup> getpeopleList(HttpServletRequest request, Long userid){
		Long deptId = userService.get(userid).getDepartmentId();
	//	String userName = userService.get(this.getCurrentUser().id).getUserName();
	//	Depart depart = deptService.get(deptId);
		List<Personroup> plist = personGroupService.findByDeptId(deptId);
		return plist;
	}
	
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	@RequestMapping("del")
	@ResponseBody
	public boolean del(String id){
		try {
			personGroupService.deleteById(Integer.parseInt(id));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
	
	
	
	
}







