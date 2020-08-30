package cn.movinginfo.tztf.sev.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.common.domain.GeoCoordinate;
import cn.movinginfo.tztf.common.enums.OperationAction;
import cn.movinginfo.tztf.common.utils.*;
import cn.movinginfo.tztf.releasechannel.ChannelReactorImpl;
import cn.movinginfo.tztf.releasechannel.support.NationalEmergencySupport;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.AlarmNotice;
import cn.movinginfo.tztf.sev.domain.Relay;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.*;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sys.domain.*;
import cn.movinginfo.tztf.sys.mapper.AreaMapper;
import cn.movinginfo.tztf.sys.service.*;
import cn.movinginfo.tztf.sys.shiro.ShiroDBRealm;
import cn.movinginfo.tztf.sys.shiro.ShiroDBRealm.ShiroUser;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.util.ByteSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.geom.Point2D;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:Alarm action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/sev/alarm")
public class AlarmAction extends MagicAction<Alarm, AlarmService> {
    private static Logger log = LoggerFactory.getLogger(AlarmAction.class);

    @Autowired
    private ReleaseChannelInstanceService releaseChannelInstanceService;
    @Autowired
    private DictService dictService;
    @Autowired(required = false)
    private ServletContext servletContext;
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private ReleaseChannelService releaseChannelService;
    @Autowired
    private AlarmTypeService alarmTypeService;
    @Autowired
    private EventTypeService eventTypeService;
    @Autowired
    private AlarmNoticeService alarmNoticeService;
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private DecisionService decisionService;

    @Autowired
    private DepartFaxService departFaxService;

    @Resource
    private RelayService relayService;

    // 发布渠道适配器
    @Autowired
    private ChannelReactorImpl channelReactor;
    @Autowired
    private ShiroDBRealm shiroDBRealm;

    private void loadPermission() {
        if (shiroDBRealm.getAuthorizationInfo(this.getCurrentUser()) == null) {
            shiroDBRealm.clearCachedAuthorizationInfo(this.getCurrentUser());
            shiroDBRealm.isPermitted(SecurityUtils.getSubject().getPrincipals(), Long.toString(System.currentTimeMillis()));
        }
    }

    /**
     * 历史预警 - 数据iframe页面，使用Velocity模板，进行分页展示
     * <p>
     *
     * @param request
     * @param model
     * @return
     * @author: zhangd
     * @createTime: 2017年8月24日 下午3:11:46
     * @updateTime:
     */
    @RequestMapping(value = "historyData")
    public String historyData(HttpServletRequest request, Model model) throws Exception {
        // 获取历史预警数据
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart depart = deptService.get(deptId);
        String areaId = depart.getAreaId();
        String pubState = request.getParameter("pubState");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Map<String, String> map = new HashMap<String, String>();
        if (depart.getDeptType().equals("2") || depart.getDeptType().equals("4")) {
            map = getParameterMap(request);
        } else {
            map = getParameterMap(request);
            map.put("deptId", Long.toString(deptId));
        }
        if (areaId.equals("1")) {
            if ("18".equals(String.valueOf(deptId))) {
                map = getParameterMap(request);
            } else {
                map = getParameterMap(request);
                map.put("deptId", String.valueOf(deptId));
            }
        } else {
            map = getParameterMap(request);
            map.put("areaId", areaId);
        }
        PageInfo<?> pageInfo = entityService.queryPaged(map);
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        model.addAttribute("areaId", areaId);
        model.addAttribute("deptId", deptId);
        model.addAttribute("departList", deptService.queryListAllVlue());
        System.out.println(getNameSpace());
        return getNameSpace() + "historyData";
    }

