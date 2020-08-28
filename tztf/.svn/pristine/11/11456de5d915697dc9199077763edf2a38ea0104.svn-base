/**
 * 
 */
package cn.movinginfo.tztf.mq;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.rpc.ServiceException;

import net.sf.json.JSONObject;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import cn.movinginfo.tztf.common.utils.GTAlarmDomain;
import cn.movinginfo.tztf.common.utils.MsgConfigUtil;
import cn.movinginfo.tztf.common.utils.MsgDomain;



/**
 *  @author yougq
 *	2019年3月27日上午10:16:58
 */
//生成者
public class TztfProducter {
//	public static int sendMsg(MsgDomain domain,String uuid) throws JMSException, RemoteException, ServiceException{
//		System.out.println("突发短信生产者开始发送内容");
//		String tcp = MsgConfigUtil.getValue("tcp");
//		String requestquene = MsgConfigUtil.getValue("requestquene");
//		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
//				ActiveMQConnection.DEFAULT_PASSWORD, tcp);
//		Connection connection = connectionFactory.createConnection();
//		connection.start();
//		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//		// Query名
//		Destination destination = session.createQueue(requestquene);
//		// MessageProducer：消息生产者
//		MessageProducer producer = session.createProducer(destination);
//		// 点对点通信，不持久化
//		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//		// 发送一条消息
//		String json = JSONObject.fromObject(domain).toString();
//		sendMsg(session, producer, json);
//		connection.close();
//		return 0;
//	}
	
	public static int sendGTAlarm(GTAlarmDomain domain) throws JMSException, RemoteException, ServiceException{
		System.out.println("突发国突渠道开始发送内容");
		String tcp = MsgConfigUtil.getValue("tcp");
		String requestquene = MsgConfigUtil.getValue("requestquene");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, tcp);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// Query名
		Destination destination = session.createQueue(requestquene);
		// MessageProducer：消息生产者
		MessageProducer producer = session.createProducer(destination);
		// 点对点通信，不持久化
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		// 发送一条消息
		String json = JSONObject.fromObject(domain).toString();
		sendMsg(session, producer, json);
		connection.close();
		return 0;
	}
	
	
	
	/**
	 * 
	 * @param session
	 *            消息会话
	 * @param producer
	 *            消息生产者
	 */
	public static void sendMsg(Session session, MessageProducer producer, String domain) throws JMSException {
		// 创建一条文本消息
		TextMessage message = session.createTextMessage(domain);
		// 通过消息生产者发出消息
		producer.send(message);
	}
	
	public static void main(String[] args) throws JMSException, RemoteException, ServiceException {

		GTAlarmDomain alarm = new GTAlarmDomain();
		alarm.setAlarmContent("测试预警");
		String uuid = UUID.randomUUID().toString();
		
	}
	
	
	
	
	
}
