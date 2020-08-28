package cn.movinginfo.tztf.sen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.AreaAlarm;
import tk.mybatis.mapper.common.Mapper;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface AreaalarmMapper extends Mapper<AreaAlarm>{
	
	@Select("select * from bj_areaalarm where CONCAT(',',rel_station,',') LIKE concat('%,',#{relStation},',%') AND valid=1")
	@Results(value = {
            @Result(property = "relStation", column = "rel_station"),
            @Result(property = "alertruleId", column = "alertrule_id"),
            @Result(property = "alertruleTwoId", column = "alertruleTwo_id"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
    })
	public List<AreaAlarm> getAreaAlarmByRelStation(@Param("relStation")String relStation);
}
