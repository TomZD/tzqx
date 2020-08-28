package cn.movinginfo.tztf.stb.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class StbConUtil {
	public StbConUtil(){}
	private static Properties props = new Properties(); 
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("stb.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		String value = props.getProperty(key);
		
		try {
			value = new String(value.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	public static boolean isContains(String key){
		return props.containsKey(key);
	}
	
//	public static void add(String key){
//		
//		File file = new File("src/main/resources/time.properties");
//		try {
//			OutputStream out = new FileOutputStream(file);
//			props.setProperty(key, "08:00-18:00");
//			try {
//				props.store(out, null);
//			
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		
//		
//	}

}
