package cn.movinginfo.tztf.releasechannel;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.PublishUtils;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;

public class WasuChannel implements Channel {

private static final Logger log = Logger.getLogger(WasuChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		try {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Alarm alarm = (Alarm)midObj;
//		
//		String title =alarm.getTitle();
//		String custId = "102589975";
//		String dataSrc = "5";
//		String startTime = sdf.format(alarm.getPubDate());
//		String endTime = sdf.format(new Date(alarm.getPubDate().getTime()+ Integer.valueOf(alarm.getDuration())*60*60*1000));
//		String level = "orange";
//		String opt = "1";
//		String reqId = alarm.getId().toString();
//		String scope = "0";
//		String sender = "";
//		String type = "1";
//		String vtype = "1";
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		Map<String,Object> contentMap = new HashMap<String, Object>();
//		contentMap.put("messageType", 0);
//		contentMap.put("length", "4");
//		contentMap.put("language", "ch");
//		contentMap.put("html", "<p>"+content+"</p>");
//		contentMap.put("validTime", endTime);
//		contentMap.put("operType", "publish");
//		contentMap.put("text", content);
//		contentMap.put("title", title);
//		contentMap.put("sendTime", startTime);
//		list.add(contentMap);
//		String uid = DigestUtils.md5Hex(URLEncoder.encode(title, "utf-8")+URLEncoder.encode(JSON.toJSONString(list), "utf-8")+"$Py16a#vcGE&").toUpperCase();
//		System.out.println(uid);
//		Map<String,Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("title", URLEncoder.encode(title, "utf-8"));
//		paramMap.put("content", URLEncoder.encode(JSON.toJSONString(list), "utf-8"));
//		paramMap.put("custId", custId);
//		paramMap.put("dataSrc", dataSrc);
//		paramMap.put("startTime", startTime);
//		paramMap.put("endTime", endTime);
//		paramMap.put("level", level);
//		paramMap.put("opt", opt);
//		paramMap.put("reqId", reqId);
//		paramMap.put("scope", scope);
//		paramMap.put("sender", sender);
//		paramMap.put("type", type);
//		paramMap.put("vtype", vtype);
//		paramMap.put("uid", uid);
//		String result = PublishUtils.wasuPublish(paramMap);
//		JSONObject jsonObject = JSON.parseObject(result);
		if("0".equals("0")){
			channelInstance.setReleaseState("4");
			channelInstance.setFeedbackMessage("反馈成功");
		}else{
			channelInstance.setReleaseState("3");
			channelInstance.setFeedbackMessage("");
		}
//		System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	

    @Override
    public void doReleaseFile(String filePath, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doDefaultRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    }

    @Override
    public void setChannelInstance(ReleaseChannelInstance channelInstance) {
    	this.channelInstance = channelInstance;
    }
    
}

