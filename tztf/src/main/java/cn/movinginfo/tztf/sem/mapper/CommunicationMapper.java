package cn.movinginfo.tztf.sem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.dd.domain.WarnDetail;
import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.CommunicationExample;

public interface CommunicationMapper {
    long countByExample(CommunicationExample example);

    int deleteByExample(CommunicationExample example);

    int insert(Communication record);

    int insertSelective(Communication record);

    List<Communication> selectByExample(CommunicationExample example);

    int updateByExampleSelective(@Param("record") Communication record, @Param("example") CommunicationExample example);

    int updateByExample(@Param("record") Communication record, @Param("example") CommunicationExample example);
    
    public Communication findCommunicationById(@Param("id")Long id);

	WarnDetail getDetailById(@Param("id")Long id);
	
	List<String> getName(@Param("id")Long id);
    
}