package cn.movinginfo.tztf.common.constants;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cn.movinginfo.tztf.common.utils.AreaConfig;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;

/**
 * Created by allenwc on 16/2/18.
 */
public class SystemProperties {

    public static final String APP_SAVE_PATH = "app_save_path";
    public static final String APP_URL = "app_url";
    public static String APP_PATH;
    public static final String PUBLISH_URL = "publish_url";
    public static final Map<String,String> WARN_MAP = new HashMap<String,String>();//预警信号对应的图片名
    public static final Map<Long,ReleaseChannel> RELEASECHANNEL_MAP = new HashMap<Long,ReleaseChannel>();
    public static final Map<Long,List<ReleaseChannel>> SUBRELEASECHANNEL_MAP = new HashMap<Long,List<ReleaseChannel>>();//子发布渠道
    public static final Map<Long,List<Depart>> RELEASECHANNEL_DEPART_MAP = new HashMap<Long,List<Depart>>();
    public static Properties prop = new Properties();
    public static final Map<String,String> AREA_MAP = new HashMap<String,String>();//区县对应的
    
    static{
        try {
            prop.load( new InputStreamReader(AreaConfig.class.getClassLoader().getResourceAsStream("system.properties"),"UTF-8"));
            APP_PATH = prop.getProperty("app_path", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        WARN_MAP.put("台风蓝色","01.jpg");
        WARN_MAP.put("台风黄色","02.jpg");
        WARN_MAP.put("台风橙色","03.jpg");
        WARN_MAP.put("台风红色","04.jpg");
        WARN_MAP.put("暴雨蓝色","05.jpg");
        WARN_MAP.put("暴雨黄色","06.jpg");
        WARN_MAP.put("暴雨橙色","07.jpg");
        WARN_MAP.put("暴雨红色","08.jpg");
        WARN_MAP.put("暴雪蓝色","09.jpg");
        WARN_MAP.put("暴雪黄色","10.jpg");
        WARN_MAP.put("暴雪橙色","11.jpg");
        WARN_MAP.put("暴雪红色","12.jpg");
        WARN_MAP.put("寒潮蓝色","13.jpg");
        WARN_MAP.put("寒潮黄色","14.jpg");
        WARN_MAP.put("寒潮橙色","15.jpg");
        WARN_MAP.put("寒潮红色","16.jpg");
        WARN_MAP.put("大风黄色","17.jpg");
        WARN_MAP.put("大风橙色","18.jpg");
        WARN_MAP.put("大风红色","19.jpg");
        WARN_MAP.put("大雾黄色","20.jpg");
        WARN_MAP.put("大雾橙色","21.jpg");
        WARN_MAP.put("大雾红色","22.jpg");
        WARN_MAP.put("雷电黄色","23.jpg");
        WARN_MAP.put("雷电橙色","24.jpg");
        WARN_MAP.put("雷电红色","25.jpg");
        WARN_MAP.put("冰雹橙色","26.jpg");
        WARN_MAP.put("冰雹红色","27.jpg");
        WARN_MAP.put("霜冻蓝色","28.jpg");
        WARN_MAP.put("霜冻黄色","29.jpg");
        WARN_MAP.put("霜冻橙色","30.jpg");
        WARN_MAP.put("高温橙色","31.jpg");
        WARN_MAP.put("高温红色","32.jpg");
        WARN_MAP.put("干旱橙色","33.jpg");
        WARN_MAP.put("干旱红色","34.jpg");
        WARN_MAP.put("道路结冰黄色","35.jpg");
        WARN_MAP.put("道路结冰橙色","36.jpg");
        WARN_MAP.put("道路结冰红色","37.jpg");
        WARN_MAP.put("霾黄色","38.jpg");
        WARN_MAP.put("霾橙色","39.jpg");
        WARN_MAP.put("霾红色","40.jpg");
        WARN_MAP.put("蓝色","event_blue.png");
        WARN_MAP.put("黄色","event_yellow.png");
        WARN_MAP.put("橙色","event_orange.png");
        WARN_MAP.put("红色","event_red.png");
        
        AREA_MAP.put("yuhang", "余杭区");
        AREA_MAP.put("xiaoshan", "萧山区");
        AREA_MAP.put("fuyang", "富阳区");
        AREA_MAP.put("tonglu", "桐庐县");
        AREA_MAP.put("chunan", "淳安县");
        AREA_MAP.put("jiande", "建德市");
        AREA_MAP.put("linan", "临安区");
    }
    
    public static String getProperty(String key,String defaultValue){
    	return prop.getProperty(key, defaultValue);
    }
    
    public static String getProperty(String key){
    	return getProperty(key, "");
    }

}
