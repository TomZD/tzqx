package cn.movinginfo.tztf.sys.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {
	
	public static void main(String[] args) {
//        String ip =getIpAddress();
//        System.out.println(ip);
//		  Socket Skt;
//	      String host = "localhost";
//	      if (args.length > 0) {
//	         host = args[0];
//	      }
//	      for (int i = 0; i < 1024; i++) {
//	         try {
//	            System.out.println("查看 "+ i);
//	            Skt = new Socket(host, i);
//	            System.out.println("端口 " + i + " 已被使用");
//	         }
//	         catch (UnknownHostException e) {
//	            System.out.println("Exception occured"+ e);
//	            break;
//	         }
//	         catch (IOException e) {
//	         }
//	      }
//		List<String> li = getLocalIpAdress();
//		for(String l : li) {
//			System.out.println(l);
//		}
		try {
			getHtml();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }
	
    
	
	/**
	 * 获取指定网址的Ip地址
	 * @return
	 */
	public static String getIpAddress() {
		 InetAddress address = null;
	        try {
	            address = InetAddress.getByName("www.baidu.com");
	        }
	        catch (UnknownHostException e) {
	            System.exit(2);
	        }
	        return address.getHostAddress();
	}
	
	/**
	 * 获取本机ip地址和主机名
	 * @return
	 */
	public static List<String> getLocalIpAdress() {
		InetAddress addr;
		List<String> li = new ArrayList<>();
		try {
			addr = InetAddress.getLocalHost();
			String localIp = addr.getHostAddress();
			String hostname = addr.getHostName();
			li.add(localIp);
			li.add(hostname);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return li;
	}
	
	/**
	 * 抓取网页（html）
	 * @throws IOException
	 */
	public static void getHtml() throws IOException {
		 URL url = new URL("http://www.baidu.com");
	      BufferedReader reader = new BufferedReader
	      (new InputStreamReader(url.openStream()));
	      BufferedWriter writer = new BufferedWriter
	      (new FileWriter("E:\\data.html"));
	      String line;
	      while ((line = reader.readLine()) != null) {
	         System.out.println(line);
	         writer.write(line);
	         writer.newLine();
	      }
	      reader.close();
	      writer.close();
	   }
	
}
