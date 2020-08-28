/**
 * 
 */
package cn.movinginfo.tztf.mq;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.movinginfo.tztf.common.utils.MsgConfigUtil;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;



/**
 *  @author yougq
 *	2019年3月28日下午1:52:21
 */
public class ActivityMqListener implements ApplicationListener<ContextRefreshedEvent>  {
	@Autowired
	private ReleaseChannelInstanceService releaseChannelInstanceService;
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			try {
			System.out.println("ActivityMq消费者监听器启动成功！");
			String reponsequene = MsgConfigUtil.getValue("reponsequene");
			String tcp = MsgConfigUtil.getValue("tcp");
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
					ActiveMQConnection.DEFAULT_PASSWORD, tcp);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(reponsequene);
			MessageConsumer consumer = session.createConsumer(destination);
				consumer.setMessageListener(new MessageListener(){  
				    public void onMessage(Message m) {  
				        try {
				            System.out.println(new Date() + "  tztf : " + ((TextMessage)m).getText());
				            releaseChannelInstanceService.updateById(((TextMessage)m).getText(), "4");
				           
				        } catch (Exception e) {  
				            e.printStackTrace();  
				        }
				    }  
				});
			} catch (JMSException e) {
				e.printStackTrace();
			} 
		}
	}
	
}

