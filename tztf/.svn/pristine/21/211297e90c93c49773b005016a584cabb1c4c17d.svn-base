package cn.movinginfo.tztf.webserviceClient.faxold;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//XML解吸文件
public class ServletClass {

	    String namespaceURI = "TradeService";
	    Document document = null;
	    public String errInfo = "";
	    public String succInfo = "";
	    String paraSerial = "serial";//序号
	    String paraRand = "rand";//随机码
	    String paraEncode = "encode";//hashcode
	    
	    
	    /****
	     * 根据传进去的节点名，返回该节点的值
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
		   * 把一个字符串转成XML类型
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
