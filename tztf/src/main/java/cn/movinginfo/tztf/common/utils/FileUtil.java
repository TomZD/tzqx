package cn.movinginfo.tztf.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;







import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;



public class FileUtil {
	
	public static String getContextPath(){
		String filePath=FileUtil.class.getClassLoader().getResource("")  
        .getPath();
		if(filePath.indexOf("WEB-INF")!=-1){
			filePath=filePath.split("WEB-INF")[0];
		}
		filePath=filePath+"data/";
//		filePath=filePath+"ZGF0YQ"+"/";
		return filePath; 
	}

	
	public static void convert2Html(String path, String file,String type) throws IOException, ParserConfigurationException, TransformerException{
		  InputStream input = new FileInputStream(path + file);
		  HWPFDocument wordDocument = new HWPFDocument(input);
		  WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
		    DocumentBuilderFactory.newInstance().newDocumentBuilder()
		      .newDocument());
		  wordToHtmlConverter.setPicturesManager(new PicturesManager() {
		   public String savePicture(byte[] content, PictureType pictureType,
		     String suggestedName, float widthInches, float heightInches) {
		    return suggestedName;
		   }
		  });
		  wordToHtmlConverter.processDocument(wordDocument);
		  List pics = wordDocument.getPicturesTable().getAllPictures();
		  if (pics != null) {
		   for (int i = 0; i < pics.size(); i++) {
		    Picture pic = (Picture) pics.get(i);
		    try {
		     pic.writeImageContent(new FileOutputStream(path
		       + pic.suggestFullFileName()));
		    } catch (FileNotFoundException e) {
		     e.printStackTrace();
		    }
		   }
		  }
		  Document htmlDocument = wordToHtmlConverter.getDocument();
		  ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		  DOMSource domSource = new DOMSource(htmlDocument);
		  StreamResult streamResult = new StreamResult(outStream);
		  TransformerFactory tf = TransformerFactory.newInstance();
		  Transformer serializer = tf.newTransformer();
		  serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		  serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		  serializer.setOutputProperty(OutputKeys.METHOD, "html");
		  serializer.transform(domSource, streamResult);
		  outStream.close();
		  String content = new String(outStream.toByteArray());
		  FileUtils.write(new File(path, type+".html"), content, "utf-8");
	}

	/**
	 * 获取某路径下，所有文件
	 *
	 * @param path
	 * @return
	 */
	public static String[] getFileList(String path) {
		String[] result = null;
		File dir = new File(path);
		result = dir.list();
		return result;
	}

	/**
	 * 以字节流的形式读取文件内容
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileToBytes(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * 以utf-8的编码读取文件内容
	 *
	 * @param filePath
	 * @return
	 */
	public static String readFileToString(String filePath) {
		String str = "";
		File file = new File(filePath);
		try {
			FileInputStream in = new FileInputStream(file);
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer, "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return str;
	}
	            
	    
}
