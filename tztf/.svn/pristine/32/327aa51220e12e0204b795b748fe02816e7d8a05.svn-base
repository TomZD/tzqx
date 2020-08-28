package cn.movinginfo.tztf.releasechannel;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.PublishUtils;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.service.DeptService;
import net.ryian.core.utils.SpringContextUtil;

/**
 * weixin发布渠道接口
 * @author zhangd
 */
public class WeixinChannel implements Channel {

	private static final Logger log = Logger.getLogger(WeixinChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	private DeptService deptService = SpringContextUtil.getBean(DeptService.class);
	private AlarmService alarmService = SpringContextUtil.getBean(AlarmService.class);
	
//    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
	
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("进入微信方法----------------------------");
		 
		int result=alarmService.createFile(17);
		if(result==1){
			channelInstance.setReleaseState("4");
			channelInstance.setFeedbackMessage("文件生成成功");
		}else{
			channelInstance.setReleaseState("3");
			channelInstance.setFeedbackMessage("文件生成失败");
		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
//		Alarm alarm = (Alarm)midObj;
//		String title = alarm.getTitle();
//		String alarmType = alarm.getAlarmTypeName().substring(0, alarm.getAlarmTypeName().length()-2);
//		String alarmLevel = alarm.getAlarmTypeName().substring(alarm.getAlarmTypeName().length()-2, alarm.getAlarmTypeName().length());
//		String publishDate = sdf.format(alarm.getPubDate());
//		String releaseDate = sdf.format(alarm.getPubDate());
//		Depart depart = deptService.get(alarm.getDeptId());
//		String department = depart.getName();
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		
//		paramMap.put("title", title);
//		paramMap.put("department", department);
//		paramMap.put("alarmType", alarmType);
//		paramMap.put("alarmLevel", alarmLevel);
//		paramMap.put("publishDate", publishDate);
//		if(title.indexOf("解除")!=-1){
//			paramMap.put("releaseDate", releaseDate);
//		}
//		paramMap.put("content", content);
//		
//		String result = PublishUtils.weixin(paramMap);
//		JSONObject jsonObject = JSON.parseObject(result);
//		channelInstance.setReleaseState("4");
//		channelInstance.setFeedbackMessage("发布时间");
//		System.out.println(result);
		System.out.println("发送微信结束----------------------------");
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
    
    public static void main(String[] args) {
    	WeixinChannel wb = new WeixinChannel();
    	try {
			wb.doRelease("测试", new Alarm());
		} catch (ChannelReleaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
