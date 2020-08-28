package cn.movinginfo.tztf.sen.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.ColourCode;
import cn.movinginfo.tztf.sen.domain.FileName;
import cn.movinginfo.tztf.sen.domain.Menu;
import cn.movinginfo.tztf.sen.service.MenuService;

@Controller
@RequestMapping("/sen/radarAndCloud")
public class RadarAndCloudAction {
	
	@Autowired
	private MenuService menuService;
	/**
	 * 获取列表数据	
	 * @param type
	 * @return
	 */
	@RequestMapping(value="getFileList")
	@ResponseBody
	public String getFileList(String type) {
		JSONObject ob = new JSONObject();
		List<FileName> fileList = new ArrayList<>();
		List<ColourCode> colorList=null;
		String redcloud_type = ConfigHelper.getProperty("redcloud_type");//红外云图
		String lightcloud_type = ConfigHelper.getProperty("lightcloud_type");//可见光云图
		String colourcloud_type = ConfigHelper.getProperty("colourcloud_type");//彩色云图
		String radarCombined_type = ConfigHelper.getProperty("radarCombined_type");//雷达组合反射率
		String E01_type = ConfigHelper.getProperty("E01_type");//0.5度
		String E02_type = ConfigHelper.getProperty("E02_type");//1.5度
		String E03_type = ConfigHelper.getProperty("E03_type");//2.4度
		String monitoring_day = ConfigHelper.getProperty("monitoring_day");//实况监测的时间轴
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -Integer.parseInt(monitoring_day));
        Date oldDate = c.getTime();
		String path="";
		if(type.equals(redcloud_type) || type.equals(colourcloud_type) ||type.equals(lightcloud_type)) {
			 path = ConfigHelper.getProperty("cloud_path");//卫星云图
			 File file=new File(path+type);
			 if(file.exists()) {
				File[] tempList = file.listFiles();
				 // 按照文件名正序排序
				assert tempList != null;
				Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
				for (File fi :tempList) {
					if(fi.isFile()) {
						String time = fi.getName().split("_MLS_")[1].split("\\.")[0];
						Date date = DateUtil.parse(time, "yyyyMMdd_HHmm");
						Date relDate = new Date(date.getTime() + 8*60*60*1000);//8小时后的时间
						if(relDate.getTime()>oldDate.getTime()) {
						String now = DateUtil.format(relDate, "yyyy年MM月dd日 HH时mm分");
						//System.out.println(now);
						FileName fn = new FileName();
						fn.setFileName(fi.getName());
						fn.setTime(now);
						fileList.add(fn);
						}
					}
				}
			 }
		}else if(type.equals(radarCombined_type)) {
			String radar_colour = ConfigHelper.getProperty("radar_colour");//色标
			colorList = readTxtFile(radar_colour);
			path = ConfigHelper.getProperty("radarCombined_path");//组合反射率
			File file=new File(path);
			if(file.exists()) {
			File[] tempList = file.listFiles();
			assert tempList != null;
			Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
			for (File fi :tempList) {
				if(fi.isFile()) {
					String time = fi.getName().split("_L88_")[1].split("_")[0].substring(0, 12);
					Date date = DateUtil.parse(time, "yyyyMMddHHmm");
					Date relDate = new Date(date.getTime() + 8*60*60*1000);//8小时后的时间
					if(relDate.getTime()>oldDate.getTime()) {
					String now = DateUtil.format(relDate, "yyyy年MM月dd日 HH时mm分");
					FileName fn = new FileName();
					fn.setFileName(fi.getName());
					fn.setTime(now);
					fileList.add(fn);
					}
				}
			 }
			}
		}else if(type.equals(E01_type) ||type.equals(E02_type) || type.equals(E03_type)){
			String radar_colour = ConfigHelper.getProperty("radar_colour");//色标
			colorList = readTxtFile(radar_colour);
			path = ConfigHelper.getProperty("radarBasic_path");//基本反射率
			File file=new File(path);
			if(file.exists()) {
			File[] tempList = file.listFiles();
			assert tempList != null;
			Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
			for (File fi :tempList) {
				if(fi.isFile()) {
					if(fi.getName().contains(type)) {
						String time = fi.getName().split("_"+type+"_")[1].split("_")[0].substring(0, 12);
						Date date = DateUtil.parse(time, "yyyyMMddHHmm");
						Date relDate = new Date(date.getTime() + 8*60*60*1000);//8小时后的时间
						if(relDate.getTime()>oldDate.getTime()) {
						String now = DateUtil.format(relDate, "yyyy年MM月dd日 HH时mm分");
						FileName fn = new FileName();
						fn.setFileName(fi.getName());
						fn.setTime(now);
						fileList.add(fn);
						}
					}
				}
			  }
			}
		}
		ob.put("fileList", fileList);
		ob.put("colorList", colorList);
		
