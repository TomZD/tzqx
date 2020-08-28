package cn.movinginfo.tztf.sev.action;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sev.domain.Led;
import cn.movinginfo.tztf.sev.service.LedService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

/**
*
* @description:Led action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/sev/led")
@SuppressWarnings("serial")
public class LedAction extends MagicAction<Led,LedService> {
	private static Logger log = LoggerFactory.getLogger(LedAction.class);
	
	@Autowired
	private LedService ledService;

	@RequestMapping(value = "updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		try{
			String code = request.getParameter("code");
			String status = request.getParameter("status");
			if(!StringUtils.isEmpty(code)&&!StringUtils.isEmpty(status)){
				entityService.updateStatus(code, status);
			}
			jsonObject.put("result", true);
			jsonObject.put("message", "更新成功！");
		}catch(Exception e){
			log.error("updateStatus", e);
			jsonObject.put("result", false);
			jsonObject.put("message", "更新失败，请联系管理员！");
		}
		printJson(response, JSON.toJSONString(jsonObject));
		
	}
	
	@RequestMapping(value = "updateAllStatus")
	public void updateAllStatus(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		try{
			String code = request.getParameter("codes");
			if(!StringUtils.isEmpty(code)){
				String[] codes = code.split(",");
				entityService.updateAllStatus(codes);
				jsonObject.put("result", true);
				jsonObject.put("message", "更新成功！");
			}
		}catch(Exception e){
			log.error("updateStatus", e);
			jsonObject.put("result", false);
			jsonObject.put("message", "更新失败，请联系管理员！");
		}
		
		printJson(response, JSON.toJSONString(jsonObject));
	}

	@Override
	protected void beforeIndex(HttpServletRequest request, Model model) {
		PageInfo<?> pageInfo = entityService.queryPaged(getParameterMap(request));
		List<Led>  ledList=  (List<Led>) pageInfo.getList();
		for(Led led : ledList) {
			System.out.println(led);	                          
		}
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page",page);
		
		super.beforeIndex(request, model);
	}
	
	
	@RequestMapping(value = "del")
	public String del(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/sev/led";
	}

	@RequestMapping(value = "index")
	public String beforeQuery(HttpServletRequest request, Model model) throws Exception {
		// 获取数据
		PageInfo<?> pageInfo = entityService.queryPaged(getParameterMap(request));
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page", page);
		return getNameSpace() + "index";
	}
	
	@RequestMapping("leds")
	@ResponseBody
	public List<Led> getLed() {
		 List<Led> leds = ledService.getSevLed();
		   return leds;
	}
	
	@RequestMapping("trafficLeds")
	@ResponseBody
	public List<Led> getTrafficLeds() {
		 List<Led> leds = ledService.getTrafficLeds();
		   return leds;
	}
	
	@RequestMapping("busLeds")
	@ResponseBody
	public List<Led> getBusLeds() {
		 List<Led> leds = ledService.getBusLeds();
		   return leds;
	}
	
	@RequestMapping("metroLeds")
	@ResponseBody
	public List<Led> getMetroLeds() {
		 List<Led> leds = ledService.getMetroLeds();
		   return leds;
	}
	
	@RequestMapping("schoolLeds")
	@ResponseBody
	public List<Led> getSchoolLeds() {
		 List<Led> leds = ledService.getSchoolLeds();
		   return leds;
	}
	
	@RequestMapping("cinemaLeds")
	@ResponseBody
	public List<Led> getCinemaLeds() {
		 List<Led> leds = ledService.getCinemaLeds();
		   return leds;
	}
	
	@RequestMapping("weatherLeds")
	@ResponseBody
	public List<Led> getWeatherLeds() {
		 List<Led> leds = ledService.getWeatherLeds();
		   return leds;
	}
	
	@RequestMapping("getLeds")
	@ResponseBody
	public Map<String,Float> getLeds() {
		 List<Led> leds = ledService.getSevLed();
		 List<Led>  connectedLeds = ledService.getConnectedLed();
		 List<Led> ununitedLeds = ledService.getUnunitedLed();
		 int num1 = connectedLeds.size();
		 int num2 = ununitedLeds.size();
		 int num3 = leds.size();
		 DecimalFormat df=new DecimalFormat("0.0");
		 float connectedNum = Float.parseFloat(df.format((float)num1/num3*100));
		 float ununitedNum = Float.parseFloat(df.format((float)num2/num3*100));
		 float unusualNum = Float.parseFloat(df.format((1-(float)num2/num3-(float)num1/num3)*100));
		 Map<String, Float> map = new HashMap<String, Float>();
		 map.put("type1", connectedNum);
		 map.put("type2", ununitedNum);
		 map.put("type3", unusualNum);
		 return map;
	}
	
	@RequestMapping(value = "channelState")
	public String getLedNums(HttpServletRequest request, Model model) throws Exception {
		List<Led> trafficLeds = ledService.getTrafficLeds();
	    int trafficLedNum = trafficLeds.size();
	    List<Led> busLeds = ledService.getBusLeds();
	    int busLedNum = busLeds.size();
	    List<Led> metroLeds = ledService.getMetroLeds();
	    int metroLedNum = metroLeds.size();
	    List<Led> schoolLeds = ledService.getSchoolLeds();
	    int schoolLedNum = schoolLeds.size();
	    List<Led> cinemaLeds = ledService.getCinemaLeds();
	    int cinemaLedNum = cinemaLeds.size();
	    List<Led> weatherLeds = ledService.getWeatherLeds();
	    int weatherLedNum = weatherLeds.size();
	    model.addAttribute("trafficLedNum",trafficLedNum);
	    model.addAttribute("busLedNum",busLedNum);
	    model.addAttribute("metroLedNum",metroLedNum);
	    model.addAttribute("schoolLedNum",schoolLedNum);
	    model.addAttribute("cinemaLedNum",cinemaLedNum);
	    model.addAttribute("weatherLedNum",weatherLedNum);
	    return getNameSpace() + "channelState";
	}
	
}