    /**
     * 历史预警 - 查询页面
     * <p>
     *
     * @param request
     * @param model
     * @return
     * @author: zhangd
     * @createTime: 2017年8月24日 下午3:11:00
     * @updateTime:
     */
    @RequestMapping(value = "historyQuery")
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
        return getNameSpace() + "historyQuery";
    }

    @Override
    protected void beforeIndex(HttpServletRequest request, Model model) {
        super.beforeIndex(request, model);
    }

    @RequestMapping(value = "warning")
    public String warning(HttpServletRequest request, Model model, String name, String areaId) throws Exception {
        Calendar a = Calendar.getInstance();
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH) + 1;
        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        Depart depart = deptService.get(departId);
        if (StringUtils.isEmpty(areaId)) {        // 刚加载时 传入空值   否则就是筛选条件
            areaId = depart.getAreaId();
        }

        model.addAttribute("year1", entityService.getNum(year, "蓝色", areaId));
        model.addAttribute("year2", entityService.getNum(year, "黄色", areaId));
        model.addAttribute("year3", entityService.getNum(year, "橙色", areaId));
        model.addAttribute("year4", entityService.getNum(year, "红色", areaId));
        model.addAttribute("year", entityService.getYear(year, areaId));
        model.addAttribute("month1", entityService.getMonth(year, month, "蓝色", areaId));
        model.addAttribute("month2", entityService.getMonth(year, month, "黄色", areaId));
        model.addAttribute("month3", entityService.getMonth(year, month, "橙色", areaId));
        model.addAttribute("month4", entityService.getMonth(year, month, "红色", areaId));
        model.addAttribute("month", entityService.getMonthAll(year, month, areaId));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        a.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        model.addAttribute("week1", entityService.getWeek(df.format(a.getTime()), "蓝色", areaId));
        model.addAttribute("week2", entityService.getWeek(df.format(a.getTime()), "黄色", areaId));
        model.addAttribute("week3", entityService.getWeek(df.format(a.getTime()), "橙色", areaId));
        model.addAttribute("week4", entityService.getWeek(df.format(a.getTime()), "红色", areaId));
        model.addAttribute("week", entityService.getWeekAll(df.format(a.getTime()), areaId));
        model.addAttribute("eventWeek1", entityService.getEventWeek(df.format(a.getTime()), 1, areaId));
        model.addAttribute("eventWeek2", entityService.getEventWeek(df.format(a.getTime()), 78, areaId));
        model.addAttribute("eventWeek3", entityService.getEventWeek(df.format(a.getTime()), 220, areaId));
        model.addAttribute("eventWeek4", entityService.getEventWeek(df.format(a.getTime()), 226, areaId));
        model.addAttribute("eventYear1", entityService.getEventYear(year, 1, areaId));
        model.addAttribute("eventYear2", entityService.getEventYear(year, 78, areaId));
        model.addAttribute("eventYear3", entityService.getEventYear(year, 220, areaId));
        model.addAttribute("eventYear4", entityService.getEventYear(year, 226, areaId));
        model.addAttribute("eventMonth1", entityService.getEventMonth(year, month, 1, areaId));
        model.addAttribute("eventMonth2", entityService.getEventMonth(year, month, 78, areaId));
        model.addAttribute("eventMonth3", entityService.getEventMonth(year, month, 220, areaId));
        model.addAttribute("eventMonth4", entityService.getEventMonth(year, month, 226, areaId));
        model.addAttribute("years", new Integer[]{year, year - 1, year - 2});
        List<Dict> dicList = dictService.getDicList("area_id");
        for (int i = 0; i < dicList.size(); i++) {
            if (dicList.get(i).getValue().equals(areaId)) {
                dicList.add(0, dicList.remove(i));
                break;
            }
        }

        List<String> list = new ArrayList<String>();
        for (Dict dict : dicList) {
            list.add(dict.getContent());
        }
        model.addAttribute("area", dicList);
        model.addAttribute("areaid", areaId);
        model.addAttribute("deptId", depart.getId());
        return getNameSpace() + "warning";
    }

    @RequestMapping(value = "home")
    public String home(HttpServletRequest request, Model model, String name) throws Exception {
        Calendar a = Calendar.getInstance();
        int year = a.get(Calendar.YEAR);
        int month = a.get(Calendar.MONTH) + 1;
        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        Depart depart = deptService.get(departId);
        String areaId = depart.getAreaId();
        model.addAttribute("year1", entityService.getNum(year, "蓝色", areaId));
        model.addAttribute("year2", entityService.getNum(year, "黄色", areaId));
        model.addAttribute("year3", entityService.getNum(year, "橙色", areaId));
        model.addAttribute("year4", entityService.getNum(year, "红色", areaId));
        model.addAttribute("year", entityService.getYear(year, areaId));
        model.addAttribute("month1", entityService.getMonth(year, month, "蓝色", areaId));
        model.addAttribute("month2", entityService.getMonth(year, month, "黄色", areaId));
        model.addAttribute("month3", entityService.getMonth(year, month, "橙色", areaId));
        model.addAttribute("month4", entityService.getMonth(year, month, "红色", areaId));
        model.addAttribute("month", entityService.getMonthAll(year, month, areaId));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        a.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        model.addAttribute("week1", entityService.getWeek(df.format(a.getTime()), "蓝色", areaId));
        model.addAttribute("week2", entityService.getWeek(df.format(a.getTime()), "黄色", areaId));
        model.addAttribute("week3", entityService.getWeek(df.format(a.getTime()), "橙色", areaId));
        model.addAttribute("week4", entityService.getWeek(df.format(a.getTime()), "红色", areaId));
        model.addAttribute("week", entityService.getWeekAll(df.format(a.getTime()), areaId));

        return getNameSpace() + "home";
    }

    /**
     * 预警制作跳转
     *
     * @param request
     * @param model
     * @param name
     * @return
     * @throws Exception
     * @author jinb
     */
    @RequestMapping(value = "createAlarm")
    public String craeteAlarm(HttpServletRequest request, Model model, String name) throws Exception {
        if (name != null) {
            model.addAttribute("name", "通州区气象台发布" + name.trim());
            model.addAttribute("typeL", name.trim());
        }

        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        String deptName = deptService.get(deptId).getName();
        List<ReleaseChannel> allReleaseChannel = releaseChannelService.getByKettle();

        Collections.sort(allReleaseChannel, new Comparator<ReleaseChannel>() {

            @Override
            public int compare(ReleaseChannel o1, ReleaseChannel o2) {
                if (o1.getSortNumber() < o2.getSortNumber()) {
                    return -1;
                }
                return 0;
            }
        });

        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        List<Depart> departList = deptService.getDepartByNotIdAndCenterId(departId);//联合部门
        int deptLength = departList.size();
        Depart depart = deptService.get(departId);
        String areaId = depart.getAreaId();
		

		/*List<ReleaseChannel> releaseChannels =  new ArrayList<ReleaseChannel>();
		for(ReleaseChannel releaseChannel:allReleaseChannel){
			if(releaseChannel.getParentId()==null){
				releaseChannels.add(releaseChannel);
			}
		}*/
        model.addAttribute("deptLength", 0);
        model.addAttribute("departList", departList);
        model.addAttribute("deptId", deptId);
        model.addAttribute("areaId", areaId);
        model.addAttribute("departmentName", deptName);
        model.addAttribute("alarmName", "alarm");
        model.addAttribute("alarmType", com.alibaba.fastjson.JSON.toJSONString(alarmTypeService.getAll()));
        model.addAttribute("alarmTypeName", alarmTypeService.getDistinctAlarmType());
        if (areaId.equals("0")) {
            model.addAttribute("areaAll", areaService.getCountryArea());
        } else {
            model.addAttribute("areaAll", areaService.getCountryAreaById(areaId));
        }

        List<Decision> decisionList = decisionService.findDecisionList(deptId);
        model.addAttribute("areaAllTown", areaService.getAll());
        model.addAttribute("releaseChannel", allReleaseChannel);
        model.addAttribute("departFax", departFaxService.getAll());
        model.addAttribute("decision", decisionList);
        return getNameSpace() + "createAlarm";
    }


    @RequestMapping(value = "checker")
    public String checker(HttpServletRequest request, Model model, String name) throws Exception {
        if (name != null) {
            model.addAttribute("name", "杭州市气象台发布" + name.trim());
            model.addAttribute("typeL", name.trim());
        }

        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        String deptName = deptService.get(deptId).getName();
        List<ReleaseChannel> allReleaseChannel = releaseChannelService.getByKettle();

        Collections.sort(allReleaseChannel, new Comparator<ReleaseChannel>() {

            @Override
            public int compare(ReleaseChannel o1, ReleaseChannel o2) {
                if (o1.getSortNumber() < o2.getSortNumber()) {
                    return -1;
                }
                return 0;
            }
        });

        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        List<Depart> departList = deptService.getDepartByNotIdAndCenterId(departId);//联合部门
        int deptLength = departList.size();
        Depart depart = deptService.get(departId);

        String areaId = depart.getAreaId();


		/*List<ReleaseChannel> releaseChannels =  new ArrayList<ReleaseChannel>();
		for(ReleaseChannel releaseChannel:allReleaseChannel){
			if(releaseChannel.getParentId()==null){
				releaseChannels.add(releaseChannel);
			}
		}*/
        model.addAttribute("deptLength", deptLength);
        model.addAttribute("departList", departList);
        model.addAttribute("deptId", deptId);
        model.addAttribute("areaId", areaId);
        model.addAttribute("departmentName", deptName);
        model.addAttribute("alarmName", "alarm");
        model.addAttribute("alarmType", com.alibaba.fastjson.JSON.toJSONString(alarmTypeService.getAll()));
        model.addAttribute("alarmTypeName", alarmTypeService.getDistinctAlarmType());
        if (areaId.equals("0")) {
            model.addAttribute("areaAll", areaService.getCountryArea());
        } else {
            model.addAttribute("areaAll", areaService.getCountryAreaById(areaId));
        }

        List<Decision> decisionList = decisionService.findDecisionList(deptId);
        model.addAttribute("areaAllTown", areaService.getAll());
        model.addAttribute("releaseChannel", allReleaseChannel);
        model.addAttribute("departFax", departFaxService.getAll());
        model.addAttribute("decision", decisionList);
        return getNameSpace() + "checker";
    }

    /**
     * 事件制作跳转
     *
     * @param request
     * @param model
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "createEvent")
    public String createEvent(HttpServletRequest request, Model model, String name) throws Exception {
        Long eventTypeId = Long.valueOf(name);
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        String deptName = deptService.get(deptId).getName();
        List<ReleaseChannel> allReleaseChannel = releaseChannelService.getSomeChannel();

            Collections.sort(allReleaseChannel, new Comparator<ReleaseChannel>() {

                @Override
                public int compare(ReleaseChannel o1, ReleaseChannel o2) {
                    if (o1.getSortNumber() < o2.getSortNumber()) {
                        return -1;
                    }
                    return 0;
                }
            });


        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        List<Depart> departList = deptService.getDepartByNotIdAndCenterId(departId);//联合部门
        for(Depart de : departList) {
        	if(de.getName().contains("气象")) {
        		de.setName(de.getName().replace("气象台", "气象局"));
        	}
        }
        int deptLength = departList.size();
        Depart depart = deptService.get(departId);

        String areaId = depart.getAreaId();
        model.addAttribute("deptLength", deptLength);
        model.addAttribute("departList", departList);
        model.addAttribute("deptId", deptId);
        model.addAttribute("areaId", areaId);
        model.addAttribute("departmentName", deptName);
        model.addAttribute("alarmName", "event");
        model.addAttribute("eventType", eventTypeService.getDeptEventType(deptId, eventTypeId));
        model.addAttribute("allEventType",
                com.alibaba.fastjson.JSON.toJSONString(eventTypeService.getDeptEventType(deptId, eventTypeId)));
        if (areaId.equals("0")) {
            model.addAttribute("areaAll", areaService.getCountryArea());
        } else {
            model.addAttribute("areaAll", areaService.getCountryAreaById(areaId));
        }
        List<Decision> decisionList = decisionService.findDecisionList(deptId);
        model.addAttribute("decision", decisionList);
        model.addAttribute("areaAllTown", areaService.getAll());
        model.addAttribute("releaseChannel", allReleaseChannel);
        model.addAttribute("departFax", departFaxService.getAll());
        return getNameSpace() + "createAlarm";
    }

    @RequestMapping(value = "getData")
    public void getData(HttpServletRequest request, HttpServletResponse response, int year, int month, int type, String areaId)
            throws IOException {
        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        Depart depart = deptService.get(departId);
        if (StringUtils.isEmpty(areaId)) {        // 刚加载时 传入空值   否则就是筛选条件
            areaId = depart.getAreaId();
        }

        List<Alarm> getData = new ArrayList<Alarm>();
        if (type == 0) {
            if ("1".equals(areaId)) {
                getData = entityService.getData(year, month);
            } else {
                getData = entityService.getOtherData(year, month, areaId);
            }
        } else {
            getData = entityService.getDataAll(year, month, type, areaId);
        }
        for (int i = 0; i < getData.size(); i++) {
            getData.get(i)
                    .setAlarmTypeName(dictService.getValue(DictKeys.ALARM_LEVEL, getData.get(i).getAlarmTypeName()));
        }
        JSONObject obj = new JSONObject();
        obj.put("lists", JSONArray.parseArray(JSONArray.toJSONString(getData)));
        printJson(response, obj.toString());
    }

    @RequestMapping(value = "getYear")
    public void getYear(HttpServletRequest request, HttpServletResponse response, int year, int type)
            throws IOException {
        List<Alarm> getYear = new ArrayList<Alarm>();
        if (type == 0) {
            getYear = entityService.getYearList(year);
        } else {
            getYear = entityService.getYearListByType(year, type);
        }
        for (int i = 0; i < getYear.size(); i++) {
            getYear.get(i)
                    .setAlarmTypeName(dictService.getValue(DictKeys.ALARM_LEVEL, getYear.get(i).getAlarmTypeName()));
        }
        JSONObject obj = new JSONObject();
        obj.put("lists", JSONArray.parseArray(JSONArray.toJSONString(getYear)));
        printJson(response, obj.toString());
    }

    @RequestMapping(value = "getDetail")
    public void getDetail(HttpServletRequest request, HttpServletResponse response, String level, String time)
            throws IOException {
        Long departmentId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart department = deptService.findById(departmentId);
        String areaId = department.getAreaId();
        String content1 = dictService.getContent(DictKeys.ALARM_LEVEL, level);
        if ("1".equals(areaId)) {
            List<Alarm> getDetail = alarmService.getDetail(time, content1);
            JSONObject obj = new JSONObject();
            List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时MM分");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/ HH:MM");
            for (int i = 0; i < getDetail.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                String content = getDetail.get(i).getAlarmTypeName();
                Depart depart = deptService.get(getDetail.get(i).getDeptId());
                map.put("jy", content + "预警");
                map.put("fbdw", depart.getAddress());
                map.put("cxsj", getDetail.get(i).getDuration());
                map.put("pub", sdf.format(getDetail.get(i).getPubDate()));
                map.put("title", getDetail.get(i).getTitle());
                map.put("time", df.format(getDetail.get(i).getPubDate()));
                map.put("id", getDetail.get(i).getId().toString());
                map.put("pubState", getDetail.get(i).getPubState());
                if (Integer.valueOf(getDetail.get(i).getPubState()) == 6) {
                    Alarm alarm = alarmService.getListByPubNo(getDetail.get(i).getPubNo());//QXJ20180001
                    if (alarm != null) {
                        map.put("cancel", sdf.format(alarm.getPubDate()));
                    }
                }
                detailList.add(map);
            }
            obj.put("lists", JSONArray.parseArray(JSONArray.toJSONString(detailList)));
            printJson(response, obj.toString());
        } else {
            List<Alarm> getDetail = entityService.getOtherDetail(time, content1, areaId);
            JSONObject obj = new JSONObject();
            List<Map<String, String>> detailList = new ArrayList<Map<String, String>>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时MM分");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/ HH:MM");
            for (int i = 0; i < getDetail.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                String content = getDetail.get(i).getAlarmTypeName();
                Depart depart = deptService.get(getDetail.get(i).getDeptId());
                map.put("jy", content + "预警");
                map.put("fbdw", depart.getAddress());
                map.put("cxsj", getDetail.get(i).getDuration());
                map.put("pub", sdf.format(getDetail.get(i).getPubDate()));
                map.put("title", getDetail.get(i).getTitle());
                map.put("time", df.format(getDetail.get(i).getPubDate()));
                map.put("id", getDetail.get(i).getId().toString());
                map.put("pubState", getDetail.get(i).getPubState());
                if (Integer.valueOf(getDetail.get(i).getPubState()) == 6) {
                    Alarm alarm = entityService.getListByPubNo(getDetail.get(i).getPubNo());//QXJ20180001
                    map.put("cancel", sdf.format(alarm.getPubDate()));
                }
                detailList.add(map);
            }
            obj.put("lists", JSONArray.parseArray(JSONArray.toJSONString(detailList)));
            printJson(response, obj.toString());
        }
    }

    /**
     * 预警存储
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "saveAlarm")
    @ResponseBody
    public String saveAlarm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
        System.out.println(alarm.getAlarmType());
        String result = entityService.checkAlarm(alarm);
        if (!result.equals("pass")) {
            return result;
        }
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart d = deptService.get(deptId);
        String departName = d.getName();
        String[] releaseChannel = request.getParameterValues("channelCheckBoxes");
        String[] group = request.getParameterValues("decisionCheckBoxes");
        String[] fax = request.getParameterValues("faxCheckBoxes");
        alarm.setAreaId(d.getAreaId());
        alarm.setCreator(this.getCurrentUser().id);
        alarm.setCreateDate(new Date());
        NationalEmergencySupport support = new NationalEmergencySupport();
        String msgId = support.makeMsgId(alarm.getCreator(), alarm.getCreateDate());
        alarm.setMsgId(msgId);
        alarm.setDeptId(deptId);
        alarm.setIsRelay(0);
        alarm.setKettle("0");
        String pubChannel = "";
        String smsGroup = "";
        String deptFax = "";
        for (int i = 0; i < releaseChannel.length; i++) {
            pubChannel += releaseChannel[i] + " ";
        }
        if (group != null) {
            for (int i = 0; i < group.length; i++) {
                smsGroup += group[i] + " ";
            }
        }

        if (fax != null) {
            for (int i = 0; i < fax.length; i++) {
                deptFax += fax[i] + " ";
            }
        }

        alarm.setPubChannel(pubChannel);
        alarm.setSmsGroup(smsGroup);
        alarm.setDepartFax(deptFax);
        alarm.setOperationAction(OperationAction.Alert.ordinal());
        
        entityService.saveAlarm(alarm, request);
        Alarm nextAlarm = entityService.passVersionAndCode(alarm.getVersion(), alarm.getPubNo());
        String qxjDept = ConfigHelper.getProperty("qxj_dept");
//		if (nextAlarm.getType().equals("alarm")) {
        if (departName.contains(qxjDept)) {//是否包含气象两个字段
            nextAlarm.setAuditDate(new Date());
            nextAlarm.setPubState("1");
            channelReactor.doRelease(nextAlarm.getChannelInstances(), nextAlarm);
            nextAlarm.setPubState("5");
            nextAlarm.setReleaseDate(new Date());
            entityService.saveOrUpdate(nextAlarm);
        }

//		return "redirect:/sev/reminder/detail";
        return "pass";
    }


    /**
     * 预警更新
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "updateAlarm")
    public String updateAlarm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
        alarm.setCreator(this.getCurrentUser().id);
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        alarm.setDeptId(deptId);
        entityService.updateAlarm(alarm, request);
        return "redirect:/sev/reminder/detail";
    }


    /**
     * 生成发布表单
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "createForm")
    public void createForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
//		System.out.println(alarm.getId());
        if (alarm.getId() != null) {
            alarm.setTypeId(entityService.get(alarm.getId()).getTypeId());
        }
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        alarm.setDeptId(deptId);
        String root = entityService.createForm(alarm, request);
        printJson(response, root);
    }


    /**
     * 生成对外传真
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "createFaxForm")
    public void createFaxForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
//		System.out.println(alarm.getId());
        if (alarm.getId() != null) {
            alarm.setTypeId(entityService.get(alarm.getId()).getTypeId());
        }
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        alarm.setDeptId(deptId);
        String root = entityService.createFaxForm(alarm, request);
        printJson(response, root);
    }


    @RequestMapping(value = "/getAlarm")
    @ResponseBody
    public Alarm getAlarm(String pubNo) {
        return alarmService.getAlarmByPubNo(pubNo);
    }

    /**
     * 生成变更表单
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "updateForm")
    public void updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
        alarm.setCreator(this.getCurrentUser().id);
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        alarm.setDeptId(deptId);
        String root = entityService.updateForm(alarm, request);
        printJson(response, root);
    }

    /**
     * 生成解除表单
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "createRemoveForm")
    public void createRemoveForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        alarm.setDeptId(deptId);
        String root = entityService.createRemoveForm(alarm, request);

        printJson(response, root);
    }


    /**
     * 解除预警信息列表
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "relieveAlarmList")
    public String relieveAlarmList(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        List<Alarm> list = entityService.getCompleteList(deptId);
        for (int i = 0; i < list.size(); i++) {
            Depart dept = deptService.get(list.get(i).getDeptId());
            list.get(i).setDeptName(dept.getAddress());
        }
        model.addAttribute("alarmList", list);
        return getNameSpace() + "relieveAlarmList";
    }

    /**
     * 解除预警 无用
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "removeAlarm")
    public String removeAlarm(HttpServletRequest request, HttpServletResponse response, String code, Model model)
            throws Exception {
        Alarm alarm = entityService.get(Long.valueOf(code));
        alarm.setPubState("6");
        alarm.setValid(0);
        Alarm newAlarm = entityService.get(Long.valueOf(code));
        newAlarm.setCreator(this.getCurrentUser().id);
        newAlarm.setId(null);
        newAlarm.setPubState("7");
        newAlarm.setVersion(1);
        entityService.saveOrUpdate(newAlarm);
        entityService.saveOrUpdate(alarm);
        return "redirect:/sev/alarm/relieveAlarmList";
    }

    /**
     * 解除预警的详细信息
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "detailsRemoveAlarm")
    public String detailsAlarm(HttpServletRequest request, HttpServletResponse response, String code, Model model)
            throws Exception {
        Long depid = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart d = deptService.getByDepartmentId(depid);
        String areaId = d.getAreaId();
        Alarm alarm = entityService.get(Long.valueOf(code));
        Depart dept = deptService.get(alarm.getDeptId());
        alarm.setDeptName(dept.getName());
        alarm.setDeptId(userService.get(this.getCurrentUser().id).getDepartmentId());
        model.addAttribute("alarm", alarm);
        model.addAttribute("departmentName", dept.getName());
        String alarmType = alarm.getAlarmTypeName();
        String level = alarmType.substring(alarmType.length() - 2, alarmType.length());
        String type = alarmType.substring(0, alarmType.indexOf(level));
        model.addAttribute("level", level);
        model.addAttribute("type", type);
        model.addAttribute("creator", userService.get(alarm.getCreator()).getName());
        // 审稿人和审核人
        List<User> user = userService.getByDepartmentId(alarm.getDeptId());
        model.addAttribute("userListReviewer", user);
        Depart deptList = deptService.getPublisher(areaId);
        List<User> deptUser = userService.getByDepartmentId(deptList.getId());
        model.addAttribute("userListIssuer", deptUser);
        // 审稿人和审核人
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByNumber2(alarm.getPubNo(),
                alarm.getVersion());
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        List<ReleaseChannel> releaseChannel = releaseChannelService.getAll();
        model.addAttribute("releaseChannel", com.alibaba.fastjson.JSON.toJSONString(releaseChannel));
        return getNameSpace() + "detailsRemoveAlarm";
    }

    /**
     * 变更预警的详细信息
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "detailsUpdateAlarm")
    public String detailsUpdateAlarm(HttpServletRequest request, HttpServletResponse response, String code, Model model)
            throws Exception {
        Long depid = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart d = deptService.getByDepartmentId(depid);
        String areaId = d.getAreaId();
        Alarm alarm = entityService.get(Long.valueOf(code));
        Depart dept = deptService.get(alarm.getDeptId());
        alarm.setDeptName(dept.getName());
        alarm.setDeptId(userService.get(this.getCurrentUser().id).getDepartmentId());
        model.addAttribute("alarm", alarm);
        String alarmType = alarm.getAlarmTypeName();
        String level = alarmType.substring(alarmType.length() - 2, alarmType.length());
        String type = alarmType.substring(0, alarmType.indexOf(level));
        model.addAttribute("departmentName", dept.getName());
        model.addAttribute("alarmType", com.alibaba.fastjson.JSON.toJSONString(alarmTypeService.getAll()));
        model.addAttribute("level", level);
        model.addAttribute("type", type);
        model.addAttribute("creator", userService.get(alarm.getCreator()).getName());
        // 审稿人和审核人
        List<User> user = userService.getByDepartmentId(alarm.getDeptId());
        model.addAttribute("userListReviewer", user);
        Depart deptList = deptService.getPublisher(areaId);
        List<User> deptUser = userService.getByDepartmentId(deptList.getId());
        model.addAttribute("userListIssuer", deptUser);
        // 审稿人和审核人
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByNumber2(alarm.getPubNo(),
                alarm.getVersion());
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        List<ReleaseChannel> releaseChannel = releaseChannelService.getAll();
        model.addAttribute("releaseChannel", com.alibaba.fastjson.JSON.toJSONString(releaseChannel));
        return getNameSpace() + "detailsUpdateAlarm";
    }


    /**
     * 详细页面解除预警
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "detailsRemove")
    @ResponseBody
    public String detailsRemoveAlarm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
        String result = entityService.checkAlarm(alarm);
        if (!result.equals("pass")) {
            return result;
        }
        Record record = new Record();
        String userName = userService.get(this.getCurrentUser().id).getName();
        Long userIds = userService.get(this.getCurrentUser().id).getId();
        String pubId = request.getParameter("pubId");
        if (StringUtils.isBlank(pubId)) {
        } else {
            entityService.removeOldAlarm(request, pubId, false);
        }
        Alarm alarm2 = entityService.get(Long.valueOf(pubId));
        String alarmType = alarm2.getAlarmTypeName();
        Alarm nextAlarm = entityService.passVersionAndCode(1, alarm2.getPubNo());
        record.setUserName(userName);
        record.setUserId(userIds);
        record.setContent(userName + "用户解除了(" + alarmType + ")的预警");
        record.setOperateDate(new Date());
        record.setIpAddress(IpAddressUtil.getIpAddr(request));//添加本机的ip地址
        recordService.saveRcord(record);

        if (nextAlarm.getType().equals("alarm")) {
            NationalEmergencySupport support = new NationalEmergencySupport();
            String msgId = support.makeMsgId(this.getCurrentUser().id, new Date());
            nextAlarm.setRefMsgId(alarm2.getMsgId());
            nextAlarm.setMsgId(msgId);
            nextAlarm.setAuditDate(new Date());
            nextAlarm.setPubState("8");
            channelReactor.doRelease(nextAlarm.getChannelInstances(),
                    nextAlarm);
            nextAlarm.setReleaseDate(new Date());
            nextAlarm.setPubState("12");
//            entityService.saveOrUpdate(nextAlarm);
            int alarmId = entityService.saveOrUpdate(nextAlarm).intValue();
            String[] areas = nextAlarm.getPubRangeName().split(",");
            for (String area : areas) {
                //保存转发数据
                Relay relay = new Relay();
                relay.setAlarmId(alarmId);
                relay.setIsFeedback(0);
                relay.setIsRelay(0);
                relay.setTownId(areaService.getAreaByTown(area).getId().intValue());
                relayService.saveOrUpdate(relay);
            }
        }
        return "pass";
    }

    /**
     * 详变更预警
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "detailsUpdate")
    @ResponseBody
    public String detailsUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
        String result = entityService.checkAlarm(alarm);
        if (!result.equals("pass")) {
            return result;
        }
        Record record = new Record();
        String userName = userService.get(this.getCurrentUser().id).getName();
        Long userIds = userService.get(this.getCurrentUser().id).getId();
        String pubId = request.getParameter("pubId");
        if (StringUtils.isBlank(pubId)) {
        } else {
            entityService.removeOldAlarm(request, pubId, true);
        }
        Alarm alarm2 = entityService.get(Long.valueOf(pubId));
        String alarmType = alarm2.getAlarmTypeName();
        Alarm nextAlarm = entityService.passVersionAndCode(2, alarm2.getPubNo());
        record.setUserName(userName);
        record.setUserId(userIds);
        record.setContent(userName + "用户变更了(" + alarmType + ")的预警");
        record.setOperateDate(new Date());
        record.setIpAddress(IpAddressUtil.getIpAddr(request));//添加本机的ip地址
        recordService.saveRcord(record);

        if (nextAlarm.getType().equals("alarm")) {
            NationalEmergencySupport support = new NationalEmergencySupport();
            String msgId = support.makeMsgId(this.getCurrentUser().id, new Date());
            nextAlarm.setRefMsgId(alarm2.getMsgId());
            nextAlarm.setMsgId(msgId);
            nextAlarm.setAuditDate(new Date());
            //nextAlarm.setPubState("8");
            channelReactor.doRelease(nextAlarm.getChannelInstances(),
                    nextAlarm);
            nextAlarm.setReleaseDate(new Date());
            nextAlarm.setPubState("5");
            int alarmId = entityService.saveOrUpdate(nextAlarm).intValue();
            String[] areas = nextAlarm.getPubRangeName().split(",");
            for (String area : areas) {
                //保存转发数据
                Relay relay = new Relay();
                relay.setAlarmId(alarmId);
                relay.setIsFeedback(0);
                relay.setIsRelay(0);
                relay.setTownId(areaService.getAreaByTown(area).getId().intValue());
                relayService.saveOrUpdate(relay);
            }
        }
        return "pass";
    }


    /**
     * 解除预警时右侧渠道加载
     *
     * @param request
     * @param response
     * @param pubNo
     * @throws IOException
     */
    @RequestMapping(value = "getText")
    public void getData(HttpServletRequest request, HttpServletResponse response, String alarmId)
            throws IOException {
        JSONObject obj = new JSONObject();
        if (alarmId != null) {
            List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.findChannelByalarmId(Long.parseLong(alarmId));
            List<Map<String, String>> content = new ArrayList<Map<String, String>>();
            for (int i = 0; i < releaList.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                ReleaseChannel channel=releaseChannelService.get(releaList.get(i).getChannelId());
                map.put("content", releaList.get(i).getContent());
                map.put("channel", channel.getName());
                map.put("releaseState", releaList.get(i).getReleaseState());
                map.put("id", releaList.get(i).getId().toString());
                if(channel.getName().contains("钉钉")) {
                	map.put("readState", DingDingUtil.getReadState(releaList.get(i).getData(), releaList.get(i).getResultId()));
                }
                else
                {
                	map.put("readState","");
                }
                content.add(map);
            }

            obj.put("data", JSONArray.parseArray(JSONArray.toJSONString(content)));
        }

        printJson(response, obj.toString());
    }

    @RequestMapping(value = "download")
    public void download(HttpServletRequest request, HttpServletResponse response, String data) {
        String newPath = servletContext.getRealPath("/fileDownload") + "/" + data + ".doc";
        InputStream in = null;
        OutputStream out = null;
        try {
            File info = new File(newPath);
            String name = info.getName();
            String fileName = info.getAbsolutePath();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-disposition",
                    "attachment;filename=" + new String(name.getBytes("GBK"), "ISO8859-1"));
            // 4.获取要下载的文件输入流
            in = new FileInputStream(fileName);
            int len = 0;
            // 5.创建数据缓冲区
            byte[] buffer = new byte[(int) info.length()];
            // 6.通过response对象获取OutputStream流
            out = response.getOutputStream();
            // 7.将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                // 8.使用OutputStream将缓冲区的数据输出到客户端浏览器
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取发布者重改的页面
     *
     * @param request
     * @param response
     * @param data
     */
    @RequestMapping(value = "releaseAuditAlarm")
    public String releaseAuditAlarm(HttpServletRequest request, HttpServletResponse response, String alarmId,
                                    Model model) {
        Alarm alarm = entityService.get(Long.valueOf(alarmId));
        List<Relay> list = relayService.selectByAlarmId(alarm.getId().intValue());
        String msg = "";
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Depart depart1 = deptService.findByTownId(String.valueOf(list.get(i).getTownId()));
                if (depart1 != null&&StringUtil.isNotEmpty(list.get(i).getMessage())) {
                    if (i != list.size() - 1) {
                        msg += depart1.getName() + ":" + list.get(i).getMessage() + "\r\n";
                    } else {
                        msg += depart1.getName() + ":" + list.get(i).getMessage() + "";
                    }
                }
            }
            alarm.setMessage(msg);
        } else {
            alarm.setMessage("");
        }
        String tN = alarm.getAlarmTypeName();
        String level = tN.substring(tN.length() - 2, tN.length());
        String type = tN.substring(0, tN.indexOf(level));
        model.addAttribute("level", level);
        model.addAttribute("type", type);
        model.addAttribute("alarm", alarm);
        Long userId = alarm.getCreator();
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByNumber2(alarm.getPubNo(),
                alarm.getVersion());
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        return getNameSpace() + "releaseAuditAlarm";
    }

    /**
     * 文件上传
     *
     * @param request
     * @param response
     * @param data
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = "uploadPage")
    public void uploadPage(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,
                           HttpServletResponse response) throws IllegalStateException, IOException {
        String name = entityService.uploadPage(file, request);
        String alarmId = request.getParameter("alarmId");
        if (!StringUtils.isEmpty(alarmId)) {
            Alarm alarm = new Alarm();
            alarm.setId(Long.valueOf(alarmId));
            alarm.setImagePath(name);
            entityService.saveOrUpdate(alarm);
        }
        JSONObject obj = new JSONObject();
        obj.put("data", name);
        printJson(response, obj.toString());
    }


    /**
     * 文件上传本地路径
     *
     */
    @RequestMapping(value = "uploadPageLocal")
    public void uploadPageLocal(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request,
                           HttpServletResponse response) throws IllegalStateException, IOException {
        String name = entityService.uploadPageLocal(file, request);
        JSONObject obj = new JSONObject();
        obj.put("data", name);
        printJson(response, obj.toString());
    }

    /**
     * 退回信息重新编辑
     *
     * @param request
     * @param response
     * @param data
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping(value = "remakeAlarm")
    public String remakeAlarm(HttpServletRequest request, HttpServletResponse response, String id, Model model)
            throws IllegalStateException, IOException {
        Alarm alarm = entityService.get(Long.valueOf(id));
        if (alarm.getType().equals("alarm")) {
            model.addAttribute("alarmType", com.alibaba.fastjson.JSON.toJSONString(alarmTypeService.getAll()));
            model.addAttribute("alarmTypeName", alarmTypeService.getDistinctAlarmType());
        } else if (alarm.getType().equals("event")) {
            Long eventTT = Long.valueOf(alarm.getTypeId());
            model.addAttribute("eventType",
                    eventTypeService.getDeptEventType(alarm.getDeptId(), eventTypeService.get(eventTT).getPId()));
            model.addAttribute("allEventType", com.alibaba.fastjson.JSON.toJSONString(
                    eventTypeService.getDeptEventType(alarm.getDeptId(), eventTypeService.get(eventTT).getPId())));
        }
        Long deptId = alarm.getDeptId();
        String deptName = deptService.get(deptId).getName();
        model.addAttribute("departmentName", deptName);
//		model.addAttribute("areaAll", areaService.getAll());
        model.addAttribute("areaAll", areaService.getCountryArea());
        model.addAttribute("areaAllTown", areaService.getAll());
        model.addAttribute("releaseChannel", releaseChannelService.getAll());
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByNumber2(alarm.getPubNo(),
                alarm.getVersion());
        model.addAttribute("releaList", releaList);
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }

        model.addAttribute("alarm", alarm);
        return getNameSpace() + "remakeAlarm";
    }

    /**
     * 获取最新点击
     *
     * @param request
     * @param response
     * @param data
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
        if (deptType.equals("1")||deptType.equals("2")) {  //上级
            List<Relay> list = relayService.selectByAlarmId(alarm.getId().intValue());
            String msg = "";
            if (list != null && list.size() != 0) {
                for (int i = 0; i < list.size(); i++) {
                    Depart depart1 = deptService.findByTownId(String.valueOf(list.get(i).getTownId()));
                    if (depart1 != null&&StringUtil.isNotEmpty(list.get(i).getMessage())) {
                        if (i != list.size() - 1) {
                            msg += depart1.getName() + ":" + list.get(i).getMessage() + "\r\n";
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
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.findChannelByalarmId(Long.parseLong(alarmId));
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        return getNameSpace() + "releaseNowAlarm";
    }

    /**
     * 预警制作跳转
     *
     * @param request
     * @param model
     * @param name
     * @return
     * @throws Exception
     * @author jinb
     */
    @RequestMapping(value = "createExternalAlarm")
    public String createExternalAlarm(HttpServletRequest request, Model model, String town, String type, String level) throws Exception {
        String name = type;
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
//		loadPermission();

        if (name != null) {
            model.addAttribute("name", "杭州市气象台发布" + name.trim());
            model.addAttribute("typeL", name.trim());
        }
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        String deptName = deptService.get(deptId).getName();
        model.addAttribute("departmentName", deptName);
        model.addAttribute("alarmName", "alarm");
        model.addAttribute("alarmType", com.alibaba.fastjson.JSON.toJSONString(alarmTypeService.getAll()));
        model.addAttribute("alarmTypeName", alarmTypeService.getDistinctAlarmType());
        model.addAttribute("areaAll", areaService.getCountryArea());
        model.addAttribute("areaAllTown", areaService.getAll());
        model.addAttribute("releaseChannel", releaseChannelService.getAll());
        //新增
        model.addAttribute("nameLevel", name + level);
        model.addAttribute("pubRangeTown", town);
        return getNameSpace() + "createExternalAlarm";
    }

    @RequestMapping(value = "saveExternalAlarm")
    public String saveExternalAlarm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Alarm alarm = bindEntity(request, entityClass);
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        alarm.setCreator(this.getCurrentUser().id);
        alarm.setDeptId(deptId);
        entityService.saveAlarm(alarm, request);
        Alarm nextAlarm = entityService.passVersionAndCode(alarm.getVersion(), alarm.getPubNo());
        if (nextAlarm.getType().equals("alarm")) {
            nextAlarm.setAuditDate(new Date());
            nextAlarm.setPubState("1");
            channelReactor.doRelease(nextAlarm.getChannelInstances(), nextAlarm);
            nextAlarm.setPubState("5");
            nextAlarm.setReleaseDate(new Date());
            entityService.saveOrUpdate(nextAlarm);
        }

        return "redirect:/sev/check/checkExternal";
    }


    /**
     * 获取最新点击
     *
     * @param request
     * @param response
     * @param data
     */
    @RequestMapping(value = "releaseExternalAlarm")
    public String releaseExternalAlarm(HttpServletRequest request, HttpServletResponse response, String alarmId,
                                       Model model) {
        Alarm alarm = entityService.get(Long.valueOf(alarmId));
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
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        return getNameSpace() + "releaseExternalAlarm";
    }

    @RequestMapping(value = "historyExternalQuery")
    public String historyExternalQuery(HttpServletRequest request, Model model) {

        model.addAttribute("currentPage", 1);
        model.addAttribute("pageSize", 10);
        // 上报部门
        List<Depart> depts = deptService.queryList();
        model.addAttribute("depts", depts);
        // 发布状态、事件状态
        model.addAttribute("pubStates", dictService.getDictsByKey(DictKeys.PUB_STATE).values());
        return getNameSpace() + "historyExternalQuery";
    }

    @RequestMapping(value = "historyExternalData")
    public String historyExternalData(HttpServletRequest request, Model model) throws Exception {
        // 获取历史预警数据
        PageInfo<?> pageInfo = entityService.queryPaged(getParameterMap(request));
        JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
        model.addAttribute("page", page);
        model.addAttribute("departList", deptService.queryList());
        return getNameSpace() + "historyExternalData";
    }


    /**
     * 解除预警信息json
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "relieveAlarm")
    public void relieveAlarm(HttpServletRequest request, HttpServletResponse response, Model model)
            throws Exception {
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
            }
        }
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        List<Alarm> list = entityService.getCompleteList(deptId);
        for (int i = 0; i < list.size(); i++) {
            Depart dept = deptService.get(list.get(i).getDeptId());
            list.get(i).setDeptName(dept.getAddress());
        }
        JSONObject obj = new JSONObject();
        obj.put("data", JSONArray.parseArray(JSONArray.toJSONString(list)));
        printJson(response, obj.toString());
    }

    @RequestMapping(value = "detailsExternalRemoveAlarm")
    public String detailsExternalAlarm(HttpServletRequest request, HttpServletResponse response, String alarmId, Model model)
            throws Exception {
        Long depid = userService.get(this.getCurrentUser().id).getDepartmentId();
        Depart d = deptService.getByDepartmentId(depid);
        String areaId = d.getAreaId();
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
            }
        }
        Alarm alarm = entityService.get(Long.valueOf(alarmId));
        Depart dept = deptService.get(alarm.getDeptId());
        alarm.setDeptName(dept.getAddress());
        alarm.setDeptId(userService.get(this.getCurrentUser().id).getDepartmentId());
        model.addAttribute("alarm", alarm);
        String alarmType = alarm.getAlarmTypeName();
        String level = alarmType.substring(alarmType.length() - 2, alarmType.length());
        String type = alarmType.substring(0, alarmType.indexOf(level));
        model.addAttribute("level", level);
        model.addAttribute("type", type);
        model.addAttribute("creator", userService.get(alarm.getCreator()).getName());
        // 审稿人和审核人
        List<User> user = userService.getByDepartmentId(alarm.getDeptId());
        model.addAttribute("userListReviewer", user);
        Depart deptList = deptService.getPublisher(areaId);
        List<User> deptUser = userService.getByDepartmentId(deptList.getId());
        model.addAttribute("userListIssuer", deptUser);
        // 审稿人和审核人
        String channel = "";
        List<ReleaseChannelInstance> releaList = releaseChannelInstanceService.queryByNumber2(alarm.getPubNo(),
                alarm.getVersion());
        if (releaList.size() != 0) {
            for (int i = 0; i < releaList.size(); i++) {
                channel += releaseChannelService.get(releaList.get(i).getChannelId()).getName() + "，";
            }
            model.addAttribute("channel", channel.substring(0, channel.length() - 1));
        }
        List<ReleaseChannel> releaseChannel = releaseChannelService.getAll();
        model.addAttribute("releaseChannel", com.alibaba.fastjson.JSON.toJSONString(releaseChannel));
        return getNameSpace() + "detailsExternalRemoveAlarm";
    }

    @RequestMapping(value = "detailsRemoveExternal")
    public String detailsRemoveExternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pubId = request.getParameter("pubId");
        if (StringUtils.isBlank(pubId)) {
        } else {
            entityService.removeOldAlarm(request, pubId, false);
        }
        Alarm alarm2 = entityService.get(Long.valueOf(pubId));
        Alarm nextAlarm = entityService.passVersionAndCode(1, alarm2.getPubNo());

        if (nextAlarm.getType().equals("alarm")) {
            nextAlarm.setAuditDate(new Date());
            nextAlarm.setPubState("8");
            // channelReactor.doRelease(nextAlarm.getChannelInstances(),
            // nextAlarm);
            nextAlarm.setReleaseDate(new Date());
            nextAlarm.setCompleteDate(new Date());
            nextAlarm.setPubState("12");
//            entityService.saveOrUpdate(nextAlarm);
            int alarmId = entityService.saveOrUpdate(nextAlarm).intValue();
            String[] areas = nextAlarm.getPubRangeName().split(",");
            for (String area : areas) {
                //保存转发数据
                Relay relay = new Relay();
                relay.setAlarmId(alarmId);
                relay.setIsFeedback(0);
                relay.setIsRelay(0);
                relay.setTownId(areaService.getAreaByTown(area).getId().intValue());
                relayService.saveOrUpdate(relay);
            }
        }
        return "redirect:/sev/check/checkExternal";
    }

    /**
     * 生成预警信号发布通知单
     *
     * @param request
     * @param model
     * @param name
     * @return
     * @throws Exception
     * @author jinb
     */
    @RequestMapping(value = "generateAlarmNotice")
    public String generateAlarmNotice(HttpServletRequest request, Model model) throws Exception {
        String alarmId = request.getParameter("alarmId");
        Alarm alarm = entityService.get(Long.valueOf(alarmId));
        if (StringUtils.isEmpty(alarm.getNoticeFile())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日HH时mm分");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String noticeFile = "/template/" + sdf3.format(new Date()) + ".doc";
            alarm.setNoticeFile(noticeFile);
            Date releaseDate = alarm.getReleaseDate();
            List<ReplaceData> rls = new ArrayList<ReplaceData>();
            ReplaceData bhReplaceData = new ReplaceData();
            bhReplaceData.setName("编号");
            bhReplaceData.setContent("YJBH-457-" + sdf.format(releaseDate) + "-01");
            ReplaceData qfrReplaceData = new ReplaceData();
            qfrReplaceData.setName("签发人");
            qfrReplaceData.setContent(alarm.getIssuer());
            ReplaceData bmReplaceData = new ReplaceData();
            bmReplaceData.setName("部门名称");
            bmReplaceData.setContent("杭州市气象台");
            ReplaceData fbsjReplaceData = new ReplaceData();
            fbsjReplaceData.setName("发布时间");
            fbsjReplaceData.setContent(sdf1.format(releaseDate));
            ReplaceData zhReplaceData = new ReplaceData();
            zhReplaceData.setName("灾害名称");
            zhReplaceData.setContent(alarm.getAlarmTypeName());
            ReplaceData yjxxReplaceData = new ReplaceData();
            yjxxReplaceData.setName("预警信息");
            yjxxReplaceData.setContent(alarm.getContent());
            ReplaceData fbrqReplaceData = new ReplaceData();
            fbrqReplaceData.setName("发布日期");
            fbrqReplaceData.setContent(sdf2.format(releaseDate));
            rls.add(bhReplaceData);
            rls.add(qfrReplaceData);
            rls.add(bmReplaceData);
            rls.add(fbsjReplaceData);
            rls.add(zhReplaceData);
            rls.add(yjxxReplaceData);
            rls.add(fbrqReplaceData);
            AlarmType alarmType = alarmTypeService.get(Long.valueOf(alarm.getTypeId()));
            String image = SystemProperties.APP_PATH + "/static/images/warnsmall/" + alarmType.getImage();
            String templateFile = SystemProperties.APP_PATH + noticeFile;
            String modelFile = SystemProperties.APP_PATH + "/fileModel/预警信号发布通知单.doc";
            try {
                FileGernerator.generateAlarmNoticeFile(rls, image, templateFile, modelFile);
                entityService.saveOrUpdate(alarm);
            } catch (Exception e) {
                e.printStackTrace();
            }
//	    	GenerateAlarmNoticeThread generateAlarmNoticeThread = new GenerateAlarmNoticeThread(entityService,alarm,rls, image, templateFile, modelFile);
//	    	Thread thread = new Thread(generateAlarmNoticeThread);
//	    	thread.start();
        }
        Long deptId = userService.get(this.getCurrentUser().id).getDepartmentId();
        List<Depart> departmentList = deptService.query(new HashMap<String, String>());
        for (Depart depart : departmentList) {
            if (depart.getId().equals(deptId)) {
                departmentList.remove(depart);
                break;
            }
        }
        model.addAttribute("departmentList", departmentList);
        model.addAttribute("alarm", alarm);
        model.addAttribute("pdfPath", alarm.getNoticeFile().replaceAll("doc", "pdf"));
        return getNameSpace() + "generateAlarmNotice";
    }

    @RequestMapping(value = "sendAlarmNotice")
    public String sendAlarmNotice(HttpServletRequest request, RedirectAttributes model) {
        String[] departmentIds = request.getParameterValues("departmentId");
        String alarmId = request.getParameter("alarmId");
        if (departmentIds != null && !StringUtils.isEmpty(alarmId)) {
            Date sendDate = new Date();
            Alarm alarm = entityService.get(Long.valueOf(alarmId));
            alarm.setIsSend(1);
            alarm.setSendDate(sendDate);
            entityService.saveOrUpdate(alarm);
            for (String departmentId : departmentIds) {
                AlarmNotice alarmNotice = new AlarmNotice();
                alarmNotice.setIsPublish(0);
                alarmNotice.setAlarmId(Long.valueOf(alarmId));
                alarmNotice.setDepartmentId(Long.valueOf(departmentId));
                alarmNotice.setCreateDate(sendDate);
                alarmNotice.setCreator(this.getCurrentUser().id);
                alarmNoticeService.saveOrUpdate(alarmNotice);
            }
        }
        model.addFlashAttribute("alarmId", alarmId);
        return "redirect:/sev/alarm-notice/query";
    }

    //注意：这个方法不要动
    @RequestMapping(value = "getAreaJsonData")
    public String getAreaJsonData(HttpServletRequest request, HttpServletResponse response) {
        String country[] = {"", "杭州市", "桐庐县", "淳安县", "建德市", "萧山区", "余杭区", "富阳区", "临安市"};
        String pointStr = "";
        User user = userService.get(getCurrentUser().id);
        Long departId = user.getDepartmentId();
        Depart depart = deptService.get(departId);

        int areaId = Integer.valueOf(depart.getAreaId());


        try {
            Set<String> townsAquired = new HashSet<String>();

            Double lat = Double.valueOf(0.00);
            Double lot = Double.valueOf(0.00);
            int index = 0;
            String point = request.getParameter("points");
            List<Point2D> points = new ArrayList<Point2D>();
            for (String str1 : point.split(";")) {
                index++;
                String[] xyStr = str1.split(",");
                Point2D p = GISHelper
                        .mercator2Location(new Point2D.Double(Double.valueOf(xyStr[0]), Double.valueOf(xyStr[1])));
                lot += p.getX();
                lat += p.getY();
                pointStr += String.valueOf(p.getX()) + "," + String.valueOf(p.getY()) + ";";

                points.add(GISHelper
                        .mercator2Location(new Point2D.Double(Double.valueOf(xyStr[0]), Double.valueOf(xyStr[1]))));
                MapHelper.drawCustomPloygon(points);
                List<LinkedHashSet<String>> townNames = MapHelper.getArea(points);
                if (areaId == 1) {
//                	for (String town : townNames.get(2)) {
//
//                        if (town.substring(0, 3).equals(AreaConfig.AREA_COUNTY_NAME)) {
//                            town = town.substring(4, town.length());
//                            if(town.equals("富阳区")||town.equals("余杭区")||town.equals("临安市")||town.equals("建德市")||town.equals("淳安县")||town.equals("桐庐县")||town.equals("萧山区")){
//                            	
//                            }else{
//                            	townsAquired.add(town);
//                            } 
//                        }
//                    }
//                    int key=1;
//                    for (String town : townNames.get(3)) {
//                    	//System.out.println(town);
//                        if (town.substring(0, 3).equals("萧山区")) {
//                            town = town.substring(4, town.length());
//                            if((town.equals("临江街道")||town.equals("义蓬街道")||town.equals("前进街道")||town.equals("河庄街道")||town.equals("新湾街道"))&&key==1){
//                            	townsAquired.add("大江东区");
//                            	key=2;
//                            } 
//                        }
//                    }
                    for (String town : townNames.get(3)) {

                        if (town.substring(0, 3).equals("滨江区") || town.substring(0, 3).equals("拱墅区") || town.substring(0, 3).equals("江干区") ||
                                town.substring(0, 3).equals("上城区") || town.substring(0, 3).equals("西湖区") || town.substring(0, 3).equals("下城区") ||
                                town.substring(0, 3).equals("大江东区")) {
                            town = town.substring(4, town.length());
                            townsAquired.add(town);
                        }
                    }
                } else {
                    for (String town : townNames.get(3)) {
                        if (town.substring(0, 3).equals(country[areaId])) {
                            town = town.substring(4, town.length());
                            if (town.equals("临江街道") || town.equals("义蓬街道") || town.equals("前进街道") || town.equals("河庄街道") || town.equals("新湾街道") || town.equals("萧山区围垦区") || town.equals("顺坝垦区")) {
                                continue;
                            }
                            //System.out.println("\""+town+"\":\""+converterToFirstSpell(town)+"\",");
                            townsAquired.add(town);
                        }
                    }
                }


            }
            if (index > 1) {
                lat = lat / index;
                lot = lot / index;
            }
            if (townsAquired.size() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("lat", lat);
                map.put("lot", lot);
                map.put("town", townsAquired);
                map.put("points", pointStr);

                printJson(response, JSON.toJSONString(map));
            }
        } catch (Exception e) {
            log.error("", e);
        }

        return null;
    }

    //	public static String converterToFirstSpell(String chines) {
