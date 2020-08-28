package cn.movinginfo.tztf.sys.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.Relay;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.RelayService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.domain.Feedback;
import cn.movinginfo.tztf.sys.service.*;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

/**
 * 信息反馈
 */
@Controller
@RequestMapping("/sys/feedback")
@SuppressWarnings("serial")
public class FeedbackAction extends MagicAction<Feedback, FeedbackService> {

    @Resource
    private UserService userService;

    @Resource
    private DeptService deptService;

    @Resource
    private DictService dictService;

    @Resource
    private FeedbackService feedbackService;

    @Resource
    private AlarmService alarmService;

    @Resource
    private ReleaseChannelInstanceService releaseChannelInstanceService;

    @Resource
    private ReleaseChannelService releaseChannelService;

    @Resource
    private RelayService relayService;

    /**
     * 查询反馈列表
     *
     * @param request 参数
     * @param model   返回值
     * @return 页面
     */
    @RequestMapping(value = "/feedbackQuery")
    public String feedbackQuery(HttpServletRequest request, Model model) {
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
        model.addAttribute("detId", deptId);
        model.addAttribute("depart", depart);
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        // 上报部门
        String type = depart.getDeptType();
        String areaId = depart.getAreaId();
        if (areaId.equals("1")) {
            List<Depart> depts = deptService.queryListByType(type);
            model.addAttribute("depts", depts);
        } else {
            List<Depart> depts = deptService.queryListByAreaAndType(areaId, type);
            model.addAttribute("depts", depts);
        }
        // 发布状态、事件状态
        model.addAttribute("pubStates", dictService.getDictsByKey(DictKeys.PUB_STATE).values());
        //区域选择
        if (areaId.equals("1")) {
            if ("18".equals(String.valueOf(deptId))) {
                model.addAttribute("areas", dictService.getDictsByKey(DictKeys.AREA_ID).values());
            } else {
                String area = depart.getAreaId();
                List<Dict> areas = dictService.getDicList("area_id");
                List<Dict> areass = new ArrayList<Dict>();
                areass.add(areas.get(1 - Integer.parseInt(area)));
                model.addAttribute("areas", areass);
            }
        } else {
            String area = depart.getAreaId();
            List<Dict> areas = dictService.getDicList("area_id");
            List<Dict> areass = new ArrayList<Dict>();
            areass.add(areas.get(1 - Integer.parseInt(area)));
            model.addAttribute("areas", areass);
        }
        //查询类型级别列表
        List<String> types = alarmService.selectAlarmType();
        model.addAttribute("types", types);
        return "/sys/feedback/feedbackQuery";
    }

    /**
     * 信息反馈查询列表数据
     *
     * @param request 参数
     * @param model   返回值
     * @return 页面
     */
    @RequestMapping(value = "feedbackData")
    public String feedbackData(HttpServletRequest request, Model model) {
        // 获取历史预警数据
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
//        String areaId = depart.getAreaId();
        String pageNum = request.getParameter("page");
//        String pubState = request.getParameter("pubState");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String alarmTypeName = request.getParameter("alarmTypeName");
        Timestamp start = null;
        if (startDate != null && !startDate.equals("")) {
            Date startTime = DateUtil.parse(startDate, "yyyy-MM-dd");
            assert startTime != null;
            start = new Timestamp(startTime.getTime());
        }
        Timestamp end = null;
        if (endDate != null && !endDate.equals("")) {
            Date endTime = DateUtil.parse(endDate, "yyyy-MM-dd");
            assert endTime != null;
            end = new Timestamp(endTime.getTime());
        }
        PageInfo<Alarm> pageInfo = feedbackService.selectTownAlarm(depart.getTownId(), alarmTypeName, null, start, end, pageNum == null ? String.valueOf(1) : pageNum);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        model.addAttribute("alarmTypeName", alarmTypeName);
        model.addAttribute("deptId", deptId);
        model.addAttribute("departList", deptService.queryListAllVlue());
        return "/sys/feedback/feedbackData";
    }

