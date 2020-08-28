package cn.movinginfo.tztf.sen.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.constants.SystemProperties;

public class test {

    public static void main(String[] args) {  
    	String url=SystemProperties.getProperty("ldurl");
//    	File [] files=new File(url).listFiles();
//    	for (File file : files) {
//    		System.out.println(file.getName());
////    		File file = new File(string);
//		}
    	url+="Z_P_LPD__C_BABJ_20190618235201_2019_06_19.txt";
    	File file=new File(url);
    	String content=txt2String(file);
//    	System.out.println(content);
    	String [] datas=content.split("\r\n");
    	
    	System.out.println(datas.length);
    	for (int i = 0; i < datas.length; i++) {
    		
			System.out.println(datas[i]);
		}

    }  
    
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
//            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");  
            BufferedReader br = new BufferedReader(isr);  
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
      
   

}
