package cn.movinginfo.tztf.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class Test14 {
//	private static final String DEF_ENCODING = "GBK";
//	
//	public static void main(String []args){
//		Test14 generHtmlForVelocity = new Test14();
//		generHtmlForVelocity.doRelease();
//	}
// 
//	/**
//	 * 生成页面主体
//	 * @author wanghjbuf 
//	 */
//	public void doRelease(){
//		try {  
//			Properties p = initVelocityProperties();
//		    Velocity.init(p);  
//		    
//		    Template template = getVelocityTemplate();  
//		    VelocityContext context = initVelocityContext();  
//		    BufferedWriter writer = getWriterStream();
//		   
//		    template.merge(context, writer);  
//		    writer.close();  
//		} catch (Exception e) {  
//		    e.printStackTrace();  
//		}  
//	}
//	
//	/**
//	 * 初始化VelocityProperties
//	 * @author wanghjbuf 
//	 */
//	public Properties initVelocityProperties() throws Exception{
//		Properties p = new Properties();  
//		try{
//			p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");  
//			p.setProperty(Velocity.ENCODING_DEFAULT, DEF_ENCODING);  
//			p.setProperty(Velocity.INPUT_ENCODING, DEF_ENCODING);  
//			p.setProperty(Velocity.OUTPUT_ENCODING, DEF_ENCODING); 
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return p;
//	}
//	
//	/**
//	 * 初始化VelocityContext
//	 * @author wanghjbuf 
//	 */
//	public VelocityContext initVelocityContext() throws Exception{
//	    VelocityContext context = new VelocityContext();  
//		try{
//		    context.put("status", "发布"); 
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return context;
//	}
//	
//	/**
//	 * 初始化VelocityContext
//	 * @author wanghjbuf
//	 * @param  String author_name
//	 *         List<String> blog_list
//	 */
//	@SuppressWarnings("rawtypes")
//	public VelocityContext initVelocityContext(String author_name,List blog_list) throws Exception{
//	    VelocityContext context = new VelocityContext();  
//		try{
//		    context.put("name", author_name);  
//		    context.put("last_modif", new Timestamp(System.currentTimeMillis()));
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return context;
//	}
//	
//	/**
//	 * 获取Velocity模板
//	 * @author wanghjbuf 
//	 */
//	public Template getVelocityTemplate() throws Exception{
//		Template template = new Template();
//		
////		BodyPart html = new MimeBodyPart();  
//		try{
//			template = Velocity.getTemplate("F:\\form.vm");
////			html.setContent(template, "text/html; charset=UTF-8");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return template;
//	}
//	
//	/**
//	 * 获取Velocity模板
//	 * @author wanghjbuf 
//	 * @param String velocityTemplateSource
//	 */
//	public Template getVelocityTemplate(String velocityTemplateSource) throws Exception{
//		Template template = new Template();
//		try{
//			template = Velocity.getTemplate(velocityTemplateSource);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return template;
//	}
//	
//	/**
//	 * 获取Velocity写入流
//	 * @author wanghjbuf 
//	 */
//	public BufferedWriter getWriterStream() throws Exception{
//		try{
//		    FileOutputStream fos = new FileOutputStream("F:\\form.html");  
//		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, DEF_ENCODING));
//		    return writer;
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	/**
//	 * 获取Velocity写入流
//	 * @author wanghjbuf 
//	 * @param String writerStreamSource
//	 */
//	public BufferedWriter getWriterStream(String writerStreamSource) throws Exception{
//		try{
//		    FileOutputStream fos = new FileOutputStream(writerStreamSource);  
//		    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, DEF_ENCODING));
//		    return writer;
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public static boolean writeWordFile() {
		 
		  boolean w = false;
		  String path = "F:\\";
		  
		  try {
		   if (!"".equals(path)) {
		    
		    // 检查目录是否存在
		    File fileDir = new File(path);
		    if (fileDir.exists()) {
		     
		     // 生成临时文件名称
		     String fileName = "a.doc";
		     String content = "<html>" +
		           "<head>你好</head>" +
		          "<body>" +
		            "<table>" +
		             "<tr>" +
		              "<td>信息1</td>" +              
		              "<td>信息2</td>" +              
		              "<td>t3</td>" +              
		             "<tr>" +
		            "</table>" +
		            "</body>" +
		            "</html>";
		     
		     byte b[] = content.getBytes();
		     ByteArrayInputStream bais = new ByteArrayInputStream(b);
		     POIFSFileSystem poifs = new POIFSFileSystem();
		     DirectoryEntry directory = poifs.getRoot();
		     DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
		     FileOutputStream ostream = new FileOutputStream(path+ fileName);
		     poifs.writeFilesystem(ostream);
		     ostream.close();
		     
		    }
		   }
		 
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 
		  return w;
		 }
		 
		 public static void main(String[] args){
		  writeWordFile();
		 }
}
