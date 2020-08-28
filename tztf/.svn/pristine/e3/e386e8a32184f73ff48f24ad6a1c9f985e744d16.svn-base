package cn.movinginfo.tztf.stb.socket;

import io.netty.channel.ChannelHandlerContext;

import java.net.URL;

import javax.annotation.PostConstruct;

import net.ryian.core.utils.SpringContextUtil;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.stb.model.Stb;
import cn.movinginfo.tztf.stb.service.StbService;



@Component
public class StbConnectHandler {
//	@Autowired
//	private StbService stbService;
	
	 private StbService stbService = SpringContextUtil.getBean("stbService");
	
	private String baseurl=StbConUtil.getValue("BASE_URL");
	private String show_url=StbConUtil.getValue("SHOW_URL");
	
//	public static StbConnectHandler stbConnectHandler;
//	
//	public StbConnectHandler(){
//		
//	}
//	@PostConstruct
//	public void init(){
//		stbConnectHandler=this;
//		stbConnectHandler.stbService=this.stbService;
//	}
	
	//心跳
	public String stbSwitchStatus(ChannelHandlerContext ctx, Stb stb) {
//		String url = baseurl + "/getSwitchStatus?equipmentCode="+stb.getEquipmentcode()+"&status="+stb.getSwitchStatus()+"&lat="+stb.getLat()+"&lon="+stb.getLon()+"&area="+stb.getArea();

//		String result="";
//		stbService=new StbService();
		String result=stbService.getSwitchStatus(stb.getEquipmentcode(), stb.getSwitchStatus(), stb.getLat(), stb.getLon(), stb.getArea());
		try {
//			 result = IOUtils.toString(new URL(url),"UTF-8");
			 result= JSONAddUrl(result);
//			 System.out.println("返回数据:"+result);
			 ctx.write(result);
         	ctx.flush();
			
		} catch (Exception e) {
			ctx.write(getErr("300"));
        	ctx.flush();
			//e.printStackTrace();
		}
		return result;
	}
	//返回json加上url
	private String JSONAddUrl(String result) {
		JSONObject jsonStr = JSONObject.parseObject(result);
		jsonStr.put("show_url", show_url);
		jsonStr.put("version", Integer.valueOf(StbConUtil.getValue("VERSION")));
		jsonStr.put("content", "");
		jsonStr.put("update_url", StbConUtil.getValue("UPDATE_URL"));
		return jsonStr.toJSONString();
	}

	public void stbDisconnected(ChannelHandlerContext ctx,String equipmentCode) {
//		String url = baseurl + "/channelInactive?equipmentCode="+equipmentCode;
		try {
//			stbService=new StbService();
//			 String result = IOUtils.toString(new URL(url),"UTF-8");
			stbService.channelInactive(equipmentCode);
		} catch (Exception e) {
			ctx.write(getErr("300"));
        	ctx.flush();
//			e.printStackTrace();
		}

	}
	
	public String getErr(String err){
    	JSONObject jsonStr = new JSONObject();
    	jsonStr.put("result", "fail");
    	jsonStr.put("err", err);
    	return jsonStr.toJSONString();
    }
	
	


}
