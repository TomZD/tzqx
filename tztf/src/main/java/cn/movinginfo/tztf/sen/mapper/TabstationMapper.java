package cn.movinginfo.tztf.sen.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.Tabstation;
import tk.mybatis.mapper.common.Mapper;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface TabstationMapper extends Mapper<Tabstation>{
	
	@Select("select * from sen_t_tabstation where iiiii=#{iiiii} and valid=1")
	@Results(value = {
			@Result(property = "stationName", column = "station_name")
	})
	public Tabstation getTabstation(@Param("iiiii")String iiiii);
}
