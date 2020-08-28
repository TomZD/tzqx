package cn.movinginfo.tztf.sen.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.common.utils.Reflex;
import cn.movinginfo.tztf.sen.domain.ChineseEnglish;
import cn.movinginfo.tztf.sen.domain.Equipment;
import cn.movinginfo.tztf.sen.domain.FacilityEquipment;
import cn.movinginfo.tztf.sen.domain.KeyPlace;
import cn.movinginfo.tztf.sen.domain.PlaceType;
import cn.movinginfo.tztf.sen.service.EquipmentService;
import cn.movinginfo.tztf.sen.service.FacilityEquipmentService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;

/**
*
* @description:FacilityEquipment action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/yzt/facilityEquipment")
@SuppressWarnings("serial")
public class FacilityEquipmentAction extends MagicAction<FacilityEquipment,FacilityEquipmentService> {
	
	@Autowired
	private EquipmentService equipmentService;
	
	
	/**
	 * 设施设备管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("facilityEquipmentQuery")
	public String indexFacilityEquipmentQuery(HttpServletRequest request, Model model,String equipmentId){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("equipmentId",equipmentId);
		return "/yzt/facilityEquipment/facilityEquipmentQuery";
	}
	
	/**
	 * 获取列表数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/facilityEquipmentData")
	public String facilityEquipmentData(HttpServletRequest request, Model model,String equipmentId)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map=getParameterMap(request);
		map.put("equipmentId", equipmentId);
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter())); 
		model.addAttribute("page", page);
		String equipmentCh = "";
		String equipmentEn = "";
		switch(equipmentId) {
			case "4" ://大喇叭
				equipmentCh = ConfigExportHelper.getProperty("equipment_suona_ch");
				equipmentEn = ConfigExportHelper.getProperty("equipment_suona_en");
				break;
			case "9" ://乡镇显示屏
				equipmentCh = ConfigExportHelper.getProperty("equipment_town_ch");
				equipmentEn = ConfigExportHelper.getProperty("equipment_town_en");
				break;
			default :
				break;
		}
		List<ChineseEnglish> ceList = new ArrayList<ChineseEnglish>();
		List<String> equipmentChList = Arrays.asList(equipmentCh.split(","));
		List<String> equipmentEnList = Arrays.asList(equipmentEn.split(","));
		for(int x = 0;x<equipmentEnList.size();x++) {
			ChineseEnglish ce = new ChineseEnglish();
			ce.setChinese(equipmentChList.get(x));
			ce.setEnglish(equipmentEnList.get(x));
			ceList.add(ce);
		}
		model.addAttribute("equipmentChList", equipmentChList);
		model.addAttribute("equipmentEnList", equipmentEnList);
		model.addAttribute("ceList", ceList);
		model.addAttribute("equipmentId", equipmentId);
		return getNameSpace() + "facilityEquipmentData";
	}
	
	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editKeyFacilityEquipment")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		FacilityEquipment facilityEquipment = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(facilityEquipment));
	}
	
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletefacilityEquipment")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		Long equipmentId = entityService.get(Long.parseLong(id)).getEquipmentId();
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/yzt/facilityEquipment/facilityEquipmentQuery?equipmentId="+equipmentId;
	}

	
	/**
	 * 新增保存和修改
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		FacilityEquipment facilityEquipment = bindEntity(request, entityClass);
		Long equipmentId = facilityEquipment.getEquipmentId();
		String code = facilityEquipment.getCode();
		FacilityEquipment fe = entityService.getFacilityEquipmentByCodeAndEquipmentId(code, equipmentId);
		if(facilityEquipment.getId() != null) {
			if(fe != null && !fe.getId().equals(facilityEquipment.getId())) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该终端编号已存在！"));
				return;
				}
		}else {
			if(fe != null) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该终端编号已存在！"));
				return;
			}
		}
		facilityEquipment.setCity("台州市");
		facilityEquipment.setCounty("黄岩区");
		entityService.saveOrUpdate(facilityEquipment);
	}
	
	
	/**
     * 导入数据
     * @param file
     * @param request
     * @throws Exception
     */
	@RequestMapping(value = "import")
	@ResponseBody
	public void uploadPage(@RequestParam("file")  CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response,String equipmentId)
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
		entityService.readXls(root,equipmentId);
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
	  public void exportData( HttpServletRequest request, HttpServletResponse response,String equipmentId) throws ParseException{
		  	List<FacilityEquipment> list = entityService.getFacilityEquipmentByEquipmentId(Long.parseLong(equipmentId));
			Equipment equipment = equipmentService.get(Long.parseLong(equipmentId));
			String name = equipment.getName();
			HSSFWorkbook wb = new HSSFWorkbook();//声明工
	        Sheet sheet = wb.createSheet("sheet1");//新建表
	        sheet.setDefaultColumnWidth((short) 25);//设置表宽
	        sheet.setColumnWidth(0, 11000);//设置第一例的宽度
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
	        switch (equipmentId) {
		        case "4":
					 title = ConfigExportHelper.getProperty("equipment_suona_title");
			         exportjson = ConfigExportHelper.getProperty("equipment_suona_export");
					break;
		        case "9":
					 title = ConfigExportHelper.getProperty("equipment_town_title");
			         exportjson = ConfigExportHelper.getProperty("equipment_town_export");
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
	  public void exportTemplet( HttpServletRequest request, HttpServletResponse response,String equipmentId) throws ParseException{
			Equipment equipment = equipmentService.get(Long.parseLong(equipmentId));
			FacilityEquipment fe = entityService.getFirstFacilityEquipmentByEquipmentId(Long.parseLong(equipmentId));
			String name = equipment.getName();
			HSSFWorkbook wb = new HSSFWorkbook();//声明工
	        Sheet sheet = wb.createSheet("sheet1");//新建表
	        sheet.setDefaultColumnWidth((short) 25);//设置表宽
	        sheet.setColumnWidth(0, 11000);//设置第一例的宽度
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
	        switch (equipmentId) {
		        case "4":
					 title = ConfigExportHelper.getProperty("equipment_suona_title");
			         exportjson = ConfigExportHelper.getProperty("equipment_suona_export");
					break;
		        case "9":
					 title = ConfigExportHelper.getProperty("equipment_town_title");
			         exportjson = ConfigExportHelper.getProperty("equipment_town_export");
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
	        if(fe != null) {
	        	row1 = sheet.createRow(1);
	        	row1.setHeight((short)300);//内容表高
		        for(int n=0;n<titles.length;n++){
		        	Cell cell1 =row1.createCell(n);
		        	cell1.setCellValue((String)Reflex.method(fe,(String)map.get(titles[n])));//从map中取出对应的属性再通过反射取值
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
