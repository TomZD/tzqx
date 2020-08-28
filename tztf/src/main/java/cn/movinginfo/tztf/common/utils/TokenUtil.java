package cn.movinginfo.tztf.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.grasssoft.dingding.SendDDMsg;

import cn.movinginfo.tztf.common.constants.SystemProperties;

public class TokenUtil {
	public static String GetToken(HttpServletRequest request, String token,
			String contents) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();// 审核已通过，群就会收到预警内容
		String url = SystemProperties.getProperty("publish_url") + "/dingding/content";
		// String jump = "https://www.baidu.com";
		List<BasicNameValuePair> resultdata = new ArrayList<BasicNameValuePair>();
		HttpPost post = new HttpPost(url);
		resultdata.add(new BasicNameValuePair("webhooktoken","https://oapi.dingtalk.com/robot/send?access_token=" + token));
		resultdata.add(new BasicNameValuePair("content", contents));
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(resultdata,
				"UTF-8");
		post.setEntity(uefEntity);
		HttpEntity entity2 = post.getEntity();
		CloseableHttpResponse httpResponse = httpClient.execute(post);
		HttpEntity entity = httpResponse.getEntity();
		String result = EntityUtils.toString(entity);
		return result;
	}
	
	
	public static String sendContent(@RequestParam(value = "webhooktoken", required = false) String webhooktoken,  
			  @RequestParam(value = "content", required = false) String content) {

		String webhooktokens=SystemProperties.getProperty("dd_token") +webhooktoken;
		if(StringUtils.isEmpty(content)){
		return "正文不能为空！";
		}else if(StringUtils.isEmpty(webhooktokens)){
		return "webhooktoken不能为空！";
		}
		String result = SendDDMsg.sendMsgwithText(webhooktokens, content);
		return result;
		}

}
