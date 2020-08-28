package cn.movinginfo.tztf.sys.action;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import cn.movinginfo.tztf.sys.domain.Decision;



public class Test {
	public static void main(String[] args) throws IOException {
		int index=0;
		InputStream is = new FileInputStream("F:/2018年气象灾害防御重点单位联系人_20190527172108.xls");

        HSSFWorkbook excel = new HSSFWorkbook(is);

            HSSFSheet sheet = excel.getSheetAt(0);//第二张表格读不了
            for (int rowNum = 1; rowNum < sheet.getLastRowNum()+1; rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);

                if(row != null) {
        
                	
                	HSSFCell name = row.getCell(0);
                	name.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	de.setName(String.valueOf(name));
                	if(String.valueOf(name).equals("*一级组织机构")){
                		index=1;
                		continue;
                	}
                	if(index==0){
                		continue;
                	}

                	
                	System.out.println(String.valueOf(name));
                	
                	HSSFCell depart = row.getCell(1);
                	depart.setCellType(HSSFCell.CELL_TYPE_STRING);

                	System.out.println(String.valueOf(depart));
                	
                	HSSFCell phone = row.getCell(2);
                	phone.setCellType(HSSFCell.CELL_TYPE_STRING);

                	System.out.println(String.valueOf(phone));

                }
//                list.add(de);
            }
        
	}
	   
	
	

	
	 public static void convert2Html(String fileName, String outPutFile)
	            throws TransformerException, IOException,
	            ParserConfigurationException {

	        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
	         //兼容2007 以上版本
//	        XSSFWorkbook  xssfwork=new XSSFWorkbook(new FileInputStream(fileName));
	        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
	                DocumentBuilderFactory.newInstance().newDocumentBuilder()
	                        .newDocument());
	        wordToHtmlConverter.setPicturesManager( new PicturesManager()
	        {
	            public String savePicture( byte[] content,
	                                       PictureType pictureType, String suggestedName,
	                                       float widthInches, float heightInches )
	            {
	                return "test/"+suggestedName;
	            }
	        } );
	        wordToHtmlConverter.processDocument(wordDocument);
	        
	        //save pictures
	        List pics=wordDocument.getPicturesTable().getAllPictures();
	        if(pics!=null){
	            for(int i=0;i<pics.size();i++){
	                Picture pic = (Picture)pics.get(i);
	                System.out.println();
	                try {
	                    pic.writeImageContent(new FileOutputStream("D:/test/"
	                            + pic.suggestFullFileName()));
	                } catch (FileNotFoundException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        Document htmlDocument = wordToHtmlConverter.getDocument();
	        
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        DOMSource domSource = new DOMSource(htmlDocument);
	       
	        StreamResult streamResult = new StreamResult(out);


	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer serializer = tf.newTransformer();
	     
	        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	        serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
	        serializer.transform(domSource, streamResult);
	        out.close();
	        writeFile(new String(out.toByteArray()), outPutFile);
	    }
	 
	 
	 public static void writeFile(String content, String path) {
	        FileOutputStream fos = null; 
	        BufferedWriter bw = null;
	        org.jsoup.nodes.Document doc = Jsoup.parse(content);
	        String styleOld=doc.getElementsByTag("style").html();
	        //统一字体格式为宋体
	        styleOld=styleOld.replaceAll("font-family:.+(?=;\\b)", "font-family:SimSun");
	        
	        doc.getElementsByTag("head").empty();
	        doc.getElementsByTag("head").append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></meta>");
	        doc.getElementsByTag("head").append(" <style type=\"text/css\"></style>");
	        doc.getElementsByTag("style").append(styleOld);
	        /*正则表达式查询字体内容：font-family:.+(?=;\b)*/
	        System.out.println(content);
	        content=doc.html();
	        content=content.replace("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">", "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></meta>");
	        try {
	            File file = new File(path);
	            fos = new FileOutputStream(file);
	            bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
	            bw.write(content);
	        } catch (FileNotFoundException fnfe) {
	            fnfe.printStackTrace();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        } finally {
	            try {
	                if (bw != null)
	                    bw.close();
	                if (fos != null)
	                    fos.close();
	            } catch (IOException ie) {
	            }
	        }
	    }
	 
//	 public  void main(String[] args) {
////		 try {
////			convert2Html("E:\\20190212141100.doc","E:\\1.html");
////		} catch (TransformerException | IOException | ParserConfigurationException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		 String a =this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("WEB-INF/classes/", "fileModel/杭州市突发事件预警信息发布.doc");
//		 System.out.println(a);
//	}
}
