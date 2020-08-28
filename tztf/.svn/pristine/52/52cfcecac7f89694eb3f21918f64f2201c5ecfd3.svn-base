package cn.movinginfo.tztf.sen.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.common.utils.FileUtil;
import cn.movinginfo.tztf.common.utils.GetXml;
import cn.movinginfo.tztf.sen.domain.DangerPoint;
import cn.movinginfo.tztf.sen.domain.FileName;
import cn.movinginfo.tztf.sen.domain.Menu;
import cn.movinginfo.tztf.sen.domain.Station;
import cn.movinginfo.tztf.sen.service.AlertruleService;
import cn.movinginfo.tztf.sen.service.DangerPointService;
import cn.movinginfo.tztf.sen.service.ExportGisService;
import cn.movinginfo.tztf.sen.service.MenuService;

@Controller
@RequestMapping("/sen/rainstormFlood")
public class RainstormFloodAction {

    @Autowired
    private DangerPointService dangerPointService;

    @Autowired
    private AlertruleService alertruleService;

    @Autowired
    private ExportGisService exportGisService;

    @Autowired
    private MenuService menuService;

    public static void main(String[] args) {
        String color_path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\config\\Rainbow\\").substring(6);
    }

    /**
     * 计算面雨量获取时间轴
     *
     * @param type    二级菜单
     * @param lowType 三级菜单
     * @return 数组
     */
    @RequestMapping(value = "/getTimeList")
    @ResponseBody
    public List<FileName> getTimeList(String type, String lowType) {
        List<FileName> result = new ArrayList<>();
        //获取时间轴天数
        String rainfallDay = ConfigHelper.getProperty("rainstorm_flood");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -Integer.parseInt(rainfallDay));
        //时间轴开始时间
        Date startDay = c.getTime();
        //读取文件路径
//        String path = ConfigHelper.getProperty("rainstorm_path") + "\\" + lowType.replace("h", "hour");

        List<Menu> menuList = menuService.getMenuByType(type);
        List<Menu> menus = menuService.getMenuByType(lowType);
        String title = "";
        String path = "";
        if (menuList.size() != 0) {
            Menu menu = menuList.get(0);
            title = menu.getName();

        }
        if (menus.size() != 0) {
            Menu menu = menus.get(0);
            title += menu.getName();
            path = menu.getFileURL();
        }
        File files = new File(path);
        File[] tempList = files.listFiles();


