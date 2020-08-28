package cn.movinginfo.tztf.sen.service;

import com.alibaba.fastjson.JSONArray;

import cn.movinginfo.tztf.common.typhoon.ConfigHelper;
import cn.movinginfo.tztf.common.typhoon.GISHelper;
import cn.movinginfo.tztf.common.typhoon.TyphoonUtil;
import cn.movinginfo.tztf.sen.mapper.TyphoonMapper;
import cn.movinginfo.tztf.sen.model.Point;
import cn.movinginfo.tztf.sen.model.SimpleTyphoonItem;
import cn.movinginfo.tztf.sen.model.TyphoonInfoDetail;
import cn.movinginfo.tztf.sen.model.TyphoonInfoItem;
import cn.movinginfo.tztf.sen.model.TyphoonMenu;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: baolonghui
 * @Description
 * @Data Created in 16:23 2018/4/18
 * @Modified By:
 */
@Service
public class TyphoonService {

    private static final Logger log = LoggerFactory.getLogger(TyphoonService.class);
    private static String localPath=ConfigHelper.getProperty("typhoonLocalPath");
    @Autowired
    private TyphoonTask typhoonTask;
    @Autowired
    private TyphoonMapper typhoonMapper;

    /**
     * @Author: BaoLongHui
     * @Description 当前是否有台风
     * @Date 16:32 2018/4/18
     */
    public List<TyphoonInfoDetail> haveTyphoon()throws Exception
    {
        List<TyphoonInfoDetail> result=new ArrayList<>();
        List<SimpleTyphoonItem> typhoonItemList=getAllTyphoonItem();
        for (SimpleTyphoonItem item:typhoonItemList
                ) {
            if(item.getHistory()==false)
            {
                TyphoonInfoDetail typhoonInfoItem=getTyphoonInfoDetailByNo(item.getNO());
                if(typhoonInfoItem!=null) {
                    result.add(typhoonInfoItem);
                }
            }
        }
        return result;
    }
    /**
    * @Author: BaoLongHui
    * @Description 获得台风详细信息
    * @Date 9:43 2018/6/22
    */
    public TyphoonInfoDetail getTyphoonInfoDetailByNo(String bianhao)throws Exception
    {
        String fileName=localPath+"\\typhoonInfo_"+bianhao+".json";
        File file=new File(fileName);
        if(file.exists())
        {
            String content=readToString(fileName);
            TyphoonInfoDetail detail=JSONArray.parseObject(content,TyphoonInfoDetail.class);
            return detail;
        }
        else
        {
            return null;
        }
    }
    /**
     * @Author: BaoLongHui
     * @Description 根据编号获得该台风的所有发布者
     * @Date 18:36 2018/4/28
     */
    public List<String> getPublisher(String bianhao)
    {
        return typhoonTask.getPublisher(bianhao);
    }

    public List<String> getAllPublisher() throws Exception
    {
        return typhoonTask.getAllPublisher();
    }

    /**
     * @Author: BaoLongHui
     * @Description 根据条件获取台风列表(简易列表)
     * @Date 16:13 2018/4/24
     */
    public List<SimpleTyphoonItem> getSearchTyphoons(Integer year,String keyWords)throws Exception
    {
        List<SimpleTyphoonItem> typhoonInfoList= getAllTyphoonItem();
        if(year!=null)
        {
            typhoonInfoList=typhoonInfoList.stream().filter(t->Integer.parseInt( t.getNO().substring(0,4))==year).collect(Collectors.toList());
        }
        if(keyWords!=null&&!keyWords.equals(""))
        {
            typhoonInfoList=typhoonInfoList.stream().filter(t-> t.getNameCn().contains(keyWords)).collect(Collectors.toList());
        }
        return typhoonInfoList;
    }

    /**
     * @Author: BaoLongHui
     * @Description 获取海宁国家站的经纬度
     * @Date 14:18 2018/4/26
     */
    public Point2D getHaiNingLocation()
    {
       return typhoonTask.getHaiNingLocation();
    }

    /**
     * @Author: BaoLongHui
     * @Description 获得经过区域内的所有台风列表(简易列表)
     * @Date 14:18 2018/4/26
     */
    public List<SimpleTyphoonItem> getTyphoonCrossArea(List<Point> points) throws Exception
    {
        List<SimpleTyphoonItem> result=new ArrayList<SimpleTyphoonItem>();
        List<SimpleTyphoonItem> allTyphoonList= getAllTyphoonItem();
        for (SimpleTyphoonItem item:allTyphoonList
                ) {
            TyphoonInfoDetail typhoonMapInfo=getTyphoonInfoDetailByNo(item.getNO());
            if(typhoonMapInfo==null)
            {
                continue;
            }
            List<Point> typhoonPath=new ArrayList<Point>();
            for (TyphoonInfoItem typhoonInfoItem:typhoonMapInfo.getTyphoonInfoItems()
                    ) {
                try {
                    double x = Double.parseDouble(typhoonInfoItem.getLon());
                    double y = Double.parseDouble(typhoonInfoItem.getLat());
                    Point point = new Point(x, y);
                    typhoonPath.add(point);
                }
                catch (Exception ex)
                {
                    continue;
                }
            }
            if(TyphoonUtil.isIntersact(points.get(0),points.get(1),typhoonPath))
            {
                result.add(item);
                continue;
            }
            if(TyphoonUtil.isIntersact(points.get(1),points.get(2),typhoonPath))
            {
                result.add(item);
                continue;
            }
            if(TyphoonUtil.isIntersact(points.get(2),points.get(3),typhoonPath))
            {
                result.add(item);
                continue;
            }
            if(TyphoonUtil.isIntersact(points.get(3),points.get(0),typhoonPath))
            {
                result.add(item);
                continue;
            }
        }
        return result;
    }

