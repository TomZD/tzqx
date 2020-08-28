package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.ExpertLaLo;
import cn.movinginfo.tztf.sem.domain.ExpertLaLoExample;

public interface ExpertLaLoMapper {
    long countByExample(ExpertLaLoExample example);

    int deleteByExample(ExpertLaLoExample example);

    int insert(ExpertLaLo record);

    int insertSelective(ExpertLaLo record);

    List<ExpertLaLo> selectByExample(ExpertLaLoExample example);

    int updateByExampleSelective(@Param("record") ExpertLaLo record, @Param("example") ExpertLaLoExample example);

    int updateByExample(@Param("record") ExpertLaLo record, @Param("example") ExpertLaLoExample example);
    
    public ExpertLaLo findExpertById(@Param("id")Long id);
}