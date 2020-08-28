package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.ForwardAlarm;
import tk.mybatis.mapper.common.Mapper;

public interface ForwardAlarmMapper extends Mapper<ForwardAlarm> {
    //根据departmentid
    @Select("INSERT INTO sys_t_forward(alarm_id,user_id,sms_group,valid) VALUES(#{alarmId},#{userId},#{smsGroup},#{valid})")
    @Results(value={
            @Result(property = "alarmId", column = "alarm_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "smsGroup", column = "sms_groups"),
    })
    public Integer saveForwardAlarm(ForwardAlarm forwardAlarm);

}
