package cn.movinginfo.tztf.common.task;

import cn.grassinfo.plugins.arithmetic.weather.DataParser.MicapsData4;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.common.utils.FileUtil;
import cn.movinginfo.tztf.sen.domain.Alertrule;
import cn.movinginfo.tztf.sen.domain.AreaAlarm;
import cn.movinginfo.tztf.sen.domain.DangerAlarm;
import cn.movinginfo.tztf.sen.domain.DangerPoint;
import cn.movinginfo.tztf.sen.domain.Elementinfo;
import cn.movinginfo.tztf.sen.domain.Tabstation;
import cn.movinginfo.tztf.sen.service.AlertruleService;
import cn.movinginfo.tztf.sen.service.AreaalarmService;
import cn.movinginfo.tztf.sen.service.DangerAlarmService;
import cn.movinginfo.tztf.sen.service.DangerPointService;
import cn.movinginfo.tztf.sen.service.ElementinfoService;
import cn.movinginfo.tztf.sen.service.PointDangertypeService;
import cn.movinginfo.tztf.sen.service.TabstationService;
import net.ryian.commons.StringUtils;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

@Service("HiddenDangerJob")
public class HiddenDangerJob {

	@Resource
	private DangerPointService dangerPointService;
	@Resource
	private PointDangertypeService pointDangertypeService;
	@Resource
	private AlertruleService alertruleService;
	@Resource
	private ElementinfoService elementinfoService;
	@Autowired
	private DangerAlarmService dangerAlarmService;
	@Autowired
	private AreaalarmService areaalarmService;
	@Autowired
	private TabstationService tabstationService;

	/**
	 * 风险隐患点监控
	 * 
	 * @throws Exception
	 */
	public void job() throws Exception {
		System.out.println("------隐患点报警定时任务启动--------");
		// 获取全部隐患点
		List<DangerPoint> dangerPoints = dangerPointService.selectAll();
		if (dangerPoints != null && dangerPoints.size() != 0) {
			for (DangerPoint dangerPoint : dangerPoints) {
				List<String> list = new ArrayList<String>();
				String alertThreeId = dangerPoint.getAlertruleThreeId();
				if (!StringUtils.isEmpty(alertThreeId)) {// 如果三级报警存在
					list.add(alertThreeId);
				}
				String alertTwoId = dangerPoint.getAlertruleTwoId();
				if (!StringUtils.isEmpty(alertTwoId)) {// 如果二级报警存在
					list.add(alertTwoId);
				}
				String alertId = dangerPoint.getAlertruleId();// 获取报警条件Id
				if(!StringUtils.isEmpty(alertId)) {
					list.add(alertId);
				}

				for (String aleId : list) {// 从等级高开始
					Alertrule alert = alertruleService.get(Long.parseLong(aleId));
					String elementIds = alert.getElementIds();// 获取气象因子
					String logic = alert.getLogic();// 获取逻辑关系
					String relStation = dangerPoint.getRelStation();
					String lon = dangerPoint.getLongitude();
					String lat = dangerPoint.getLatitude();
					String rule = alert.getRuleExpr();
					String[] elements = elementIds.split(",");// 气象因子
					String[] logics = logic.split(",");// >=,<=
					String[] values = new String[elements.length];
					String time = "";
					boolean flag = true;
					for (int x = 0; x < elements.length; x++) {
						Map<String, String> map = getData(elements[x], relStation, logics[x], lon, lat);
						String value = map.get("value");
						if (value == null) {
							flag = false;
						}
						values[x] = value;
						time = map.get("alarmTime");
					}
					ScriptEngineManager manager = new ScriptEngineManager();// 给字符串替换值
					ScriptEngine engine = manager.getEngineByName("js");
					for (int x = 0; x < elements.length; x++) {
						String eleId = elementinfoService.get(Long.parseLong(elements[x])).getElementId();
						engine.put(eleId, values[x]);
					}
					Object result;
					if (flag) {
						result = engine.eval(rule);// 将字符串转成boolean类型数据返回
					} else {
						result = false;
					}
					// System.out.println(result);
					dangerPoint.setOverThreshold(0);
					if ((boolean) result) {
						dangerPoint.setOverThreshold(1);
						DangerAlarm dangerAlarm = dangerAlarmService.findDangerAlarmByTimeAndPointId(
								DateUtil.parse(time, "yyyyMMddHHmm"), dangerPoint.getId());
						if (dangerAlarm == null) {
							DangerAlarm alarm = new DangerAlarm();// 超过阈值记录日志
							alarm.setPointId(dangerPoint.getId());
							alarm.setValue(StringUtils.join(values, ","));// 将数组转成逗号分割的字符串
							alarm.setRuleExpr(alert.getRuleExpr());
							alarm.setLevel(alert.getAlertLevel());
							alarm.setDangerTypeId(dangerPoint.getDangerTypeId());
							alarm.setElementinfoIds(elementIds);
							alarm.setAlarmTime(DateUtil.parse(time, "yyyyMMddHHmm"));
							alarm.setIsForecast("0");
							dangerAlarmService.saveOrUpdate(alarm);
						}
						break;
					}
				}

				dangerPointService.saveOrUpdate(dangerPoint);
			}
		}
		System.out.println("------隐患点报警定时任务结束--------");
		System.out.println("------区域报警系统定时任务开始-------");
		areaAlarm();
		System.out.println("------区域报警系统定时任务结束-------");
		deleteMoreData();// 定时处理数据
	}

