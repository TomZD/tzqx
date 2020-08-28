package cn.movinginfo.tztf.sys.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sys.domain.People;
import cn.movinginfo.tztf.sys.mapper.peopleMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

@Component
public class PeopleService extends BaseService<People,peopleMapper>{
    
	/**
	 * 列表页面获取数据库信息
	 */
	public List<People> query(Map<String, String> paramMap) {
        Example example = new Example(People.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String first_name = paramMap.get("first_name");
        String second_name = paramMap.get("second_name");
        String user_name = paramMap.get("user_name");
        String position = paramMap.get("position");
        String type = paramMap.get("type");
        String sector = paramMap.get("sector");
        String district_code = paramMap.get("district_code");
        String district_name = paramMap.get("district_name");
        String iphone = paramMap.get("iphone");
        String phone = paramMap.get("phone");
        String fax = paramMap.get("fax");
        String mailbox = paramMap.get("mailbox");
        String longitude = paramMap.get("longitude");
        String latitude = paramMap.get("latitude");
        String areaId = paramMap.get("areaId");
        String deptType = paramMap.get("deptType");
        String decision_id = paramMap.get("decision_id");
        if(!StringUtils.isEmpty(first_name)) {
        	criteria.andEqualTo("first_name",first_name);
        }
        if(!StringUtils.isEmpty(second_name)) {
        	criteria.andEqualTo("second_name",second_name);
        }
        if(!StringUtils.isEmpty(user_name)){
        	 criteria.andLike("user_name", "%" + user_name+ "%");
        }
        if(!StringUtils.isEmpty(position)) {
        	criteria.andEqualTo("position",position);
       
        }
        if(!StringUtils.isEmpty(type)){
       	 criteria.andEqualTo("type", type);
       }
        if(!StringUtils.isEmpty(sector)){
          	 criteria.andEqualTo("sector",sector);
          }
        if(!StringUtils.isEmpty(district_code)){
          	 criteria.andEqualTo("district_code", district_code);
          }
        if(!StringUtils.isEmpty(district_name)){
             	 criteria.andEqualTo("district_name",district_name);
             }
        if(!StringUtils.isEmpty(iphone)){
             	 criteria.andEqualTo("iphone", iphone);
             }
        if(!StringUtils.isEmpty(phone)){
                	 criteria.andEqualTo("phone",phone);
                }
        if(!StringUtils.isEmpty(fax)){
                	 criteria.andEqualTo("fax", fax);
                }
        if(!StringUtils.isEmpty(mailbox)){
                   	 criteria.andEqualTo("mailbox",mailbox);
                   }
        if(!StringUtils.isEmpty(longitude)){
                	 criteria.andEqualTo("longitude",longitude);
                }
        if(!StringUtils.isEmpty(latitude)){
                	 criteria.andEqualTo("latitude", latitude);
                }
        if(!StringUtils.isEmpty(areaId)) {
        	criteria.andEqualTo("areaId",areaId);
        }
        if(!StringUtils.isEmpty(deptType)) {
        	criteria.andEqualTo("deptType",deptType);
        }
        if(!StringUtils.isEmpty(decision_id)) {
        	criteria.andEqualTo("decision_id",decision_id);
        }
        return mapper.selectByExample(example);
    }
	
	public int deleByDid(Long decision_id) {
		Example example = new Example(People.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("decision_id",decision_id);
		return mapper.deleteByExample(example);
	}
	
	public People findPeopleByPhone(People people2, Long decision_id) {
		People people = new People();
		people.setValid(1);
		people.setPhone(people2.getPhone());
		people.setUser_name(people2.getUser_name());
		people.setDecision_id(String.valueOf(decision_id));
		return mapper.selectOne(people);
	}
	
	public List<People> findPeopleListByUserName(String user_name) {
		Example example = new Example(People.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("name",user_name);
		return mapper.selectByExample(example);
	}
	public List<People> findPeopleListByDecisionId(String decision_id) {
		Example example = new Example(People.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("decision_id",decision_id);
		return mapper.selectByExample(example);
	}
	 public List<People> findPeopleList() {
		Example example = new Example(People.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
	 }
	 //notid
	public People findPeopleByNotIdAndPhone(Long id,String phone) {
		return mapper.findPeopleByNotIdAndPhone(id, phone);
	}
	
	public List<People> readXls(String root) throws Exception {
        InputStream is = new FileInputStream(root);
        int index=0;
        HSSFWorkbook excel = new HSSFWorkbook(is);
        People people = null;
        List<People> list = new ArrayList<People>();
        
       
        
            HSSFSheet sheet = excel.getSheetAt(0);
           
            
            for (int rowNum = 3; rowNum < sheet.getLastRowNum()+1; rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                if(row != null) {
//                	people = new People();
//                	HSSFCell first_name = row.getCell(0);
//                	first_name.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	if(String.valueOf(first_name).equals("*一级组织机构")){
//                		index=1;
//                		continue;
//                	}
//                	if(index==0){
//                		continue;
//                	}
//                	people.setFirst_name(String.valueOf(first_name));
//                	
//                	
//                	HSSFCell second_name = row.getCell(1);
//                	second_name.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setSecond_name(String.valueOf(second_name));
//                	
//                	HSSFCell user_name = row.getCell(2);
//                	user_name.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setUser_name(String.valueOf(user_name));
//                	
//                	HSSFCell position = row.getCell(3);
//                	position.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setPosition(String.valueOf(position));
//                	
//                	HSSFCell type = row.getCell(4);
//                	type.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setType(String.valueOf(type));
//                	
//                	HSSFCell sector = row.getCell(5);
//                	sector.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setSector(String.valueOf(sector));
//                	
//                	HSSFCell district_code = row.getCell(6);
//                	district_code.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setDistrict_code(String.valueOf(district_code));
//                	
//                	HSSFCell district_name = row.getCell(7);
//                	district_name.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setDistrict_name(String.valueOf(district_name));
//                	
//                	HSSFCell iphone = row.getCell(8);
//                	iphone.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setIphone(String.valueOf(iphone));
//                	
//                	
//                	HSSFCell phone = row.getCell(9);
//                	if(phone!=null){
//                		phone.setCellType(HSSFCell.CELL_TYPE_STRING);
//                    	people.setPhone(String.valueOf(phone));
//                	}
//                	
//                	
//                	HSSFCell fax = row.getCell(10);
//                	if(fax!=null){
//                		fax.setCellType(HSSFCell.CELL_TYPE_STRING);
//                    	people.setFax(String.valueOf(fax));
//                	}
//                	
//                	
//                	HSSFCell mailbox = row.getCell(11);
//                	if(mailbox!=null){
//                	mailbox.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setMailbox(String.valueOf(mailbox));
//                	}
//                	
//                	HSSFCell longitude = row.getCell(12);
//                	if(longitude!=null){
//                	longitude.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setLongitude(String.valueOf(longitude));
//                	}
//                	
//                	HSSFCell latitude = row.getCell(13);
//                	if(latitude!=null){
//                	latitude.setCellType(HSSFCell.CELL_TYPE_STRING);
//                	people.setLatitude(String.valueOf(latitude));
//                	}
                	//姓名
                	people = new People();
                	HSSFCell userName = row.getCell(0);
                	userName.setCellType(HSSFCell.CELL_TYPE_STRING);
                	if(String.valueOf(userName)==null||String.valueOf(userName).length()==0) {
                		continue;
                	}
                	people.setUser_name(String.valueOf(userName));
                	//手机号
                	HSSFCell iphone = row.getCell(1);
                	iphone.setCellType(HSSFCell.CELL_TYPE_STRING);
                	people.setIphone(String.valueOf(iphone));
                	//电话号
                	HSSFCell phone = row.getCell(3);
                	phone.setCellType(HSSFCell.CELL_TYPE_STRING);
                	people.setPhone(String.valueOf(phone));
                	//所属部门
                	HSSFCell sector = row.getCell(6);
                	sector.setCellType(HSSFCell.CELL_TYPE_STRING);
                	people.setPhone(String.valueOf(sector));
                	//所属职务
                	HSSFCell position = row.getCell(8);
                	position.setCellType(HSSFCell.CELL_TYPE_STRING);
                	people.setPhone(String.valueOf(position));
                }
                list.add(people);
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
		String regex = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$";
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
//    /**
//     * 插入或更新对象
//     * @param o
//     * @return
//     */
//    public Long saveOrUpdate(People p) {
//    	Example example=new Example(People.class);
//    	example.createCriteria().andEqualTo("iphone", p.getIphone()).andEqualTo("decision_id",p.getDecision_id());
//    	List<People> pList=mapper.selectByExample(example);
//        if (pList.size()==0) {
//        	p.setCreateDate(new Date());
//            mapper.insertSelective(p);
//        } else {
//        	p.setUpdateDate(new Date());
//            mapper.updateByExampleSelective(p, example);
//        }
//        return p.getId();
//    }
	
    public List<People> getAllPeople() {
    	Example example = new Example(People.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
    }
}
