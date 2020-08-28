package cn.movinginfo.tztf.common.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * 地区配置类
 * @author 朱潜
 *
 */
public class AreaConfig {
    
    static{
        Properties prop = new Properties();
        try {
            prop.load( new InputStreamReader(AreaConfig.class.getClassLoader().getResourceAsStream("areaConfig.properties"),"UTF-8"));
            AREA_NAME        = new String(prop.get("areaName").toString());
            AREA_COUNTY_NAME = prop.get("areaCountyName").toString().trim();
            SYSTEM_TITLE     = prop.get("systemTitle").toString().trim();
            JS_CONFIG        = prop.get("jsConfig").toString().trim();
            OBSERVATORY_NAME = prop.get("observatoryName").toString().trim();
            XML_LOCATION     = prop.get("xmlLocation").toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String AREA_NAME;
    public static String AREA_COUNTY_NAME;
    public static String SYSTEM_TITLE;
    public static String OBSERVATORY_NAME;
    public static String JS_CONFIG;
    public static String XML_LOCATION;
    
    private AreaConfig(){
        
    }
}
