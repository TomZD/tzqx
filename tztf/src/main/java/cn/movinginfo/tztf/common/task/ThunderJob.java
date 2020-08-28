package cn.movinginfo.tztf.common.task;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.sen.service.ThunderService;

@Service("ThunderJob")
public class ThunderJob {
	@Autowired
	private ThunderService thunderService;

	public void job() throws ParseException {
		System.out.println("---------开始同步雷电数据--------------");
		insertData();
	}

	public void insertData() throws ParseException {
		String xmlPath = this.getClass().getResource("/").getPath().replaceFirst("/", "").replaceAll("WEB-INF/classes/",
				"static2/data/hyq.xml");
		String data = thunderService.readFileToString(xmlPath);
		Document doc = (Document) Jsoup.parse(data);
		String coords = doc.getElementsByTag("Area").get(0).attr("Coords");// 获取xml文件里区域边界的经纬度
		String[] coor = coords.split(",");
		double[] LngCollotions = new double[coor.length / 2];// 经度数组
		double[] LatCollotions = new double[coor.length / 2];// 纬度数组
		int m = 0;
		int n = 0;
		for (int i = 0; i < coor.length; i++) {
			if (i % 2 == 0) {
				if (m < coords.length() / 2) {
					LngCollotions[m] = Double.parseDouble(coor[i]);
					m++;
				}
			} else if (i % 2 == 1) {
				if (n < coords.length() / 2) {
					LatCollotions[n] = Double.parseDouble(coor[i]);
					n++;
				}
			}
		}
		String path = ConfigHelper.getProperty("thunder_path");
		File file = new File(path);
		if (file.exists()) {
			File[] tempList = file.listFiles();
			for (File fi : tempList) {
				if (fi.isFile() && fi.getName().endsWith(".txt")) {
					String filePath = fi.getPath();
					List<String> list = thunderService.readTxtFile(filePath);
					for (String li : list) {
						thunderService.insertData(li, LngCollotions, LatCollotions);
					}
				}
			}
		}

	}

}
