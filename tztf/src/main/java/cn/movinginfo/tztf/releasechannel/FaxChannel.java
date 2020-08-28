package cn.movinginfo.tztf.releasechannel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.common.utils.FileGernerator;
import cn.movinginfo.tztf.common.utils.HttpConfigUtil;
import cn.movinginfo.tztf.common.utils.PublishUtils;
import cn.movinginfo.tztf.common.utils.ReplaceData;
import cn.movinginfo.tztf.releasechannel.support.PublishResultThread;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sys.domain.Decision;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.DecisionService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.ReleaseChannelService;
import cn.movinginfo.tztf.sys.service.UserService;
import cn.movinginfo.tztf.webserviceClient.faxold.SoapFaxClient;
import net.ryian.core.utils.SpringContextUtil;
import net.sf.json.xml.XMLSerializer;
/**
 * 传真发布渠道接口
 * @author zhangd
 */
public class FaxChannel implements Channel {
	
	@Autowired
	private DeptService deptService;
	
	
	

	private static final Logger log = Logger.getLogger(FaxChannel.class);
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
    private UserService userService = SpringContextUtil.getBean(UserService.class);
    private AlarmTypeService alarmTypeService = SpringContextUtil.getBean(AlarmTypeService.class);
    private EventTypeService eventTypeService = SpringContextUtil.getBean(EventTypeService.class);
    private DeptService departmentService = SpringContextUtil.getBean(DeptService.class);
    private ServletContext servletContext = SpringContextUtil.getBean(ServletContext.class);
    private ReleaseChannelService releaseChannelService= SpringContextUtil.getBean(ReleaseChannelService.class);
    private DecisionService decisionService = SpringContextUtil.getBean(DecisionService.class);
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("进入传真方法----------------------------");
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
		
		List<ReleaseChannel> allReleaseChannel =  releaseChannelService.getByValid();
		
		Collections.sort(allReleaseChannel, new Comparator<ReleaseChannel>() {

			@Override
			public int compare(ReleaseChannel o1, ReleaseChannel o2) {
				if(o1.getSortNumber()<o2.getSortNumber()){
					return -1;
				}
				return 0;
			}
		});
		for(ReleaseChannel r:allReleaseChannel){
			
			if(!alarm.getPubChannel().contains(r.getNameEn())){
				r.setValid(0);
			}
			
		}
		List<Decision> decisions = decisionService.getAllDecision();
		
		for(Decision d:decisions){
			if(!alarm.getSmsGroup().contains(d.getName())){
				d.setValid(0);
			}
			
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
			rls.add(FileGernerator.getReplaceData("信号名称",alarm.getAlarmTypeName()));
			rls.add(FileGernerator.getReplaceData("预警信息", alarm.getContent()));
			rls.add(FileGernerator.getReplaceData("发布人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("预警时间",pdate));
			rls.add(FileGernerator.getReplaceData("编号",alarm.getPubNo()));

			String fileNamePath="";// 模板文件名
			String image="";
				String alarmTypeId = alarm.getTypeId();
				String defense =alarmTypeService.get(Long.valueOf(alarmTypeId)).getDefence();
				rls.add(FileGernerator.getReplaceData("审稿人",alarm.getIssuer()));
				if(alarm.getAlarmTypeName().equals("alarm")){
					fileNamePath= "通州区气象灾害预警信号模板.doc";
					image="D:\\tztfsource\\images\\warning_icon\\"+alarm.getAlarmTypeName()+".jpg";
					rls.add(FileGernerator.getReplaceData("防御指南",defense));
				}else{
					fileNamePath= "通州区突发事件预警信号模板.doc";
					image="";
				}
			
			
			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			System.out.println(newPath);
			FileGernerator.createFiledir(newPath);
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,allReleaseChannel,decisions);
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
				String destnumber = SystemProperties.getProperty("destnumber");	
				String result = s.upload(img, null, null, 
						null, null, null, null,
						null, null, null, list, sendnumber,
						"测试文件", useraccount, bnetaccount, password, destnumber,
						areaid, "0");
				org.json.JSONObject jo =  XML.toJSONObject(result);
				jo =   jo.getJSONObject("root");
				String resultId =  jo.getString("faxid");
				this.channelInstance.setResultId(resultId);
				PublishResultThread publishResultThread = new PublishResultThread(releaseChannelInstanceService,channelInstance);
				PublishUtils.getPublishResult(publishResultThread, 60l, 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}
		System.out.println("发送传真结束-------------------------");
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
    
  
    public void getV(Alarm alarm){
    	Date pubDate = alarm.getPubDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat sdf2 =   new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String downFileName = sdf1.format(new Date());// 替换后的文件名
		try {
		Date pubDate1 = alarm.getPubDate();
/*		//	String pdate = request.getParameter("pubDate");
			Date date = sdf2.parse(pdate);
			int duration =0;
			if(request.getParameter("duration").equals("")){
				
			}else{
				duration = Integer.parseInt(request.getParameter("duration"));
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, duration);
			Date enddate = c.getTime();
			String edate=sdf2.format(enddate);
			String pedate=pdate+"  至  "+edate;
			
			String pubRange=request.getParameter("pubRange");
			
			List<ReplaceData> rls = new ArrayList<ReplaceData>();
			rls.add(FileGernerator.getReplaceData("信号名称",alarm.getAlarmTypeName()));
			rls.add(FileGernerator.getReplaceData("预警信息", alarm.getContent()));
			rls.add(FileGernerator.getReplaceData("发布人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("起止时间",pedate));
			rls.add(FileGernerator.getReplaceData("状态","发布"));
			rls.add(FileGernerator.getReplaceData("发布范围",pubRange));
			Depart dept = departmentService.get(alarm.getDeptId());
			rls.add(FileGernerator.getReplaceData("电话号码", dept.getPhone()));
			rls.add(FileGernerator.getReplaceData("传真", dept.getFax()));
			rls.add(FileGernerator.getReplaceData("部门名称", dept.getName()));
			String fileNamePath="";// 模板文件名
			if(alarm.getType().equals("alarm")){
				String alarmTypeId = alarm.getTypeId();
				String defense =alarmTypeService.get(Long.valueOf(alarmTypeId)).getDefence();
				rls.add(FileGernerator.getReplaceData("审稿人",alarm.getIssuer()));
				rls.add(FileGernerator.getReplaceData("防御指南",defense));
				fileNamePath= "杭州市突发事件预警信息发布.doc";
			}else{
				rls.add(FileGernerator.getReplaceData("审稿人",""));
				//fileNamePath= "事件信号U.doc";
				fileNamePath= "杭州市突发事件预警信息发布.doc";
			}
			String image="";
			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			FileGernerator.createFiledir(newPath);
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image);*/
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}
    	
    }
    public static String fileToBase64(String path) {
        String base64 = null;
        InputStream in = null;
        try {
            File file = new File(path);
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            base64 = Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }
    
    public static String encryptToBase64(String filePath) {
    	if (filePath == null) {
    		return null;
    		}
    	try {
    		byte[] b = Files.readAllBytes(Paths.get(filePath));
    		return Base64.encodeBase64String(b);
    		} catch (IOException e) {
    			e.printStackTrace();
    			} 
    	return null;
    	}
    
    
    public static byte[] img2byte(String path){
	    byte[] data = null;
	    FileImageInputStream input = null;
	    try {
//	      String path = "D://20181105172111.doc";
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
}