	/**
	 * 获取数值和站号
	 * 
	 * @param elementIds
	 * @param relStation
	 * @param logic
	 * @param lon
	 * @param lat
	 * @return
	 */
	public Map<String, String> getData(String elementIds, String relStation, String logic, String lon, String lat) {
		Map<String, String> maps = new HashMap<String, String>();
		String path = "";
		String pathName = "";
		Elementinfo elementinfo = elementinfoService.get(Long.parseLong(elementIds));
		String filePATH = elementinfo.getFilePath();
		String filePath = "";
		if (!StringUtils.isEmpty(filePATH) && filePATH != null) {
			filePath = filePATH.replaceAll("\\\\", "/");
		}
		File file = new File(filePath);
		if (file.exists()) {
			// 列出该目录下所有文件和文件夹
			File[] files = file.listFiles();
			// 按照文件名倒序排序
			assert files != null;
			Arrays.sort(files, (file1, file2) -> file2.getName().compareTo(file1.getName()));
			for (File f : files) {
				if (f.isFile()) {
					path = f.getPath();
					pathName = f.getName();
					break;
				}
			}

			// 获取半小时前的时间
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MINUTE, -30);
			Date date = calendar.getTime();
			String time = pathName.split("\\.")[0];
			Date date1 = DateUtil.parse(time, "yyyyMMddHHmm");
			assert date1 != null;
			// 如果文件不超过半小时就读取
			if (date.getTime() < date1.getTime()) {
				if (!StringUtils.isEmpty(relStation) && relStation != null) {// 有关联站点的数据
					String[] relStations = relStation.split(",");
					String stationIiiii = "";
					for (String r : relStations) {
						String iiiii = tabstationService.get(Long.parseLong(r)).getIiiii();
						stationIiiii += iiiii + ",";
					}
					String value = readFileGetMaxOrMin(path, stationIiiii, logic);
					maps.put("value", value);
					maps.put("alarmTime", time);
				} else {// 没有关联站点的取最近的站点数据，并将站点入库
					// 读取文件
					String content = FileUtil.readFileToString(path);
					assert content != null;
					String[] data = content.split("\\r?\\n");
					List<Map<String, String>> list = new ArrayList<>();
					if (data.length > 4) {
						// 将所有点的距离放到map中
						for (int i = 4; i < data.length; i++) {
							Map<String, String> map = new HashMap<>();
							String[] strings = data[i].split(" ");
							Double longitude = Double.valueOf(strings[1]);
							Double latitude = Double.valueOf(strings[2]);
							double distance = getDistance(Double.valueOf(lat), Double.valueOf(lon), latitude,
									longitude);
							map.put("distance", String.valueOf(distance));
							map.put("value", strings[4]);
							map.put("code", strings[0]);
							list.add(map);
						}
						// 找出距离最近的值
						list.sort((a, b) -> {
							Double dis1 = Double.parseDouble(a.get("distance"));
							Double dis2 = Double.parseDouble(b.get("distance"));
							return dis1 == dis2 ? 0 : (dis1 > dis2) ? 1 : -1;
						});
						String code = list.get(0).get("code");// 存入关联站点站号
						String value = list.get(0).get("value");// 获取值
						maps.put("code", code);
						maps.put("value", value);
						maps.put("alarmTime", time);
					}
				}
			}
		}

