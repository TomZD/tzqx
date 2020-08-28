package cn.movinginfo.tztf.sem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sem.domain.SevAlarm;
import cn.movinginfo.tztf.sem.domain.SevAlarmExample;
import cn.movinginfo.tztf.sem.mapper.SevAlarmMapper;

@Service
public class SevAlarmService {

	@Autowired
	private SevAlarmMapper alarmMapper;

	public void save(List<SevAlarm> list) {
		for (SevAlarm sevAlarm : list) {
			SevAlarmExample example = new SevAlarmExample();
			example.createCriteria().andPubNoEqualTo(sevAlarm.getPubNo());
			List<SevAlarm> pubnolist = alarmMapper.selectByExample(example);
			if(pubnolist.size() < 1 ) {
				alarmMapper.insertSelective(sevAlarm);
			}
		}
	}
}
