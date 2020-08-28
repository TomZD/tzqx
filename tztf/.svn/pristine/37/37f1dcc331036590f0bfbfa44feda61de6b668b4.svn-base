package cn.movinginfo.tztf.releasechannel.support;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.PublishUtils;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import net.ryian.commons.DateUtils;

public class PublishResultThread implements Runnable {
	
	 private ReleaseChannelInstanceService releaseChannelInstanceService;
	 private ReleaseChannelInstance channelInstance;
	
	public PublishResultThread(ReleaseChannelInstanceService releaseChannelInstanceService,ReleaseChannelInstance channelInstance){
		this.releaseChannelInstanceService = releaseChannelInstanceService;
		this.channelInstance = channelInstance;
	}

	@Override
	public void run() {
		try{
			
			String result = PublishUtils.faxResult(channelInstance.getResultId());
			JSONObject jo =  JSON.parseObject(result);
			Map map = (Map) jo.get("root");
			String resultcode = String.valueOf(map.get("resultcode"));
			if("1000".equals(resultcode)){
				Map resultMap = (Map) map.get("xmlresult");
				if(resultMap.get("starttime")!=null&&resultMap.get("endtime")!=null){//
					String starttime = (String) resultMap.get("starttime");
					String endtime = (String) resultMap.get("endtime");
					String resultdesc = String.valueOf(map.get("resultdesc"));
					channelInstance.setReleaseTime(DateUtils.parseTime(starttime));
					channelInstance.setArriveTime(DateUtils.parseTime(endtime));
					channelInstance.setReleaseState("4");
					channelInstance.setFeedbackMessage(resultdesc);
					releaseChannelInstanceService.saveOrUpdate(channelInstance);
					Long oid = channelInstance.getId();
		            Long ocid = channelInstance.getChannelId();
		            if(channelInstance.getInstanceIds()!=null&&!"".equals(channelInstance.getInstanceIds())){
		            	String[] instanceIdArr  = channelInstance.getInstanceIds().split(",");
		            	String[] channelIdArr  = channelInstance.getChannelIds().split(",");
		            	for(int i=0;i<instanceIdArr.length;i++){
		            		String instanceId = instanceIdArr[i];
		            		channelInstance.setId(Long.valueOf(instanceId));
		            		channelInstance.setChannelId(Long.valueOf(channelIdArr[i]));
		            		releaseChannelInstanceService.saveOrUpdate(channelInstance);
		            	}
		            	channelInstance.setId(oid);
		            	channelInstance.setChannelId(ocid);
		            }
				}else{
					PublishResultThread publishResultThread = new PublishResultThread(releaseChannelInstanceService,channelInstance);
					PublishUtils.getPublishResult(publishResultThread, 30l, 1);
				}
				
			}else{
				channelInstance.setReleaseState("3");
				channelInstance.setFeedbackMessage("发送失败");
				releaseChannelInstanceService.saveOrUpdate(channelInstance);
				Long oid = channelInstance.getId();
	            Long ocid = channelInstance.getChannelId();
	            if(channelInstance.getInstanceIds()!=null&&!"".equals(channelInstance.getInstanceIds())){
	            	String[] instanceIdArr  = channelInstance.getInstanceIds().split(",");
	            	String[] channelIdArr  = channelInstance.getChannelIds().split(",");
	            	for(int i=0;i<instanceIdArr.length;i++){
	            		String instanceId = instanceIdArr[i];
	            		channelInstance.setId(Long.valueOf(instanceId));
	            		channelInstance.setChannelId(Long.valueOf(channelIdArr[i]));
	            		releaseChannelInstanceService.saveOrUpdate(channelInstance);
	            	}
	            	channelInstance.setId(oid);
	            	channelInstance.setChannelId(ocid);
	            }
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