		return maps;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 通过经纬度获取距离(单位：米)
	 *
	 * @param lat1
	 *            纬度1
	 * @param log1
	 *            经度1
	 * @param lat2
	 *            纬度2
	 * @param log2
	 *            经度2
	 * @return 距离（米）
	 */
	public static double getDistance(double lat1, double log1, double lat2, double log2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(log1) - rad(log2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		double EARTH_RADIUS = 6378.137;
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
	}

	/**
	 * 取最大值，或最小值
	 * 
	 * @param filePath
	 *            文件路径
	 * @param code
	 *            站号
	 * @param logic
	 *            >=,<=
	 * @return
	 */
	public static String readFileGetMaxOrMin(String filePath, String code, String logic) {
		String value = "";
		List<String> list = new ArrayList<String>();
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String[] stations = code.split(",");
				for (String s : stations) {
					int line = 1;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						if (line >= 5) {// 第五行起读
							String[] Txts = lineTxt.split("\\s+");
							if (Txts[0].equals(s)) {
								list.add(Txts[4]);
								break;
							}
						}
						line++;
					}
				}

				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		// 正序排序
		list.sort((a, b) -> {
			Double dis1 = Double.parseDouble(a);
			Double dis2 = Double.parseDouble(b);
			return dis1 == dis2 ? 0 : (dis1 > dis2) ? 1 : -1;
		});
		if (list.size() != 0) {
			if (">=".equals(logic)) {// 取最大值
				value = list.get(list.size() - 1);
			} else {// 取最小值
				value = list.get(0);
			}
		}
		return value;
	}

	/**
	 * 区域报警系统
	 * 
	 * @throws Exception
	 */
	public void areaAlarm() throws Exception {
		List<AreaAlarm> areaList = areaalarmService.getAll();
		for (AreaAlarm area : areaList) {
			List<String> list = new ArrayList<String>();
			String alertThreeId = area.getAlertruleThreeId();
			if (!StringUtils.isEmpty(alertThreeId)) {// 如果三级报警存在
				list.add(alertThreeId);
			}
			String alertTwoId = area.getAlertruleTwoId();
			if (!StringUtils.isEmpty(alertTwoId)) {// 如果二级报警存在
				list.add(alertTwoId);
			}
			String alertId = area.getAlertruleId();// 获取报警条件Id
			if (!StringUtils.isEmpty(alertId)) {
			list.add(alertId);
			}

			for (String aleId : list) {// 从等级高开始
				Alertrule alert = alertruleService.get(Long.parseLong(aleId));
				String elementIds = alert.getElementIds();// 获取气象因子
				String relStation = area.getRelStation();
				String rule = alert.getRuleExpr();
				String[] elements = elementIds.split(",");// 气象因子
				Elementinfo eleInfo = elementinfoService.get(Long.parseLong(elements[0]));
				String isForecast = eleInfo.getIsForecast();
				String[] values = new String[elements.length];
				String time = "";
				for (int x = 0; x < elements.length; x++) {
					Map<String, String> map = getAreaThreeData(elements[x], relStation);
					String value = map.get("value");
					values[x] = value;
					time = map.get("alarmTime");
				}
				ScriptEngineManager manager = new ScriptEngineManager();// 给字符串替换值
				ScriptEngine engine = manager.getEngineByName("js");
				for (int x = 0; x < elements.length; x++) {
					String eleId = elementinfoService.get(Long.parseLong(elements[x])).getElementId();
					// System.out.println(values[x]);
					engine.put(eleId, values[x]);
				}
				Object result = engine.eval(rule);// 将字符串转成boolean类型数据返回
				if ((boolean) result) {
					DangerAlarm dangerAlarm = dangerAlarmService
							.findDangerAlarmByTimeAndAreaId(DateUtil.parse(time, "yyyyMMddHHmm"), area.getId());
					if (dangerAlarm == null) {
						DangerAlarm alarm = new DangerAlarm();// 超过阈值记录日志
						alarm.setAreaId(area.getId());
						alarm.setValue(StringUtils.join(values, ","));// 将数组转成逗号分割的字符串
						alarm.setRuleExpr(alert.getRuleExpr());
						alarm.setLevel(alert.getAlertLevel());
						alarm.setDangerTypeId((long) 0);
						alarm.setElementinfoIds(elementIds);
						alarm.setAlarmTime(DateUtil.parse(time, "yyyyMMddHHmm"));
						alarm.setIsForecast(isForecast);
						dangerAlarmService.saveOrUpdate(alarm);
					}
					break;
				}
			}
		}
	}

