package cn.movinginfo.tztf.common.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import cn.movinginfo.tztf.common.constants.SystemProperties;

public class MapHelper {

	public static final int MAP_WIDTH = 1000;// 浙江地图的宽度
	public static final int MAP_HEIGHT = 1000;// 浙江地图的高度
	public static final double FULLCOLORMAP_MAXX = 122.58399963378906;// 浙江地图最大的经度
	public static final double FULLCOLORMAP_MAXY = 31.18199920654297;// 浙江地图最大的纬度
	public static final double FULLCOLORMAP_MINX = 118.02300262451172;// 浙江地图最小的经度
	public static final double FULLCOLORMAP_MINY = 27.14299964904785;// 浙江地图最小的纬度
	public static final double FULLCOLORMAP_STARTX = 0.0;// 地图的开始x坐标
	public static final double FULLCOLORMAP_STARTY = 57.0;// 地图的开始y坐标
	public static final double FULLCOLORMAP_FACTOR = 219.25030820365362;//
	public static BufferedImage FULLCOLORMAP;
	public static final String PATH = "ColorMap.png";
	private static LinkedHashMap<String, String> FULLCOLORMAP_LEVEL = new LinkedHashMap<String, String>();

	static {
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(new File(SystemProperties.getProperty("app_path") + "/WEB-INF/classes/colorindex.txt"),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] regionColor = lines.get(0).substring(1).split("_");
		FULLCOLORMAP_LEVEL.put(regionColor[0], regionColor[regionColor.length - 1]);
		for (int i = 1; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] rc = line.split("_");
			FULLCOLORMAP_LEVEL.put(rc[0], rc[rc.length - 1]);
		}
		File map = new File(MapHelper.class.getResource("/").getPath() + PATH);
		try {
			FULLCOLORMAP = ImageIO.read(map);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: getArea 
	 * @Description: 根据经纬度获取地区 
	 * @param points 经纬度集合
	 * @return
	 * @throws Exception   
	 * @return List<LinkedHashSet<String>> 
	 * @throws
	 */
	public static List<LinkedHashSet<String>> getArea(List<Point2D> points) throws Exception {
		List<LinkedHashSet<String>> result = new ArrayList<LinkedHashSet<String>>();
		LinkedHashSet<String> provinceList = new LinkedHashSet<String>();//省
		LinkedHashSet<String> cityList = new LinkedHashSet<String>();//市
		LinkedHashSet<String> countryList = new LinkedHashSet<String>();//县
		LinkedHashSet<String> townList = new LinkedHashSet<String>();//镇
		result.add(provinceList);
		result.add(cityList);
		result.add(countryList);
		result.add(townList);
		List<Integer[]> xyList = drawCustomPloygon(points);// 获取自定义的多边形的坐标集合
		String area;
		for (Integer[] xy : xyList) {
			int x = xy[0];
			int y = xy[1];
			if (x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT) {
				continue;
			}
			int[] rgb = new int[3]; 
			int pixel = FULLCOLORMAP.getRGB(x, y);
			rgb[0] = (pixel & 0xff0000) >> 16;
			rgb[1] = (pixel & 0xff00) >> 8;
			rgb[2] = (pixel & 0xff);
			Color color = new Color(rgb[0], rgb[1], rgb[2], 255);
			if(FULLCOLORMAP_LEVEL.containsKey(color.getAlpha() + ",0,0,0")){
				area = FULLCOLORMAP_LEVEL.get(color.getAlpha() + ",0,0,0").replace("\\", "/");
				provinceList.add(area);
			}
			
			if(FULLCOLORMAP_LEVEL.containsKey(color.getAlpha() + "," + color.getRed() + ",0,0")){
				area = FULLCOLORMAP_LEVEL.get(color.getAlpha() + "," + color.getRed() + ",0,0").replace("\\", "/");
				cityList.add(area);
			}
			
			if(FULLCOLORMAP_LEVEL.containsKey(color.getAlpha() + "," + color.getRed() + "," + color.getGreen() + ",0")){
				area = FULLCOLORMAP_LEVEL.get(color.getAlpha() + "," + color.getRed() + "," + color.getGreen() + ",0").replace("\\", "/");
				countryList.add(area);
			}
			
			if(FULLCOLORMAP_LEVEL.containsKey(color.getAlpha() + "," + color.getRed() + "," + color.getGreen() + "," + color.getBlue())){
				area = FULLCOLORMAP_LEVEL.get(color.getAlpha() + "," + color.getRed() + "," + color.getGreen() + "," + color.getBlue()).replace("\\", "/");
				townList.add(area);
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: drawCustomPloygon 
	 * @Description: 根据经纬度集合画多边形，返回地图的坐标集合 
	 * @param points
	 * @return
	 * @throws Exception   
	 * @return List<Integer[]> 
	 * @throws
	 */
	public static List<Integer[]> drawCustomPloygon(List<Point2D> points) throws Exception {
		int fromx = 1000;
		int tox = 0;
		int fromy = 1000;
		int toy = 0;
		int[] xs = new int[points.size()];
		int[] ys = new int[points.size()];
		for (int i = 0; i < points.size(); i++) {
			Point2D thePoint = points.get(i);
			float x = (float) ((thePoint.getX() - FULLCOLORMAP_MINX) * FULLCOLORMAP_FACTOR + FULLCOLORMAP_STARTX);
			float y = (float) ((FULLCOLORMAP_MAXY - thePoint.getY()) * FULLCOLORMAP_FACTOR + FULLCOLORMAP_STARTY);
			xs[i] = (int) x;
			ys[i] = (int) y;
		}
		for (int i = 0; i < xs.length; i++) {
			tox = Math.max(xs[i], tox);
			fromx = Math.min(xs[i], fromx);
			toy = Math.max(ys[i], toy);
			fromy = Math.min(ys[i], fromy);
		}

		BufferedImage customPloygon = new BufferedImage(MAP_WIDTH, MAP_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		Color color = new Color(255, 0, 0, 255);
		Graphics graphics = customPloygon.getGraphics();
		graphics.setColor(color);
		graphics.fillPolygon(xs, ys, points.size());
		List<Integer[]> result = new ArrayList<Integer[]>();
		for (int i = fromx; i < tox; i++) {
			for (int j = fromy; j < toy; j++) {
				int pixel = customPloygon.getRGB(i, j);
				if (255 == (pixel & 0xff0000) >> 16) {
					Integer[] xy = new Integer[2];
					xy[0] = i;
					xy[1] = j;
					result.add(xy);
				}
			}
		}
		ImageIO.write(customPloygon, "PNG", new File(MapHelper.class.getResource("").getPath()+"222.png"));
		return result;
	}
	
	
	public static void main(String[] args) {
		double x = 13450467.21941903;
		double y = 3471227.6826823708;
		List<Point2D> points = new ArrayList<Point2D>();
//		points.add(new Point2D.Float(120.72f,29.91f));
//		points.add(new Point2D.Float(120.5f,29.68f));
//		points.add(new Point2D.Float(120.89f,29.64f));
//		points.add(new Point2D.Float(120.72f,29.91f));
		String pointsStr = "13497551.798058,3513815.9605931;13435790.679212,3498528.5549382;13464531.001843,3447774.3681639;13559924.41313,3458169.8040092;13499997.782963,3495471.0738072;13557478.428225,3524211.3964384;13561758.901808,3475291.6983427;13525069.128237,3545613.7643553;13427841.228271,3540721.7945457;13387482.477343,3491190.6002238;13434567.68676,3431875.4662828;13456581.550903,3441047.9096757;13497551.798058,3513815.9605931";
		try {
			String content = FileUtils.readFileToString(new File("d:/嘉兴市_海宁市.xml"), "UTF-8");
			pointsStr = content.substring(content.indexOf("Coords=")+8,content.lastIndexOf("\""));
			String[] xyStr = pointsStr.split(",");
			for(int i=0;i<xyStr.length;i+=2){
				points.add(new Point2D.Double(Double.valueOf(xyStr[i]), Double.valueOf(xyStr[i+1])));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
//		System.out.println(pointsStr);
//		for(String str:pointsStr.split(";")){
//			String[] xyStr = str.split(",");
//			points.add(GISHelper.mercator2Location(new Point2D.Double(Double.valueOf(xyStr[0]), Double.valueOf(xyStr[1]))));
//			
//		}
//		drawCustomPloygon(points);
//		points.add(GISHelper.mercator2Location(new Point2D.Double(x, y)));
		try {
			drawCustomPloygon(points);
//			List<LinkedHashSet<String>> result = getArea(points);
//			for(String town:result.get(3)){
//				System.out.println(town);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
}
