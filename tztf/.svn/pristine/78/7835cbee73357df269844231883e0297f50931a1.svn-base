package cn.movinginfo.tztf.sen.action;


import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.common.utils.FileUtil;
import cn.movinginfo.tztf.common.utils.GetXml;
import cn.movinginfo.tztf.sen.domain.FileName;
import cn.movinginfo.tztf.sen.domain.Menu;
import cn.movinginfo.tztf.sen.domain.Station;
import cn.movinginfo.tztf.sen.service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/sen/refinement")
public class RefinementAction {

    @Autowired
    private MenuService menuService;
    /**
     * wtitten by qh
     * 获取时间轴
     * @param lowType
     * @return
     */
    @RequestMapping(value="/getJxhTimeList")
    @ResponseBody
    public String getJxhTimeList(String lowType,String reportTime) {
        String flag="false";
        JSONObject jb = new JSONObject();
        String xmlpath = "";
        List<String> beginTimeList = new ArrayList<>();
        if(!lowType.equals("")) {
            switch(lowType){
                case "JXH_rain_3":
                    xmlpath = ConfigHelper.getProperty("JXH_rain_xml").split(",")[0];
                    flag="true";
                    break;
                case "JXH_rain_6":
                    xmlpath = ConfigHelper.getProperty("JXH_rain_xml").split(",")[1];
                    flag="true";
                    break;
                case "JXH_rain_12":
                    xmlpath = ConfigHelper.getProperty("JXH_rain_xml").split(",")[2];
                    flag="true";
                    break;
                case "JXH_temperature":
                    xmlpath = ConfigHelper.getProperty("JXH_temperature_xml");
                    flag="true";
                    break;
                case "JXH_wind":
                    xmlpath = ConfigHelper.getProperty("JXH_wind_xml");
                    flag="true";
                    break;
                case "JXH_seeing":
                    xmlpath = ConfigHelper.getProperty("JXH_seeing_xml");
                    flag="true";
                    break;
                case "OCF_rain_3":
                    //xmlpath = ConfigHelper.getProperty("OCF_rain_xml");
                    xmlpath = ConfigHelper.getProperty("OCF_rain_xml").split(",")[0];
                    break;
                case "OCF_rain_6":
                    xmlpath = ConfigHelper.getProperty("OCF_rain_xml").split(",")[1];
                    break;
                case "OCF_rain_12":
                    xmlpath = ConfigHelper.getProperty("OCF_rain_xml").split(",")[2];
                    break;
                case "OCF_temperature":
                    xmlpath = ConfigHelper.getProperty("OCF_temperature_xml");
                    break;
                case "OCF_wind":
                    xmlpath = ConfigHelper.getProperty("OCF_wind_xml");
                    break;
                case "OCF_seeing":
                    xmlpath = ConfigHelper.getProperty("OCF_seeing_xml");
                    break;
                default:
                    xmlpath="";
            }

            /*if (lowType.equals("OCF_rain")) {
                xmlpath = ConfigHelper.getProperty("OCF_rain_xml");//OCF模式 降水下的文件
            } else if (lowType.equals("OCF_temperature")) {
                xmlpath = ConfigHelper.getProperty("OCF_temperature_xml");
            } else if (lowType.equals("OCF_wind")) {
                xmlpath = ConfigHelper.getProperty("OCF_wind_xml");
            } else if (lowType.equals("OCF_seeing")) {
                xmlpath = ConfigHelper.getProperty("OCF_seeing_xml");
            } else if (lowType.equals("JXH_rain")) {
                xmlpath = ConfigHelper.getProperty("JXH_rain_xml");
                flag="true";
            } else if (lowType.equals("JXH_temperature")) {
                xmlpath = ConfigHelper.getProperty("JXH_temperature_xml");
                flag="true";
            } else if (lowType.equals("JXH_wind")) {
                xmlpath = ConfigHelper.getProperty("JXH_wind_xml");
                flag="true";
            } else if (lowType.equals("JXH_seeing")) {
                xmlpath = ConfigHelper.getProperty("JXH_seeing_xml");
                flag="true";
            }*/
            //根据lowType 从数据库type字段获取下拉菜单名
            List<Menu> menuList = menuService.getMenuByType(lowType);
            String title = "";
            if (menuList.size() != 0) {
                Menu menu = menuList.get(0);
                title = menu.getName();
            }
            //对文件夹下的需要获取的xml数量做限制
            String monitoring_day = ConfigHelper.getProperty("monitoring_day");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, -Integer.parseInt(monitoring_day));
            Date oldDate = c.getTime();
            //根据前端传过来的参数 确实要读取服务器的xml文件路径 然后获取该文件夹下的文件
            File file = new File(xmlpath);
            List<FileName> fileList = new ArrayList<>();
            //获取文件夹下的xml文件
            File[] tempList = file.listFiles();
            if(flag.equals("false")) {
                 beginTimeList = this.getBeginTime(tempList, reportTime);
            }
            // 按照文件名称正序排序
            assert tempList != null;
            Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
            //默认显示最新预报时间的时间轴
            String begintime = tempList[0].getName().substring(0, 8);
            //获取前端select框下的起报时间和时次 获取对应的文件名前缀
            if(reportTime!=null  && !reportTime.equals("")){
                //2019072208 转化成文件夹对应文件的文件名形式
                String lastHour=reportTime.replace("-","").substring(8,10);
                lastHour=lastHour.equals("08")?"00":"12";
                begintime=reportTime.replace("-","").substring(2,8)+lastHour;
            }
            //需求变更 OCF模式和精细化模式下的数据 文件名不一样
            if(flag.equals("false")) {
                for (File fi : tempList) {
                    if (fi.isFile()) {
                        //根据起报时间 动态绑定时间轴
                        if (fi.getName().substring(0, 8).equals(begintime)) {
                            //19072212_000_ZJOCF_T_dat 文件名格式这样 预报时间等于20190722日12时 加上8小时  加上000小时 计算日期
                            String time = this.getRealTime(fi.getName().split("_")[0], fi.getName().split("_")[1]);
                            Date date = DateUtil.parse(time, "yyyyMMddHH");
                            if (date.getTime() > oldDate.getTime()) {
                                String now = DateUtil.format(date, "yyyy年MM月dd日HH时");
                                FileName fn = new FileName();
                                fn.setFileName(fi.getName());
                                fn.setXmlPath(xmlpath.replace("\\", "/") + "/" + fi.getName());
                                fn.setPath(fi.getPath());
                                fn.setTime(now);
                                fn.setTitle(now + title);
                                fileList.add(fn);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            //JXH 201908061540_000.xml
            else if(flag.equals("true")){
               //起报时间格式固定了
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                String month = String.format("%2d",calendar.get(Calendar.MONTH)+1).replace(" ","0");
                String date = String.format("%2d",calendar.get(Calendar.DATE)-1).replace(" ","0");
                String beginTime=year+"-"+month+"-"+date+"-"+"20";
                String compareTime=year+month+date+"20"+"00";
                beginTimeList.add(beginTime);
                for(File fi : tempList){
                    String datetime=fi.getName().split("_")[0];
                    if(datetime.compareTo(compareTime)>0){
                        String time = fi.getName().split("_")[0];
                        Date afterDate = DateUtil.parse(time, "yyyyMMddHHmm");
                        if(afterDate.getTime()>oldDate.getTime()) {
                            String now = DateUtil.format(afterDate, "yyyy年MM月dd日 HH时mm分");
                            FileName fn = new FileName();
                            fn.setPath(fi.getPath());
                            fn.setFileName(fi.getName());
                            fn.setTime(now);
                            fn.setTitle(now+title);
                            fn.setXmlPath(xmlpath.replace("\\", "/") + "/" + fi.getName());
                            fn.setPath(fi.getPath());
                            fileList.add(fn);
                        }
                    }
                    else{
                        continue;
                    }
                }
            }
            jb.put("fileList",fileList);
            jb.put("timeList",beginTimeList);
            return jb.toJSONString();
        }
        else{
            return null;
        }

    }


    /**
     * 读取具体的xml文件
     * wtitten by qh
     * @return
     */
    @RequestMapping(value="getRefinementData")
    @ResponseBody
    public String getRefinementData(String xmlPath) {
        if (xmlPath != null && !xmlPath.equals("")) {
            JSONObject jb = new JSONObject();
            String xmlString = menuService.readFileToString(xmlPath);
            jb.put("xml", xmlString);
            return jb.toString();
        } else {
            return null;
        }
    }


    /**
     * @author qh
     * @time 2019-07-30
     * 客观精细化绑定时间轴时 根据文件名得到想要的时间格式
     */
    public  String getRealTime(String first_,String second_){
        //固定需要加上的小时
         String addHour="8";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date nowtime = new Date();
        String formatDate = sdf.format(nowtime);
        //得到年份的前两位
        Object s1=formatDate.subSequence(0,2);
        String realFirst_ =s1+first_;
        Date d = DateUtil.parse(realFirst_, "yyyyMMddHH");
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.HOUR, Integer.parseInt(second_)+Integer.parseInt(addHour));
        Date date = cal.getTime();
        String day = DateUtil.format(date, "yyyyMMddHH");
        return day;
    }

    /**
     * @author qh
     * @time 2019-08-01
     *获取前端select下拉框选择起报时间日期
     */
    public List<String> getBeginTime(File[] tempList,String reportTime){
        // 按照文件名称倒序排序
        assert tempList != null;
        Arrays.sort(tempList, (file1, file2) -> file2.getName().compareTo(file1.getName()));
        System.out.println("..."+tempList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date nowtime = new Date();
        String formatDate = sdf.format(nowtime);
        //得到年份的前两位
        Object s1=formatDate.subSequence(0,2);
        List<String> beginTimeList = new ArrayList<String>();
        String year="";
        String month="";
        String day="";
        int hour=0;
        String hourlater="";
        for(int i=0;i<tempList.length;i++){
            if(i==0){
                 year=s1+tempList[0].getName().substring(0,2)+"-";
                 month=tempList[0].getName().substring(2,4)+"-";
                 day=tempList[0].getName().substring(4,6)+"-";
                 hour=Integer.parseInt(tempList[0].getName().substring(6,8))+8;
                 hourlater= String.format("%2d",hour).replace(" ","0");
                 beginTimeList.add(year+month+day+hourlater);
            }
            else{
                String datebefore=tempList[i-1].getName().substring(0,8);
                String datelater=tempList[i].getName().substring(0,8);
                if(datebefore.equals(datelater)){
                    continue;
                }
                else{
                    year=s1+tempList[i].getName().substring(0,2)+"-";
                    month=tempList[i].getName().substring(2,4)+"-";
                    day=tempList[i].getName().substring(4,6)+"-";
                    hour=Integer.parseInt(tempList[i].getName().substring(6,8))+8;
                    hourlater= String.format("%2d",hour).replace(" ","0");
                    beginTimeList.add(year+month+day+hourlater);
                }
            }
        }
        //将前端select选中的起报日期放在第一位
        if(reportTime!=null && !reportTime.equals("")){
            beginTimeList.remove(reportTime);
            beginTimeList.add(0,reportTime);
        }

        return beginTimeList;
    }



    /**
     * @author  qh
     * @time 2019-08-06
     * 雷雨大风 大风落区 6级以上
     * 绑定时间轴
     */
    @RequestMapping(value = "/getStrongwindArea")
    @ResponseBody
    public String getStrongwindArea(String lowType){
        JSONObject obj = new JSONObject();
        String xmlpath="";
        if (lowType.equals("strongwind_area")) {
            xmlpath = ConfigHelper.getProperty("strongwind_area");//雷雨大风模块的风力等级6级以上
        }else if (lowType.equals("strongwind_area_forecast")) {
        	xmlpath = ConfigHelper.getProperty("strongwind_area_forecast");//预报雷雨大风模块的风力等级6级以上
        }
        //根据lowType 从数据库type字段获取下拉菜单名
        List<Menu> menuList = menuService.getMenuByType(lowType);
        String title = "";
        if (menuList.size() != 0) {
            Menu menu = menuList.get(0);
            title = menu.getName();
        }
        //对文件夹下的需要获取的xml数量做限制
        String monitoring_day = ConfigHelper.getProperty("monitoring_day");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -Integer.parseInt(monitoring_day));
        Date oldDate = c.getTime();
        //根据前端传过来的参数 确实要读取服务器的xml文件路径 然后获取该文件夹下的文件
        File file = new File(xmlpath);
        List<FileName> fileList = new ArrayList<>();
        //获取文件夹下的xml文件
        File[] tempList = file.listFiles();
        // 按照文件名称正序排序
        assert tempList != null;
        Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
        for(File fi : tempList){
            if(fi.isFile()) {
                String filename=fi.getName();
                String time = filename.substring(0,filename.lastIndexOf("."));
                //String time = fi.getName().split(".")[0];
                Date afterDate = DateUtil.parse(time, "yyyyMMddHHmm");
                if (afterDate.getTime() > oldDate.getTime()) {
                    String now = DateUtil.format(afterDate, "yyyy年MM月dd日 HH时mm分");
                    FileName fn = new FileName();
                    fn.setFileName(fi.getName());
                    fn.setTime(now);
                    fn.setTitle(now + title);
                    fn.setPath(xmlpath.replace("\\", "/") + "/" + fi.getName());
                    fileList.add(fn);
                }
            }
        }
        obj.put("fileList",fileList);
        return obj.toJSONString();
    }

    /**
     * @author  qh
     * @time 2019-08-06
     * 根据文件路径 获得具体的的站点数据 并且转化成xml文件
     */
    @RequestMapping(value="/getStrongwindAreaData")
    @ResponseBody
    public String getStrongwindAreaData(String xmlPath) {
        if (xmlPath != null && !xmlPath.equals("")) {
            JSONObject jb = new JSONObject();
            //读取大风的色标系列
            String color_path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\config\\Rainbow\\").substring(6);
            color_path+="high_wind.clv";
            //读取色系内容
            String desc = FileUtil.readFileToString(color_path.replace("%20", " "));
            //工具类 将格点数据 转化成 xml
            String xmlFile= GetXml.getXML(xmlPath,desc,"大风落区(风力6级以上)数据");
            return xmlFile;
        } else {
            return null;
        }
    }


    /**
     * @author qh
     * @time 2019-08-07
     * 自动站风力6级以上 綁定時間軸
     */
    @RequestMapping(value = "/getWindTimeList")
    @ResponseBody
    public String getWindTimeList(String lowType){
        JSONObject obj = new JSONObject();
        String xmlpath="";
        if (lowType.equals("automatic_station")) {
            xmlpath = ConfigHelper.getProperty("wind_rank");//雷雨大风模块的风力等级6级以上
        }
        //根据lowType 从数据库type字段获取下拉菜单名
        List<Menu> menuList = menuService.getMenuByType(lowType);
        String title = "";
        if (menuList.size() != 0) {
            Menu menu = menuList.get(0);
            title = menu.getName();
        }
        //对文件夹下的需要读取的文件数量做限制
        String monitoring_day = ConfigHelper.getProperty("monitoring_day");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -Integer.parseInt(monitoring_day));
        Date oldDate = c.getTime();
        //根据前端传过来的参数 确实要读取服务器的文件路径 然后获取该文件夹下的文件
        File file = new File(xmlpath);
        List<FileName> fileList = new ArrayList<>();
        //获取文件夹下的文件
        File[] tempList = file.listFiles();
        // 按照文件名称正序排序
        assert tempList != null;
        Arrays.sort(tempList, (file1, file2) -> file1.getName().compareTo(file2.getName()));
        for(File fi : tempList){
            if(fi.isFile()) {
                String filename=fi.getName();
                String time = filename.substring(0,filename.lastIndexOf("."));
                Date afterDate = DateUtil.parse(time, "yyyyMMddHHmm");
                if (afterDate.getTime() > oldDate.getTime()) {
                    String now = DateUtil.format(afterDate, "yyyy年MM月dd日 HH时mm分");
                    FileName fn = new FileName();
                    fn.setPath(xmlpath.replace("\\", "/") + "/" + fi.getName());
                    fn.setFileName(fi.getName());
                    fn.setTime(now);
                    fn.setTitle(now + title);
                    fileList.add(fn);
                }
            }
        }
        obj.put("fileList",fileList);
        return obj.toJSONString();
    }

    /**
     * @author qh
     * @time 2019-08007
     * 根据文件路径 获取三类文件的内容
     * 将风速转等级
     * 等级超过6级的增加标识isRed=1;
     */
    @RequestMapping(value="/getWindRank")
    @ResponseBody
    public List<Station> getWindRank(String xmlPath, HttpServletRequest request) {
        String parmTemp = request.getParameter("parmTemp");
        if (xmlPath != null && !xmlPath.equals("")) {
            JSONObject jb = new JSONObject();
            List<Station> list = menuService.readTxtFile(xmlPath);
            for(Station station:list){
                //风速转风力
                int windRank=this.calculateWindRank(Double.valueOf(station.getValue()));
                station.setRank(windRank);
                //风力大于6级 设置为高亮
                if(windRank>6){
                    station.setIsRed("1");
                }
                else{
                    station.setIsRed("0");
                }
            }
            List<Station> listTemp = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                if(Integer.parseInt(parmTemp)==1){
                    if(list.get(i).getRank()>=7&&list.get(i).getRank()<=8){
                        listTemp.add(list.get(i));
                    }
                }
                if(Integer.parseInt(parmTemp)==2){
                    if(list.get(i).getRank()>=8&&list.get(i).getRank()<=9){
                        listTemp.add(list.get(i));
                    }
                }
                if(Integer.parseInt(parmTemp)==3){
                    if(list.get(i).getRank()>=9&&list.get(i).getRank()<=10){
                        listTemp.add(list.get(i));
                    }
                }
                if(Integer.parseInt(parmTemp)==4){
                    if(list.get(i).getRank()>=10){
                        listTemp.add(list.get(i));
                    }
                }

            }

            return listTemp;
        } else {
            return null;
        }
    }


    /**
     * 计算风力等级
     *
     * @param windValue
     * @return
     */
    private int calculateWindRank(double windValue) {
        int result = 0;
        // 获取风速等级表
        String[] winds = ConfigHelper.getProperty("WindRank").split("\\|");
        for (int i = 0; i < winds.length; i++) {
            String[] ranges = winds[i].split("-");
            if (windValue > Double.parseDouble(ranges[0]) && windValue < Double.parseDouble(ranges[1])) {
                result = i;
                break;
            }
        }
        return result;
    }
 }
