package cn.movinginfo.tztf.sen.action;


import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.sen.model.Point;
import cn.movinginfo.tztf.sen.model.SimpleTyphoonItem;
import cn.movinginfo.tztf.sen.model.TyphoonInfoDetail;
import cn.movinginfo.tztf.sen.model.TyphoonMenu;
import cn.movinginfo.tztf.sen.service.TyphoonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.geom.Point2D;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author: baolonghui
 * @Description
 * @Data Created in 14:47 2018/4/18
 * @Modified By:
 */
@Controller
@RequestMapping("/hnqx/typhoon")
public class TyphoonAction {

    private static final Logger log = LoggerFactory.getLogger(TyphoonAction.class);

    @Autowired
    private TyphoonService typhoonService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index()
    {
        return "/sen/typhoon/typhoon";
    }

    /**
     * @Author: BaoLongHui
     * @Description 当前是否有台风,如果有直接返回当前台风列表,没有返回null或者空列表
     * @Date 17:25 2018/4/18
     */
    @RequestMapping("haveTyphoon")
    @ResponseBody
    public List<TyphoonInfoDetail> haveTyphoon()
    {
        try {
            return typhoonService.haveTyphoon();
        }
        catch (Exception ex)
        {
            log.error(ex.toString());
            return null;
        }
    }
    /**
     * @Author: BaoLongHui
     * @Description 根据台风编号获取多家模式预报的名称
     * @Date 15:11 2018/4/28
     */
    @RequestMapping("getPublisher")
    @ResponseBody
    public List<String> getPublisher(String bianhao)
    {
        try {
            return typhoonService.getPublisher(bianhao);
        }
        catch (Exception ex)
        {
            log.error(ex.toString());
            return null;
        }
    }

    /**
    * @Author: BaoLongHui
    * @Description 获得所有发布机构
    * @Date 16:59 2018/6/25
    */
    @RequestMapping("getAllPublisher")
    @ResponseBody
    public List<String> getAllPublisher()
    {
        try {
            return typhoonService.getAllPublisher();
        }
        catch (Exception ex)
        {
            log.error(ex.toString());
            return null;
        }
    }

    /**
     * @Author: BaoLongHui
     * @Description 根据搜索条件获取台风列表
     * @Date 17:27 2018/4/18
     */
    @RequestMapping("getSearchTyphoons")
    @ResponseBody
    public List<SimpleTyphoonItem> getSearchTyphoons(Integer year, String keyWords)
    {
        try {
            return typhoonService.getSearchTyphoons(year,keyWords);
        }
        catch (Exception ex)
        {
            log.error(ex.toString());
            return null;
        }
    }

    /**
     * @Author: BaoLongHui
     * @Description 根据台风编号获取台风具体路径信息
     * @Date 17:28 2018/4/18
     */
    @RequestMapping("getTyphoonMapInfo")
    @ResponseBody
    public TyphoonInfoDetail getTyphoonMapInfo(String bianhao)
    {
        try {
            return typhoonService.getTyphoonInfoDetailByNo(bianhao);
        }
        catch (Exception ex)
        {
            log.error(ex.toString());
            return null;
        }
    }

    /**
     * @Author: BaoLongHui
     * @Description 获取历史上路径通过此区域的全部台风列表
     * @Date 17:04 2018/4/24
     */
    @RequestMapping("getTyphoonCrossArea")
    @ResponseBody
    public List<SimpleTyphoonItem> getTyphoonCrossArea(String pointstring)
    {
        try {
            List<Point> points=JSON.parseArray(pointstring,Point.class) ;
            return typhoonService.getTyphoonCrossArea(points);
        }
        catch (Exception ex)
        {
            log.error(ex.toString());
            ex.printStackTrace();
            return null;
        }
    }
    /**
     * @Author: BaoLongHui
     * @Description 获取海宁国家站的经纬度坐标
     * @Date 16:15 2018/4/26
     */
    @RequestMapping("getHaiNingLocation")
    @ResponseBody
    public Point2D getHaiNingLocation()
    {
        try {
            return typhoonService.getHaiNingLocation();
        }
        catch (Exception ex)
        {
            log.error(ex.toString());
            return null;
        }
    }
    /**
     * @Author: BaoLongHui
     * @Description 导出台风动态图
     * @Date 16:16 2018/4/26
     */
    @RequestMapping("exportTyphoonGif")
    @ResponseBody
    public void exportTyphoonGif(HttpServletRequest request, HttpServletResponse response, String bianhao)
    {
        try {
            byte[] bytes=typhoonService.exportTyphoonGif(request,response,bianhao);
            String gifName="typhoon_"+bianhao+".gif";
            response.setContentType("image/gif");
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(gifName,"UTF-8"));
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.close();
        }
        catch (Exception ex)
        {
            log.error(ex.toString());

        }
    }

    /**
    * @Author: BaoLongHui
    * @Description 获得在台风页面上叠加的要素的菜单
    * @Date 13:44 2018/5/4
    */
    @RequestMapping("getTyphoonMenu")
    @ResponseBody
    public List<TyphoonMenu> getTyphoonMenu()
    {
        try {
            return typhoonService.getTyphoonMenu();
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
            log.error(ex.toString());
            return null;
        }
    }

    /**
    * @Author: BaoLongHui
    * @Description 传入两个点，返回两点连成的直线外扩50KM的矩形区域的4个点
    * @Date 11:39 2018/6/28
    */
    @RequestMapping("getSelectArea")
    @ResponseBody
    public List<Point> getSelectArea(String pointstring)
    {
        try {
            List<Point> points=JSON.parseArray(pointstring,Point.class) ;
            return typhoonService.getSelectArea(points.get(0),points.get(1));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return  null;
        }

    }
}
