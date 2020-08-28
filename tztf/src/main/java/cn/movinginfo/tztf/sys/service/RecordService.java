package cn.movinginfo.tztf.sys.service;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sys.domain.Record;
import cn.movinginfo.tztf.sys.domain.RecordExample;
import cn.movinginfo.tztf.sys.mapper.RecordMapper;
@Service
public class RecordService {
    @Autowired
	private RecordMapper recordMapper;
	
	public List<Record> getRecordList() {
		RecordExample example = new RecordExample();
		return recordMapper.selectByExample(example);
	}
	
	public int saveRcord(Record record) {
		return recordMapper.insertSelective(record);
	}
}
