package cn.movinginfo.tztf.common.utils;
//package cn.movinginfo.tztf.common.utils;
//
//import java.awt.image.BufferedImage;
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.ImageType;
//import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.tools.imageio.ImageIOUtil;
//
//
//public class HtmlToImage {
////protected static void generateOutput() throws Exception {
////		
////		//load the webpage into the editor
////		//JEditorPane ed = new JEditorPane(new URL("http://www.google.com"));
////		JEditorPane ed = new JEditorPane(new URL("https://www.baidu.com"));
////		ed.setSize(1000,1000);
//// 
////		//create a new image
////		BufferedImage image = new BufferedImage(ed.getWidth(), ed.getHeight(),
////		                                        BufferedImage.TYPE_INT_ARGB);
//// 
////		//paint the editor onto the image
////		SwingUtilities.paintComponent(image.createGraphics(), 
////		                              ed, 
////		                              new JPanel(), 
////		                              0, 0, image.getWidth(), image.getHeight());
////		//save the image to file
////		ImageIO.write((RenderedImage)image, "png", new File("html3.png"));
////	}
////	public static void main(String[] args) {
////		try {
////			generateOutput();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//	
//	 public final static String IMG_TYPE_PDF = ".pdf"; 
//
//	 
//	 public static String saveToFile(String destUrl,String orderCode) throws Exception {
//
//		 //存储在服务器上的地址
//		 String filePath ="F:/";
//	
//		 //返回给用户的地址前缀
//		 String realPath="localhost:8080";
//		 
//		 FileOutputStream fos = null;
//		 BufferedInputStream bis = null;
//		 HttpURLConnection httpUrl = null;
//		 URL url = null;
//		 int BUFFER_SIZE = 1024;
//		 byte[] buf = new byte[BUFFER_SIZE];
//		 int size = 0;
//		 try {
//		 url = new URL(destUrl);
//		 httpUrl = (HttpURLConnection) url.openConnection();
//		 httpUrl.connect();
//		 bis = new BufferedInputStream(httpUrl.getInputStream());
//		 String pdfUrl=filePath+"/"+orderCode+IMG_TYPE_PDF;
//		 System.out.println("pdfUrl="+pdfUrl);
//		 try {
//		 fos = new FileOutputStream(pdfUrl);
//		 } catch (Exception e) {
//		 e.printStackTrace();
//		 return null;
//		 }
//		 
//		 while ((size = bis.read(buf)) != -1) {
//		 fos.write(buf, 0, size);
//		 }
//		 fos.flush();
//		 String ourUrl=realPath+"/"+orderCode+IMG_TYPE_PDF;
//		 pdf2img(pdfUrl,filePath+"/"+orderCode,".png");
//		 ourUrl=realPath+"/"+orderCode+".pdf";
//		 System.out.println("outUrl="+ourUrl);
//		 return ourUrl;
//		 } catch (IOException e) {
//		 e.printStackTrace();
//		 } catch (ClassCastException e) {
//		 e.printStackTrace();
//		 } finally {
//		 try {
//		 fos.close();
//		 bis.close();
//		 httpUrl.disconnect();
//		 } catch (IOException e) {
//		 e.printStackTrace();
//		 } catch (NullPointerException e) {
//		 e.printStackTrace();
//		    }
//	     }
//		 return null;
//		 }
//
//	 public static void pdf2img(String pdfPath, String savePath, String imgType) throws IOException {
//	 PDDocument document = PDDocument.load(new File(pdfPath));
//	 PDFRenderer pdfRenderer = new PDFRenderer(document);
//	 for (int page = 0; page < document.getNumberOfPages(); ++page){
//	BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
//	ImageIOUtil.writeImage(bim, savePath + imgType, 300);
//	}
//	 document.close();
//	 }
//
//	 public static void main(String[] args) throws Exception {
//		 HtmlToImage dw = new HtmlToImage();
//	 String str=dw.saveToFile("https://s1.tuchong.com/content-image/af1cd4d8387103ded6eb0fe78198e5c9.jpg","1254");
//	 System.out.println("str="+str);
//	 }
//}
