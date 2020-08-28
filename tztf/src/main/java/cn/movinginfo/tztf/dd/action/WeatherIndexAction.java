package cn.movinginfo.tztf.dd.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.common.utils.HttpGetUtil;
import cn.movinginfo.tztf.sem.domain.SevAlarm;
import cn.movinginfo.tztf.sem.domain.SevReleaseChannelInstance;
import cn.movinginfo.tztf.sem.service.SevAlarmService;
import cn.movinginfo.tztf.sem.service.SevReleaseService;
import cn.movinginfo.tztf.sen.domain.Menu;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/dd/weather")
public class WeatherIndexAction {
	
	
	
	@Autowired
	private SevAlarmService sevAlarmService;
	
	@Autowired
	private SevReleaseService sevReleaseService;
	
	/**
	 * @author yougq
	 * 钉钉首页天气情况
	 * @return
	 */
	@RequestMapping("index")
	@ResponseBody
	public String getWeather(){
		String url = SystemProperties.getProperty("publish_url") + "/ddweather/data";
		return HttpGetUtil.sendGet(url);
		
	}
	
	@RequestMapping("data")
	@ResponseBody
	public String getData(){
		String realdataurl = "http://www.hzqx.com/hztq/data/dateLdptJson.json";
		return HttpGetUtil.sendGet(realdataurl);
	}
	
	@RequestMapping("dzzh")
	@ResponseBody
	public String getDzzhData(){
		String realdataurl = "http://www.hzqx.com/hztq/data/disaster.json";
		return HttpGetUtil.sendGet(realdataurl);
	}
	
	@RequestMapping("alarm")
	@ResponseBody
	public String getAlarm() throws Exception{
		String realdataurl =  SystemProperties.getProperty("publish_url") + "/fqyj/getalarmdata";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String result = HttpGetUtil.sendGet(realdataurl);
	  //  JSONArray json = new JSONArray(result);
	    List<SevAlarm> list = (List<SevAlarm>)JSONArray.toList(JSONArray.fromObject(result), SevAlarm.class);
	    for (SevAlarm sevAlarm : list) {
	    	String pubDateStr = sevAlarm.getPubDateStr();
	    	String releaseDateStr = sevAlarm.getReleaseDateStr();
	    	if(StringUtils.isNotEmpty(pubDateStr)){
	    		sevAlarm.setPubDate(sdf.parse(pubDateStr));
	    	}
	    	if(StringUtils.isNotEmpty(releaseDateStr)){
	    		sevAlarm.setReleaseDate(sdf.parse(releaseDateStr));
	    	}
		}
	    sevAlarmService.save(list);
	    return null;
	}
	
	@RequestMapping("release")
	@ResponseBody
	public String getRelease() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String realdataurl =  SystemProperties.getProperty("publish_url") + "/fqyj/getreleasedata";
		String result = HttpGetUtil.sendGet(realdataurl);
		//  JSONArray json = new JSONArray(result);
		List<SevReleaseChannelInstance> list = (List<SevReleaseChannelInstance>)JSONArray.toList(JSONArray.fromObject(result), SevReleaseChannelInstance.class);
		for (SevReleaseChannelInstance sevReleaseChannelInstance : list) {
			String createstr = sevReleaseChannelInstance.getCreatestr();
			sevReleaseChannelInstance.setCreateDate(sdf.parse(createstr));
		}
		sevReleaseService.save(list);
		return null;
	}
	
	
	@RequestMapping("zd")
	@ResponseBody
	public String getZdData(String type){
		String realdataurl = "http://www.hzqx.com/hztq/data/Micaps3/"+type+"/Hzgis.json";
		return HttpGetUtil.sendGet(realdataurl);
	}
	
	@RequestMapping("tqskxml")
	@ResponseBody
	public String getTqskXmlData(String type){
		String realdataurl = "http://www.hzqx.com/hztq/data/Micaps4/"+type+"/gis.xml";
		return HttpGetUtil.sendGet(realdataurl);
	}
	
	@RequestMapping("fc")
	@ResponseBody
	public String getFcData(String type){
		String realdataurl = "http://www.hzqx.com/hztq/data/Micaps3/"+type+"/windGis.json";
		return HttpGetUtil.sendGet(realdataurl);
	}
	
	@RequestMapping("jtzsKsPmJson")
	@ResponseBody
	public String getJtzsKsPmJson() throws UnirestException, IOException {
		HttpResponse<String> response = Unirest.post("http://www.hzjtydzs.com/WebService.asmx/GetExpressWayRank")
				  .header("content-type", "application/json")
				  .asString();
		FileUtils.writeStringToFile(new File(SystemProperties.APP_PATH+"/GetExpressWayRank.json"), response.getBody(), "UTF-8"); 
			//System.out.println(response.getBody());
		
		return response.getBody();
	}
    
	@RequestMapping("jtzsKsJson")
	@ResponseBody
	public String getJtzsKsJson() throws UnirestException, IOException {
		HttpResponse<String> response = Unirest.post("http://www.hzjtydzs.com/WebService.asmx/GetEXPRESSWAYData")
				  .header("content-type", "application/json")
				  .asString();
		FileUtils.writeStringToFile(new File(SystemProperties.APP_PATH+"/GetEXPRESSWAYData.json"), response.getBody(), "UTF-8"); 
		
		return response.getBody();
	}
	
	public static void main(String[] args) throws Exception {
		HttpResponse<String> response = Unirest.post("http://localhost:8080/sen/station/getOneMenu?id=1")
				  .header("content-type", "application/json")
				  .asString();
		FileUtils.writeStringToFile(new File(SystemProperties.APP_PATH+"/GetEXPRESSWAYData.json"), response.getBody(), "UTF-8"); 
		
		String a = response.getBody();
		System.out.println(a);
		JSONArray json = JSONArray.fromObject(a);
		if (json.size()> 0) {
            for (int i = 0; i < json.size(); i++) {
                JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                Menu m = JSON.parseObject(job.toString(), Menu.class);
                System.out.println(m);

            }
        }
//		String b = a.substring(1,a.length()-1);
//		String[] c = b.split(",");
//		System.out.println(c[0]);
//		Menu m = JSON.parseObject(c[0], Menu.class);
//		System.out.println(m);
	}
	
}