    /**
     * 信息反馈详情页
     *
     * @param alarmId 预警id
     * @param model   返回值
     * @return 页面
     */
    @RequestMapping(value = "feedbackAlarmDetail")
    public String feedbackAlarmDetail(HttpServletRequest request, HttpServletResponse response, String alarmId,
                                      Model model) {
        //获取乡镇id
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
        Alarm alarm = alarmService.getAlarmById(Long.valueOf(alarmId));
        //判断是上级还是乡镇
        String deptType = depart.getDeptType();
        if (deptType.equals("2")) {  //上级
            List<Relay> list = relayService.selectByAlarmId(alarm.getId().intValue());
            String msg = "";
            if (list != null && list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    Depart depart1 = deptService.findByTownId(String.valueOf(list.get(i).getTownId()));
                    if (depart1 != null) {
                        if (i != list.size() - 1) {
                            msg += depart1.getName() + ":" + list.get(i).getMessage() + ",";
                        } else {
                            msg += depart1.getName() + ":" + list.get(i).getMessage() + "";
                        }
                    }
                }
                alarm.setMessage(msg);
            } else {
                alarm.setMessage("");
            }
        } else if (deptType.equals("3")) {  //乡镇
            Relay relay = relayService.selectByAlarmTown(alarm.getId().intValue(), Integer.valueOf(depart.getTownId()));
            if (relay != null) {
                alarm.setMessage(relay.getMessage());
            }
        }
        String tN = alarm.getAlarmTypeName();
        String level = tN.substring(tN.length() - 2, tN.length());
        String type = tN.substring(0, tN.indexOf(level));
        model.addAttribute("level", level);
        model.addAttribute("type", type);
        model.addAttribute("alarm", alarm);
        model.addAttribute("deptType", deptType);
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.findChannelByalarmId(Long.parseLong(alarmId));
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        return "/sys/feedback/feedbackAlarmDetail";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> update(@RequestBody Alarm alarm) {
        Map<String, Object> result = new HashMap<>();
        //获取乡镇id
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
        if (alarm.getId() == null) {
            result.put("code", 1);
            return result;
        }
        //处理反馈信息
        Relay relay = relayService.selectByAlarmTown(alarm.getId().intValue(), Integer.valueOf(depart.getTownId()));
        relay.setIsFeedback(1);
        relay.setMessage(alarm.getMessage());
        relayService.saveOrUpdate(relay);
//        Feedback feedback = new Feedback();
//        feedback.setAddTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        feedback.setAlarmId(alarm.getId().intValue());
//        feedback.setMessage(alarm.getMessage());
//        feedback.setUserId(this.getCurrentUser().id.intValue());
//        Map<String, String> map = new HashMap<>();
//        map.put("user_id", this.getCurrentUser().id.toString());
//        map.put("alarm_id", alarm.getId().toString());
//        List<Feedback> list = feedbackService.query(map);
//        if (list != null && list.size() != 0) {
//            list.get(0).setMessage(alarm.getMessage());
//            feedbackService.saveOrUpdate(list.get(0));
//        } else {
//            feedbackService.saveOrUpdate(feedback);
//        }
        result.put("code", 0);
        return result;
    }

    /**
     * 查询历史预警列表
     *
     * @param request 参数
     * @param model   返回值
     * @return 页面
     */
    @RequestMapping(value = "/historyQuery")
    public String historyQuery(HttpServletRequest request, Model model) {
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
        model.addAttribute("detId", deptId);
        model.addAttribute("depart", depart);
        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        // 上报部门
        String type = depart.getDeptType();
        String areaId = depart.getAreaId();
        if (areaId.equals("1")) {
            List<Depart> depts = deptService.queryListByType(type);
            model.addAttribute("depts", depts);
        } else {
            List<Depart> depts = deptService.queryListByAreaAndType(areaId, type);
            model.addAttribute("depts", depts);
        }
        // 发布状态、事件状态
        model.addAttribute("pubStates", dictService.getDictsByKey(DictKeys.PUB_STATE).values());
        //区域选择
        if (areaId.equals("1")) {
            if ("18".equals(String.valueOf(deptId))) {
                model.addAttribute("areas", dictService.getDictsByKey(DictKeys.AREA_ID).values());
            } else {
                String area = depart.getAreaId();
                List<Dict> areas = dictService.getDicList("area_id");
                List<Dict> areass = new ArrayList<Dict>();
                areass.add(areas.get(1 - Integer.parseInt(area)));
                model.addAttribute("areas", areass);
            }
        } else {
            String area = depart.getAreaId();
            List<Dict> areas = dictService.getDicList("area_id");
            List<Dict> areass = new ArrayList<Dict>();
            areass.add(areas.get(1 - Integer.parseInt(area)));
            model.addAttribute("areas", areass);
        }
        //查询类型级别列表
        List<String> types = alarmService.selectAlarmType();
        model.addAttribute("types", types);
        return "/sys/feedback/historyQuery";
    }

