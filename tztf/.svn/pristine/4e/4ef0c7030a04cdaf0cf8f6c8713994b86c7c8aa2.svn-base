package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.Depart;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by allenwc on 15/9/10.
 */
public interface DeptMapper extends Mapper<Depart> {
	
	@Select("select * from sys_t_department where NAME LIKE'%发布中心%'  and valid='1' and area_id = #{areaId}")
   Depart getPublisher(String areaId);
   
    @Select("select * from sys_t_department where dept_type='2'  and valid='1' and area_id = #{areaId}")
   Depart getDepartByAreaId(String areaId);
    
    @Select("select * from sys_t_department where NAME = (SELECT `NAME` FROM sys_release_channel WHERE ID=#{channelId})")
    Depart getPublishDept(Long channelId);
    
    @Select("select id from sys_t_department where name like #{name} and valid = 1")
    List<String> getIdByName(@Param("name")String name);

    @Select("select * from sys_t_department where valid = 1")
    @Results(value={
            @Result(property = "name", column = "NAME"),
            @Result(property = "phone", column = "PHONE"),
            @Result(property = "fax", column = "FAX"),
            @Result(property = "linker", column = "LINKER"),
            @Result(property = "address", column = "ADDRESS")
    })
    List<Depart> getDepartAll();


 @Select("select * from sys_t_department where id =  #{param1}")
 @Results(value={
         @Result(property = "name", column = "NAME"),
         @Result(property = "phone", column = "PHONE"),
         @Result(property = "fax", column = "FAX"),
         @Result(property = "linker", column = "LINKER"),
         @Result(property = "address", column = "ADDRESS")
 })
        Depart getDepart(Long id);


 @Select("select * from sys_t_department where dept_type =3")
 @Results(value={
         @Result(property = "name", column = "NAME"),
         @Result(property = "phone", column = "PHONE"),
         @Result(property = "fax", column = "FAX"),
         @Result(property = "linker", column = "LINKER"),
         @Result(property = "address", column = "ADDRESS"),
         @Result(property = "deptType", column = "dept_type")
 })
 List<Depart> getDepartName();
}
