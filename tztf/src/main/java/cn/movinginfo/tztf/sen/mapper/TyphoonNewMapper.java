package cn.movinginfo.tztf.sen.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sen.model.TyphoonNew;
import cn.movinginfo.tztf.sen.model.TyphoonNewExample;

public interface TyphoonNewMapper {
    long countByExample(TyphoonNewExample example);

    int deleteByExample(TyphoonNewExample example);

    int insert(TyphoonNew record);

    int insertSelective(TyphoonNew record);

    List<TyphoonNew> selectByExample(TyphoonNewExample example);

    int updateByExampleSelective(@Param("record") TyphoonNew record, @Param("example") TyphoonNewExample example);

    int updateByExample(@Param("record") TyphoonNew record, @Param("example") TyphoonNewExample example);

	void insertList(List<TyphoonNew> list);
}