package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.Target;
import cn.movinginfo.tztf.sem.domain.TargetExample;

public interface TargetMapper {
    long countByExample(TargetExample example);

    int deleteByExample(TargetExample example);

    int insert(Target record);

    int insertSelective(Target record);

    List<Target> selectByExample(TargetExample example);

    int updateByExampleSelective(@Param("record") Target record, @Param("example") TargetExample example);

    int updateByExample(@Param("record") Target record, @Param("example") TargetExample example);
    
    public Target findTargetById(@Param("id")Long id);
}