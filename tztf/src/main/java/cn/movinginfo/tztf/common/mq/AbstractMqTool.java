package cn.movinginfo.tztf.common.mq;


import java.io.File;
import java.io.Serializable;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.BlobMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * MQ
 * @author xingzengwu
 *
 */
public abstract class AbstractMqTool implements MessageListener {
	protected Logger log = LoggerFactory.getLogger(AbstractMqTool.class);
	private String subject = "MOVINGINFO.INTERNAL_COMMAND";
	private Destination destination = null;
	private Connection connection = null;
	private Session session = null;
	private MessageConsumer consumer = null;
	private MessageProducer producer = null; 
	
	private boolean enableConsumer = false;
	private boolean enableProducer = false;
	
	private boolean enablePersist = false;
	private String clientId;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	ActiveMQConnectionFactory connectionFactory;
	
	public void finalize(){
		try {
			close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	public AbstractMqTool(boolean ep,boolean ec){
		this(ep,ec,false);
	}
	public AbstractMqTool(boolean ep,boolean ec,boolean es){
		enableProducer = ep;
		enableConsumer = ec;
		enablePersist = es;
	}	
	public void init(int type) throws JMSException, Exception {
		connection = connectionFactory.createConnection();
		if(clientId!=null)
		connection.setClientID(clientId);
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//from false to true
		
		 if(type!=1){
			 destination = session.createTopic(subject);
		 }else{
			 destination = session.createQueue(subject);
		 }
		
		if(enableConsumer){
			if(type!=2){
				consumer = session.createConsumer(destination);//普通订阅
			}else{
				consumer = session.createDurableSubscriber((Topic) destination, clientId);
			}
			
			consumer.setMessageListener(this);
		}
		
		if(enableProducer){
			producer = session.createProducer(destination);
			producer.setDeliveryMode(enablePersist?DeliveryMode.PERSISTENT:DeliveryMode.NON_PERSISTENT);
			
			//producer.setTimeToLive( 1000*60*3); //remains 3 minutes...
		}
 
		connection.start();

		log.info("Begin listening");
	}
	
	public void init() throws JMSException, Exception {
		init(0);
		//which avoids making a copy of the ObjectMessage to send; though this assumes that you don't try and resuse the ObjectMessage instance; you just create it once and send it once and don't try and change the body of the message after sending it.
		
		 //connectionFactory.setCopyMessageOnSend( false );
		
		//disable the automatic serialization of ObjectMessage payloads so that the objects are passed by value 
		//connectionFactory.setObjectMessageSerializationDefered(true);
		
//		connection = connectionFactory.createConnection();
//		
//		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//from false to true
//		
//		 
//		destination = session.createTopic(subject);
//		
//		 
//		if(enableConsumer){
//			consumer = session.createConsumer(destination);
//			consumer.setMessageListener(this);
//		}
//		
//		if(enableProducer){
//			producer = session.createProducer(destination);
//			producer.setDeliveryMode(enablePersist?DeliveryMode.PERSISTENT:DeliveryMode.NON_PERSISTENT);
//			
//			//producer.setTimeToLive( 1000*60*3); //remains 3 minutes...
//		}
// 
//		connection.start();
//
//		log.info("Begin listening");
		
	}
	

	public void close() throws JMSException {
		log.info("Closing connection");
		if (consumer != null)
			consumer.close();
		if (session != null)
			session.close();
		if (connection != null)
			connection.close();
	}
	public void produceMessage(Serializable obj)  {
		 
		ObjectMessage msg;
		try {
			msg = session.createObjectMessage( obj );
			connection.start();
			producer.send(msg);		
			
		} catch (JMSException e) {
			 
			e.printStackTrace();
		}

	}
	public void produceTxtMessage(String txt)  {
		 
		 Message msg;
		try {
			msg = session.createTextMessage(txt);
			connection.start();
			producer.send(msg);	
			//session.commit();
		} catch (JMSException e) {
			 
			e.printStackTrace();
		}

	}	
	
	public void produceFileMessage(File file){
		ActiveMQSession activeMQSession = (ActiveMQSession) session;
		BlobMessage msg; 
		try{
			msg = activeMQSession.createBlobMessage(file);
			msg.setStringProperty("FILE.NAME",file.getName()); 
			msg.setLongProperty("FILE.SIZE",file.length()); 
			producer.send(msg); 
		} catch (JMSException e) {
			 
			e.printStackTrace();
		}
	}  
	
	public void sendMapMessage(Map map){
		try {
			MapMessage msg =session.createMapMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void onMessage(Message message);
	  
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setConnectionFactory(ActiveMQConnectionFactory f) {
		this.connectionFactory = f;
	}
	public boolean isEnablePersist() {
		return enablePersist;
	}
	public void setEnablePersist(boolean enablePersist) {
		this.enablePersist = enablePersist;
	}
	
	

}