		return ob.toJSONString();
	}
	
	/**
	 * 请求数据路径
	 * @param fileName
	 * @param type
	 * @param time
	 * @param icon
	 * @return
	 */
	@RequestMapping(value="/getData")
	@ResponseBody
	public String getData(String fileName,String type,String time,String icon) {
		String radarCombined_type = ConfigHelper.getProperty("radarCombined_type");//雷达组合反射率
		String E01_type = ConfigHelper.getProperty("E01_type");//0.5度
		String E02_type = ConfigHelper.getProperty("E02_type");//1.5度
		String E03_type = ConfigHelper.getProperty("E03_type");//2.4度
		String dataPath = ConfigHelper.getProperty("data_path");
		String path = "";
		if(radarCombined_type.equals(type) ||E01_type.equals(type) ||E02_type.equals(type)||E03_type.equals(type)) {
			 path = dataPath+"Type=" +icon + "&File=" +fileName+"&x=${x}&y=${y}&z=${z}&Level=0&FilterValue=5";
		}else {
			 path = dataPath+"Type=" +icon + "&File=" +fileName+"&x=${x}&y=${y}&z=${z}";
		}
		
		Date date = DateUtil.parse(time, "yyyyMMddHHmm");
		String reaTime = DateUtil.format(date, "yyyy年MM月dd日 HH时mm分");
		List<Menu> list = menuService.getMenuByType(type);
		String title = "";
		if(list.size() != 0) {
			Menu menu = list.get(0);
			String name = menu.getName();
			if(name.equals("0.5度") || name.equals("1.5度") ||name.equals("2.4度")) {
				Menu higherMenu = menuService.get(menu.getPid());
				String higherName = higherMenu.getName();
				title = reaTime + higherName + name;
			}else {
				 title = reaTime + name;
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("path", path);
		jo.put("title", title);
		return jo.toJSONString();
	}
	
	/**
	 * 获取雷达色标
	 * @param filePath
	 * @return
	 */
	public static List<ColourCode> readTxtFile(String filePath){
		List<ColourCode> list=new ArrayList<>();
		    try { 
		        String encoding="UTF-8"; 
		        File file=new File(filePath); 
		        if(file.isFile() && file.exists()){ //判断文件是否存在 
		          InputStreamReader read = new InputStreamReader( 
		          new FileInputStream(file),encoding);//考虑到编码格式 
		          BufferedReader bufferedReader = new BufferedReader(read); 
		          String lineTxt = null; 
		          int line = 1;
		          while((lineTxt = bufferedReader.readLine()) != null){ 
		        	  if(line>=2) {//第二行起读
		        		  String[] Txts = lineTxt.split("\\s+");
		        		  ColourCode color = new ColourCode();
		        		  color.setItem(Txts[0]);
		        		  color.setColor(Txts[1]+","+Txts[2]+","+Txts[3]);
		        		  list.add(color);
		        	  }
		            line++;
		          } 
		          read.close(); 
		    }else{ 
		      System.out.println("找不到指定的文件"); 
		    } 
		    } catch (Exception e) { 
		      System.out.println("读取文件内容出错"); 
		      e.printStackTrace(); 
		    } 
		    return list;
		  } 
	

}
