package cn.movinginfo.tztf.common.mq;

import java.io.File;
import java.io.IOException;

import javax.jms.JMSException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.releasechannel.Weibo;
import cn.movinginfo.tztf.releasechannel.weibo4j.util.WeiboConfig;

public class SendMsgToMq extends SimpleMQProducer {

	private static int count = 1;
	public SendMsgToMq(){
//		try {
//			initQueue();
////			initTopic();
////			initPersistTopic();
//		} catch (JMSException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void initQueue(String topicName) throws JMSException, Exception{
		setConnectionFactory(ReceiveMsgFromMq.getActiveMQConnectionFactory());
		setSubject(topicName);
		init(1);
	}
	
	public void initTopic() throws JMSException, Exception{
		setConnectionFactory(ReceiveMsgFromMq.getActiveMQConnectionFactory());
		setSubject("topic.demo");
		init(0);
	}
	
	public void initPersistTopic() throws JMSException, Exception{
		setConnectionFactory(ReceiveMsgFromMq.getActiveMQConnectionFactory());
		setSubject("persist.topic.demo");
		setEnablePersist(true);
		init(2);
	}
	
	public static void main(String[] args) {
		try {
			SendMsgToMq sendMq = new SendMsgToMq();
			sendMq.initQueue(WeiboConfig.getValue("sendTopicName"));
			Weibo weibo = new Weibo();
			weibo.setId("58457");
			weibo.setContent("文字图片测试！！！！");
			String imgPath = SystemProperties.APP_PATH+"/static/images/warnsmall/01.jpg";
			File file = new File(imgPath);
			byte[] arr;
			String img = "";
			try {
				arr = FileUtils.readFileToByteArray(file);
				img = Base64.encodeBase64String(arr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			weibo.setImg(img);
			String jsonStr = JSON.toJSONString(weibo);
			sendMq.produceTxtMessage(jsonStr);
			sendMq.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
