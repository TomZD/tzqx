package cn.movinginfo.tztf.common.mq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.BlobMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.releasechannel.Weibo;
import cn.movinginfo.tztf.releasechannel.weibo4j.util.WeiboConfig;




public class ReceiveMsgFromMq extends SimpleMQConsumer{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveMsgFromMq.class);

	
	private static String BROKERURL = WeiboConfig.getValue("brokerUrl");
	private static String USERNAME = WeiboConfig.getValue("username");
	private static String PASSWORD = WeiboConfig.getValue("password");
	
	private static int count = 0;

	public ReceiveMsgFromMq() {
//		try {
////			initQueue();
////			initTopic();
//			initPersistTopic();
//		} catch (JMSException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
	
	public void initQueue(String topicName) throws JMSException, Exception{
		setConnectionFactory(getActiveMQConnectionFactory());
		setSubject(topicName);
		init(1);
	}
	
	public void initTopic() throws JMSException, Exception{
		setConnectionFactory(getActiveMQConnectionFactory());
		setSubject("topic.demo");
		init(0);
	}
	
	public void initPersistTopic() throws JMSException, Exception{
		setConnectionFactory(getActiveMQConnectionFactory());
		setClientId("consumer2");
		setSubject("cms");
		init(2);
	}
	
	
	
	public static ActiveMQConnectionFactory getActiveMQConnectionFactory(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(BROKERURL);
		factory.setUserName(USERNAME);
		factory.setPassword(PASSWORD);
		factory.setWarnAboutUnstartedConnectionTimeout(1000l);
		return factory;
	}
	
	public void onMessage(Message message){
		try {
			
			if(message instanceof ObjectMessage){
				Object obj = ((ObjectMessage)message).getObject();
				System.out.println("收到Object消息" +obj);
			}else if(message instanceof TextMessage){
				String obj = ((TextMessage)message).getText();
				WeiboUtil.fabu(obj);
				System.out.println(obj);
			}else if(message instanceof BlobMessage){
				try {
					int byteread = 0;
					InputStream inputStream = ((BlobMessage) message).getInputStream();
					String fileName = message.getStringProperty("FILE.NAME");
					File file = new File("f:/mqrec2/"+fileName);
					if(!file.exists()){
						file.getParentFile().mkdirs();
						file.createNewFile();
						
					}
					FileOutputStream fout = new FileOutputStream(file);
					byte[] buffer = new byte[1024];
					while((byteread=inputStream.read(buffer)) != -1){
						fout.write(buffer, 0, byteread); 
					}
					fout.close();
					inputStream.close();  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			count++;
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	 
	public static void main(String[] args) {
		ReceiveMsgFromMq remq = new ReceiveMsgFromMq();
		try {
			LOGGER.info("weiMq start");
			remq.initQueue(WeiboConfig.getValue("sendTopicName"));
//			long s = System.currentTimeMillis();
//			while(true){
//				System.out.println( System.currentTimeMillis()-s);
//				Thread.sleep(10000);
//			}
		}  catch (Exception e) {
			LOGGER.info("weiMq ",e);
		}
	}
}
