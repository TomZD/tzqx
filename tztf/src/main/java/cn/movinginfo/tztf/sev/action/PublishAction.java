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
import cn.movinginfo.tztf.sev.domain.Publish;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.PublishService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.service.UserService;

@Controller
@RequestMapping("/sev/publish")
public class PublishAction extends MagicAction<Publish, PublishService> {

	@Autowired
	private AlarmService alarmService;
	@Autowired
	private UserService userService;
	@Autowired
    private ReleaseChannelInstanceService channelInstanceService;
	
	@RequestMapping(value = "data")
	public String data(String alarmId, String instanceId,HttpServletRequest request, Model model){
		if(StringUtils.isEmpty(alarmId)){
			alarmId = request.getParameter("alarmId");
		}
		if(StringUtils.isEmpty(instanceId)){
			instanceId = request.getParameter("instanceId");
		}
		List<Publish> publishList = new ArrayList<Publish>();
		if(!StringUtils.isEmpty(alarmId)){
			publishList = entityService.getPublishListByAlarmId(Long.valueOf(alarmId));
		}else{
			publishList = entityService.getPublishListByInstanceId(Long.valueOf(instanceId));
		}
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("alarmId", alarmId);
		model.addAttribute("publishList", publishList);
		
		return getNameSpace() + "data";
	}
	
	@RequestMapping(value = "query")
	public String query(@ModelAttribute("alarmId") String alarmId,@ModelAttribute("instanceId") String instanceId,HttpServletRequest request, Model model){
		model.addAttribute("alarmId", alarmId);
		model.addAttribute("instanceId", instanceId);
		return getNameSpace() + "query";
	}
	
	@RequestMapping(value = "departmentData")
	public String departmentData(HttpServletRequest request, Model model){
		Map<String,String> paramMap = new HashMap<String,String>();
		Long departmentId = userService.get(this.getCurrentUser().id).getDepartmentId();
		List<Publish> publishList = entityService.getPublishListByDepartmentId(Long.valueOf(departmentId));
		Date nowtime=new Date();
		for(Publish attribute : publishList) {
			if(attribute.getReceiveDate()==null)
			  attribute.setReceiveDate(nowtime);
			entityService.saveOrUpdate(attribute);
			}
		model.addAttribute("publishList", publishList);
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
	@RequestMapping(value = "editPublish")
	public void editDepartment(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Publish publish = entityService.get(Long.parseLong(id));
		if(publish.getReceiveDate()==null){
			publish.setReceiveDate(new Date());
		}
		entityService.saveOrUpdate(publish);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(publish));
	}
	
	
	/**
	 * 向编辑框传值
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "detail")
	public void detail(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Publish publish = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(publish));
	}
	
	
	
	@Override
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			Publish publish = bindEntity(request,Publish.class);
			publish.setReplyDate(new Date());
			entityService.saveOrUpdate(publish);
			publish = entityService.get(publish.getId());
			ReleaseChannelInstance releaseChannelInstance = new ReleaseChannelInstance();
			releaseChannelInstance.setId(publish.getReleaseChannelInstanceId());
			releaseChannelInstance.setReleaseState("4");
			releaseChannelInstance.setFeedbackMessage("反馈成功！");
			channelInstanceService.saveOrUpdate(releaseChannelInstance);
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
