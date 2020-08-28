package cn.movinginfo.tztf.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.alibaba.fastjson.JSONObject;

public class VmToHtml {
	private static final String DEF_ENCODING = "GBK";
	/**
	 * 生成页面主体
	 * @author wanghjbuf 
	 */
	public static void doRelease(String source,String dest,JSONObject o){
		try {  
			Properties p = initVelocityProperties();
		    Velocity.init(p);  
		    
		    Template template = getVelocityTemplate(source);  
		    VelocityContext context = initVelocityContext(o);  
		    BufferedWriter writer = getWriterStream(dest);
		   
		    template.merge(context, writer);  
		    writer.close();  
		} catch (Exception e) {  
		    e.printStackTrace();  
		}  
	}
	
	/**
	 * 初始化VelocityProperties
	 * @author wanghjbuf 
	 */
	public  static Properties initVelocityProperties() throws Exception{
		Properties p = new Properties();  
		try{
			p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");  
			p.setProperty(Velocity.ENCODING_DEFAULT, DEF_ENCODING);  
			p.setProperty(Velocity.INPUT_ENCODING, DEF_ENCODING);  
			p.setProperty(Velocity.OUTPUT_ENCODING, DEF_ENCODING); 
		}catch(Exception e){
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 * 初始化VelocityContext
	 * @author wanghjbuf
	 * @param  String author_name
	 *         List<String> blog_list
	 */
	@SuppressWarnings("rawtypes")
	public static VelocityContext initVelocityContext(JSONObject o) throws Exception{
	    VelocityContext context = new VelocityContext();  
		try{
		    context.put("status", o.get("status"));  
		    context.put("signal", o.get("signal"));
		    context.put("warning", o.get("warning"));
		    context.put("startStopTime", o.get("startStopTime"));
		    context.put("distribution", o.get("distribution"));
		    context.put("depart", o.get("depart"));
		    context.put("phone", o.get("phone"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return context;
	}
	
	/**
	 * 获取Velocity模板
	 * @author wanghjbuf 
	 * @param String velocityTemplateSource
	 */
	public static Template getVelocityTemplate(String velocityTemplateSource) throws Exception{
		Template template = new Template();
		try{
			template = Velocity.getTemplate(velocityTemplateSource);
		}catch(Exception e){
			e.printStackTrace();
		}
		return template;
	}
	
	/**
	 * 获取Velocity写入流
	 * @author wanghjbuf 
	 * @param String writerStreamSource
	 */
	public static BufferedWriter getWriterStream(String writerStreamSource) throws Exception{
		try{
		    FileOutputStream fos = new FileOutputStream(writerStreamSource);  
		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, DEF_ENCODING));
		    return writer;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getNew(String path,String loadPath) {
		File file = new File(path);
		//得到文件夹下的所有文件和文件夹
		String[] list = file.list();
		
		if(list!=null && list.length>0){
			for (String oldName : list) {
				File oldFile = new File(path,oldName);
				//判断出文件和文件夹
				if(!oldFile.isDirectory()){
					//文件则判断是不是要修改的
					if(oldName.contains(".html")){
						System.out.println(oldName);
						String newoldName = oldName.substring(0, oldName.lastIndexOf("."))+".doc";
						System.out.println(newoldName);
						File newFile = new File(loadPath,newoldName);
						boolean flag = oldFile.renameTo(newFile);
						System.out.println(flag);
						break;
					}
				}
			}
		}
	}
}
