package cn.movinginfo.tztf.sen.service;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.sen.domain.*;
import cn.movinginfo.tztf.sen.mapper.DangerPointMapper;
import cn.movinginfo.tztf.sys.service.DictService;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class DangerPointService extends BaseService<DangerPoint, DangerPointMapper> {
	@Autowired
	private DangerTypeService dangerTypeService;

	@Autowired
	private DictService dictService;

	@Autowired
	private PointDangertypeService dangertypeService;

	@Resource
	private DangerPointMapper dangerPointMapper;

	@Autowired
	private AlertruleService alertruleService;

	@Autowired
	private ElementinfoService elementinfoService;

	@Autowired
	private TabstationService tabstationService;

	/**
	 * 根据条件查询分页
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<DangerPoint> query(Map<String, String> paramMap) {
		Example example = new Example(DangerPoint.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		String name = paramMap.get("name");
		String dangerTId = paramMap.get("dangerTypeId");
		if (!StringUtils.isEmpty(dangerTId)) {
			criteria.andEqualTo("dangerTypeId", dangerTId);
		}
		if (!StringUtils.isEmpty(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		example.setOrderByClause("id desc");
		List<DangerPoint> list = mapper.selectByExample(example);
		String allTypeId = ConfigExportHelper.getProperty("allTypeId");
		if(allTypeId.contains(","+dangerTId+",")) {
		for (DangerPoint danger : list) {
			Long dangerTypeId = danger.getDangerTypeId();
			DangerType dangerType = dangerTypeService.getDangerTypeById(dangerTypeId);
			danger.setDangerTypeName(dangerType.getName());
			String alertNameOne = "";
			String alertOneId = danger.getAlertruleId();
			if (!StringUtils.isEmpty(alertOneId)) {
				Alertrule alertOne = alertruleService.get(Long.parseLong(alertOneId));
				alertNameOne = alertOne.getRuleExpr();
			}
			danger.setAlertName(alertNameOne);
			String alertNameTwo = "";
			String alertTwoId = danger.getAlertruleTwoId();
			if (!StringUtils.isEmpty(alertTwoId)) {
				Alertrule alertTwo = alertruleService.get(Long.parseLong(alertTwoId));
				alertNameTwo = alertTwo.getRuleExpr();
			}
			danger.setAlertNameTwo(alertNameTwo);
			String alertNameThree = "";
			String alertThreeId = danger.getAlertruleThreeId();
			if (!StringUtils.isEmpty(alertThreeId)) {
				Alertrule alertThree = alertruleService.get(Long.parseLong(alertThreeId));
				alertNameThree = alertThree.getRuleExpr();
			}
			danger.setAlertNameThree(alertNameThree);
			String relStation = danger.getRelStation();
			String[] rels = relStation.split(",");
			String stationIiiii = "";
			for (String r : rels) {
				if (!StringUtils.isEmpty(r)) {
					String iiiii = tabstationService.get(Long.parseLong(r)).getIiiii();
					stationIiiii += iiiii + ";";
				}
			}
			danger.setStationIiiii(stationIiiii == ""? "":stationIiiii.substring(0,stationIiiii.length()-1));
		  }
		}
		return list;
	}

	public List<DangerPoint> getDangerPointByDangerTypeId(Long dangerTypeId) {
		Example example = new Example(DangerPoint.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		criteria.andEqualTo("dangerTypeId", dangerTypeId);
		return mapper.selectByExample(example);
	}

	public List<DangerPoint> getUntreatedDangerPoint() {
		Example example = new Example(DangerPoint.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		List<DangerPoint> list = mapper.selectByExample(example);
		return list;
	}

	public List<DangerPoint> selectAll() {
		Example example = new Example(DangerPoint.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		List<DangerPoint> list = mapper.selectByExample(example);
		return list;
	}

	public void insertSelective(DangerPoint dangerPoint) {
		mapper.insertSelective(dangerPoint);
	}

	public void update(DangerPoint dangerPoint) {
		mapper.updateByPrimaryKeySelective(dangerPoint);
	}
	
	public DangerPoint getFirstDangerPointBydangerId(Long dangerTypeId) {
		return mapper.getFirstDangerPointByDangerTypeId(dangerTypeId);
	}

	public DangerPoint getValueByType(String string) {
		Example example = new Example(DangerPoint.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("dangerTypeId", string);
		List<DangerPoint> list = mapper.selectByExample(example);
		if (list != null) {
			return list.get(0);
		}
		return null;

	}

	public List<DangerPoint> getDangerPointByRelStation(String relStation) {
		return mapper.getDangerPointByRelStation(relStation);
	}

	public DangerPoint getDangerPointByLonLat(String lon, String lat,Long dangerTypeId) {
		return mapper.getDangerPointByLonLatTypeId(lon, lat, dangerTypeId);
	}

	public void readXls(String root, String dangerTypeId) throws Exception {
		// InputStream is = new FileInputStream(root);
		// String danger_export = ConfigHelper.getProperty("danger_export");
		// HSSFWorkbook excel = new HSSFWorkbook(is);//读取xls
		// DangerPoint dp = null;
		//
		// // 循环工作表Sheet
		// for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
		// HSSFSheet sheet = excel.getSheetAt(numSheet);
		// if (sheet == null)
		// continue;
		// // 循环行Row
		// for (int rowNum = 0; rowNum < sheet.getLastRowNum()+1; rowNum++) {
		// HSSFRow row = sheet.getRow(rowNum);
		// if(row != null) {
		// if(rowNum == 0) {
		// for(int x=0;x<21;x++) {
		// HSSFCell name = row.getCell(x);
		// if(!StringUtils.isEmpty(String.valueOf(name)) && name != null) {
		// name.setCellType(HSSFCell.CELL_TYPE_STRING);
		// System.out.println(String.valueOf(name));
		// danger_export=danger_export.replaceAll(String.valueOf(name),
		// ","+String.valueOf(x)+",");
		// }
		// }
		//
		// }
		//
		// }
		// System.out.println(danger_export);
		// }
		// }
		String danger_export="";
		if(!"".equals(dangerTypeId)) {
			switch(dangerTypeId) {
				case "1" :
					danger_export = ConfigExportHelper.getProperty("danger_flood_lead");//山洪
					break;
				case "3" :
					danger_export = ConfigExportHelper.getProperty("danger_waterflood_lead");//中小河流域
					break;
				case "5" :
					danger_export = ConfigExportHelper.getProperty("danger_waterlogging_lead");//易涝点
					break;
				case "6" :
					danger_export = ConfigExportHelper.getProperty("danger_forest_lead");//森林火险
					break;
				case "7" :
					danger_export = ConfigExportHelper.getProperty("danger_build_lead");//建筑工地
					break;
				case "9" :
					danger_export = ConfigExportHelper.getProperty("danger_station_lead");//自动站
					break;	
				default : 
					break;
			}
				
		}
		String dangerPoint = "";
		InputStream is = new FileInputStream(root);
//		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);// 读取xlsx格式
//		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		Workbook workbook = WorkbookFactory.create(is);
		Sheet hssfSheet = workbook.getSheetAt(0);  //示意访问sheet

		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();

		for (int i = rowstart; i <= rowEnd; i++) {
			dangerPoint = danger_export;
			Row row = hssfSheet.getRow(i);
			if (null == row)
				continue;
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();

			for (int k = cellStart; k <= cellEnd; k++) {
				Cell cell =  row.getCell(k);
				if (null == cell) {
					System.out.print(k + "cell null");
					dangerPoint = dangerPoint.replaceAll("," + String.valueOf(k) + ",", "");
					continue;
				}
				if (i == rowstart) {// 第一行的所有字段
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						// System.out.print(cell.getStringCellValue() + "\t");
						if (cell.getStringCellValue().equals("致灾因子：降水")) {// 字和列数替换
							danger_export = danger_export.replace("," + cell.getStringCellValue() + ",",
									"ls_rain=," + String.valueOf(k) + ",");
						} else {
							danger_export = danger_export.replace("," + cell.getStringCellValue() + ",",
									"," + String.valueOf(k) + ",");
						}

						break;
					case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
						System.out.println(cell.getBooleanCellValue() + "\t");
						break;
					case HSSFCell.CELL_TYPE_FORMULA: // 公式
						System.out.print(cell.getCellFormula() + "\t");
						break;
					case HSSFCell.CELL_TYPE_BLANK: // 空值
						System.out.println(" 控制");
						break;
					case HSSFCell.CELL_TYPE_ERROR: // 故障
						System.out.println(" 故障");
						break;
					default:
						System.out.print("未知类型 ");
						break;
					}
				} else {
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);//再转成String
						dangerPoint = dangerPoint.replaceAll("," + String.valueOf(k) + ",", cell.toString());
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串

						dangerPoint = dangerPoint.replaceAll("," + String.valueOf(k) + ",", cell.getStringCellValue());
						break;
					default:
						break;
					}

				}
			}
			if (i > rowstart) {// 第二行开始
				DangerPoint dp = JSON.parseObject(dangerPoint, DangerPoint.class);// json字符串转对象
				String lon = String.valueOf(getTwoDouble(4, Double.parseDouble(dp.getLongitude())));//保留3位小数
				String lat = String.valueOf(getTwoDouble(4, Double.parseDouble(dp.getLatitude())));//保留3位小数
				String phone = dp.getPhone();
				if(StringUtils.isEmpty(phone)) {
					if(validatemobile(phone) ==false) {//判断手机号码
						continue;
					}
				}
				DangerPoint danger = getDangerPointByLonLat(lon, lat,Long.parseLong(dangerTypeId));
				if (danger != null) {// 通过经纬度判断是否存在
					dp.setId(danger.getId());;
				}
				dp.setPhone(phone);
				dp.setCity(dp.getCity());
				dp.setCounty(dp.getCounty());
				dp.setTown(dp.getTown());
//				saveOrUpdate(dp);
				String allTypeId = ConfigExportHelper.getProperty("allTypeId");
				if(allTypeId.contains(","+dangerTypeId+",")) {
				String alertName = dp.getAlertName();// ls_rain=包含>=100||不包含>=None||不包含>=None
				String[] alertNames = alertName.split("\\|\\|");
				List<String> list = new ArrayList<>();
				for (String a : alertNames) {
					if (a.contains("=包含")) {// 筛选存在的气象因子
						a = a.replace("=包含", "");
						list.add(a);
					}
				}
				String alertId = "";
				if(list.size() != 0) {
					String logic = "";// >=
					String value = "";// 阈值
					String relative = "0";// 关系
					String ruleExpr = "";// 规则
					String elementIds = "";// 气象因子
					if (list.size() > 1) {
						relative = "";
					}
					for (int x = 0; x < list.size(); x++) {
						if (x > 0) {
							relative += "||,";
							ruleExpr += "||" + list.get(x);
						} else {
							ruleExpr += list.get(x);
						}
						logic += ">=,";
						value += list.get(x).split(">=")[1] + ",";
						Elementinfo element = elementinfoService.getElementByElementId(list.get(x).split(">=")[0]);
						elementIds += String.valueOf(element.getId()) + ",";

					}
					Alertrule alert = new Alertrule();// 创建规则
					alert.setAlertLevel(1);
					alert.setLogic(logic.substring(0, logic.length() - 1));
					alert.setElementIds(elementIds.substring(0, elementIds.length() - 1));
					alert.setThresholdValue(value.substring(0, value.length() - 1));
					alert.setRelative(relative == "0" ? relative : relative.substring(0, relative.length() - 1));
					alert.setRuleExpr(ruleExpr);
					alert.setType("隐患点");
					 alertId = String.valueOf(alertruleService.saveOrUpdate(alert));
				}
				
				dp.setAlertruleId(alertId);
				dp.setOverThreshold(0);// 设置不超阈值
				String relStation = dp.getRelStation();
				String[] relStations = relStation.split(",");
				for (String rel : relStations) {
					Tabstation tab = tabstationService.geTabstationBy(rel);
					String id = String.valueOf(tab.getId());
					relStation = relStation.replaceAll(rel, id);// 将站号换成，主键
				  }
				dp.setRelStation(relStation);
				}
				dp.setLongitude(lon);;
				dp.setLatitude(lat);
				dp.setDangerTypeId(Long.parseLong(dangerTypeId));
				
				saveOrUpdate(dp);
				System.out.println(dp);
			}

		}

	}

	// 保留小数后几位
	public double getTwoDouble(int x, double f) {
		BigDecimal bg = new BigDecimal(f);
		double f1 = bg.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
	
	//电话验证
	public boolean validatemobile(String phone) {
		String regex = "^[1][3,5,7,8][0-9]{9}$";
//		String regex = "^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]) {1}|(18[0-9]{1}))+\\\\d{8})$";
		Pattern p1 = Pattern.compile(regex);
		boolean isTure = p1.matcher(phone).matches();
		if(phone.length() != 11) {
			return false;
		}else if(phone.length() == 0) {
			return false;
		}else if(!isTure) {
			return false;
		}
		return true;
	}


}
