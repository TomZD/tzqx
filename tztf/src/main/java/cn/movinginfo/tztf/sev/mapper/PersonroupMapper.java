package cn.movinginfo.tztf.sev.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sev.domain.Personroup;
import cn.movinginfo.tztf.sev.domain.PersonroupExample;

public interface PersonroupMapper {
    long countByExample(PersonroupExample example);

    int deleteByExample(PersonroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Personroup record);

    int insertSelective(Personroup record);

    List<Personroup> selectByExample(PersonroupExample example);

    Personroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Personroup record, @Param("example") PersonroupExample example);

    int updateByExample(@Param("record") Personroup record, @Param("example") PersonroupExample example);

    int updateByPrimaryKeySelective(Personroup record);

    int updateByPrimaryKey(Personroup record);
}