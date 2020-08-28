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
	@Select("select distinct namecn from sys_alarm_type")
	public  List<AlarmType> getDistinctList();
	
	@Select("select *  from sys_alarm_type where caption=#{param}")
	public  AlarmType getByCaption(String caption);
}
