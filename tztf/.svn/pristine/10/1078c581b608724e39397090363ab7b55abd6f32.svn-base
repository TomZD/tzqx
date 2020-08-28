package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.domain.UserXt;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Author: ZhangKX
 * Created by Administrator on 2019/10/29.
 * Reamrk:
 */
public interface UserXtMapper extends Mapper<UserXt> {

    @Select("select distinct(key_name) from sys_dict where valid='1'")
    public List<String> getKeyNames();

    @Select("select * from sys_user_xt where name = #{name}")
    public UserXt selectUserByName(String name);


    @Select("select * from sys_user_xt where user_name = #{name}")
    public UserXt selectUserByUserName(String name);

    @Select("select * from sys_user_xt where id =  #{param1}")
    @Results(value={

            @Result(property = "departmentId", column = "department_id"),

    })
    public Long selectDepartmentId(Long id);

}
