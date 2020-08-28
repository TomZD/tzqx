package cn.movinginfo.tztf.common.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import cn.movinginfo.tztf.common.constants.SystemProperties;

public class JtzsUtils {

	public static void getJtzsJson(){
		try {
			HttpResponse<String> response = Unirest.post("http://www.hzjtydzs.com/WebService.asmx/GetPageOneData_Select")
					  .header("content-type", "application/json")
					  .asString();
			FileUtils.writeStringToFile(new File(SystemProperties.APP_PATH+"/GetPageOneData_Select.json"), response.getBody(), "UTF-8"); ;
			System.out.println(response.getBody());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getJtzsKsJson(){
		try {
			HttpResponse<String> response = Unirest.post("http://www.hzjtydzs.com/WebService.asmx/GetEXPRESSWAYData")
					  .header("content-type", "application/json")
					  .asString();
			FileUtils.writeStringToFile(new File(SystemProperties.APP_PATH+"/GetEXPRESSWAYData.json"), response.getBody(), "UTF-8"); 
			System.out.println(response.getBody());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void getJtzsKsPmJson(){
		try {
			HttpResponse<String> response = Unirest.post("http://www.hzjtydzs.com/WebService.asmx/GetExpressWayRank")
					  .header("content-type", "application/json")
					  .asString();
			FileUtils.writeStringToFile(new File(SystemProperties.APP_PATH+"/GetExpressWayRank.json"), response.getBody(), "UTF-8"); 
			System.out.println(response.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		getJtzsJson();
//		getJtzsKsJson();
//		getJtzsKsPmJson();
	}
}
