package cn.movinginfo.tztf.releasechannel;

import java.rmi.RemoteException;
import java.util.Date;

import javax.jms.JMSException;
import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.ryian.core.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.common.utils.FileToBase64Util;
import cn.movinginfo.tztf.common.utils.WordFileDomain;
import cn.movinginfo.tztf.mq.LocalArchiveProducter;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.domain.Share;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.DingDingGroupService;
import cn.movinginfo.tztf.sev.service.ShareService;

public class LocalArchiveChannel extends MagicAction<Alarm, AlarmService>  implements Channel{
	
	private static final Logger log = Logger.getLogger(NationEmergencyPlatformChannel.class);
    
    private ReleaseChannelInstance channelInstance;
    
    private AlarmService alarmService = SpringContextUtil.getBean(AlarmService.class);

    private ShareService shareService =SpringContextUtil.getBean(ShareService.class);
    
    @Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
    	
    	Alarm alarm = (Alarm)midObj;
    	Long id = channelInstance.getId();
        String root = alarmService.createLocalForm(alarm);
        String base64 = FileToBase64Util.encryptToBase64(root);
        Date pubTime = alarm.getPubDate();
        String year =DateUtil.format(pubTime, "yyyy");
        String month =DateUtil.format(pubTime, "M");
        String alarmTypeName = alarm.getAlarmTypeName();
        String fileName =year + "-" +month + "-通州区-"+alarmTypeName + "预警.doc";
        WordFileDomain domain = new WordFileDomain();
        domain.setBase64(base64);
        domain.setFileName(fileName);
        domain.setPubNo(String.valueOf(id));
        Share share=shareService.getAll().get(0);
        domain.setSharePath(share.getSharePath());
        domain.setUser(share.getUser());
        domain.setPassword(share.getPassword());
		LocalArchiveProducter hp = new LocalArchiveProducter();
		try {
			
			int result=hp.sendBase64(domain);
			if(result==0){
				channelInstance.setReleaseState("2");
				channelInstance.setFeedbackMessage("本地存档渠道已推送");
			}else{
				channelInstance.setReleaseState("3");
				channelInstance.setFeedbackMessage("本地存档推送失败");
			}
		} catch (RemoteException | JMSException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }

    @Override
    public void doReleaseFile(String filePath, MiddleObject midObj) throws ChannelReleaseException {
        
    }

    @Override
    public void doRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        
    }

    @Override
    public void doDefaultRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        
    }

    @Override
    public void setChannelInstance(ReleaseChannelInstance channelInstance) {
        this.channelInstance = channelInstance;
    }

}
