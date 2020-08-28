package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Danger;
import cn.movinginfo.tztf.sem.domain.DangerExample;

public interface DangerMapper {
    long countByExample(DangerExample example);

    int deleteByExample(DangerExample example);

    int insert(Danger record);

    int insertSelective(Danger record);

    List<Danger> selectByExample(DangerExample example);

    int updateByExampleSelective(@Param("record") Danger record, @Param("example") DangerExample example);

    int updateByExample(@Param("record") Danger record, @Param("example") DangerExample example);
    
    public Danger findDangerById(@Param("id")Long id);
}