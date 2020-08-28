package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.EmergencyProduct;
import cn.movinginfo.tztf.sem.domain.EmergencyProductExample;

public interface EmergencyProductMapper {
    long countByExample(EmergencyProductExample example);

    int deleteByExample(EmergencyProductExample example);

    int insert(EmergencyProduct record);

    int insertSelective(EmergencyProduct record);

    List<EmergencyProduct> selectByExample(EmergencyProductExample example);

    int updateByExampleSelective(@Param("record") EmergencyProduct record, @Param("example") EmergencyProductExample example);

    int updateByExample(@Param("record") EmergencyProduct record, @Param("example") EmergencyProductExample example);
    
    public EmergencyProduct findProductById(@Param("id")Long id);
}