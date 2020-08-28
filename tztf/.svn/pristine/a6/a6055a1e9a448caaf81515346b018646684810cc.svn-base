package cn.movinginfo.tztf.ser.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.ser.domain.HuasuHotel;
import cn.movinginfo.tztf.ser.service.HuasuHotelService;

@Controller
@RequestMapping("/ser/hotel")
public class HuasuHotelAction {
	
	@Autowired
	private HuasuHotelService huasuHotelService;
	
	/**
	 * 华数电视
	 * @return
	 */
	@RequestMapping("getHotel")
	@ResponseBody
	public  List<HuasuHotel> getHuasuHotel(){
		List<HuasuHotel> result = huasuHotelService.getHuasuHotel();
		return result;
		
	}

}