	/**
	 * 获取区域报警的数据
	 * 
	 * @param elementIds
	 * @param relStation
	 * @param logic
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getAreaThreeData(String elementIds, String relStation) throws Exception {
		Map<String, String> maps = new HashMap<String, String>();
		String path = "";
		String pathName = "";
		Elementinfo elementinfo = elementinfoService.get(Long.parseLong(elementIds));
		// String isForecast = elementinfo.getIsForecast();
		String isForecast = elementinfo.getIsForecast();

		String filePATH = elementinfo.getFilePath();
		String filePath = "";
		if (!StringUtils.isEmpty(filePATH) && filePATH != null) {
			filePath = filePATH.replaceAll("\\\\", "/");
		}
		if (isForecast.equals("0")) {// 实况读取数据
			File file = new File(filePath);
			if (file.exists()) {
				// 列出该目录下所有文件和文件夹
				File[] files = file.listFiles();
				// 按照文件名倒序排序
				assert files != null;
				Arrays.sort(files, (file1, file2) -> file2.getName().compareTo(file1.getName()));
				for (File f : files) {
					if (f.isFile()) {
						path = f.getPath();
						pathName = f.getName();
						break;
					}
				}
				String time = pathName.split("\\.")[0];
				// 获取半小时前的时间
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.MINUTE, -30);
				Date date = calendar.getTime();
				Date date1 = DateUtil.parse(time, "yyyyMMddHHmm");
				assert date1 != null;

				// 如果文件不超过半小时就读取
				if (date.getTime() < date1.getTime()) {
					if (!StringUtils.isEmpty(relStation) && relStation != null) {// 有关联站点的数据
						String value = "";
						String[] relStations = relStation.split(",");
						String stationIiiii = "";
						for (String r : relStations) {
							String iiiii = tabstationService.get(Long.parseLong(r)).getIiiii();
							stationIiiii += iiiii + ",";
						}
						value = readThreeDataGetAvg(path, stationIiiii);
						maps.put("value", value);
						maps.put("alarmTime", time);
					}
				}
			}
		} else if (isForecast.equals("1")) {// 预报读取数据
			int forecastHour = elementinfo.getForecastHour();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.HOUR, +forecastHour);
			Date date = calendar.getTime();
			String forecastDate = DateUtil.format(date, "yyyyMMddHH");
			String nowFile = forecastDate + "00.000";
			String forecastFile = filePath + "/" + nowFile;
			File file = new File(forecastFile);
			if (file.exists()) {
				if (!StringUtils.isEmpty(relStation) && relStation != null) {// 有关联站点的数据
					String value = "";
					String[] relStations = relStation.split(",");
					String stationIiiii = "";
					for (String r : relStations) {
						String iiiii = tabstationService.get(Long.parseLong(r)).getIiiii();
						stationIiiii += iiiii + ",";
					}
					value = readFourDataGetAvg(forecastFile, stationIiiii);
					maps.put("value", value);
					maps.put("alarmTime", forecastDate + "00");
				}
			}

		}

		return maps;
	}

	/**
	 * 取三类数据平均值
	 * 
	 * @param filePath
	 *            文件路径
	 * @param code
	 *            站号
	 * @param logic
	 *            >=,<=
	 * @return
	 */
	public String readThreeDataGetAvg(String filePath, String code) {
		String value = "";
		List<Double> list = new ArrayList<Double>();
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String[] stations = code.split(",");
				for (String s : stations) {
					int line = 1;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						if (line >= 5) {// 第五行起读
							String[] Txts = lineTxt.split("\\s+");
							if (Txts[0].equals(s)) {
								list.add(Double.parseDouble(Txts[4]));
								break;
							}
						}
						line++;
					}
				}

				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		DoubleSummaryStatistics stats = list.stream().mapToDouble(x -> x).summaryStatistics();// 取平均值
		double avg = stats.getAverage();
		value = String.valueOf(getDouble(1, avg));
		return value;
	}

	/**
	 * 读取4类数据获取平均值
	 * 
	 * @param filePath
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String readFourDataGetAvg(String filePath, String code) throws Exception {
		String value = "";
		List<Double> list = new ArrayList<Double>();
		try {
			String contents = FileUtils.readFileToString(new File(filePath), "UTF-8");
			MicapsData4 m4 = new MicapsData4(contents);
			m4.init();
			String[] stations = code.split(",");
			for (String s : stations) {
				Tabstation tab = tabstationService.geTabstationBy(s);
				String eeeee = tab.getEeeee();
				String nnnn = tab.getNnnn();
				double lon = Double.parseDouble(eeeee) / (double) 100;
				double lat = Double.parseDouble(nnnn) / (double) 100;
				double c = m4.getPointValue(lon, lat);
				if (String.valueOf(c).contains("-999")) {
					c = 0.0;
				}
				list.add(c);
			}
			DoubleSummaryStatistics stats = list.stream().mapToDouble(x -> x).summaryStatistics();// 取平均值
			double avg = stats.getAverage();
			value = String.valueOf(getDouble(1, avg));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return value;
	}

	/**
	 * 保留小数点几位小数
	 * 
	 * @param x
	 * @param f
	 * @return
	 */
	public double getDouble(int x, double f) {
		BigDecimal bg = new BigDecimal(f);
		double f1 = bg.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	/**
	 * 删除多余的数据
	 * 
	 * @param dangerAlarm
	 */
	public void deleteMoreData() {
		String d = ConfigHelper.getProperty("delete_date");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -Integer.parseInt(d));
		Date date = c.getTime();
		dangerAlarmService.deleteDangerAlarmByTime(date);

	}

}
