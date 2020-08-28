package cn.movinginfo.tztf.sen.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.common.action.MagicAction;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.DisasterReport;
import cn.movinginfo.tztf.sen.domain.Station;
import cn.movinginfo.tztf.sen.service.DisasterReportService;
import cn.movinginfo.tztf.sen.service.MenuService;

@Controller
@RequestMapping("/sen/disasterInformation")
public class DisasterInformationAction extends MagicAction<DisasterReport, DisasterReportService>{
	
	@Autowired
	private MenuService menuService;
	/**
	 * 获取所有灾情上报
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	@ResponseBody
	public List<DisasterReport> getAll() {
		List<DisasterReport> list = entityService.getAll();
		return list;
	}
	/**
	 * 获取单个灾情上报
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getDisaster")
	@ResponseBody
	public DisasterReport getDisaster(Long id) {
		DisasterReport disaster = entityService.get(id);
		Date disasterTime = disaster.getDisasterTime();
		String disTime = DateUtil.format(disasterTime, "yyyy年MM月dd日 HH时mm分"); 
		String image = disaster.getImages();
		String[] imageList = image.split(","); 
		disaster.setDisTime(disTime);
		disaster.setImageList(imageList);
		return disaster;
	}
	/**
	 * 获取自动站一小时的最新记录
	 * @return
	 */
	@RequestMapping(value="/getAutomaticData")
	@ResponseBody
	public List<Station> getAutomaticData() {
		String path = ConfigHelper.getProperty("station_1h");//自动站一小时数据
		String data_path="";
		File file=new File(path);
		if(file.exists()) {
			File[] tempList = file.listFiles();
			for(int i=tempList.length-1;i>=0;i--) {
				if(tempList[i].isFile()) {
					data_path=tempList[i].getPath();
					break;
				}
			}
		}
		List<Station> list = menuService.readTxtFile(data_path);
		return list;
		
	}
	
	/**
	 * 获取单个自动站数据
	 * @param path
	 * @param code
	 * @return
	 */
	@RequestMapping(value="getAutomatic")
	@ResponseBody
	public Station getAutomatic(String path,String code) {
		Station station = new Station();
		String value = menuService.readTxtFile2(path,code);
		station.setName(code);
		station.setValue(value);
		return station;
	}

}
