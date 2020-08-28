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

import cn.movinginfo.tztf.sev.domain.Publish;
import cn.movinginfo.tztf.sev.mapper.PublishMapper;
import net.ryian.orm.service.BaseService;

@Component
public class PublishService extends BaseService<Publish, PublishMapper>{

	private static final Logger LOG = Logger.getLogger(PublishService.class);
	
	public List<Publish> getPublishListByDepartmentId(Long deparmentId){
		return mapper.getPublishListByDepartmentId(deparmentId);
	}
	
	public List<Publish> getPublishListByAlarmId(Long alarmId){
		
		return mapper.getPublishListByAlarmId(alarmId);
	}
	
	public List<Publish> getPublishListByInstanceId(Long instanceId){
		
		return mapper.getPublishListByInstanceId(instanceId);
	}
	
	/**
	 * 根据部门ID查询未发布的预警通知单
	 * @param departmentId
	 * @return
	 */
	public List<Publish> getPublishList(Long departmentId){
		Publish publish = new Publish();
		publish.setDepartmentId(departmentId);
		publish.setIsPublish(0);
		return mapper.select(publish);
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
