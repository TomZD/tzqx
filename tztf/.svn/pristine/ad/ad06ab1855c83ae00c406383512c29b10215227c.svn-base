package cn.movinginfo.tztf.releasechannel.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.movinginfo.tztf.common.enums.OperationAction;
import cn.movinginfo.tztf.common.utils.AreaConfig;
import cn.movinginfo.tztf.common.utils.ZipUtil;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sm.domain.EventType;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sys.domain.AlarmType;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.AlarmTypeService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.UserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import net.ryian.core.utils.SpringContextUtil;

/*******************************************************
* 文件描述：国突平台生成xml文件支撑代码
* 说　　明：国突平台生成xml文件支撑代码
* 作　　者：朱潜
* 组　　织：小草信息-Java组
* 创建时间：2016-08-22 
* 更新时间：2016-08-22
*******************************************************/

/**
* 类国突平台生成xml文件支撑类
* @author 朱潜
* @Time 2016-08-22 
*/
public class NationalEmergencySupport {
    
    private static final Logger log = Logger.getLogger(NationalEmergencySupport.class);
    
    private static final String KEY_DEPT = "sender"; // 部门

    private static final String KEY_SENDER_CODE = "senderCode";// 部门编号


    private static final String KEY_MSG_TYPE = "msgType";// 是枚举值，只能为
                                                         // Alert（首次），Update(更新)，Cancel(解除)，Ack（确认），Error（错误）

    private static final String KEY_TITLE = "title";// 标题       类似“湖州市气象台发布霾预警[III级/较大]”

    //private static final String KEY_AUDIENCEPRT = "audiencePrt";

    private static final String KEY_EVENTTYPE = "eventType";//事件的国家信息编码，类似11B03这种

    private static final String KEY_COLOR = "severity";// 预警信号颜色

    private static final String KEY_PUBLISHTIME = "effective";// 信号发布时间

    private static final String KEY_ONSETTIME = "onset";// 预计发生时间


    private static final String KEY_DEFENCE = "defence";// 采取措施

    private static final String KEY_PUBLISHAREA = "publishArea";// 发布对应编码
                                                                // 用","分隔多个地区
    @SuppressWarnings("unused")
    private static final String KEY_PUBLISHAREACODE = "publishAreaCode";// 发布地区
    
    private static final String KEY_IDENTIFIER = "identifier";//33050041600000_20160406160000 发布单位编号与时间戳的组合
    
    private static final String KEY_EXPIRES_TIME = "expires";//失效时间
    
    private static final String KEY_DESC = "desc";
    
    private static final String KEY_REFERENCE = "reference";
    
    private UserService userService = SpringContextUtil.getBean(UserService.class);
    
    private DeptService deptService = SpringContextUtil.getBean(DeptService.class);

    private EventTypeService eventTypeService = SpringContextUtil.getBean(EventTypeService.class);
    
    private AlarmTypeService alarmTypeService = SpringContextUtil.getBean(AlarmTypeService.class);

    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static final String[] COLORS_CN = { "红色", "橙色", "黄色", "蓝色" };
    //bug 无 fix bug by 朱潜 修补一个明显的错误
    private static final String[] COLORS = { "RED", "ORANGE", "YELLOW", "BLUE" };
    private static final String[] SEVERITY_LEVELS = {"[I级/特别重大]","[II级/重大]","[III级/较重]", "[IV级/一般]"};
    
