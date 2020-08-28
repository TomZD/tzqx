package cn.movinginfo.tztf.releasechannel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.action.ExernalWeb;
import cn.movinginfo.tztf.sys.service.DeptService;
import net.ryian.core.utils.SpringContextUtil;

public class ExternalWebChannel implements Channel {
	private static final Logger log = Logger.getLogger(ExternalWebChannel.class);
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil
			.getBean("releaseChannelInstanceService");
	private AlarmService alarmService = SpringContextUtil.getBean(AlarmService.class);
	private DeptService deptService = SpringContextUtil.getBean(DeptService.class);
	@Override
	public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		SimpleDateFormat fd = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		List<Alarm> listAlarm = alarmService.getListNowAllAlarm();
		List<ExernalWeb> ewList= new LinkedList<ExernalWeb>();
		for(Alarm alarm : listAlarm){
			ExernalWeb ew = new ExernalWeb();
			Long deptId = alarm.getDeptId();
			Date comptDate = alarm.getCompleteDate();
			if(comptDate==null){
				continue;
			}
			ew.setDeartment(deptService.get(deptId).getName());
			ew.setContent(alarm.getContent());
			ew.setDate(fd.format(comptDate));
			ew.setPrescription(alarm.getDuration());
			ew.setRange(alarm.getPubRangeName());
			ew.setTitle(alarm.getTitle());
			ewList.add(ew);
		}
		String alarmStr = com.alibaba.fastjson.JSON.toJSONString(ewList);
		ServletContext context = SpringContextUtil.getBean(ServletContext.class);
		String newPath = context.getRealPath("/jsonStr/publish.json");
		File file = new File(newPath);
		OutputStreamWriter bw = null;
		try {
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(alarmStr);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
		// TODO Auto-generated method stub

	}
}
