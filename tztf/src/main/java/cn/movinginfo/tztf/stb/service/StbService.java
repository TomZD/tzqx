package cn.movinginfo.tztf.stb.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.stb.mapper.StbMapper;
import cn.movinginfo.tztf.stb.model.Stb;



@Component
public class StbService {
	@Autowired
	private StbMapper stbMapper;

	public String getSwitchStatus(String equipmentCode, boolean status, double lat, double lon, String area) {
		JSONObject result=new JSONObject();
		Date now =new Date();
		Stb stb=stbMapper.selectByPrimaryKey(equipmentCode);
		if(stb==null){
			Stb newstb=new Stb();
			newstb.setEquipmentcode(equipmentCode);
			newstb.setCreateDate(new Date());
			newstb.setSwitchTime("08:00-18:00");
			newstb.setSwitchModel(0);
			stbMapper.insertSelective(newstb);
		}
		stb=stbMapper.selectByPrimaryKey(equipmentCode);
		stb.setConnectStatus(true);
		stb.setSwitchStatus(status);
		stb.setUpdateDate(new Date());
		stb.setLat(lat);
		stb.setLon(lon);
		stb.setArea(area);
		stbMapper.updateByPrimaryKeySelective(stb);
		int model=stb.getSwitchModel();
		if(model==0){
			stb.setSwitchStatus(getStatus(stb.getSwitchTime().split("-")));
		}else if(model==1){
			stb.setSwitchStatus(true);
		}else if(model==2){
			stb.setSwitchStatus(false);
		}
		if(stb.getSwitchStatus()){
			result.put("status", "OPEN_ALL");
		}else{
			result.put("status", "CLOSE_ALL");
		}
//		result.put("version", "1.1.1");
		result.put("id",equipmentCode);
		result.put("result", "sucess");
		return result.toJSONString();
		
	}


	public void channelInactive(String equipmentCode) {
		Stb stb=new Stb();
		stb.setEquipmentcode(equipmentCode);
		stb.setConnectStatus(false);
		stbMapper.updateByPrimaryKeySelective(stb);
		System.out.println("机顶盒"+equipmentCode+"断开连接");
	}
	
	 /**根据时间判断开关机状态
	 * @param time
	 * @return
	 */
	public boolean  getStatus (String []time){
	    	SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	    	String now=format.format(new Date());
	    	int nowtime=Integer.parseInt(now.split(":")[0])*60+Integer.parseInt(now.split(":")[1]);
	    	int first=Integer.parseInt(time[0].split(":")[0])*60+Integer.parseInt(time[0].split(":")[1]);
	    	int last=Integer.parseInt(time[1].split(":")[0])*60+Integer.parseInt(time[1].split(":")[1]);
	    	if(nowtime>first&&nowtime<last){
	    		return true;
	    	}
	    	return false;
	    }

}
