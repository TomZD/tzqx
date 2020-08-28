package cn.movinginfo.tztf.releasechannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import cn.movinginfo.tztf.common.utils.PublishUtils;
import cn.movinginfo.tztf.common.utils.TokenUtil;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.DingDingGroup;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.DingDingGroupService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.webserviceClient.dingding.DingDingService;
import cn.movinginfo.tztf.webserviceClient.dingding.DingDingServiceService;
import cn.movinginfo.tztf.webserviceClient.fax.SendFaxService;
import cn.movinginfo.tztf.webserviceClient.fax.SendFaxServiceService;
import net.ryian.core.utils.SpringContextUtil;

/**
 * 钉钉渠道接口
 * @author zhangd
 */
public class DingChannel implements Channel {

	private static final Logger log = Logger.getLogger(DingChannel.class);
	;
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
    private UserService userService= SpringContextUtil.getBean(UserService.class);
	private DeptService departmentService = SpringContextUtil.getBean(DeptService.class);
	private DingDingGroupService dingdingGroupService = SpringContextUtil.getBean(DingDingGroupService.class);
	
	
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("钉钉开始------------------");
//    	Boolean flag = null;
//    	Long msgIndex = null;//短信内容主键MsgIndex
////    	smsToYd(content,midObj);
//    	smsToLt(content,midObj);
//		log.info(channelInstance.getChannel().getName() + ">>发布渠道");
		Alarm alarm = (Alarm)midObj;
		//发布内容
		String contents = alarm.getContent();
		//钉钉群组列表
		List<DingDingGroup> dingdingGroupList=dingdingGroupService.getAllGroup();
		DingDingServiceService dingdingService = new DingDingServiceService();
		DingDingService port = dingdingService.getDingDingServicePort();
		//先获取token值
		String token=port.getToken();
		String chatIds="";
		String msgIds="";
		for(DingDingGroup group:dingdingGroupList)
		{
			String msgId=port.sendMsg(token, group.getChatID(), contents);
			chatIds+=group.getChatID()+",";
			msgIds+=msgId+",";
		}
		if(chatIds.endsWith(","))
		{
			chatIds=chatIds.substring(0,chatIds.length()-1);
		}
		if(msgIds.endsWith(","))
		{
			msgIds=msgIds.substring(0,msgIds.length()-1);
		}
		//若返回的消息ids长度大于0，则表示发送成功
		if (msgIds.length()>0) {
			channelInstance.setReleaseState("4");
			channelInstance.setFeedbackMessage("发送成功");
		} else {
			channelInstance.setReleaseState("3");
			channelInstance.setFeedbackMessage("发送失败");
		}
		channelInstance.setData(chatIds);
		channelInstance.setResultId(msgIds);
		//获取所有群组
		//channelInstance.setData(data);
		//TokenUtil.sendContent(token, contents);
		System.out.println("钉钉结束");
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
    
    private String getYDArea(String points){
    	
    	String result = "120.126063,30.287696;120.128439,30.287724;120.12613,30.286768;120.12852,30.286858"; 
//    	DecimalFormat df = new DecimalFormat("#.0000");
//		String test=points;
//		String [] str=test.split(";");
//		String result="(";
//		for(int i=0;i<str.length;i++){
//			result+="(";
//			String [] str1=str[i].split(",");
//			for(int j=0;j<str1.length;j++){
//				result+=df.format(Double.valueOf(str1[j])/100000);
//				if(j<str1.length-1){
//					result+=",";
//				}
//			}
//			result+=")";
//			if(i<str.length-1){
//				result+=",";
//			}
//		}
//		result+=")";
		return result;
    	
    }
private JSONArray getLTArea(String points){
	DecimalFormat df = new DecimalFormat("#.0000");
	String test=points;
	JSONArray data=new JSONArray();
	
	String [] str=test.split(";");

	for(int i=0;i<str.length;i++){
		JSONObject ob=new JSONObject();
		

		String [] str1=str[i].split(",");
		for(int j=0;j<str1.length;j++){
			
			if(j<str1.length-1){
				ob.put("lng", df.format(Double.valueOf(str1[j])/100000));
				
			}else{
				ob.put("lat", df.format(Double.valueOf(str1[j])/100000));
			}
		}
		
		data.add(ob);
	}
	return data;
    	
    }
    
    public static void main(String[] args) {
//		System.out.print(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
    	double num1 = 7.15;
    	double num2 = 10.0;
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String result = numberFormat.format((num1 / num2 * 100));
		System.out.println("num1和num2的百分比为:" + result + "%");
		System.out.println("num1和num2的百分比为:" + decimalFormat.format(Double.valueOf(result)) + "%");
	}

}
