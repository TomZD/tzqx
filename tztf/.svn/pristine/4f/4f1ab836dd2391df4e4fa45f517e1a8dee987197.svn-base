package cn.movinginfo.tztf.common.mq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.activemq.BlobMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.releasechannel.Weibo;
import cn.movinginfo.tztf.releasechannel.weibo4j.util.WeiboConfig;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;

@Lazy(false)
public class ReceiveMsgFabuFromMq extends ReceiveMsgFromMq implements InitializingBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveMsgFabuFromMq.class);
	@Autowired
    private ReleaseChannelInstanceService channelInstanceService;
	
	@Override
	public void afterPropertiesSet() throws Exception{
//		new Thread(new  Runnable() {
//			public void run() {
//				try {
//					LOGGER.info("weiMq start");
//					initQueue(WeiboConfig.getValue("receiveTopicName"));
//				}  catch (Exception e) {
//					LOGGER.info("weiMq ",e);
//				}
//			}
//		}).start();;
		
	}

	@Override
	public void onMessage(Message message) {
		try {

			if (message instanceof ObjectMessage) {
				Object obj = ((ObjectMessage) message).getObject();
				System.out.println("收到Object消息" + obj);
			} else if (message instanceof TextMessage) {
				String obj = ((TextMessage) message).getText();
				Weibo weibo = JSON.parseObject(obj,Weibo.class);
				ReleaseChannelInstance releaseChannelInstance = new ReleaseChannelInstance();
				releaseChannelInstance.setId(Long.valueOf(weibo.getId()));
				releaseChannelInstance.setReleaseState("4");
				releaseChannelInstance.setFeedbackMessage("反馈成功！");
				channelInstanceService.saveOrUpdate(releaseChannelInstance);
				System.out.println(obj);
			} else if (message instanceof BlobMessage) {
				try {
					int byteread = 0;
					InputStream inputStream = ((BlobMessage) message).getInputStream();
					String fileName = message.getStringProperty("FILE.NAME");
					File file = new File("f:/mqrec2/" + fileName);
					if (!file.exists()) {
						file.getParentFile().mkdirs();
						file.createNewFile();

					}
					FileOutputStream fout = new FileOutputStream(file);
					byte[] buffer = new byte[1024];
					while ((byteread = inputStream.read(buffer)) != -1) {
						fout.write(buffer, 0, byteread);
					}
					fout.close();
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	

}
