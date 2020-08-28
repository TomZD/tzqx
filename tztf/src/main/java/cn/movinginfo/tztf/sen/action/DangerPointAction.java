package cn.movinginfo.tztf.sen.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.common.utils.Reflex;
import cn.movinginfo.tztf.sen.domain.*;
import cn.movinginfo.tztf.sen.service.*;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @description:DangerPoint action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/yzt/dangerPoint")
@SuppressWarnings("serial")
public class DangerPointAction extends MagicAction<DangerPoint, DangerPointService> {
	@Autowired
	private DangerTypeService dangerTypeService;
	@Autowired
	private PointDangertypeService dangertypeService;
	@Autowired
	private AlertruleService alertruleService;
	@Autowired
	private ElementinfoService elementinfoService;
	@Autowired
	private TabstationService tabstationService;

	/**
	 * 隐患点管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("dangerPointQuery")
	public String indexdangerPointQuery(HttpServletRequest request, Model model,String dangerTypeId){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("dangerTypeId", dangerTypeId);
		return "/yzt/dangerPoint/dangerPointQuery";
	}
	
	/**
	 * 获取列表数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dangerPointData")
	public String dangerPointData(HttpServletRequest request, Model model,String dangerTypeId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map = getParameterMap(request);
		map.put("dangerTypeId", dangerTypeId);
		PageInfo<DangerPoint> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page", page);
		String dangerCh = "";
		String dangerEn = "";
		switch(dangerTypeId) {
			case "1" :
				dangerCh = ConfigExportHelper.getProperty("danger_flood_ch");//山洪
				dangerEn = ConfigExportHelper.getProperty("danger_flood_en");
				break;
			case "3" :
				dangerCh = ConfigExportHelper.getProperty("danger_waterflood_ch");//中小河流
				dangerEn = ConfigExportHelper.getProperty("danger_waterflood_en");
				break;
			case "5" :
				dangerCh = ConfigExportHelper.getProperty("danger_waterlogging_ch");//易涝
				dangerEn = ConfigExportHelper.getProperty("danger_waterlogging_en");
				break;
			case "6" :
				dangerCh = ConfigExportHelper.getProperty("danger_forest_ch");//森林火险
				dangerEn = ConfigExportHelper.getProperty("danger_forest_en");
				break;
			case "7" :
				dangerCh = ConfigExportHelper.getProperty("danger_build_ch");//建筑工地
				dangerEn = ConfigExportHelper.getProperty("danger_build_en");
				break;
			case "9" :
				dangerCh = ConfigExportHelper.getProperty("danger_station_ch");//自动站
				dangerEn = ConfigExportHelper.getProperty("danger_station_en");
				break;
			default :
//				dangerCh = ConfigExportHelper.getProperty("danger_ch");
//				dangerEn = ConfigExportHelper.getProperty("danger_en");
				break;
		}
		
		List<ChineseEnglish> ceList = new ArrayList<ChineseEnglish>();
		List<String> dangerChList = Arrays.asList(dangerCh.split(","));
		List<String> dangerEnList = Arrays.asList(dangerEn.split(","));
		for(int x = 0;x<dangerEnList.size();x++) {
			ChineseEnglish ce = new ChineseEnglish();
			ce.setChinese(dangerChList.get(x));
			ce.setEnglish(dangerEnList.get(x));
			ceList.add(ce);
		}
		String allTypeId = ConfigExportHelper.getProperty("allTypeId");
		model.addAttribute("allTypeId", allTypeId);
		model.addAttribute("dangerChList", dangerChList);
		model.addAttribute("dangerEnList", dangerEnList);
		model.addAttribute("ceList", ceList);
		model.addAttribute("dangerTypeId", dangerTypeId);
		return getNameSpace() + "dangerPointData";
	}



	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editDangerPoint")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		DangerPoint dangerPoint = entityService.get(Long.parseLong(id));
		Long dangerTypeId = dangerPoint.getDangerTypeId();
		String allTypeId = ConfigExportHelper.getProperty("allTypeId");
		if(allTypeId.contains(","+String.valueOf(dangerTypeId)+",")) {
		DangerType dangerType = dangerTypeService.getDangerTypeById(dangerTypeId);
		dangerPoint.setDangerTypeName(dangerType.getName());
		String thresholdValueOne = "0";
		String relativeOne = "0";
		String ElementIdsOne = "0";
		String logicOne = "0";
		String alertOneId = dangerPoint.getAlertruleId();
		if (!StringUtils.isEmpty(alertOneId)) {
			Alertrule alertOne = alertruleService.get(Long.parseLong(alertOneId));
			thresholdValueOne = alertOne.getThresholdValue();
			relativeOne = alertOne.getRelative();
			ElementIdsOne = alertOne.getElementIds();
			logicOne = alertOne.getLogic();
		}

		String thresholdValueTwo = "0";
		String relativeTwo = "0";
		String ElementIdsTwo = "0";
		String logicTwo = "0";
		String alertTwoId = dangerPoint.getAlertruleTwoId();
		if (!StringUtils.isEmpty(alertTwoId)) {
			Alertrule alertTwo = alertruleService.get(Long.parseLong(alertTwoId));
			thresholdValueTwo = alertTwo.getThresholdValue();
			relativeTwo = alertTwo.getRelative();
			ElementIdsTwo = alertTwo.getElementIds();
			logicTwo = alertTwo.getLogic();
		}
		String thresholdValueThree = "0";
		String relativeThree = "0";
		String ElementIdsThree = "0";
		String logicThree = "0";
		String alertThreeId = dangerPoint.getAlertruleThreeId();
		if (!StringUtils.isEmpty(alertThreeId)) {
			Alertrule alertThree = alertruleService.get(Long.parseLong(alertThreeId));
			thresholdValueThree = alertThree.getThresholdValue();
			relativeThree = alertThree.getRelative();
			ElementIdsThree = alertThree.getElementIds();
			logicThree = alertThree.getLogic();
		}
		dangerPoint.setLogic(logicOne + "@" + logicTwo + "@" + logicThree);
		dangerPoint.setThresholdValue(thresholdValueOne + "@" + thresholdValueTwo + "@" + thresholdValueThree);
		dangerPoint.setRelative(relativeOne + "@" + relativeTwo + "@" + relativeThree);
		dangerPoint.setMeteorologicalId(ElementIdsOne + "@" + ElementIdsTwo + "@" + ElementIdsThree);
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(dangerPoint));
	}

	/**
	 * 获取气象因子列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getAllMeteorological")
	public void getAllMeteorological(HttpServletRequest request, HttpServletResponse response) {
		List<Elementinfo> list = elementinfoService.getAll();
		JSONArray json = new JSONArray();
		for (Elementinfo e : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", e.getId());
			jo.put("text", e.getElementName());
			json.put(jo);
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(json.toString()));
	}

	/**
	 * 获取所有的站点
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getRelStation")
	public void getRelStation(HttpServletRequest request, HttpServletResponse response) {
		List<Tabstation> list = tabstationService.getAll();
		JSONArray json = new JSONArray();
		for (Tabstation tab : list) {
			JSONObject jo = new JSONObject();
			jo.put("id", tab.getId());
			jo.put("text", tab.getIiiii());
			json.put(jo);
		}
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(json.toString()));
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteDangerPoint")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		Long dangerTypeId = entityService.get(Long.parseLong(id)).getDangerTypeId();
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
			dangertypeService.deleteByPointId(Long.parseLong(id));
		}
		return "redirect:/yzt/dangerPoint/dangerPointQuery?dangerTypeId="+dangerTypeId;
	}

	/**
	 * 新增保存和修改
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model, String logic,
			String thresholdValue, String meteorologicalId) throws Exception {
		DangerPoint dangerPoint = bindEntity(request, entityClass);
		Long dangerTypeId = dangerPoint.getDangerTypeId();
		String allTypeId = ConfigExportHelper.getProperty("allTypeId");
		if(allTypeId.contains(","+String.valueOf(dangerTypeId)+",")) {
		String[] meIds = meteorologicalId.split("@");
		String[] vals = thresholdValue.split("@");
		String[] logs = logic.split("@");
		String[] rels = dangerPoint.getRelative().split("@");
		for (String me : meIds) {
			String[] meteorologicalIds = me.split(",");
			HashSet<String> hashSet = new HashSet<String>();
			for (int i = 0; i < meteorologicalIds.length; i++) {// 判断气象因子是否重复
				hashSet.add(meteorologicalIds[i]);
			}
			if (hashSet.size() != meteorologicalIds.length) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("同一阈值条件不能存在相同的气象因子！"));
				return;
			}
		}
		Alertrule alert = null;
		String alertId = null;
		DangerPoint dangerP = entityService.getDangerPointByLonLat(dangerPoint.getLongitude(), dangerPoint.getLatitude(),dangerTypeId);
		for (int i = 0; i < meIds.length; i++) {
			if (dangerPoint.getId() != null) {
				if(!dangerP.getId().equals(dangerPoint.getId()) ) {
					printJson(response, com.alibaba.fastjson.JSON.toJSONString("该隐患点已存在！"));
					return;
				}
				DangerPoint dp = entityService.get(dangerPoint.getId());
				if (i == 0) {
					if (!StringUtils.isEmpty(dp.getAlertruleId())) {
						alert = alertruleService.get(Long.parseLong(dp.getAlertruleId()));
					} else {
						alert = new Alertrule();
						alert.setAlertLevel(i + 1);
					}
				} else if (i == 1) {
					if (!StringUtils.isEmpty(dp.getAlertruleTwoId())) {
						alert = alertruleService.get(Long.parseLong(dp.getAlertruleTwoId()));
					} else {
						alert = new Alertrule();
						alert.setAlertLevel(i + 1);
					}
				} else if (i == 2) {
					if (!StringUtils.isEmpty(dp.getAlertruleThreeId())) {
						alert = alertruleService.get(Long.parseLong(dp.getAlertruleThreeId()));
					} else {
						alert = new Alertrule();
						alert.setAlertLevel(i + 1);
					}
				}
				
			} else {
				if(dangerP != null) {
					printJson(response, com.alibaba.fastjson.JSON.toJSONString("该隐患点已存在！"));
					return;
				}
				alert = new Alertrule();
				alert.setAlertLevel(i + 1);
			}

			if (meIds[i].equals("0")) {
				alertId = "";
			} else {
				String[] elements = meIds[i].split(",");
				String[] values = vals[i].split(",");
				String[] logics = logs[i].split(",");
				String[] relatives = rels[i].split(",");
				alert.setLogic(logs[i]);
				alert.setElementIds(meIds[i]);
				alert.setThresholdValue(vals[i]);
				alert.setRelative(rels[i]);
				alert.setType("隐患点");
				String ruleEpr = "";
				for (int x = 0; x < elements.length; x++) {
					Elementinfo ele = elementinfoService.get(Long.parseLong(elements[x]));
					String eleId = ele.getElementId();
					if (x > 0) {
						ruleEpr += relatives[x - 1];
					}
					ruleEpr += eleId + logics[x] + values[x];

				}
				alert.setRuleExpr(ruleEpr);
				alertId = String.valueOf(alertruleService.saveOrUpdate(alert));
			}

			dangerPoint.setOverThreshold(0);
				if (i == 0) {
					dangerPoint.setAlertruleId(String.valueOf(alertId));
				} else if (i == 1) {
					dangerPoint.setAlertruleTwoId(String.valueOf(alertId));
				} else if (i == 2) {
					dangerPoint.setAlertruleThreeId(String.valueOf(alertId));
				}
		    }
		}

		try {
			if (dangerPoint.getId() == null) {
				dangerPoint.setCity("台州市");
				dangerPoint.setCounty("通州区");
				entityService.insertSelective(dangerPoint);
			} else {
				entityService.update(dangerPoint);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// alertruleService.delete(Long.parseLong(alertId));

		}

	}


	@RequestMapping(value = "getData")
	@ResponseBody
	public void getData() {
		List<Tabstation> list = tabstationService.getAll();
		for (Tabstation t : list) {
			DangerPoint dp = new DangerPoint();
			dp.setLongitude(String.valueOf(Double.parseDouble(t.getEeeee()) / (double) 100));
			dp.setLatitude(String.valueOf(Double.parseDouble(t.getNnnn()) / (double) 100));
			dp.setName(t.getStationName());
			dp.setRelStation(String.valueOf(t.getId()));
			dp.setDangerTypeId(Long.parseLong("9"));
			dp.setAlertruleId("1");
			dp.setThresholdValue("0");
			entityService.saveOrUpdate(dp);
		}
	}
	
	/**
     * 导入数据
     * @param file
     * @param request
     * @throws Exception
     */
	@RequestMapping(value = "import")
	@ResponseBody
	public void uploadPage(@RequestParam("file")  CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response,String dangerTypeId)
			throws Exception {
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
		entityService.readXls(root,dangerTypeId);



		printJson(response, com.alibaba.fastjson.JSON.toJSONString("导入成功！"));
	}
	
	
	/**
	   * 导出xls表
	   * @param request
	   * @param response
	   * @param
	   * @throws ParseException
	   */
	  @RequestMapping(value = "/exportData")
	  @ResponseBody
	  public void exportData( HttpServletRequest request, HttpServletResponse response,String dangerTypeId) throws ParseException{
		  List<DangerPoint> list = entityService.getDangerPointByDangerTypeId(Long.parseLong(dangerTypeId));
		  DangerType dt = dangerTypeService.get(Long.parseLong(dangerTypeId));
		  String name = dt.getName();
		  String allTypeId = ConfigExportHelper.getProperty("allTypeId");
		  if(allTypeId.contains(","+dangerTypeId+",")) {
		  for(DangerPoint dp : list) {
			  dp.setRain("不包含");
			  dp.setRainValue("None");
			  dp.setWaterLevel("不包含");
			  dp.setWaterLevelValue("None");
			  dp.setGround("不包含");
			  dp.setGroundValue("None");//默认设置
			  String alertId = dp.getAlertruleId();
			  Alertrule alert = alertruleService.get(Long.parseLong(alertId));
			  String value = alert.getThresholdValue();
			  String elemenId = alert.getElementIds();
			  String[] elementIds = elemenId.split(",");
			  String[] values = value.split(",");
			  for(int x=0;x<elementIds.length;x++) {
				  Elementinfo eleInfo = elementinfoService.get(Long.parseLong(elementIds[x]));
				  String eleName = eleInfo.getElementId();
				  if(eleName.equals("ls_rain")) {//是否包含降水
					  dp.setRain("包含");
					  dp.setRainValue(values[x]);
				  }else  {
					  
				  }
			  }
			  
			  String relStation = dp.getRelStation();//站点由主键转成站号
			  if(!relStation.equals("") && !relStation.equals(null)) {
				  String[] relStations = relStation.split(",");
				  for(String rel : relStations) {
					  Tabstation tab = tabstationService.get(Long.parseLong(rel));
					  String iiiii = tab.getIiiii();
					  relStation = (","+relStation+",").replaceAll(","+rel+",", ","+iiiii+",");
				  }
				  String regex = "^,*|,*$";//规则字符串前后去逗号
				  String str = relStation.replaceAll(regex, "");
				  dp.setRelStation(str);
			  }
		    }
		  }
		  HSSFWorkbook wb = new HSSFWorkbook();//声明工
	        Sheet sheet = wb.createSheet("sheet1");//新建表
	        //sheet.setDefaultColumnWidth(50);//设置表宽
	        sheet.setDefaultColumnWidth((short) 25);//设置表宽
	        HSSFCellStyle titleStyle = wb.createCellStyle();
	        //设置标题字体样式
	        org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
	        titleFont.setFontHeightInPoints((short)15);//设置大小
	        titleStyle.setFont(titleFont);
	        titleStyle.setWrapText(true);
	        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
	        titleStyle.setFillForegroundColor(HSSFColor.YELLOW.index);//填红色
	        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
	        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	        //设置内容字体样式
	        HSSFCellStyle style = wb.createCellStyle();
	        org.apache.poi.hssf.usermodel.HSSFFont Font = wb.createFont();
	        Font.setFontHeightInPoints((short)11);//设置大小
	        style.setFont(Font);
	        style.setWrapText(true);
	        String title = "";
	        String exportjson = "";
	        switch (dangerTypeId) {
			case "1":
				 title = ConfigExportHelper.getProperty("danger_flood_title");
		         exportjson = ConfigExportHelper.getProperty("danger_flood_export");
				break;
			case "3":
				 title = ConfigExportHelper.getProperty("danger_waterflood_title");
		         exportjson = ConfigExportHelper.getProperty("danger_waterflood_export");
		         break;
			case "5":
				 title = ConfigExportHelper.getProperty("danger_waterlogging_title");
		         exportjson = ConfigExportHelper.getProperty("danger_waterlogging_export");
		         break;
			case "6":
				 title = ConfigExportHelper.getProperty("danger_forest_title");
		         exportjson = ConfigExportHelper.getProperty("danger_forest_export");
		         break;
			case "7":
				 title = ConfigExportHelper.getProperty("danger_build_title");
		         exportjson = ConfigExportHelper.getProperty("danger_build_export");
		         break;     
			case "9":
				 title = ConfigExportHelper.getProperty("danger_station_title");
		         exportjson = ConfigExportHelper.getProperty("danger_station_export");
		         break;
			default:
				break;
			}
	        
	        Map<String, Object> map = JSON.parseObject(exportjson);
	        Row row1 = sheet.createRow(0);
	        row1.setHeight((short)400);//设置标题表高
	        Cell cell ;
	        String[] titles = title.split(",");
	        for(int x=0;x<titles.length;x++) {
	        	cell = row1.createCell(x);
		        cell.setCellValue(titles[x]);
		        cell.setCellStyle(titleStyle);
	        }
	        int i=0;
	        for(int x=0;x<list.size();x++) {
	        	row1 = sheet.createRow(++i);
	        	row1.setHeight((short)300);//内容表高
		        for(int n=0;n<titles.length;n++){
		        	Cell cell1 =row1.createCell(n);
		        	cell1.setCellValue((String)Reflex.method(list.get(x),(String)map.get(titles[n])));//从map中取出对应的属性再通过反射取值
		        	cell1.setCellStyle(style);
		        	
		        }
	        }
	        
	        
	        response.reset();
	        response.setContentType("application/msexcel;charset=UTF-8");
	        try {
	            SimpleDateFormat newsdf=new SimpleDateFormat("yyyyMMddHHmmss");
	            String date = newsdf.format(new Date());
	            response.addHeader("Content-Disposition", "attachment;filename=\""
	                    + new String(("导出-"+name + "信息" + date + ".xls").getBytes("Utf-8"),
	                            "ISO8859_1") + "\"");
	            OutputStream out = response.getOutputStream();
	            wb.write(out);
	            out.flush();
	            out.close();
	        } catch (FileNotFoundException e) {
	            JOptionPane.showMessageDialog(null, "导出失败!");
	            e.printStackTrace();
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "导出失败!");
	            e.printStackTrace();
	        }
	  }
	  
	  
	  /**
	   * 导出xls模板
	   * @param request
	   * @param response
	   * @param
	   * @throws ParseException
	   */
	  @RequestMapping(value = "/exportTemplet")
	  @ResponseBody
	  public void exportTemplet( HttpServletRequest request, HttpServletResponse response,String dangerTypeId) throws ParseException{
		  DangerType dt = dangerTypeService.get(Long.parseLong(dangerTypeId));
		  DangerPoint dp = entityService.getFirstDangerPointBydangerId(Long.parseLong(dangerTypeId));
		  String allTypeId = ConfigExportHelper.getProperty("allTypeId");
		  if(allTypeId.contains(","+dangerTypeId+",")) {
		  dp.setRain("不包含");
		  dp.setRainValue("None");
		  dp.setWaterLevel("不包含");
		  dp.setWaterLevelValue("None");
		  dp.setGround("不包含");
		  dp.setGroundValue("None");//默认设置
		  String alertId = dp.getAlertruleId();
		  Alertrule alert = alertruleService.get(Long.parseLong(alertId));
		  String value = alert.getThresholdValue();
		  String elemenId = alert.getElementIds();
		  String[] elementIds = elemenId.split(",");
		  String[] values = value.split(",");
		  for(int x=0;x<elementIds.length;x++) {
			  Elementinfo eleInfo = elementinfoService.get(Long.parseLong(elementIds[x]));
			  String eleName = eleInfo.getElementId();
			  if(eleName.equals("ls_rain")) {//是否包含降水
				  dp.setRain("包含");
				  dp.setRainValue(values[x]);
			  }else  {
				  
			  }
		  }
		  
		  String relStation = dp.getRelStation();//站点由主键转成站号
		  if(!relStation.equals("") && !relStation.equals(null)) {
			  String[] relStations = relStation.split(",");
			  for(String rel : relStations) {
				  Tabstation tab = tabstationService.get(Long.parseLong(rel));
				  String iiiii = tab.getIiiii();
				  relStation = (","+relStation+",").replaceAll(","+rel+",", ","+iiiii+",");
			  }
			  String regex = "^,*|,*$";//规则字符串前后去逗号
			  String str = relStation.replaceAll(regex, "");
			  dp.setRelStation(str);
		  }
		  }
		  String name = dt.getName();
		  HSSFWorkbook wb = new HSSFWorkbook();//声明工
	        Sheet sheet = wb.createSheet("sheet1");//新建表
	        //sheet.setDefaultColumnWidth(50);//设置表宽
	        sheet.setDefaultColumnWidth((short) 25);//设置表宽
	        HSSFCellStyle titleStyle = wb.createCellStyle();
	        //设置标题字体样式
	        org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
	        titleFont.setFontHeightInPoints((short)15);//设置大小
	        titleStyle.setFont(titleFont);
	        titleStyle.setWrapText(true);
	        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
	        titleStyle.setFillForegroundColor(HSSFColor.YELLOW.index);//填红色
	        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
	        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	        //设置内容字体样式
	        HSSFCellStyle style = wb.createCellStyle();
	        org.apache.poi.hssf.usermodel.HSSFFont Font = wb.createFont();
	        Font.setFontHeightInPoints((short)11);//设置大小
	        style.setFont(Font);
	        style.setWrapText(true);
	        String title = "";
	        String exportjson = "";
	        switch (dangerTypeId) {
			case "1":
				 title = ConfigExportHelper.getProperty("danger_flood_title");
		         exportjson = ConfigExportHelper.getProperty("danger_flood_export");
				break;
			case "3":
				 title = ConfigExportHelper.getProperty("danger_waterflood_title");
		         exportjson = ConfigExportHelper.getProperty("danger_waterflood_export");
		         break;
			case "5":
				 title = ConfigExportHelper.getProperty("danger_waterlogging_title");
		         exportjson = ConfigExportHelper.getProperty("danger_waterlogging_export");
		         break;
			case "6":
				 title = ConfigExportHelper.getProperty("danger_forest_title");
		         exportjson = ConfigExportHelper.getProperty("danger_forest_export");
		         break;
			case "7":
				 title = ConfigExportHelper.getProperty("danger_build_title");
		         exportjson = ConfigExportHelper.getProperty("danger_build_export");
		         break; 
			case "9":
				 title = ConfigExportHelper.getProperty("danger_station_title");
		         exportjson = ConfigExportHelper.getProperty("danger_station_export");
		         break;    
			default:
				break;
			}
	        
	        Map<String, Object> map = JSON.parseObject(exportjson);
	        Row row1 = sheet.createRow(0);
	        row1.setHeight((short)400);//设置标题表高
	        Cell cell ;
	        String[] titles = title.split(",");
	        for(int x=0;x<titles.length;x++) {
	        	cell = row1.createCell(x);
		        cell.setCellValue(titles[x]);
		        cell.setCellStyle(titleStyle);
	        }
	        
	        if(dp != null) {
	        	row1 = sheet.createRow(1);
	        	row1.setHeight((short)300);//内容表高
		        for(int n=0;n<titles.length;n++){
		        	Cell cell1 =row1.createCell(n);
		        	cell1.setCellValue((String)Reflex.method(dp,(String)map.get(titles[n])));//从map中取出对应的属性再通过反射取值
		        	cell1.setCellStyle(style);
		        	
		        }
	        }
	        
	        
	        response.reset();
	        response.setContentType("application/msexcel;charset=UTF-8");
	        try {
	            SimpleDateFormat newsdf=new SimpleDateFormat("yyyyMMddHHmmss");
	            String date = newsdf.format(new Date());
	            response.addHeader("Content-Disposition", "attachment;filename=\""
	                    + new String(("导出-"+name + "模板" + date + ".xls").getBytes("Utf-8"),
	                            "ISO8859_1") + "\"");
	            OutputStream out = response.getOutputStream();
	            wb.write(out);
	            out.flush();
	            out.close();
	        } catch (FileNotFoundException e) {
	            JOptionPane.showMessageDialog(null, "导出失败!");
	            e.printStackTrace();
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "导出失败!");
	            e.printStackTrace();
	        }
	  }

}
