package cn.movinginfo.tztf.sev.action;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.Reminder;
import cn.movinginfo.tztf.sev.domain.Suser;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.ReminderService;
import cn.movinginfo.tztf.sev.service.SuserService;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sys.domain.AlarmType;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;
import cn.movinginfo.tztf.sys.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
*
* @description:Reminder action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/sev/reminder")
@SuppressWarnings("serial")
public class ReminderAction extends MagicAction<Reminder,ReminderService> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DictService dictService;
	
	@Autowired
	private SuserService suserService;   
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private AlarmTypeService alarmTypeService;  
	
	@Autowired
	private DeptService deptService;  

	@Autowired
	private EventTypeService eventTypeService;

    
    @RequestMapping(value = "detail")
    public String check(HttpServletRequest request,Model model)
            throws Exception {
    	model.addAttribute("type","2");
    	Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
    	//审核退回
    	List<Alarm> listAuditAlarm = alarmService.getListAuditAlarm(deptId);
    	model.addAttribute("listAuditAlarm",listAuditAlarm);
    	//审核退回
    	//等待审核
    	List<Alarm> listWaitAuditAlarm = alarmService.getListWaitAuditAlarm(deptId);
    	model.addAttribute("listWaitAuditAlarm",listWaitAuditAlarm);
    	//等待审核
    	//正在发布
    	List<Alarm> listNowAlarm = alarmService.getListNowAlarm(deptId);
    	model.addAttribute("listNowAlarm",listNowAlarm);
    	//正在发布
    	//最近一个月发布
    	List<Alarm> publishOneMonthAlarm = alarmService.getListPublishOneMonthAlarm(deptId);
    	model.addAttribute("publishOneMonthAlarm",publishOneMonthAlarm);
    	//最近一个月发布
        return getNameSpace() + "detail";
    }



	@RequestMapping(value = "detailler")
	public String checker(HttpServletRequest request,Model model)
			throws Exception {
		model.addAttribute("type","2");
		Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
		//正在发布
		List<Alarm> listNowAlarm = alarmService.getListNowAlarm(deptId);
		model.addAttribute("listNowAlarm",listNowAlarm);
		//正在发布
		//最近一个月发布
		List<Alarm> publishOneMonthAlarm = alarmService.getListPublishOneMonthAlarm(deptId);
		model.addAttribute("publishOneMonthAlarm",publishOneMonthAlarm);
		//最近一个月发布
		return getNameSpace() + "detailler";
	}
    
    /**
     * @Description: TODO 获取可发布的预警类别
     * @Class: cn.movinginfo.tztf.sev.action.ReminderAction
     * @Title: getListType
     * @param request
     * @param response
     * @param model
     * @throws Exception void
     * @author: zhangdi
     * @createTime: 2020-8-28 17:01:14
     * @updateTime: 
     * @throws 
     */
    @RequestMapping(value = "type")
    public void getListType(HttpServletRequest request,HttpServletResponse response,Model model)
            throws Exception {
    	Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
    	String deptName = deptService.get(deptId).getName();
    	JSONObject object = new JSONObject();
    	JSONObject obj = new JSONObject();
    	if(deptName.indexOf("气象")>-1){
    		
    		// 开始：2020-8-30 16:08:49 # zhangdi - 气象预警可配置。
//    		List<AlarmType> list =alarmTypeService.getDistinctAlarmType();
    		List<AlarmType> list =alarmTypeService.getDistinctAlarmNameType(deptId);
    		// 结束：2020-8-30 16:09:41 # zhangdi - 气象预警可配置。
    		
    		obj.put("alarm", list);
    		obj.put("event",eventTypeService.getDeptEventType2(deptId));
    		object.put("alarm", obj);
    	}else{
    		object.put("event",eventTypeService.getDeptEventType2(deptId));
    	}
    	printJson(response, com.alibaba.fastjson.JSON.toJSONString(object));
//    	printJson(response, com.alibaba.fastjson.JSON.toJSONString(list));
    }
	

	
	/**
     * 将查询得到的用户容器以JSON格式输出到前台的combobox中以完成多选
     * @param request
     * @param response
     * @throws Exception
     * @author 韩明睿
     */
    @RequestMapping(value = "/querySuser")
    public void querySuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
  		
        try {
            @SuppressWarnings("unchecked")
			List<Suser> list = entityService.querySuser(getParameterMap(request));
            printJson(response,JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    @RequestMapping(value = "/queryDic")
    public void queryDic(HttpServletRequest request, HttpServletResponse response) throws Exception {
  		
        try {
            @SuppressWarnings("unchecked")
			List<Dict> list = dictService.getDicList("strong_weather");
            printJson(response,JSON.toJSONString(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    @Override
    @RequestMapping(value = "edit/{id}")
    public String edit(HttpServletRequest request,@PathVariable("id") Long id, Model model)
            throws Exception {
        if (id != null) {
            Reminder entity = entityService.get(id);
            model.addAttribute("model", entity);
            List<Suser> suserList= new ArrayList<Suser>();
            suserList = suserService.queryById(entity.getSuid().toString());
            model.addAttribute("name", suserList.get(0).getName());
            beforeEdit(request,model,entity);
        }
        return getNameSpace() + "edit";
    }


}
