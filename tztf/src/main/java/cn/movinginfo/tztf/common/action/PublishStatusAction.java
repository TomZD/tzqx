package cn.movinginfo.tztf.common.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import cn.movinginfo.tztf.dd.domain.Disaster;
import cn.movinginfo.tztf.dd.service.DisasterService;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.LedService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.domain.AlarmType;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.ChannelCountService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.ReleaseChannelService;
import cn.movinginfo.tztf.sys.service.UserService;

@Controller
public class PublishStatusAction extends BaseMagicAction{
	
	@Autowired
    protected UserService userService;
	@Autowired
	protected DeptService deptService;

	@Autowired
	protected AlarmService alarmService;
	@Autowired
	protected AlarmTypeService alarmTypeService;
	@Autowired
	protected ReleaseChannelInstanceService releaseChannelInstanceService;
	@Autowired
	protected ReleaseChannelService releaseChannelService;
	@Autowired

	protected ChannelCountService channelCountService;
	
	@Autowired
	protected DisasterService disasterService;
	
	@Autowired
	protected LedService ledService;
	
	
	 @RequestMapping(value = "/publishStatus")
	    public String publishStatus(HttpServletRequest req,HttpServletResponse response,Model model) {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Calendar a = Calendar.getInstance();
			int year = a.get(Calendar.YEAR);
			model.addAttribute("alarmYear", alarmService.getAlarmYear(year));
	    	Alarm alarm=alarmService.getNewAlarm();
	    	model.addAttribute("alarm", alarm);
	    	if(alarm!=null){
	    		if(Long.valueOf(alarm.getTypeId())>40){
		    		String image =  "./static/images/warnsmall/logo.png" ;
		        	model.addAttribute("image", image);
		    	}else{

		    		AlarmType alarmType = alarmTypeService.getByCaption(alarm.getAlarmTypeName());
		        	String image =  "./static/images/warnsmall/" + alarmType.getImage();
		        	model.addAttribute("image", image);
		    	}
		    	
		    	List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByVersion(alarm.getPubNo(),
						Integer.valueOf(alarm.getVersion()));
				List<Map<String, String>> content = new ArrayList<Map<String, String>>();
				for (int i = 0; i < releaList.size(); i++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("content", releaList.get(i).getContent());
					map.put("channel", releaseChannelService.get(releaList.get(i).getChannelId()).getName());
					map.put("releaseState", releaList.get(i).getReleaseState());
					map.put("update",sdf.format(releaList.get(i).getUpdateDate()) );
					map.put("id", releaList.get(i).getId().toString());
					content.add(map);
				}
				JSONObject obj = new JSONObject();
				obj.put("data", JSONArray.parseArray(JSONArray.toJSONString(content)));
				model.addAttribute("data", JSONArray.parseArray(JSONArray.toJSONString(content)));
				//printJson(response, obj.toString());
	    	}
	    	
	    	
	    	JSONArray data= new JSONArray();
	    	List<Disaster> disaster = disasterService.getDdDisaster();
	    	for(Disaster dis : disaster) {
	    		Date time = dis.getPubDate();
	    		SimpleDateFormat sdff = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	    		String date = sdff.format(time);
	    		String address = dis.getPubAdd();
	    		String imgs = dis.getImagePath();
	    		String[] img = imgs.split(",");
	    		for(int i=0;i<img.length;i++) {
	    			JSONObject o=new JSONObject();
	    			o.put("imagePath", img[i]);
	    			o.put("time", date);
	    			o.put("address", address);
	    			data.add(o);
	    		   }
	    		
	    		
	    	}
	    	model.addAttribute("allDisaster",data);
	    	JSONArray json=channelCountService.getCountList();
	    	model.addAttribute("count", json);
	    	model.addAttribute("countAll", channelCountService.getCount());
	    	model.addAttribute("dxMonth", channelCountService.getDxMonth());
	    	model.addAttribute("dxYear", channelCountService.getDxYear());
	    	model.addAttribute("dxAll", channelCountService.getCountByChannel("全网短信"));
	    	model.addAttribute("service", channelCountService.getCountByChannel("全网短信"));
	    	model.addAttribute("countAllWeb", channelCountService.getWebCount());
	        return "publishStatus";
	    }
	 
	 
	 @RequestMapping(value="/getAlarmByArea")
		@ResponseBody
		public JSONArray getAlarmByArea(HttpServletRequest request, HttpServletResponse response){
			
		 JSONArray data=alarmService.getAlarmByArea();
			response.setHeader("Access-Control-Allow-Origin", "*");
	
			return data;
		}
	 
//	 @RequestMapping(value="/getHighcharts")
//		@ResponseBody
//		public  List <Serie> getHighcharts(HttpServletRequest request, HttpServletResponse response){
//			
//		 List <Serie> series=channelCountService.getHighcharts1();
//			//serice[0]=getDay();
//			
//			return series;
//		}
	 @RequestMapping(value="/getHighcharts")
		@ResponseBody
		public String[][] getHighcharts(HttpServletRequest request, HttpServletResponse response){
			
			String[][] serice=channelCountService.getHighcharts();
			//serice[0]=getDay();
			
			return serice;
		}

	
	

}
