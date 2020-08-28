package cn.movinginfo.tztf.sev.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import tk.mybatis.mapper.common.Mapper;

public interface ReleaseChannelInstanceMapper extends Mapper<ReleaseChannelInstance> {
	
	@Select("select * from sev_t_release_channel_instance where SENDER_NUMBER = #{param1} and version =#{param2} and valid=1")
	@Results(value = {
			@Result(property = "channelId", column = "CHANNEL_ID"),
			@Result(property = "arriveTime", column = "ARRIVE_TIME"),
			@Result(property = "senderType", column = "SENDER_TYPE"),
			@Result(property = "releaseState", column = "CHANNEL_STATE"),
			@Result(property = "updateDate", column = "update_date"),
			@Result(property = "resultId", column = "RESULT_ID")
    })
	public List<ReleaseChannelInstance> queryByVersion(String no,int version);
	
	@Select("select * from sev_t_release_channel_instance where SENDER_NUMBER = #{param1} and version !=#{param2} and valid =1")
	@Results(value = {
			@Result(property = "channelId", column = "CHANNEL_ID"),
			@Result(property = "arriveTime", column = "ARRIVE_TIME"),
			@Result(property = "senderType", column = "SENDER_TYPE"),
			@Result(property = "releaseState", column = "CHANNEL_STATE"),
			@Result(property = "updateDate", column = "update_date"),
			@Result(property = "resultId", column = "RESULT_ID")

    })
	public List<ReleaseChannelInstance> getReleaseChannelInstanceByPubNo(String no,int version);
	
	@Select("select * from sev_t_release_channel_instance where SENDER_NUMBER = #{param1} and VERSION = #{param2}")
	@Results(value = {
			@Result(property = "channelId", column = "CHANNEL_ID"),
			@Result(property = "arriveTime", column = "ARRIVE_TIME"),
			@Result(property = "senderType", column = "SENDER_TYPE"),
			@Result(property = "releaseState", column = "CHANNEL_STATE"),
			@Result(property = "resultId", column = "RESULT_ID")
    })
	public List<ReleaseChannelInstance> queryByNumber(String no,Integer version);
	
	
	@Select("select * from sev_t_release_channel_instance where alarm_id = #{param1} ")
	@Results(value = {
			@Result(property = "channelId", column = "CHANNEL_ID"),
			@Result(property = "arriveTime", column = "ARRIVE_TIME"),
			@Result(property = "senderType", column = "SENDER_TYPE"),
			@Result(property = "releaseState", column = "CHANNEL_STATE"),
			@Result(property = "resultId", column = "RESULT_ID")
    })
	public List<ReleaseChannelInstance> findChannelByAlarmId(Long alarmId);
	
	@Select("select * from sev_t_release_channel_instance where SENDER_NUMBER = #{param1}")
	@Results(value = {
			@Result(property = "channelId", column = "CHANNEL_ID"),
			@Result(property = "arriveTime", column = "ARRIVE_TIME"),
			@Result(property = "senderType", column = "SENDER_TYPE"),
			@Result(property = "releaseState", column = "CHANNEL_STATE"),
			@Result(property = "resultId", column = "RESULT_ID")
    })
	public List<ReleaseChannelInstance> queryByOnlyNumber(String no);
	
	@Select("select * from sev_t_release_channel_instance where SENDER_NUMBER = #{param1} and VERSION = #{param2} and valid =1")
	@Results(value = {
			@Result(property = "channelId", column = "CHANNEL_ID"),
			@Result(property = "arriveTime", column = "ARRIVE_TIME"),
			@Result(property = "senderType", column = "SENDER_TYPE"),
			@Result(property = "releaseState", column = "CHANNEL_STATE"),
			@Result(property = "resultId", column = "RESULT_ID")
    })
	public List<ReleaseChannelInstance> queryByNumber2(String no,Integer version);

	@Select("UPDATE sev_t_release_channel_instance SET valid =0 where SENDER_NUMBER = #{param1} and VERSION = #{param2}")
	public void removeOldRelease(String pubNo,Integer version);
}
