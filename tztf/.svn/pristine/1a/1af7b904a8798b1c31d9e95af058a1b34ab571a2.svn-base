package cn.movinginfo.tztf.common.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import cn.movinginfo.tztf.common.constants.SystemProperties;

public class PublishUtils {
	
	private static Logger logger = LoggerFactory.getLogger(PublishUtils.class);
	
	private static ScheduledExecutorService ses  = Executors.newScheduledThreadPool(10);  

	public static String weibo(String content){
		String result = null;
		String url = SystemProperties.getProperty("publish_url") + "weibo/publish";
		try {
			Map<String,Object> map = new  HashMap<String,Object>();
			map.put("content", content);
			map.put("id", "");
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .body(JSON.toJSONString(map))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			logger.error("weibo", e);
		}
		return result;
	}
	
	public static String weixin(Map<String,Object> map){
		String result = null;
		String url = SystemProperties.getProperty("publish_url") + "weixin/publish";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .body(JSON.toJSONString(map))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			logger.error("weixin", e);
		}
		return result;
	}
	
	public static String wasuPublish(Map<String,Object> map){
		String result = null;
		String url = SystemProperties.getProperty("publish_url") + "wasu/publish";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .body(JSON.toJSONString(map))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			logger.error("wasuPublish", e);
		}
		return result;
	}
	
	public static String cmccPublish(Map<String,Object> map){
		String result = null;
		String url = SystemProperties.getProperty("publish_url_nw") + "sms/cmccPublish";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .body(JSON.toJSONString(map))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			logger.error("cmccPublish", e);
		}
		return result;
	}
	
	public static String cuccPublish(Map<String,Object> map){
		String result = null;
		String url = SystemProperties.getProperty("publish_url") + "sms/cuccPublish";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .body(JSON.toJSONString(map))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			logger.error("cmccPublish", e);
		}
		return result;
	}
	
//	public static String wasuRelease(Map<String,Object> map){
//		String result = null;
//		String url = SystemProperties.getProperty("publish_url") + "wasu/release";
//		try {
//			HttpResponse<String> response = Unirest.post(url)
//					  .header("content-type", "application/json")
//					  .body(JSON.toJSONString(map))
//					  .asString();
//			result = response.getBody();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	public static String guotuPublish(Map<String,Object> map){
		String result = null;
		String url = SystemProperties.getProperty("publish_url_nw") + "guotu/publish";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .body(JSON.toJSONString(map))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("guotuPublish", e);
		}
		return result;
	}
	
	public static String guotuRelease(Map<String,Object> map){
		String result = null;
		String url = SystemProperties.getProperty("publish_url_nw") + "guotu/release";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .body(JSON.toJSONString(map))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("guotuRelease", e);
		}
		return result;
	}
	
	public static String toFax(String destnumber,String title,String file,String filename){
		String result = null;
		String url = SystemProperties.getProperty("publish_url") + "fax/sendfax";
		try {
			String sendnumber = SystemProperties.getProperty("sendnumber");
			String useraccount = SystemProperties.getProperty("useraccount");
			String bnetaccount = SystemProperties.getProperty("bnetaccount");
			String password = SystemProperties.getProperty("password");
			String areaid = SystemProperties.getProperty("areaid");
			Map<String,Object> jsonObject = new HashMap<String,Object>();
			jsonObject.put("sendnumber", sendnumber);
			jsonObject.put("useraccount", useraccount);
			jsonObject.put("bnetaccount", bnetaccount);
			jsonObject.put("password", password);
			jsonObject.put("areaid", areaid);
			jsonObject.put("destnumber", destnumber);
			jsonObject.put("title", title);
			jsonObject.put("file", file);
			jsonObject.put("filename", filename);
			HttpResponse<String> response = Unirest.post(url)
					.header("content-type", "application/json")
					.body(JSON.toJSONString(jsonObject))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("toFax", e);
		}
		return result;
	}
	
	public static String faxResult(String resultId){
		String result = null;
		String url = SystemProperties.getProperty("publish_url") + "fax/getDetail";
		try {
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .queryString("faxid", resultId)
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String messageRemind(String mobile,String content){
		String result = null;
		String url = SystemProperties.getProperty("publish_url") + "sms/messageRemind";
		try {
			Map map = new HashMap();
			map.put("mobile", StringUtils.join(mobile.split("、|;"),","));
			map.put("content", content);
			HttpResponse<String> response = Unirest.post(url)
					  .header("content-type", "application/json")
					  .body(JSON.toJSONString(map))
					  .asString();
			result = response.getBody();
		} catch (Exception e) {
			logger.error("cmccPublish", e);
		}
		return result;
	}
	
	public static String encryptToBase64(String filePath) {
		if (filePath == null) {
			return null;
		}
		try {
			byte[] b = Files.readAllBytes(Paths.get(filePath));
			return Base64.encodeBase64String(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getPublishResult(Runnable command,Long delay,Integer count){
		if(count==1){
			ses.schedule(command, delay, TimeUnit.SECONDS);
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(toWeibo("测试微博"));
		String file = "D://20181105172111.doc";
//		String file = "D://1234.txt";
		System.out.println(encryptToBase64(file));
//		System.out.println(toFax("057187352314","测试",file,"123.txt"));
//		faxResult("057187352314-1720104652779314");
//		String str = "";
//		for(int i=1;i<=28;i++){
//			str += "0_"+i + ".JPG,"; 
//		}
//		System.out.println(str);
		
		String sendnumber = SystemProperties.getProperty("sendnumber");
		String useraccount = SystemProperties.getProperty("useraccount");
		String bnetaccount = SystemProperties.getProperty("bnetaccount");
		String password = SystemProperties.getProperty("password");
		String areaid = SystemProperties.getProperty("areaid");
	}
	
	
}
