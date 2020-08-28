package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.Refuge;
import cn.movinginfo.tztf.sem.domain.RefugeExample;

public interface RefugeMapper {
    long countByExample(RefugeExample example);

    int deleteByExample(RefugeExample example);

    int insert(Refuge record);

    int insertSelective(Refuge record);

    List<Refuge> selectByExample(RefugeExample example);

    int updateByExampleSelective(@Param("record") Refuge record, @Param("example") RefugeExample example);

    int updateByExample(@Param("record") Refuge record, @Param("example") RefugeExample example);
    
    public Refuge findRefugeById(@Param("id")Long id);
}