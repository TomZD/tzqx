package cn.movinginfo.tztf.sev.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.movinginfo.tztf.sev.domain.AlarmNotice;
import cn.movinginfo.tztf.sev.mapper.AlarmNoticeMapper;
import net.ryian.orm.service.BaseService;

@Component
public class AlarmNoticeService extends BaseService<AlarmNotice, AlarmNoticeMapper>{

	private static final Logger LOG = Logger.getLogger(AlarmNoticeService.class);
	
	public List<AlarmNotice> getAlarmNoticeListByDepartmentId(Long deparmentId){
		return mapper.getAlarmNoticeListByDepartmentId(deparmentId);
	}
	
	public List<AlarmNotice> getAlarmNoticeListByAlarmId(Long alarmId){
		
		return mapper.getAlarmNoticeListByAlarmId(alarmId);
	}
	
	/**
	 * 根据部门ID查询未发布的预警通知单
	 * @param departmentId
	 * @return
	 */
	public List<AlarmNotice> getAlarmNoticeList(Long departmentId){
		AlarmNotice alarmNotice = new AlarmNotice();
		alarmNotice.setDepartmentId(departmentId);
		alarmNotice.setIsPublish(0);
		return mapper.select(alarmNotice);
	}
	
	public String upload(CommonsMultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		ServletContext servletCtx = request.getSession().getServletContext();
		String root = servletCtx.getRealPath("/upload");
		File tmpFile = new File(root);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		String newFileName = UUID.randomUUID().toString();
		String page = file.getOriginalFilename();
		String[] xx = page.split("\\.");
		String ext = xx[xx.length - 1];
		root = root + "/" + newFileName + "." + ext;
		File newFile = new File(root);
		file.transferTo(newFile);
		return newFileName + "." + ext;
	}
	
}
