package cn.movinginfo.tztf.sem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sem.domain.SevReleaseChannelInstance;
import cn.movinginfo.tztf.sem.domain.SevReleaseChannelInstanceExample;
import cn.movinginfo.tztf.sem.domain.SevReleaseChannelInstanceExample.Criteria;
import cn.movinginfo.tztf.sem.mapper.SevReleaseChannelInstanceMapper;

@Service
public class SevReleaseService {

	@Autowired
	private SevReleaseChannelInstanceMapper channelInstanceMapper;

	public void save(List<SevReleaseChannelInstance> list) {
		for (SevReleaseChannelInstance sevReleaseChannelInstance : list) {
			SevReleaseChannelInstanceExample example = new SevReleaseChannelInstanceExample();
			Criteria criteria = example.createCriteria();
			criteria.andSenderNumberEqualTo(sevReleaseChannelInstance.getSenderNumber());
			criteria.andChannelIdEqualTo(sevReleaseChannelInstance.getChannelId());
			List<SevReleaseChannelInstance> result = channelInstanceMapper.selectByExample(example);
			if(result.size() < 1) {
				channelInstanceMapper.insertSelective(sevReleaseChannelInstance);
			}
		//		channelInstanceMapper.insertSelective(sevReleaseChannelInstance);
		//	}
		}
	}
	
}
