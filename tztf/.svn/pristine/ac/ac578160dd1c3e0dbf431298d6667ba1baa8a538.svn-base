package cn.movinginfo.tztf.sev.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.IpAddressUtil;
import cn.movinginfo.tztf.common.utils.TokenUtil;
import cn.movinginfo.tztf.releasechannel.ChannelReactorImpl;
import cn.movinginfo.tztf.sen.domain.KeyPeople;
import cn.movinginfo.tztf.sen.domain.PersonType;
import cn.movinginfo.tztf.sen.service.KeyPeopleService;
import cn.movinginfo.tztf.sen.service.PersonTypeService;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.Relay;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.RelayService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.Record;
import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.*;
import cn.movinginfo.tztf.sys.shiro.ShiroDBRealm.ShiroUser;
import cn.movinginfo.tztf.webserviceClient.sms.SendSMsgService;
import cn.movinginfo.tztf.webserviceClient.sms.SendSMsgServiceService;
import cn.movinginfo.tztf.webserviceClient.sms.ServiceException_Exception;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @description:Alarm action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/sev/check")
public class CheckAction extends MagicAction<Alarm, AlarmService> {
    // 发布渠道适配器
    @Autowired
    private ChannelReactorImpl channelReactor;
    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private ReleaseChannelInstanceService releaseChannelInstanceService;

    @Autowired
    private ReleaseChannelService releaseChannelService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private EmergencyInformationService emergencyInformationService;

    @Autowired
    private PersonTypeService personTypeService;

    @Autowired
    private KeyPeopleService keyPeopleService;

    @Resource
    private RelayService relayService;

