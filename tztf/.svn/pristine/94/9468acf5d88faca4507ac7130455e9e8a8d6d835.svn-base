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
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import net.ryian.core.utils.SpringContextUtil;

/**
 * 电视媒体渠道接口
 * @author zhangd
 */
public class WebChannel implements Channel {

	private static final Logger log = Logger.getLogger(WebChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
	
	
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("电视媒体开始");
//    	Boolean flag = null;
//    	Long msgIndex = null;//短信内容主键MsgIndex
////    	smsToYd(content,midObj);
//    	smsToLt(content,midObj);
//		log.info(channelInstance.getChannel().getName() + ">>发布渠道");
		System.out.println("电视媒体结束");
    }

    private void smsToLt(String content, MiddleObject midObj) {
    	
    	 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    	 SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
    	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date pubTime=midObj.getPubDate();

		Map<String, Object> data = new HashMap<String, Object>();
    	
    	data.put("startTime",format2.format(pubTime));
    	data.put("startDate",format1.format(pubTime));
    	if(midObj.getPoints()==null||"".equals(midObj.getPoints())){
    		String points="";
    		data.put("area",points);
    	}else{
    		String points=midObj.getPoints();
    		data.put("area", "[{\"lng\":120.1138,\"lat\":30.335},{\"lng\":120.1644,\"lat\":30.26},{\"lng\":120.0784,\"lat\":30.1688},{\"lng\":120.1138,\"lat\":30.335}]");
    	}
    	Calendar time = Calendar.getInstance();
    
    	time.setTime(pubTime);
    	time.add(Calendar.HOUR, Integer.valueOf(midObj.getDuration()));
		data.put("content", content);
		
		data.put("endDate", format1.format(time.getTime()));
		data.put("endTime",format2.format(time.getTime()));
    	data.put("title",formatter.format(pubTime)+midObj.getAlarmTypeName());
		data.put("receivers","18758255310");
		PublishUtils.cuccPublish(data);
	}

	private void smsToYd(String content, MiddleObject midObj) {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
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
		PublishUtils.cmccPublish(data);
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
