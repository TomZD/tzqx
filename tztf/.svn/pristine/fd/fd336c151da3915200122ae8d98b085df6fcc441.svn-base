package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.SevAlarm;
import cn.movinginfo.tztf.sem.domain.SevAlarmExample;

public interface SevAlarmMapper {
    long countByExample(SevAlarmExample example);

    int deleteByExample(SevAlarmExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SevAlarm record);

    int insertSelective(SevAlarm record);

    List<SevAlarm> selectByExample(SevAlarmExample example);

    SevAlarm selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SevAlarm record, @Param("example") SevAlarmExample example);

    int updateByExample(@Param("record") SevAlarm record, @Param("example") SevAlarmExample example);

    int updateByPrimaryKeySelective(SevAlarm record);

    int updateByPrimaryKey(SevAlarm record);
}