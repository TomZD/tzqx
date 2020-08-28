package cn.movinginfo.tztf.dd.service;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.ryian.commons.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.movinginfo.tztf.dd.domain.Disaster;
import cn.movinginfo.tztf.dd.domain.DisasterExample;
import cn.movinginfo.tztf.dd.mapper.DisasterMapper;
import cn.movinginfo.tztf.sev.domain.Alarm;
import tk.mybatis.mapper.entity.Example;
@Component
public class DisasterService  {
	@Autowired
	private DisasterMapper disasterMapper;
	
	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	

	public int saveDisaster(Disaster disaster) {
		return disasterMapper.insertSelective(disaster);
		
	}
	
	public List<Disaster> query(Map<String, String> paramMap) {
		DisasterExample example = new DisasterExample();
		DisasterExample.Criteria criteria = example.createCriteria();
		//criteria.andEqualTo("valid", "1");
		String areaId = paramMap.get("areaId");
		String type = paramMap.get("type");
		if (!StringUtils.isEmpty(areaId)) {
			criteria.andAreaIdEqualTo(areaId);
		}
//		if (!StringUtils.isEmpty(type)) {
//			criteria.andLike("type", "%" + type + "%");
//		}
		// 上报部门
		String deptId = paramMap.get("deptId");
		if (!StringUtils.isEmpty(deptId)) {
			criteria.andDeptIdEqualTo(Long.valueOf(deptId));
		}
		
		String pubState = paramMap.get("pubState");
		if (!StringUtils.isEmpty(pubState)) {
			criteria.andPubStateEqualTo(pubState);
		}

		// 开始时间
		String startDate = paramMap.get("startDate");
		if (!StringUtils.isEmpty(startDate)) {
			
			try {
				criteria.andPubDateGreaterThan(df1.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 结束时间
		String endDate = paramMap.get("endDate");
		if (!StringUtils.isEmpty(endDate)) {
			try {
				criteria.andPubDateLessThan(df1.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		example.setOrderByClause("pub_date desc");
		return disasterMapper.selectByExample(example);
	}
	
	public Disaster get(long id) {
		
		return disasterMapper.selectByPrimaryKey(id);
	}
	
	public void saveOrUpdate(Disaster disaster) {
		
		disasterMapper.updateByPrimaryKeySelective(disaster);
	}
	
	
	public String uploadPage(CommonsMultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		ServletContext servletCtx = request.getSession().getServletContext();
		String root = servletCtx.getRealPath("/disaUpload");

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

	public List<Disaster> getListByAreaId(String areaId) {
		// TODO Auto-generated method stub
		return disasterMapper.getListByAreaId(areaId);
	}

	public List<Disaster> getListIsPublishing(String areaId) {
		DisasterExample example = new DisasterExample();
		DisasterExample.Criteria criteria = example.createCriteria();
		if(!"1".equals(areaId)) {
			criteria.andAreaIdEqualTo(areaId);
		}
		criteria.andPubStateEqualTo("1");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);   
		calendar.add(Calendar.MONTH, -1);
		Date mathTime = calendar.getTime();
		criteria.andPubDateGreaterThan(mathTime);
		example.setOrderByClause("pub_date desc");
		return disasterMapper.selectByExample(example);
	}

	
    public List<Disaster> getDdDisaster() {
    	DisasterExample example = new DisasterExample();
    	return disasterMapper.selectByExample(example);
    }
	
    public Disaster findDisasterById(Long id) {
    	return disasterMapper.findDisasterById(id);
    }
    
    public List<Disaster> getDdDisaster(Long departId) {
    	DisasterExample example = new DisasterExample();
    	DisasterExample.Criteria criteria = example.createCriteria();
    	criteria.andDeptIdEqualTo(departId);
    	example.setOrderByClause("pub_date desc");
    	return disasterMapper.selectByExample(example);
    }
}
