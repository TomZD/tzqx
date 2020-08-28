package cn.movinginfo.tztf.releasechannel;

import java.rmi.RemoteException;
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
import java.util.UUID;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.xml.rpc.ServiceException;

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

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.common.utils.MsgDomain;
import cn.movinginfo.tztf.mq.TztfProducter;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.webserviceClient.sms.SendSMsgService;
import cn.movinginfo.tztf.webserviceClient.sms.SendSMsgServiceService;
import cn.movinginfo.tztf.webserviceClient.sms.ServiceException_Exception;
import net.ryian.core.utils.SpringContextUtil;

/**
 * 短信全网发布渠道接口
 * @author zhangd
 */
public class SmsAllChannel implements Channel {

	private static final Logger log = Logger.getLogger(SmsAllChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
	
//	// 短信用户，数据库链接
//    protected JdbcTemplate smsAllUserJdbcTemplate = SpringContextUtil.getBean("smsAllUserJdbcTemplate");
//	
//	// 短信内容，数据库链接
//	protected JdbcTemplate smsAllMessageJdbcTemplate = SpringContextUtil.getBean("smsAllMessageJdbcTemplate");
	
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	/**
	 * 获取预警短信用户，号码列表<p>
	 * @return
	 * @author: zhangd
	 * @createTime: 2017年8月31日 下午4:14:45
	 * @updateTime: 
	 */
	public List<String> getPhoneNoList() {
		String sql = "select * from RF_SMSUser58457 where WarningSwitch=1";
		List<String> list = new ArrayList<String>();
//		list = smsAllUserJdbcTemplate.query(sql, new RowMapper<String>() {
//			@Override
//			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//				String phoneNo = rs.getString("Number");
//				return phoneNo;
//			}
//		});
		return list;
	}
	
	/**
	 * 发送短信，向短信内容表插入一条记录，并返回生成的主键MsgIndex<p>
	 * @return 短信内容主键MsgIndex
	 * @author: zhangd
	 * @createTime: 2017年8月31日 下午4:24:16
	 * @updateTime: 
	 */
	public Long sendSmsMessage(String phoneNo, String msg, int i) {
		String sql = "INSERT INTO USERWakeMessage (intime, MobileNo, Msg, Pri) VALUES (''{0}'',''{1}'',''{2}'',''{3}'')";
		String intime = sdf.format(new Date());
		Object[] o = {intime, phoneNo, msg, (i+1)};
		final String isql = MessageFormat.format(sql, o);
		
		// 执行插入语句后，获取自动生成的主键
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Long autoIncId = 0L;
//		smsAllMessageJdbcTemplate.update(new PreparedStatementCreator() {
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement ps = con.prepareStatement(isql, PreparedStatement.RETURN_GENERATED_KEYS);
//				return ps;
//			}
//		}, keyHolder);
//		autoIncId = keyHolder.getKey().longValue();
		
		return autoIncId;
	}
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("开始发送全网短信---------------------------");
		List<String> list = new ArrayList<String>();
		list.add(SystemProperties.getProperty("smsAll"));
		SendSMsgServiceService sendSMsgServiceService=new SendSMsgServiceService();
		SendSMsgService port=sendSMsgServiceService.getSendSMsgServicePort();
		try {
			int result= port.sendMsg(list, channelInstance.getContent());
		} catch (ServiceException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("全网短信结束----------------------------");
    }

    private void smsToLt(String content, MiddleObject midObj) {
    	
    	 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	 SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
    	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date pubTime=midObj.getPubDate();
		String lturl="http://10.135.134.15:8080/gi-publish/sms/cuccPublish";

		Map<String, Object> data = new HashMap<String, Object>();
    	
    	data.put("startTime",format2.format(pubTime));
    	data.put("startDate",format1.format(pubTime));
    	if(midObj.getPoints()==null||"".equals(midObj.getPoints())){
    		String points="";
    		data.put("area",points);
    	}else{
    		String points=midObj.getPoints();
    		data.put("area",getLTArea(points).toJSONString());
    	}
    	Calendar time = Calendar.getInstance();
    
    	time.setTime(pubTime);
    	time.add(Calendar.HOUR, Integer.valueOf(midObj.getDuration()));
		data.put("content", content);
		
		data.put("endDate", format1.format(time.getTime()));
		data.put("endTime",format2.format(time.getTime()));
    	data.put("title",formatter.format(pubTime)+midObj.getAlarmTypeName());
		data.put("receivers","18758255310");
		
		
		
		
		HttpResponse<String> res;
		try {
			res = Unirest.post(lturl)
					.header("accept", "application/json")
			        .header("Content-Type", "application/json")
					.header("witsky-appkey","f23e2ce4877540f6")
					.header("witsky-signature","20181127")
					.body(JSON.toJSONString(data)).asString();
			String result = res.getBody();
			System.out.println(result);
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void smsToYd(String content, MiddleObject midObj) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String ydurl="http://10.135.134.15:8080/gi-publish/sms/cmccPublish";
    	
    	Map<String, Object> data = new HashMap<String, Object>();
    	
    	Date pubTime=midObj.getPubDate();
    	Calendar time = Calendar.getInstance();
        
    	time.setTime(pubTime);
    	time.add(Calendar.HOUR, Integer.valueOf(midObj.getDuration()));
    	
    	data.put("beginTime",format1.format(pubTime));
    	data.put("endTime",format1.format(time.getTime()));
		data.put("receivers","18758255310");
		data.put("content", content);
		
		if(midObj.getPoints()==null||midObj.getPoints().equals("")){
    		String points="";
    		data.put("area",points);
    	}else{
    		String points=midObj.getPoints();
    		data.put("area",getYDArea(points));
    	}
		
		
		HttpResponse<String> res;
		try {
			res = Unirest.post(ydurl)
					.header("accept", "application/json")
			        .header("Content-Type", "application/json")
					.header("witsky-appkey","f23e2ce4877540f6")
					.header("witsky-signature","20181127")
					.body(JSON.toJSONString(data)).asString();
			String result = res.getBody();
			System.out.println(result);
		} catch (UnirestException e) {
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
    
    private String getYDArea(String points){
    	
    	
    	DecimalFormat df = new DecimalFormat("#.0000");
		String test=points;
		String [] str=test.split(";");
		String result="(";
		for(int i=0;i<str.length;i++){
			result+="(";
			String [] str1=str[i].split(",");
			for(int j=0;j<str1.length;j++){
				result+=df.format(Double.valueOf(str1[j]));
				if(j<str1.length-1){
					result+=",";
				}
			}
			result+=")";
			if(i<str.length-1){
				result+=",";
			}
		}
		result+=")";
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
				ob.put("lng", df.format(Double.valueOf(str1[j])));
				
			}else{
				ob.put("lat", df.format(Double.valueOf(str1[j])));
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
