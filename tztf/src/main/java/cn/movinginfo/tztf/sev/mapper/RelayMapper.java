package cn.movinginfo.tztf.sev.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Relay;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface RelayMapper extends Mapper<Relay>{
    //根据预警和乡镇id查询
    @Select("select * from sev_t_relay where alarm_id =#{alarm_id} and town_id = #{town_id}")
    @Results(value = {
            @Result(property = "isFeedback", column = "is_feedback"),
            @Result(property = "alarmId", column = "alarm_id"),
            @Result(property = "isRelay", column = "is_relay"),
            @Result(property = "townId", column = "town_id")
    })
    Relay selectByAlarmTown(@Param("alarm_id") Integer alarm_id, @Param("town_id") Integer town_id);
    //根据预警id查询
    @Select("select * from sev_t_relay where alarm_id =#{alarm_id}")
    @Results(value = {
            @Result(property = "isFeedback", column = "is_feedback"),
            @Result(property = "alarmId", column = "alarm_id"),
            @Result(property = "isRelay", column = "is_relay"),
            @Result(property = "townId", column = "town_id")
    })
    List<Relay> selectByAlarmId(@Param("alarm_id") Integer alarm_id);
}
