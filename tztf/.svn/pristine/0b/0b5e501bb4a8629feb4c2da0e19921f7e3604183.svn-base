package cn.movinginfo.tztf.sen.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.common.task.HiddenDangerJob;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.ConfigureResult;
import cn.movinginfo.tztf.sen.domain.FileName;
import cn.movinginfo.tztf.sen.domain.KeyPlace;
import cn.movinginfo.tztf.sen.domain.PageModel;
import cn.movinginfo.tztf.sen.model.TownForecast;
import cn.movinginfo.tztf.sen.model.TownInfo;
import cn.movinginfo.tztf.sen.service.KeyPlaceService;
import cn.movinginfo.tztf.sen.service.MenuService;
import cn.movinginfo.tztf.sen.service.TownInfoService;
import cn.movinginfo.tztf.sen.service.TownJXHService;
import cn.movinginfo.tztf.sev.domain.Page;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/sen/townJxh")
public class TownJXHAction {
    @Resource
    private TownJXHService jxhService;
    @Resource
    private TownInfoService townInfoService;
    @Autowired
    private MenuService menuService;

    @Resource
    private KeyPlaceService keyPlaceService;

    /**
     * 获取乡镇
     *
     * @return 乡镇列表
     */
    @RequestMapping(value = "/getTown")
    @ResponseBody
    public List<TownInfo> getTown() {
        return townInfoService.getTownInfo();
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getScenic")
    @ResponseBody
    public List<KeyPlace> getScenic() {
        List<KeyPlace> list = keyPlaceService.getKeyPlaceByTypeId((long) 4);
        for (KeyPlace keyPlace : list) {
            keyPlace.setLat(keyPlace.getLatitude());
            keyPlace.setLon(keyPlace.getLongitude());
        }
        return list;
    }

    /**
     *
     * @param name 旅游景点民称
     * @return 景点7天预报
     */
    @RequestMapping(value = "/getScenicData")
    @ResponseBody
    public List<TownForecast> getScenicData(String name) {
        return jxhService.getScenicForecast(name);
    }

    @RequestMapping(value = "/a")
    @ResponseBody
    public String a() {
        String dis = "";
        List<KeyPlace> keyPlaces = keyPlaceService.selectAll();
        List<TownInfo> townInfos = townInfoService.selectAll();
        for (KeyPlace keyPlace : keyPlaces) {
            List<Map<String, String>> list = new ArrayList<>();
            for (TownInfo townInfo : townInfos) {
                Map<String, String> map = new HashMap<>();
                double distance = HiddenDangerJob.getDistance(Double.valueOf(keyPlace.getLatitude()), Double.valueOf(keyPlace.getLongitude()), townInfo.getLat(),
                        townInfo.getLon());
                map.put("distance", String.valueOf(distance));
                map.put("name", keyPlace.getName());
                map.put("code", townInfo.getIiiii());
                list.add(map);
            }
            list.sort((a, b) -> {
                Double dis1 = Double.parseDouble(a.get("distance"));
                Double dis2 = Double.parseDouble(b.get("distance"));
                return dis1.compareTo(dis2);
            });
            dis = dis + list.get(0).get("name") + ":" + list.get(0).get("code") + "\r\n";
        }
        return dis;
    }

    /**
     * 获取乡镇七天预报信息
     *
     * @param name 乡镇名称
     * @return 数组
     */
    @RequestMapping(value = "/getJxhData")
    @ResponseBody
    public List<TownForecast> getJxhData(String name) {
        return jxhService.getTownForecast(name);
    }

    /**
     * 获取乡镇当天预报以及位置信息
     *
     * @return 数组
     */
    @RequestMapping(value = "/getWeather")
    @ResponseBody
    public List<TownForecast> getWeather() {
        return jxhService.getWeather();
    }

    /**
     * 获取决策材料、一周天气预报、天气气候公报、重要天气报告列表
     *
     * @param beginTime
     * @param endTime
     * @param name
     * @param type
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "getReport")
    @ResponseBody
    public ConfigureResult getReport(int pages, String beginTime, String endTime, String name, String type) throws ParseException {
        //PageHelper.startPage(pages,9);
        String path = ConfigHelper.getProperty("decisionMaking_path");//决策材料、一周天气预报、天气气候公报、重要天气报告基路径
        String nowPath = path + type;
        //判断路径信息，来进行文件的筛选
        if(type.equals("forecastweek")||type.equals("weatherclimate")){
            List<FileName> list = menuService.fileList(nowPath, type, beginTime, endTime, name);
            Collections.sort(list, (s1, s2) -> s2.getFileName().compareTo(s1.getFileName()));
            PageModel<FileName> pm = new PageModel<FileName>(list, 9);
            ConfigureResult cr = getConfigureResult(pm, pages);
            return cr;
        }else{
            String  time1="";
            String time2="";
            //进行筛选近两周的时间
            Calendar calendar = Calendar.getInstance();
            time2=DateUtil.format(calendar.getTime(),"yyyy-MM-dd");
            calendar.add(Calendar.DAY_OF_MONTH,-14);
            time1 = DateUtil.format(calendar.getTime(),"yyyy-MM-dd");
            if(beginTime==""){
                beginTime=time1;
            }else{
                beginTime=beginTime;
            }
            if(endTime==""){
                endTime=time2;
            }else{
                endTime=endTime;
            }
            List<FileName> list = menuService.fileList(nowPath, type, beginTime, endTime, name);
            Collections.sort(list, (s1, s2) -> s2.getFileName().compareTo(s1.getFileName()));
            PageModel<FileName> pm = new PageModel<FileName>(list, 9);
            ConfigureResult cr = getConfigureResult(pm, pages);
            return cr;

        }




    }

    /**
     * 应急辅助
     * @param pages
     * @param beginTime
     * @param endTime
     * @param name
     * @param type
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "getReporter")
    @ResponseBody
    public ConfigureResult getReporter(int pages, String beginTime, String endTime, String name, String type) throws ParseException {
        //PageHelper.startPage(pages,9);
        String path = ConfigHelper.getProperty("decisionMaking_path");
        String nowPath = path + type;

            String  time1="";
            String time2="";
            //进行筛选近两周的时间
            Calendar calendar = Calendar.getInstance();
            time2=DateUtil.format(calendar.getTime(),"yyyy-MM-dd");
            calendar.add(Calendar.DAY_OF_MONTH,-14);
            time1 = DateUtil.format(calendar.getTime(),"yyyy-MM-dd");
            if(beginTime==""){
                beginTime=time1;
            }else{
                beginTime=beginTime;
            }
            if(endTime==""){
                endTime=time2;
            }else{
                endTime=endTime;
            }
            List<FileName> list = menuService.fileList(nowPath, type, beginTime, endTime, name);
            Collections.sort(list, (s1, s2) -> s2.getFileName().compareTo(s1.getFileName()));
            PageModel<FileName> pm = new PageModel<FileName>(list, 9);
            ConfigureResult cr = getConfigureResult(pm, pages);
            return cr;





    }

    /**
     * 分页
     *
     * @param
     * @return
     */
    private ConfigureResult getConfigureResult(PageModel<FileName> pm, int pages) {
        ConfigureResult pr = new ConfigureResult();
        List<FileName> sublist = pm.getObjects(pages);
        pr.setFileList(sublist);
        Page p = new Page();
        p.setFirstItemOnPage(pm.getPageStartRow());
        p.setHasNextPage(pm.isHasNextPage());
        p.setHasPreviousPage(pm.isHasPreviousPage());
//		p.setIsFirstPage(page.isIsFirstPage());
//		p.setIsLastPage(page.isIsLastPage());
        p.setLastItemOnPage(pm.getPageEndRow());
        p.setPageCount(pm.getTotalPages());  // 总页数
        p.setPageNumber(pm.getPage());
        p.setPageSize(pm.getPageRecorders());
        p.setTotalItemCount(pm.getTotalRows());
        pr.setPage(p);
        return pr;
    }

}
