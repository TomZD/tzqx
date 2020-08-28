package cn.movinginfo.tztf.common.constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.DefaultResourceLoader;

public class HZCoordsUtil {
	public static String getHangZhouCoords(String name){
        try {
            String encoding="utf-8";
          //  System.out.println(new DefaultResourceLoader().getClassLoader().getResource("").getPath());
            String path = new DefaultResourceLoader().getClassLoader().getResource("coords/"+name+".properties").getPath();
            
            File file=new File(path);
            
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                String result = null;;
                while((lineTxt = bufferedReader.readLine()) != null){
                	result =  lineTxt;
                }
                read.close();
                return result;
		    }else{
		        System.out.println("找不到指定的文件");
		        return null;
		    }
	    } catch (Exception e) {
	        System.out.println("读取文件内容出错");
	        e.printStackTrace();
	        return null;
	    }
	}
	/*public static void main(String[] args) {
		try
		   {
		 //     File file = new File(HaiNingCoordsUtil.class.getClassLoader().getResource("海宁市.txt").getPath());
		      File file=new File("D:\\workspace\\hnqx\\src\\main\\resources\\海宁市.txt");
		      System.out.println(file.getPath());
		      InputStream is = new FileInputStream(file);
		      byte[] b = new byte[5*1024];
		      is.read(b);
		      System.out.println(new String(b));
		      //关闭流
		      is.close();
		   }
		   catch (FileNotFoundException e)
		   {
		      e.printStackTrace();
		   }
		   catch (IOException e)
		   {
		      e.printStackTrace();
		   }
		}
		*/
		//String haiNingCoords = HaiNingCoordsUtil.getHaiNingCoords();	
	
	
}
