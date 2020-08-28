package cn.movinginfo.tztf.sys.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;

import cn.movinginfo.tztf.sys.domain.Area;
import cn.movinginfo.tztf.sys.mapper.AreaMapper;
import net.ryian.orm.service.BaseService;
@Component
public class AreaService extends BaseService<Area,AreaMapper>{
	
	public List<Area> getCountryArea(){
		List<Area> list =  mapper.getCountryArea();
		return list;
	}
	
	public List<Area> getCountryAreaById(String areaId){
		List<Area> list =  mapper.selectCountryByAreaId(areaId);
		return list;
	}

	public JSONArray getCountryData() {
		List<String> list =  mapper.getCountryData();
		JSONArray countrys=new JSONArray();
		for(String str:list){
			countrys.add(str);
		}
		return countrys;
	}

	public JSONArray getTownData(String country) {
		List<String> list =  mapper.getTownData(country);
		JSONArray towns=new JSONArray();
		for(String str:list){
			towns.add(str);
		}
		return towns;
	}
	
	public Area getAreaByTown(String town) {
		return mapper.getAreaByTown(town);
	}

	public List<Area> getTownList(){
		List<Area> list =  mapper.getTownList();
		return list;
	}

	public Area selectByAreaId(Long areaId) {
		return mapper.selectByAreaId(areaId);
	}
	
}
