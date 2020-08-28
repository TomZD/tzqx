package cn.movinginfo.tztf.mq;

import java.rmi.RemoteException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.rpc.ServiceException;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import cn.movinginfo.tztf.common.utils.MsgConfigUtil;
import cn.movinginfo.tztf.common.utils.WordFileDomain;
import net.sf.json.JSONObject;

public class LocalArchiveProducter {

	public static int sendBase64(WordFileDomain domain) throws JMSException, RemoteException, ServiceException{
		System.out.println("突发本地存档渠道开始发送内容");
		String tcp = MsgConfigUtil.getValue("tcp");
		String requestquene = MsgConfigUtil.getValue("cdrequestquene");
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
}
