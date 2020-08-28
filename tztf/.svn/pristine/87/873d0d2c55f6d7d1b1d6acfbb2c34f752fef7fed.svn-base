package cn.movinginfo.tztf.dd.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.ryian.commons.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.dd.domain.Log;
import cn.movinginfo.tztf.dd.domain.LogExample;
import cn.movinginfo.tztf.dd.mapper.LogMapper;

@Component
public class LogService {
	@Autowired
	private LogMapper logMapper;
	
	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");
	
	public int savelog(Log log) {
		return logMapper.insertSelective(log);
		
	}
	
	public List<Log> query(Map<String, String> paramMap) {
		LogExample example = new LogExample();
		LogExample.Criteria criteria = example.createCriteria();
		String areaId = paramMap.get("areaId");
		if (!StringUtils.isEmpty(areaId)) {
			criteria.andAreaIdEqualTo(areaId);
		}
		// 上报部门
		String deptId = paramMap.get("deptId");
		if (!StringUtils.isEmpty(deptId)) {
			criteria.andDeptIdEqualTo(Long.valueOf(deptId));
		}
		String userId = paramMap.get("userId");
		if (!StringUtils.isEmpty(userId)) {
			criteria.andUserIdEqualTo(Integer.valueOf(userId));
		}

		// 开始时间
		String startDate = paramMap.get("startDate");
		if (!StringUtils.isEmpty(startDate)) {
			
			try {
				criteria.andDateGreaterThan(df1.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 结束时间
		String endDate = paramMap.get("endDate");
		if (!StringUtils.isEmpty(endDate)) {
			try {
				criteria.andDateLessThan(df1.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		example.setOrderByClause("date desc");
		return logMapper.selectByExample(example);
	}

	public JSONArray getHistoryData(int id,String startDate,String endDate) {
		LogExample example = new LogExample();
		LogExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		
		if (!StringUtils.isEmpty(startDate)) {
			try {
				criteria.andDateGreaterThan(df1.parse(startDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 结束时间
		
		if (!StringUtils.isEmpty(endDate)) {
			try {
				criteria.andDateLessThan(df1.parse(endDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		example.setOrderByClause("date desc");
		List<Log> logData = logMapper.selectByExample(example);
		 JSONArray json = new JSONArray();
		for(Log l:logData){
			JSONObject jo = new JSONObject();
			jo.put("date",df1.format(l.getDate()) );
			jo.put("content",l.getContent());
			json.add(jo);
			
		}
		
		return json;
	}

}
