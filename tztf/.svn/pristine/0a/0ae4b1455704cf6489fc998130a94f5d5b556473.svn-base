package cn.movinginfo.tztf.releasechannel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.Publish;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.PublishService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.DictService;

@Service
public class ChannelReactorImpl implements ChannelReactor {
    
    private static final Logger log = Logger.getLogger(ChannelReactorImpl.class);
    
    private ExecutorService service = Executors.newCachedThreadPool();
    
    private MiddleObject midObject;
    
    @Autowired
    private ReleaseChannelInstanceService channelInstanceService;
    
    @Autowired
	private DictService dictService;
    
    @Autowired
	private PublishService publishService;
    
    @Autowired(required = false)
    private ServletContext servletContext;
    
    @Autowired
    private AlarmService alarmService;
    
    @Autowired
	private DeptService deptService;
    
    @Override
    public void doRelease(ReleaseChannelInstance instance) {
        service.submit(new ChannelReleaseTask(instance));
    }

    @Override
    public void doRelease(Set<ReleaseChannelInstance> instances, MiddleObject midObject) {
        log.info("发布渠道开始发布工作！");
        log.info("开始发布接口：" + instances);
        
        if(midObject==null) 
            throw new RuntimeException("传入的midObject为空！");
        
        setEntity(midObject);
        Map<String,Integer> map = new HashMap<String,Integer>();
        Alarm alarm = null;
        Depart department = null;
        List<ReleaseChannelInstance> instanceList =  getReleaseChannelInstance(instances);
        for(ReleaseChannelInstance instance: instanceList){
        	 doRelease(instance);
        }
//        for(ReleaseChannelInstance instance: instances) {
//        	if(alarm == null){
//        		alarm = alarmService.passVersionAndCode(instance.getVersion(), instance.getSenderNumber());
//        		department = deptService.get(alarm.getDeptId());
//        	}
//            doRelease(instance);
//            List<ReleaseChannel> releaseChannelList = SystemProperties.SUBRELEASECHANNEL_MAP.get(instance.getChannelId());
//            if(SystemProperties.RELEASECHANNEL_DEPART_MAP.get(instance.getChannelId())!=null){
//            	ReleaseChannel releaseChannel = SystemProperties.RELEASECHANNEL_MAP.get(instance.getChannelId());
//            	if(releaseChannelList==null){
//            		releaseChannelList = new ArrayList<ReleaseChannel>();
//            	}
//            	releaseChannelList.add(releaseChannel);
//            }
//            if(releaseChannelList!=null){
//            	for(ReleaseChannel releaseChannel :releaseChannelList){
//                	if(releaseChannel.getNeedFeedback()!=null&&releaseChannel.getNeedFeedback().equals(1)){
//                		List<Depart> departList = SystemProperties.RELEASECHANNEL_DEPART_MAP.get(instance.getChannelId());
//                		for(Depart depart:departList){
//                			if(!map.containsKey(midObject.getId()+"_"+depart.getId())){
//                				Publish publish = new Publish();
//                    			publish.setDepartmentId(depart.getId());
//                    			publish.setAlarmId(midObject.getId());
//                    			publish.setReleaseChannelInstanceId(instance.getId());
//                    			publish.setIsPublish(0);
//                    			publish.setCreateDate(new Date());
//                    			publishService.saveOrUpdate(publish);
//                    			String phone = depart.getPhone();
//                    			String content = "";
//                    			if(instance.getContent().indexOf("解除")!=-1){
//                    				content = department.getName()+"已解除"+alarm.getAlarmTypeName()+"预警，请及时清除，解除审批单已通过"+depart.getFax()+"传真发往您处，请注意查收";
//                    			}else{
//                    				content = "您有待发预警信息，请及时登入http://21.15.91.133:8080确认发布任务！";
//                        			if("杭州市交通运输局".equals(depart.getName())||"杭州市地铁集团".equals(depart.getName())||"杭州市邮政公司".equals(depart.getName())){
//                        				content = "您有待发预警信息审批单正通过"+depart.getFax()+"传真发往您处，请注意查收。发布内容为："+instance.getContent();
//                        			}else if("杭州移动公司".equals(depart.getName())||"杭州电信".equals(depart.getName())||"杭州联通公司".equals(depart.getName())){
//                        				content = "您有全网（区域）发布待发预警信息（气象）需审核，请及时登入相应平台，完成审核并发布";
//                        			}
//                    			}
//                    			alarmService.sendSmsMessage(phone, content, 0);
//                    			
////                    			"收到"+depart.getName()+"预警发布请求，请及时登入http://172.41.129.120:8080审核！"
//                    			map.put(midObject.getId()+"_"+depart.getId(), 1);
//                    			
//                			}
//                		}
//                	}
//                }
//            }
//        }
    }
    
    /**
     * 将渠道对应的类相同的进行归类
     * @param instances
     * @return
     */
    public List<ReleaseChannelInstance> getReleaseChannelInstance(Set<ReleaseChannelInstance> instances){
    	List<ReleaseChannelInstance> result = new ArrayList<ReleaseChannelInstance>();
    	Map<String,ReleaseChannelInstance> map = new HashMap<String,ReleaseChannelInstance>();
    	 for(ReleaseChannelInstance instance: instances) {
    		 ReleaseChannel channel = instance.getChannel();
             String handleClazzName = channel.getHandlerClazz();
             if(!map.containsKey(handleClazzName)){
            	 map.put(handleClazzName, instance);
             }else{
            	 Long instanceId = instance.getId();
            	 Long channelId = instance.getChannelId();
            	 ReleaseChannelInstance releaseChannelInstance = map.get(handleClazzName);
            	 String instanceIds = releaseChannelInstance.getInstanceIds();
            	 String channelIds = releaseChannelInstance.getChannelIds();
            	 if(instanceIds!=null&&!"".equals(instanceIds)){
            		 instanceIds += "," + instanceId;
            		 channelIds += "," + channelId;
            	 }else{
            		 instanceIds = instanceId.toString();
            		 channelIds = channelId.toString();
            	 }
            	 releaseChannelInstance.setInstanceIds(instanceIds);
            	 releaseChannelInstance.setChannelIds(channelIds);
             }
    	 }
    	 result.addAll(map.values());
    	return result;
    }
    
