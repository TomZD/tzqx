package cn.movinginfo.tztf.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class IpAddressUtil {
	/** 
     * 获取当前登录用户的IP地址 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
    public static String getIpAddr(HttpServletRequest request)  
            throws Exception {  
        String ip = request.getHeader("X-Real-IP");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
        ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
        ip = request.getHeader("X-Forwarded-For");
        }


        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
        ip = request.getHeader("Proxy-Client-IP");
        }
    
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
        ip = request.getHeader("WL-Proxy-Client-IP");
        }


        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
        ip = request.getHeader("HTTP_CLIENT_IP");
        }


        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
        ip = request.getRemoteAddr();
        if (ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")) {
        	InetAddress inet = null;
        	 try {
        		    inet = InetAddress.getLocalHost();
        		    } catch (UnknownHostException e) {
        		    e.printStackTrace();
        		    }
        	 ip = inet.getHostAddress();
           }
        }
        if(ip!=null && ip.length()>15){ //"***.***.***.***".length() = 15  
        	 if(ip.indexOf(",")>0){  
                 ip = ip.substring(0,ip.indexOf(","));  
                 }  
        }
        return ip;
        }
    
    
     }
    

