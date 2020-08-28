package cn.movinginfo.tztf.common.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.movinginfo.tztf.sen.domain.ChineseEnglish;

/**
 * 反射
 * @author wqb
 *
 */
public class Reflex {
	
	public static Object method(Object obj, String filed) {  
		Object o = null;
	    try {  
	        Class clazz = obj.getClass();  
	        PropertyDescriptor pd = new PropertyDescriptor(filed, clazz);  
	        Method getMethod = pd.getReadMethod();//获得get方法  
	  
	        if (pd != null) {  
	  
	             o = getMethod.invoke(obj);//执行get方法返回一个Object  
	            System.out.println(o);  
	  
	        }  
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IllegalArgumentException e) {  
	        e.printStackTrace();  
	    } catch (IntrospectionException e) {  
	        e.printStackTrace();  
	    } catch (IllegalAccessException e) {  
	        e.printStackTrace();  
	    } catch (InvocationTargetException e) {  
	        e.printStackTrace();  
	    }
	    return o;
	}
	public static void main(String[] args) {
		ChineseEnglish  ch = new ChineseEnglish();
		ch.setChinese("123");
		ch.setEnglish("asdad");
		method(ch,"chinese");
	}

}
