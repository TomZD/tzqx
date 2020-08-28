package cn.movinginfo.tztf.sys.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sys.domain.ChannelCount;
import cn.movinginfo.tztf.sys.domain.ChannelCountExample;

public interface ChannelCountMapper {
    long countByExample(ChannelCountExample example);

    int deleteByExample(ChannelCountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ChannelCount record);

    int insertSelective(ChannelCount record);

    List<ChannelCount> selectByExample(ChannelCountExample example);

    ChannelCount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ChannelCount record, @Param("example") ChannelCountExample example);

    int updateByExample(@Param("record") ChannelCount record, @Param("example") ChannelCountExample example);

    int updateByPrimaryKeySelective(ChannelCount record);

    int updateByPrimaryKey(ChannelCount record);
    
    int getCountMonthByChannel(@Param("channel")String channel, @Param("month1")Date month1, @Param("month2")Date month2);

	int getCountYearByChannel(@Param("channel")String channel, @Param("year1")Date year1, @Param("year2")Date year2);
	
	int getCountByChannel(@Param("channel")String channel);
	
	int getCount();

	ChannelCount getCountByChannelAndDate(@Param("channel")String channel, @Param("time")Date time);

	int getWebCount();
}