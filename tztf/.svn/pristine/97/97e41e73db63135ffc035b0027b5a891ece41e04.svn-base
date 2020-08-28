package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.PeopleGroup;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PeopleGroupMapper extends Mapper<PeopleGroup> {

    @Select("select  i.name, i.depart, i.job, i.address, i.phone,g.name  from sys_people_group  g left join sys_emergency_information i on i.group_id = g.id ")
    @Results(value={

            @Result(property = "name", column = "name"),
            @Result(property = "depart", column = "depart"),
            @Result(property = "phone", column = "phone"),
    })
    public List<PeopleGroup> getListGroup();



    @Select("select * from sys_people_group where id =  #{param1}  ")
    @Results(value={

            @Result(property = "name", column = "name"),
            @Result(property = "depart", column = "depart"),
            @Result(property = "phone", column = "phone"),
    })
    public PeopleGroup getQueryGroup(Long id);

    @Select("select * from sys_people_group where valid = 1 ")
    @Results(value={

            @Result(property = "name", column = "name"),
            @Result(property = "department", column = "department"),
            @Result(property = "phone", column = "phone"),
    })
    public List<PeopleGroup> getQueryGroupAll();

}
