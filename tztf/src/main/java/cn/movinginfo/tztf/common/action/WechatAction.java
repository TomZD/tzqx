package cn.movinginfo.tztf.common.action;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.ryian.commons.DateUtils;

@Controller
@RequestMapping(value = "/wechat")
public class WechatAction {
	/**
	 * 短期预报
	 * @return
	 */
    @RequestMapping(value = "/shortForecast")
    public String shortForecast() {
        return "wechat/short-forecast";
    }
    /**
     * 乡镇预报
     * @return
     */
    @RequestMapping(value = "/townForecast")
    public String townForecast() {
    	
        return "wechat/fine-forecast";
    }
    /**
     * 小流域预报
     * @return
     */
    @RequestMapping(value = "/watershedForecast")
    public String watershedForecast() {
    	
        return "wechat/watershed-forecast";
    }
    /**
     * 趋势预报
     * @return
     */
    @RequestMapping(value = "/trendForecast")
    public String trendForecast(){
    	
    	return "wechat/trend-forecast";
    }
    /**
     * 交通预报
     * @return
     */
    @RequestMapping(value = "/trafficForecast")
    public String trafficForecast(){
    	
    	return "wechat/traffic-forecast";
    }
    /**
     * 指数预报
     * @return
     */
    @RequestMapping(value = "/indexForecast")
    public String indexForecast(){
    	
    	return "wechat/num-forecast";
    }
    /**
     * 预警
     * @return
     */
    @RequestMapping(value = "/warning")
    public String warning(Model model){
    	Date date = new Date();
    	String time = DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    	model.addAttribute("nowTime", time);
    	return "wechat/forecast-info";
    }
}
