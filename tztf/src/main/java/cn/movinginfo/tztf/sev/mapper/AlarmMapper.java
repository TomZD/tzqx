package cn.movinginfo.tztf.sev.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Alarm;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface AlarmMapper extends Mapper<Alarm>{
	
	@Select("select count(*) as num,right(alarm_type_name, 2) as alarm_type_name,DATE_FORMAT(pub_date,'%Y-%m-%d') as time from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') =#{param1}  and  pub_state in (5,6) and DATE_FORMAT(pub_date,'%m') =#{param2}  GROUP BY right(alarm_type_name, 2),DATE_FORMAT(pub_date,'%Y-%m-%d')")
	@Results(value = {
			@Result(property = "num", column = "num"),
            @Result(property = "time", column = "time"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "alarmTypeName", column = "alarm_type_name")
    })
	public  List<Alarm> getData(int year,int month);

	
	@Select("select count(*) as num,right(alarm_type_name, 2) as alarm_type_name,DATE_FORMAT(pub_date,'%Y-%m-%d') as time from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') =#{param1}  and  pub_state in (5,6) and DATE_FORMAT(pub_date,'%m') =#{param2} and area_id = #{param3}  GROUP BY right(alarm_type_name, 2),DATE_FORMAT(pub_date,'%Y-%m-%d')")
	@Results(value = {
			@Result(property = "num", column = "num"),
            @Result(property = "time", column = "time"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "alarmTypeName", column = "alarm_type_name")
    })
	public  List<Alarm> getOtherData(int year,int month,String areaId);

	
	@Select("select count(*) as num,right(alarm_type_name, 2) as alarm_type_name,DATE_FORMAT(pub_date,'%Y-%m-%d') as time from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') =#{param1} and pub_state in (5,6) and area_id=#{param4} and DATE_FORMAT(pub_date,'%m') =#{param2} and pid=#{param3}  GROUP BY right(alarm_type_name, 2),DATE_FORMAT(pub_date,'%Y-%m-%d')")
	@Results(value = {
			@Result(property = "num", column = "num"),
            @Result(property = "time", column = "time"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "alarmTypeName", column = "alarm_type_name")
    })
	public  List<Alarm> getDataAll(int year,int month,int type, String areaId);


	@Select("select count(*) as num,right(alarm_type_name, 2) as alarm_type_name,DATE_FORMAT(pub_date,'%Y-%m-%d') as time from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') =#{param1}  and pub_state in (5,6) GROUP BY right(alarm_type_name, 2),DATE_FORMAT(pub_date,'%Y-%m-%d')")
	@Results(value = {
			@Result(property = "num", column = "num"),
            @Result(property = "time", column = "time"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "alarmTypeName", column = "alarm_type_name")
    })
	public  List<Alarm> getYearList(int year);

	
	@Select("select count(*) as num,right(alarm_type_name, 2) as alarm_type_name,DATE_FORMAT(pub_date,'%Y-%m-%d') as time from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') =#{param1}  and pub_state in (5,6) and pid=#{param2} GROUP BY right(alarm_type_name, 3),DATE_FORMAT(pub_date,'%Y-%m-%d')")
	@Results(value = {
			@Result(property = "num", column = "num"),
            @Result(property = "time", column = "time"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "alarmTypeName", column = "alarm_type_name")
    })
	public  List<Alarm> getYearListByType(int year,int type);

	
	
	@Select("select * from sev_t_alarm where DATE_FORMAT(pub_date,'%Y%m%d') = #{param1} and right(alarm_type_name, 2) = #{param2}  and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "pubDate", column = "pub_date"),
            @Result(property = "cancelDate", column = "cancel_date"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "alarmTypeName", column = "alarm_type_name"),
            @Result(property = "pubState", column = "pub_state"),
            @Result(property = "pubNo", column = "pub_no")
            
    })
	public  List<Alarm> getDetail(String time,String level);

	
	@Select("select * from sev_t_alarm where DATE_FORMAT(pub_date,'%Y%m%d') = #{param1} and right(alarm_type_name, 2) = #{param2}  and pub_state in (5,6) and area_id = #{param3}")
	@Results(value = {
			@Result(property = "pubDate", column = "pub_date"),
            @Result(property = "cancelDate", column = "cancel_date"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "alarmTypeName", column = "alarm_type_name"),
            @Result(property = "pubState", column = "pub_state"),
            @Result(property = "pubNo", column = "pub_no"),
            @Result(property = "areaId", column = "area_id")
            
    })
	public  List<Alarm> getOtherDetail(String time,String level,String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') = #{param1} and right(alarm_type_name, 2) = #{param2} and area_id = #{param3} and pub_state in (5,6) ")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public  int getNum(int time,String level, String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') = #{param1}  and area_id = #{param2} and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public  int getYear(int time, String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') = #{param1} and DATE_FORMAT(pub_date,'%m') = #{param2} and right(alarm_type_name, 2) = #{param3} and area_id=#{param4} and pub_state in (5,6) ")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public int getMonth(int time,int month,String level, String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') = #{param1} and DATE_FORMAT(pub_date,'%m') = #{param2} and area_id=#{param3} and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public int getMonthAll(int time,int month, String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where pub_date >= #{param1} and right(alarm_type_name, 2) = #{param2} and area_id= #{param3} and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public int getWeek(String time,String level, String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where pub_date >= #{param1} and pid =#{param2} and area_id=#{param3} and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public int getEventWeek(String time,int pid, String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') = #{param1} and pid =#{param2} and area_id=#{param3} and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public int getEventYear(int time,int pid, String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') = #{param1} and DATE_FORMAT(pub_date,'%m') = #{param2} and area_id=#{param4} and pid =#{param3} and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public int getEventMonth(int time,int month,int pid, String areaId);

	
	@Select("select COUNT(*) as num from sev_t_alarm where pub_date >= #{param1} and area_id =#{param2} and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public int getWeekAll(String time, String areaId);

	
	@Select("select * from sev_t_alarm where pub_no is NOT null and dept_id = #{param1} and pub_state in(0,5,6) and kettle = 0 order by id desc limit 1")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "imagePath", column = "image_path")
    })
	public Alarm getNewMessage(Long deptId);
	
	
	@Select("select * from sev_t_alarm where pub_state='5' and dept_id = #{param1} and kettle != 1 ORDER BY pub_date DESC")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "alarmTypeName", column = "alarm_type_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getCompLsit(Long deptId);

	
	@Select("select * from sev_t_alarm where pub_state in (0,4,7,11) and valid = 1 and area_id = #{param2} order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListByDeaprtId(Long id, String areaId);

	
	@Select("select * from sev_t_alarm where valid =1 and pub_state in('2','9') and dept_id = #{param1} order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListAuditAlarm(Long deptId);

	
	@Select("select * from sev_t_alarm where valid =1 and pub_state in('0','7') and dept_id = #{param1} order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListWaitAuditAlarm(Long deptId);

	
	@Select("select * from sev_t_alarm where valid =1 and pub_state ='5' and dept_id = #{param1} and kettle != 1 order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListNowAlarm(Long deptId);

	
	@Select("select * from sev_t_alarm where pub_no = #{param1} and valid =1 and kettle =0")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public Alarm getAlarmByPubNo(String pubNo);

	
	@Select("select * from sev_t_alarm where id = #{param1}")
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "alarmTypeName", column = "alarm_type_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public Alarm getAlarmById(Long id);

	
	@Select("select * from sev_t_alarm where valid =1 and pub_state ='5' and area_id = #{param1} order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListNowAlarmPublish(String areaId);

	
	@Select("select * from sev_t_alarm where pub_state in('6','12') and pub_date > #{param1} and dept_id = #{param2}"
			+ " and id in (select id  from (SELECT pub_no,MAX(version) as version  FROM sev_t_alarm WHERE version IN (0,1) GROUP BY pub_no ) a left "
			+ " join sev_t_alarm b on b.pub_no=a.pub_no and b.version=a.version) order by pub_date desc")
	//@Select("select * from sev_t_alarm where pub_state in('6','12') and pub_date > #{param1} and dept_id = #{param2} order by pub_date desc")
	//插眼
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListPublishOneMonthAlarm(String dateTime,Long deptId);

	
	@Select("select * from sev_t_alarm where valid =1 and version = #{param1} and pub_no = #{param2} and kettle = 0")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path"),
			@Result(property = "operationAction", column = "LAST_ACTION"),
			@Result(property = "msgId", column = "MSG_ID"),
			@Result(property = "type", column = "type"),
			@Result(property = "typeId", column = "type_id"),
			@Result(property = "alarmTypeName", column = "alarm_type_name"),
			@Result(property = "pubChannel", column = "pub_channel"),
			@Result(property = "smsGroup", column = "sms_group"),
			@Result(property = "departFax", column = "depart_fax"),
			@Result(property = "alarmType", column = "alarm_type")
    })
	public Alarm passVersionAndCode(Integer version,String code);

	
	@Select("select * from sev_t_alarm where valid =1 and pub_state ='5' order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListNowAllAlarm();

	
	
	
	@Select("select * from sev_t_alarm where valid =1 and pub_state ='5' and area_id=1 order by pub_date desc ")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "alarmTypeName", column = "alarm_type_name"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListNowAllAlarmFormHz();

	
	@Select("select * from sev_t_alarm where pub_state in('5','12') and dept_id = #{param1} and valid =1 and trim(complete_date)!='' order by complete_date desc limit 1")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public Alarm getNewOne(Long deptId );

	
	@Select("select * from sev_t_alarm where valid =1 and pub_state in('0','7')  order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getwaitAudit();



	@Select("select publishTypeName from sev_t_alarm where id = #{param1}")
	public String getPublishType(Long id);


	// 预警发布中心登陆 可查看所有value=1
	@Select("select * from sev_t_alarm where pub_state in (0,4,7,11) and valid = 1 and area_id = #{param1} order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListByDeaprtIdHZ(String areaId);


	
	// 预警发布中心登陆 可查看所有value=1
	@Select("select * from sev_t_alarm where pub_state in (0,4,7,11) and valid = 1 and area_id = #{param1} order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "imagePath", column = "image_path")
    })
	public List<Alarm> getListByDeaprtIdAndAreaId(String areaId);
	
	
	//发布完成处于待转发状态（is_relay 0：待转发 1：已转发）
	@Select("select * from sev_t_alarm where valid =1 and pub_state IN (5,6,12) and is_relay=0 and CONCAT('%,', town_id, ',%') like CONCAT('%,', #{townId}, ',%') order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "isRelay", column = "is_relay"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path"),
			@Result(property = "townId", column = "town_id")
    })
	public List<Alarm> getListIsPublishRelay(String townId);


	@Select("select * from sev_t_alarm where valid =1 and pub_state ='5'  order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
	})
	public List<Alarm> getListIsPublishing();

    //pub_state发布成功
	@Select("select * from sev_t_alarm where valid =1 and pub_state IN (5,6,12) and is_relay=1 and CONCAT('%,', town_id, ',%') like CONCAT('%,', #{townId}, ',%')   order by pub_date asc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "isRelay", column = "is_relay"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path"),
			@Result(property = "townId", column = "town_id")
	})
	public List<Alarm> getRelaySucess(String townId);

	@Select("select * from sev_t_alarm where pub_state in('5','12')  and valid =1 and kettle= 0 and trim(complete_date)!='' order by pub_date desc limit 1 ")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path"),
			@Result(property = "alarmTypeName", column = "alarm_type_name"),
			@Result(property = "typeId", column = "type_id")
    })
	public Alarm getNewAlarm();
	
	
	@Select("select COUNT(*) as num from sev_t_alarm where DATE_FORMAT(pub_date,'%Y') = #{param1}  and pub_state in (5,6)")
	@Results(value = {
			@Result(property = "num", column = "num")
    })
	public int getAlarmYear(int time);
	
	
	
	@Select("select  *  from sev_t_alarm   where pub_state =5 and  area_id=#{param1}")
