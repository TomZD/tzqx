package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sys.domain.Feedback;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
public interface FeedbackMapper extends Mapper<Feedback> {
    //查询信息反馈列表(预警列表)
    @Select({"<script>" +
            "select a.*,r.is_feedback as feedback,r.is_relay as relay, r.message as msg from sev_t_alarm a,sev_t_relay r where a.id=r.alarm_id and a.pub_state IN (5,6,12) and a.valid=1 " +
            "<if test=\"alarmTypeName != null and alarmTypeName !='' \">" +
            "   and a.alarm_type_name =#{alarmTypeName}" +
            "</if> " +
            "<if test=\"deptId != null and deptId !='' \">" +
            "   and a.dept_id =#{deptId}" +
            "</if> " +
            "<if test=\"startDate != null and startDate !='' \">" +
            "   and a.pub_date >=#{startDate}" +
            "</if> " +
            "<if test=\"endDate != null and endDate !='' \">" +
            "   and a.pub_date &lt;=#{endDate}" +
            "</if> " +
            "<if test=\"townId != null and townId !='' \">" +
            "   and r.town_id=#{townId}" +
            "</if> " +
            " ORDER BY a.pub_date DESC" +
            "</script>"})
    @Results(value = {
            @Result(property = "areaId", column = "area_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "pubNo", column = "pub_no"),
            @Result(property = "alarmId", column = "alarm_id"),
            @Result(property = "isRelay", column = "is_relay"),
            @Result(property = "townId", column = "town_id"),
            @Result(property = "smsGroup", column = "sms_group"),
            @Result(property = "departFax", column = "depart_fax"),
            @Result(property = "pubRange", column = "pub_range"),
            @Result(property = "pubDate", column = "pub_date"),
            @Result(property = "pubRangeName", column = "pub_range_name"),
            @Result(property = "pubState", column = "pub_state"),
            @Result(property = "auditDate", column = "audit_date"),
            @Result(property = "releaseDate", column = "release_date"),
            @Result(property = "completeDate", column = "complete_date"),
            @Result(property = "cancelDate", column = "cancel_date"),
            @Result(property = "operationAction", column = "LAST_ACTION"),
            @Result(property = "pubChannel", column = "pub_channel"),
            @Result(property = "imagePath", column = "image_path"),
            @Result(property = "alarmTypeName", column = "alarm_type_name"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "isSend", column = "is_send"),
            @Result(property = "noticeFile", column = "notice_file"),
            @Result(property = "sendDate", column = "send_date"),
            @Result(property = "isFeedback", column = "is_feedback")
    })
    public List<Alarm> selectTownAlarm(@Param("townId") String townId, @Param("alarmTypeName") String alarmTypeName, @Param("deptId") Long deptId, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
    //查询历史预警记录
    @Select({"<script>" +
            "select a.*,r.is_feedback as feedback,r.is_relay as relay, r.message as msg from sev_t_alarm a,sev_t_relay r where a.id=r.alarm_id and a.pub_state IN (5,6,12) and a.valid=1 " +
            "<if test=\"alarmTypeName != null and alarmTypeName !='' \">" +
            "   and a.alarm_type_name =#{alarmTypeName}" +
            "</if> " +
            "<if test=\"deptId != null and deptId !='' \">" +
            "   and a.dept_id =#{deptId}" +
            "</if> " +
            "<if test=\"startDate != null and startDate !='' \">" +
            "   and a.pub_date >=#{startDate}" +
            "</if> " +
            "<if test=\"endDate != null and endDate !='' \">" +
            "   and a.pub_date &lt;=#{endDate}" +
            "</if> " +
            "<if test=\"townId != null and townId !='' \">" +
            "   and r.town_id=#{townId}" +
            "</if> " +
            " ORDER BY a.pub_date DESC" +
            "</script>"})
    @Results(value = {
            @Result(property = "areaId", column = "area_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "pubNo", column = "pub_no"),
            @Result(property = "alarmId", column = "alarm_id"),
            @Result(property = "isRelay", column = "is_relay"),
            @Result(property = "townId", column = "town_id"),
            @Result(property = "smsGroup", column = "sms_group"),
            @Result(property = "departFax", column = "depart_fax"),
            @Result(property = "pubRange", column = "pub_range"),
            @Result(property = "pubDate", column = "pub_date"),
            @Result(property = "pubRangeName", column = "pub_range_name"),
            @Result(property = "pubState", column = "pub_state"),
            @Result(property = "auditDate", column = "audit_date"),
            @Result(property = "releaseDate", column = "release_date"),
            @Result(property = "completeDate", column = "complete_date"),
            @Result(property = "cancelDate", column = "cancel_date"),
            @Result(property = "operationAction", column = "LAST_ACTION"),
            @Result(property = "pubChannel", column = "pub_channel"),
            @Result(property = "imagePath", column = "image_path"),
            @Result(property = "alarmTypeName", column = "alarm_type_name"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "isSend", column = "is_send"),
            @Result(property = "noticeFile", column = "notice_file"),
            @Result(property = "sendDate", column = "send_date"),
            @Result(property = "isFeedback", column = "is_feedback")
    })
    public List<Alarm> selectHistoryAlarm(@Param("townId") String townId, @Param("alarmTypeName") String alarmTypeName, @Param("deptId") Long deptId, @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
}
