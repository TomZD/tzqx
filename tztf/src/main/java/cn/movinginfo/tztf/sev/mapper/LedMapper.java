package cn.movinginfo.tztf.sev.mapper;


import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Led;
import tk.mybatis.mapper.common.Mapper;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface LedMapper extends Mapper<Led>{
	
	@Select("UPDATE sev_t_led SET status =#{param1},update_date=now() where code = #{param2}")
	public void updateStatus(Integer status,String code);
	
}
