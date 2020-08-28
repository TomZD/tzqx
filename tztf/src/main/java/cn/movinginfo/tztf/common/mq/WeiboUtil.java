package cn.movinginfo.tztf.common.mq;

import java.io.File;
import java.net.URLEncoder;

import javax.jms.JMSException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.Base64;

import cn.movinginfo.tztf.releasechannel.Weibo;
import cn.movinginfo.tztf.releasechannel.weibo4j.Timeline;
import cn.movinginfo.tztf.releasechannel.weibo4j.http.ImageItem;
import cn.movinginfo.tztf.releasechannel.weibo4j.model.Status;
import cn.movinginfo.tztf.releasechannel.weibo4j.util.WeiboConfig;



public class WeiboUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeiboUtil.class);

	public static Status fabu(String jsonStr){
		Status result = null;
		boolean isSuccess = false;
		Weibo weibo = JSON.parseObject(jsonStr,Weibo.class);
		try {
			
			String id = weibo.getId();
			String content = weibo.getContent()  +" http://www.hzqx.com ";
			String img = weibo.getImg();
			String access_token = WeiboConfig.getValue("accessToken");//  "2.00z8L5pFbMYOQD440bc3c47caI3sJC";//args[0];
			String statuses = weibo.getContent();//"微博测试发布文字和图片";//args[1];
			Timeline tm = new Timeline(access_token);
			if(img!=null&&!"".equals(img)){//是否包图片
				ImageItem item = new ImageItem(Base64.decodeFast(img));//图片对象
				result = tm.updateStatusWithPic(URLEncoder.encode(content,"utf-8").replaceAll("\\+", "%20"), item);//发布图片和文字，文字需要URLEncoder.encode 处理过，否则会乱码
			}else{
				result = tm.updateStatus(content);//直接发布文字
			}
			isSuccess = true;
		} catch (Exception e) {
			LOGGER.error("fabu", e);
		}	
		Weibo resWeibo = new Weibo();
		resWeibo.setId(weibo.getId());
		resWeibo.setFabuStatus(isSuccess?"1":"0");//成功返回1，失败返回0
		String returnJsonStr = JSON.toJSONString(resWeibo);
		sendFabuStatus(returnJsonStr);
		return result;
	}
	
	/**
	 * 发送微博发布状态
	 * @param jsonStr
	 */
	public static void sendFabuStatus(String jsonStr){
		SendMsgToMq sendMq = new SendMsgToMq();
		try {
			sendMq.initQueue(WeiboConfig.getValue("receiveTopicName"));
			sendMq.produceTxtMessage(jsonStr);
			sendMq.close();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String access_token = "2.003ZuPiB6C39RE895234cac9HPTGZE";//args[0];
		String statuses = "测试 【台风穿过杭州，带来狂风暴雨！暴雨过后更要防范次生灾害！】年第14号台风“摩羯”于昨天（12日）23时35分登陆温岭，先后穿过临海、仙居、磐安，6时左右从诸暨进入我市富阳，穿经临安，今天08时台风中心位于安徽广德，中心附近最大风力有8级（18米/秒）。今天上午10时逐渐远离我市，杭州市气象台2018年8月13日10时20分解除台风蓝色预警信号！ ​​​​".substring(0,140) +"http://www.hzqx.com ";//args[1];
		statuses = "http://www.hzqx.com ";
		Timeline tm = new Timeline(access_token);
		try {
			File file = new File("D:\\123.jpg");
			byte[] content = FileUtils.readFileToByteArray(file);
			ImageItem item = new ImageItem(content);
			File file1 = new File("D:\\20180813143905.png");
			byte[] content1 = FileUtils.readFileToByteArray(file1);
			ImageItem item1 = new ImageItem(content1);
			Status status = tm.updateStatusWithPic(URLEncoder.encode(statuses,"utf-8").replaceAll("\\+", "%20"), new ImageItem[]{item,item1});
//			Status status = tm.updateStatus(statuses);
			System.out.println(status.getId());
//			Log.logInfo(status.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
