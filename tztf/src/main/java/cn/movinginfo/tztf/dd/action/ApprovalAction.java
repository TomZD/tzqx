package cn.movinginfo.tztf.dd.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.action.BaseMagicAction;
import cn.movinginfo.tztf.dd.domain.WarnDetail;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.UserService;


@Controller
@RequestMapping("/dd/approval")
public class ApprovalAction  extends BaseMagicAction {
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
	@Autowired
	private AlarmService alarmService;
	
	
	
	@RequestMapping("alarmListToApproval")
	@ResponseBody
	public JSONArray getAlarmListToApproval(Long userId) {
		Long deptId = userService.get(userId).getDepartmentId();
		JSONArray json=alarmService.getAlarmListByDeptId(deptId);
		
		return json;
		
	}
	
	@RequestMapping("alarmList")
	@ResponseBody
	public JSONArray getDdAlarmList(Long userId) {
		Long deptId = userService.get(userId).getDepartmentId();
		JSONArray json=alarmService.getDdAlarmListByDeptId(deptId);
		
		return json;
		
	}
	
	@RequestMapping(value = "uploadPage")
	public void uploadPageFromDd(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		String name = alarmService.uploadPage(file, request);
		String alarmId = request.getParameter("alarmId");
		if(!StringUtils.isEmpty(alarmId)){
			Alarm alarm = new Alarm();
			alarm.setId(Long.valueOf(alarmId));
			alarm.setImagePath(name);
			alarmService.saveOrUpdate(alarm);
		}
		JSONObject obj = new JSONObject();
		obj.put("data", name);
		printJson(response, obj.toString());
	}
	@RequestMapping("detail")
	@ResponseBody
	public WarnDetail getDetail(Long id){
		WarnDetail warn = alarmService.getDetailById(id);
		List<String> name = alarmService.getName(id);
		warn.setChannelname(name);
		return warn;
	}

}













