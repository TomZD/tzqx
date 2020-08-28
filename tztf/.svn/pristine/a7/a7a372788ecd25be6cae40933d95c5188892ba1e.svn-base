package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.Transport;
import cn.movinginfo.tztf.sem.domain.TransportExample;

public interface TransportMapper {
    long countByExample(TransportExample example);

    int deleteByExample(TransportExample example);

    int insert(Transport record);

    int insertSelective(Transport record);

    List<Transport> selectByExample(TransportExample example);

    int updateByExampleSelective(@Param("record") Transport record, @Param("example") TransportExample example);

    int updateByExample(@Param("record") Transport record, @Param("example") TransportExample example);
    
    public Transport findTransportById(@Param("id")Long id);
}