package cn.movinginfo.tztf.sen.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.*;
import cn.movinginfo.tztf.sen.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/sen/station")
public class StationAction {
	
	@Autowired
	private MenuService menuService;
	@Autowired 
	private KeyPeopleService keyPeopleService;
	@Autowired
	private PersonTypeService personTypeService;
	@Autowired
	private KeyPlaceService keyPlaceService;
	@Autowired
	private PlaceTypeService placeTypeService;
	@Autowired
	private FacilityEquipmentService facilityEquipmentService;
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private DangerPointService dangerPointService;
	@Autowired
	private DangerTypeService dangerTypeService;
	@Autowired
	private AlertruleService alertruleService;
	@Autowired
	private ElementinfoService elementinfoService;
	@Autowired
	private RnflRService rnflRService;
	
	/**
	 * 获取时间轴
	 * @param type
	 * @param lowType
	 * @return
	 */
	@RequestMapping(value="/getTimeList")
	@ResponseBody
	public List<FileName> getTimeList(String type,String lowType) {
		List<FileName> fileList = new ArrayList<>();
		String Weather_type = ConfigHelper.getProperty("Weather_type");//气象站
		String Farmland_type = ConfigHelper.getProperty("Farmland_type");//农田站
		String Environmental_type = ConfigHelper.getProperty("Environmental_type");//环保站
		String monitoring_day = ConfigHelper.getProperty("monitoring_day");
		String station_xml=ConfigHelper.getProperty("station_xml");//站点等值线
		String xml_file=ConfigHelper.getProperty("xml_file");
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -Integer.parseInt(monitoring_day));
        Date oldDate = c.getTime();
		String path="";
		if(Weather_type.equals(type) || Environmental_type.equals(type)) {
			path = ConfigHelper.getProperty("station_path");
			List<Menu> menuList = menuService.getMenuByType(lowType);
			String title="";
			if(menuList.size() !=0) {
				Menu menu = menuList.get(0);
				 title = menu.getName();
			}
			File file=new File(path+lowType);
			if(file.exists()) {
				File[] tempList = file.listFiles();
				 // 按照文件名正序排序
	            assert tempList != null;
	            Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
				for (File fi :tempList) {
					if(fi.isFile()) {
						String time = fi.getName().split("\\.")[0];
						Date date = DateUtil.parse(time, "yyyyMMddHHmm");
						if(date.getTime()>oldDate.getTime()) {
						String now = DateUtil.format(date, "yyyy年MM月dd日 HH时mm分");
						FileName fn = new FileName();
						fn.setFileName(fi.getName());
						fn.setXmlPath(station_xml+lowType+"/"+xml_file+"/"+fi.getName().replace(".dat", "_000.xml"));
						fn.setPointPath(station_xml+lowType+"/"+fi.getName().replace(".dat", ".000"));
						fn.setPath(fi.getPath());
						fn.setTime(now);
						fn.setTitle(now+title);
						fileList.add(fn);
						}
					}
				}
			}
		}else if(Farmland_type.equals(type) ) {
			path = ConfigHelper.getProperty("station_path");
			List<Menu> menuList = menuService.getMenuByType(type);
			String title="";
			if(menuList.size() !=0) {
				Menu menu = menuList.get(0);
				 title = menu.getName();
			}
			File file=new File(path+type);
			if(file.exists()) {
				File[] tempList = file.listFiles();
				assert tempList != null;
				Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
				for (File fi :tempList) {
					if(fi.isFile()) {
						String time = fi.getName().split("\\.")[0];
						Date date = DateUtil.parse(time, "yyyyMMddHHmm");
						if(date.getTime()>oldDate.getTime()) {
						String now = DateUtil.format(date, "yyyy年MM月dd日 HH时mm分");
						FileName fn = new FileName();
						fn.setPath(fi.getPath());
						fn.setFileName(fi.getName());
						fn.setTime(now);
						fn.setTitle(now+title);
						fileList.add(fn);
						}
					}
				}
			}
		}
		return fileList;
	}
	
	/**
	 * 读取三类数据文件
	 * @param path
	 * @return
	 */
	@RequestMapping(value="getStationData")
	@ResponseBody
	public String getStationData(String path,String xmlPath,String pointPath) {
		JSONObject jb = new JSONObject();
		String pointString=menuService.readFileToString(pointPath);
		String xmlString = menuService.readFileToString(xmlPath);
		List<Station> list = menuService.readTxtFile(path);
		Collections.sort(list, new Comparator<Station>() {//按值排序
			 public int compare(Station s1,Station s2) {
				 return Double.parseDouble(s1.getValue()) == Double.parseDouble(s2.getValue())?0:(Double.parseDouble(s2.getValue()) > Double.parseDouble(s1.getValue())?1:-1);
			 }
		 });
		jb.put("point",pointString);
		jb.put("xml", xmlString);
		jb.put("station", list);
		return jb.toJSONString();
	}


	
	/**
	 * 获取站点数据
	 * @param type 二级
	 *                菜单类型
	 * @param lowType 三级菜单类型
	 * @param time 时间
	 * @param code 站号
	 * @return
	 */
	@RequestMapping(value="getThisData")
	@ResponseBody
	public String getThisData(String type,String lowType,String time,String code) {
		String Weather_type = ConfigHelper.getProperty("Weather_type");//气象站
		String Farmland_type = ConfigHelper.getProperty("Farmland_type");//农田站
		String Environmental_type = ConfigHelper.getProperty("Environmental_type");//环保站
		JSONArray jsonarray = new JSONArray();
		String path = ConfigHelper.getProperty("station_path");
		for(int x=0;x<24;x++) {
			String relTime = menuService.getDates(time,x);
			//Date relDate = DateUtil.parse(relTime, "yyyyMMddHHmm");
			if(Farmland_type.equals(type) ) {
				String thisPath = path +type + "/"+relTime + ".dat";
				String value = menuService.readTxtFile2(thisPath,code);
				JSONObject ob = new JSONObject();
				ob.put("time", relTime.substring(8, 10)+"时");
				ob.put("value", value);
				ob.put("code", code);
				jsonarray.add(ob);
			}else if(Weather_type.equals(type) || Environmental_type.equals(type)){
				String thisPath = path+lowType+"/"+relTime+".dat";
				String value = menuService.readTxtFile2(thisPath,code);
				JSONObject ob = new JSONObject();
				ob.put("time", relTime.substring(8, 10)+"时");
				ob.put("value", value);
				ob.put("code", code);
				jsonarray.add(ob);
			}
		}
		return jsonarray.toJSONString();
	}
	
	
	
	/**
	 * 获取不同类型的重点人员
	 * @param personId
	 * @return
	 */
	@RequestMapping(value = "/getKeyPeoples")
	@ResponseBody
	public List<KeyPeople> getKeyPeoples(Long personId) {
		List<KeyPeople> list = keyPeopleService.getKeyPeopleByPersonId(personId);
		String icon = personTypeService.get(personId).getIcon();
		for(KeyPeople kp :list) {
			kp.setIcon(icon);
			kp.setDataType("staff");
		}
		return list;
	}
	
	/**
	 * 获取单个重点人员的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getOneKeyPeople")
	@ResponseBody
	public KeyPeople getOneKeyPeople(Long id) {
		KeyPeople keyPeople = keyPeopleService.get(id);
		Long personId = keyPeople.getPersonTypeId();
		PersonType personType = personTypeService.get(personId);
		keyPeople.setPersonType(personType.getName());
		if(keyPeople.getAddress()==null){
			keyPeople.setAddress("无");
		}
		if(keyPeople.getDepart()==null){
			keyPeople.setDepart("无");
		}
		if(keyPeople.getJob()==null){
			keyPeople.setJob("无");
		}
		return keyPeople;
	}
	
	/**
	 * 获取不同类型的重点场所
	 * @param typeId
	 * @return
	 */
	@RequestMapping(value = "/getKeyPlaces")
	@ResponseBody
	public List<KeyPlace> getKeyPlaces(Long typeId) {
		List<KeyPlace> list =  keyPlaceService.getKeyPlaceByTypeId(typeId);
		String icon = placeTypeService.get(typeId).getIcon();
		for(KeyPlace kp : list) {
			kp.setIcon(icon);
			kp.setDataType("key-unit");
			kp.setLon(kp.getLongitude());
			kp.setLat(kp.getLatitude());
			if(typeId == (long)7) {
				kp.setPointlist(kp.getLongitude()+","+kp.getLatitude()+"," + kp.getEndLongitude()+"," + kp.getEndLatitude());
			}
		} 
		return list;
	}
	
	/**
	 * 获取单个重点场所的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getOneKeyPlace")
	@ResponseBody
	public KeyPlace getOneKeyPlace(Long id) {
		KeyPlace keyPlace = keyPlaceService.get(id);
		Long typeId = keyPlace.getTypeId();
		PlaceType placeType = placeTypeService.get(typeId);
		keyPlace.setType(placeType.getName());
		keyPlace.setLatitude(String.valueOf(getTwoDouble(2,Double.parseDouble(keyPlace.getLatitude()))));
		keyPlace.setLongitude(String.valueOf(getTwoDouble(2,Double.parseDouble(keyPlace.getLongitude()))));
		return keyPlace;
	}
	

	/**
	 * 获取不同类型的设备设施
	 * @param equipmentId
	 * @return
	 */
	@RequestMapping(value="/getFacilityEquipment")
	@ResponseBody
	public List<FacilityEquipment> getFacilityEquipment(Long equipmentId) {
		List<FacilityEquipment> list = facilityEquipmentService.getFacilityEquipmentByEquipmentId(equipmentId);
		String icon = equipmentService.get(equipmentId).getIcon();
		for(FacilityEquipment facility : list) {
			facility.setIcon(icon);
			facility.setDataType("equipment");
		}
		return list;
	}
	/**
	 * 获取单个设备的详细信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getOneFacilityEquipment")
	@ResponseBody
	public FacilityEquipment getOneFacilityEquipment(Long id) {
		FacilityEquipment facilityEquipment = facilityEquipmentService.get(id);
		Long equipmentId = facilityEquipment.getEquipmentId();
		Equipment equipment = equipmentService.get(equipmentId);
		facilityEquipment.setEquipmentName(equipment.getName());
		return facilityEquipment;
	}
	
	/**
	 * 获取不同类型的隐患点
	 * 
	 * @param dangerTypeId
	 * @return
	 */
	@RequestMapping(value = "/getDangerPoints")
	@ResponseBody
	public List<DangerPoint> getDangerPoints(Long dangerTypeId) {
		List<DangerPoint> list = dangerPointService.getDangerPointByDangerTypeId(dangerTypeId);
		String icon = dangerTypeService.get(dangerTypeId).getIcon();
		for (DangerPoint point : list) {
				point.setIcon(icon);

			point.setDataType("danger-point");
		}
		return list;
	}

	/**
	 * 获取单个隐患点的信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getOneDangerPoint")
	@ResponseBody
	public DangerPoint getOneDangerPoint(Long id) {
		DangerPoint dangerPoint = dangerPointService.get(id);
		Long dangerTypeId = dangerPoint.getDangerTypeId();
		DangerType dangerType = dangerTypeService.get(dangerTypeId);
		dangerPoint.setDangerTypeName(dangerType.getName());
		String alertId = dangerPoint.getAlertruleId();
		Alertrule alert = alertruleService.get(Long.parseLong(alertId));
		dangerPoint.setThreshold(alert.getThresholdValue());
		String elementIds = alert.getElementIds();
		String[] eleIds = elementIds.split(",");

		String elementName = "";
		for(String e : eleIds) {
			Elementinfo eleInfo = elementinfoService.get(Long.parseLong(e));
			elementName += eleInfo.getElementName()+ ",";
		}
		dangerPoint.setMeteorological(elementName.substring(0, elementName.length()-1));
		if(dangerPoint.getLocation()==null){
			dangerPoint.setLocation("无");
		}
		if(dangerPoint.getDepart()==null){
			dangerPoint.setDepart("无");
		}
		if(dangerPoint.getLinker()==null){
			dangerPoint.setLinker("无");
		}
		if(dangerPoint.getPhone()==null){
			dangerPoint.setPhone("无");
		}
		if(dangerPoint.getJob()==null){
			dangerPoint.setJob("无");
		}
		if(dangerPoint.getCode()==null){
			dangerPoint.setCode("无");
		}
		return dangerPoint;
	}
	
	/**
     * 获取标题数据
     *
     * @return
     */
    @RequestMapping(value = "getTitleData")
    @ResponseBody
    public List<Menu> getTitleData() {
        return menuService.getTitleData();
    }
	
	/**
     * 获取一级菜单包括一级菜单内含有的二级菜单
     *
     * @param id 标题对应的id
     * @return
     */
    @RequestMapping(value = "getOneMenu")
    @ResponseBody
    public List<Menu> getOneMenu(Long id) {
        List<Menu> oneMenu = menuService.getMenuListByPid(id);
        for (Menu menu : oneMenu) {
            Long idTwo = menu.getId();
            List<Menu> twoMenu = menuService.getOtherMenuListByPid(idTwo);
            if (twoMenu.size() != 0) {
                menu.setLiMenu(twoMenu);
            }
            for (Menu me : twoMenu) {
                List<Menu> threeMenu = menuService.getOtherMenuListByPid(me.getId());
                if (threeMenu.size() != 0) {
                    me.setThreeMenu(threeMenu);
                }
            }
        }

        return oneMenu;
    }
    
    /**
     * 获取应急辅助的所有图标
     * @return
     */
    @RequestMapping(value = "getAllIcon")
    @ResponseBody
    public Map<String ,Object> getAllIcon() {
    	String point_icon = ConfigHelper.getProperty("point_icon");
    	Map<String,Object> map = JSON.parseObject(point_icon);
    	return map;
    }
    /**
     * 
     * @param x
     * @param f
     * @return
     */
    public double getTwoDouble(int x, double f) {
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }


	/**
	 * 获取气温大雾专题
	 * @param lowType
	 * @return
	 */

	@RequestMapping(value="/getMaxTemp")
	@ResponseBody
	public List<FileName> getMaxTemp(String type,String lowType) {
		List<FileName> fileList = new ArrayList<>();
		String monitoring_day = ConfigHelper.getProperty("monitoring_day");
		String maxtem_xml=ConfigHelper.getProperty("maxtem_xml");//站点等值线//D:\\hydata\\forecast\\Ruler/
		String xml_file=ConfigHelper.getProperty("maxtem_file");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, -Integer.parseInt(monitoring_day));
		Date oldDate = c.getTime();
			List<Menu> menuList = menuService.getMenuByType(lowType);
			String title="";
			if(menuList.size() !=0) {
				Menu menu = menuList.get(0);
				title = menu.getName();
			}
			File file=new File(maxtem_xml+lowType+"/"+xml_file);
			if(file.exists()) {
				File[] tempList = file.listFiles();
				// 按照文件名正序排序
				assert tempList != null;
				Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
				for (File fi :tempList) {
					if(fi.isFile()) {
						String time = fi.getName().split("_")[0];
						Date date = DateUtil.parse(time, "yyyyMMddHHmm");
						if(date.getTime()>oldDate.getTime()) {
							String now = DateUtil.format(date, "yyyy年MM月dd日 HH时mm分");
							FileName fn = new FileName();
							fn.setFileName(fi.getName());
							fn.setXmlPath(fi.getPath());
							fn.setPath(fi.getPath());
							fn.setTime(now);
							fn.setTitle(now+title);
							fileList.add(fn);
						}
					}
				}
			}

		return fileList;
	}

	/**
	 * 读取三类数据文件
	 * @param
	 * @return
	 */
	@RequestMapping(value="getStationList")
	@ResponseBody
	public String getStationList(String xmlPath) {
		JSONObject jb = new JSONObject();
		String xmlString = menuService.readFileToString(xmlPath);
		System.out.println(xmlString);
		jb.put("xml", xmlString);
		return jb.toJSONString();
	}



	
}
