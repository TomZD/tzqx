package cn.movinginfo.tztf.common.fax;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//XML�����ļ�
public class ServletClass {

	    String namespaceURI = "TradeService";
	    Document document = null;
	    public String errInfo = "";
	    public String succInfo = "";
	    String paraSerial = "serial";//���
	    String paraRand = "rand";//�����
	    String paraEncode = "encode";//hashcode
	    
	    
	    /****
	     * ���ݴ���ȥ�Ľڵ��������ظýڵ��ֵ
	     * @param nodeName
	     * @return
	     * @throws Exception
	     */
		  public String getSingleNodeValue(String nodeName) throws Exception {  
		        String ret = "";
		
		        NodeList nodeList = document.getElementsByTagName(nodeName);
		        if (nodeList.item(0) == null) {
		            throw new Exception("Can't find node!" + nodeName);
		        }
		        if (nodeList.item(0).getFirstChild() != null) {
		            ret = nodeList.item(0).getFirstChild().getNodeValue().trim();
		        }
	           
		        return ret;
		    }
		  
		  
		  /****
		   * ��һ���ַ���ת��XML����
		   * @param content
		   * @throws Exception
		   */
		    public void loadXml(String content) throws Exception {
		        InputStream is = new ByteArrayInputStream(content.getBytes());
		       
		        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder builder = factory.newDocumentBuilder();
		        
		        document = builder.parse(is);
		    } 
		  	    
		   
}