@Results(value = {
			
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path"),
			@Result(property = "alarmTypeName", column = "alarm_type_name"),
			@Result(property = "typeId", column = "type_id")
    })
	
	public List<Alarm> getAlarmByArea(String areaid);
	
	@Select("select *  from sev_t_alarm  where    (image_path='' or image_path is null) and dept_id=#{param1} and valid=1 order by create_date DESC limit 100")
	@Results(value = {
			
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path"),
			@Result(property = "alarmTypeName", column = "alarm_type_name"),
			@Result(property = "typeId", column = "type_id")
    })
	public List<Alarm> getAlarmListByDeptId(Long deptId);
	
	
	@Select("select *  from sev_t_alarm  where pub_state in (0,5) and image_path!='' and dept_id=#{param1} and valid=1 order by create_date DESC limit 100")
	@Results(value = {
			
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path"),
			@Result(property = "alarmTypeName", column = "alarm_type_name"),
			@Result(property = "typeId", column = "type_id")
    })
	public List<Alarm> getDdAlarmListByDeptId(Long deptId);
	
	@Select("select * from sev_t_alarm WHERE pub_date >(select SUBDATE(now(),interval duration HOUR) as currentBefore) and valid=1 and pub_state='5' and type='alarm'")
	@Results(value = {

				@Result(property = "pubNo", column = "pub_no"),
				@Result(property = "pubRange", column = "pub_range"),
				@Result(property = "pubRangeName", column = "pub_range_name"),
				@Result(property = "pubState", column = "pub_state"),
				@Result(property = "pubDate", column = "pub_date"),
				@Result(property = "auditDate", column = "audit_date"),
				@Result(property = "releaseDate", column = "release_date"),
				@Result(property = "completeDate", column = "complete_date"),
				@Result(property = "checkContent", column = "check_content"),
				@Result(property = "imagePath", column = "image_path"),
			@Result(property = "deptId", column = "dept_id"),
				@Result(property = "alarmTypeName", column = "alarm_type_name"),
				@Result(property = "typeId", column = "type_id")
	    })
	public List<Alarm> getPublishAlarm();



	//转发完成之后is_relay 0： 1：已转发）
	@Select("update sev_t_alarm set is_relay = 1 where id = #{param1}")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "isRelay", column = "is_relay"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
	})
	public Integer getUpdateIsPublishRelay(Long id);



	//pub_state发布成功,通过id查询(查询出转发的所有信息)
	@Select("select * from sev_t_alarm where valid =1 and pub_state ='5' and is_relay=1 id=  #{param1}  order by pub_date desc")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "isRelay", column = "is_relay"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
	})
	public Alarm getRelaySucessAll(Long id);


	//根据id查询预警的所有信息
	@Select("select * from sev_t_alarm where valid =1 and id=  #{param1}  ")
	@Results(value = {
			@Result(property = "deptId", column = "dept_id"),
			@Result(property = "pubNo", column = "pub_no"),
			@Result(property = "pubRange", column = "pub_range"),
			@Result(property = "pubRangeName", column = "pub_range_name"),
			@Result(property = "pubState", column = "pub_state"),
			@Result(property = "isRelay", column = "is_relay"),
			@Result(property = "pubDate", column = "pub_date"),
			@Result(property = "auditDate", column = "audit_date"),
			@Result(property = "releaseDate", column = "release_date"),
			@Result(property = "completeDate", column = "complete_date"),
			@Result(property = "checkContent", column = "check_content"),
			@Result(property = "imagePath", column = "image_path")
	})
	public Alarm getRelayAlarm(Long id);

	//查询预警级别列表
	@Select("select distinct alarm_type_name FROM sev_t_alarm")
	@Results(value = {
			@Result(property = "alarmTypeName", column = "alarm_type_name")
	})
	List<String> selectAlarmType();
	
}
