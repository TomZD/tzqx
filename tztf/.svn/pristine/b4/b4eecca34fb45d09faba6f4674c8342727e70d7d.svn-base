package cn.movinginfo.tztf.releasechannel.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class NationalEmergencyAckParser {
    
    
    public static String parse(String path) throws DocumentException, FileNotFoundException, MalformedURLException {
        File xmlFile = new File(path);
        InputStream xmlStream = new FileInputStream(xmlFile);
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlFile);
        List<Element> nodes = document.selectNodes("/msg/data/rows/*");
        Element rootElement = document.getRootElement(); 
        String status = "";
        String errorCode = "";
        String note = "";
        
        for(Element n: nodes) {
           if(n.getName().equals("status")){
               status = n.getStringValue();
           }
           if(n.getName().equals("error_code")){
               errorCode = n.getStringValue();
           }
           if(n.getName().equals("note")){
               note = n.getStringValue();
           }
        }
        
        StringBuilder builder = new StringBuilder();
        if(status.trim().equals("0")) {
            builder.append("CAP文件上传成功！请发布人员确认国突平台信息！");
        } else {
            if(status.trim()!=null)
            builder.append("状态码：" + status.trim());
            builder.append("错误编号：" + errorCode);
            builder.append("错误信息：" + note);
        }
        
        return builder.toString();
    }
    
    public static void main(String[] args) throws FileNotFoundException, DocumentException, MalformedURLException {
        NationalEmergencyAckParser.parse("D:/33060441600000_201611041522000000000_330600-AMP_330600-OTH_20161104171200074_AI-1_0.xml");
    }
}
