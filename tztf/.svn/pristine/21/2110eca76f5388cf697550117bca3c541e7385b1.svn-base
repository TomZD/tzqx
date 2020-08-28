package cn.movinginfo.tztf.sm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sm.domain.EventType;
import tk.mybatis.mapper.common.Mapper;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface EventTypeMapper extends Mapper<EventType>{
	
	@Select("SELECT * FROM sm_t_event_type WHERE valid = 1 AND display_name = #{eventTypeThirds}" )
	public EventType selectUpdateTypes(String eventTypeThirds);

		
	@Select("select p.national_code from sm_t_event_type p where p.display_name = #{eventTypeFirsts}")
	public String selectClickTypes(String eventTypeFirsts);

	@Select("select * from sm_t_event_type where valid = 1 AND national_code = #{nationalCode}")
	public EventType selectCode(String nationalCode);
	
}
