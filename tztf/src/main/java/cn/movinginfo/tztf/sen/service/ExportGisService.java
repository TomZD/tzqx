package cn.movinginfo.tztf.sen.service;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.ImageUtils;
import cn.movinginfo.tztf.common.utils.PointUtil;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;


@Component
public class ExportGisService {

    public String exportGisGif(String xml) throws Exception {
//		String xml = readFileToString("E:\\qtdata\\201909281555_000.xml");
//		String xml = ReadFile.readFileToString("E:\\qtdata\\test.xml");
//		System.out.println(xml);


        String tempDir = ConfigHelper.getProperty("tempDir");//存放路径
        File dir = new File(tempDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String BaseMapPath = ConfigHelper.getProperty("BaseMapPath");
        String MapRange = ConfigHelper.getProperty("MapRange");
        String[] points = MapRange.split("\\|");
        //底图左上角经纬度
        Point2D leftTop = new Point2D.Float();
        leftTop.setLocation(Float.parseFloat(points[0].split(",")[0]), Float.parseFloat(points[0].split(",")[1]));

        //底图右下角经纬度
        Point2D rightBottom = new Point2D.Float();
        rightBottom.setLocation(Float.parseFloat(points[1].split(",")[0]), Float.parseFloat(points[1].split(",")[1]));

        File picture = new File(BaseMapPath);
        BufferedImage bimg = ImageIO.read(new FileInputStream(picture));
        int imgWidth = bimg.getWidth();
        int imgHeight = bimg.getHeight();


        String tempImgs = drawTyphoonRoad(bimg, tempDir, imgWidth, imgHeight, leftTop, rightBottom, xml);

        //Image bgimage = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\save.png"));
        Image bgimage = ImageIO.read(new File(tempImgs));
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
        BufferedImage bimg2 = ImageIO.read(new FileInputStream(picture));
        Graphics2D g2d = bimg2.createGraphics();
        g2d.setComposite(ac);
        g2d.drawImage(bgimage, 0, 0, null);
        g2d.dispose();
        String fileName = UUID.randomUUID().toString();
        ImageIO.write(bimg2, "png", new File(tempDir + "\\" + fileName + ".png"));
        String tempImgsNew = tempDir + "\\" + fileName + ".png";

        return tempImgsNew;

    }

    private String drawTyphoonRoad(BufferedImage bimg, String tempDir, int imgWidth, int imgHeight, Point2D leftTop, Point2D rightBottom, String xml) throws Exception {
        String tempImgs = "";


//		xml=readFileContent("D://qtdata//test.xml");
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement().element("RS");
        int index = 0;
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {

            Element element = it.next();
            for (Iterator<Element> it1 = element.elementIterator(); it1.hasNext(); ) {
                Element element1 = it1.next();
                String[] latlonArr = element1.getTextTrim().split(" ");
                Point2D lonLat = new Point2D.Float();
                if (latlonArr.length == 5) {
                    index++;
                }
            }

        }
//		String a = "";


        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Graphics2D g2d = bimg.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Element element = it.next();
            String co = element.attributeValue("Color");
            Color col = new Color(Integer.valueOf(co.split(",")[1]), Integer.valueOf(co.split(",")[2]), Integer.valueOf(co.split(",")[3]), 180);
            //Color colwhite=new Color(Integer.valueOf(co.split(",")[1]),Integer.valueOf(co.split(",")[2]),Integer.valueOf(co.split(",")[3]),0);
            Color colwhite = new Color(255, 255, 255);

            for (Iterator<Element> it1 = element.elementIterator(); it1.hasNext(); ) {
                Element element1 = it1.next();
//	            	System.out.println(element1.getTextTrim());
                String[] latlonArr = element1.getTextTrim().split(" ");
//	            	if(a.equals("")) {
//	            		latlonArr=element1.getTextTrim().split(" ");
//	            	}else {
//	            		String str = checkRepeat(a, element1.getTextTrim());
//	            		latlonArr = element1.getTextTrim().replaceAll(str, "").split(" ");
//	            	}
                int px[] = new int[latlonArr.length];
                int py[] = new int[latlonArr.length];

                Point2D lonLat = new Point2D.Float();
                if (index > 1) {
                    index--;
                    break;
                }
                for (int i = 0; i < latlonArr.length; i++) {

                    lonLat.setLocation(Double.parseDouble(latlonArr[i].split(",")[0]), Double.parseDouble(latlonArr[i].split(",")[1]));
                    Point2D drawPoint = getDrawLocation(imgWidth, imgHeight, leftTop, rightBottom, lonLat);
                    px[i] = (int) drawPoint.getX();
                    py[i] = (int) drawPoint.getY();

                }

                g2d.setColor(colwhite);
                g2d.fillPolygon(px, py, latlonArr.length);


                g2d.setColor(col);
                g2d.fillPolygon(px, py, latlonArr.length);

                g2d.dispose();

//					a = element1.getTextTrim();

            }

        }

        BufferedImage newimg = copyBufferedImage(bimg);
        Graphics2D g2 = newimg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Element content = document.getRootElement().element("Rainbow").element("Content");
        String con = content.getTextTrim();
        String[] cons = con.split("\\s+");
        String title = cons[0];
        System.out.println(title);
        String[] num = new String[(cons.length - 1) / 2];
        String[] color = new String[(cons.length - 1) / 2];
        for (int x = 0; x < (cons.length - 1) / 2; x++) {
            num[x] = cons[2 * x + 1];
            color[x] = cons[2 * x + 2];
        }
        Point2D lon = new Point2D.Float();
        String[] latlon = new String[5];
        for (int x = 0; x < color.length; x++) {
            latlon[0] = String.valueOf(Double.parseDouble("121.477")) + "," + String.valueOf(Double.parseDouble("28.490") + (double) x * 0.011);//色标正方形的4个角的经纬度
            latlon[1] = String.valueOf(Double.parseDouble("121.477")) + "," + String.valueOf(Double.parseDouble("28.481") + (double) x * 0.011);
            latlon[2] = String.valueOf(Double.parseDouble("121.489")) + "," + String.valueOf(Double.parseDouble("28.481") + (double) x * 0.011);
            latlon[3] = String.valueOf(Double.parseDouble("121.489")) + "," + String.valueOf(Double.parseDouble("28.490") + (double) x * 0.011);
            latlon[4] = String.valueOf(Double.parseDouble("121.477")) + "," + String.valueOf(Double.parseDouble("28.490") + (double) x * 0.011);
            Color col = new Color(Integer.valueOf(color[x].split(",")[1]), Integer.valueOf(color[x].split(",")[2]), Integer.valueOf(color[x].split(",")[3]), 255);
            int px[] = new int[latlon.length];
            int py[] = new int[latlon.length];
            for (int i = 0; i < latlon.length; i++) {
                lon.setLocation(Double.parseDouble(latlon[i].split(",")[0]), Double.parseDouble(latlon[i].split(",")[1]));
                Point2D drawPoint = getDrawLocation(imgWidth, imgHeight, leftTop, rightBottom, lon);
                px[i] = (int) drawPoint.getX() - 4;
                py[i] = (int) drawPoint.getY() - 4;

            }
            g2.setColor(col);
            g2.fillPolygon(px, py, latlon.length);

        }

        Graphics2D g3 = newimg.createGraphics();
        Point2D numLon = new Point2D.Float();
        for (int x = 0; x < color.length; x++) {
            String numLatLon = String.valueOf(Double.parseDouble("121.497")) + "," + String.valueOf(Double.parseDouble("28.483") + (double) x * 0.011);//色标的值
            numLon.setLocation(Double.parseDouble(numLatLon.split(",")[0]), Double.parseDouble(numLatLon.split(",")[1]));

            Point2D numPoint = getDrawLocation(imgWidth, imgHeight, leftTop, rightBottom, numLon);
            g3.setColor(Color.BLACK);
            g3.drawString(num[x], (int) numPoint.getX() - 4, (int) numPoint.getY() - 4);

            if (x == color.length - 1) {
                String titleLatLon = String.valueOf(Double.parseDouble("121.477")) + "," + String.valueOf(Double.parseDouble("28.483") + (double) (x + 1) * 0.011);//色标的标题
                numLon.setLocation(Double.parseDouble(titleLatLon.split(",")[0]), Double.parseDouble(titleLatLon.split(",")[1]));
                Point2D titlePoint = getDrawLocation(imgWidth, imgHeight, leftTop, rightBottom, numLon);
                g3.drawString(title, (int) titlePoint.getX() - 4, (int) titlePoint.getY() - 4);
            }
        }

        String fileName = UUID.randomUUID().toString();
        ImageIO.write(newimg, "png", new File(tempDir + "\\" + fileName + ".png"));
        tempImgs = tempDir + "\\" + fileName + ".png";

        return tempImgs;
    }

