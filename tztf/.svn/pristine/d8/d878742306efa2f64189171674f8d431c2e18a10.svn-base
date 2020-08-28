package cn.movinginfo.tztf.releasechannel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.json.XML;
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
import cn.movinginfo.tztf.common.utils.FileGernerator;
import cn.movinginfo.tztf.common.utils.PublishUtils;
import cn.movinginfo.tztf.common.utils.ReplaceData;
import cn.movinginfo.tztf.releasechannel.Channel;
import cn.movinginfo.tztf.releasechannel.ChannelReleaseException;
import cn.movinginfo.tztf.releasechannel.TVStationChannel;
import cn.movinginfo.tztf.releasechannel.support.PublishResultThread;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.domain.Decision;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.DecisionService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.ReleaseChannelService;
import cn.movinginfo.tztf.webserviceClient.faxold.SoapFaxClient;
import net.ryian.core.utils.SpringContextUtil;

/**
 * 广播电台渠道接口
 * @author zhangd
 */
public class TVStationChannel implements Channel {

	private static final Logger log = Logger.getLogger(TVStationChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
    private DecisionService decisionService = SpringContextUtil.getBean(DecisionService.class);
    private ServletContext servletContext = SpringContextUtil.getBean(ServletContext.class);
    private ReleaseChannelService releaseChannelService= SpringContextUtil.getBean(ReleaseChannelService.class);
    private AlarmTypeService alarmTypeService = SpringContextUtil.getBean(AlarmTypeService.class);
    private DeptService departmentService = SpringContextUtil.getBean(DeptService.class);
	
	
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("广播电台开始");
		Alarm alarm = (Alarm)midObj;
		Date pubDate = alarm.getPubDate();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat sdf2 =   new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String downFileName = sdf1.format(new Date());// 替换后的文件名
		String imagePath = alarm.getImagePath();
		if(imagePath!=null&&!"".equals(imagePath)){
			String path = servletContext.getRealPath("/upload") + "/"+ imagePath;
		}
		try {
			String pdate = sdf2.format(pubDate);
		//	String pdate = request.getParameter("pubDate");
			Date date = sdf2.parse(pdate);
			int duration =0;
			if("".equals(alarm.getDuration())){
				
			}else{
				if(alarm.getDuration()!=null){
					duration = Integer.parseInt(alarm.getDuration());
				}
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, duration);
			Date enddate = c.getTime();
			String edate=sdf2.format(enddate);
			String pedate=pdate+"  至  "+edate;
			
			String pubRange=alarm.getPubRangeName();
			
			List<ReplaceData> rls = new ArrayList<ReplaceData>();
			rls.add(FileGernerator.getReplaceData("预警内容", channelInstance.getContent()));
			rls.add(FileGernerator.getReplaceData("制作人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("发送时间",pdate));
			rls.add(FileGernerator.getReplaceData("部门",departmentService.get(alarm.getDeptId()).getName()));
			rls.add(FileGernerator.getReplaceData("流水号",alarm.getPubNo().substring(alarm.getPubNo().length()-8, alarm.getPubNo().length()-4)+alarm.getPubNo()
					.substring(alarm.getPubNo().length()-2,alarm.getPubNo().length())));

			String fileNamePath="";// 模板文件名
			String image="";
				String alarmTypeId = alarm.getTypeId();
				String defense =alarmTypeService.get(Long.valueOf(alarmTypeId)).getDefence();
				rls.add(FileGernerator.getReplaceData("签发人",alarm.getIssuer()));
				if(alarm.getType().equals("alarm")){
					fileNamePath= "预警对外模板.doc";
					image="D:\\hytfsource\\images\\warning_icon\\"+alarm.getAlarmTypeName()+".jpg";
					rls.add(FileGernerator.getReplaceData("防御指南",defense));
				}else{
					fileNamePath= "预警对外模板.doc";
					image="";
				}
			
		 
			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			System.out.println(newPath);
			FileGernerator.createFiledir(newPath);
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,null,null);
			//FileGernerator.creatFile2(alarm, dept, downFileName);
		
			String path = newPath + downFileName + ".doc";
			Long channelId = channelInstance.getChannelId();
			Depart depart = departmentService.getPublishDept(channelId);
			String fax = "057187352314";
//			if(depart!=null){
//				fax = depart.getFax();
//			}
//			if(channelInstance.getChannelIds()!=null&&!"".equals(channelInstance.getChannelIds())){
//				String[] channelIdArr  = channelInstance.getChannelIds().split(",");
//				for(int i=0;i<channelIdArr.length;i++){
//					depart = departmentService.getPublishDept(Long.valueOf(channelIdArr[i]));
//					if(depart!=null){
//						fax += "," + depart.getFax();
//					}
//				}
//			}
			if(!"".equals(fax)){
				byte[] img = img2byte(path);
				SoapFaxClient s = new SoapFaxClient();
				List<String>list = new ArrayList<String>();
				list.add(downFileName + ".doc");
				
				String sendnumber = SystemProperties.getProperty("sendnumber");
				String useraccount = SystemProperties.getProperty("useraccount");
				String bnetaccount = SystemProperties.getProperty("bnetaccount");
				String password = SystemProperties.getProperty("password");
				String areaid = SystemProperties.getProperty("areaid");
				String destnumber = SystemProperties.getProperty("dsmtdestnumber");	
				String result = s.upload(img, null, null, 
						null, null, null, null,
						null, null, null, list, sendnumber,
						"预警文件", useraccount, bnetaccount, password, destnumber,
						areaid, "0");
				org.json.JSONObject jo =  XML.toJSONObject(result);
				jo =   jo.getJSONObject("root");
//				String resultId =  jo.getString("faxid");
//				String resultdesc =  jo.getString("resultdesc");
				int resultcode=jo.getInt("resultcode");
				if(resultcode==1000){
					channelInstance.setReleaseState("4");
					channelInstance.setFeedbackMessage(jo.getString("resultdesc"));
				}else{
					channelInstance.setReleaseState("3");
					channelInstance.setFeedbackMessage(jo.getString("resultdesc"));
				}
//				this.channelInstance.setResultId(resultId);
//				PublishResultThread publishResultThread = new PublishResultThread(releaseChannelInstanceService,channelInstance);
//				PublishUtils.getPublishResult(publishResultThread, 60l, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}
		System.out.println("广播电台结束");
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
public static byte[] img2byte(String path){
    byte[] data = null;
    FileImageInputStream input = null;
    try {
//      String path = "D://20181105172111.doc";
      input = new FileImageInputStream(new File(path));
      ByteArrayOutputStream output = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];
      int numBytesRead = 0;
      while ((numBytesRead = input.read(buf)) != -1) {
      output.write(buf, 0, numBytesRead);
      }
      data = output.toByteArray();
      output.close();
      input.close();
    }
    catch (FileNotFoundException ex1) {
    	ex1.printStackTrace();
    }
    catch (IOException ex1) {
        ex1.printStackTrace();
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
