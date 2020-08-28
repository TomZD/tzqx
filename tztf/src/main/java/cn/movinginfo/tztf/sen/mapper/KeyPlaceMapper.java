package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.KeyPlace;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface KeyPlaceMapper extends Mapper<KeyPlace> {

    @Select("Select * from sen_t_key_place where name=#{name} and type_id=#{typeId} and valid=1")
    public KeyPlace getKeyPlaceByNameAndTypeId(@Param("name") String name, @Param("typeId") Long typeId);

    @Select("SELECT * FROM sen_t_key_place WHERE type_id =#{typeId} and valid=1 LIMIT 1")
    @Results(value = {
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "hospitalLevel", column = "hospital_level"),
            @Result(property = "hospitalBed", column = "hospital_bed"),
            @Result(property = "schoolSize", column = "school_size")
    })
    public KeyPlace getFirstKeyPlaceByTypeId(@Param("typeId") Long typeId);

    @Select("SELECT * FROM sen_t_key_place WHERE name =#{name} and valid=1 ")
    public KeyPlace selectByName(@Param("name") String name);

    @Select("SELECT * FROM sen_t_key_place WHERE type_id=4 and valid=1 ")
    public List<KeyPlace> selectList();
}