//		          
//		          String pinyinName = "";
//		          char[] nameChar = chines.toCharArray();
//		    
//		          
//		          HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat(); 
//		            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
//		            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//
//		         for (int i = 0; i < nameChar.length; i++) {
//		             if (nameChar[i] > 128) {
//		                 try {
//		                      pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
//		                      
//		                  } catch (BadHanyuPinyinOutputFormatCombination e) {
//		                     e.printStackTrace();
//		                 }
//		            } else {
//		                 pinyinName += nameChar[i];
//		             }
//		         }
//		         return pinyinName;
//		     }
    @RequestMapping("indexData")
    @ResponseBody
    public List<Alarm> indexData() {
        List<Alarm> result = alarmService.getIndex();
        for (Alarm alarm : result) {
            Long id = alarm.getDeptId();
            Depart dept = deptService.findById(id);
            String name = dept.getName();
            alarm.setDeptName(name);
            Date dateTime = alarm.getPubDate();
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH时");
            String realTime = sdf.format(dateTime);
            alarm.setRealTime(realTime);
            SimpleDateFormat sdff = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            String reallyTime = sdff.format(dateTime);
            alarm.setReallyTime(reallyTime);
        }

        return result;
    }

    @RequestMapping("informationData")
    @ResponseBody
    public List<Alarm> getInformationData(String id) {
        List<Alarm> result = alarmService.getInformation(id);
        for (Alarm alarm : result) {
            Date dateTime = alarm.getPubDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String realTime = sdf.format(dateTime);
            alarm.setRealTime(realTime);
        }
        return result;
    }

    @RequestMapping("allAlarm")
    @ResponseBody
    public List<Alarm> getAll() {
        return alarmService.getAll();
    }

