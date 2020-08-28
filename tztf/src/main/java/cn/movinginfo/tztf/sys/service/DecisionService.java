package cn.movinginfo.tztf.sys.service;

import net.ryian.orm.service.BaseService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sys.domain.Decision;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.mapper.DecisionMapper;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class DecisionService extends BaseService<Decision,DecisionMapper>{
    @Autowired
    private DeptService deptService;
	/**
	 * 列表页面获取数据库信息
	 */
	public List<Decision> query(Map<String, String> paramMap) {
        Example example = new Example(Decision.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String name = paramMap.get("name");
        String department = paramMap.get("department");
        String phone = paramMap.get("phone");
        String areaId = paramMap.get("areaId");
        String deptType = paramMap.get("deptType");
        String userId = paramMap.get("userId");
        String deptId = paramMap.get("deptId");
        if(!StringUtils.isEmpty(areaId)) {
        	criteria.andEqualTo("areaId",areaId);
        }
        if(!StringUtils.isEmpty(deptType)) {
        	criteria.andEqualTo("deptType",deptType);
        }
        if(!StringUtils.isEmpty(userId)) {
        	criteria.andEqualTo("userId",userId);
        }
        if(!StringUtils.isEmpty(name)){
        	 criteria.andLike("name", "%" + name+ "%");
        }
        if(!StringUtils.isEmpty(department)){
       	 criteria.andEqualTo("department", department);
       }
        if(!StringUtils.isEmpty(phone)){
          	 criteria.andEqualTo("phone",phone);
          }
        if(!StringUtils.isEmpty(deptId)){
         	 criteria.andEqualTo("deptId",deptId);
         }
        List<Decision> list = mapper.selectByExample(example);
        for(Decision de : list) {
        	String departId = de.getDeptId();
        	Depart dept = deptService.get(Long.parseLong(departId));
        	if(dept != null) {
        		de.setDeptName(dept.getName());
        	}
        }
        return list;
    }
	
	public Decision findDecisionByPhone(String phone) {
		Decision decision = new Decision();
		decision.setValid(1);
		decision.setPhone(phone);
		return mapper.selectOne(decision);
	}
	
	public List<Decision> findDecisionListByName(String name) {
		Example example = new Example(Decision.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("name",name);
		return mapper.selectByExample(example);
	}
	 public List<Decision> findDecisionList(Long deptId) {
		Example example = new Example(Decision.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("deptId",deptId);
		return mapper.selectByExample(example);
	 }
	public Decision findDecisionByNotIdAndPhone(Long id,String phone) {
		return mapper.findDecisionByNotIdAndPhone(id, phone);
	}
	
	public List<Decision> readXls(String root) throws Exception {
        InputStream is = new FileInputStream(root);

        HSSFWorkbook excel = new HSSFWorkbook(is);
        Decision de = null;
        List<Decision> list = new ArrayList<Decision>();
        
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
            HSSFSheet sheet = excel.getSheetAt(numSheet);
            if (sheet == null)
                continue;
            // 循环行Row
            for (int rowNum = 1; rowNum < sheet.getLastRowNum()+1; rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                if(row != null) {
                	de = new Decision();
                	
                	HSSFCell name = row.getCell(0);
                	name.setCellType(HSSFCell.CELL_TYPE_STRING);
                	de.setName(String.valueOf(name));
                	
                	HSSFCell depart = row.getCell(1);
                	depart.setCellType(HSSFCell.CELL_TYPE_STRING);
                	de.setDepartment(String.valueOf(depart));
                	
                	HSSFCell phone = row.getCell(2);
                	phone.setCellType(HSSFCell.CELL_TYPE_STRING);
                	de.setPhone(String.valueOf(phone));
                }
                list.add(de);
            }
        }

        return list;
    }
	
	public boolean deleteExcel(String fileDir){
    	boolean flag = false;
    	File file = new File(fileDir);
    	// 推断文件夹或文件是否存在  
        if (!file.exists()) {  // 不存在返回 false  
            return flag;  
        } else {  
            // 推断是否为文件  
            if (file.isFile()) {  // 为文件时调用删除文件方法  
                file.delete();
                flag = true;
            } 
        }
        return flag;
    }
	
	public boolean validatemobile(String phone) {
		String regex = "^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1})|(19[0-9]{1}))+\\d{8})$";
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
	
    public List<Decision> getAllDecision() {
    	Example example = new Example(Decision.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
    }
}
