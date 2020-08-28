package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.FacilityEquipment;
import tk.mybatis.mapper.common.Mapper;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface FacilityEquipmentMapper extends Mapper<FacilityEquipment>{
	
	@Select("select * from sen_t_facility_equipment where code=#{code} and equipment_id=#{equipmentId} and valid=1")
	public FacilityEquipment getFacilityEquipmentByCodeAndEquipmentId(@Param("code")String code,@Param("equipmentId")Long equipmentId);
	
	@Select("SELECT * FROM sen_t_facility_equipment WHERE equipment_id =#{equipmentId} and valid=1 LIMIT 1")
	@Results(value = {
            @Result(property = "equipmentId", column = "equipment_id"),
            @Result(property = "serverCode", column = "server_code"),
            @Result(property = "originalCode", column = "original_code"),
            @Result(property = "manufacturerCode", column = "manufacturer_code"),
            @Result(property = "departCode", column = "depart_code")
    })
	public FacilityEquipment getFirstFacilityEquipmentByEquipmentId(@Param("equipmentId")Long equipmentId);
}