    /**
     * 历史预警查询列表数据
     *
     * @param request 参数
     * @param model   返回值
     * @return 页面
     */
    @RequestMapping(value = "historyData")
    public String historyData(HttpServletRequest request, Model model) {
        // 获取历史预警数据
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
//        String areaId = depart.getAreaId();
        String pageNum = request.getParameter("page");
//        String pubState = request.getParameter("pubState");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String alarmTypeName = request.getParameter("alarmTypeName");
        Timestamp start = null;
        if (startDate != null && !startDate.equals("")) {
            Date startTime = DateUtil.parse(startDate, "yyyy-MM-dd");
            assert startTime != null;
            start = new Timestamp(startTime.getTime());
        }
        Timestamp end = null;
        if (endDate != null && !endDate.equals("")) {
            Date endTime = DateUtil.parse(endDate, "yyyy-MM-dd");
            assert endTime != null;
            end = new Timestamp(endTime.getTime());
        }
        PageInfo<Alarm> pageInfo = feedbackService.selectHistoryAlarm(depart.getTownId(), alarmTypeName, null, start, end, pageNum == null ? String.valueOf(0) : pageNum);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        model.addAttribute("alarmTypeName", alarmTypeName);
        model.addAttribute("deptId", deptId);
        model.addAttribute("departList", deptService.queryListAllVlue());
        return "/sys/feedback/historyData";
    }

    /**
     * 历史预警详情页
     *
     * @param alarmId 预警id
     * @param model   返回值
     * @return 页面
     */
    @RequestMapping(value = "releaseNowAlarm")
    public String releaseNowAlarm(HttpServletRequest request, HttpServletResponse response, String alarmId,
                                  Model model) {
        //获取乡镇id
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
        Alarm alarm = alarmService.getAlarmById(Long.valueOf(alarmId));
        //判断是上级还是乡镇
        String deptType = depart.getDeptType();
        if (deptType.equals("2")) {  //上级
            List<Relay> list = relayService.selectByAlarmId(alarm.getId().intValue());
            String msg = "";
            if (list != null && list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    Depart depart1 = deptService.findByTownId(String.valueOf(list.get(i).getTownId()));
                    if (depart1 != null) {
                        if (i != list.size() - 1) {
                            msg += depart1.getName() + ":" + list.get(i).getMessage() + ",";
                        } else {
                            msg += depart1.getName() + ":" + list.get(i).getMessage() + "";
                        }
                    }
                }
                alarm.setMessage(msg);
            } else {
                alarm.setMessage("");
            }
        } else if (deptType.equals("3")) {  //乡镇
            Relay relay = relayService.selectByAlarmTown(alarm.getId().intValue(), Integer.valueOf(depart.getTownId()));
            if (relay != null) {
                alarm.setMessage(relay.getMessage());
            }
        }
        String tN = alarm.getAlarmTypeName();
        String level = tN.substring(tN.length() - 2, tN.length());
        String type = tN.substring(0, tN.indexOf(level));
        model.addAttribute("level", level);
        model.addAttribute("type", type);
        model.addAttribute("alarm", alarm);
        model.addAttribute("deptType", deptType);
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.findChannelByalarmId(Long.parseLong(alarmId));
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        return "/sys/feedback/releaseNowAlarm";
    }

}
