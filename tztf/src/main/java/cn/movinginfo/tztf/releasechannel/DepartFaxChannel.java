package cn.movinginfo.tztf.releasechannel;

import net.ryian.core.utils.SpringContextUtil;
import org.apache.log4j.Logger;
import org.json.XML;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.common.utils.FileGernerator;
import cn.movinginfo.tztf.common.utils.GTAlarmDomain;
import cn.movinginfo.tztf.common.utils.ReplaceData;
import cn.movinginfo.tztf.common.utils.ZipUtil;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.DepartFax;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.DepartFaxService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.PeopleService;
import cn.movinginfo.tztf.webserviceClient.fax.SendFaxService;
import cn.movinginfo.tztf.webserviceClient.fax.SendFaxServiceService;
import cn.movinginfo.tztf.webserviceClient.faxold.SoapFaxClient;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletContext;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 部门传真发布渠道接口
 * 
 * @author zhangd
 */
public class DepartFaxChannel implements Channel {

	private static final Logger log = Logger.getLogger(DepartFaxChannel.class);

	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;

	private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil
			.getBean("releaseChannelInstanceService");

	private DepartFaxService departFaxService = SpringContextUtil.getBean(DepartFaxService.class);

	private DeptService deptService = SpringContextUtil.getBean("deptService");

	private EventTypeService eventTypeService = SpringContextUtil.getBean(EventTypeService.class);

	private AlarmTypeService alarmTypeService = SpringContextUtil.getBean(AlarmTypeService.class);

	private AlarmService alarmService = SpringContextUtil.getBean(AlarmService.class);

	private ServletContext servletContext = SpringContextUtil.getBean(ServletContext.class);
	private PeopleService peopleService = SpringContextUtil.getBean("peopleService");
	private DeptService departmentService = SpringContextUtil.getBean(DeptService.class);

	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Override
	public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("部门传真开始");
		Alarm alarm = (Alarm) midObj;

		if (!alarm.getDepartFax().equals("")) {
			String[] departFaxs = alarm.getDepartFax().split(" ");
			for (int i = 0; i < departFaxs.length; i++) {
				DepartFax d = departFaxService.get(Long.valueOf(departFaxs[i]));

				System.out.println(d.getDepart());

			}

		}

		Date pubDate = alarm.getPubDate();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String downFileName = sdf1.format(new Date());// 替换后的文件名
		String imagePath = alarm.getImagePath();
		if (imagePath != null && !"".equals(imagePath)) {
			String path = servletContext.getRealPath("/upload") + "/" + imagePath;
		}

		try {
			String pdate = sdf2.format(pubDate);
			// String pdate = request.getParameter("pubDate");
			Date date = sdf2.parse(pdate);
			int duration = 0;
			if ("".equals(alarm.getDuration())) {

			} else {
				if (alarm.getDuration() != null) {
					duration = Integer.parseInt(alarm.getDuration());
				}
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, duration);
			Date enddate = c.getTime();
			String edate = sdf2.format(enddate);
			String pedate = pdate + "  至  " + edate;

			String pubRange = alarm.getPubRangeName();

			List<ReplaceData> rls = new ArrayList<ReplaceData>();
//			rls.add(FileGernerator.getReplaceData("信号名称",alarm.getAlarmTypeName()));
			rls.add(FileGernerator.getReplaceData("预警内容", channelInstance.getContent()));
			rls.add(FileGernerator.getReplaceData("制作人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("发送时间", pdate));
			rls.add(FileGernerator.getReplaceData("部门", departmentService.get(alarm.getDeptId()).getName()));
			rls.add(FileGernerator.getReplaceData("流水号",
					alarm.getPubNo().substring(alarm.getPubNo().length() - 8, alarm.getPubNo().length() - 4)
							+ alarm.getPubNo().substring(alarm.getPubNo().length() - 2, alarm.getPubNo().length())));

			String fileNamePath = "";// 模板文件名
			String image = "";
			String alarmTypeId = alarm.getTypeId();
			rls.add(FileGernerator.getReplaceData("签发人", alarm.getIssuer()));
			if (alarm.getType().equals("alarm")) {
				fileNamePath = "部门传真模板.doc";
				image = "D:\\tztfsource\\images\\warning_icon\\" + alarm.getAlarmTypeName() + ".jpg";
				String defense = alarmTypeService.get(Long.valueOf(alarmTypeId)).getDefence();
				rls.add(FileGernerator.getReplaceData("防御指南", defense));
			} else {
				fileNamePath = "部门事件传真模板.doc";
				image = "";
				rls.add(FileGernerator.getReplaceData("类型", alarm.getAlarmTypeName()));
			}

			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			System.out.println(newPath);
			FileGernerator.createFiledir(newPath);
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,
					null, null);
			// FileGernerator.creatFile2(alarm, dept, downFileName);

			String path = newPath + downFileName + ".pdf";
			FileGernerator.getTifByDoc(newPath + downFileName);
			String fax = "057187352314";

			if (!"".equals(fax)) {
				byte[] img = img2byte(path);
				List<Object> list = new ArrayList<Object>();
				list.add(downFileName + ".pdf");

				String sendnumber = SystemProperties.getProperty("sendnumber");
				String useraccount = SystemProperties.getProperty("useraccount");
				String bnetaccount = SystemProperties.getProperty("bnetaccount");
				String password = SystemProperties.getProperty("password");
				String areaid = SystemProperties.getProperty("areaid");

				SendFaxServiceService createAlarmFileService = new SendFaxServiceService();
				SendFaxService port = createAlarmFileService.getSendFaxServicePort();
				boolean faxSatus = true;
				String message = "";
				if (!alarm.getDepartFax().equals("")) {
					String destnumber = "";
					String[] departFaxs = alarm.getDepartFax().split(" ");
					for (int i = 0; i < departFaxs.length; i++) {
						DepartFax d = departFaxService.get(Long.valueOf(departFaxs[i]));
						destnumber += d.getFax_num() + ",";
					}
					destnumber = destnumber.length() > 0 ? destnumber.substring(0, destnumber.length() - 1) : "";
					System.out.println(destnumber);
						String result = port.upload(img, null, null, 
								null, null, null, null,
								null, null, null, list, sendnumber,
								"预警文件", useraccount, bnetaccount, password, destnumber,
								areaid, "0");
						org.json.JSONObject jo =  XML.toJSONObject(result);
						jo =   jo.getJSONObject("root");
						int resultcode=jo.getInt("resultcode");
						if(resultcode==1000){
						}else{
							faxSatus=false;
							message="发送失败";
						}

				}
				if (faxSatus) {
					channelInstance.setReleaseState("4");
					channelInstance.setFeedbackMessage("发送成功");
				} else {
					channelInstance.setReleaseState("3");
					channelInstance.setFeedbackMessage(message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}

		System.out.println("部门传真结束");
	}

	private void createGTxmlFile(GTAlarmDomain alarm) throws IOException {
		String filePath = "D:/gtxml/" + alarm.getFileName();
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File(filePath), false), "UTF-8"));
		bw.write(alarm.getAlarmContent());
		bw.close();
		String zipPath = "D:/gtzip/" + alarm.getFileName().replace(".xml", ".zip");
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

	public static byte[] img2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
//          String path = "D://20181105172111.doc";
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
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}

}
