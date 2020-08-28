package cn.movinginfo.tztf.sen.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

@Controller
@RequestMapping("/sen/data")
public class DataAction {
	
	@RequestMapping("getWaterData")
	@ResponseBody
	public String getWaterData(String time) {
		String result = null;
		String token="f76024edfabf4e6387be765b74619ff2";
		String url = "http://10.135.134.4:9091/stations-service/query/rsvr/waterLevel";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json").queryString("time", time)
					  .queryString("token",token)
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("getWaterTimeData")
	@ResponseBody
	public String getWaterTimeData() {
		String result = null;
		String token="f76024edfabf4e6387be765b74619ff2";
		String url = "http://10.135.134.4:9091/stations-service/query/rsvr/waterLevel/timeLine";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .queryString("token",token)
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		//System.out.println(getWaterData());
//		System.out.println(getWaterTimeData());
	}
}
