package cn.movinginfo.tztf.sev.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.common.enums.OperationAction;
import cn.movinginfo.tztf.common.utils.*;
import cn.movinginfo.tztf.dd.domain.WarnDetail;
import cn.movinginfo.tztf.sem.mapper.CommunicationMapper;
import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.Relay;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.domain.Sensitive;
import cn.movinginfo.tztf.sev.mapper.AlarmMapper;
import cn.movinginfo.tztf.sev.mapper.ReleaseChannelInstanceMapper;
import cn.movinginfo.tztf.sm.service.EventTypeService;
import cn.movinginfo.tztf.sys.domain.*;
import cn.movinginfo.tztf.sys.mapper.ChannelCountMapper;
import cn.movinginfo.tztf.sys.mapper.PeopleGroupMapper;
import cn.movinginfo.tztf.sys.service.*;
import cn.movinginfo.tztf.webserviceClient.alarmFile.CreateAlarmFileService;
import cn.movinginfo.tztf.webserviceClient.alarmFile.CreateAlarmFileServiceService;
import net.ryian.core.utils.SpringContextUtil;
import net.ryian.orm.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class AlarmService extends BaseService<Alarm, AlarmMapper> {
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy");
	private static final SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat df3 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分:ss");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static final Logger log = Logger.getLogger(AlarmService.class);
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private SensitiveService sensitiveService;

	@Autowired
	private AreaService areaService;

	@Resource
	private RelayService relayService;

	
	private static final Map<String, String> mapWeather = new HashMap<String, String>() {
		{
			put("台风", "typhoon_icon");
			put("暴雨", "rainstorm_icon");
			put("暴雪", "blizzard_icon");
			put("大风", "gale_icon");
			put("寒潮", "coldCurrent_icon");
			put("沙尘暴", "sandStorm_icon");
			put("高温", "highTemperature_icon");
			put("冰雹", "hail_icon");
			put("霜冻", "frost_icon");
			put("大雾", "fog_icon");
			put("霾", "haze_icon");
			put("干旱", "drought_icon");
			put("道路结冰", "freeze_icon");
			put("雷电", "thunder_icon");
		}
	};

	@Autowired
	private DictService dictService;

	@Autowired(required = false)
	private ServletContext servletContext;

	@Autowired
	private DeptService departmentService;

	@Autowired
	private ReleaseChannelService releaseChannelService;

	@Autowired
	private ReleaseChannelInstanceMapper releaseChannelInstanceMapper;

	@Autowired
	private EventTypeService eventTypeService;

	@Autowired
	private UserService userService;

	@Autowired
	private AlarmTypeService alarmTypeService;

	@Autowired
	private ReleaseChannelInstanceService releaseChannelInstanceService;

	@Autowired
	private ChannelCountMapper channelCountMapper;

	@Autowired
	private CommunicationMapper communicationMapper;

	@Autowired
	private DecisionService decisionService;

	@Autowired
	private PeopleGroupMapper peopleGroupMapper;

//	// 短信内容，数据库链接
	protected JdbcTemplate smsAllMessageJdbcTemplate = SpringContextUtil.getBean("smsAllMessageJdbcTemplate");

	@Override
	public Alarm get(Long id) {
		Alarm entity = super.get(id);
		if (entity != null) {
			Set<ReleaseChannelInstance> channels = releaseChannelInstanceService.getChannels(entity);
			entity.setChannelInstances(channels);
		}
		return entity;
	}

	/**
	 * 根据条件查询分页
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<Alarm> query(Map<String, String> paramMap) {
		Example example = new Example(Alarm.class);
		Example.Criteria criteria = example.createCriteria();
		// criteria.andEqualTo("valid", "1");
		String areaId = paramMap.get("areaId");
		String type = paramMap.get("type");
		if (!StringUtils.isEmpty(areaId)) {
			criteria.andEqualTo("areaId", areaId);
		}
		if (!StringUtils.isEmpty(type)) {
			criteria.andLike("type", "%" + type + "%");
		}
		// 上报部门
		String deptId = paramMap.get("deptId");
		if (!StringUtils.isEmpty(deptId)) {
			criteria.andEqualTo("deptId", deptId);
		}
		// 发布状态
		String pubState = paramMap.get("pubState");
		if (!StringUtils.isEmpty(pubState)) {
			criteria.andEqualTo("pubState", pubState);
		}
		// 开始时间
		String startDate = paramMap.get("startDate");
		if (!StringUtils.isEmpty(startDate)) {
			criteria.andGreaterThanOrEqualTo("pubDate", startDate);
		}
		// 结束时间
		String endDate = paramMap.get("endDate");
		if (!StringUtils.isEmpty(endDate)) {
			criteria.andLessThanOrEqualTo("pubDate", endDate);
		}
		example.setOrderByClause("pubDate desc");
		return mapper.selectByExample(example);
	}

	public List<Alarm> getData(int year, int month) {
		return mapper.getData(year, month);
	}

	public List<Alarm> getOtherData(int year, int month, String areaId) {
		return mapper.getOtherData(year, month, areaId);
	}

	public List<Alarm> getDataAll(int year, int month, int type, String areaId) {
		return mapper.getDataAll(year, month, type, areaId);
	}

	public List<Alarm> getYearList(int year) {
		return mapper.getYearList(year);
	}

	public List<Alarm> getYearListByType(int year, int type) {
		return mapper.getYearListByType(year, type);
	}

	public List<Alarm> getDetail(String time, String level) {
		return mapper.getDetail(time, level);
	}

	public List<Alarm> getOtherDetail(String time, String level, String areaId) {
		return mapper.getOtherDetail(time, level, areaId);
	}

	public int getNum(int time, String level, String areaId) {
		return mapper.getNum(time, level, areaId);
	}

	public int getYear(int time, String areaId) {
		return mapper.getYear(time, areaId);
	}

	public int getMonth(int time, int month, String level, String areaId) {
		return mapper.getMonth(time, month, level, areaId);
	}

	public int getMonthAll(int time, int month, String areaId) {
		return mapper.getMonthAll(time, month, areaId);
	}

	public int getWeek(String time, String level, String areaId) {
		return mapper.getWeek(time, level, areaId);
	}

	public int getEventWeek(String time, int pid, String areaId) {
		return mapper.getEventWeek(time, pid, areaId);
	}

	public int getEventYear(int year, int pid, String areaId) {
		return mapper.getEventYear(year, pid, areaId);
	}

	public int getEventMonth(int year, int month, int pid, String areaId) {
		return mapper.getEventMonth(year, month, pid, areaId);
	}

	public int getWeekAll(String time, String areaId) {
		return mapper.getWeekAll(time, areaId);
	}

	public Alarm getAlarmByPubNo(String pubNo) {
		return mapper.getAlarmByPubNo(pubNo);
	}

	public Alarm getAlarmById(Long id) {
		return mapper.getAlarmById(id);
	}

	public void saveAlarmData(Alarm alarm) {
		mapper.insert(alarm);
		String channelId = alarm.getChannelId();
		if (channelId != null) {
			String[] chs = channelId.split(",");
			for (int x = 0; x < chs.length; x++) {
				ReleaseChannelInstance rci = new ReleaseChannelInstance();
				if (chs[x].equals("1")) {// 交警LED
					rci.setChannelId(Long.parseLong("31"));
				} else if (chs[x].equals("2")) {// 地铁沿线LED
					rci.setChannelId(Long.parseLong("33"));
				} else if (chs[x].equals("3")) {// 邮政
					rci.setChannelId(Long.parseLong("43"));
				} else if (chs[x].equals("4")) {// 新浪微博
					rci.setChannelId(Long.parseLong("16"));
				} else if (chs[x].equals("5")) {// 公交站牌
					rci.setChannelId(Long.parseLong("32"));
				} else if (chs[x].equals("6")) {// 地铁显示屏
					rci.setChannelId(Long.parseLong("42"));
				} else if (chs[x].equals("7")) {// 新网络媒体
					rci.setChannelId(Long.parseLong("28"));
				} else if (chs[x].equals("8")) {// 市级电视
					rci.setChannelId(Long.parseLong("20"));
				} else if (chs[x].equals("9")) {// 91.8广播
					rci.setChannelId(Long.parseLong("38"));
				} else if (chs[x].equals("10")) {// 区域短信
					rci.setChannelId(Long.parseLong("9"));
				} else if (chs[x].equals("11")) {// 全网短信
					rci.setChannelId(Long.parseLong("10"));
				} else if (chs[x].equals("12")) {// 华数全频道
					rci.setChannelId(Long.parseLong("26"));
				}
				rci.setAlarmId(alarm.getId());
				if (alarm.getStatus().equals("fb")) {
					rci.setVersion(0);
				} else if (alarm.getStatus().equals("gx")) {
					List<ReleaseChannelInstance> oldRci = releaseChannelInstanceService
							.getReleaseChannelInstanceByPubNo(alarm.getPubNo(), alarm.getVersion());
					for (ReleaseChannelInstance oldR : oldRci) {
						oldR.setValid(0);
						releaseChannelInstanceService.saveOrUpdate(oldR);
					}
					rci.setVersion(2);

				} else if (alarm.getStatus().equals("jc")) {
					List<ReleaseChannelInstance> oldRci = releaseChannelInstanceService
							.getReleaseChannelInstanceByPubNo(alarm.getPubNo(), alarm.getVersion());
					for (ReleaseChannelInstance oldR : oldRci) {
						oldR.setValid(0);
						releaseChannelInstanceService.saveOrUpdate(oldR);
					}
					rci.setVersion(1);

				}
				rci.setValid(1);
				rci.setReleaseState("0");
				rci.setSenderType(alarm.getType());
				rci.setContent(alarm.getContent());
				rci.setSenderNumber(alarm.getPubNo());
				releaseChannelInstanceMapper.insert(rci);

			}
		}
	}

	public void saveAlarm(Alarm alarm, HttpServletRequest request) throws Exception, ParseException {
		Record record = new Record();
		User user = userService.get(alarm.getCreator());
		Long userId = user.getId();
		String userName = user.getName();
		String alarmType = alarm.getAlarmTypeName();
		String publishTypeName = request.getParameter("publishTypeName");
		System.out.println("saveAlarm方法中的 --publishTypeName  =   ： " + publishTypeName);
		alarm.setPublishTypeName(publishTypeName);
		String[] areas = request.getParameterValues("pubRangeNameTown");// fxy
		String[] areas1 = request.getParameterValues("pubRangeName");
		String[] releaseChannel = request.getParameterValues("channelCheckBoxes");
//		if("".equals(alarm.getPoints())){
//			if("1".equals(alarm.getAreaId())){
//				String points="";
//				Properties prop = new Properties();
//				try {
//					prop.load( new InputStreamReader(AlarmService.class.getClassLoader().getResourceAsStream("AreaPoint.properties"),"UTF-8"));
//					for(int i=0;i<areas1.length;i++){
//						points+=prop.get(areas1[i]).toString().trim();
//					}
//					
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				alarm.setPoints(points);
//				
//				
//			}
//		}

		// 处理判断其中的事件总类型的那个id start
		if (alarm.getType().equals("event")) {
			Long eventId = Long.valueOf(alarm.getTypeId());
			if (eventTypeService.get(eventId).getPId() != 0) {
				Long finalId = eventTypeService.get(eventTypeService.get(eventId).getPId()).getPId();
				if (finalId == 0) {
					finalId = eventTypeService.get(eventId).getPId();
				}
				alarm.setPid(finalId);
			} else {
				alarm.setPid(eventTypeService.get(eventId).getPId());
			}

		} else {
			alarm.setPid(Long.valueOf("1"));
		}
		// 处理判断其中的事件总类型的那个id end
		alarm.setVersion(0);
		alarm.setPubState("0");
		alarm.setCreateDate(new Date());
		String time = request.getParameter("pubDate") + ":00";
		alarm.setPubDate(df3.parse(time));
		Alarm oldAlarm = mapper.getNewMessage(alarm.getDeptId());
		String year = df.format(new Date());
		Long deptId = alarm.getDeptId();
		Depart depart = departmentService.get(deptId);
		String dept = depart.getCode();
		// 处理编号
		if (oldAlarm == null) {
			String pubNo = dept + year + "0001";
			alarm.setPubNo(pubNo);
		} else {
			String no1 = oldAlarm.getPubNo();
			String bh1 = no1.substring(no1.length() - 8, no1.length());
			String year2 = bh1.substring(0, 4);
			if (year.equals(year2)) {
				Integer num = Integer.valueOf(bh1.substring(4, bh1.length())) + 1;
				Integer LongNum = String.valueOf(num).length();
				String finalNum = String.valueOf(num);
				for (int i = 0; i < 4 - LongNum; i++) {
					finalNum = "0" + finalNum;
				}
				alarm.setPubNo(dept + year + finalNum);
			} else {
				alarm.setPubNo(dept + year + "0001");
			}
		}
		// 处理乡镇
		if (areas != null) {
			String area = "";
			String townId = "";
			for (int i = 0; i < areas.length; i++) {
				area += areas[i] + ",";
				townId += String.valueOf(areaService.getAreaByTown(areas[i]).getId()) + ",";
			}
			area = area.substring(0, area.length() - 1);
			alarm.setPubRangeName(area);
			townId = townId.substring(0, townId.length() - 1);
			alarm.setTownId(townId);
		} else {
			alarm.setPubRangeName(null);
		}
		if (areas1 != null) {
			String area1 = "";
			for (int i = 0; i < areas1.length; i++) {
				area1 += areas1[i] + ",";
			}
			area1 = area1.substring(0, area1.length() - 1);
			alarm.setPubRange(area1);
		} else {
			alarm.setPubRange(null);
		}
		alarm.setCreateDate(new Date());
		int alarmId = saveOrUpdate(alarm).intValue();
		if (areas != null) {
			for (String area : areas) {
				// 保存转发数据
				Relay relay = new Relay();
				relay.setAlarmId(alarmId);
				relay.setIsFeedback(0);
				relay.setIsRelay(0);
				relay.setTownId(areaService.getAreaByTown(area).getId().intValue());
				relayService.saveOrUpdate(relay);
			}
		}
		List<String> li = new ArrayList<String>();
		if (releaseChannel != null) {
			for (int j = 0; j < releaseChannel.length; j++) {
				String value = releaseChannel[j];
				String[] val2 = value.split("_");
				String name = val2[val2.length - 1];
				Long rcId = releaseChannelService.passNameGetMessage(name).getId();
				String chname = releaseChannelService.passNameGetMessage(name).getName();
				li.add(chname);
				ReleaseChannelInstance rci = new ReleaseChannelInstance();
				rci.setVersion(0);
				rci.setReleaseState("0");
				rci.setSenderType(alarm.getType());
				rci.setChannelId(rcId);
				rci.setContent(request.getParameter(value));
				rci.setSenderNumber(alarm.getPubNo());
				rci.setAlarmId(alarm.getId());
				releaseChannelInstanceMapper.insert(rci);
				/*
				 * if("ledScreen".equals(name)){ ReleaseChannelInstance jjrci = new
				 * ReleaseChannelInstance(); jjrci.setVersion(0); jjrci.setReleaseState("0");
				 * jjrci.setSenderType(alarm.getType()); jjrci.setChannelId(31l);
				 * jjrci.setContent(request.getParameter("channel_traffic"));
				 * jjrci.setSenderNumber(alarm.getPubNo());
				 * releaseChannelInstanceMapper.insert(jjrci); ReleaseChannelInstance dtrci =
				 * new ReleaseChannelInstance(); dtrci.setVersion(0);
				 * dtrci.setReleaseState("0"); dtrci.setSenderType(alarm.getType());
				 * dtrci.setChannelId(33l);
				 * dtrci.setContent(request.getParameter("channel_subway"));
				 * dtrci.setSenderNumber(alarm.getPubNo());
				 * releaseChannelInstanceMapper.insert(dtrci); }
				 */
			}
			record.setUserName(userName);
			record.setUserId(userId);
			record.setOperateDate(new Date());
			record.setContent(userName + "用户发布了(" + alarmType + ")的预警(发布渠道：" + li + ")");
			record.setIpAddress(IpAddressUtil.getIpAddr(request));
			if (userName.contains("气象局")) {
//				TokenUtil.GetToken(request, token, contents);//气象局一旦发布，就会发到自己所在的发布中心群里
//				TokenUtil.sendContent(token, contents);
			}
			recordService.saveRcord(record);
		}
		// 事件通知发布中心人员
		String phone = departmentService.get(18l).getPhone();
		Long msgIndex = null;
		msgIndex = sendSmsMessage(phone, "收到" + depart.getName() + "预警发布请求，请及时登入http://21.15.116.61:8080/审核！", 0);
		if (msgIndex != 0) {
//			sendPhoneSucceedMap.put(phoneNo, msgIndex);
		} else {
//			sendPhoneFailedMap.put(phoneNo, msgIndex);
		}
	}

	/**
	 * 发送短信，向短信内容表插入一条记录，并返回生成的主键MsgIndex
	 * <p>
	 * 
	 * @return 短信内容主键MsgIndex
	 * @author: zhangd
	 * @createTime: 2017年8月31日 下午4:24:16
	 * @updateTime:
	 */
	public Long sendSmsMessage(String phoneNo, String msg, int i) {
//		String sql = "INSERT INTO USERWakeMessage (intime, MobileNo, Msg, Pri) VALUES (''{0}'',''{1}'',''{2}'',''{3}'')";
//		String intime = sdf.format(new Date());
		PublishUtils.messageRemind(phoneNo, msg);
		Long autoIncId = 0L;
//		if(phoneNo.indexOf("、")!=-1){
//			String[] arr = phoneNo.split("、");
//			for(int j=0;j<arr.length;j++){
//				Object[] o = {intime, arr[j], msg, (i+1)};
//				final String isql = MessageFormat.format(sql, o);
//				// 执行插入语句后，获取自动生成的主键
//				KeyHolder keyHolder = new GeneratedKeyHolder();
//				smsAllMessageJdbcTemplate.update(new PreparedStatementCreator() {
//					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//						PreparedStatement ps = con.prepareStatement(isql, PreparedStatement.RETURN_GENERATED_KEYS);
//						return ps;
//					}
//				}, keyHolder);
//				autoIncId = keyHolder.getKey().longValue();
//			}
//		}else{
//			Object[] o = {intime, phoneNo, msg, (i+1)};
//			final String isql = MessageFormat.format(sql, o);
//			// 执行插入语句后，获取自动生成的主键
//			KeyHolder keyHolder = new GeneratedKeyHolder();
//			smsAllMessageJdbcTemplate.update(new PreparedStatementCreator() {
//				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//					PreparedStatement ps = con.prepareStatement(isql, PreparedStatement.RETURN_GENERATED_KEYS);
//					return ps;
//				}
//			}, keyHolder);
//			autoIncId = keyHolder.getKey().longValue();
//		}

		return autoIncId;
	}

	public String createForm(Alarm alarm, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		String downFileName = sdf1.format(new Date());// 替换后的文件名
		try {
			String pdate = request.getParameter("pubDate");
			Date date = sdf2.parse(pdate);
			int duration = 0;
			if (request.getParameter("duration").equals("")) {

			} else {
				duration = Integer.parseInt(request.getParameter("duration"));
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, duration);
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
			for (ReleaseChannel r : allReleaseChannel) {

				if (!alarm.getPubChannel().contains(r.getName())) {
					r.setValid(0);
				}

			}
			List<Decision> decisions = decisionService.findDecisionList(alarm.getDeptId());
			String[] smsIds = alarm.getSmsGroup().split(" ");

			for (Decision d : decisions) {
				d.setValid(0);

				for (int i = 0; i < smsIds.length; i++) {
					if (!"".equals(smsIds[i])) {
						if (Long.valueOf(smsIds[i]) == d.getId()) {
							d.setValid(1);
							break;
						}
					}
				}

			}

			List<ReplaceData> rls = new ArrayList<ReplaceData>();
			rls.add(FileGernerator.getReplaceData("信号名称", alarm.getAlarmTypeName()));
			rls.add(FileGernerator.getReplaceData("预警信息", alarm.getContent()));
			rls.add(FileGernerator.getReplaceData("发布人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("预警时间", pdate));
			rls.add(FileGernerator.getReplaceData("编号", alarm.getPubNo() != null ? alarm.getPubNo() : getPubNo(alarm)));

			String fileNamePath = "";// 模板文件名
			String image = "";
			String alarmTypeId = alarm.getTypeId();
			String defense = "";
			AlarmType alarmType = alarmTypeService.get(Long.valueOf(alarmTypeId));
			if (alarmType != null) {
				defense = alarmType.getDefence();
			}

			rls.add(FileGernerator.getReplaceData("审稿人", alarm.getIssuer()));
			if (alarm.getType().equals("alarm")) {
				fileNamePath = "通州区气象灾害预警信号模板.doc";
				image = "D:\\tztfsource\\images\\warning_icon\\" + alarm.getAlarmTypeName() + ".jpg";
				rls.add(FileGernerator.getReplaceData("防御指南", defense));
			} else {
				fileNamePath = "通州区突发事件预警信号模板.doc";
				image = "";
			}

			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			System.out.println(newPath);
			FileGernerator.createFiledir(newPath);
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,
					allReleaseChannel, decisions);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}
		return downFileName;
	}
	
	public String createLocalForm(Alarm alarm){
		String downFileName = DateUtil.format(new Date(), "yyyyMMddHHmmss") +"bd";// 替换后的文件名
		try {
			Date date = alarm.getPubDate();
			String pdate = DateUtil.format(date, "yyyy年MM月dd日HH时mm分");
			int duration = 0;
			if (!StringUtils.isEmpty(alarm.getDuration())) {
				duration = Integer.parseInt(alarm.getDuration());
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, duration);
			String type = alarm.getType();
			List<ReleaseChannel> allReleaseChannel = null;
			if(type.equals("alarm")) {
				allReleaseChannel = releaseChannelService.getByKettle();
			}else if(type.equals("event")) {
				allReleaseChannel = releaseChannelService.getSomeChannel();
			}
			

			Collections.sort(allReleaseChannel, new Comparator<ReleaseChannel>() {

				@Override
				public int compare(ReleaseChannel o1, ReleaseChannel o2) {
					if (o1.getSortNumber() < o2.getSortNumber()) {
						return -1;
					}
					return 0;
				}
			});
			for (ReleaseChannel r : allReleaseChannel) {

				if (!alarm.getPubChannel().contains(r.getNameEn())) {
					r.setValid(0);
				}

			}
			String[] channel = "乡镇显示屏,微信公众号".split(",");
			for(String cl : channel) {
				ReleaseChannel relChannel = new ReleaseChannel(); 
				relChannel.setValid(1);
				relChannel.setName(cl);
				allReleaseChannel.add(relChannel);
			}
			List<Decision> decisions = decisionService.getAll();
//			String[] smsIds = alarm.getSmsGroup().split(" ");

//			for (Decision d : decisions) {
//				d.setValid(0);
//
//				for (int i = 0; i < smsIds.length; i++) {
//					if (!"".equals(smsIds[i])) {
//						if (Long.valueOf(smsIds[i]) == d.getId()) {
//							d.setValid(1);
//							break;
//						}
//					}
//				}
//
//			}

			List<ReplaceData> rls = new ArrayList<ReplaceData>();
			rls.add(FileGernerator.getReplaceData("信号名称", alarm.getAlarmTypeName()));
			rls.add(FileGernerator.getReplaceData("预警信息", alarm.getContent()));
			rls.add(FileGernerator.getReplaceData("发布人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("预警时间", pdate));
			rls.add(FileGernerator.getReplaceData("编号", alarm.getPubNo()));

			String fileNamePath = "";// 模板文件名
			String image = "";
			String alarmTypeId = alarm.getTypeId();
			String defense = "";
			AlarmType alarmType = alarmTypeService.get(Long.valueOf(alarmTypeId));
			if (alarmType != null) {
				defense = alarmType.getDefence();
			}

			rls.add(FileGernerator.getReplaceData("审稿人", alarm.getIssuer()));
			if (alarm.getType().equals("alarm")) {
				fileNamePath = "通州区气象灾害预警信号模板.doc";
				image = "D:\\tztfsource\\images\\warning_icon\\" + alarm.getAlarmTypeName() + ".jpg";
				rls.add(FileGernerator.getReplaceData("防御指南", defense));
			} else {
				fileNamePath = "通州区突发事件预警信号模板1.doc";
				image = "";
			}

			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			System.out.println(newPath);
			FileGernerator.createFiledir(newPath);
			downFileName =FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,
					allReleaseChannel, decisions);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}
		return downFileName;
	}

	public String createFaxForm(Alarm alarm, HttpServletRequest request) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		String downFileName = sdf1.format(new Date());// 替换后的文件名
		try {
			String pdate = request.getParameter("pubDate");
			Date date = sdf2.parse(pdate);
			int duration = 0;
			if (request.getParameter("duration").equals("")) {

			} else {
				duration = Integer.parseInt(request.getParameter("duration"));
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, duration);

			List<ReplaceData> rls = new ArrayList<ReplaceData>();

			rls.add(FileGernerator.getReplaceData("预警内容", alarm.getContent()));
			rls.add(FileGernerator.getReplaceData("制作人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("发送时间", pdate));
			rls.add(FileGernerator.getReplaceData("部门", departmentService.get(alarm.getDeptId()).getName()));
			alarm.setPubNo(getPubNo(alarm));
			rls.add(FileGernerator.getReplaceData("流水号",
					alarm.getPubNo().substring(alarm.getPubNo().length() - 8, alarm.getPubNo().length() - 4)
							+ alarm.getPubNo().substring(alarm.getPubNo().length() - 2, alarm.getPubNo().length())));

			String fileNamePath = "";// 模板文件名
			String image = "";
			String alarmTypeId = alarm.getTypeId();
			String defense = alarmTypeService.get(Long.valueOf(alarmTypeId)).getDefence();
			rls.add(FileGernerator.getReplaceData("签发人", alarm.getIssuer()));
			if (alarm.getType().equals("alarm")) {
				fileNamePath = "预警对外模板.doc";
				image = "D:\\tztfsource\\images\\warning_icon\\" + alarm.getAlarmTypeName() + ".jpg";
				rls.add(FileGernerator.getReplaceData("防御指南", defense));
			} else {
				fileNamePath = "预警对外模板.doc";
				image = "";
			}

			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			System.out.println(newPath);
			FileGernerator.createFiledir(newPath);
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,
					null, null);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}
		return downFileName;
	}

	private String getPubNo(Alarm alarm) {
		String pubNo = "";
		Alarm oldAlarm = mapper.getNewMessage(alarm.getDeptId());
		String year = df.format(new Date());
		Long deptId = alarm.getDeptId();
		Depart depart = departmentService.get(deptId);
		String dept = depart.getCode();
		// 处理编号
		if (oldAlarm == null) {
			pubNo = dept + year + "0001";

		} else {
			String no1 = oldAlarm.getPubNo();
			String bh1 = no1.substring(no1.length() - 8, no1.length());
			String year2 = bh1.substring(0, 4);
			if (year.equals(year2)) {
				Integer num = Integer.valueOf(bh1.substring(4, bh1.length())) + 1;
				Integer LongNum = String.valueOf(num).length();
				String finalNum = String.valueOf(num);
				for (int i = 0; i < 4 - LongNum; i++) {
					finalNum = "0" + finalNum;
				}
				pubNo = dept + year + finalNum;
			} else {
				pubNo = dept + year + "0001";
			}
		}
		return pubNo;
	}

	public String updateForm(Alarm alarm, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String downFileName = sdf1.format(new Date());// 替换后的文件名
		try {
			String pdate = request.getParameter("pubDate");
			Date date = sdf.parse(pdate);
			int duration = 0;
			if (request.getParameter("duration").equals("")) {

			} else {
				duration = Integer.parseInt(request.getParameter("duration"));
			}
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR, duration);
			Date enddate = c.getTime();
			String edate = sdf2.format(enddate);
			String pedate = pdate + "  至  " + edate;

			String pubRange = request.getParameter("pubRange");

			List<ReplaceData> rls = new ArrayList<ReplaceData>();
			rls.add(FileGernerator.getReplaceData("信号名称", alarm.getAlarmTypeName()));
			rls.add(FileGernerator.getReplaceData("预警信息", alarm.getContent()));
			rls.add(FileGernerator.getReplaceData("发布人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("起止时间", pedate));
			rls.add(FileGernerator.getReplaceData("状态", "变更"));
			rls.add(FileGernerator.getReplaceData("发布范围", pubRange));
			Depart dept = departmentService.get(alarm.getDeptId());
			rls.add(FileGernerator.getReplaceData("电话号码", dept.getPhone()));
			rls.add(FileGernerator.getReplaceData("传真", dept.getFax()));
			rls.add(FileGernerator.getReplaceData("部门名称", dept.getName()));
			String fileNamePath = "";// 模板文件名
			if (alarm.getType().equals("alarm")) {
				String alarmTypeId = alarm.getTypeId();
				if (alarmTypeId != null) {
					String defense = alarmTypeService.get(Long.valueOf(alarmTypeId)).getDefence();
					rls.add(FileGernerator.getReplaceData("防御指南", defense));
				}
				rls.add(FileGernerator.getReplaceData("审稿人", alarm.getIssuer()));
				fileNamePath = "杭州市突发事件预警信息发布.doc";
			} else {
				rls.add(FileGernerator.getReplaceData("审稿人", ""));
				// fileNamePath= "事件信号U.doc";
				fileNamePath = "杭州市突发事件预警信息发布.doc";
			}
			String image = "";
			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			FileGernerator.createFiledir(newPath);
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,
					null, null);
//			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}
		return downFileName;
	}

//	 public void convert2Html(String fileName, HttpServletResponse response)
//	            throws TransformerException, IOException,
//	            ParserConfigurationException {
//
//	        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));//WordToHtmlUtils.loadDoc(new FileInputStream(inputFile));
//	         //兼容2007 以上版本
////	        XSSFWorkbook  xssfwork=new XSSFWorkbook(new FileInputStream(fileName));
//	        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//	                DocumentBuilderFactory.newInstance().newDocumentBuilder()
//	                        .newDocument());
//	        wordToHtmlConverter.setPicturesManager( new PicturesManager()
//	        {
//	            public String savePicture( byte[] content,
//	                                       PictureType pictureType, String suggestedName,
//	                                       float widthInches, float heightInches )
//	            {
//	                return "test/"+suggestedName;
//	            }
//	        } );
//	        wordToHtmlConverter.processDocument(wordDocument);
//	        
//	        //save pictures
//	        List pics=wordDocument.getPicturesTable().getAllPictures();
//	        if(pics!=null){
//	            for(int i=0;i<pics.size();i++){
//	                Picture pic = (Picture)pics.get(i);
//	                System.out.println();
//	                try {
//	                    pic.writeImageContent(new FileOutputStream("D:/test/"
//	                            + pic.suggestFullFileName()));
//	                } catch (FileNotFoundException e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        }
//	        Document htmlDocument = wordToHtmlConverter.getDocument();
//	        
//	        ByteArrayOutputStream out = new ByteArrayOutputStream();
//	        DOMSource domSource = new DOMSource(htmlDocument);
//	       
//	        StreamResult streamResult = new StreamResult(out);
//
//
//	        TransformerFactory tf = TransformerFactory.newInstance();
//	        Transformer serializer = tf.newTransformer();
//	     
//	        serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//	        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//	        serializer.setOutputProperty(OutputKeys.METHOD, "HTML");
//	        serializer.transform(domSource, streamResult);
//	        out.close();
//	        writeFile(new String(out.toByteArray()), response);
//	    }
//	 
//	 
//	 public  void writeFile(String content, HttpServletResponse response) {
//	        OutputStream os = null; 
//	        BufferedWriter bw = null;
//	        org.jsoup.nodes.Document doc = Jsoup.parse(content);
//	        String styleOld=doc.getElementsByTag("style").html();
//	        //统一字体格式为宋体
//	        styleOld=styleOld.replaceAll("font-family:.+(?=;\\b)", "font-family:SimSun");
//	        
//	        doc.getElementsByTag("head").empty();
//	        doc.getElementsByTag("head").append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></meta>");
//	        doc.getElementsByTag("head").append(" <style type=\"text/css\"></style>");
//	        doc.getElementsByTag("style").append(styleOld);
//	        /*正则表达式查询字体内容：font-family:.+(?=;\b)*/
//	        System.out.println(content);
//	        content=doc.html();
//	        content=content.replace("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">", "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></meta>");
//	        try {
//	            os = response.getOutputStream();
//	            bw = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
//	            bw.write(content);
//	        } catch (FileNotFoundException fnfe) {
//	            fnfe.printStackTrace();
//	        } catch (IOException ioe) {
//	            ioe.printStackTrace();
//	        } finally {
//	            try {
//	                if (bw != null)
//	                    bw.close();
//	                if (os != null)
//	                    os.close();
//	            } catch (IOException ie) {
//	            }
//	        }
//	    }

	// 生成解除表单
	public String createRemoveForm(Alarm alarm, HttpServletRequest request) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String downFileName = sdf1.format(new Date());// 替换后的文件名
		try {
			List<ReplaceData> rls = new ArrayList<ReplaceData>();
			rls.add(FileGernerator.getReplaceData("信号名称", alarm.getAlarmTypeName()));
			rls.add(FileGernerator.getReplaceData("预警编号", alarm.getPubNo()));
			rls.add(FileGernerator.getReplaceData("预警信息", alarm.getContent()));
			rls.add(FileGernerator.getReplaceData("发布人", alarm.getPublisher()));
			rls.add(FileGernerator.getReplaceData("起止时间", request.getParameter("pubDate")));
			rls.add(FileGernerator.getReplaceData("状态", "解除"));
			rls.add(FileGernerator.getReplaceData("发布范围", request.getParameter("pubRangeName")));
			Depart dept = departmentService.get(alarm.getDeptId());
			rls.add(FileGernerator.getReplaceData("电话号码", dept.getPhone()));
			rls.add(FileGernerator.getReplaceData("传真", dept.getFax()));
			rls.add(FileGernerator.getReplaceData("部门名称", dept.getName()));
			if (alarm.getType().equals("alarm")) {
				rls.add(FileGernerator.getReplaceData("审稿人", alarm.getIssuer()));
			} else {
				rls.add(FileGernerator.getReplaceData("审稿人", ""));
			}
			String fileNamePath = "杭州市突发事件预警信息发布.doc";
			String image = "";
			// 生成地区文件夹
			String newPath = servletContext.getRealPath("/fileDownload") + File.separator;
			FileGernerator.createFiledir(newPath);
			// 把生成文档的内容rls放进FileGernerator
			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image,
					null, null);
//			FileGernerator.creatFile(fileNamePath, null, downFileName, rls, 0, newPath, null, servletContext, image);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("生成文档出错！" + e.getMessage());
			throw new RuntimeException("生成文档出错！" + e.getMessage());
		}
		return downFileName;
	}

	public List<Alarm> getCompleteList(Long deptId) {
		List<Alarm> list = mapper.getCompLsit(deptId);
		return list;
	}

	public List<Alarm> getListByDeaprtId(Long id, String areaId) {
		return mapper.getListByDeaprtId(id, areaId);
	}

	/*
	 * 预警上报人显示右边界面
	 */
	public List<Alarm> getListAuditAlarm(Long deptId) {
		return mapper.getListAuditAlarm(deptId);
	}

	public List<Alarm> getListWaitAuditAlarm(Long deptId) {
		return mapper.getListWaitAuditAlarm(deptId);
	}

	public List<Alarm> getListNowAlarm(Long deptId) {
		return mapper.getListNowAlarm(deptId);
	}

	/**
	 * 获取所有正在发布中的预警
	 * 
	 * @return
	 */
	public List<Alarm> getListNowAllAlarm() {
		return mapper.getListNowAllAlarm();
	}

	public List<Alarm> getListNowAlarmPublish(String areaId) {
		return mapper.getListNowAlarmPublish(areaId);
	}

	public Alarm getListByPubNo(String pubNo) {
		Alarm a = new Alarm();
		a.setPubNo(pubNo);
		a.setVersion(1);
		return mapper.selectOne(a);
	}

	/**
	 * 解除旧的渠道
	 * 
	 * @param
	 */
	public void removeOldRelease(String pubNo, Integer version) {
		releaseChannelInstanceMapper.removeOldRelease(pubNo, version);
	}

	public void removeOldAlarm(HttpServletRequest request, String pubId, boolean isUpdate) throws ParseException {
		Alarm alarm = this.get(Long.valueOf(pubId));
		// 处理旧渠道把信息处理掉
		removeOldRelease(alarm.getPubNo(), alarm.getVersion());
		alarm.setPubState("6");
		alarm.setValid(0);
		this.saveOrUpdate(alarm);
		Alarm newAlarm = get(Long.valueOf(pubId));
		newAlarm.setIssuer(request.getParameter("issuer"));
		newAlarm.setTitle(request.getParameter("title"));
		newAlarm.setAlarmTypeName(request.getParameter("alarmTypeName"));
		newAlarm.setValid(1);
		newAlarm.setId(null);
		if (isUpdate) {
			newAlarm.setPubState("0");
			newAlarm.setVersion(2);
		} else {
			newAlarm.setPubState("7");
			newAlarm.setVersion(1);
		}

		newAlarm.setPid(alarm.getId());
		newAlarm.setOperationAction(OperationAction.Cancel.ordinal());
		String pubDate = request.getParameter("pubDate") + ":00";
		String content = request.getParameter("content");
		String duration = request.getParameter("duration");
		newAlarm.setPubDate(df3.parse(pubDate));
		newAlarm.setContent(content);
		newAlarm.setAuditDate(null);
		newAlarm.setReleaseDate(null);
		newAlarm.setCompleteDate(null);
		newAlarm.setDuration(duration);
		this.saveOrUpdate(newAlarm);
		String releaseChannelName = request.getParameter("releaseChannelName");
		String[] releaseChannelMath = releaseChannelName.split("，");
		for (int i = 0; i < releaseChannelMath.length; i++) {
			String ix = releaseChannelMath[i];
			String Ename = "channel_" + releaseChannelService.passCNameToEName(ix);
			Long rcId = releaseChannelService.passNameGetMessage(releaseChannelService.passCNameToEName(ix)).getId();
			ReleaseChannelInstance rci = new ReleaseChannelInstance();
			rci.setChannelId(rcId);
			rci.setVersion(newAlarm.getVersion());
			rci.setSenderType(newAlarm.getType());
			rci.setContent(request.getParameter(Ename));
			rci.setSenderNumber(newAlarm.getPubNo());
			rci.setAlarmId(newAlarm.getId());
			releaseChannelInstanceMapper.insert(rci);
		}
	}

	public String uploadPage(CommonsMultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		ServletContext servletCtx = request.getSession().getServletContext();
		String root = servletCtx.getRealPath("/upload");
		File tmpFile = new File(root);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		String newFileName = UUID.randomUUID().toString();
		String page = file.getOriginalFilename();
		String[] xx = page.split("\\.");
		String ext = xx[xx.length - 1];
		root = root + "/" + newFileName + "." + ext;
		File newFile = new File(root);
		file.transferTo(newFile);
		return newFileName + "." + ext;
	}

	public String uploadPageLocal(CommonsMultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		String root = ConfigHelper.getProperty("tempDirTffk");// 存放路径
		File tmpFile = new File(root);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		String newFileName = UUID.randomUUID().toString();
		String page = file.getOriginalFilename();
		String[] xx = page.split("\\.");
		String ext = xx[xx.length - 1];
		root = root + "/" + newFileName + "." + ext;
		File newFile = new File(root);
		file.transferTo(newFile);
		return newFileName + "." + ext;
	}

	public void updateAlarm(Alarm alarm, HttpServletRequest request) throws ParseException {
		String[] areas = request.getParameterValues("pubRangeNameTown");
		String[] releaseChannel = request.getParameterValues("channelCheckBoxes");
		Integer oldPub = Integer.valueOf(alarm.getPubState()) - 2;
		alarm.setPubState(oldPub.toString());
		alarm.setCreateDate(new Date());
		String time = request.getParameter("pubDate") + ":00";
		alarm.setPubDate(df1.parse(time));
		// 处理乡镇
		if (areas != null) {
			String area = "";
			for (int i = 0; i < areas.length; i++) {
				area += areas[i] + ",";
			}
			area = area.substring(0, area.length() - 1);
			alarm.setPubRangeName(area);
		} else {
			alarm.setPubRangeName(null);
		}
		alarm.setCreateDate(new Date());
		mapper.updateByPrimaryKey(alarm);
		ReleaseChannelInstance deleteRelease = new ReleaseChannelInstance();
		deleteRelease.setValid(1);
		deleteRelease.setVersion(alarm.getVersion());
		deleteRelease.setSenderNumber(alarm.getPubNo());
		releaseChannelInstanceMapper.delete(deleteRelease);
		if (releaseChannel != null) {
			for (int j = 0; j < releaseChannel.length; j++) {
				String value = releaseChannel[j];
				String[] val2 = value.split("_");
				String name = val2[val2.length - 1];
				Long rcId = releaseChannelService.passNameGetMessage(name).getId();
				ReleaseChannelInstance rci = new ReleaseChannelInstance();
				rci.setVersion(alarm.getVersion());
				rci.setChannelId(rcId);
				rci.setSenderType(alarm.getType());
				rci.setContent(request.getParameter(value).trim());
				rci.setSenderNumber(alarm.getPubNo());
				rci.setReleaseState("0");
				rci.setAlarmId(alarm.getId());
				releaseChannelInstanceMapper.insert(rci);
			}
		}
	}

	/**
	 * 最近发布一个月的预警
	 * 
	 * @return
	 */
	public List<Alarm> getListPublishOneMonthAlarm(Long deptId) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		Date mathTime = calendar.getTime();
		String dateTime = df1.format(mathTime);
		return mapper.getListPublishOneMonthAlarm(dateTime, deptId);
	}

	/**
	 * 通过version和编号 查询
	 * 
	 * @return
	 */
	public Alarm passVersionAndCode(Integer version, String code) {
		Alarm entity = mapper.passVersionAndCode(version, code);
		if (entity != null) {
			Set<ReleaseChannelInstance> channels = releaseChannelInstanceService.getChannels(entity);
			entity.setChannelInstances(channels);
		}
		return entity;
	}

	/**
	 * 根据状态获取所有部门最近发布一月的预警信息
	 * 
	 * @param areaId
	 * @return
	 */
	public List<Alarm> getListPublishOneMonthAlarmAll(String pubState, String areaId) {
		Example example = new Example(Alarm.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		if (!"1".equals(areaId)) {
			criteria.andEqualTo("areaId", areaId);
		}
		if (!StringUtils.isEmpty(pubState)) {
			criteria.andEqualTo("pubState", pubState);
		}
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		Date mathTime = calendar.getTime();
		criteria.andGreaterThan("pubDate", mathTime);
		return mapper.selectByExample(example);
	}

	public Alarm getNewOne(Long deptId) {
		return mapper.getNewOne(deptId);
	}

	public List<Alarm> getwaitAudit() {
		return mapper.getwaitAudit();
	}

	public String getPublishType(Long id) {
		return mapper.getPublishType(id);
	}

	public List<Alarm> getListByDeaprtIdHZ(String areaId) {
		return mapper.getListByDeaprtIdHZ(areaId);
	}

	public List<Alarm> getListByDeaprtIdAndAreaId(String areaId) {
		return mapper.getListByDeaprtIdAndAreaId(areaId);
	}

	// 所有正在发布的（预警中心登录）
	public List<Alarm> getListIsPublishing() {
		return mapper.getListIsPublishing();
	}

	// 发布成功已转发
	public List<Alarm> getRelaySucess(String townId) {
		return mapper.getRelaySucess(townId);
	}
	// 根据id查询出来转发成功的所有sev_t_alarm的所有信息

	public Alarm getRelaySucessAll(Long id) {
		return mapper.getRelaySucessAll(id);
	}

	// 所有发布成功并且处于待转发的
	public List<Alarm> getListIsPublishRelay(String townId) {
		return mapper.getListIsPublishRelay(townId);
	}

	public List<Alarm> getIndex() {
		Example example = new Example(Alarm.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("kettle", "0");
		criteria.andEqualTo("valid", "1");
		criteria.andEqualTo("pubState", "5");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		Date mathTime = calendar.getTime();
		criteria.andGreaterThan("pubDate", mathTime);
		example.setOrderByClause("pub_date desc");
		return mapper.selectByExample(example);
	}

	public List<Alarm> getInformation(String id) {
		Example example = new Example(Alarm.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		criteria.andEqualTo("pubState", "5");
		criteria.andEqualTo("id", id);
		return mapper.selectByExample(example);
	}

	public List<Alarm> getPublishAlarm() {
		return mapper.getPublishAlarm();
	}

	public Alarm getNewAlarm() {
		// TODO Auto-generated method stub
		return mapper.getNewAlarm();
	}

	public Object getAlarmYear(int year) {
		// TODO Auto-generated method stub
		return mapper.getAlarmYear(year);
	}

	public JSONArray getAlarmByArea() {
		String[] towns1 = { "", "", "桐庐县", "淳安县", "建德市", "萧山区", "余杭区", "富阳市", "临安市" };
		Map<String, Integer> params = new HashMap<String, Integer>();

		params.put("蓝色", 1);

		params.put("黄色", 2);
		params.put("橙色", 3);
		params.put("红色", 4);
		Map<String, String> encolor = new HashMap<String, String>();
		encolor.put("蓝色", "blue");
		encolor.put("黄色", "yellow");
		encolor.put("橙色", "orange");
		encolor.put("红色", "red");

		JSONArray data = new JSONArray();
		for (int i = 2; i < 9; i++) {
			JSONObject obj = new JSONObject();
			List<Alarm> areas = mapper.getAlarmByArea(String.valueOf(i));
			if (!areas.isEmpty()) {
				int colorNum = 0;
				for (Alarm d : areas) {
					String color = d.getAlarmTypeName().substring(d.getAlarmTypeName().length() - 2,
							d.getAlarmTypeName().length());

					int colorNum1 = params.get(color);
					if (colorNum1 > colorNum) {
						colorNum = colorNum1;
					}
				}
				String color = "";
				for (Map.Entry<String, Integer> entry : params.entrySet()) {
					if (colorNum == entry.getValue()) {
						color = entry.getKey();
					}
				}
				obj.put("area", towns1[Integer.valueOf(i)]);
				obj.put("color", encolor.get(color));
				data.add(obj);

			}
		}

		int[] areaAndColor = { 0, 0, 0, 0, 0, 0, 0, 0 };
		Map<String, Integer> townTOnum = new HashMap<String, Integer>();
		townTOnum.put("滨江区", 0);
		townTOnum.put("拱墅区", 1);
		townTOnum.put("江干区", 2);
		townTOnum.put("上城区", 3);
		townTOnum.put("西湖区", 4);
		townTOnum.put("下城区", 5);
		townTOnum.put("大江东区", 6);

		List<Alarm> alarms = mapper.getListNowAllAlarmFormHz();
		if (!alarms.isEmpty()) {
			for (Alarm a : alarms) {
				if (a.getPubRange() == null || "".equals(a.getPubRange())) {

				} else {
					String[] towns = a.getPubRange().split(",");
					String color = a.getAlarmTypeName().substring(a.getAlarmTypeName().length() - 2,
							a.getAlarmTypeName().length());
					int colorNum = params.get(color);

					for (int i = 0; i < towns.length; i++) {

						if ((!"杭州".equals(towns[i])) && colorNum > areaAndColor[townTOnum.get(towns[i])]) {
							areaAndColor[townTOnum.get(towns[i])] = colorNum;
						}

					}
				}

			}
		}
		for (int i = 0; i < areaAndColor.length; i++) {
			JSONObject obj = new JSONObject();
			if (areaAndColor[i] > 0) {
				String color = "";
				for (Map.Entry<String, Integer> entry : params.entrySet()) {
					if (areaAndColor[i] == entry.getValue()) {
						color = entry.getKey();
					}
				}
				String area = "";
				for (Map.Entry<String, Integer> entry : townTOnum.entrySet()) {
					if (i == entry.getValue()) {
						area = entry.getKey();
					}
				}
				obj.put("area", area);
				obj.put("color", encolor.get(color));
				data.add(obj);
			}

		}

		return data;
	}

	public JSONArray getAlarmListByDeptId(Long deptId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		List<Alarm> alarm = mapper.getAlarmListByDeptId(deptId);

		JSONArray data = new JSONArray();
		if (!alarm.isEmpty()) {
			for (Alarm a : alarm) {
				JSONObject ob = new JSONObject();
				ob.put("alarmId", a.getId());
				ob.put("pubDate", sdf.format(a.getPubDate()));
				if ("".equals(a.getImagePath()) || a.getImagePath() == null) {
					ob.put("isImage", false);
				} else {
					ob.put("isImage", true);
				}
				// AlarmType alarmType = alarmTypeService.get(Long.valueOf(a.getTypeId()));
//				if(Long.valueOf(a.getTypeId())>40){
//		    		String image =  "/static/images/warnsmall/logo.png" ;
//		    		ob.put("image", image);
//		    	}else{
//		    		AlarmType alarmType = alarmTypeService.get(Long.valueOf(a.getTypeId()));
//		        	String image =  "/static/images/warnsmall/" + alarmType.getImage();
//		        	ob.put("image", image);
//		    	}

				ob.put("typeName", a.getAlarmTypeName() + "预警");

				data.add(ob);

			}

		}

		return data;

	}

	public JSONArray getDdAlarmListByDeptId(Long deptId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		List<Alarm> alarm = mapper.getDdAlarmListByDeptId(deptId);
		JSONArray data = new JSONArray();
		if (!alarm.isEmpty()) {
			for (Alarm a : alarm) {
				JSONObject ob = new JSONObject();
				ob.put("alarmId", a.getId());
				ob.put("pubDate", sdf.format(a.getPubDate()));
				if (a.getImagePath().equals("") || a.getImagePath() == null) {
					ob.put("isImage", false);
				} else {
					ob.put("isImage", true);
				}
//				if(Long.valueOf(a.getTypeId())>40){
//		    		String image =  "/static/images/warnsmall/logo.png" ;
//		    		ob.put("image", image);
//		    	}else{
//		    		AlarmType alarmType = alarmTypeService.get(Long.valueOf(a.getTypeId()));
//		        	String image =  "/static/images/warnsmall/" + alarmType.getImage();
//		        	ob.put("image", image);
//		    	}

				ob.put("typeName", a.getAlarmTypeName() + "预警");
				data.add(ob);
			}
		}
		return data;

	}

	public WarnDetail getDetailById(Long id) {
		return communicationMapper.getDetailById(id);
	}

	public List<String> getName(Long id) {
		return communicationMapper.getName(id);
	}

	// 微信机顶盒渠道在站点位置生成文件
	public int createFile(int channelID) {

		JSONArray data = new JSONArray();
		Calendar cal = Calendar.getInstance();
		Date now = new Date();
		cal.setTime(now);
		cal.add(Calendar.DATE, -3);
		now = cal.getTime();
		Example example = new Example(ReleaseChannelInstance.class);
		example.createCriteria().andBetween("releaseState", 0, 3).andEqualTo("valid", 1)
				.andEqualTo("channelId", channelID).andGreaterThanOrEqualTo("createDate", now);
		List<ReleaseChannelInstance> channels = releaseChannelInstanceMapper.selectByExample(example);
//		List<Alarm> alarmlist=new ArrayList();

		for (ReleaseChannelInstance releaseChannelInstance : channels) {
//			Example aexample = new Example(Alarm.class);
//			aexample.createCriteria().andEqualTo("pub_no",releaseChannelInstance.getSenderNumber()).andEqualTo("valid", "1");
			Alarm alarm = new Alarm();
			alarm.setPubNo(releaseChannelInstance.getSenderNumber());
			alarm.setValid(1);
			alarm = mapper.selectOne(alarm);
//			alarmlist.add(alarm);
			JSONObject obj = new JSONObject();
			obj.put("content", releaseChannelInstance.getContent());
			obj.put("duration", alarm.getDuration());
			obj.put("pubTime", DateUtil.format(alarm.getPubDate(), "yyyy-MM-dd HH:mm:ss"));
			obj.put("image", alarm.getAlarmTypeName());
			data.add(obj);

		}

		File file = new File("D:/hydata");
		if (!file.exists()) {// 如果文件夹不存在
			file.mkdirs();// 创建文件夹
		}
		// 如果file文件夹下没有test.txt就会创建该文件
		String url = "";
		if (channelID == 17) {
			url = SystemProperties.getProperty("wxtfyj");
//			url="D:/hydata/tfyj.json";//显示屏渠道
		} else {
			url = SystemProperties.getProperty("tfyj");
//			url="D:/hydata/wxtfyj.json";//微信渠道
		}
		CreateAlarmFileServiceService createAlarmFileService = new CreateAlarmFileServiceService();
		CreateAlarmFileService port = createAlarmFileService.getCreateAlarmFileServicePort();

		int result = port.createAlarmFile(url, data.toJSONString());
		return result;
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws Exception {
		File file = new File("D:/tztfsource/tfyj");
		if (!file.exists()) {// 如果文件夹不存在
			file.mkdir();// 创建文件夹
		}
		try {// 异常处理
				// 如果file文件夹下没有test.txt就会创建该文件
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:/tztfsource/tfyj/tfyj.json"));
			bw.write("Hello!11111");// 在创建好的文件中写入"Hello"
			bw.close();// 一定要关闭文件
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	// 预警检测
	public String checkAlarm(Alarm alarm) {
		String result = isSensit(alarm);
		if (!result.equals("pass")) {
			return result;
		}
		result = isConsist(alarm);
		if (!result.equals("pass")) {
			return result;
		}
		result = isDescError(alarm);
		if (!result.equals("pass")) {
			return result;
		}
		result = isBLLError(alarm);
		if (!result.equals("pass")) {
			return result;
		}

		return "pass";
	}

	// 业务逻辑错误
	private String isBLLError(Alarm alarm) {
		// TODO Auto-generated method stub
		return "pass";
	}

	// 正文内容错误
	private String isDescError(Alarm alarm) {
		if (alarm.getContent().length() < 25) {
			return "正文内容字数小于25";
		}
		return "pass";
	}

	// 内容一致性
	private String isConsist(Alarm alarm) {
		if (!alarm.getContent().contains(alarm.getAlarmTypeName())) {
			return "类型和等级与正文不一致";
		}
		if (!alarm.getTitle().contains(alarm.getAlarmTypeName())) {
			return "类型和等级与标题不一致";
		}
		return "pass";
	}

	// 是否存在敏感词
	private String isSensit(Alarm alarm) {
		List<Sensitive> sensitWords = sensitiveService.getAll();
//			String [] sensitWords=SystemProperties.getProperty("sensitWords").split(" ");
		for (Sensitive sent : sensitWords) {
			if (alarm.getContent().contains(sent.getWord()) || alarm.getTitle().contains(sent.getWord())
					|| alarm.getIssuer().contains(sent.getWord()) || alarm.getPublisher().contains(sent.getWord())) {
				return "存在敏感词" + sent.getWord();
			}
		}

		return "pass";
	}

	public Alarm getFirstAlarmByPubNo(String pubNo) {
		// TODO Auto-generated method stub
		Alarm alarm = new Alarm();
		alarm.setPubNo(pubNo);
		alarm.setVersion(0);
		alarm.setValid(0);
		alarm = mapper.selectOne(alarm);
		return alarm;

	}

	/**
	 * 以utf-8的编码读取文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readFileToString(String filePath) {
		String str = "";
		File file = new File(filePath);
		try {
			FileInputStream in = new FileInputStream(file);
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer, "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return str;
	}

	public List<PeopleGroup> getAllGroup() {
		Example example = new Example(PeopleGroup.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		return peopleGroupMapper.selectByExample(example);
	}

	public Integer updateIsRelay(Long id) {
		// 根据id去修改isrelay的状态
		Integer i = mapper.getUpdateIsPublishRelay(id);
		// 把信息存入到sys_p_group

		return i;
	}

	public Alarm getRelayAlarm(Long id) {
		return mapper.getRelayAlarm(id);
	}

	public List<String> selectAlarmType() {
		return mapper.selectAlarmType();
	}

}
