package cn.movinginfo.tztf.sen.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sen.model.TyphoonData;
import cn.movinginfo.tztf.sen.model.TyphoonDataExample;

public interface TyphoonDataMapper {
    long countByExample(TyphoonDataExample example);

    int deleteByExample(TyphoonDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TyphoonData record);

    int insertSelective(TyphoonData record);

    List<TyphoonData> selectByExample(TyphoonDataExample example);

    TyphoonData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TyphoonData record, @Param("example") TyphoonDataExample example);

    int updateByExample(@Param("record") TyphoonData record, @Param("example") TyphoonDataExample example);

    int updateByPrimaryKeySelective(TyphoonData record);

    int updateByPrimaryKey(TyphoonData record);
    
    void insertList(List<TyphoonData> result);
}