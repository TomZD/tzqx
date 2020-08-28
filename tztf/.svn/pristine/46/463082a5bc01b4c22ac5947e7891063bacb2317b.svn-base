package cn.movinginfo.tztf.sev.action;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.AlarmNotice;
import cn.movinginfo.tztf.sev.service.AlarmNoticeService;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.service.UserService;

@Controller
@RequestMapping("/sev/alarm-notice")
public class AlarmNoticeAction extends MagicAction<AlarmNotice, AlarmNoticeService> {

	@Autowired
	private AlarmService alarmService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "data")
	public String data(String alarmId,HttpServletRequest request, Model model){
		if(StringUtils.isEmpty(alarmId)){
			alarmId = request.getParameter("alarmId");
		}
		Alarm alarm = alarmService.get(Long.valueOf(alarmId));
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("alarmId", alarmId);
		List<AlarmNotice> alarmNoticeList = entityService.getAlarmNoticeListByAlarmId(Long.valueOf(alarmId));
		model.addAttribute("alarmNoticeList", alarmNoticeList);
		model.addAttribute("pdfPath", alarm.getNoticeFile().replaceAll("doc", "pdf"));
		return getNameSpace() + "data";
	}
	
	@RequestMapping(value = "query")
	public String query(@ModelAttribute("alarmId") String alarmId,HttpServletRequest request, Model model){
		model.addAttribute("alarmId", alarmId);
		return getNameSpace() + "query";
	}
	
	@RequestMapping(value = "departmentData")
	public String departmentData(HttpServletRequest request, Model model){
		Map<String,String> paramMap = new HashMap<String,String>();
		Long departmentId = userService.get(this.getCurrentUser().id).getDepartmentId();
		List<AlarmNotice> alarmNoticeList = entityService.getAlarmNoticeListByDepartmentId(Long.valueOf(departmentId));
		model.addAttribute("alarmNoticeList", alarmNoticeList);
		return getNameSpace() + "department_data";
	}
	
	@RequestMapping(value = "departmentQuery")
	public String departmentQuery(HttpServletRequest request, Model model){
		return getNameSpace() + "department_query";
	}
	
	/**
	 * 向编辑框传值
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editAlarmNotice")
	public void editDepartment(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		AlarmNotice alarmNotice = entityService.get(Long.parseLong(id));
		if(alarmNotice.getReceiveDate()==null){
			alarmNotice.setReceiveDate(new Date());
		}
		entityService.saveOrUpdate(alarmNotice);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(alarmNotice));
	}
	
	
	/**
	 * 向编辑框传值
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "detail")
	public void detail(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		AlarmNotice alarmNotice = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(alarmNotice));
	}
	
	
	
	@Override
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			AlarmNotice alarmNotice = bindEntity(request,AlarmNotice.class);
			alarmNotice.setReplyDate(new Date());
			entityService.saveOrUpdate(alarmNotice);
			printJson(response, messageSuccuseWrap());
		} catch (Exception e) {
			e.printStackTrace();
			printJson(response, messageFailureWrap("保存失败！"));
		}
	}
	
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @param data
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "upload")
	public void upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException {
		String name = entityService.upload(file, request);
		JSONObject obj = new JSONObject();
		obj.put("data", name);
		printJson(response, obj.toString());
	}
	
}