    /**
     * @Author: BaoLongHui
     * @Description 根据经纬度获取该点在图片上的位置
     * @Date 14:45 2018/4/27
     */
    private Point2D getDrawLocation(int imgWidth, int imgHeight, Point2D leftTop, Point2D rightBottom, Point2D yourLocation) {
//         Point2D location=GISHelper.location2Mercator(yourLocation);
        Point2D result = new Point2D.Float();
        Double x = ((yourLocation.getX() - leftTop.getX()) / (rightBottom.getX() - leftTop.getX())) * imgWidth;
        Double y = ((yourLocation.getY() - leftTop.getY()) / (rightBottom.getY() - leftTop.getY())) * imgHeight;
        result.setLocation(x, y);
        return result;
    }

    /**
     * @Author: BaoLongHui
     * @Description 克隆一个BufferedImage对象
     * @Date 9:29 2018/4/28
     */
    public BufferedImage copyBufferedImage(BufferedImage bimage) {
        BufferedImage bimage2 = new BufferedImage(bimage.getWidth(), bimage.getHeight(), bimage.getType());
        bimage2.setData(bimage.getData());
        return bimage2;

    }

    /**
     * 以utf-8的编码读取文件内容
     *
     * @param filePath
     * @return
     */
    public static String readFileToString(String filePath) {
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


    public static String checkRepeat(String str1, String str2) {
        String[] arr1 = str1.split(" ");
        String[] arr2 = str2.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if (arr1[j].equals(arr2[i])) {
                    sb.append(arr1[j] + "");
                }
            }
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    public String exportMap(String parm, String rootPath) throws Exception {

        String tempImgs = "";
        String tempDir = ConfigHelper.getProperty("tempDir2");//存放路径
        File dir = new File(tempDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String BaseMapPath = ConfigHelper.getProperty("BaseMapPath2");
        String MapRange = ConfigHelper.getProperty("MapRange2");
        String[] points = MapRange.split("\\|");
        //底图左上角经纬度
        Point2D leftTop = new Point2D.Float();
        leftTop.setLocation(Float.parseFloat(points[0].split(",")[0]), Float.parseFloat(points[0].split(",")[1]));

        //底图右下角经纬度
        Point2D rightBottom = new Point2D.Float();
        rightBottom.setLocation(Float.parseFloat(points[1].split(",")[0]), Float.parseFloat(points[1].split(",")[1]));

        File picture = new File(BaseMapPath);

        BufferedImage bimgFin = ImageIO.read(new FileInputStream(picture));
        int imgWidthFin = bimgFin.getWidth();
        int imgHeightFin = bimgFin.getHeight();

        String fileNameFin = UUID.randomUUID().toString();
        ImageUtils.fromFile(picture)
                .size(imgWidthFin * 2, imgHeightFin * 2)
                .quality(0.6f)
                .fixedGivenSize(true)
                .keepRatio(true)
                .bgcolor(null)    //透明背景
                .toFile(new File(tempDir + "\\" + fileNameFin + ".png"));
        String newPathFin = tempDir + "\\" + fileNameFin + ".png";
        BufferedImage bimg = ImageIO.read(new FileInputStream(new File(newPathFin)));
        int imgWidth = bimg.getWidth();
        int imgHeight = bimg.getHeight();

        Graphics2D g2d = bimg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        JSONArray jsonArray = JSON.parseArray(parm);
        List<PointUtil> list = new ArrayList<>();
        Point2D lonLat = new Point2D.Float();
        List<String> listIcon = new ArrayList();

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String icon = jsonObject.getString("icon");
            String lat = jsonObject.getString("lat");
            String lon = jsonObject.getString("lon");
            lonLat.setLocation(Double.parseDouble(lon), Double.parseDouble(lat));
            Point2D drawPoint = getDrawLocation(imgWidth, imgHeight, leftTop, rightBottom, lonLat);
            //计算偏移量
            int x = (int) drawPoint.getX() - 34;
            int y = (int) drawPoint.getY() - 38;
            //路径获取图片
            String path = rootPath + "static2\\emergencyImages\\" + icon + ".png";
            BufferedImage img1 = ImageIO.read(new File(path));
//			File orgPng = new File(path);
//			String fileName =UUID.randomUUID().toString();
//			ImageUtils.fromFile(orgPng)
//					.size(48, 50)
//					.quality(0.6f)
//					.fixedGivenSize(true)
//					.keepRatio(true)
//					.bgcolor(null)	//透明背景
//					.toFile(new File(tempDir+"\\"+fileName+".png"));
//			String newPath = tempDir+"\\"+fileName+".png";
//			BufferedImage img1 = ImageIO.read(new File(newPath));

            g2d.drawImage(img1, x, y, img1.getWidth(), img1.getHeight(), null);
//			File file = new File(newPath);
//			file.delete();

            String pointList = jsonObject.getString("pointList");
            if (pointList != null) {
                String[] linePoint = pointList.split(",");
                Point2D lonLat2 = new Point2D.Float();
                Point2D lonLat3 = new Point2D.Float();
                lonLat2.setLocation(Double.parseDouble(linePoint[0]), Double.parseDouble(linePoint[1]));
                lonLat3.setLocation(Double.parseDouble(linePoint[2]), Double.parseDouble(linePoint[3]));
                Point2D drawPoint2 = getDrawLocation(imgWidth, imgHeight, leftTop, rightBottom, lonLat2);
                Point2D drawPoint3 = getDrawLocation(imgWidth, imgHeight, leftTop, rightBottom, lonLat3);
                g2d.setColor(Color.blue);
                Stroke stroke =  g2d.getStroke();
                g2d.setStroke(new BasicStroke(4f));
                g2d.drawLine((int) drawPoint2.getX(), (int) drawPoint2.getY(), (int) drawPoint3.getX(), (int) drawPoint3.getY());
            }

            if (listIcon.size() == 0) {
                listIcon.add(icon);
            }
            for (int j = 0; j < listIcon.size(); j++) {
                if (!listIcon.contains(icon)) {
                    listIcon.add(icon);
                }
            }

        }


        g2d.setColor(Color.white);
        g2d.fillRect(1750 * 2, 998 * 2 - 60 * listIcon.size(), 180, 60 * listIcon.size());
        String point_icon = ConfigHelper.getProperty("point_icon2");
        Map<String, Object> map = JSON.parseObject(point_icon);
        for (int i = 0; i < listIcon.size(); i++) {

            String path = rootPath + "static2\\emergencyImages\\" + listIcon.get(i) + ".png";
            BufferedImage img1 = ImageIO.read(new File(path));

//			File orgPng = new File(path);
//			String fileName =UUID.randomUUID().toString();
//			ImageUtils.fromFile(orgPng)
//					.size(48, 50)
//					.quality(0.6f)
//					.fixedGivenSize(true)
//					.keepRatio(true)
//					.bgcolor(null)	//透明背景
//					.toFile(new File(tempDir+"\\"+fileName+".png"));
//			String newPath = tempDir+"\\"+fileName+".png";
//			BufferedImage img1 = ImageIO.read(new File(newPath));

            g2d.drawImage(img1, 1760 * 2 - 30, 1018 * 2 - 60 * listIcon.size() + 60 * i - 50, img1.getWidth(), img1.getHeight(), null);

            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("微软雅黑", Font.BOLD, 24));
            String write = map.get(listIcon.get(i)).toString();
            g2d.drawString(write, 1780 * 2, 1018 * 2 - 60 * listIcon.size() + 60 * i);
//			File file = new File(newPath);
//			file.delete();
        }

        g2d.dispose();

        String fileName = UUID.randomUUID().toString();
        ImageIO.write(bimg, "png", new File(tempDir + "\\" + fileName + ".png"));
        tempImgs = tempDir + "\\" + fileName + ".png";

        File file = new File(newPathFin);
        file.delete();
        return tempImgs;
    }

}
