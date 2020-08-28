package cn.movinginfo.tztf.ser.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.ser.domain.HuasuHotel;
import cn.movinginfo.tztf.ser.domain.HuasuHotelExample;
import cn.movinginfo.tztf.ser.mapper.HuasuHotelMapper;


@Service
public class HuasuHotelService {
	@Autowired
	private HuasuHotelMapper huasuHotelMapper;
	
	public List<HuasuHotel> getHuasuHotel() {
		HuasuHotelExample example = new HuasuHotelExample();
		return huasuHotelMapper.selectByExample(example);
	}

}
