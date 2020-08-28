package cn.movinginfo.tztf.releasechannel;



import net.ryian.core.utils.SpringContextUtil;
import org.apache.log4j.Logger;

import cn.movinginfo.tztf.common.utils.FtpHelper;
import cn.movinginfo.tztf.common.utils.GTAlarmDomain;
import cn.movinginfo.tztf.common.utils.ZipUtil;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sys.domain.Decision;
import cn.movinginfo.tztf.sys.domain.People;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.DecisionService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.PeopleService;
import cn.movinginfo.tztf.webserviceClient.sms.SendSMsgService;
import cn.movinginfo.tztf.webserviceClient.sms.SendSMsgServiceService;
import cn.movinginfo.tztf.webserviceClient.sms.ServiceException_Exception;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 短信组发布渠道接口
 * @author zhangd
 */
public class SmsGroupChannel implements Channel {

	private static final Logger log = Logger.getLogger(SmsGroupChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
    
    private DecisionService decisionService = SpringContextUtil.getBean("decisionService");
    
    private DeptService deptService = SpringContextUtil.getBean("deptService");
    
    private EventTypeService eventTypeService = SpringContextUtil.getBean(EventTypeService.class);
    
    private AlarmTypeService alarmTypeService = SpringContextUtil.getBean(AlarmTypeService.class);
    
    private AlarmService alarmService = SpringContextUtil.getBean(AlarmService.class);
    
    
    private PeopleService peopleService = SpringContextUtil.getBean("peopleService");
	
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("短信组开始");
		List<String> tels=new ArrayList();
		Alarm alarm = (Alarm)midObj;
		if(!alarm.getSmsGroup().equals("")){
			String [] smsGroups=alarm.getSmsGroup().split(" ");
			for (int i = 0; i < smsGroups.length; i++) {
				Decision d=decisionService.get(Long.valueOf(smsGroups[i]));
				List<People>peoples=peopleService.findPeopleListByDecisionId(smsGroups[i]);
				for (People p : peoples) {
					tels.add(p.getIphone());
				}
			}
			try {
				SendSMsgServiceService sendSMsgServiceService=new SendSMsgServiceService();
				SendSMsgService port=sendSMsgServiceService.getSendSMsgServicePort();
				int result= port.sendMsg(tels, channelInstance.getContent());
				if(result==0){
					channelInstance.setReleaseState("4");
					channelInstance.setFeedbackMessage("短信发送成功");
				}else{
					channelInstance.setReleaseState("3");
					channelInstance.setFeedbackMessage("短信发送失败");
				}
			} catch (ServiceException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("短信组结束");
    }

	
	private void createGTxmlFile(GTAlarmDomain alarm) throws IOException{
		String filePath="D:/gtxml/"+alarm.getFileName();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath),false), "UTF-8"));
	    bw.write(alarm.getAlarmContent());
		bw.close();
		String zipPath="D:/gtzip/"+alarm.getFileName().replace(".xml", ".zip");
		ZipUtil.zipSingleFile(filePath, zipPath);
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
    
    
    public static void main(String[] args) throws Exception {
    	String aaa="33100341600000_20190530212300_11B01_BLUE_Alert.xml";
    	String zipPath="D:/gtzip/"+aaa.replace(".xml", ".zip");
    	String filePath="D:/gtxml/"+aaa;
		ZipUtil.zipSingleFile(filePath, zipPath);
		
		FtpHelper ftpHelper = new FtpHelper();
		ftpHelper.upload(zipPath,"gtzip");
	}
    
    
//    ftpUsername=ftp_33010041600000
//    		ftpPassword=1qaz@WSX201801
//    		ftpPort=21
//    		ftpAddr=127.0.0.1

}
