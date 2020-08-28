package cn.movinginfo.tztf.sen.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.common.utils.Reflex;
import cn.movinginfo.tztf.sen.domain.ChineseEnglish;
import cn.movinginfo.tztf.sen.domain.KeyPlace;
import cn.movinginfo.tztf.sen.domain.PlaceType;
import cn.movinginfo.tztf.sen.service.KeyPlaceService;
import cn.movinginfo.tztf.sen.service.MenuService;
import cn.movinginfo.tztf.sen.service.PlaceTypeService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
 * @description:KeyPlace action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/yzt/keyPlace")
@SuppressWarnings("serial")
public class KeyPlaceAction extends MagicAction<KeyPlace, KeyPlaceService> {
	@Autowired
	private PlaceTypeService placeTypeService;
	@Autowired
	private MenuService menuService;
	

	/**
	 * 重点场所管理页面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("keyPlaceQuery")
	public String indexKeyPlaceQuery(HttpServletRequest request, Model model,String typeId) {
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("typeId", typeId);
		return "/yzt/keyPlace/keyPlaceQuery";
	}

	/**
	 * 获取列表数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/keyPlaceData")
	public String keyPlaceData(HttpServletRequest request, Model model,String typeId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map = getParameterMap(request);
		map.put("typeId", typeId);
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject.toJSONString(pageInfo, new DictFilter()));
		model.addAttribute("page", page);
		String placeCh = "";
		String placeEn = "";
		switch(typeId) {
			case "2" ://农业园区
				placeCh = ConfigExportHelper.getProperty("place_agricultural_ch");
				placeEn = ConfigExportHelper.getProperty("place_agricultural_en");
				break;
			case "4" ://旅游景点
				placeCh = ConfigExportHelper.getProperty("place_tour_ch");
				placeEn = ConfigExportHelper.getProperty("place_tour_en");
				break;
			case "6" ://山塘水库
				placeCh = ConfigExportHelper.getProperty("place_reservoir_ch");
				placeEn = ConfigExportHelper.getProperty("place_reservoir_en");
				break;
			case "7" ://堤坝
				placeCh = ConfigExportHelper.getProperty("place_dyke_ch");
				placeEn = ConfigExportHelper.getProperty("place_dyke_en");
				break;
			case "10" ://学校
				placeCh = ConfigExportHelper.getProperty("place_school_ch");
				placeEn = ConfigExportHelper.getProperty("place_school_en");
				break;
			case "11" ://医院
				placeCh = ConfigExportHelper.getProperty("place_hospital_ch");
				placeEn = ConfigExportHelper.getProperty("place_hospital_en");
				break;	
			case "14" ://农家乐
				placeCh = ConfigExportHelper.getProperty("place_farm_ch");
				placeEn = ConfigExportHelper.getProperty("place_farm_en");
				break;
			case "15" ://其他单位
				placeCh = ConfigExportHelper.getProperty("place_other_ch");
				placeEn = ConfigExportHelper.getProperty("place_other_en");
				break;
			case "16" ://乡镇驻点
				placeCh = ConfigExportHelper.getProperty("place_town_ch");
				placeEn = ConfigExportHelper.getProperty("place_town_en");
				break;	
			default :
				break;
		}
		List<ChineseEnglish> ceList = new ArrayList<ChineseEnglish>();
		List<String> placeChList = Arrays.asList(placeCh.split(","));
		List<String> placeEnList = Arrays.asList(placeEn.split(","));
		for(int x = 0;x<placeEnList.size();x++) {
			ChineseEnglish ce = new ChineseEnglish();
			ce.setChinese(placeChList.get(x));
			ce.setEnglish(placeEnList.get(x));
			ceList.add(ce);
		}
		model.addAttribute("placeChList", placeChList);
		model.addAttribute("placeEnList", placeEnList);
		model.addAttribute("ceList", ceList);
		model.addAttribute("typeId", typeId);
		return getNameSpace() + "keyPlaceData";
	}

	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editKeyPlace")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		KeyPlace keyPlace = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(keyPlace));
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteKeyPlace")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		Long typeId = entityService.get(Long.parseLong(id)).getTypeId();
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/yzt/keyPlace/keyPlaceQuery?typeId="+typeId;
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
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		KeyPlace keyPlace = bindEntity(request, entityClass);
		Long typeId = keyPlace.getTypeId();
		String name = keyPlace.getName();
		KeyPlace kp = entityService.getKeyPlaceByNameAndTypeId(name, typeId);
		if(keyPlace.getId() != null) {
			if(kp != null && !keyPlace.getId().equals(kp.getId())) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该单位名称已存在！"));
				return;
			}
		}else {
			if(kp != null) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该单位名称已存在！"));
				return;
			}
		}
		keyPlace.setCity("台州市");
		keyPlace.setCounty("黄岩区");
		entityService.saveOrUpdate(keyPlace);
	}
	
	
	/**
     * 导入数据
     * @param file
     * @param request
     * @throws Exception
     */
	@RequestMapping(value = "import")
	@ResponseBody
	public void uploadPage(@RequestParam("file")  CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response,String typeId)
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
		entityService.readXls(root,typeId);
		printJson(response, com.alibaba.fastjson.JSON.toJSONString("导入成功！"));
	}
	
	
	/**
	   * 导出xls表
	   * @param request
	   * @param response
	   * @param year
	   * @throws ParseException
	   */
	  @RequestMapping(value = "/exportData")
	  @ResponseBody
	  public void exportData( HttpServletRequest request, HttpServletResponse response,String typeId) throws ParseException{
		  List<KeyPlace> list = entityService.getKeyPlaceByTypeId(Long.parseLong(typeId));
		  PlaceType pt = placeTypeService.get(Long.parseLong(typeId));
		  String name = pt.getName();
		  HSSFWorkbook wb = new HSSFWorkbook();//声明工
	        Sheet sheet = wb.createSheet("sheet1");//新建表
	        sheet.setDefaultColumnWidth((short) 25);//设置表宽
	        sheet.setColumnWidth(0, 11000);//设置第一例的宽度
	        if(typeId.equals("11")) {//医院
		        sheet.setColumnWidth(10, 11000);
	        }
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
	        switch (typeId) {
		        case "2":
					 title = ConfigExportHelper.getProperty("place_agricultural_title");
			         exportjson = ConfigExportHelper.getProperty("place_agricultural_export");
					break;
		        case "4":
					 title = ConfigExportHelper.getProperty("place_tour_title");
			         exportjson = ConfigExportHelper.getProperty("place_tour_export");
					break;
		        case "6":
					 title = ConfigExportHelper.getProperty("place_reservoir_title");
			         exportjson = ConfigExportHelper.getProperty("place_reservoir_export");
					break;
		        case "7":
					 title = ConfigExportHelper.getProperty("place_dyke_title");
			         exportjson = ConfigExportHelper.getProperty("place_dyke_export");
					break;
		        case "10":
					 title = ConfigExportHelper.getProperty("place_school_title");
			         exportjson = ConfigExportHelper.getProperty("place_school_export");
					break;
				case "11":
					 title = ConfigExportHelper.getProperty("place_hospital_title");
			         exportjson = ConfigExportHelper.getProperty("place_hospital_export");
					break;
				case "14":
					 title = ConfigExportHelper.getProperty("place_farm_title");
			         exportjson = ConfigExportHelper.getProperty("place_farm_export");
					break;	
				case "15":
					 title = ConfigExportHelper.getProperty("place_other_title");
			         exportjson = ConfigExportHelper.getProperty("place_other_export");
					break;	
				case "16":
					 title = ConfigExportHelper.getProperty("place_town_title");
			         exportjson = ConfigExportHelper.getProperty("place_town_export");
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
	                    + new String(("导出-"+name +"信息" + date + ".xls").getBytes("Utf-8"),
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
	   * @param year
	   * @throws ParseException
	   */
	  @RequestMapping(value = "/exportTemplet")
	  @ResponseBody
	  public void exportTemplet( HttpServletRequest request, HttpServletResponse response,String typeId) throws ParseException{
		  PlaceType pt = placeTypeService.get(Long.parseLong(typeId));
		  KeyPlace kp = entityService.getFirstKeyPlaceByTypeId(Long.parseLong(typeId));
		  String name = pt.getName();
		  HSSFWorkbook wb = new HSSFWorkbook();//声明工
	        Sheet sheet = wb.createSheet("sheet1");//新建表
	        sheet.setDefaultColumnWidth((short) 25);//设置表宽
	        sheet.setColumnWidth(0, 11000);//设置第一例的宽度
	        if(typeId.equals("11")) {//医院
		        sheet.setColumnWidth(10, 11000);
	        }
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
	        switch (typeId) {
		        case "2":
					 title = ConfigExportHelper.getProperty("place_agricultural_title");
			         exportjson = ConfigExportHelper.getProperty("place_agricultural_export");
					break;
		        case "4":
					 title = ConfigExportHelper.getProperty("place_tour_title");
			         exportjson = ConfigExportHelper.getProperty("place_tour_export");
					break;
		        case "6":
					 title = ConfigExportHelper.getProperty("place_reservoir_title");
			         exportjson = ConfigExportHelper.getProperty("place_reservoir_export");
					break;
		        case "7":
					 title = ConfigExportHelper.getProperty("place_dyke_title");
			         exportjson = ConfigExportHelper.getProperty("place_dyke_export");
					break;
		        case "10":
					 title = ConfigExportHelper.getProperty("place_school_title");
			         exportjson = ConfigExportHelper.getProperty("place_school_export");
					break;
				case "11":
					 title = ConfigExportHelper.getProperty("place_hospital_title");
			         exportjson = ConfigExportHelper.getProperty("place_hospital_export");
					break;
				case "14":
					 title = ConfigExportHelper.getProperty("place_farm_title");
			         exportjson = ConfigExportHelper.getProperty("place_farm_export");
					break;	
				case "15":
					 title = ConfigExportHelper.getProperty("place_other_title");
			         exportjson = ConfigExportHelper.getProperty("place_other_export");
					break;
				case "16":
					 title = ConfigExportHelper.getProperty("place_town_title");
			         exportjson = ConfigExportHelper.getProperty("place_town_export");
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
	        if(kp != null) {
	        	row1 = sheet.createRow(1);
	        	row1.setHeight((short)300);//内容表高
		        for(int n=0;n<titles.length;n++){
		        	Cell cell1 =row1.createCell(n);
		        	cell1.setCellValue((String)Reflex.method(kp,(String)map.get(titles[n])));//从map中取出对应的属性再通过反射取值
		        	cell1.setCellStyle(style);
		        	
		        }
	        }
	        
	        
	        
	        response.reset();
	        response.setContentType("application/msexcel;charset=UTF-8");
	        try {
	            SimpleDateFormat newsdf=new SimpleDateFormat("yyyyMMddHHmmss");
	            String date = newsdf.format(new Date());
	            response.addHeader("Content-Disposition", "attachment;filename=\""
	                    + new String(("导出-"+name +"模板" + date + ".xls").getBytes("Utf-8"),
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
