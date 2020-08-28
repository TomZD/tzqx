package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.RescueTeam;
import cn.movinginfo.tztf.sem.domain.RescueTeamExample;

public interface RescueTeamMapper {
    long countByExample(RescueTeamExample example);

    int deleteByExample(RescueTeamExample example);

    int insert(RescueTeam record);

    int insertSelective(RescueTeam record);

    List<RescueTeam> selectByExample(RescueTeamExample example);

    int updateByExampleSelective(@Param("record") RescueTeam record, @Param("example") RescueTeamExample example);

    int updateByExample(@Param("record") RescueTeam record, @Param("example") RescueTeamExample example);
    
    public RescueTeam findRescueTeamById(@Param("id")Long id);
}