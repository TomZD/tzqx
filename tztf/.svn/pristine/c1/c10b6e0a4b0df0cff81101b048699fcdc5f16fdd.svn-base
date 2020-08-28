package cn.movinginfo.tztf.sen.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.sen.domain.KeyPeople;
import cn.movinginfo.tztf.sen.domain.PersonType;
import cn.movinginfo.tztf.sen.mapper.KeyPeopleMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  KeyPeopleService extends BaseService<KeyPeople,KeyPeopleMapper> {
	
	@Autowired
	private PersonTypeService personTypeService;
	
	@Autowired
	private DefenseDepartmentService defenseDepartmentService;
    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<KeyPeople> query(Map<String, String> paramMap) {
        Example example = new Example(KeyPeople.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String name = paramMap.get("name");
        String town = paramMap.get("town");
        String personTypeId=paramMap.get("personTypeId");
        String typeIds = paramMap.get("typeIds");
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name+ "%");
        }
        if (!StringUtils.isEmpty(personTypeId)) {
            criteria.andEqualTo("personTypeId",personTypeId);
        }
        if(!StringUtils.isEmpty(town)) {
        	criteria.andEqualTo("town",town);
        }
        if(!StringUtils.isEmpty(typeIds)) {
        	String[] types = typeIds.split(",");
        	List list=Arrays.asList(types);
        	criteria.andIn("personTypeId", list);
        }
	    example.setOrderByClause("id desc");
	    List<KeyPeople> list = mapper.selectByExample(example);
	    for(KeyPeople kp : list) {
	    	Long peopleTypeId = kp.getPersonTypeId();
	    	PersonType person = personTypeService.get(peopleTypeId);
	    	kp.setPersonType(person.getName());
//	    	Long departmentId = kp.getDepartmentId();
//	    	DefenseDepartment defenseDepartment = defenseDepartmentService.get(departmentId);
//	    	kp.setDepartmentName(defenseDepartment.getName());
	    }
	    return list;
    }
	public KeyPeople getQueryPerson(Long id){
    	return mapper.getQueryPerson(id);
	}
    
    public KeyPeople findKeyPeopleByPhoneAndType(String phone,String personTypeId) {
    	KeyPeople kp = new KeyPeople();
    	kp.setPhone(phone);
    	kp.setPersonTypeId(Long.parseLong(personTypeId));
    	kp.setValid(1);
    	return mapper.selectOne(kp);
    }
    
    
    public KeyPeople getKeyPeopleByPhoneAndType(Long personTypeId,String phone) {
    	return mapper.getKeyPeopleByPhoneAndType(personTypeId, phone);
    }
    
    public KeyPeople getKeyPeopleByNameAndType(Long personTypeId,String name) {
    	return mapper.getKeyPeopleByNameAndType(personTypeId, name);
    }
    
    public KeyPeople getFirstKeyPeopleByType(Long personTypeId) {
    	return mapper.getFirstKeyPeopleByTypeId(personTypeId);
    }
    
    public List<KeyPeople> getKeyPeopleByPersonId(Long personId) {
    	Example example = new Example(KeyPeople.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andEqualTo("personTypeId",personId);
        return mapper.selectByExample(example);
    }
    

    public void readXls(String root, String personTypeId) throws Exception {
		String people_export="";
		if(!"".equals(personTypeId)) {
			switch(personTypeId) {
				case "5" :
					people_export = ConfigExportHelper.getProperty("people_meteorological_lead");//气象信息员
					break;
				case "7" :
					people_export = ConfigExportHelper.getProperty("people_rescue_lead");//救援专家
					break;
				case "8" :
					people_export = ConfigExportHelper.getProperty("people_danger_lead");//气象灾害责任人
					break;
				case "9" :
					people_export = ConfigExportHelper.getProperty("people_network_lead");//网络责任人
					break;	
				default : 
					break;
			}
				
		}
		String keyPeople = "";
		InputStream is = new FileInputStream(root);
		Workbook workbook = WorkbookFactory.create(is);
		Sheet hssfSheet = workbook.getSheetAt(0);  //示意访问sheet

		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();

		for (int i = rowstart; i <= rowEnd; i++) {
			keyPeople = people_export;
			Row row = hssfSheet.getRow(i);
			if (null == row)
				continue;
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();

			for (int k = cellStart; k <= cellEnd; k++) {
				Cell cell = row.getCell(k);
				if (null == cell) {
					System.out.print(k + "cell null");
					keyPeople = keyPeople.replaceAll("," + String.valueOf(k) + ",", "");
					continue;
				}
				if (i == rowstart) {// 第一行的所有字段
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						people_export = people_export.replace("," + cell.getStringCellValue() + ",","," + String.valueOf(k) + ",");

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
						keyPeople = keyPeople.replaceAll("," + String.valueOf(k) + ",", cell.toString());
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						keyPeople = keyPeople.replaceAll("," + String.valueOf(k) + ",", cell.getStringCellValue());
						break;
					default:
						break;
					}

				}
			}
			if (i > rowstart) {// 第二行开始
				System.out.println(keyPeople);
				KeyPeople kp = JSON.parseObject(keyPeople, KeyPeople.class);// json字符串转对象
				String lon = String.valueOf(getTwoDouble(4, Double.parseDouble(kp.getLongitude())));//保留3位小数
				String lat = String.valueOf(getTwoDouble(4, Double.parseDouble(kp.getLatitude())));//保留3位小数
				String phone = kp.getPhone();
				if(validatemobile(phone.trim()) ==false) {//判断手机号码
					continue;
				}
				KeyPeople people = findKeyPeopleByPhoneAndType(phone.trim(),personTypeId);
				if (people != null) {// 通过电话判断是否存在
					kp.setId(people.getId());;
				}
				kp.setLongitude(lon);;
				kp.setLatitude(lat);
				kp.setPersonTypeId(Long.parseLong(personTypeId));
				saveOrUpdate(kp);
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
// 			String regex = "^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]) {1}|(18[0-9]{1}))+\\\\d{8})$";
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
