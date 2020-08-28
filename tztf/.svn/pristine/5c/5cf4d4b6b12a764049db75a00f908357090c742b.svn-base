package cn.movinginfo.tztf.releasechannel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.webserviceClient.warnWeibo.Status;
import cn.movinginfo.tztf.webserviceClient.warnWeibo.WarnWeiBoService;
import cn.movinginfo.tztf.webserviceClient.warnWeibo.WarnWeiBoServiceService;
import cn.movinginfo.tztf.webserviceClient.weibo.WeiBoService;
import cn.movinginfo.tztf.webserviceClient.weibo.WeiBoServiceService;

/**
 * weibo发布渠道接口
 * @author zhangd
 */
public class WeiboChannel implements Channel {

	private static final Logger log = Logger.getLogger(WeiboChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
//    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
	
    private static Properties props = new Properties(); 
	static{
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("weibo.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("进入微博方法----------------------------");
//		String result = PublishUtils.weibo(content);
//		JSONObject jsonObject = JSON.parseObject(result);
//		if(jsonObject.getBooleanValue("result")){
//			channelInstance.setReleaseState("4");
//			channelInstance.setFeedbackMessage(jsonObject.getString("message"));
//		}else{
//			channelInstance.setReleaseState("3");
//			channelInstance.setFeedbackMessage(jsonObject.getString("message"));
//		}
//		System.out.println(result);
		WarnWeiBoServiceService warmWeiBoService = new WarnWeiBoServiceService();
		WarnWeiBoService port = warmWeiBoService.getWarnWeiBoServicePort();
//		WeiBoServiceService weiBoService=new WeiBoServiceService();
//		WeiBoService port=weiBoService.getWeiBoServicePort();
		Status data = port.sendword(channelInstance.getContent());
		
		if(!data.getId().isEmpty()){
			channelInstance.setReleaseState("4");
			channelInstance.setFeedbackMessage("发送微博成功");
		}else{
			channelInstance.setReleaseState("3");
			channelInstance.setFeedbackMessage("");
		}
		System.out.println("发送微博结束----------------------------");
    }
	
	

    @Override
    public void doReleaseFile(String filePath, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doDefaultRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    }

    @Override
    public void setChannelInstance(ReleaseChannelInstance channelInstance) {
    	this.channelInstance = channelInstance;
    }
    
    public static void main(String[] args) {
//    	WeiBoServiceService weiBoService=new WeiBoServiceService();
//		WeiBoService port=weiBoService.getWeiBoServicePort();
//		Status data = port.sendword("test");
	}

}
