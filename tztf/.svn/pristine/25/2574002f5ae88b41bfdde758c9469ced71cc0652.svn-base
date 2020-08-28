package cn.movinginfo.tztf.dd.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.movinginfo.tztf.sys.service.AreaService;

/**
*
* @description:责任区信息模块
* @author: autoCode
* @history:
*/

@Controller
@RequestMapping("/dd/areaInfo")
public class AreaInfoAction {
	@Autowired
	private AreaService areaService;
	
	
	
	
	@RequestMapping(value="getCountryData")
	@ResponseBody
	public String getCountryData(HttpServletRequest request, HttpServletResponse response){
		
		JSONArray json=areaService.getCountryData();
		response.setHeader("Access-Control-Allow-Origin", "*");
		String result =   json.toJSONString();
		return result;
	}
	
	@RequestMapping(value="getTownData")
	@ResponseBody
	public String getTownData(HttpServletRequest request, HttpServletResponse response,String country){
//		try {
//			country=new String(country.getBytes("ISO-8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		JSONArray json=areaService.getTownData(country);
		response.setHeader("Access-Control-Allow-Origin", "*");
		String result =   json.toJSONString();
		return result;
	}

}
