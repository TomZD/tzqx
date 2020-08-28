package cn.movinginfo.tztf.common.typhoon;

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

    public static Properties properties=PropertyRead("typhoondata.properties");

    /**
    * @Author: BaoLongHui
    * @Description 加载指定的属性文件
    * @Date 13:59 2018/4/13
    */
    public static Properties PropertyRead(String configFile)
    {
        try{
            Properties properties=new Properties();
            //以URL形式获取工程的资源文件 classpath 路径, 得到以file:/为开头的URL
            //例如返回: file:/D:/workspace/myproject01/WEB-INF/classes/
            URL classPath = Thread.currentThread().getContextClassLoader().getResource("");
            String proFilePath =URLDecoder.decode(classPath.toString(),"UTF-8");

            //移除开通的file:/六个字符
            proFilePath = proFilePath.substring(6);

            //如果为window系统下,则把路径中的路径分隔符替换为window系统的文件路径分隔符
            proFilePath = proFilePath.replace("/", java.io.File.separator);

            //兼容处理最后一个字符是否为 window系统的文件路径分隔符,同时建立 properties 文件路径
            //例如返回: D:\workspace\myproject01\WEB-INF\classes\config.properties
            if(!proFilePath.endsWith(java.io.File.separator)){
                proFilePath = proFilePath + java.io.File.separator + configFile;
            } else {
                proFilePath = proFilePath + configFile;
            }

            //以文件流形式读取指定路径的配置文件 config.properties
            FileInputStream ins = new FileInputStream(proFilePath);
            FileInputStream fis = new FileInputStream(proFilePath);
            BufferedReader bf = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
            properties.load(bf);     ///加载属性列表
            return properties;

        }
        catch(Exception e){
            System.out.println(e);
            return new Properties();
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