//	@RequestMapping(value = "/saveAlarmData",method = RequestMethod.POST)
//	@ResponseBody
//	  public void saveAlarmData(Long deptId,String pubNo,String title,String pubRange,
//			  String pubRangeName,String content,String duration,Date pubDate,Date createDate,
//			  Long creator,String alarmTypeName,int version,String type,String typeId,String issuer,
//			  String publisher,String msgId,String publishtypename,String kettle,String areaId,HttpServletRequest request) throws Exception {
//		     Alarm alarm = new Alarm();
//		     alarm.setDeptId(deptId);
//		     alarm.setPubNo(pubNo);
//		     alarm.setTitle(title);
//		     alarm.setPubRange(pubRange);
//		     alarm.setPubRangeName(pubRangeName);
//		     alarm.setContent(content);
//		     alarm.setDuration(duration);
//		     alarm.setPubDate(pubDate);
//		     alarm.setCreateDate(createDate);
//		     alarm.setCreator(creator);
//		     alarm.setAlarmTypeName(alarmTypeName);
//		     alarm.setVersion(version);
//		     alarm.setType(type);
//		     alarm.setTypeId(typeId);
//		     alarm.setIssuer(issuer);
//		     alarm.setPublisher(publisher);
//		     alarm.setMsgId(msgId);
//		     alarm.setPublishTypeName(publishtypename);
//		     alarm.setKettle(kettle);
//		     alarm.setAreaId(areaId);
//		     entityService.saveAlarm(alarm, request);
//	}

    @RequestMapping(value = "/saveAlarmData", method = RequestMethod.POST)
    @ResponseBody
    public void saveAlarmData(@RequestBody Alarm a, HttpServletRequest request) throws Exception {

        entityService.saveAlarmData(a);
        Alarm nextAlarm = entityService.passVersionAndCode(a.getVersion(), a.getPubNo());
        if (nextAlarm.getType().equals("alarm")) {
            nextAlarm.setAuditDate(new Date());
            nextAlarm.setPubState("1");
            channelReactor.doRelease(nextAlarm.getChannelInstances(), nextAlarm);
            nextAlarm.setPubState("5");
            nextAlarm.setReleaseDate(new Date());
            entityService.saveOrUpdate(nextAlarm);
        }
    }

    @RequestMapping(value = "/form")
    public String form(HttpServletRequest req, HttpServletResponse response, Model model) {
//		String status=req.getParameter("status");
//		String signal =req.getParameter("signal");
//		String warning=req.getParameter("warning");
//		String startStopTime=req.getParameter("startStopTime");
//		String distribution=req.getParameter("distribution");
//		String depart=req.getParameter("depart");
//		String phone=req.getParameter("phone");
//		model.addAttribute("status",status);
//		model.addAttribute("signal",signal);
//		model.addAttribute("warning",warning);
//		model.addAttribute("startStopTime",startStopTime);
//		model.addAttribute("distribution",distribution);
//		model.addAttribute("depart",depart);
//		model.addAttribute("phone",phone);
        return "/sev/alarm/form";
    }

    /**
     * 获取一张图里预警信号的预警
     *
     * @return
     */
    @RequestMapping(value = "/getPublishAlarm")
    @ResponseBody
    public List<Alarm> getPublishAlarm() {
        List<Alarm> alarm = entityService.getPublishAlarm();
        for (Alarm a : alarm) {
            String area = a.getPubRange();
            String[] areas = area.split(",");
            List<GeoCoordinate> geoCoordinateList = new ArrayList<GeoCoordinate>();
            for (String ar : areas) {
                String path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\areaXml\\" + ar + ".xml").substring(6).replace("%20", " ");
//				String path = this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("WEB-INF/classes/", "static2/data/"+ar+".xml");
                String data = alarmService.readFileToString(path);
                Document doc = (Document) Jsoup.parse(data);
                String cenPoint = doc.getElementsByTag("Area").get(0).attr("Center");//获取中心点
                String[] points = cenPoint.split(",");
                GeoCoordinate g = new GeoCoordinate();
                g.setLongitude(Double.parseDouble(points[0]));
                g.setLatitude(Double.parseDouble(points[1]));
                geoCoordinateList.add(g);
            }
            GeoCoordinate re = GetCenterPointFromListOfCoordinates.getCenterPoint(geoCoordinateList);
            String centrePoint = re.getLongitude() + "," + re.getLatitude();
            String[] lonlat = centrePoint.split(",");
            a.setLon(lonlat[0]);
            a.setLat(lonlat[1]);
            a.setCentrePoint(centrePoint);
        }
        return alarm;
    }

    /**
     * 一张图里获取单个预警的详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getThisAlarm")
    @ResponseBody
    public Alarm getOneAlarm(Long id) {
        Alarm alarm = entityService.get(id);
        Date pubDate = alarm.getPubDate();
        String pubTime = DateUtil.format(pubDate, "yyyy-MM-dd HH:mm");
        alarm.setPubTime(pubTime);//发布时间
        Long deptId = alarm.getDeptId();
        String deptName = deptService.get(deptId).getName();
        alarm.setDeptName(deptName);//部门
        String content = alarm.getContent();
        String[] contents = content.split("：");
        alarm.setAlarmContent(contents.length > 1 ? contents[1] : "");//发布内容
        return alarm;
    }


}
