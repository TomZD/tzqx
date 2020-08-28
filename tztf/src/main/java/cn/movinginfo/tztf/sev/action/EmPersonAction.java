package cn.movinginfo.tztf.sev.action;
/*package cn.movinginfo.tztf.sev.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.sev.domain.EmPersonResult;
import cn.movinginfo.tztf.sev.domain.Emperson;
import cn.movinginfo.tztf.sev.domain.Page;
import cn.movinginfo.tztf.sev.service.EmPersonService;
import cn.movinginfo.tztf.sys.domain.Area;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;


*//**
 * @author yougq
 * 2018/11/22 11:09
 * 应急责任人控制层
 *
 *//*
@Controller
@RequestMapping("/sev/em")
public class EmPersonAction {
	
	@Autowired
	private EmPersonService emPersonService;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private DeptService deptservice;
	*//**
	 * @param request
	 * @param model
	 * @return
	 * 首页
	 *//*
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return getNameSpace() + "emperson";
	}
	
	*//**
	 * @param num    页码
	 * @param size   条数
	 * @return
	 *//*
	@RequestMapping("search")
	@ResponseBody
	public EmPersonResult  getPerson(int num, int size){
		PageHelper.startPage(num,size);
		List<Emperson> list = emPersonService.getAllValid();
		for (Emperson e : list) {
			Dict dict = dictService.findByCond(e.getAreaId());
			e.setAreaname(dict.getContent());
		}
		PageInfo<Emperson> page = new PageInfo<Emperson>(list);
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

	*//**
	 * @param p   实体类
	 * @return
	 *//*
	@RequestMapping("saveorupdate")
	@ResponseBody
	public boolean add(@RequestBody Emperson p){
		try {
			p.setCreateTime(new Date());
			if(p.getId() != null ) {    	//更新
				emPersonService.update(p);  
			}else{  						//保存
				emPersonService.save(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@RequestMapping("del")
	@ResponseBody
	public boolean del(String id){
		try {
			emPersonService.deleteById(Integer.parseInt(id));
		} catch (Exception e2) {
			return false;
		}
		return true;
	}
	
	*//**
	 * @param id   主键
	 * @return
	 *//*
	@RequestMapping("detail")
	@ResponseBody
	public Emperson getDetail(String id){
		Emperson e = emPersonService.findById(Integer.parseInt(id));
		Dict dict = dictService.findByCond(e.getAreaId());
		e.setAreaname(dict.getContent());
		return e;
	}
	
	*//**
	 * @return
	 * 所有区域
	 *//*
	@RequestMapping("getArea")
	@ResponseBody
	public List<Area>  getArea(){
		List<Depart> areas = deptservice.getAllArea();
		return areas;
	}
	
	
	@RequestMapping("allperson")
	@ResponseBody
	public List<Emperson> getPeopleList(){
		return null;
	}
	
	
    protected String getNameSpace() {
        String ns = null;
        RequestMapping r = getClass().getAnnotation(RequestMapping.class);
        ns = r.value()[0];
        if (!ns.endsWith("/"))
            ns += "/";
        return ns;
    }
}














package cn.movinginfo.tztf.sev.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.sev.domain.Emperson;
import cn.movinginfo.tztf.sev.service.EmPersonService;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.service.DictService;

@Controller
@RequestMapping("/sev/em")
public class EmPersonAction {
	
	@Autowired
	private EmPersonService emPersonService;
	
	@Autowired
	private DictService dictService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model){
		return getNameSpace() + "emperson";
	}
	
	@RequestMapping("search")
	@ResponseBody
	public PageInfo<Emperson>  getPerson(int num, int size){
		PageHelper.startPage(num,size);
		List<Emperson> list = emPersonService.getAllValid();
		for (Emperson e : list) {
			Dict dict = dictService.findByCond(e.getAreaId());
			e.setAreaname(dict.getContent());
		}
		PageInfo<Emperson> page = new PageInfo<Emperson>(list);
		return page;
	}
	
	@RequestMapping("saveorupdate")
	@ResponseBody
	public boolean add(@RequestBody Emperson p){
		try {
			p.setCreateTime(new Date());
			if(p.getId() != null ) {    	//更新
				emPersonService.update(p);  
			}else{  						//保存
				emPersonService.save(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@RequestMapping("del")
	@ResponseBody
	public boolean del(String id){
		try {
			emPersonService.deleteById(Integer.parseInt(id));
		} catch (Exception e2) {
			return false;
		}
		return true;
	}
	
	@RequestMapping("detail")
	@ResponseBody
	public Emperson getDetail(String id){
		Emperson e = emPersonService.findById(Integer.parseInt(id));
		Dict dict = dictService.findByCond(e.getAreaId());
		e.setAreaname(dict.getContent());
		return e;
	}
	
	@RequestMapping("getArea")
	@ResponseBody
	public List<Dict>  getArea(){
		List<Dict> areas = dictService.getAllAreas();
		return areas;
	}
	
	protected String getNameSpace() {
		String ns = null;
		RequestMapping r = getClass().getAnnotation(RequestMapping.class);
		ns = r.value()[0];
		if (!ns.endsWith("/"))
			ns += "/";
		return ns;
	}
}














*/