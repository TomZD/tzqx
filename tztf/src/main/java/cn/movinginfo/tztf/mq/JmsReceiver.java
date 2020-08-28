/**
 * 
 */
package cn.movinginfo.tztf.mq;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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



import net.ryian.core.utils.SpringContextUtil;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.MsgConfigUtil;
import cn.movinginfo.tztf.sen.model.TyphoonData;
import cn.movinginfo.tztf.sen.model.TyphoonNew;
import cn.movinginfo.tztf.sen.service.DangerTypeService;
import cn.movinginfo.tztf.sen.service.TyphoonNewService;
import cn.movinginfo.tztf.stb.service.StbService;




/**
 *  @author yougq
 *	2019年3月27日上午10:32:37
 */
public class JmsReceiver  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private TyphoonNewService typhoonNewService;
	
//	private TyphoonDataService typhoonDataService = SpringContextUtil.getBean("typhoonDataService");
	
	public static void main(String[] args) throws JMSException, ServiceException, RemoteException {
		System.out.println("消费者启动成功！");
		String tcp = MsgConfigUtil.getValue("tcp");
		//接收消息quene
		String requestquene = MsgConfigUtil.getValue("tyrequestquene");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, tcp);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// Session： 一个发送或接收消息的线程
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(requestquene);
		// 消费者，消息接收者
		MessageConsumer consumer = session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener(){  
            public void onMessage(Message m) {  
                try {
                	List<TyphoonData> result=new ArrayList();
                    System.out.println(new Date() + "mq消费者接收到信息：     " + ((TextMessage)m).getText());
                    List<TyphoonData> list = JSONArray.parseArray(((TextMessage)m).getText(), TyphoonData.class);
                    System.out.println(list.size());
                } catch (JMSException e) {  
                    e.printStackTrace();  
                }
                

            }  
        }); 
	}


	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// TODO Auto-generated method stub
		try {
		System.out.println("消费者启动成功！");
		String tcp = MsgConfigUtil.getValue("tcp");
		//接收消息quene
		String requestquene = MsgConfigUtil.getValue("tyrequestquene");
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, tcp);
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// Session： 一个发送或接收消息的线程
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(requestquene);
		// 消费者，消息接收者
		MessageConsumer consumer = session.createConsumer(destination);
		consumer.setMessageListener(new MessageListener(){  
            public void onMessage(Message m) {  
                try {
//                	List<TyphoonData> result=new ArrayList();
                
                    List<TyphoonNew> list = JSONArray.parseArray(((TextMessage)m).getText(), TyphoonNew.class);
                    System.out.println(new Date() + "mq消费者接收到信息：     " + list.size()+"条");
                    typhoonNewService.saveData(list);
//                    System.out.println(list.size());
                } catch (JMSException e) {  
                    e.printStackTrace();  
                }
                

            }  
        }); 
		
		} catch (JMSException e) {
			e.printStackTrace();
		} 
		
	}
}
