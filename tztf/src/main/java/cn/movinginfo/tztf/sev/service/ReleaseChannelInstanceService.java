package cn.movinginfo.tztf.sev.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.mapper.ReleaseChannelInstanceMapper;
import cn.movinginfo.tztf.sys.service.DictService;
import tk.mybatis.mapper.entity.Example;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;

@Component
public class ReleaseChannelInstanceService extends BaseService<ReleaseChannelInstance,ReleaseChannelInstanceMapper>  {
	
	@Autowired
	private DictService dictService;
	
	/**
	 * 获取指定预警对应的发布渠道内容<p>
	 * @param entity
	 * @return
	 * @author: zhangd
	 * @createTime: 2017年8月29日 下午4:20:51
	 * @updateTime: 
	 */
	public Set<ReleaseChannelInstance> getChannels(Alarm entity) {
		List<ReleaseChannelInstance> channels = getChannels(entity.getPubNo(), entity.getType(), entity.getVersion());
        return new HashSet<ReleaseChannelInstance>(channels);
	}
	
	public List<ReleaseChannelInstance> getChannels(String number, String type, Integer version) {
		
//		Example example = new Example(ReleaseChannelInstance.class);
//		Example.Criteria criteria = example.createCriteria();
//		if (!StringUtils.isEmpty(number)) {
//			criteria.andEqualTo("senderNumber", number);
//		}
//		if (!StringUtils.isEmpty(type)) {
//			criteria.andEqualTo("senderType", type);
//		}
//		if (!StringUtils.isEmpty(version.toString())) {
//			criteria.andEqualTo("version", version);
//		}
//		return mapper.selectByExample(example);
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("number", number);
		paramMap.put("type", type);
		paramMap.put("version", version.toString());
		String statement = "cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance.getChannelInstances";
		List<ReleaseChannelInstance> list = sqlSession.selectList(statement, paramMap);
		return list;
		
    }
	
	public Long update(ReleaseChannelInstance entity) {
		if (entity.getId() != null || entity.getId() != 0) {
			entity.setUpdateDate(new Date());
			mapper.updateByPrimaryKey(entity);
		}
		return entity.getId();
	}
	
	public int updateByPubNo(String pubNo,String status) {
		ReleaseChannelInstance r=new ReleaseChannelInstance();
		r.setValid(1);
		r.setSenderNumber(pubNo);
		r=mapper.selectOne(r);
		r.setReleaseState(status);
		return mapper.updateByPrimaryKey(r);
	}
	
	public int updateById(String id,String status) {
		ReleaseChannelInstance r=new ReleaseChannelInstance();
		r.setValid(1);
		r.setId(Long.parseLong(id));
		r=mapper.selectOne(r);
		r.setReleaseState(status);
		return mapper.updateByPrimaryKey(r);
	}
	
	public List<ReleaseChannelInstance> queryByVersion(String no,int version){
		return mapper.queryByVersion(no,version);
	}

	public List<ReleaseChannelInstance> queryByNumber(String no,Integer version){
		return mapper.queryByNumber(no,version);
	}
	
	public List<ReleaseChannelInstance> queryByOnlyNumber(String no){
		return mapper.queryByOnlyNumber(no);
	}

	public List<ReleaseChannelInstance> queryByNumber2(String no,Integer version){
		return mapper.queryByNumber2(no,version);
	}
	
	public List<ReleaseChannelInstance> findChannelByalarmId(Long alarmId) {
		return mapper.findChannelByAlarmId(alarmId);
	}
	
	public List<ReleaseChannelInstance> getReleaseChannelInstanceByPubNo(String no,int version) {
		return mapper.getReleaseChannelInstanceByPubNo(no,version);
	}
	
	public List<ReleaseChannelInstance> getChannelList(String pubNo,String type, Integer version) {
		Example example = new Example(ReleaseChannelInstance.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		if (!StringUtils.isEmpty(pubNo)) {
			criteria.andEqualTo("senderNumber", pubNo);
		}
		if (!StringUtils.isEmpty(type)) {
			criteria.andEqualTo("senderType", type);
		}
		if (version != null) {
			criteria.andEqualTo("version", version);
		}
		return mapper.selectByExample(example);
	}

	public void deleteById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
	
}
