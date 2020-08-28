package cn.movinginfo.tztf.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;
import cn.movinginfo.tztf.sys.service.ReleaseChannelService;

@Component
@Lazy(false)
public class InitWorker implements InitializingBean {

	@Autowired
	private DictService dictService;
	
	@Autowired
	private ReleaseChannelService releaseChannelService;
	
	@Autowired
	private DeptService deptService;
	

	@Override
	public void afterPropertiesSet() throws Exception {
		// 数据字典存入缓存
		List<String> keys = dictService.getKeyNames();
		for (String key : keys) {
			dictService.getDictsByKey(key);
		}
		List<ReleaseChannel> releaseChannelList = releaseChannelService.getAll();
		for(ReleaseChannel releaseChannel:releaseChannelList){
			if(releaseChannel.getParentId()!=null){
				if(SystemProperties.SUBRELEASECHANNEL_MAP.containsKey(releaseChannel.getParentId())){
					SystemProperties.SUBRELEASECHANNEL_MAP.get(releaseChannel.getParentId()).add(releaseChannel);
				}else{
					List<ReleaseChannel> subList = new ArrayList<ReleaseChannel>();
					subList.add(releaseChannel);
					SystemProperties.SUBRELEASECHANNEL_MAP.put(releaseChannel.getParentId(), subList);
				}
			}
			SystemProperties.RELEASECHANNEL_MAP.put(releaseChannel.getId(), releaseChannel);
		}
		List<Depart> deptList = deptService.getAll();
		for(Depart depart:deptList){
			if(depart.getChannelId()!=null){
				if(SystemProperties.RELEASECHANNEL_DEPART_MAP.containsKey(depart.getChannelId())){
					SystemProperties.RELEASECHANNEL_DEPART_MAP.get(depart.getChannelId()).add(depart);
				}else{
					List<Depart> departList = new ArrayList<Depart>();
					departList.add(depart);
					SystemProperties.RELEASECHANNEL_DEPART_MAP.put(depart.getChannelId(), departList);
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new Date(1442791800000l));
	}

}
