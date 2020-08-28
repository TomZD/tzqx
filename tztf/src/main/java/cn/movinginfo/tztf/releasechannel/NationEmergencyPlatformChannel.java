package cn.movinginfo.tztf.releasechannel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.xml.rpc.ServiceException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.common.enums.OperationAction;
import cn.movinginfo.tztf.common.utils.AreaConfigUtil;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.common.utils.GTAlarmDomain;
import cn.movinginfo.tztf.common.utils.MsgConfigUtil;
import cn.movinginfo.tztf.common.utils.PublishUtils;
import cn.movinginfo.tztf.mq.TztfProducter;
import cn.movinginfo.tztf.releasechannel.support.FtpHelper;
import cn.movinginfo.tztf.releasechannel.support.NationalEmergencySupport;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sm.domain.EventType;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sys.domain.AlarmType;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.DecisionService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.PeopleService;
import net.ryian.core.utils.SpringContextUtil;
import net.sf.json.JSONObject;

/**
 * 国突平台发布渠道接口
 * @author zq-jyp
 *
 */
public class NationEmergencyPlatformChannel implements Channel {
    
    
    private static final Logger log = Logger.getLogger(NationEmergencyPlatformChannel.class);
    
    private ReleaseChannelInstance channelInstance;
    
private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
    
    private DecisionService decisionService = SpringContextUtil.getBean("decisionService");
    
    private DeptService deptService = SpringContextUtil.getBean("deptService");
    
    private EventTypeService eventTypeService = SpringContextUtil.getBean(EventTypeService.class);
    
    private AlarmTypeService alarmTypeService = SpringContextUtil.getBean(AlarmTypeService.class);
    
    private AlarmService alarmService = SpringContextUtil.getBean(AlarmService.class);
    
    
    private PeopleService peopleService = SpringContextUtil.getBean("peopleService");
    
    @Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
    	
    	Alarm alarm = (Alarm)midObj;
		
    	Long id = channelInstance.getId();
		GTAlarmDomain gtalarm=createGTxml(alarm,String.valueOf(id));
		TztfProducter hp=new TztfProducter();
		try {
			
			int result=hp.sendGTAlarm(gtalarm);
			if(result==0){
				channelInstance.setReleaseState("2");
				channelInstance.setFeedbackMessage("国突渠道已推送");
			}else{
				channelInstance.setReleaseState("3");
				channelInstance.setFeedbackMessage("国突渠道推送失败");
			}
		} catch (RemoteException | JMSException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }

    @Override
    public void doReleaseFile(String filePath, MiddleObject midObj) throws ChannelReleaseException {
        
    }

