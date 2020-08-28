package cn.movinginfo.tztf.common.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.sys.domain.ChannelCount;
import cn.movinginfo.tztf.sys.service.ChannelCountService;


@Service(value = "channelDataTask")
public class ChannelDataTask {
	@Autowired
	private ChannelCountService channelCountService;
	
	
	
	public void job(){
		//System.out.println(SystemProperties.getProperty("publish_url")+"/hzweather/hzvisitcount");
		try
	      {
			String path=SystemProperties.getProperty("publish_url")+ "/hzweather/hzvisitcount";
	         URL url = new URL(path);
	         //URL url = new URL("http://localhost:8089/hzweather/hzvisitcount");
	         URLConnection urlConnection = url.openConnection();
	         HttpURLConnection connection = null;
	         if(urlConnection instanceof HttpURLConnection)
	         {
	            connection = (HttpURLConnection) urlConnection;
	         }
	         else
	         {
	            System.out.println("请输入 URL 地址");
	            return;
	         }
	         BufferedReader in = new BufferedReader(
	         new InputStreamReader(connection.getInputStream()));
	         String urlString = "";
	         String current;
	         while((current = in.readLine()) != null)
	         {
	            urlString += current;
	         }
	         ChannelCount count=new ChannelCount();
	         count.setChannelName("杭州天气网");
	         count.setCount(Long.valueOf(urlString));
	         channelCountService.saveCount(count);
	         
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
		

		
	}
	


	
	
	
	

	public static void main(String[] args) {
		ChannelDataTask t=new ChannelDataTask();
		t.job();

	}
}
