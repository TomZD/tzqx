package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Expert;
import cn.movinginfo.tztf.sem.domain.ExpertExample;

public interface ExpertMapper {
    long countByExample(ExpertExample example);

    int deleteByExample(ExpertExample example);

    int insert(Expert record);

    int insertSelective(Expert record);

    List<Expert> selectByExample(ExpertExample example);

    int updateByExampleSelective(@Param("record") Expert record, @Param("example") ExpertExample example);

    int updateByExample(@Param("record") Expert record, @Param("example") ExpertExample example);
}