    public class ChannelReleaseTask implements Runnable{
        public ChannelReleaseTask(ReleaseChannelInstance instance){
            this.instance = instance;
        }
        
        private ReleaseChannelInstance instance;
        
        @SuppressWarnings("static-access")
		@Override
        public void run() {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 设置渠道内容发布状态
//            instance.setReleaseState(ReleaseChannelState.RELEASE_ING);
            instance.setReleaseState(dictService.getValue(DictKeys.CHANNEL_STATE, "递交中"));
            instance.setFeedbackMessage("尝试发布中");
            channelInstanceService.update(instance);
            
            String content = instance.getContent();
            ReleaseChannel channel = instance.getChannel();
            String handleClazzName = channel.getHandlerClazz();
            
            if(StringUtils.isBlank(handleClazzName)) {
                log.warn(channel+"对应的接口处理类为空！");
            }
            
            String errorMsg;
            try {
                Channel channelInterface = (Channel)Class.forName(handleClazzName).newInstance();
                channelInterface.setChannelInstance(instance);
                channelInterface.doRelease(content, midObject);
                
                instance.setArriveTime(new Date());
//				if (instance.getReleaseState() == null
//						|| instance.getReleaseState() == ReleaseChannelState.TO_BE_RELEASED
//						|| instance.getReleaseState() == ReleaseChannelState.RELEASE_ING) {
//					instance.setReleaseState(ReleaseChannelState.RELEASE_SUCESEE);
//				}
				// 渠道发布完成后，渠道发布状态：空、“准备递交”、“递交中”，改为“递交成功”
				if (instance.getReleaseState() == null
						|| dictService.getValue(DictKeys.CHANNEL_STATE, "准备递交").equals(instance.getReleaseState())
						|| dictService.getValue(DictKeys.CHANNEL_STATE, "递交中").equals(instance.getReleaseState())) {
					instance.setReleaseState(dictService.getValue(DictKeys.CHANNEL_STATE, "递交成功"));
				}
                
                if(StringUtils.isBlank(instance.getFeedbackMessage()) || "尝试发布中".equals(instance.getFeedbackMessage())) {
                    instance.setFeedbackMessage("发布成功！");
                }
                channelInstanceService.saveOrUpdate(instance);
                Long oid = instance.getId();
                Long ocid = instance.getChannelId();
                if(instance.getInstanceIds()!=null&&!"".equals(instance.getInstanceIds())){
                	String[] instanceIdArr  = instance.getInstanceIds().split(",");
                	String[] channelIdArr  = instance.getChannelIds().split(",");
                	for(int i=0;i<instanceIdArr.length;i++){
                		String instanceId = instanceIdArr[i];
                		instance.setId(Long.valueOf(instanceId));
                		instance.setChannelId(Long.valueOf(channelIdArr[i]));
                		channelInstanceService.saveOrUpdate(instance);
                	}
                	instance.setId(oid);
                	instance.setChannelId(ocid);
                }
                Alarm alarm = new Alarm();
                alarm.setId(instance.getAlarmId());
                alarm.setCompleteDate(new Date());
                alarmService.saveOrUpdate(alarm);//修改发布完成时间
                return;
            } catch (InstantiationException e) {
                e.printStackTrace();
                log.warn("ChannelReleaseTask工作异常",e);
                errorMsg = e.getMessage();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                errorMsg = e.getMessage();
                log.warn("ChannelReleaseTask工作异常",e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                errorMsg = e.getMessage();
                log.warn("ChannelReleaseTask工作异常",e);
//            } catch (ChannelReleaseException e) {
//                e.printStackTrace();
//                errorMsg = e.getMessage();
//                log.warn("ChannelReleaseTask工作异常",e);
            } catch (Exception e) {
                e.printStackTrace();
                errorMsg = e.getMessage();
                log.warn("ChannelReleaseTask工作异常",e);
            }
            
            try {
                instance.setArriveTime(new Date());
//                instance.setReleaseState(ReleaseChannelState.RELEASE_FAIL);
                // 设置渠道发布失败
                instance.setReleaseState(dictService.getValue(DictKeys.CHANNEL_STATE, "递交失败"));
                instance.setFeedbackMessage("发布失败：" + errorMsg);
                
                channelInstanceService.update(instance);
            } catch (Exception e) {
                log.warn(e);
                log.error("更新db出错！", e);
                e.printStackTrace();
            }
           
        }
    }

    @Override
    public void setEntity(MiddleObject midObj) {
        midObject = midObj;
    }
    
//    @PostConstruct
//    public void testSupport() {
//        if(servletContext==null) {
//            servletContext = new MockServletContext("file://D:/test-resouce");
//            System.out.println(servletContext);
//            System.out.println(servletContext.getRealPath("test"));
//        }
//    }

    @Override
    public void doRemove(Set<ReleaseChannelInstance> instances, MiddleObject midObject) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doDefaultRemove(Set<ReleaseChannelInstance> instances, MiddleObject midObject) {
        // TODO Auto-generated method stub
        
    }
}
