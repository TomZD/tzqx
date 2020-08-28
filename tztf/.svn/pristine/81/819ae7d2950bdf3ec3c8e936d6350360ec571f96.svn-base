package cn.movinginfo.tztf.sys.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.sys.domain.Decision;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.People;
import cn.movinginfo.tztf.sys.domain.Tffk;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.service.DecisionService;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.PeopleService;
import cn.movinginfo.tztf.sys.utils.json.DictFilter;
import net.ryian.commons.StringUtils;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/sys/people")
public class PeopleAction extends MagicAction<People, PeopleService>{
     
	@Autowired
	private DecisionService decisionService;
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("peopleQuery")
	public String indexMenu(HttpServletRequest request, Model model, String id){
		System.out.println(id);
		model.addAttribute("descId",id);
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		return "/sys/people/peopleQuery";
	}
	
	/**
	 * 获取列表数据
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("peopleData")
	public String peopleData(HttpServletRequest request, Model model,String id){
		model.addAttribute("currentPage", 1);
		model.addAttribute("pageSize", 10);
		model.addAttribute("descId", id);
		System.out.println(id);
		Decision decision = decisionService.get(Long.parseLong(id));
//		String areaId = decision.getAreaId();
//		String deptType =decision.getDeptType();
		Long decision_id = decision.getId();
		Map<String,String> map = new HashMap<String,String>();
//		if(areaId.equals("1")) {
//			if(deptType.equals("2")) {
//			map = getParameterMap(request);
//			}else {
				map = getParameterMap(request);
				map.put("decision_id", String.valueOf(decision_id));
//			}
//		}else {
//			if(deptType.equals("2")) {
//			map = getParameterMap(request);
//			map.put("areaId", areaId);
//			}else {
//				map = getParameterMap(request);
//				map.put("decision_id", String.valueOf(decision_id));
//			}
//		}
//		
				
			
		PageInfo<?> pageInfo = entityService.queryPaged(map);
		JSONObject page = (JSONObject) JSONObject.parse(JSONObject
				.toJSONString(pageInfo, new DictFilter())); 
		model.addAttribute("page", page);
		return getNameSpace() + "peopleData";
	}
	


	

	/**
	 * 新增修改保存
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveOrUpdate")
	public void suerEdit(HttpServletRequest request, HttpServletResponse response, Model model,String id) throws Exception {
		People people = bindEntity(request, entityClass);
		
//		Decision decision = decisionService.get(Long.parseLong(id));
//		
////		String areaId = decision.getAreaId();
////		String deptType = decision.getDeptType();
//		Long decision_id = decision.getId();
////		people.setAreaId(areaId);
////		people.setDeptType(deptType);
//	    people.setDecision_id(String.valueOf(decision_id));
		Long id1 = people.getId();
//		String phone = decision.getPhone();
		if(id1 != null) {
//			Decision dec = entityService.findDecisionByNotIdAndPhone(id, phone);
//			if(dec != null) {
//				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该电话号码已存在！"));
//				return;
//			}
			people.setUpdateDate(new Date());
//			people.setUpdator(Long.parseLong(id));
		}else {
//			Decision de = entityService.findDecisionByPhone(phone);
//			if(de != null) {
//				printJson(response, com.alibaba.fastjson.JSON.toJSONString("该电话号码已存在！"));
//				return;
//			}
			people.setCreateDate(new Date());
//			people.setCreator(Long.parseLong(id));
		}
		entityService.saveOrUpdate(people);
	}
	/**
	 * 删除
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePeople")
	public String deletePeople(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws Exception {
		String id = request.getParameter("id");
		String did=entityService.get(Long.parseLong(id)).getDecision_id();
		if (!StringUtils.isEmpty(id)) {
			entityService.logicRemove(Long.parseLong(id));
		}
//		redirectAttributes.addFlashAttribute("id", did);
		return "redirect:/sys/people/peopleQuery?id="+did;
	}
	
	/**
	 * 向编辑框传值
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "editPeople")
	public void editPeople(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		People people = entityService.get(Long.parseLong(id));
		printJson(response, com.alibaba.fastjson.JSON.toJSONString(people));
	}
	

    /**
     * 导入数据
     * @param file
     * @param request
     * @throws Exception
     */
	@RequestMapping(value = "importPeople")
	@ResponseBody
	public void uploadPage(@RequestParam("file")  CommonsMultipartFile file, HttpServletRequest request,HttpServletResponse response,String descId)
			throws Exception {
		System.out.println(descId);
		Decision decision = decisionService.get(Long.parseLong(descId));
		String areaId = decision.getAreaId();
		String deptType = decision.getDeptType();
		Long decision_id = decision.getId();
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
		List<People> list = entityService.readXls(root);
		List<String> li = new ArrayList<String>();
		   for(int x=0;x<list.size();x++) {
			   String iphone = list.get(x).getIphone();
			   boolean isPhone = entityService.validatemobile(iphone);
//			   People oldDe = entityService.findPeopleByPhone(list.get(x),decision_id);
			   if(isPhone != true) {
				   li.add("第"+ (x+1) +"条数据导入失败");
			   }else {
				   People people = new People();
				   people.setFirst_name(list.get(x).getFirst_name());
				   people.setSecond_name(list.get(x).getSecond_name());
				   people.setUser_name(list.get(x).getUser_name());
				   people.setPosition(list.get(x).getPosition());
				   people.setType(list.get(x).getType());
				   people.setSector(list.get(x).getSector());
				   people.setDistrict_code(list.get(x).getDistrict_code());
				   people.setDistrict_name(list.get(x).getDistrict_name());
				   people.setIphone(list.get(x).getIphone());
				   people.setPhone(list.get(x).getPhone());
				   people.setFax(list.get(x).getFax());
				   people.setMailbox(list.get(x).getMailbox());
				   people.setLongitude(list.get(x).getLongitude());
				   people.setLatitude(list.get(x).getLatitude());
				   people.setAreaId(areaId);
				   people.setDeptType(deptType);
				   people.setDecision_id(String.valueOf(decision_id));
				   entityService.saveOrUpdate(people); 
			   }
		   }
		   entityService.deleteExcel(root);
		   printJson(response, com.alibaba.fastjson.JSON.toJSONString(li));
	}
	@RequestMapping(value = "/exportPeople")
    @ResponseBody
    public void exportPeople(HttpServletRequest request, HttpServletResponse response,String descId) throws ParseException {
        List<People> list = new ArrayList<>();
		Decision decision = decisionService.get(Long.parseLong(descId));
        list = entityService.findPeopleListByDecisionId(descId);

        InputStream in = this.getClass().getResourceAsStream("/templates/excel/peopleTemplate.xls");
        HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Sheet sheet=wb.getSheet("Sheet1");
        for (int i = 0; i < list.size(); i++) {
        	//从第四行开始
        	Row row1 = sheet.getRow(i+3);
            row1.getCell(0).setCellValue(list.get(i).getUser_name());
            row1.getCell(1).setCellValue(list.get(i).getIphone());
            row1.getCell(3).setCellValue(list.get(i).getPhone());
            row1.getCell(5).setCellValue(list.get(i).getSector());
            row1.getCell(7).setCellValue(list.get(i).getPosition());
        }


        response.reset();
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + new String((decision.getName()+".xls").getBytes("Utf-8"),
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
	 * 获取所有的电话号码
	 * @return
	 */
	@RequestMapping(value = "allPhones")
	@ResponseBody
	public List<String> getAllPhones() {
		List<People> person = entityService.getAllPeople();
		List<String> phones = new ArrayList<String>();
		for(People de :person) {
			phones.add(de.getPhone());
		}
		return phones;
	}
	

}
