package cn.movinginfo.tztf.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.core.io.DefaultResourceLoader;

public class XmlUtils {
	
		 public static void getHtml(String type,String webappRoot) {
			 String path ="https://www.hzqx.com/hztq/data/Micaps4/"+type+"/gis.xml";
		        OutputStream out = null;
//		        String paths = new DefaultResourceLoader().getClassLoader().getResource("tqsk/"+type+".properties").getPath();
//                System.out.println(paths);
		        
		        //将path转换为URL格式
		        URL url;
				try {
					url = new URL(path);
					//打开Http链接
			        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			        //设置请求方式为GET
			        conn.setRequestMethod("GET");
			        //设置连接超时时间
			        conn.setConnectTimeout(5 * 1000);
			 
			        //通过conn获取输入流(字节流)---inStream
			        InputStream inStream = conn.getInputStream();
			        //将readInputStream返回的字节数组存入data中
			        byte[] data = readInputStream(inStream);
			        //通过new String的方法。并设置utf-8的编码方式，转换为String类型
			        String html = new String(data, "UTF-8");
			        File file = new File(webappRoot); 
			        // 根据文件创建文件的输出流
		            out = new FileOutputStream(file);
		            // 把内容转换成字节数组
		            byte[] data1 = html.getBytes();
		            // 向文件写入内容
		            out.write(data1);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            try {
		                // 关闭输出流
		                out.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		    }
		        
		        
		    
		    public static byte[] readInputStream(InputStream inStream) throws Exception{
		        //转换成字节数组
		        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		        //新建缓冲字节数组
		        byte[] buffer = new byte[1024];
		        int len = 0;
		        /*
		        JDK解释：read(Byte []b)一个参数
		        从输入流中读取一定量的字节，将其存入缓冲区数组buffer中
		        以整数形式返回其实际读取的字节数。
		        如果b的长度为0，或者不读取任何字节，则返回为0；
		        否则，尝试读取至少一个字节。
		        如果，流位于末尾而没有可用字节，则返回为-1；
		        否则，至少读取一个字节并将其存储在 b 中。
		        将读取的第一个字节存入buffer[0]中，
		        下一个存入buffer[1]中，以此类推。
		        读取的字节数最多等于buffer的长度。
		         */
		        while( (len=inStream.read(buffer)) != -1 ){
		            //每次读取8个字节作为一次循环
		            //将这8个字节存入的数组buffer作为参数
		            //写入outStream中
		            //将指定的byte数组从偏移量off开始的len个字节写入此输出流
		            outStream.write(buffer, 0, len);
		        }
		        //字节流操作之后，将资源关闭
		        inStream.close();
		        //将获得的outStream转换为字节数组，并返回
		        return outStream.toByteArray();
		    }
		    
//   public String getUrl() {
//	   String webappRoot = this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("WEB-INF/classes/", "static1/data/1.xml");
//	   return webappRoot;
//   }
		    

   public static void main(String[] args) throws Exception {
//	   String x = getHtml();
//	   System.out.println(x.substring(0, 500));
//	   System.getProperty("user.dir")+File.separator+"src"+File.separator+"main"+
//       File.separator+"webapp"+File.separator+"static2"+File.separator+"data"+File.separator+"sk.xml"
//	   strChangeXML(XmlUtils.getHtml());
//	   getHtml("1hour_R");
   }
   
}
	

