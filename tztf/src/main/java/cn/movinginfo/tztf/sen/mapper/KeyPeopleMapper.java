package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.KeyPeople;
import tk.mybatis.mapper.common.Mapper;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface KeyPeopleMapper extends Mapper<KeyPeople>{
	@Select("select * from sen_t_key_people where person_type_id = #{personTypeId} and phone = #{phone} and valid =1")
	KeyPeople getKeyPeopleByPhoneAndType(@Param("personTypeId")Long personTypeId,@Param("phone")String phone);
	
	@Select("select * from sen_t_key_people where person_type_id = #{personTypeId} and name = #{name} and valid =1")
	KeyPeople getKeyPeopleByNameAndType(@Param("personTypeId")Long personTypeId,@Param("name")String name);
	
	@Select("SELECT * FROM sen_t_key_people WHERE person_type_id =#{personTypeId} and valid=1 LIMIT 1")
	@Results(value = {
            @Result(property = "personTypeId", column = "person_type_id"),
            @Result(property = "homeAddress", column = "home_address"),
            @Result(property = "areaCode", column = "area_code"),
            @Result(property = "departCode", column = "depart_code"),
            @Result(property = "administrationCode", column = "administration_code"),
            @Result(property = "networkName", column = "network_name")
    })
	public KeyPeople getFirstKeyPeopleByTypeId(@Param("personTypeId")Long personTypeId);




	@Select("SELECT * FROM sen_t_key_people WHERE id=#{personTypeId} and valid=1 LIMIT 1")
	@Results(value = {
			@Result(property = "personTypeId", column = "person_type_id"),
			@Result(property = "homeAddress", column = "home_address"),
			@Result(property = "areaCode", column = "area_code"),
			@Result(property = "departCode", column = "depart_code"),
			@Result(property = "administrationCode", column = "administration_code"),
			@Result(property = "networkName", column = "network_name")
	})
	public KeyPeople getQueryPerson(Long id);
}
