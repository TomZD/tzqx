package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.People;
import tk.mybatis.mapper.common.Mapper;

public interface peopleMapper extends Mapper<People>{
	
	@Select("select * from sys_people where id != #{param1} and phone=#{param2}")
	public People findPeopleByNotIdAndPhone(Long id,String phone);
}
