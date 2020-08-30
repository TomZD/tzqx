package cn.movinginfo.tztf.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sys.domain.AlarmType;
import tk.mybatis.mapper.common.Mapper;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface AlarmTypeMapper extends Mapper<AlarmType>{

	/**
	 * @Description: TODO 获取部门设置的气象预警类型
	 * @Class: cn.movinginfo.tztf.sys.mapper.AlarmTypeMapper
	 * @Title: getDistinctList
	 * @param deptId 部门ID
	 * @return List<AlarmType>
	 * @author: zhangdi
	 * @createTime: 2020-8-30 15:25:02
	 * @updateTime: 
	 * @throws 
	 */
	@Select("SELECT DISTINCT AT .namecn FROM sm_r_department_envent_type det JOIN sm_t_event_type et ON et.valid = 1 AND det.event_type_id = et.id AND et.alarm_name IS NOT NULL AND det.department_id = #{param} JOIN sys_alarm_type AT ON AT .valid = 1 AND et.alarm_name = AT . NAME WHERE det.valid = 1")
	public  List<AlarmType> getDistinctAlarmNameType(Long deptId);
	
	@Select("select distinct namecn from sys_alarm_type")
	public  List<AlarmType> getDistinctList();
	
	@Select("select *  from sys_alarm_type where caption=#{param}")
	public  AlarmType getByCaption(String caption);
	
}
