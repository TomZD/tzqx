package cn.movinginfo.tztf.sen.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sen.model.TownInfo;
import cn.movinginfo.tztf.sen.model.TownInfoExample;

public interface TownInfoMapper {
    long countByExample(TownInfoExample example);

    int deleteByExample(TownInfoExample example);

    int deleteByPrimaryKey(String iiiii);

    int insert(TownInfo record);

    int insertSelective(TownInfo record);

    List<TownInfo> selectByExample(TownInfoExample example);

    TownInfo selectByPrimaryKey(String iiiii);

    int updateByExampleSelective(@Param("record") TownInfo record, @Param("example") TownInfoExample example);

    int updateByExample(@Param("record") TownInfo record, @Param("example") TownInfoExample example);

    int updateByPrimaryKeySelective(TownInfo record);

    int updateByPrimaryKey(TownInfo record);

    //根据站名查询
    TownInfo selectByName(String name);

    List<TownInfo> selectAll();
}