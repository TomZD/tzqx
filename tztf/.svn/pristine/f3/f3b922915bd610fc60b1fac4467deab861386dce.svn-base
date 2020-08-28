package cn.movinginfo.tztf.webserviceClient.faxold;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;




/**
 * 
 * 锟斤拷锟斤拷锟斤拷锟絊oap锟斤拷锟斤拷涌诘目突锟斤拷锟�
 * @author
 *
 */
public class SoapFaxClient {
	//客户端请求发送传真件接口
	public String upload(byte[] buffer1, byte[] buffer2, byte[] buffer3,
			byte[] buffer4, byte[] buffer5, byte[] buffer6, byte[] buffer7,
			byte[] buffer8, byte[] buffer9, byte[] buffer10, List nameList,
			String sendnumber, String title, String useraccount,String bnetaccount, String password,
			String destnumbers,String areaid,String piclevel) {

		String endPoint = "http://www.bnetfax.net/ZJBnetSoap/services/Manger";
		Call call = null;
		String xml = null;

		try {
			Service service = new Service();
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endPoint);
			call.setOperationName(new QName(endPoint, "SendFax"));
			String filename1 = (String) nameList.get(0);
/*			String filename2 = (String) nameList.get(1);
			String filename3 = (String) nameList.get(2);
			String filename4 = (String) nameList.get(3);
			String filename5 = (String) nameList.get(4);
			String filename6 = (String) nameList.get(5);
			String filename7 = (String) nameList.get(6);
			String filename8 = (String) nameList.get(7);
			String filename9 = (String) nameList.get(8);
			String filename10 = (String) nameList.get(9);*/

			MD5 md5 = new MD5();
			//MD5加密password
//			password = md5.getMD5ofStr(password);
			password = MDES.encrypt(password);
			final String KEY = "1234567890ABCDEF";
			//获取系统时间戳
			long timestamp = System.currentTimeMillis();
			//生成哈希码 Md5（useraccount + timestamp + KEY）
			String hashcode = md5.getMD5ofStr(useraccount+bnetaccount + timestamp + KEY);

			StringBuffer xmlstring = new StringBuffer(
					"<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
			xmlstring.append("\n<root>");
			xmlstring.append("\n<useraccount>" + useraccount + "</useraccount>");
			xmlstring.append("\n<bnetaccount>" + bnetaccount + "</bnetaccount>");
			xmlstring.append("\n<areaid>" + areaid + "</areaid>");
			xmlstring.append("\n<password>" + password + "</password>");
			xmlstring.append("\n<destnumbers>" + destnumbers + "</destnumbers>");
			xmlstring.append("\n<sender>" + sendnumber + "</sender>");
			xmlstring.append("\n<title>" + title + "</title>");
			xmlstring.append("\n<piclevel>" + piclevel + "</piclevel>");
			xmlstring.append("\n<timestamp>" + timestamp + "</timestamp>");
			xmlstring.append("\n<hashcode>" + hashcode + "</hashcode>");
			xmlstring.append("\n<fifle>" + filename1 + "</fifle>");
			xmlstring.append("\n<file>" + 2 + "</file>");
			xmlstring.append("\n</root>");
			//System.out.println("\n发送的XML:\n" + xmlstring.toString());

			//客户端调用Soap传真服务发送传真件接口
			xml = (String) call.invoke(new Object[] { xmlstring.toString(), buffer1,
					buffer2, buffer3, buffer4, buffer5, buffer6, buffer7, buffer8,
					buffer9, buffer10 });

			//为了同时返回发送和返回的xml包，将两个包用“@@”连接
//			xml += "@@" + xmlstring.toString();

		} catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("打印错误:\n" + ex.toString());
		}
		return xml;
	}

	public static void main(String[] args) {
		SoapFaxClient s = new SoapFaxClient();
		byte[] img = img2byte(); 
		List<String>list = new ArrayList<String>();
		list.add("20181105172111.doc");
	/*	list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);
		list.add(null);*/
		String fax = s.upload(img, null, null, 
				null, null, null, null,
				null, null, null, list, "057187352314",
				"测试文件", "admin", "114216455", "1234qwer", "057187352314",
				"0571", "0");
//		System.out.println(fax);
		
//		String result = s.queryFaxResult("057187352314-3115162010981273");
//		System.out.println(result);
		
	}
	
	//客户端请求查询传真回执接口
	public String queryFaxResult(String faxId) {

		//设置请求访问Soap网络传真接口的url
		String endPoint = "http://www.bnetfax.net/ZJBnetSoap/services/Manger";
		Call call = null;
		String xml = null;

		try {
			Service service = new Service();
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endPoint);
			//设置请求访问的url及接口名称
			call.setOperationName(new QName(endPoint, "queryFaxResult"));

			MD5 md5 = new MD5();
			final String KEY = "1234567890ABCDEF";
			//获取系统时间戳
			long timestamp = System.currentTimeMillis();
			//生成哈希码 Md5（faxid + timestamp + KEY）
			String hashcode = md5.getMD5ofStr(faxId + timestamp + KEY);

			StringBuffer xmlstring = new StringBuffer(
					"<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
			xmlstring.append("\n<root>");
			xmlstring.append("\n<faxid>" + faxId + "</faxid>");
			xmlstring.append("\n<timestamp>" + timestamp + "</timestamp>");
			xmlstring.append("\n<hashcode>" + hashcode + "</hashcode>");
			xmlstring.append("\n</root>");
			//System.out.println("\n发送的XML:\n"+xmlstring.toString());

			//客户端调用Soap传真服务查询传真回执接口
			xml = (String) call.invoke(new Object[] { xmlstring.toString() });

			//为了同时返回发送和返回的xml包，将两个包用“@@”连接
//			xml += "@@" + xmlstring.toString();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("打印错误:\n" + ex.toString());
		}
		return xml;
	}
	
	//客户端请求查询接收传真件接口
	public String queryFaxRecv(String useraccount,String bnetaccount, String password,
			String netfaxno, String flag, String starttime, String endtime,String areaid) {

		//设置请求访问Soap网络传真接口的url
		String endPoint = "http://www.bnetfax.net/ZJBnetSoap/services/Manger";
		Call call = null;
		String xml = null;

		try {
			Service service = new Service();
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(endPoint);
			//设置请求访问的url及接口名称
			call.setOperationName(new QName(endPoint, "queryFaxRecv"));

			MD5 md5 = new MD5();
			final String KEY = "1234567890ABCDEF";
			//MD5加密password
//			password = md5.getMD5ofStr(password);
			//des加密
			password = MDES.encrypt(password);
			//获取系统时间戳
			long timestamp = System.currentTimeMillis();
			//生成哈希码 Md5（useraccount + timestamp + KEY）
			String hashcode = md5.getMD5ofStr(useraccount + timestamp + KEY);

			StringBuffer xmlstring = new StringBuffer(
					"<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
			xmlstring.append("\n<root>");
			xmlstring.append("\n<useraccount>" + useraccount + "</useraccount>");
			xmlstring.append("\n<bnetaccount>" + bnetaccount + "</bnetaccount>");
			xmlstring.append("\n<areaid>" + areaid + "</areaid>");
			xmlstring.append("\n<password>" + password + "</password>");
			xmlstring.append("\n<netfaxno>" + netfaxno + "</netfaxno>");
			xmlstring.append("\n<flag>" + flag + "</flag>");
			xmlstring.append("\n<starttime>" + starttime + "</starttime>");
			xmlstring.append("\n<endtime>" + endtime + "</endtime>");
			xmlstring.append("\n<timestamp>" + timestamp + "</timestamp>");
			xmlstring.append("\n<hashcode>" + hashcode + "</hashcode>");
			xmlstring.append("\n</root>");
			//System.out.println("\n发送的XML:\n"+xmlstring.toString());

			//客户端调用Soap传真服务查询接收传真件接口
			xml = (String) call.invoke(new Object[] { xmlstring.toString() });

			//为了同时返回发送和返回的xml包，将两个包用“@@”连接
			xml += "@@" + xmlstring.toString();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("打印错误:\n" + ex.toString());
		}
		return xml;

	}
	
	public static byte[] img2byte(){
	    byte[] data = null;
	    FileImageInputStream input = null;
	    try {
	      String path = "D://20181105172111.doc";
	      input = new FileImageInputStream(new File(path));
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      byte[] buf = new byte[1024];
	      int numBytesRead = 0;
	      while ((numBytesRead = input.read(buf)) != -1) {
	      output.write(buf, 0, numBytesRead);
	      }
	      data = output.toByteArray();
	      output.close();
	      input.close();
	    }
	    catch (FileNotFoundException ex1) {
	    	ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	        ex1.printStackTrace();
	    }
	    return data;
	}
		/*public static void main(String args[]){
		
		SoapFaxClient test = new SoapFaxClient();
		String xml = test.queryFaxResult("11111");
		System.out.println(xml);
		String[] xmls = xml.split("@@"); 
		System.out.println("查询传真回执返回的XML:\n"+xmls[0]);
		System.out.println("查询传真回执发送的XML:\n"+xmls[1]);
	}*/


}
