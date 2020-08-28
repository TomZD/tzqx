package cn.movinginfo.tztf.sen.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;

import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.common.utils.StaticStation;
import cn.movinginfo.tztf.sen.domain.FileName;
import cn.movinginfo.tztf.sen.domain.Menu;
import cn.movinginfo.tztf.sen.domain.Station;
import cn.movinginfo.tztf.sen.mapper.MenuMapper;
import tk.mybatis.mapper.entity.Example;

@Component
public class MenuService extends BaseService<Menu, MenuMapper> {

	/**
	 * 列表页面获取数据库信息
	 */
	public List<Menu> query(Map<String, String> paramMap) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		String name = paramMap.get("name");
		String pid = paramMap.get("pid");
		String menuId = paramMap.get("menuId");
		String type = paramMap.get("type");
		if (!StringUtils.isEmpty(name)) {
			criteria.andLike("name", "%" + name + "%");
		}
		if (!StringUtils.isEmpty(pid)) {
			criteria.andEqualTo("pid", pid);
		}
		if (!StringUtils.isEmpty(menuId)) {
			// criteria.andEqualTo("menuId", menuId);
			criteria.andGreaterThanOrEqualTo("menuId", menuId);
		}
		if (!StringUtils.isEmpty(type)) {
			criteria.andEqualTo("type", type);
		}
		example.setOrderByClause("id desc");
		List<Menu> menuList = mapper.selectByExample(example);
		for (Menu menu : menuList) {
			if (menu.getPid() != (long) -100) {
				String pidName = get(menu.getPid()).getName();
				menu.setPidName(pidName);
			} else {
				menu.setPidName(menu.getName());
			}
		}
		return menuList;
	}

	public List<Menu> getTitleData() {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andEqualTo("menuId", 0);
		return mapper.selectByExample(example);
	}

	public List<Menu> getAllTitleMenu() {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		return mapper.selectByExample(example);
	}

	public List<Menu> getOtherMenu(Long id) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andNotEqualTo("id", id);
		return mapper.selectByExample(example);
	}

	public Menu findMenuById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public List<Menu> getMenuListByPid(Long pid) {
		return mapper.selectMenus(pid);
	}
	
	public List<Menu> getOtherMenuListByPid(Long pid) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andEqualTo("pid",pid);
		return mapper.selectByExample(example);
	}

	public Menu findMenuByName(String name) {
		Menu menu = new Menu();
		menu.setValid(1);
		menu.setName(name);
		return mapper.selectOne(menu);
	}

	public Menu findMenuByNameAndPid(String name, Long pid) {
		Menu menu = new Menu();
		menu.setValid(1);
		menu.setName(name);
		menu.setPid(pid);
		return mapper.selectOne(menu);
	}

	public List<Menu> getMenuByName(String name) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andEqualTo("name", name);
		return mapper.selectByExample(example);
	}

	public List<Menu> getMenuByNameAndId(String name, Long id) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andEqualTo("name", name);
		criteria.andEqualTo("id", String.valueOf(id));
		return mapper.selectByExample(example);
	}

	public List<Menu> getOneMenu(String title) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andEqualTo("menuId", 1);
		criteria.andEqualTo("title", title);
		return mapper.selectByExample(example);
	}

	public String getTimes(Date d, int x) {
		Date now_10 = new Date(d.getTime() - 600000 * (x + 2)); // 10分钟前的时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");// 可以方便地修改日期格式
		String nowTime_10 = dateFormat.format(now_10);
		String nowTime = nowTime_10 + "00";
		String str = "0";
		StringBuffer sb = new StringBuffer(nowTime);
		if (StringUtils.isNotBlank(str)) {
			sb.replace(15, 16, str);
		}
		return sb.toString();
	}

	public String getOldTimes(Date d, int x) {
		Date now_10 = new Date(d.getTime() - 600000 * (x + 5) - 600000 * 48); // 10分钟前的时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");// 可以方便地修改日期格式
		String nowTime_10 = dateFormat.format(now_10);
		String nowTime = nowTime_10 + "00";
		String str = "0";
		StringBuffer sb = new StringBuffer(nowTime);
		if (StringUtils.isNotBlank(str)) {
			sb.replace(15, 16, str);
		}
		return sb.toString();
	}

	public String getDates(String time, int x) {
		Date d = DateUtil.parse(time, "yyyyMMddHHmm");
		Date HourAgo = new Date(d.getTime() - x * 1000 * 60 * 60);
		String hourAgo = DateUtil.format(HourAgo, "yyyyMMddHHmm");
		return hourAgo;
	}

	public List<Menu> getOneMenus(String title) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menuId", 1);
		criteria.andEqualTo("title", title);
		return mapper.selectByExample(example);
	}

	public List<Menu> getAllTitle(String title) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andEqualTo("title", title);
		return mapper.selectByExample(example);
	}

	public List<Menu> getMenuByType(String type) {
		return mapper.getMenuByType(type);
	}

	public List<Menu> getOneTitle(String title) {
		Example example = new Example(Menu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andEqualTo("title", title);
		return mapper.selectByExample(example);
	}

	public String getMenuName(Long id, String name) {
		System.out.println(id);
		Long i = id;
		Menu menu = get(id);
		if (menu.getPid() == (long) -100) {
			return name;
		}
		Menu pidMenu = get(menu.getPid());
		name = pidMenu.getName() + "-" + name;
		i = menu.getPid();
		return getMenuName(i, name);
	}

	public void deleteMenu(Long id) {
		Menu menu = get(id);
		menu.setValid(0);
		saveOrUpdate(menu);
		List<Menu> menuList = getMenuListByPid(id);
		if (menuList.size() == 0) {
			return;
		}
		for (Menu m : menuList) {
			deleteMenu(m.getId());
		}
	}

	/**
	 * 服务材料接口
	 *
	 * @param path本地路径
	 * @param time1
	 *            起始时间
	 * @param pages 
	 * @param time2结束时间
	 * @return
	 * @throws ParseException
	 */
	public List<FileName> fileList(String path,String type, String time1, String time2, String name) throws ParseException {
		String default_path = ConfigHelper.getProperty("default_path");//默认路径
		File file = new File(path);
		List<FileName> fileList = new ArrayList<>();
		if (file.exists()) {
			File[] tempList = file.listFiles();
			// 按照文件最后修改日期正序排序
			assert tempList != null;
			Arrays.sort(tempList, (file1, file2) -> (int) (file1.lastModified() - file2.lastModified()));
			for (File fi : tempList) {
				if (fi.isFile()) {
					//获取文件的修改时间
				     Long updatetime =  fi.lastModified();
				     Date time = new Date(updatetime);
				     SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				     SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
				     Date date = DateUtil.parse(sdf1.format(time),"yyyyMMdd");
					String fileName = fi.getName();
					if (fileName.contains(name)) {
						if (time1 != "" && time2 != "") {
							Date date1 = DateUtil.parse(time1, "yyyy-MM-dd");
							Date date2 = DateUtil.parse(time2, "yyyy-MM-dd");
							if (date.getTime() >= date1.getTime() && date.getTime() <= date2.getTime()) {
								FileName filen = new FileName();
								filen.setFileName(fi.getName().split("\\.")[0]);
								filen.setPath(default_path +type+"/"+ fi.getName());
								filen.setTime(sdf.format(date));
								fileList.add(filen);
								System.out.println(filen);
							}
						} else if (time1 != "" && time2 == "") {
							Date date1 = DateUtil.parse(time1, "yyyy-MM-dd");
							if (date.getTime() >= date1.getTime()) {
								FileName filen = new FileName();
								filen.setFileName(fi.getName().split("\\.")[0]);
								filen.setPath(default_path +type+"/"+ fi.getName());
								filen.setTime(sdf.format(date));
								fileList.add(filen);
							}
						} else if (time1 == "" && time2 != "") {
							Date date2 = DateUtil.parse(time2, "yyyy-MM-dd");
							if (date.getTime() <= date2.getTime()) {
								FileName filen = new FileName();
								filen.setFileName(fi.getName().split("\\.")[0]);
								filen.setPath(default_path +type+"/"+ fi.getName());
								filen.setTime(sdf.format(date));
								fileList.add(filen);
							}
						} else {
							FileName filen = new FileName();
							filen.setFileName(fi.getName().split("\\.")[0]);
							filen.setPath(default_path +type+"/"+ fi.getName());
							filen.setTime(sdf.format(date));
							fileList.add(filen);
						}
					}
				}
			}
		}
		return fileList;
	}

	/**
	 * 以utf-8的编码读取文件内容
	 * 
	 * @param filePath
	 * @return
	 */
	public String readFileToString(String filePath) {
		String str = "";
		File file = new File(filePath);
		try {
			FileInputStream in = new FileInputStream(file);
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer, "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return str;
	}

	/**
	 * 读取三类文件
	 *
	 * @param filePath
	 * @return
	 */
	public List<Station> readTxtFile(String filePath) {
		List<Station> list = new ArrayList<>();
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int line = 1;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (line >= 5) {// 第五行起读
						// System.out.println(line+":"+lineTxt);
						String[] Txts = lineTxt.split("\\s+");
						Station station = new Station();
						station.setName(StaticStation.CACHE.get(Txts[0]));// 站名
						station.setCode(Txts[0]);
						station.setLon(Txts[1]);// 经度
						station.setLat(Txts[2]);// 纬度
						station.setValue(Txts[4]);// 值
						station.setPath(filePath);
						list.add(station);
					}
					line++;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return list;
	}

	public String readTxtFile2(String filePath, String code) {
		String value = "";
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int line = 1;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (line >= 5) {// 第五行起读
						// System.out.println(line+":"+lineTxt);
						String[] Txts = lineTxt.split("\\s+");
						if (code.equals(Txts[0])) {
							value = Txts[4];
							break;// 读到值就退出循环
						}
					}
					line++;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return value;
	}

	public List<Menu> selectByPid(Integer pid) {
		return mapper.selectByPid(pid);
	}

	public Menu selectByType(String type) {
		return mapper.selectByType(type);
	}

	private String getcnweather(String x) {
		String result = null;
		String key = x.replace(" ", "");
		switch (key) {
		case "0":
			result = "晴";
			break;
		case "1":
			result = "多云";
			break;
		case "2":
			result = "阴";
			break;
		case "3":
			result = "阵雨";
			break;
		case "4":
			result = "雷阵雨";
			break;
		case "5":
			result = "冰雹";
			break;
		case "6":
			result = "雨夹雪";
			break;
		case "7":
			result = "小雨";
			break;
		case "8":
			result = "中雨";
			break;
		case "9":
			result = "大雨";
			break;
		case "10":
			result = "暴雨";
			break;
		case "11":
			result = "大暴雨";
			break;
		case "12":
			result = "特大暴雨";
			break;
		case "13":
			result = "阵雪";
			break;
		case "14":
			result = "雪";
			break;
		case "15":
			result = "中雪";
			break;
		case "16":
			result = "大雪";
			break;
		case "18":
			result = "雾";
			break;
		case "19":
			result = "冻雨";
			break;
		case "20":
			result = "沙尘暴";
			break;
		case "21":
			result = "小到中雨";
			break;
		case "22":
			result = "中到大雨";
			break;
		case "23":
			result = "大到暴雨";
			break;
		case "24":
			result = "暴雨到大暴雨";
			break;
		case "25":
			result = "大暴雨到特大暴雨";
			break;
		case "26":
			result = "小到中雪";
			break;
		case "27":
			result = "中到大雪";
			break;
		case "28":
			result = "大到暴雪";
			break;
		case "29":
			result = "浮尘";
			break;
		case "30":
			result = "扬沙";
			break;
		case "31":
			result = "强沙尘暴";
			break;
		case "53":
			result = "霾";
			break;
		default:
			result = "晴";
			break;
		}

		return result;
	}

}
