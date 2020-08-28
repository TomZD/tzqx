package cn.movinginfo.tztf.sev.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.AlarmNotice;
import tk.mybatis.mapper.common.Mapper;

public interface AlarmNoticeMapper extends Mapper<AlarmNotice>{

	@Select("select t1.*,t2.name,t3.title,t3.notice_file from sev_t_alarm_notice t1 LEFT JOIN sys_t_department t2 on t1.department_id = t2.id LEFT JOIN sev_t_alarm t3 on t1.alarm_id = t3.id where department_id=#{param1}")
	@Results(value={
			@Result(property = "alarmId", column = "alarm_id"),
            @Result(property = "departmentId", column = "department_id"),
            @Result(property = "receiveDate", column = "receive_date"),
            @Result(property = "replyDate", column = "reply_date"),
            @Result(property = "isPublish", column = "is_publish"),
            @Result(property = "publishDetail", column = "publish_detail"),
            @Result(property = "publishDate", column = "publish_date"),
            @Result(property = "images", column = "images"),
            @Result(property = "depart.name", column = "name"),
            @Result(property = "alarm.title", column = "title"),
            @Result(property = "alarm.noticeFile", column = "notice_file")
	})
	public List<AlarmNotice> getAlarmNoticeListByDepartmentId(Long deparmentId);
	
	@Select("select t1.*,t2.name from sev_t_alarm_notice t1 LEFT JOIN sys_t_department t2 on t1.department_id = t2.id where alarm_id=#{param1}")
	@Results(value={
			@Result(property = "alarmId", column = "alarm_id"),
            @Result(property = "departmentId", column = "department_id"),
            @Result(property = "receiveDate", column = "receive_date"),
            @Result(property = "replyDate", column = "reply_date"),
            @Result(property = "isPublish", column = "is_publish"),
            @Result(property = "publishDetail", column = "publish_detail"),
            @Result(property = "publishDate", column = "publish_date"),
            @Result(property = "images", column = "images"),
            @Result(property = "depart.name", column = "name")
	})
	public List<AlarmNotice> getAlarmNoticeListByAlarmId(Long alarmId);
}
