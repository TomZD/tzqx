package cn.movinginfo.tztf.stb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.stb.service.StbService;






@Controller
@RequestMapping("stb")
public class StbController {
	@Autowired
	private StbService stbService;
	
	/**1.获得机顶盒开关机状态并返回正确状态（心跳）
	 * 2.更新连接状态
	 * 3.新的id需自动注册
	 * @param equipmentCode
	 * @param status
	 */
	@RequestMapping(value = "/getSwitchStatus")
	@ResponseBody
	public String getSwitchStatus(String equipmentCode,boolean status,double lat,double lon,String area){
		return stbService.getSwitchStatus(equipmentCode,status,lat,lon,area);
		
	}
	
	/**机顶盒连接关闭
	 * @param equipmentCode
	 */
	@RequestMapping(value = "/channelInactive")
	@ResponseBody
	public void channelInactive(String equipmentCode){
		stbService.channelInactive(equipmentCode);
		
	}

}
