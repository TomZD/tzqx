package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.EmergencyInformation;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface EmergencyInformationMapper extends Mapper<EmergencyInformation>{
    @Select("select i.id, i.name, i.depart, i.job, i.address, i.phone,g.name as groupName ,d.name as departName,d.id from sys_people_group  g left join sys_emergency_information i on i.group_id = g.id left join sys_t_department d on d.id = i.depart_id where i.depart_id =#{depart_id}")


    @Results(value={
            @Result(property = "name", column = "name"),
            @Result(property = "depart", column = "depart"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "departid", column = "depart_id"),
    })
    public List<EmergencyInformation> getListGroup(Long departid);



    //根据departmentid
    @Select("select * from sys_emergency_information where departid = #{depart_id}  ")
    @Results(value={

            @Result(property = "departid", column = "depart_id"),
    })
    public List<EmergencyInformation> getEmergencyInfo(Long departid);


    //根据groupid去查找对应得群组人员
    @Select("select * from sys_emergency_information where group_id= #{group_id}  ")
    @Results(value={

            @Result(property = "departid", column = "depart_id"),
            @Result(property = "groupId", column = "group_id"),
    })
    public List<EmergencyInformation> getEmergencyInfoAll(Long groupId);





    //根据groupid去查找对应得群组人员
    @Select("delete from sys_emergency_information where id= #{id}  ")
    @Results(value={

            @Result(property = "departid", column = "depart_id"),
            @Result(property = "groupId", column = "group_id"),
    })
    public EmergencyInformation deleteEmergencyInfo(Long id);




}
