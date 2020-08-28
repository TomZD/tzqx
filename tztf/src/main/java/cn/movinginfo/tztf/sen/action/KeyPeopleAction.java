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
import org.springframework.util.StringUtils;
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
import cn.movinginfo.tztf.sen.domain.KeyPeople;
import cn.movinginfo.tztf.sen.domain.PersonType;
import cn.movinginfo.tztf.sen.service.KeyPeopleService;
import cn.movinginfo.tztf.sen.service.PersonTypeService;
import cn.movinginfo.tztf.sys.domain.UserXt;
import cn.movinginfo.tztf.sys.service.AreaService;
import cn.movinginfo.tztf.sys.service.UserXtService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;



/**
*
* @description:KeyPeople action
* @author: autoCode
* @history:
*/
@Controller
@RequestMapping("/yzt/keyPeople")
@SuppressWarnings("serial")
public class KeyPeopleAction extends MagicAction<KeyPeople,KeyPeopleService> {
	
	@Autowired
	private PersonTypeService personTypeService;

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private UserXtService userXtService;
	
	/**
	 * 人员管理页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("keyPeopleQuery")
	public String indexKeyPeopleQuery(HttpServletRequest request, Model model,String personTypeId){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("personTypeId", personTypeId);
		return "/yzt/keyPeople/keyPeopleQuery";
	}
	
	/**
	 * 获取列表数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/keyPeopleData")
	public String keyPeopleData(HttpServletRequest request, Model model,String personTypeId)
			throws Exception {
		UserXt user = (UserXt)request.getSession().getAttribute("yztUser");
		UserXt u = userXtService.get(user.getId());
		Long townId = u.getTownId();
		String town = "";
		if(townId != null) {
			town = areaService.get(townId).getTown();
		}
		Map<String, String> map = new HashMap<String, String>();
		map=getParameterMap(request);
		map.put("personTypeId", personTypeId);
		map.put("town", town);
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter())); 
		model.addAttribute("page", page);
		String peopleCh = "";
		String peopleEn = "";
		switch(personTypeId) {
			case "5" ://气象信息员
				peopleCh = ConfigExportHelper.getProperty("people_meteorological_ch");
				peopleEn = ConfigExportHelper.getProperty("people_meteorological_en");
				break;
			case "7" ://救援专家
				peopleCh = ConfigExportHelper.getProperty("people_rescue_ch");
				peopleEn = ConfigExportHelper.getProperty("people_rescue_en");
				break;
			case "8" ://气象灾害责任人
				peopleCh = ConfigExportHelper.getProperty("people_danger_ch");
				peopleEn = ConfigExportHelper.getProperty("people_danger_en");
				break;
			case "9" ://网络责任人
				peopleCh = ConfigExportHelper.getProperty("people_network_ch");
				peopleEn = ConfigExportHelper.getProperty("people_network_en");
				break;	
			default :
				break;
		}
		List<ChineseEnglish> ceList = new ArrayList<ChineseEnglish>();
		List<String> peopleChList = Arrays.asList(peopleCh.split(","));
		List<String> peopleEnList = Arrays.asList(peopleEn.split(","));
		for(int x = 0;x<peopleEnList.size();x++) {
			ChineseEnglish ce = new ChineseEnglish();
			ce.setChinese(peopleChList.get(x));
			ce.setEnglish(peopleEnList.get(x));
			ceList.add(ce);
		}
		model.addAttribute("peopleChList", peopleChList);
		model.addAttribute("peopleEnList", peopleEnList);
		model.addAttribute("ceList", ceList);
		model.addAttribute("personTypeId", personTypeId);
		 return getNameSpace() + "keyPeopleData";
	}
	
	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editKeyPeople")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		KeyPeople keyPeople = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(keyPeople));
	}
	
	
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteKeyPeople")
	public String delete(HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		Long personTypeId = entityService.get(Long.parseLong(id)).getPersonTypeId();
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
		return "redirect:/yzt/keyPeople/keyPeopleQuery?personTypeId="+personTypeId;
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
		KeyPeople keyPeople = bindEntity(request, entityClass);
		String phone = keyPeople.getPhone();
		Long personTypeId = keyPeople.getPersonTypeId();
		KeyPeople kp = entityService.getKeyPeopleByPhoneAndType(personTypeId, phone);
		if(keyPeople.getId() !=null) {
			if(kp != null &&!kp.getId().equals(keyPeople.getId())) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该电话号码已存在！"));
				return;
			}
		}else {
			if(kp != null) {
				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该电话号码已存在！"));
				return;
			}
		}
		
		
		keyPeople.setProvince("浙江省");
		keyPeople.setCity("台州市");
		keyPeople.setCounty("通州区");
		entityService.saveOrUpdate(keyPeople);
	}
	
	
	/**
     * 导入数据
     * @param file
     * @param request
     * @throws Exception
     */
	@RequestMapping(value = "import")
	@ResponseBody
	public void uploadPage(@RequestParam("file")  CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response,String personTypeId)
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
		entityService.readXls(root,personTypeId);
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
	  public void exportData( HttpServletRequest request, HttpServletResponse response,String personTypeId) throws ParseException{
		  List<KeyPeople> list = entityService.getKeyPeopleByPersonId(Long.parseLong(personTypeId));
		  PersonType pt = personTypeService.get(Long.parseLong(personTypeId));
		  String name = pt.getName();
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
	        switch (personTypeId) {
				case "5":
					 title = ConfigExportHelper.getProperty("people_meteorological_title");
			         exportjson = ConfigExportHelper.getProperty("people_meteorological_export");
					break;
				case "7":
					 title = ConfigExportHelper.getProperty("people_rescue_title");
			         exportjson = ConfigExportHelper.getProperty("people_rescue_export");
			         break;
				case "8":
					 title = ConfigExportHelper.getProperty("people_danger_title");
			         exportjson = ConfigExportHelper.getProperty("people_danger_export");
			         break;
				case "9" :
					 title = ConfigExportHelper.getProperty("people_network_title");
			         exportjson = ConfigExportHelper.getProperty("people_network_export");
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
	  public void exportTemplet( HttpServletRequest request, HttpServletResponse response,String personTypeId) throws ParseException{
		  PersonType pt = personTypeService.get(Long.parseLong(personTypeId));
		  KeyPeople kp = entityService.getFirstKeyPeopleByType(Long.parseLong(personTypeId));
		  String name = pt.getName();
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
	        switch (personTypeId) {
				case "5":
					 title = ConfigExportHelper.getProperty("people_meteorological_title");
			         exportjson = ConfigExportHelper.getProperty("people_meteorological_export");
					break;
				case "7":
					 title = ConfigExportHelper.getProperty("people_rescue_title");
			         exportjson = ConfigExportHelper.getProperty("people_rescue_export");
			         break;
				case "8":
					 title = ConfigExportHelper.getProperty("people_danger_title");
			         exportjson = ConfigExportHelper.getProperty("people_danger_export");
			         break;
				case "9" :
					 title = ConfigExportHelper.getProperty("people_network_title");
			         exportjson = ConfigExportHelper.getProperty("people_network_export");
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
