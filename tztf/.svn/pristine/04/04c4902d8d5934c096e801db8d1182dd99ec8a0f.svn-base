package cn.movinginfo.tztf.sev.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.PGroup;
import tk.mybatis.mapper.common.Mapper;

public interface PGroupMapper extends Mapper<PGroup> {

   //存入信息
    @Select("insert into sys_p_group values(dept_id = #{param1} , pub_no = 5,pub_range_name = #{param2}, pub_state =  #{param3}, sms_group=#{param4}, )")
    @Results(value = {
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "pubNo", column = "pub_no"),
            @Result(property = "pubRange", column = "pub_range"),
            @Result(property = "pubRangeName", column = "pub_range_name"),
            @Result(property = "pubState", column = "pub_state"),
            @Result(property = "pubDate", column = "pub_date"),
            @Result(property = "auditDate", column = "audit_date"),
            @Result(property = "releaseDate", column = "release_date"),
            @Result(property = "completeDate", column = "complete_date"),
            @Result(property = "checkContent", column = "check_content"),
            @Result(property = "smsGroup", column = "sms_group"),
            @Result(property = "imagePath", column = "image_path")
    })
    public Integer Save(PGroup pGroup);


}
