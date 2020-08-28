package cn.movinginfo.tztf.sen.action;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.*;
import cn.movinginfo.tztf.sen.service.DangerAlarmService;
import cn.movinginfo.tztf.sen.service.DangerPointService;
import cn.movinginfo.tztf.sen.service.MenuService;
import cn.movinginfo.tztf.sen.service.PointDangertypeService;
import cn.movinginfo.tztf.sys.service.AreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

/**
*
* @description:灾害点报警记录
* @author: autoCode
* @history:
*/

@Controller
@RequestMapping("/sen/dangerAlarm")
public class DangerAlarmAction {
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private DangerPointService dangerPointService;
	
	@Autowired
	private PointDangertypeService pointDangertypeService;

	@Autowired
	private DangerAlarmService dangerAlarmService;
	@Autowired
	private MenuService menuService;
	

	@RequestMapping(value="addAlarm")
	@ResponseBody
	public void addAlarm(HttpServletRequest request, HttpServletResponse response){
		JSONArray json=areaService.getCountryData();
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<DangerPoint> list = dangerPointService.getAll();
		for (DangerPoint d : list) {
			List<PointDangertype> plist=pointDangertypeService.getPointDangerByPointId(d.getId());
			
				if(isAlarm(plist)){
					
				}
			
			
		}
		String result =   json.toJSONString();

	}


	private boolean isAlarm(List<PointDangertype> plist) {
		// TODO Auto-generated method stub
		for (PointDangertype p : plist) {
			if(p.getMeteorologicalId().equals("1")){
				
			}if(p.getMeteorologicalId().equals("2")){
				
			}if(p.getMeteorologicalId().equals("3")){
				
			}if(p.getMeteorologicalId().equals("4")){
				
			}
			
		}
		return false;
	}

	/**
	 * @author  qh
	 * @time 2019-08-01
	 * 地质灾害风险
	 */
	@RequestMapping(value="getCurrentRisk")
	@ResponseBody
	public List<DangerAlarmDetail> getCurrentRisk(String lowType){
		Calendar ca = Calendar.getInstance();
		String year = ca.get(Calendar.YEAR)+"-";//获取年份
		String  month=ca.get(Calendar.MONTH)+"-";//获取月份
		String  day=ca.get(Calendar.DATE)+" ";//获取日
		String  dayMore=ca.get(Calendar.DATE+1)+" ";
		//当前风险 需要绑定时间轴
        if(lowType!=null && lowType.equals("current_risk")) {
			/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String currentTime = df.format(new Date());
			Calendar beforeTime = Calendar.getInstance();
			beforeTime.add(Calendar.MINUTE, -10);// 10分钟之前的时间
			Date beforeD = beforeTime.getTime();
			String before10 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);  // 前10分钟时间
			 //currentTime="2019-07-30 09:58:00";
			// before10="2019-07-30 09:48:00";
			List<DangerAlarmDetail> list = dangerAlarmService.getCurrentRisk(currentTime,before10);
			//除去pointId重复的数据 保留alarm_time最新的记录
			list=this.getDistinctList(list);
			return list;*/

			//获取时间轴
			List<Menu> menuList = menuService.getMenuByType(lowType);
			String title = "";
			if (menuList.size() != 0) {
				Menu menu = menuList.get(0);
				title = menu.getName();
			}
			List<DangerAlarmDetail> list = dangerAlarmService.getAlarmTime();
			for(int i=0;i<list.size();i++){
			//	String time = filename.substring(0,filename.lastIndexOf("."));
				String datetime=list.get(i).getTime();
				String time=list.get(i).getTime().replaceAll(" ","").replaceAll(":","").replaceAll("-","");
				Date date = DateUtil.parse(time.substring(0,12), "yyyyMMddHHmm");
				String now = DateUtil.format(date, "yyyy年MM月dd日 HH时mm分");
				list.get(i).setPrimaryTime(now);
				list.get(i).setTitle(title+now);
				list.get(i).setTime(datetime.substring(0,datetime.lastIndexOf(".")));
			}
			return list;
		}
        //当天12时的风险  2019-07-25 19:01:00
        else if(lowType!=null && lowType.equals("12hour_risk")){
			  //String before12time=year+month+day+"11:30:00";
			  //String after12Time=year+month+day+"12:30:00";
			   String timeOf12=year+month+day+"12:00:00";
			   List<DangerAlarmDetail> list = dangerAlarmService.getTimeOf12Risk(timeOf12);
			  //除去pointId重复的数据 保留alarm_time最新的记录
			  list=this.getDistinctList(list);
			  return list;
		}
        //当前24的风险
        else if(lowType!=null && lowType.equals("24hour_risk")){
			//String before24time=year+month+day+"23:30:00";
			//String after24Time=year+month+dayMore+"00:30:00";
			String timeOf24=year+month+day+"24:00:00";
			List<DangerAlarmDetail> list = dangerAlarmService.getTimeOf24Risk(timeOf24);
			//除去pointId重复的数据 保留alarm_time最新的记录
			list=this.getDistinctList(list);
			return list;
		}
        //最近风险
        else if(lowType!=null && lowType.equals("recently_risk")){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String currentTime = df.format(new Date());
			Calendar beforeTime = Calendar.getInstance();
			beforeTime.add(Calendar.MINUTE, -120);// 120分钟(2小时)之前的时间
			Date beforeD = beforeTime.getTime();
			String before120 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);  // 前10分钟时间
			List<DangerAlarmDetail> list = dangerAlarmService.getRecentRisk(before120,currentTime);
			//除去pointId重复的数据 保留alarm_time最新的记录
			list=this.getDistinctList(list);
			return list;
		}
        else{
        	return null;
		}
	}

	/**
	 * @author qh
	 * @time 2019-08-05
	 * 	除去pointId重复的数据 保留level最高的记录 若level相等 保留时间最新的数据
	 */
	public List<DangerAlarmDetail> getDistinctList(List<DangerAlarmDetail> list){
		List<DangerAlarmDetail> distinctList= new ArrayList<DangerAlarmDetail>();
		for(int i=0;i<list.size();i++){
			if(i==0){
				distinctList.add(list.get(0));
			}
			else{
				String  highPointId=list.get(i).getPointId();
				String  lowPointId=list.get(i-1).getPointId();

				if(highPointId.equals(lowPointId)){
					continue;
				}
				//pointId不相等的数据
				 else{
					distinctList.add(list.get(i));
				}
			}
		}
		return distinctList;
	}


	/**
	 * 根据报警时间 获取站点数据
	 */
	@RequestMapping(value="/getCurrentRiskData")
	@ResponseBody
	public List<DangerAlarmDetail> getCurrentRiskData(String time) {
		//根据时间轴的时间 动态获取数据
		List<DangerAlarmDetail> list = dangerAlarmService.getTimeOf24Risk(time);
		//除去pointId重复的数据 保留alarm_time最新的记录
		list=this.getDistinctList(list);
		return list;

	}

}
