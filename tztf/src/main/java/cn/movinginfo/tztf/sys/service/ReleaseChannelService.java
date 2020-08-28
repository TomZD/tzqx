package cn.movinginfo.tztf.sys.service;

import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.mapper.ReleaseChannelMapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

@Component
public class ReleaseChannelService extends BaseService<ReleaseChannel,ReleaseChannelMapper>{
	
	public ReleaseChannel passNameGetMessage(String name){
		return mapper.passNameGetMess(name);
	}
	
	public String passCNameToEName(String name){
		return mapper.passCNameToEName(name);
		
	}

	public ReleaseChannel getByNameEn(String nameEn) {
		ReleaseChannel releaseChannel = new ReleaseChannel();
		releaseChannel.setValid(1);
		releaseChannel.setNameEn(nameEn);
		return mapper.selectOne(releaseChannel);
	}
	
	/*public List<ReleaseChannel>  getAll(){
		return mapper.getAllParents();
	}*/
	public List<ReleaseChannel> getByKettle(){
		return mapper.getByKettle();
		
	}
	
	public List<ReleaseChannel> getSomeChannel() {
		return mapper.getSomeChannel();
	}
	
	public List<ReleaseChannel> getById(Long id) {
		Example example = new Example(ReleaseChannel.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("id", id);
		criteria.andNotEqualTo("kettle", 1);
		return mapper.selectByExample(example);
	}
	
	public List<ReleaseChannel> getByValid(){
		return mapper.getByValid();
	}
	
	public String findNameByChannelId(Long channelId) {
		return mapper.findNameByChannelId(channelId);
	}
}

