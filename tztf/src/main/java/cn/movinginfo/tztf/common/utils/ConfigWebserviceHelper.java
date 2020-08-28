package cn.movinginfo.tztf.common.utils;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigWebserviceHelper {
	public ConfigWebserviceHelper(){}
	private static Properties props = new Properties(); 
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("webservice.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return props.getProperty(key);
	}

}