    /**
     * 生成气象预警对应的数据模型
     * @param alarm 传入的气象预警pojo
     * @return 数据模型Map
     * @throws IOException
     */
    public Map<String, Object> getDataModel(Alarm alarm, Date currentDate, String refMsgId) throws IOException {
        
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.putAll(getDataModelFromProp());
        
        Date expireDate = null;
        
        Calendar c = Calendar.getInstance(); 
        c.setTime(currentDate); 
        if(alarm.getDuration()!=null && Integer.valueOf(alarm.getDuration())>0) {
            //如果alarm的duration存在且为正数，则使用duration计算失效的时间
            c.add(Calendar.HOUR, Integer.valueOf(alarm.getDuration()));
        } else {
            //否则默认预警发布时效为1天
            c.add(Calendar.HOUR, 24);
        }
        
        expireDate = c.getTime();//根据alarm的duration（周期）计算预警失效时间
        
        Long creator = alarm.getCreator();
        addDept(creator, dataModel);
        
        //String identifier = senderCodeByUserId(creator) + "_" + sdf1.format(currentDate);
        String identifier = alarm.getMsgId();
        
        String msgType = OperationAction.getMessageType(alarm.getOperationAction());
        
        dataModel.put(KEY_MSG_TYPE, msgType);
        
        dataModel.put("operationAction", alarm.getOperationAction());
        
        dataModel.put(KEY_IDENTIFIER, identifier);
        String defence = "";
        String color = "";
        String nationalCode = "";
        if(alarm.getType().equals("alarm")){
        	AlarmType alarmType = alarmTypeService.get(Long.valueOf(alarm.getTypeId()));
        	defence = alarmType.getDefence();
        	color = alarmType.getColor();
        	nationalCode = alarmType.getNationalCode();
        }else{
        	EventType eventType = eventTypeService.get(Long.valueOf(alarm.getTypeId()));
        	color = alarm.getAlarmTypeName().substring(alarm.getAlarmTypeName().indexOf("色")-1, alarm.getAlarmTypeName().indexOf("色")+1);
        	nationalCode = eventType.getNationalCode();
        }
        dataModel.put(KEY_DEFENCE, StringEscapeUtils.unescapeJava(defence));
        dataModel.put(KEY_COLOR, color);
        dataModel.put(KEY_ONSETTIME, sdf2.format(currentDate));
        
        dataModel.put(KEY_EXPIRES_TIME, sdf2.format(expireDate));
        String signalName = alarm.getAlarmTypeName();
        String level = null;
        
        if(signalName.contains("蓝")) {
            level = "[IV级/一般]";
        } else if (signalName.contains("黄")) {
            level = "[III级/较重]";
        } else if (signalName.contains("橙")) {
            level = "[II级/严重]";
        } else if (signalName.contains("红")) {
            level = "[I级/特别重大]";
        } else {
            level = "[IV级/一般]";
        }
        
        // title只要不是解除，其他的情况（变更、升降级）全部为 xxx部门某年某月某日某时发布了xxx预警【预警机械/较大】
        StringBuffer titleBf = new StringBuffer();
        titleBf.append(AreaConfig.OBSERVATORY_NAME);
        String operationStr = "发布";
        if(alarm.getOperationAction()==OperationAction.Cancel.ordinal()) {
            operationStr = "解除";
        }
        titleBf.append(operationStr);
        titleBf.append(signalName+"预警");
        titleBf.append(level);
       
        
        dataModel.put(KEY_TITLE, titleBf.toString());
        
        dataModel.put(KEY_DESC, alarm.getContent());
        
        Date publishDate = new Date(alarm.getPubDate().getTime());
        String publishTime =  sdf2.format(publishDate);
        dataModel.put(KEY_PUBLISHTIME, publishTime);
        
        dataModel.put(KEY_EVENTTYPE, nationalCode);
        
        if(StringUtils.isNotBlank(refMsgId)) {
            dataModel.put(KEY_REFERENCE, refMsgId);
        }
        
        return dataModel;
    }
    
    
    /**
     * 生成气象预警对应的数据模型
     * @param alarm 传入的气象预警pojo
     * @return 数据模型Map
     * @throws IOException
     */
    private Map<String, Object> getDataModelFromProp() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/channel/12379-base.properties");

        prop.load(inputStream);
        Set<Object> keys = prop.keySet();
        Map<String, Object> propMap = new HashMap<String, Object>();
        for (Object key : keys) {
            String value = prop.getProperty((String) key).trim();
            value = new String(value.getBytes("ISO-8859-1"), "utf-8");
            propMap.put((String) key, value);
        }

