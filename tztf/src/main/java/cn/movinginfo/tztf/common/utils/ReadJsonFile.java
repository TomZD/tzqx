package cn.movinginfo.tztf.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.alibaba.fastjson.JSONObject;


public class ReadJsonFile {
	/**
	 * 以utf-8的编码读取文件内容
	 * @param filePath
	 * @return
	 */
	public static String readFileToString(String filePath){
		String str="";
		File file=new File(filePath);
		try {
		 FileInputStream in=new FileInputStream(file);
		 // size 为字串的长度 ，这里一次性读完
		 int size=in.available();
		 byte[] buffer=new byte[size];
		 in.read(buffer);
		 in.close();
		 str=new String(buffer,"utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return str;
	}
	
	public static void main(String[] args) {
		String a = "E:\\hydata/skjc.json";
		String b = readFileToString(a);
		System.out.println(b);
//		JSONObject jsonObject=JSONObject.fromObject(b);
//		System.out.println(jsonObject);
		JSONObject jsonObject = JSONObject.parseObject(b);
		String r = jsonObject.getString("nowWind");//获取json里nowWind字段的值
		System.out.println(r);
	}
    
	
}
