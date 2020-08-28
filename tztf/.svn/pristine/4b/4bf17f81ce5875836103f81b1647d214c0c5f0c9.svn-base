package cn.movinginfo.tztf.sen.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sen.model.TownOCF;
import cn.movinginfo.tztf.sen.model.TownOCFExample;
import cn.movinginfo.tztf.sen.model.TownOCFKey;

public interface TownOCFMapper {
    long countByExample(TownOCFExample example);

    int deleteByExample(TownOCFExample example);

    int deleteByPrimaryKey(TownOCFKey key);

    int insert(TownOCF record);

    int insertSelective(TownOCF record);

    List<TownOCF> selectByExample(TownOCFExample example);

    TownOCF selectByPrimaryKey(TownOCFKey key);

    int updateByExampleSelective(@Param("record") TownOCF record, @Param("example") TownOCFExample example);

    int updateByExample(@Param("record") TownOCF record, @Param("example") TownOCFExample example);

    int updateByPrimaryKeySelective(TownOCF record);

    int updateByPrimaryKey(TownOCF record);
}