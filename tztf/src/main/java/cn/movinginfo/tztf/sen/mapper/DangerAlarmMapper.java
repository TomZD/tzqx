package cn.movinginfo.tztf.sen.mapper;


import org.apache.ibatis.annotations.*;

import cn.movinginfo.tztf.sen.domain.DangerAlarm;
import cn.movinginfo.tztf.sen.domain.DangerAlarmDetail;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: qu
 * @history:
 */
public interface DangerAlarmMapper extends Mapper<DangerAlarm> {

    /**
     * 地质灾害 当前风险
     */
    @Select("select alarm.point_id as pointId,alarm.alarm_time as time,alarm.value as value,alarm.level as level,point.longitude as longitude,point.latitude as latitude,point.name as name from sen_t_danger_alarm alarm,sen_t_danger_point point " +
            "where alarm.point_id=point.id and (alarm.alarm_time between #{before10} and #{currentTime}) and  alarm.danger_type_id='2' order by alarm.point_id,alarm.level desc  \n" +
            "\n")
    public List<DangerAlarmDetail> getCurrentRisk(@Param("currentTime") String currentTime, @Param("before10") String before10);


    /**
     * 地质灾害 12时风险
     */
    @Select("select alarm.point_id as pointId,alarm.alarm_time as time,alarm.value as value,alarm.level as level,point.longitude as longitude,point.latitude as latitude,point.name as name from sen_t_danger_alarm alarm,sen_t_danger_point point \n" +
            "   where alarm.point_id=point.id and (alarm.alarm_time = #{timeOf12}) and alarm.danger_type_id='2' order by alarm.point_id,alarm.level desc ")
    public List<DangerAlarmDetail> getTimeOf12Risk(@Param("timeOf12") String timeOf12);


    /**
     * 地质灾害 24时 风险
     */
    @Select("select alarm.point_id as pointId,alarm.alarm_time as time,alarm.value as value,alarm.level as level,point.longitude as longitude,point.latitude as latitude,point.name as name  from sen_t_danger_alarm alarm,sen_t_danger_point point \n" +
            "   where alarm.point_id=point.id and (alarm.alarm_time = #{timeOf24}) and alarm.danger_type_id='2' order by alarm.point_id,alarm.level desc ")
    public List<DangerAlarmDetail> getTimeOf24Risk(@Param("timeOf24") String timeOf24);

    /**
     * 地址灾害 最近风险
     */
    @Select("select alarm.point_id as pointId,alarm.alarm_time as time,alarm.value as value,alarm.level as level,point.longitude as longitude,point.latitude as latitude,point.name as name  from sen_t_danger_alarm alarm,sen_t_danger_point point \n" +
            "   where alarm.point_id=point.id and (alarm.alarm_time between #{before120} and #{currentTime}) and alarm.danger_type_id='2' order by alarm.point_id,alarm.level desc ")
    public List<DangerAlarmDetail> getRecentRisk(@Param("before120") String before120, @Param("currentTime") String currentTime);

    //获取小流域山洪实况风险数据
    @Select("SELECT distinct d.*,b.town as areaName FROM sen_t_danger_alarm d, bj_areaalarm b WHERE d.area_id=b.id AND d.danger_type_id=0 \n" +
            "AND d.rule_expr LIKE '%ls_rain%' AND alarm_time=#{time} GROUP BY d.area_id")
    @Results(value = {
            @Result(property = "areaId", column = "area_id"),
            @Result(property = "value", column = "value"),
            @Result(property = "ruleExpr", column = "rule_expr"),
            @Result(property = "level", column = "level"),
            @Result(property = "alarmTime", column = "alarm_time"),
            @Result(property = "dangerTypeId", column = "danger_type_id"),
            @Result(property = "elementinfoIds", column = "elementinfo_ids")
    })
    public List<DangerAlarm> getLiveSituation(Timestamp time);

    //获取小流域山洪预报风险数据
    @Select("SELECT a.*,b.town as areaName FROM (SELECT * FROM sen_t_danger_alarm WHERE danger_type_id =0 AND alarm_time>=#{minTime} and alarm_time<#{maxTime} ORDER BY alarm_time DESC) a,\n" +
            "bj_areaalarm b WHERE a.area_id = b.id AND a.elementinfo_ids =#{element_id} GROUP BY a.area_id")
    @Results(value = {
            @Result(property = "areaId", column = "area_id"),
            @Result(property = "value", column = "value"),
            @Result(property = "ruleExpr", column = "rule_expr"),
            @Result(property = "level", column = "level"),
            @Result(property = "alarmTime", column = "alarm_time"),
            @Result(property = "dangerTypeId", column = "danger_type_id"),
            @Result(property = "elementinfoIds", column = "elementinfo_ids")
    })
    public List<DangerAlarm> getPrediction(@Param("element_id") Integer element_id, @Param("minTime") Timestamp minTime,@Param("maxTime") Timestamp maxTime);

    /**
     * 当前风险 动态获取时间轴
     */
    @Select("select DISTINCT(alarm.alarm_time) as time from sen_t_danger_alarm alarm,sen_t_danger_point point where alarm.point_id=point.id  and alarm.danger_type_id='2' ORDER BY alarm.alarm_time asc ")
    public List<DangerAlarmDetail> getAlarmTime();

}