        return propMap;
    }
    
    /**
     * 根据用户id获取部门名称
     * @param userId 用户id
     * @return 部门名
     */
    private String deptByUserId(Long userId) {
        User user = userService.get(userId);
        Depart deart = deptService.get(user.getDepartmentId());
        return deart.getName();
    }
    
    /**
     * 根据用户id获取部门名称
     * @param userId 用户id
     * @return 部门名
     */
    private String getNationalUnitCodeByUserId(Long userId) {
        User user = userService.get(userId);
        Depart deart = deptService.get(user.getDepartmentId());
        return deart.getNationalUnitCode();
    }
    
    /**
     * 根据用户id获取部门对应的国家单位编码
     * @param userId 用户id
     * @return 单位国家编码
     */
    public String senderCodeByUserId(Long userId) {
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getResourceAsStream("/channel/12379-base.properties"));
            
        } catch (IOException e) {
            log.info("读取配置文件失败！要读取的配置文件为");
            log.info("无配置文件，默认使用SenderCode的模式为普通模式！");
            log.debug(e.getStackTrace());
            return getNationalUnitCodeByUserId(userId);
        }
        
        boolean countyFtpMode = false;
        String modeStr = prop.getProperty("SENDER_CODE_MODE").trim();
        log.info("SENDER_CODE_MODE配置为：" + modeStr);
        if(modeStr.equals("county_ftp")){
            countyFtpMode = true;
        }
        if(!countyFtpMode) {
            log.info("senderCode模式为：" + "普通模式");
            return getNationalUnitCodeByUserId(userId);
        } else {
            log.info("senderCode模式为：" + "县级ftp模式");
            User user = userService.get(userId);
            String normalCode = user.getDepart().getNationalUnitCode();
            String cityCode = prop.getProperty("CITY_CODE").trim();
            String countyShortCode = prop.getProperty("COUNTY_SHORT_CODE").trim();
            
            String mid = normalCode.substring(6, 12);
            return cityCode + mid + countyShortCode;
        }
        
    }
    
    /**
     * 向dataModel中添加部门有关信息
     * @param creatorId 创建人id
     * @param dataModel 模型Map
     */
    private void addDept(Long creatorId, Map<String, Object> dataModel) {
        dataModel.put(KEY_SENDER_CODE, senderCodeByUserId(creatorId));
        dataModel.put(KEY_DEPT, deptByUserId(creatorId));
    }

    /**
     * 模版渲染方法
     * @param obj 输入的对象
     * @param dataModel 输入的对象模型Map
     * @return 生成的压缩后的zip文件名
     * @throws Exception
     */
    public String renderTemplate(MiddleObject obj, Map<String,Object> dataModel, Date currentDate) throws Exception {
        String fileName = getFileName(obj, currentDate);

        URL url = this.getClass().getResource("/channel/12379-template.ftl");
        File templateFile = new File(url.toURI());

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        
        File folderFile = templateFile.getParentFile();
        cfg.setDirectoryForTemplateLoading(folderFile);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        
        Template temp = cfg.getTemplate(templateFile.getName());
        File xmlTempFile = File.createTempFile("national_emegerncy_", ".xml");
        FileOutputStream outputStream = new FileOutputStream(xmlTempFile);
        
        File tempZipFile = File.createTempFile("national_emegerncy_zip", ".zip");

        Writer out = new OutputStreamWriter(outputStream);
        temp.process(dataModel, out);
        out.flush();
        out.close();
        outputStream.flush();
        outputStream.close();
        
        String zipTmpFileName = tempZipFile.getAbsolutePath();
        String zipTmpFilePath = FilenameUtils.getFullPath(zipTmpFileName);
        tempZipFile.renameTo(new File(fileName + ".zip"));
        
        ZipUtil.zipSingleFile(xmlTempFile, tempZipFile, fileName+".xml");
        String otherFilePath = zipTmpFilePath + fileName + ".zip";
        FileUtils.copyFile(tempZipFile, new File(otherFilePath));
        return otherFilePath;
    }
    
    /**
     * 根据传入的对象生成国突平台的标准文件名
     * @param obj 需要传入的对象
     * @return
     */
    private String getFileName(MiddleObject obj, Date currentDate) {
        String timeStampStr = sdf1.format(currentDate);
        
        Long creatorId = obj.getCreator();
        String senderCode = senderCodeByUserId(creatorId);
        
        String levelStr = "UNKNOWN";
        String eventTypeCode = "";
        String operationAction = "";
        String color = "";
        Alarm alarm = (Alarm) obj;
        Integer op = ((Alarm) obj).getOperationAction();
        operationAction = OperationAction.getMessageType(op);
        if(alarm.getType().equals("alarm")){
        	AlarmType alarmType = alarmTypeService.get(Long.valueOf(alarm.getTypeId()));
        	levelStr = alarmType.getColor().toUpperCase();
        	eventTypeCode = alarmType.getNationalCode();
        }else{
        	EventType eventType = eventTypeService.get(Long.valueOf(alarm.getTypeId()));
        	color = alarm.getAlarmTypeName().substring(alarm.getAlarmTypeName().indexOf("色")-1, alarm.getAlarmTypeName().indexOf("色")+1);
        	eventTypeCode = eventType.getNationalCode();
        	 if(color.contains("蓝")) {
        		 levelStr = "BLUE";
             } else if (color.contains("黄")) {
            	 levelStr = "YELLOW";
             } else if (color.contains("橙")) {
            	 levelStr = "ORANGE";
             } else if (color.contains("红")) {
            	 levelStr = "RED";
             } 
        }
        
        StringBuffer buff = new StringBuffer();
        
        if(StringUtils.isEmpty(obj.getMsgId())) {
            buff.append(senderCode);
            buff.append('_');
            buff.append(timeStampStr);
        } else {
            buff.append(obj.getMsgId());
        }
        
        buff.append('_');
        buff.append(eventTypeCode);
        buff.append('_');
        buff.append(levelStr);
        buff.append('_');
        buff.append(operationAction);
        return buff.toString();
    }
    
    public String makeMsgId(Long creatorId, Date currentDate) {
        return senderCodeByUserId(creatorId) + "_" + sdf1.format(currentDate);
    }
    
    public static void main(String[] args) {
    	System.out.println(OperationAction.Update.ordinal());
	}
}
