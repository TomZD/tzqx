package cn.movinginfo.tztf.common.utils;

import java.util.List;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.service.AlarmService;

public class GenerateAlarmNoticeThread implements Runnable {

	private AlarmService alarmService;
	private List<ReplaceData> rls;
	private String image;
	private String templateFolder;
	private String modelFile;
	private Alarm alarm;
	
	public GenerateAlarmNoticeThread(AlarmService alarmService,Alarm alarm,List<ReplaceData> rls,String image,String templateFolder,String modelFile){
		this.alarmService = alarmService;
		this.alarm = alarm;
		this.rls = rls;
		this.image = image;
		this.templateFolder = templateFolder;
		this.modelFile = modelFile;
	}
	@Override
	public void run() {
		try{
			FileGernerator.generateAlarmNoticeFile(rls, image, templateFolder, modelFile);
			alarmService.saveOrUpdate(alarm);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
