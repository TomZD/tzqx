package cn.movinginfo.tztf.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ListIterator;
import java.util.Properties;

/**
 * @Author: baolonghui
 * @Description
 * @Data Created in 11:15 2018/4/13
 * @Modified By:
 */
public class ConfigHelper {

    public static Properties properties=new Properties();

    /**
    * @Author: BaoLongHui
    * @Description 加载指定的属性文件
    * @Date 13:59 2018/4/13
    */
   static
    {
        try{
        	properties.load( new InputStreamReader(ConfigHelper.class.getClassLoader().getResourceAsStream("regular.properties"),"UTF-8"));

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
    * @Author: BaoLongHui
    * @Description 从默认的属性文件中读取配置文件属性值
    * @Date 13:58 2018/4/13
    */
    public static String getProperty(String propertyName)
    {
        return  properties.getProperty(propertyName);
    }
    /**
    * @Author: BaoLongHui
    * @Description 从指定的属性文件中读取配置文件属性值
    * @Date 13:58 2018/4/13
    */
    public static String getProperty(Properties properties ,String propertyName)
    {
        return  properties.getProperty(propertyName);
    }

}
