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
import cn.movinginfo.tztf.releasechannel.support.PublishResultThread;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.domain.TVFax;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sev.service.TVFaxService;
import cn.movinginfo.tztf.sys.domain.Decision;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.DecisionService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.ReleaseChannelService;
import cn.movinginfo.tztf.webserviceClient.fax.SendFaxService;
import cn.movinginfo.tztf.webserviceClient.fax.SendFaxServiceService;
import cn.movinginfo.tztf.webserviceClient.faxold.SoapFaxClient;
import net.ryian.core.utils.SpringContextUtil;

/**
 * 电视媒体渠道接口
 * @author zhangd
 */
public class TVChannel implements Channel {

	private static final Logger log = Logger.getLogger(TVChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
//    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
    private DecisionService decisionService = SpringContextUtil.getBean(DecisionService.class);
    private ServletContext servletContext = SpringContextUtil.getBean(ServletContext.class);
    private ReleaseChannelService releaseChannelService= SpringContextUtil.getBean(ReleaseChannelService.class);
    private AlarmTypeService alarmTypeService = SpringContextUtil.getBean(AlarmTypeService.class);
    private DeptService departmentService = SpringContextUtil.getBean(DeptService.class);
	private TVFaxService tvFaxService = SpringContextUtil.getBean(TVFaxService.class);
	
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("电视媒体开始");
		Alarm alarm = (Alarm)midObj;
		Date pubDate = alarm.getPubDate();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat sdf2 =   new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String downFileName = sdf1.format(new Date())+"TV";// 替换后的文件名
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
//			rls.add(FileGernerator.getReplaceData("信号名称",alarm.getAlarmTypeName()));
			rls.add(FileGernerator.getReplaceData("预警内容", channelInstance.getContent()));
			rls.add(FileGernerator.getReplaceData("制作人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("发送时间",pdate));
			rls.add(FileGernerator.getReplaceData("部门",departmentService.get(alarm.getDeptId()).getName()));
			rls.add(FileGernerator.getReplaceData("流水号",alarm.getPubNo().substring(alarm.getPubNo().length()-8, alarm.getPubNo().length()-4)+alarm.getPubNo()
					.substring(alarm.getPubNo().length()-2,alarm.getPubNo().length())));

			String fileNamePath="";// 模板文件名
			String image="";
				String alarmTypeId = alarm.getTypeId();			
				rls.add(FileGernerator.getReplaceData("签发人",alarm.getIssuer()));
				if(alarm.getType().equals("alarm")){
					fileNamePath= "预警对外模板.doc";
					image="D:\\hytfsource\\images\\warning_icon\\"+alarm.getAlarmTypeName()+".jpg";
					String defense =alarmTypeService.get(Long.valueOf(alarmTypeId)).getDefence();
					rls.add(FileGernerator.getReplaceData("防御指南",defense));
				}else{
					fileNamePath= "部门事件传真模板.doc";
					image="";
					rls.add(FileGernerator.getReplaceData("类型", alarm.getAlarmTypeName()));
				}
			
			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			System.out.println(newPath);
			FileGernerator.createFiledir(newPath);
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,null,null);
			//FileGernerator.creatFile2(alarm, dept, downFileName);
			FileGernerator.getTifByDoc(newPath + downFileName);
			String path = newPath + downFileName + ".pdf";
			Long channelId = channelInstance.getChannelId();
			String fax = "";
			//获取传真号码
			List<TVFax> tvFaxList=tvFaxService.getAll();
			for(int i=0;i<tvFaxList.size();i++)
			{
				fax+=tvFaxList.get(i).getFaxNum()+",";
			}
			if(fax.endsWith(","))
			{
				fax=fax.substring(0,fax.length()-1);
			}
			
			if(!"".equals(fax)){
				byte[] img = img2byte(path);
				SoapFaxClient s = new SoapFaxClient();
				List<Object>list = new ArrayList<Object>();
				list.add(downFileName + ".pdf");
				
				String sendnumber = SystemProperties.getProperty("sendnumber");
				String useraccount = SystemProperties.getProperty("useraccount");
				String bnetaccount = SystemProperties.getProperty("bnetaccount");
				String password = SystemProperties.getProperty("password");
				String areaid = SystemProperties.getProperty("areaid");
				
				SendFaxServiceService createAlarmFileService=new SendFaxServiceService();
				SendFaxService port=createAlarmFileService.getSendFaxServicePort();
				String result = port.upload(img, null, null, 
						null, null, null, null,
						null, null, null, list, sendnumber,
						"预警文件", useraccount, bnetaccount, password, fax,
						areaid, "0");
				org.json.JSONObject jo =  XML.toJSONObject(result);
				jo =   jo.getJSONObject("root");
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
    	System.out.println("hysqxj20190007".substring("hysqxj20190007".length()-8, "hysqxj20190007".length()-4)+"hysqxj20190007".substring("hysqxj20190007".length()-2, "hysqxj20190007".length()));

	}

}
