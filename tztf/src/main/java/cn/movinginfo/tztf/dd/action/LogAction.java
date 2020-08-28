package cn.movinginfo.tztf.dd.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.BaseMagicAction;
import cn.movinginfo.tztf.dd.domain.Disaster;
import cn.movinginfo.tztf.dd.domain.Log;
import cn.movinginfo.tztf.dd.service.LogService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

@Controller
@RequestMapping("/dd/log")
public class LogAction extends BaseMagicAction{
	@Autowired
	private LogService logService;
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value="saveLog")
	@ResponseBody
	public String saveLog(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Long id=Long.valueOf(request.getParameter("userId"));
		User user = userService.get(id);
		Long departId = user.getDepartmentId();
		String areaId = user.getAreaId();
		
		try {
			Log log = new Log();
			log.setDeptId(departId);
			log.setAreaId(areaId);
			log.setUserId(id.intValue());
			log.setContent(request.getParameter("content"));
			log.setDate(sdf.parse(request.getParameter("date")));
			int i=logService.savelog(log);
			if(i>0){
				JSONObject obj = new JSONObject();
	        	obj.put("result", "success");
	        	obj.put("msg", "保存成功");
	        	printJson(response, obj.toString());
			}else{
				JSONObject obj = new JSONObject();
	        	obj.put("result", "fail");
	        	obj.put("msg", "保存失败");
	        	printJson(response, obj.toString());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "historyData")
	@ResponseBody
	public String historyData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取历史预警数据
		Long id=Long.valueOf(request.getParameter("userId"));
		User user = userService.get(id);
		Long deptId = user.getDepartmentId();
		String areaId = user.getAreaId();
		Depart depart=deptService.get(deptId);
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
		PageInfo pageInfo = new PageInfo(logService.query(map));
		System.out.println(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		printJson(response, page.toString());
		return  "";
	}
	
	@RequestMapping(value = "getLogHistoryData")
	@ResponseBody
	public String getLogHistoryData(HttpServletRequest request, HttpServletResponse response)  {
		// 获取历史预警数据
		int id=Integer.valueOf(request.getParameter("userId"));
		String startDate =request.getParameter("startDate");
		String endDate =request.getParameter("endDate");
		JSONArray data=logService.getHistoryData(id,startDate,endDate);
		response.setHeader("Access-Control-Allow-Origin", "*");
		String result=data.toJSONString();
		return result;
		
	}

}
