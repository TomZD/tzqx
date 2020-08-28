package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by allenwc on 15/9/10.
 */
public interface UserMapper extends Mapper<User>{
	
	@Select("select distinct(key_name) from sys_dict where valid='1'")
    public List<String> getKeyNames();
	
	@Select("select * from sys_user where name = #{name}")
	public User selectUserByName(String name);


	@Select("select * from sys_user where id =  #{param1}")
	@Results(value={

			@Result(property = "departmentId", column = "department_id"),

	})
	public Long selectDepartmentId(Long id);


}
