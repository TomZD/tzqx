package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.DangerPoint;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface DangerPointMapper extends Mapper<DangerPoint>{
    //查询所有隐患点
    @Select("SELECT * FROM sen_t_danger_point")
    public List<DangerPoint> selectAll();
    
    @Select("select * from sen_t_danger_point where CONCAT(',',rel_station,',') LIKE concat('%,',#{relStation},',%') AND valid=1")
    @Results(value = {
    		@Result(property = "historicalDisaster", column = "historical_disaster"),
    		@Result(property = "dangerTypeId", column = "danger_type_id"),
            @Result(property = "relStation", column = "rel_station"),
            @Result(property = "alertruleId", column = "alertrule_id"),
            @Result(property = "alertruleTwoId", column = "alertruleTwo_id"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
            @Result(property = "affectTown", column = "affect_town"),
            @Result(property = "watershedPopulation", column = "watershed_population"),
            @Result(property = "riverLength", column = "river_length"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
    })
	public List<DangerPoint> getDangerPointByRelStation(@Param("relStation")String relStation);
    
    @Select("Select * from sen_t_danger_point where longitude=#{longitude} and latitude=#{latitude} and danger_type_id=#{dangerTypeId} and valid=1")
    @Results(value = {
    		@Result(property = "historicalDisaster", column = "historical_disaster"),
    		@Result(property = "dangerTypeId", column = "danger_type_id"),
            @Result(property = "relStation", column = "rel_station"),
            @Result(property = "alertruleId", column = "alertrule_id"),
            @Result(property = "alertruleTwoId", column = "alertruleTwo_id"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
            @Result(property = "affectTown", column = "affect_town"),
            @Result(property = "watershedPopulation", column = "watershed_population"),
            @Result(property = "riverLength", column = "river_length"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
    })
    DangerPoint getDangerPointByLonLatTypeId(@Param("longitude")String longitude,@Param("latitude")String latitude,@Param("dangerTypeId")Long dangerTypeId);
    
    @Select("SELECT * FROM sen_t_danger_point WHERE danger_type_id =#{dangerTypeId} and valid=1 LIMIT 1")
	@Results(value = {
			@Result(property = "historicalDisaster", column = "historical_disaster"),
    		@Result(property = "dangerTypeId", column = "danger_type_id"),
            @Result(property = "relStation", column = "rel_station"),
            @Result(property = "alertruleId", column = "alertrule_id"),
            @Result(property = "alertruleTwoId", column = "alertruleTwo_id"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
            @Result(property = "affectTown", column = "affect_town"),
            @Result(property = "watershedPopulation", column = "watershed_population"),
            @Result(property = "riverLength", column = "river_length"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
    })
	public DangerPoint getFirstDangerPointByDangerTypeId(@Param("dangerTypeId")Long dangerTypeId);



    @Select("SELECT * FROM sen_t_danger_point WHERE danger_type_id =#{dangerTypeId} and valid=1 LIMIT 1")
    @Results(value = {
            @Result(property = "historicalDisaster", column = "historical_disaster"),
            @Result(property = "dangerTypeId", column = "danger_type_id"),
            @Result(property = "relStation", column = "rel_station"),
            @Result(property = "alertruleId", column = "alertrule_id"),
            @Result(property = "alertruleTwoId", column = "alertruleTwo_id"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
            @Result(property = "affectTown", column = "affect_town"),
            @Result(property = "watershedPopulation", column = "watershed_population"),
            @Result(property = "riverLength", column = "river_length"),
            @Result(property = "alertruleThreeId", column = "alertruleThree_id"),
    })
    public DangerPoint save(DangerPoint dangerPoint);


}
