package cn.movinginfo.tztf.common.utils;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import cn.grassinfo.gibos.algorithm.contour.ActiveRainbow;
import cn.grassinfo.gibos.algorithm.contour.Contour;
import cn.grassinfo.gibos.algorithm.contour.ContourCutter;
import cn.grassinfo.gibos.algorithm.contour.ContourMaker;
import cn.grassinfo.gibos.algorithm.contour.Head;
import cn.grassinfo.gibos.algorithm.contour.Micaps4PointHelper;
import cn.grassinfo.gibos.algorithm.contour.PointD;
import cn.grassinfo.gibos.algorithm.contour.Rainbow;
import cn.grassinfo.plugins.arithmetic.weather.DataParser.MicapsData4;
import cn.movinginfo.tztf.webserviceClient.getXml.WebService;
import cn.movinginfo.tztf.webserviceClient.getXml.WebServiceSoap;

public class GetXml {
//	/**
//	 * 
//	 * @param path 四类文件路径
//	 * @param rainbow1 色标内容
//	 * @param title 标题
//	 * @return
//	 */
//	public static String getXML(String path,String rainbow1,String title)
//    {
//		String result="";
//    	try {
//		      double lngInterval = 0.02;
//		      double latInterval = 0.02;
//		      int lngCount = 42;
//		      int latCount = 21;
//		      double startLng = 120.78;
//		      double endLng = 121.6;
//		      double startLat = 28.4; 
//		      double endLat = 28.8;
//		      double[] values = new double[42*21];
//		      for(int i=0;i<values.length;i++) {
//		        if(i%50==0) {
//		          values[i] = i/1000;
//		        }
//		        
//		      }
//		      String contents = FileUtils.readFileToString(new File(path), "UTF-8");
//		      MicapsData4 m4 = new MicapsData4(contents);
//		      m4.init();
//		      endLat = m4.getHeadInfo().getEndLat();
//		      endLng = m4.getHeadInfo().getEndLng();
//		      startLat = m4.getHeadInfo().getStartLat();
//		      startLng = m4.getHeadInfo().getStartLng();
//		      lngInterval = m4.getHeadInfo().getLngInterval();
//		      latInterval = m4.getHeadInfo().getLatInterval();
//		      lngCount = m4.getHeadInfo().getLngCount();
//		      latCount = m4.getHeadInfo().getLatCount();
//		      values = m4.getValues();
//		      Head head = new Head(lngInterval,latInterval,startLng,endLng,startLat,endLat,lngCount,latCount);
//		      Rainbow rainbow = new  Rainbow(rainbow1);
//		      Contour contour = ContourMaker.makeContour(head,m4.getValues(), rainbow, false, null);
////		      String xmlStr = FileUtils.readFileToString(new File("D:/hyq.xml"), "UTF-8");
//		      String xml_path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\config\\hyq.xml").substring(6);
//		      
//
//		      String xmlStr = FileUtil.readFileToString(xml_path.replace("%20", " "));
//		      
//		      
//		      List<PointD> boundaryPoints = Micaps4PointHelper.parseXml(xmlStr);
//		      Contour cutContour = contour.deepClone();
//		      ContourCutter.doCut(cutContour, boundaryPoints);
//		      result = ContourMaker.writeContourXml(cutContour, title, rainbow, ActiveRainbow.toString(rainbow));
////		      System.out.println(result);
////		      FileUtils.writeStringToFile(new File("D:/disasterXml0_12h2.xml"), result, "UTF-8");
//		    } catch (Exception e) {
//		      // TODO Auto-generated catch block
//		      e.printStackTrace();
//		    }
//       return result;
//    }
	
	
	/**
	 * 
	 * @param path 四类文件路径
	 * @param rainbow1 色标内容
	 * @param title 标题
	 * @return
	 */
	public static String getXML(String path,String rainbow1,String title)
    {
			String result="";

			try {
				String contents = FileUtils.readFileToString(new File(path), "GBK");
				WebService webService=new WebService();
			  	WebServiceSoap port=webService.getWebServiceSoap();
			  	result=port.getM4XmlByM4(title, contents, rainbow1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
		      
		     
       return result;
    }
	
	
	

}
