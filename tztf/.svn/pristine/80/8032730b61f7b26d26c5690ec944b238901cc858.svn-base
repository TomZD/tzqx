package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.Medical;
import cn.movinginfo.tztf.sem.domain.MedicalExample;

public interface MedicalMapper {
    long countByExample(MedicalExample example);

    int deleteByExample(MedicalExample example);

    int insert(Medical record);

    int insertSelective(Medical record);

    List<Medical> selectByExample(MedicalExample example);

    int updateByExampleSelective(@Param("record") Medical record, @Param("example") MedicalExample example);

    int updateByExample(@Param("record") Medical record, @Param("example") MedicalExample example);
    
    public Medical findMedicalById(@Param("id")Long id);
}