    @Override
    public void doRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        
    }

    @Override
    public void doDefaultRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        
    }

    @Override
    public void setChannelInstance(ReleaseChannelInstance channelInstance) {
        this.channelInstance = channelInstance;
    }
    
    private GTAlarmDomain createGTxml(Alarm alarm,String num) {
		GTAlarmDomain gtalarm=new GTAlarmDomain();
		Depart dept=deptService.findById(alarm.getDeptId());
		String note="";
		
		if(alarm.getVersion()==1){
    		note=dept.getName()+DateUtil.format(alarm.getPubDate(), "yyyy年MM月dd日 HH时mm分")+"解除"+alarm.getAlarmTypeName()+"预警信号";
    	}
		
		
		StringBuilder builder = new StringBuilder();
    	builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    	builder.append("\r\n");
    	
    	builder.append("<alert>");
    	builder.append("\r\n");
    	
    	builder.append("\t<identifier>" + alarm.getMsgId() + "</identifier>");
    	builder.append("\r\n");
    	
    	
    	builder.append("\t<sender>" + dept.getName().replace("气象台", "气象局") + "</sender>");
//    	builder.append("\t<sender>通州区气象局</sender>");
    	builder.append("\r\n");
    	
    	builder.append("\t<senderCode>" + dept.getNationalUnitCode() + "</senderCode>");
    	builder.append("\r\n");
    	
    	builder.append("\t<sendTime>" + DateUtil.format(DateUtil.parse(alarm.getMsgId().split("_")[1], "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:00") + "+08:00</sendTime>");
    	builder.append("\r\n");
    	
    	if(alarm.getAlarmType() == 0) {
    		builder.append("\t<status>Test</status>");//测试预警
    	}else {
        	builder.append("\t<status>Actual</status>");//正式预警
    	}
    	
    	builder.append("\r\n");
    	
    	String msgType="";
    	if(alarm.getVersion()==2){
    		msgType="Update";
    	}else if(alarm.getVersion()==1){
    		msgType="Cancel";
    	}else if(alarm.getVersion()==0){
    		msgType="Alert";
    	}
    	builder.append("\t<msgType>" + msgType + "</msgType>");
    	builder.append("\r\n");
    	
    	builder.append("\t<scope>Public</scope>");
    	builder.append("\r\n");
    	
    	
//    	if(dept.getName().contains("气象")){
    		builder.append("\t<code>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t<method>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t\t<methodName>LED</methodName>");
        	builder.append("\r\n");
        	
        	if(alarm.getVersion()==1){
        		builder.append("\t\t\t<message>" + note + "</message>");
        	}else{
        		builder.append("\t\t\t<message>" + alarm.getContent() + "</message>");
        	}
        	builder.append("\r\n");
        	if(alarm.getAlarmType() == 0) {
        	builder.append("\t\t\t<audienceGrp></audienceGrp>");
        	}else {
        	builder.append("\t\t\t<audienceGrp>"+SystemProperties.getProperty("ledGCode")+"</audienceGrp>");	
        	}
        	builder.append("\r\n");
        	
        	builder.append("\t\t\t<audenceprt>,</audenceprt>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t</method>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t<method>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t\t<methodName>SMS</methodName>");
        	builder.append("\r\n");
        	
        	if(alarm.getVersion()==1){
        		builder.append("\t\t\t<message>" + note + "</message>");
        	}else{
        		builder.append("\t\t\t<message>" + alarm.getContent() + "</message>");
        	}
        	builder.append("\r\n");
        	if(alarm.getAlarmType() == 0) {
        	builder.append("\t\t\t<audienceGrp></audienceGrp>");
        	}else {
            builder.append("\t\t\t<audienceGrp>"+SystemProperties.getProperty("smsGCode")+"</audienceGrp>");
        	}
        	builder.append("\r\n");
        	
        	if(alarm.getAlarmType() == 0) {
        	builder.append("\t\t\t<audenceprt>13736220960,13758831020</audenceprt>");
        	}else {
        	builder.append("\t\t\t<audenceprt>,</audenceprt>");
        	}
        	builder.append("\r\n");
        	
        	builder.append("\t\t</method>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t<method>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t\t<methodName>WEB</methodName>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t\t<message/>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t\t<audienceGrp/>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t\t<audenceprt>,</audenceprt>");
        	builder.append("\r\n");
        	
        	builder.append("\t\t</method>");
        	builder.append("\r\n");
//    	}
    	
    	
    	builder.append("\t</code>");
    	builder.append("\r\n");
    	
    	builder.append("\t<secClassification>None</secClassification>");
    	builder.append("\r\n");
   
    	if(alarm.getVersion()==1){
//    		note=dept.getName()+DateUtil.format(alarm.getPubDate(), "yyyy年MM月dd日 HH时mm分")+"解除"+alarm.getAlarmTypeName()+"预警信号";
    		builder.append("\t<note>" + note + "</note>");
    	}else{
    		builder.append("\t<note/>");
    	}
    	builder.append("\r\n");
    	
    	 if (alarm.getVersion()==0)//如果预警类型是首发预警是references为空
         {
             builder.append("\t<references/>");
         }else{
        	 String references=alarmService.getFirstAlarmByPubNo(alarm.getPubNo()).getMsgId();
        	 builder.append("\t<references>" + references + "</references>");
         }
    	builder.append("\r\n");
    	builder.append("\t<info>");
    	builder.append("\r\n");
    	
    	builder.append("\t\t<language>zh-CN</language>");
    	builder.append("\r\n");
    	
    	String defence = "";
        String color = "";
    	 String nationalCode = "";
         if(alarm.getType().equals("alarm")){
         	AlarmType alarmType = alarmTypeService.get(Long.valueOf(alarm.getTypeId()));
         	defence = alarmType.getDefence();
         	color = alarmType.getColor();
         	nationalCode = alarmType.getNationalCode();
         }else{
         	EventType eventType = eventTypeService.get(Long.valueOf(alarm.getTypeId()));
         	String alarmTypeName = alarm.getAlarmTypeName();
         	if(alarmTypeName.contains("蓝色")) {
         		color = "Blue";
         	}else if(alarmTypeName.contains("黄色")) {
         		color = "Yellow";
         	}else if(alarmTypeName.contains("橙色")) {
         		color = "Orange";
         	}else if(alarmTypeName.contains("红色")) {
         		color = "Red";
         	}
         	nationalCode = eventType.getNationalCode();
         }
        builder.append("\t\t<eventType>" + nationalCode + "</eventType>");
    	builder.append("\r\n");
    	
    	builder.append("\t\t<urgency>Unknown</urgency>");
    	builder.append("\r\n");
    	
    	builder.append("\t\t<severity>" + color + "</severity>");
    	builder.append("\r\n");
    	
    	builder.append("\t\t<certainty>Unknown</certainty>");
    	builder.append("\r\n");
    	
    	builder.append("\t\t<effective>" +DateUtil.format(DateUtil.parse(alarm.getMsgId().split("_")[1], "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:00")+ "+08:00</effective>");
    	builder.append("\r\n");
    	
    	builder.append("\t\t<onset/>");
    	builder.append("\r\n");
    	
    	builder.append("\t\t<expires/>");//待定
    	builder.append("\r\n");
    	
    	
    	builder.append("\t\t<senderName>" + dept.getName().replace("气象台", "气象局") + "</senderName>"); //是否与sender一样
//    	builder.append("\t\t<senderName>通州区气象局</senderName>");
    	builder.append("\r\n");
    	
    	String headline="";
    	
    	headline+=dept.getName();
    	if(alarm.getVersion()==1){
    		headline+="解除";
    	}else{
    		headline+="发布";
    	}
    	
    	String level = null;
    	String typeName=alarm.getAlarmTypeName();
        if(typeName.contains("蓝")) {
            level = "[IV级/一般]";
        } else if (typeName.contains("黄")) {
            level = "[III级/较重]";
        } else if (typeName.contains("橙")) {
            level = "[II级/严重]";
        } else if (typeName.contains("红")) {
            level = "[I级/特别重大]";
        } else {
            level = "[IV级/一般]";
        }
        headline+=typeName+"预警"+level;
        builder.append("\t\t<headline>" +headline.replace("气象台", "气象局")+ "</headline>");
    	builder.append("\r\n");
    	
    	if(alarm.getVersion()==1){
    		builder.append("\t\t<description>" + note + "</description>");
    	}else{
    		builder.append("\t\t<description>" + alarm.getContent() + "</description>");
    	}
    	builder.append("\r\n");
    	
    	builder.append("\t\t<instruction/>");
    	builder.append("\r\n");
    	
    	builder.append("\t\t<web/>");
    	builder.append("\r\n");
                      
        builder.append("\t\t<area>");
        builder.append("\r\n");
        
        String areaDesc="通州区,通州区";
        String geocode="331003000000,331003000000";
        String [] towns=alarm.getPubRange().split(",");
        for (int i = 0; i < towns.length; i++) {
        	String areaContent=AreaConfigUtil.getValue(towns[i]);
        	areaDesc+=","+areaContent.split(";")[0];
        	geocode+=","+areaContent.split(";")[1];
        
		}
        
        builder.append("\t\t\t<areaDesc>" + areaDesc + "</areaDesc>"); //待定
        builder.append("\r\n");
        
        builder.append("\t\t\t<polygon/>");
        builder.append("\r\n");
        
        builder.append("\t\t\t<circle/>");
        builder.append("\r\n");
         //TODO
        builder.append("\t\t\t<geocode>" + geocode + "</geocode>");//待定
        builder.append("\r\n");
        
        builder.append("\t\t</area>");
        builder.append("\r\n");
        
        builder.append("\t</info>");
        builder.append("\r\n");
        
        builder.append("</alert>");
        
        String fileName = alarm.getMsgId() + "_" + nationalCode + "_" + color.toUpperCase() + "_" + msgType + ".xml";
        gtalarm.setAlarmContent(builder.toString());
        gtalarm.setFileName(fileName);
        gtalarm.setPubNo(num);
        
		return gtalarm;
	}
    
    

}
