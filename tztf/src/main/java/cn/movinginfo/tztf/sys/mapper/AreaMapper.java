package cn.movinginfo.tztf.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.Area;
import cn.movinginfo.tztf.sys.domain.Depart;
import tk.mybatis.mapper.common.Mapper;

public interface AreaMapper extends Mapper<Area>{
	
	@Select("select * from sys_area where CITY='杭州市'")
    public List<Area> getAll();
	
	@Select("SELECT DISTINCT COUNTRY,abb FROM sys_area where CITY='黄岩市' and COUNTRY=TOWN and valid=1")
    public List<Area> getCountryArea();
	
	@Select("SELECT TOWN FROM sys_area where COUNTRY=#{param1} and COUNTRY!=TOWN")
    public List<String> selectCountry(String country);
	
	@Select("SELECT * FROM sys_area where area_id=#{param1} and COUNTRY!=TOWN and valid=1")
    public List<Area> selectCountryByAreaId(String areaId);
	
	@Select("SELECT DISTINCT country FROM sys_area ORDER BY area_id")
    public List<String> getCountryData();
	
	@Select("SELECT DISTINCT town FROM sys_area where COUNTRY=#{param1} and COUNTRY!=town")
    public List<String> getTownData(String country);
	
	@Select("SELECT * from sys_area where town =#{town} and valid=1")
    public Area getAreaByTown(@Param("town") String town);

    @Select("SELECT * FROM sys_area where valid=1")
    public List<Area> getTownList();

    @Select("SELECT * FROM sys_area where id=#{areaId} and valid=1")
    public Area selectByAreaId(Long areaId);

}
