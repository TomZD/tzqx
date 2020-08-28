package cn.movinginfo.tztf.sen.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
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
import cn.movinginfo.tztf.sen.domain.FacilityEquipment;
import cn.movinginfo.tztf.sen.mapper.FacilityEquipmentMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  FacilityEquipmentService extends BaseService<FacilityEquipment,FacilityEquipmentMapper> {

	@Autowired
	private EquipmentService equipmentService;
    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<FacilityEquipment> query(Map<String, String> paramMap) {
        Example example = new Example(FacilityEquipment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String code = paramMap.get("code");
        String equipmentId = paramMap.get("equipmentId");
        if (!StringUtils.isEmpty(code)) {
            criteria.andLike("code", "%" + code+ "%");
        }
        if (!StringUtils.isEmpty(equipmentId)) {
            criteria.andEqualTo("equipmentId",equipmentId);
        }
	    example.setOrderByClause("id desc");
	    List<FacilityEquipment> list = mapper.selectByExample(example);
	    return list;
    }
    
    public List<FacilityEquipment> getFacilityEquipmentByEquipmentId(Long equipmentId) {
    	Example example = new Example(FacilityEquipment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andEqualTo("equipmentId",equipmentId);
        return mapper.selectByExample(example);
    }
    
    public FacilityEquipment getFacilityEquipmentByCodeAndEquipmentId(String code,Long equipmentId) {
    	return mapper.getFacilityEquipmentByCodeAndEquipmentId(code, equipmentId);
    }
    
    public FacilityEquipment getFirstFacilityEquipmentByEquipmentId(Long equipmentId) {
    	return mapper.getFirstFacilityEquipmentByEquipmentId(equipmentId);
    }
    
    public void readXls(String root, String equipmentId) throws Exception {
		String equipment_export = "";
		if (!"".equals(equipmentId)) {
			switch (equipmentId) {
				case "4":
					equipment_export = ConfigExportHelper.getProperty("equipment_suona_lead");// 大喇叭
					break;
				case "9":
					equipment_export = ConfigExportHelper.getProperty("equipment_town_lead");// 室内显示屏
					break;	
				default:
					break;
			}

		}
		String equipment = "";
		InputStream is = new FileInputStream(root);
		Workbook workbook = WorkbookFactory.create(is);
		Sheet hssfSheet = workbook.getSheetAt(0);  //示意访问sheet

		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();

		for (int i = rowstart; i <= rowEnd; i++) {
			equipment = equipment_export;
			Row row = hssfSheet.getRow(i);
			if (null == row)
				continue;
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();

			for (int k = cellStart; k <= cellEnd; k++) {
				Cell cell = row.getCell(k);
				if (null == cell) {
					//System.out.print(k + "cell null");
					equipment = equipment.replaceAll("," + String.valueOf(k) + ",", "");
					continue;
				}
				if (i == rowstart) {// 第一行的所有字段
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						equipment_export = equipment_export.replace("," + cell.getStringCellValue() + ",",
								"," + String.valueOf(k) + ",");

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
					System.out.println(k);
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);//再转成String
						//System.out.println(k+":"+cell.toString());
						equipment = equipment.replaceAll("," + String.valueOf(k) + ",", cell.toString());
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						//System.out.println(k+":"+cell.getStringCellValue());
						equipment = equipment.replaceAll("," + String.valueOf(k) + ",", cell.getStringCellValue());
						break;
					default:
						break;
					}

				}
			}
			if (i > rowstart) {// 第二行开始
				System.out.println(equipment);
				FacilityEquipment fe = JSON.parseObject(equipment, FacilityEquipment.class);// json字符串转对象
				String lon = String.valueOf(getTwoDouble(4, Double.parseDouble(fe.getLongitude())));// 保留3位小数
				String lat = String.valueOf(getTwoDouble(4, Double.parseDouble(fe.getLatitude())));// 保留3位小数
				String phone = fe.getPhone();
				String code = fe.getCode();
				if (validatemobile(phone.trim()) == false) {// 判断手机号码
					System.out.println("-----------"+phone+"------------------");
					continue;
				}
				FacilityEquipment f= getFacilityEquipmentByCodeAndEquipmentId(code.trim(), Long.parseLong(equipmentId));//名字查重
				if(f != null) {
					fe.setId(f.getId());
				}
				fe.setLongitude(lon);
				fe.setLatitude(lat);
				fe.setEquipmentId(Long.parseLong(equipmentId));
				fe.setCity(fe.getCity().split("_")[0]);
				fe.setCounty(fe.getCounty().split("_")[0]);
				fe.setTown(fe.getTown().split("_")[0]);
				saveOrUpdate(fe);
			}

		}
	}

	// 保留小数后几位
	public double getTwoDouble(int x, double f) {
		BigDecimal bg = new BigDecimal(f);
		double f1 = bg.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}

	// 电话验证
	public boolean validatemobile(String phone) {
		String phoneRegex = "^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]) {1}|(18[0-9]{1}))+\\d{8})$";//手机号
		String seatRegex = "^[0][1-9]{2,3}-[0-9]{5,10}$";//带区号的
		String eightRegex = "^[1-9]\\d{7}$";//8位数电话
		Pattern phoneP = Pattern.compile(phoneRegex);
		boolean phoneIsTure = phoneP.matcher(phone).matches();
		Pattern pSeat = Pattern.compile(seatRegex);
		boolean seatIsTure = pSeat.matcher(phone).matches();
		Pattern eightP = Pattern.compile(eightRegex);
		boolean eightIsTure = eightP.matcher(phone).matches();
		if (phone.length() == 0) {
			return false;
		} else if (!(phoneIsTure || seatIsTure || eightIsTure)) {
			return false;
		}
		return true;
	}
    
}
