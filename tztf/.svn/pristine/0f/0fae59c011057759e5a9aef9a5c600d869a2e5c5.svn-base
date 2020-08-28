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
import cn.movinginfo.tztf.sen.domain.KeyPlace;
import cn.movinginfo.tztf.sen.mapper.KeyPlaceMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class KeyPlaceService extends BaseService<KeyPlace, KeyPlaceMapper> {

	@Autowired
	private PlaceTypeService placeTypeService;

	@Resource
	private KeyPlaceMapper keyPlaceMapper;

	/**
	 * 根据条件查询分页
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<KeyPlace> query(Map<String, String> paramMap) {
		Example example = new Example(KeyPlace.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		String name = paramMap.get("name");
		String typeId = paramMap.get("typeId");
		if (!StringUtils.isEmpty(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		if (!StringUtils.isEmpty(typeId)) {
			criteria.andEqualTo("typeId", typeId);
		}
		example.setOrderByClause("id desc");
		List<KeyPlace> list = mapper.selectByExample(example);
		return list;
	}

	public List<KeyPlace> getKeyPlaceByTypeId(Long typeId) {
		Example example = new Example(KeyPlace.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		criteria.andEqualTo("typeId", typeId);
		return mapper.selectByExample(example);
	}
	
	public KeyPlace getFirstKeyPlaceByTypeId(Long typeId) {
		return mapper.getFirstKeyPlaceByTypeId(typeId);
	}
	
	public KeyPlace getKeyPlaceByNameAndTypeId(String name,Long typeId) {
		return mapper.getKeyPlaceByNameAndTypeId(name, typeId);
	}

	public void readXls(String root, String typeId) throws Exception {
		String place_export = "";
		if (!"".equals(typeId)) {
			switch (typeId) {
				case "2":
					place_export = ConfigExportHelper.getProperty("place_agricultural_lead");// 农业园区
					break;
				case "4":
					place_export = ConfigExportHelper.getProperty("place_tour_lead");// 旅游景点
					break;
				case "6":
					place_export = ConfigExportHelper.getProperty("place_reservoir_lead");// 山塘水库
					break;
				case "7":
					place_export = ConfigExportHelper.getProperty("place_dyke_lead");// 堤坝
					break;
				case "10":
					place_export = ConfigExportHelper.getProperty("place_school_lead");// 学校
					break;
				case "11":
					place_export = ConfigExportHelper.getProperty("place_hospital_lead");// 医院
					break;
				case "14":
					place_export = ConfigExportHelper.getProperty("place_farm_lead");// 农家乐
					break;	
				case "15":
					place_export = ConfigExportHelper.getProperty("place_other_lead");// 其他单位
					break;	
				case "16":
					place_export = ConfigExportHelper.getProperty("place_town_lead");// 乡镇驻点
					break;
				default:
					break;
			}

		}
		String keyPlace = "";
		InputStream is = new FileInputStream(root);
		Workbook workbook = WorkbookFactory.create(is);
		Sheet hssfSheet = workbook.getSheetAt(0);  //示意访问sheet

		int rowstart = hssfSheet.getFirstRowNum();
		int rowEnd = hssfSheet.getLastRowNum();

		for (int i = rowstart; i <= rowEnd; i++) {
			keyPlace = place_export;
			Row row = hssfSheet.getRow(i);
			if (null == row)
				continue;
			int cellStart = row.getFirstCellNum();
			int cellEnd = row.getLastCellNum();

			for (int k = cellStart; k <= cellEnd; k++) {
				Cell cell =  row.getCell(k);
				if (null == cell) {
					//System.out.print(k + "cell null");
					keyPlace = keyPlace.replaceAll("," + String.valueOf(k) + ",", "");
					continue;
				}
				if (i == rowstart) {// 第一行的所有字段
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						place_export = place_export.replace("," + cell.getStringCellValue() + ",",
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
					
					switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_NUMERIC: // 数字
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);//再转成String
						//System.out.println(k+":"+cell.toString());
						keyPlace = keyPlace.replaceAll("," + String.valueOf(k) + ",", cell.toString());
						break;
					case HSSFCell.CELL_TYPE_STRING: // 字符串
						//System.out.println(k+":"+cell.getStringCellValue());
						keyPlace = keyPlace.replaceAll("," + String.valueOf(k) + ",", cell.getStringCellValue());
						break;
					default:
						break;
					}

				}
			}
			if (i > rowstart) {// 第二行开始
				System.out.println(keyPlace);
				KeyPlace kp = JSON.parseObject(keyPlace, KeyPlace.class);// json字符串转对象
				String lon = String.valueOf(getTwoDouble(4, Double.parseDouble(kp.getLongitude())));// 保留3位小数
				String lat = String.valueOf(getTwoDouble(4, Double.parseDouble(kp.getLatitude())));// 保留3位小数
				String phone = kp.getPhone();
				String name = kp.getName();
				if (validatemobile(phone.trim()) == false) {// 判断手机号码
					System.out.println("-----------"+phone+"------------------");
					continue;
				}
				KeyPlace k= getKeyPlaceByNameAndTypeId(name.trim(), Long.parseLong(typeId));//名字查重
				if(k != null) {
					kp.setId(k.getId());
				}
				kp.setLongitude(lon);
				kp.setLatitude(lat);
				kp.setTypeId(Long.parseLong(typeId));
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

	public List<KeyPlace> selectAll(){
		return keyPlaceMapper.selectList();
	}
}