    //直线两边的扩展距离,50KM
    final static double distance=50;

    public List<Point> getSelectArea(Point p1,Point p2) throws Exception
    {
        List<Point> result=new ArrayList<>();
        Point2D p1_lonLat=new Point2D.Float();
        p1_lonLat.setLocation(p1.getX(),p1.getY());
        Point2D p1_mercator= GISHelper.location2Mercator(p1_lonLat);
        Point2D p2_lonLat=new Point2D.Float();
        p2_lonLat.setLocation(p2.getX(),p2.getY());
        Point2D p2_mercator= GISHelper.location2Mercator(p2_lonLat);
        //通过正切比计算角度
        double angle= Math.atan((p2_mercator.getY()-p1_mercator.getY())/(p2_mercator.getX()-p1_mercator.getX()));

        //Y轴方向的横移距离(km)
        double y=distance*Math.cos(angle);
        //X轴方向的横移距离(km)
        double x=distance*Math.sin(angle);

        double x_lonlat=x/TyphoonTask.LON_INTERVAL;
        double y_lonlat=y/TyphoonTask.LON_INTERVAL;

        result.add(new Point(p1.getX()+x_lonlat,p1.getY()-y_lonlat));
        result.add(new Point(p1.getX()-x_lonlat,p1.getY()+y_lonlat));

        result.add(new Point(p2.getX()-x_lonlat,p2.getY()+y_lonlat));
        result.add(new Point(p2.getX()+x_lonlat,p2.getY()-y_lonlat));

        return result;
    }

    /**
    * @Author: BaoLongHui
    * @Description 导出台风GIF
    * @Date 9:35 2018/5/2
    */
    public byte[] exportTyphoonGif(HttpServletRequest request, HttpServletResponse response, String bianhao) throws Exception
    {
        return typhoonTask.exportTyphoonGif(request,response,bianhao);
    }

    public List<TyphoonMenu> getTyphoonMenu()  throws Exception
    {
        List<TyphoonMenu> result=typhoonMapper.selectAll();
        return result;
    }

    private  List<SimpleTyphoonItem> getAllTyphoonItem() throws Exception
    {
        String typhoonFullFilePath= localPath+"\\"+"typhoonItems.xml";
        File file=new File(typhoonFullFilePath);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(file);
            Element bookstore = document.getRootElement();
            Iterator storeit = bookstore.elementIterator();

            List<SimpleTyphoonItem> typhoonItemList = new ArrayList<SimpleTyphoonItem>();
            while(storeit.hasNext()){

                SimpleTyphoonItem item = new SimpleTyphoonItem();
                Element bookElement = (Element) storeit.next();

                Iterator bookit = bookElement.elementIterator();
                while(bookit.hasNext()){
                    Element child = (Element) bookit.next();
                    String nodeName = child.getName();
                    if(nodeName.equals("IsSelected")){
                        //System.out.println(child.getStringValue());
                        String isSelected = child.getStringValue();
                        item.setSelected(Boolean.valueOf(isSelected));
                    }else if(nodeName.equals("NO")){
                        String NO = child.getStringValue();
                        item.setNO(NO);
                    }else if(nodeName.equals("NameEn")){
                        String NameEn = child.getStringValue();
                        item.setNameEn(NameEn);
                    }else if(nodeName.equals("NameCn")){
                        String NameCn = child.getStringValue();
                        item.setNameCn(NameCn);
                    }
                    else if(nodeName.equals("IsHistory")){
                        String IsHistory = child.getStringValue();
                        item.setHistory(Boolean.valueOf(IsHistory));
                    }
                }
                typhoonItemList.add(item);
                item = null;
            }

            return  typhoonItemList;
        }
        catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String readToString(String fileName)throws Exception
    {
        String encoding="UTF-8";
        File file=new File(fileName);
        long filelength=file.length();
        byte[] filecontent=new byte[(int)filelength];
        try {
            FileInputStream in=new FileInputStream(file);
            in.read(filecontent);
            in.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            return new String(filecontent,encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
                return null;
        }
    }

}