        //按照文件名正序排序
        assert tempList != null;
        Arrays.sort(tempList, Comparator.comparing(File::getName));
        for (File file : tempList) {
            if (file.isFile()) {
                String time = file.getName().split("\\.")[0];
                Date date = DateUtil.parse(time, "yyyyMMddHHmm");
                if (date.getTime() > startDay.getTime()) {
                    String now = DateUtil.format(date, "yyyy年MM月dd日 HH时mm分");
                    FileName fileName = new FileName();
                    fileName.setFileName(file.getName());
                    fileName.setPath(file.getPath());
                    fileName.setTime(now);
                    fileName.setTitle(now + title);
                    result.add(fileName);
                }
            }
        }
        return result;
    }

    /**
     * 获取面雨量以及填色图色标
     *
     * @return string
     */
    @RequestMapping(value = "/getRainXml")
    @ResponseBody
    public String getRainfall(String path) {
        String result = "";
        try {
            String color_path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\config\\Rainbow\\").substring(6);
            if (path.contains("1hour_R") || path.contains("Pr01")) {
                color_path += "high_rain1h.clv";
            } else if (path.contains("3hour_R") || path.contains("Pr03")) {
                color_path += "high_rain3h.clv";
            } else if (path.contains("24hour_R") || path.contains("Pr24")) {
                color_path += "high_rain24h.clv";
            }

            String desc = FileUtil.readFileToString(color_path.replace("%20", " "));

            result = GetXml.getXML(path, desc, "强降水落区");
//		      exportGisService.exportGisGif(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取自动站一小时的最新记录
     *
     * @return
     */
    @RequestMapping(value = "/getStationData")
    @ResponseBody
    public List<Station> getStationData(String path) {
        List<Station> list = menuService.readTxtFile(path);
        list = isAlarmStation(list);
        return list;

    }

    //判断是否超阈值
    private List<Station> isAlarmStation(List<Station> list) {
        DangerPoint dp = dangerPointService.getValueByType("9");
        double val = Double.valueOf(alertruleService.get(Long.valueOf(dp.getAlertruleId())).getThresholdValue().split(",")[0]);

        for (Station s : list) {
            if (Double.valueOf(s.getValue()) >= val) {
                s.setAlarm(true);
            } else {
                s.setAlarm(false);
            }
        }
        return list;
    }

    /**
     * 计算一小时降水获取时间轴
     *
     * @param type 二级菜单
     * @return 数组
     */
    @RequestMapping(value = "/getSTimeList")
    @ResponseBody
//    public List<FileName> getSTimeList(String type, String lowType) {
    public List<FileName> getSTimeList(String type, String lowType) {
        List<FileName> result = new ArrayList<>();
        //获取时间轴天数
        String rainfallDay = ConfigHelper.getProperty("rainstorm_flood");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -Integer.parseInt(rainfallDay));
        //时间轴开始时间
        Date startDay = c.getTime();
        //读取文件路径
//        String path = ConfigHelper.getProperty("rainstorm_path") + "\\" + lowType.replace("h", "hour");

        List<Menu> menus = menuService.getMenuByType(lowType);
        String title = "";
        String path = "";
        if (menus.size() != 0) {
            Menu menu = menus.get(0);
            title += menu.getName();
            path = menu.getFileURL();
        }
        File files = new File(path);
        File[] tempList = files.listFiles();
        //按照文件名正序排序
        assert tempList != null;
        Arrays.sort(tempList, Comparator.comparing(File::getName));
        for (File file : tempList) {
            if (file.isFile()) {
                String time = file.getName().split("\\.")[0];
                Date date = DateUtil.parse(time, "yyyyMMddHHmm");
                if (date.getTime() > startDay.getTime()) {
                    String now = DateUtil.format(date, "yyyy年MM月dd日 HH时mm分");
                    FileName fileName = new FileName();
                    fileName.setFileName(file.getName());
                    fileName.setPath(file.getPath());
                    fileName.setTime(now);
                    fileName.setTitle(now + title);
                    result.add(fileName);
                }
            }
        }
        return result;
    }

    /**
     * 导出图片
     *
     * @param request
     * @param response
     * @param path
     * @throws Exception
     */
    @RequestMapping(value = "getImage")
    public void getImage(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String imagePath = "";
        String parm = request.getParameter("parm");
        if (parm != null) {
            //进入应急导出
            imagePath = exportGisService.exportMap(parm, rootPath);
        } else {
            String result = "";
            String name = "";
            try {
                if (path.contains("Vis") || path.contains("TMax24")) {
                    result = menuService.readFileToString(path);
//    			System.out.println(result);
                } else {
                    String color_path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\config\\Rainbow\\").substring(6);
                    if (path.contains("1hour_R") || path.contains("Pr01")) {
                        color_path += "high_rain1h.clv";
                        name = "强降水落区";
                    } else if (path.contains("3hour_R") || path.contains("Pr03")) {
                        color_path += "high_rain3h.clv";
                        name = "强降水落区";
                    } else if (path.contains("24hour_R") || path.contains("Pr24")) {
                        color_path += "high_rain24h.clv";
                        name = "强降水落区";
                    } else if (path.contains("1min_WindDV10min") || path.contains("WindMean")) {
                        color_path += "high_wind.clv";
                        name = "大风落区(风力6级以上)数据";
                    }

                    String desc = FileUtil.readFileToString(color_path.replace("%20", " "));
                    result = GetXml.getXML(path, desc, name);
                }


//		      exportGisService.exportGisGif(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            imagePath = exportGisService.exportGisGif(result);
        }
        //String imagePath = "E:\\hydata\\Rain\\222.png";
        FileInputStream inputStream = null;
        response.reset();
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            SimpleDateFormat newsdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = newsdf.format(new Date());
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + new String(("导出-图片" + date + ".png").getBytes("Utf-8"),
                    "ISO8859_1") + "\"");
            inputStream = new FileInputStream(imagePath);
            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);

            inputStream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        }
    }


    /**
     * 导出图片
     *
     * @param request
     * @param response
     * @param path
     * @throws Exception
     */
    @RequestMapping(value = "getImage2")
    @ResponseBody
    public Map<String,String> getImage2(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {
        Map<String,String> map = new HashMap<>();
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        String parm = request.getParameter("parm");

        //进入应急导出
        String imagePath = exportGisService.exportMap(parm, rootPath);
        if (imagePath != null) {
            map.put("status","1");
            map.put("imagePath",imagePath);
        }else {
            map.put("status","0");
        }
        return map;
    }

    @RequestMapping(value = "export")
    public void export(HttpServletRequest request, HttpServletResponse response, String path) throws Exception {

        String imagePath = request.getParameter("imagePath");
        FileInputStream inputStream = null;
        response.reset();
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            SimpleDateFormat newsdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = newsdf.format(new Date());
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + new String(("导出-图片" + date + ".png").getBytes("Utf-8"),
                    "ISO8859_1") + "\"");
            inputStream = new FileInputStream(imagePath);
            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);

            inputStream.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        }

    }


}