    @RequestMapping(value = "getCheck")
    public void getCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Alarm alam = new Alarm();
        if (!StringUtils.isEmpty(id)) {
            alam = entityService.get(Long.valueOf(id));
        }
        boolean data = true;
        printJson(response, com.alibaba.fastjson.JSON.toJSONString(alam));
    }

    //审核界面
    @RequestMapping(value = "doCheck")
    public String doCheck(HttpServletRequest request, Model model, String id) throws Exception {
        Alarm alam = entityService.get(Long.valueOf(id));
        String publishtype = alarmService.getPublishType(Long.valueOf(id));
        String channel = "";
        // List<ReleaseChannelInstance> releaseChannelInstances =
        // releaseChannelInstanceService.queryByNumber(alam.getPubNo(),alam.getVersion());
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByNumber2(alam.getPubNo(),
                alam.getVersion());
        model.addAttribute("releaList", releaList);
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Depart dept = deptService.get(alam.getDeptId());
        alam.setDeptName(dept.getAddress());
        String type = alam.getAlarmTypeName().substring(0, alam.getAlarmTypeName().length() - 2);
        String level = alam.getAlarmTypeName().substring(alam.getAlarmTypeName().length() - 2,
                alam.getAlarmTypeName().length());
        // model.addAttribute("releaseChannel", releaseChannelService.getAll());
        // model.addAttribute("channel",channel.substring(0,
        // channel.length()-1));
        model.addAttribute("pubTime", df.format(alam.getPubDate()));
        model.addAttribute("alam", alam);
        model.addAttribute("type", type);
        model.addAttribute("level", level);
        model.addAttribute("publishtype", publishtype);
        model.addAttribute("id", id);
        // 所有有效渠道
        List<ReleaseChannel> channels = releaseChannelService.getByKettle();
        Collections.sort(channels, new Comparator<ReleaseChannel>() {

            @Override
            public int compare(ReleaseChannel o1, ReleaseChannel o2) {
                if (o1.getSortNumber() < o2.getSortNumber()) {
                    return -1;
                }
                return 0;
            }
        });
        model.addAttribute("releaseChannel", channels);
        String theContent = alam.getContent();
        model.addAttribute("theContent", theContent);
        return getNameSpace() + "doCheck";
    }


    @RequestMapping(value = "getText")
    public void getData(HttpServletRequest request, HttpServletResponse response, String pubNo, int version)
            throws IOException {
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByVersion(pubNo, version);
        List<Map<String, String>> content = new ArrayList<Map<String, String>>();
        /*
         * SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd");
         * SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
         */
        for (int i = 0; i < releaList.size(); i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("content", releaList.get(i).getContent());
            map.put("channel", releaseChannelService.get(releaList.get(i).getChannelId()).getName());
            // map.put("time",df.format(releaList.get(i).getArriveTime()));
            // map.put("hour",sdf.format(releaList.get(i).getArriveTime()));
            content.add(map);
        }
        JSONObject obj = new JSONObject();
        obj.put("data", JSONArray.parseArray(JSONArray.toJSONString(content)));
        printJson(response, obj.toString());
    }

    @RequestMapping(value = "/getAlarm")
    @ResponseBody
    public Alarm getAlarm(String pubNo) {
        return alarmService.getAlarmByPubNo(pubNo);
    }

    @RequestMapping(value = "detail")
    public String detail(HttpServletRequest request, Model model, Long id) throws Exception {

        Alarm alarm = entityService.get(Long.valueOf(id));
        String tN = alarm.getAlarmTypeName();
        String level = tN.substring(tN.length() - 2, tN.length());
        String type = tN.substring(0, tN.indexOf(level));
        model.addAttribute("level", level);
        model.addAttribute("type", type);
        model.addAttribute("alarm", alarm);
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByNumber2(alarm.getPubNo(),
                alarm.getVersion());
        if (releaList.size() != 0) {
            //遍历发布渠道
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        return getNameSpace() + "detail";
    }

    @RequestMapping(value = "detailler")
    public String detailler(HttpServletRequest request, Model model, Long id) throws Exception {

        Alarm alarm = entityService.get(Long.valueOf(id));
        String tN = alarm.getAlarmTypeName();
        String level = tN.substring(tN.length() - 2, tN.length());
        String type = tN.substring(0, tN.indexOf(level));
        model.addAttribute("level", level);
        model.addAttribute("type", type);
        model.addAttribute("alarm", alarm);
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByNumber2(alarm.getPubNo(),
                alarm.getVersion());
        if (releaList.size() != 0) {
            //遍历发布渠道
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        return getNameSpace() + "detailler";
    }


    @RequestMapping(value = "check")
    public String check(HttpServletRequest request, Model model) throws Exception {
        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        Depart depart = deptService.findById(departId);
        String areaId = depart.getAreaId();
        if (areaId.equals("1")) {
            //待办
            List<Alarm> listAlarm = alarmService.getListByDeaprtIdHZ(areaId);
            for (int i = 0; i < listAlarm.size(); i++) {
                Depart dept = deptService.get(listAlarm.get(i).getDeptId());
                listAlarm.get(i).setDeptName(dept.getAddress());
            }
            String pubState = "12";
            List<Alarm> publishAlarm = alarmService.getListPublishOneMonthAlarmAll(pubState, areaId);
            //一个月
            model.addAttribute("publishAlarm", publishAlarm);
            // 正在发布
            List<Alarm> listNowAlarm = alarmService.getListIsPublishing();
            model.addAttribute("listNowAlarm", listNowAlarm);
            // 待办信息
            model.addAttribute("listAlarm", listAlarm);
        } else {
            //待办
            List<Alarm> listAlarm = alarmService.getListByDeaprtIdAndAreaId(areaId);
            for (int i = 0; i < listAlarm.size(); i++) {
                Depart dept = deptService.get(listAlarm.get(i).getDeptId());
                listAlarm.get(i).setDeptName(dept.getAddress());
            }
            String pubState = "12";
            List<Alarm> publishAlarm = alarmService.getListPublishOneMonthAlarmAll(pubState, areaId);
            // 一个月
            model.addAttribute("publishAlarm", publishAlarm);
            // 正在发布
            List<Alarm> listNowAlarm = alarmService.getListNowAlarmPublish(areaId);
            //		Alarm alarm = listNowAlarm.get(0);
            //		String title = alarm.getTitle();
            model.addAttribute("listNowAlarm", listNowAlarm);
            // 正在发布
            model.addAttribute("listAlarm", listAlarm);
        }
        String deptNameS = "";
        Depart dept = deptService.get(departId);
        if (dept.getName().indexOf("应急办") > -1) {
            deptNameS = "yjb";
        }
        model.addAttribute("deptName", deptNameS);
        return getNameSpace() + "check";
    }

    //预警转发（发布成功的）
    @RequestMapping(value = "checker")
    public String checker(HttpServletRequest request, Model model) throws Exception {
        //获取用户登录的id
        User user = userService.get(getCurrentUser().id);
        //根据用户的id得到部门的id
        Long departId = user.getDepartmentId();
        Depart depart = deptService.findById(departId);
        //根据部门id获取
        String areaId = depart.getAreaId();
        //根据部门id获取town_id
        String townId = depart.getTownId();
        //遍历短信分组
        List<PersonType> peopleGroups = personTypeService.getTypeInId();//信息员与网络责任人
        model.addAttribute("peopleGroups", peopleGroups);
        System.out.println("111" + peopleGroups);

        if (areaId.equals("1")) {
//			//待办
            List<Alarm> listAlarm = alarmService.getListByDeaprtIdHZ(areaId);
//			for (int i = 0; i < listAlarm.size(); i++) {
//				Depart dept = deptService.get(listAlarm.get(i).getDeptId());
//				listAlarm.get(i).setDeptName(dept.getAddress());
//			}
            String pubState = "12";
            List<Alarm> publishAlarm = alarmService.getListPublishOneMonthAlarmAll(pubState, areaId);
            //一个月
            model.addAttribute("publishAlarm", publishAlarm);
            // 正在发布
            List<Alarm> listNowAlarm = alarmService.getListIsPublishing();
            model.addAttribute("listNowAlarm", listNowAlarm);
            //发布成功并且处于待转发状态的
            List<Alarm> listRelayAlarm = alarmService.getListIsPublishRelay(townId);
            model.addAttribute("listRelayAlarm", listRelayAlarm);
            //发布成功已经转发
            List<Alarm> relaySuccess = alarmService.getRelaySucess(townId);
            model.addAttribute("relaySuccess", relaySuccess);
//			// 待办信息
            //	model.addAttribute("listAlarm", listAlarm);
        } else {
            //待办
            List<Alarm> listAlarm = alarmService.getListByDeaprtIdAndAreaId(areaId);
//			for (int i = 0; i < listAlarm.size(); i++) {
//				Depart dept = deptService.get(listAlarm.get(i).getDeptId());
//				listAlarm.get(i).setDeptName(dept.getAddress());
//			}
            String pubState = "12";
            List<Alarm> publishAlarm = alarmService.getListPublishOneMonthAlarmAll(pubState, areaId);
            // 一个月
            model.addAttribute("publishAlarm", publishAlarm);
            // 正在发布
            List<Alarm> listNowAlarm = alarmService.getListNowAlarmPublish(areaId);
            //		Alarm alarm = listNowAlarm.get(0);
            //		String title = alarm.getTitle();
            model.addAttribute("listNowAlarm", listNowAlarm);
            List<Alarm> listRelayAlarm = alarmService.getListIsPublishRelay(townId);
            //待转发
            model.addAttribute("listRelayAlarm", listRelayAlarm);
            List<Alarm> relaySuccess = alarmService.getRelaySucess(townId);
            model.addAttribute("relaySuccess", relaySuccess);
//			System.out.println("有值吗："+relaySuccess);
            // 正在发布
            model.addAttribute("listAlarm", listAlarm);
        }
        String deptNameS = "";
        Depart dept = deptService.get(departId);
        if (dept.getName().indexOf("应急办") > -1) {
            deptNameS = "yjb";
        }
        model.addAttribute("deptName", deptNameS);
        return getNameSpace() + "checker";
    }


    @RequestMapping(value = "getDoCheck")
    public void getDoCheck(HttpServletRequest request, HttpServletResponse response, Long departId) throws IOException {
        User user = userService.get(getCurrentUser().id);
        Depart depart = deptService.get(departId);
        String areaId = depart.getAreaId();
        List<Alarm> releaList = alarmService.getListByDeaprtId(departId, areaId);
        JSONObject obj = new JSONObject();
        obj.put("data", JSONArray.parseArray(JSONArray.toJSONString(releaList)));
        printJson(response, obj.toString());
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public void confirm(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            Record record = new Record();//日志
            User user = userService.get(getCurrentUser().id);
            Long userIds = user.getId();
            Depart dept = deptService.getByDepartmentId(user.getDepartmentId());
            String token = dept.getToken();
            String userName = user.getName();
            String id = request.getParameter("id");
            String[] releaseChannel = request.getParameterValues("channelCheckBoxes");
            Alarm alarm = alarmService.get(Long.parseLong(id));
            String contents = alarm.getContent();
            alarm.setAuditDate(new Date());
            alarm.setReleaseDate(new Date());
            alarm.setCompleteDate(new Date());
            record.setUserName(userName);
            record.setUserId(userIds);
            record.setContent(alarm.getTitle() + "的审核通过了");
            record.setOperateDate(new Date());
            record.setIpAddress(IpAddressUtil.getIpAddr(request));//添加本机的ip地址
            recordService.saveRcord(record);
//			TokenUtil.GetToken(request, token, contents);//一旦审核通过就会发到自己的发布中心群里
            TokenUtil.sendContent(token, contents);
            if (alarm.getVersion() == 1) {
                alarm.setPubState("12");
            } else {
                alarm.setPubState("5");
            }
            alarmService.saveOrUpdate(alarm);
            if (releaseChannel != null) {
                List<ReleaseChannelInstance> oldReleaseChannelInstances = releaseChannelInstanceService
                        .getChannelList(alarm.getPubNo(), alarm.getType(), alarm.getVersion());
                for (ReleaseChannelInstance orci : oldReleaseChannelInstances) {
                    releaseChannelInstanceService.deleteById(orci.getId());
                }
                for (int j = 0; j < releaseChannel.length; j++) {
                    String value = releaseChannel[j];
                    String[] val2 = value.split("_");
                    String nameEn = val2[val2.length - 1];
                    String content = request.getParameter(value).trim();
                    ReleaseChannelInstance releaseChannelInstance = new ReleaseChannelInstance();
                    releaseChannelInstance.setChannelId(releaseChannelService.getByNameEn(nameEn).getId());
                    releaseChannelInstance.setContent(content);
                    releaseChannelInstance.setReleaseState("1");
                    releaseChannelInstance.setVersion(alarm.getVersion());
                    releaseChannelInstance.setSenderType(alarm.getType());
                    releaseChannelInstance.setSenderNumber(alarm.getPubNo());
                    releaseChannelInstance.setCreator(this.getCurrentUser().id);
                    releaseChannelInstance.setCreateDate(new Date());
                    releaseChannelInstance.setAlarmId(alarm.getId());
                    releaseChannelInstanceService.saveOrUpdate(releaseChannelInstance);
                }
            }
            alarm = alarmService.get(Long.parseLong(id));
            channelReactor.doRelease(alarm.getChannelInstances(), alarm);
            Depart depart = deptService.get(alarm.getDeptId());
            String phone = depart.getPhone();
            alarmService.sendSmsMessage(phone, "您递交的" + alarm.getAlarmTypeName() + "预警发布信息，已经审核通过", 0);
            printJson(response, messageSuccuseWrap());
        } catch (Exception e) {
            logger.error("save", e);
            printJson(response, messageFailureWrap("发布失败！"));
        }
    }

    @RequestMapping(value = "noConfirm", method = RequestMethod.POST)
    public void noConfirm(HttpServletRequest request, HttpServletResponse response) {
        try {
            Alarm o = bindEntity(request, entityClass);
            if (o.getVersion() == 1) {
                o.setPubState("9");
            } else {
                o.setPubState("2");
            }
            o.setAuditDate(new Date());
            alarmService.saveOrUpdate(o);
            Long msgIndex = null;
            o = alarmService.get(o.getId());
            Depart depart = deptService.get(o.getDeptId());
            String phone = depart.getPhone();
            msgIndex = alarmService.sendSmsMessage(phone, "您递交的" + o.getAlarmTypeName() + "预警发布信息，因" + o.getCheckContent() + "原因被驳回，请及时登入http://21.15.116.61:8080/修改！", 0);
            printJson(response, messageSuccuseWrap());
        } catch (Exception e) {
            logger.error("save", e);
            printJson(response, messageFailureWrap("发布失败！"));
        }
    }

    @RequestMapping(value = "checkExternal")
    public String checkExternal(HttpServletRequest request, Model model) throws Exception {
        String username = "杭州市气象台";
        if (this.getCurrentUser() == null) {
            Depart dept = deptService.findByName(username);
            User user = userService.findUserByDepartment(dept.getId());
            if (user != null) {
                byte[] salt = Hex.decode(user.getSalt());
                ShiroUser user2 = new ShiroUser(user.getId(), user.getUserName(), user.getName());
                this.setCurrentUser(user2);
                new SimpleAuthenticationInfo(user2,
                        user.getPassword(), ByteSource.Util.bytes(salt), "createExternalAlarm");
            } else {
                return null;
            }
        }
        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        Depart depart = deptService.get(departId);
        List<Alarm> listAlarm = alarmService.getListByDeaprtId(departId, depart.getAreaId());
        for (int i = 0; i < listAlarm.size(); i++) {
            Depart dept = deptService.get(listAlarm.get(i).getDeptId());
            listAlarm.get(i).setDeptName(dept.getAddress());
        }
        String pubState = "12";
        List<Alarm> publishAlarm = alarmService.getListPublishOneMonthAlarmAll(pubState, depart.getAreaId());
        model.addAttribute("publishAlarm", publishAlarm);
        // 正在发布
        List<Alarm> listNowAlarm = alarmService.getListNowAlarmPublish(depart.getAreaId());
        model.addAttribute("listNowAlarm", listNowAlarm);
        // 正在发布
        model.addAttribute("listAlarm", listAlarm);
        String deptNameS = "";
        Depart dept = deptService.get(departId);
        if (dept.getName().indexOf("应急办") > -1) {
            deptNameS = "yjb";
        }
        model.addAttribute("deptName", deptNameS);
        return getNameSpace() + "checkExternal";
    }


    //转发修改保存
    @RequestMapping(value = "getData", method = RequestMethod.GET)
    @ResponseBody
    //depart:people的id
    public String getData(String smsGroup, Long id) {
        //修改成已转发状态
//        alarmService.updateIsRelay(id);
        //创建一个保存信息的对象
//		ForwardAlarm forwardAlarm = new ForwardAlarm();
//		//从shiro取出用户登录的id
//		ShiroDBRealm.ShiroUser shiroDBRealm=(ShiroDBRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
//		Long  userId = shiroDBRealm.getId();
//		forwardAlarm.setUserId(userId);
//		forwardAlarm.setAlarmId(id);
//		forwardAlarm.setSmsGroup(smsGroup);
//		forwardAlarm.setValid(1);
//		//调用添加的方法，把数据存入数据表中
//		forwardAlarmService.saveForwardAlarm(forwardAlarm);
        Alarm alarm = alarmService.getRelayAlarm(id);
        //获取乡镇id
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
        //修改转发状态
        Relay relay = relayService.selectByAlarmTown(alarm.getId().intValue(), Integer.valueOf(depart.getTownId()));
        relay.setIsRelay(1);
        relayService.saveOrUpdate(relay);
        //通过smsgroup去获取人员手机号
        List<String> tels = new ArrayList();
        if (!(smsGroup.equals(""))) {
            String[] smsGroups = smsGroup.split(",");
            for (int i = 0; i < smsGroups.length; i++) {
                List<KeyPeople> list = keyPeopleService.getKeyPeopleByPersonId(Long.parseLong(smsGroups[i]));
//			 List<EmergencyInformation> emergencyInformations = emergencyInformationService.getEmergencyInfoAll(Long.parseLong(smsGroups[i]));
//			 for (EmergencyInformation e : emergencyInformations) {
//				 tels.add(e.getPhone());
//			 }
                for (KeyPeople kp : list) {
                    tels.add(kp.getPhone());
                }
            }
            try {
                SendSMsgServiceService sendSMsgServiceService = new SendSMsgServiceService();
                SendSMsgService port = sendSMsgServiceService.getSendSMsgServicePort();
                int result = port.sendMsg(tels, alarm.getContent());
                if (result == 0) {
                    System.out.println("成功");
                }
            } catch (ServiceException_Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return "1";
    }
}
