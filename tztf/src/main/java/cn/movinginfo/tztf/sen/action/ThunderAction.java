package cn.movinginfo.tztf.sen.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.FileName;
import cn.movinginfo.tztf.sen.domain.Menu;
import cn.movinginfo.tztf.sen.domain.Thunder;
import cn.movinginfo.tztf.sen.service.MenuService;
import cn.movinginfo.tztf.sen.service.ThunderService;



@Controller
@RequestMapping("/sen/thunder")

public class ThunderAction extends MagicAction<Thunder,ThunderService>{
//	@Autowired
//	private ThunderService ldjcService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("getLdData")
	@ResponseBody
	public String getLdData(){
		
		return entityService.getLdData();
	}
	/**
	 * 同步雷电数据
	 * @throws ParseException
	 */
	@RequestMapping("insertData")
	@ResponseBody
	public void insertData() throws ParseException {
//		String xmlPath = "F://hyq.xml";
		String xmlPath =this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("WEB-INF/classes/", "static2/data/hyq.xml");
		String data = entityService.readFileToString(xmlPath);
		Document doc = (Document) Jsoup.parse(data);
		String coords =  doc.getElementsByTag("Area").get(0).attr("Coords");//获取xml文件里区域边界的经纬度
		System.out.println(coords);
		String[] coor = coords.split(",");
		double[] LngCollotions = new double[coor.length/2];//经度数组
		double[] LatCollotions = new double[coor.length/2];//纬度数组
		int m =0;
		int n =0;
		for(int i =0;i<coor.length;i++) {
			if(i%2 == 0) {
				if(m<coords.length()/2) {
					LngCollotions[m]=Double.parseDouble(coor[i]);
					m++;
				}
			}else if(i%2 == 1){
				if(n<coords.length()/2) {
				LatCollotions[n] = Double.parseDouble(coor[i]);
				n++;
				}
			}
		}
		String path = ConfigHelper.getProperty("thunder_path");
		File file=new File(path);
		File[] tempList = file.listFiles();
		for(File fi: tempList) {
			if(fi.isFile() && fi.getName().endsWith(".txt")) {
				String filePath = fi.getPath();
				List<String> list = entityService.readTxtFile(filePath);
				for(String li : list) {
					entityService.insertData(li,LngCollotions,LatCollotions);
				}
			}
		}
	}
	
	/**
	 * 获取雷电时间轴
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="getTimeList")
	@ResponseBody
	public List<FileName> getTimeList(String type) throws ParseException {
		List<Menu> menuList = menuService.getMenuByType(type);
		String title="";
		if(menuList.size() !=0) {
			Menu menu = menuList.get(0);
			 title = menu.getName();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		String nowDate = sdf.format(new Date()); 
		List<FileName> list = new ArrayList<FileName>();
		Date date = sdf.parse(nowDate);
		for(int x=0;x<24;x++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.HOUR, -x);
			Date oldDate = cal.getTime();
			String oldTime = DateUtil.format(oldDate, "yyyy-MM-dd HH:mm");
			FileName fileName = new FileName();
			String now = DateUtil.format(oldDate, "yyyy年MM月dd日 HH时mm分");
			fileName.setTime(now);
			fileName.setFileName(oldTime);
			fileName.setTitle(now+title);
			list.add(fileName);
		}
		//按时间正序排序
		//list.stream().sorted(Comparator.comparing(FileName::getTime).reversed()).collect(Collectors.toList());
		Collections.sort(list, (s1,s2) ->s1.getTime().compareTo(s2.getTime()));
		return list;
	}
	
	/**
	 * 获取雷电数据
	 * @return
	 */
	@RequestMapping(value="getThunderList")
	@ResponseBody
	public List<Thunder> getThunderList(String time) {
		Date date = DateUtil.parse(time, "yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, -1);
        Date oldDate = c.getTime();
		List<Thunder> thunderList = entityService.getAllData(oldDate, date);
		for(Thunder t : thunderList) {
			String power = t.getPower();
			if(Double.parseDouble(power)>=0) {
				t.setType("1");
			}else {
				t.setType("-1");
			}
		}
		return thunderList;
	}
	/**
	 * 获取指定雷电数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getThunder")
	@ResponseBody
	public Thunder getThunder(Long id) {
		Thunder thunder = entityService.get(id);
		Date time=thunder.getTime();
		thunder.setDate(DateUtil.format(time, "yyyy-MM-dd HH:mm:ss"));
		return thunder;
	}

}
