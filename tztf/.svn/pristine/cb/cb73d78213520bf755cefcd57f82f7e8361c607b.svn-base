package cn.movinginfo.tztf.sen.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sen.mapper.TownInfoMapper;
import cn.movinginfo.tztf.sen.model.TownInfo;

import javax.annotation.Resource;
import java.util.List;

@Service

public class TownInfoService {
    private final Logger logger = LoggerFactory.getLogger(TownInfoService.class);

    @Resource
    private TownInfoMapper townInfoMapper;

    public List<TownInfo> getTownInfo(){
        return townInfoMapper.selectByExample(null);
    }
    
    public TownInfo getTownInfo(String iiiii) {
    	return townInfoMapper.selectByPrimaryKey(iiiii);
    }

    public List<TownInfo> selectAll(){
        return townInfoMapper.selectAll();
    }

}
