package cn.movinginfo.tztf.common.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sys.action.ExernalWeb;
import cn.movinginfo.tztf.sys.service.DeptService;
import net.ryian.core.utils.SpringContextUtil;

@Service(value = "publishJsonTask")
public class PublishJsonTask {

	private AlarmService alarmService = SpringContextUtil.getBean(AlarmService.class);
	private DeptService deptService = SpringContextUtil.getBean(DeptService.class);
	
	public void job(){
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
}
