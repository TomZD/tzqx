package cn.movinginfo.tztf.dd.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.BaseMagicAction;
import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.dd.domain.Disaster;
import cn.movinginfo.tztf.dd.service.DisasterService;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;



/**
*
* @description:灾情实时上报
* @author: autoCode
* @history:
*/

@Controller
@RequestMapping("/dd/disaster")
public class DisasterAction extends BaseMagicAction {
	@Autowired
	private DisasterService disasterService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private DictService dictService;
	
	//灾情发布
	@RequestMapping(value="saveDisa")
	@ResponseBody
	public String saveDisaster(HttpServletRequest request, HttpServletResponse response){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String title=request.getParameter("title");
//		String publisher=request.getParameter("publisher");
//		String type=request.getParameter("type");
//		String add=request.getParameter("add");
//		String date=request.getParameter("date");
//		String content=request.getParameter("content");
		
		
		Long id=Long.valueOf(request.getParameter("userId"));
		//Long id=(long) 45;
		User user = userService.get(id);
		Long departId = user.getDepartmentId();
		String areaId = user.getAreaId();
		
		try {
			Disaster disaster = new Disaster();
			disaster.setTitle(request.getParameter("title"));
			disaster.setPublisher(request.getParameter("publisher"));
			disaster.setType(request.getParameter("type"));
			disaster.setPubAdd(request.getParameter("add"));
			disaster.setPubDate(sdf.parse(request.getParameter("date")));
			disaster.setContent(request.getParameter("content"));
			disaster.setDeptId(departId);
			disaster.setAreaId(areaId);
			disaster.setImagePath((request.getParameter("imagePath")));
			disaster.setLat(request.getParameter("lat"));
			disaster.setLon(request.getParameter("lon"));
			disaster.setPubState("0");
			int i=disasterService.saveDisaster(disaster);
			if(i>0){
	        	printJson(response, messageSuccuseWrap());
			}else{
				JSONObject obj = new JSONObject();
				printJson(response, messageFailureWrap("上报失败"));
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	//详情界面
	@RequestMapping(value = "releaseNowDisaster")
	public String releaseNowDisaster(HttpServletRequest request, Model model, String disasterId) {
			Disaster disaster = disasterService.get(Long.parseLong(disasterId));
			model.addAttribute("disaster", disaster);
			if(disaster.getImagePath()==null||"".equals(disaster.getImagePath())){
				
			}else{
				model.addAttribute("images", disaster.getImagePath().split(","));
			}
			
			return  "/dd/disaster/releaseNowDisaster";
	}
	
	
	
	@RequestMapping(value = "uploadPage")
	public void uploadPage(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		String name = disasterService.uploadPage(file, request);
		JSONObject obj = new JSONObject();
		obj.put("data", name);
		printJson(response, obj.toString());
	
	}
	
	@RequestMapping(value = "historyQuery")
	public String historyQuery(HttpServletRequest request, Model model) {
		Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
		Depart depart=deptService.get(deptId);
		model.addAttribute("detId", deptId);
		model.addAttribute("depart",depart);
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		// 上报部门
		String type = depart.getDeptType();
		String areaId = depart.getAreaId();
		if(areaId.equals("1")) {
		List<Depart> depts = deptService.queryListByType(type);
		model.addAttribute("depts", depts);	
		}else {
			List<Depart> depts = deptService.queryListByAreaAndType(areaId,type);
			model.addAttribute("depts", depts);
		}

		// 发布状态、事件状态
		model.addAttribute("pubStates", dictService.getDictsByKey(DictKeys.PUB_STATE).values());
		//区域选择
		if(areaId.equals("1")) {
			if("18".equals(String.valueOf(deptId))) {
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
		return  "/dd/disaster/historyQuery";
	}
	
	@RequestMapping(value = "historyData")
	public String historyData(HttpServletRequest request, Model model) throws Exception {
		// 获取历史预警数据
		Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
		Depart depart=deptService.get(deptId);
		String areaId = depart.getAreaId();
		Map<String, String> map = new HashMap<String, String>();
		if (depart.getDeptType().equals("2")||depart.getDeptType().equals("4")){
			map=getParameterMap(request);
		}else{
			map=getParameterMap(request);
			map.put("deptId", Long.toString(deptId));	
		}
		if(areaId.equals("1")) {
			if("18".equals(String.valueOf(deptId))) {
			map=getParameterMap(request);
			}else {
				map=getParameterMap(request);	
				map.put("deptId", String.valueOf(deptId));
			}
		}else {
			map=getParameterMap(request);
			map.put("areaId", areaId);
		}
		PageInfo pageInfo = new PageInfo(disasterService.query(map));
		System.out.println(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page", page);
		model.addAttribute("areaId",areaId);
		model.addAttribute("deptId",deptId);
		model.addAttribute("departList",deptService.queryListAllVlue());
		return  "/dd/disaster/historyData";
	}
	
	@RequestMapping("getDisaster")
	@ResponseBody
	public List<Disaster> addDisaster() {
		List<Disaster> result = disasterService.getDdDisaster();
		for(Disaster dis : result) {
		String lat = dis.getLat();
		dis.setLatitude(lat);
		String lon = dis.getLon();
		dis.setLongitude(lon);
	    dis.setDataType("disaster");
		}
		return result;
	}
	@RequestMapping("findDisaster")
	@ResponseBody
	public Disaster findDisaster(Long id) {
		Disaster dis = disasterService.findDisasterById(id);
		String title = dis.getTitle();
		dis.setName(title);
		String add = dis.getPubAdd();
		dis.setAddress(add);
		String publisher = dis.getPublisher();
		dis.setChargeMan(publisher);
		String images = dis.getImagePath();
		String[] img = images.split(",");
		dis.setImages(img);
		
		return dis;
	}
	
	

@RequestMapping("getDisasterList")
	@ResponseBody
	public List<Disaster> getDisasterList(HttpServletRequest request, 

HttpServletResponse response) {
		Long id=Long.valueOf(request.getParameter("userid"));
		User user = userService.get(id);
		Long departId = user.getDepartmentId();
		List<Disaster> result = disasterService.getDdDisaster(departId);
		return result;
	}
	
	
	
	
	

}
