package cn.movinginfo.tztf.dd.action;
/*package cn.movinginfo.tztf.dd.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import net.ryian.core.utils.SpringContextUtil;
import net.sf.json.JSONArray;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;

import cn.movinginfo.tztf.common.utils.HttpConfigUtil;
import cn.movinginfo.tztf.common.utils.HttpGetUtil;
import cn.movinginfo.tztf.sem.domain.SevAlarm;
import cn.movinginfo.tztf.sem.domain.SevReleaseChannelInstance;
import cn.movinginfo.tztf.sem.service.SevAlarmService;
import cn.movinginfo.tztf.sem.service.SevReleaseService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;

public class FQYJJob {
	private static Logger log = LoggerFactory.getLogger(FQYJJob.class);
    
	public void runJob() throws Exception {
		try {
			//SevAlarmService sevAlarmService = SpringContextUtil.getBean(SevAlarmService.class);
		    //SevReleaseService servletContext = SpringContextUtil.getBean(SevReleaseService.class);
			
			log.error(new Date() + "-----------分期预警同步定时任务开始-------------------");
			
			String root = HttpConfigUtil.getValue("root");
			
			// alarm表
			String alarmurl =root + "/fqyj/getalarmdata";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String result = HttpGetUtil.sendGet(alarmurl);
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
			    
			    
			    // release表
			    String releaseurl =root + "/fqyj/getalarmdata";
				String releaseresult = HttpGetUtil.sendGet(releaseurl);
				//  JSONArray json = new JSONArray(result);
				List<SevReleaseChannelInstance> releaselist = (List<SevReleaseChannelInstance>)JSONArray.toList(JSONArray.fromObject(result), SevReleaseChannelInstance.class);
				sevReleaseService.save(releaselist);
			    
			    
			log.error(new Date() + "-----------分期预警同步定时任务结束-------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
*/