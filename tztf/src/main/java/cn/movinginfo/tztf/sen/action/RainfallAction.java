package cn.movinginfo.tztf.sen.action;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.common.utils.FileUtil;
import cn.movinginfo.tztf.sen.domain.FileName;
import cn.movinginfo.tztf.sen.domain.Menu;
import cn.movinginfo.tztf.sen.domain.Tabstation;
import cn.movinginfo.tztf.sen.service.MenuService;
import cn.movinginfo.tztf.sen.service.TabstationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * 面雨量
 */
@Controller
@RequestMapping("/sen/rainfall")
public class RainfallAction {

    @Autowired
    private MenuService menuService;

    @Resource
    private TabstationService tabstationService;

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
        String rainfallDay = ConfigHelper.getProperty("rainfall_day");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -Integer.parseInt(rainfallDay));
        List<Menu> menuList = menuService.getMenuByType(type);
        List<Menu> menus = menuService.getMenuByType(lowType);
        //时间轴开始时间
        Date startDay = c.getTime();
        if (lowType.contains("1hour_R")) {
            lowType = "1hour_R";
        } else if (lowType.contains("3hour_R")) {
            lowType = "3hour_R";
        } else if (lowType.contains("6hour_R")) {
            lowType = "6hour_R";
        } else if (lowType.contains("12hour_R")) {
            lowType = "12hour_R";
        }
        //读取文件路径
        String path = ConfigHelper.getProperty("rainfall_path") + "\\" + lowType;
        File files = new File(path);
        File[] tempList = files.listFiles();
        String title = "";
        if (menuList.size() != 0) {
            Menu menu = menuList.get(0);
            title = menu.getName();
        }
        if (menus.size() != 0) {
            Menu menu = menus.get(0);
            title += menu.getName();
        }
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
                    fileName.setFileName(now);
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
     * @param type    二级菜单
     * @param lowType 三级菜单
     * @param time    时间
     * @return string
     */
    @RequestMapping(value = "/getRainfall")
    @ResponseBody
    public List<Map<String, String>> getRainfall(String type, String lowType, String time) {
        List<Map<String, String>> result = new ArrayList<>();
        //区域站点对应数组
        Map<String, String> map = new HashMap<>();
        //从配置文件获取区域对应站点
        String stations = ConfigHelper.getProperty(type);
        Map object = JSON.parseObject(stations);
        for (Object key : object.keySet()) {
            map.put(String.valueOf(key), String.valueOf(object.get(key)));
        }
        if (lowType.contains("1hour_R")) {
            lowType = "1hour_R";
        } else if (lowType.contains("3hour_R")) {
            lowType = "3hour_R";
        } else if (lowType.contains("6hour_R")) {
            lowType = "6hour_R";
        } else if (lowType.contains("12hour_R")) {
            lowType = "12hour_R";
        }
        String path = ConfigHelper.getProperty("rainfall_path") + "\\" + lowType + "\\";
        Date date = DateUtil.parse(time, "yyyy年MM月dd日 HH时mm分");
        String name = DateUtil.format(date, "yyyyMMddHHmm") + ".dat";
        path = path + name;
        // 读取文件
        String content = FileUtil.readFileToString(path);
        assert content != null;
        String[] datas = content.split("\\r?\\n");
        //获取色标文件
        String color_path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\config\\Rainbow\\").substring(6);
        if (lowType.contains("1h")) {
            color_path = color_path + "rain1h.clv";
        } else if (lowType.contains("3h")) {
            color_path = color_path + "rain3h.clv";
        } else if (lowType.contains("6h")) {
            color_path = color_path + "rain6h.clv";
        } else if (lowType.contains("12h")) {
            color_path = color_path + "rain12h.clv";
        }
        String desc = FileUtil.readFileToString(color_path.replace("%20", " "));
        assert desc != null;
        String[] colors = desc.split("\\r?\\n");
        //获取所有色标
        String color_code = desc.replace("降水:[毫米]\r\n", "");
        //遍历所有区域计算面雨量
        for (String key : map.keySet()) {
            Map<String, String> stringMap = new HashMap<>();
            String[] codes = map.get(key).split(",");
            //计算每个区域平均面雨量
            double total = 0.0;
            for (String code : codes) {
                if (datas.length > 4) {
                    for (String data : datas) {
                        if (data.contains(code)) {
                            String[] strings = data.split(" ");
                            total = total + Double.valueOf(strings[4]);
                        }
                    }
                }
            }
            //如果是库区和小流域 获取全部站点数据
            if (type.equals("reservoir_area") || type.equals("small_watershed")) {
                //获取每个站点的雨量
                for (String code : codes) {
                    Map<String, String> stationMap = new HashMap<>();
                    Tabstation tabstation = tabstationService.geTabstationBy(code);
                    stationMap.put("type", "1");
                    stationMap.put("stcd", tabstation.getStationName());
                    stationMap.put("name", tabstation.getStationName());
                    stationMap.put("station", tabstation.getStationName());
                    stationMap.put("lon", String.valueOf(Double.valueOf(tabstation.getEeeee()) / 100));
                    stationMap.put("lat", String.valueOf(Double.valueOf(tabstation.getNnnn()) / 100));
                    stationMap.put("value", String.valueOf(new BigDecimal(total / codes.length).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()));
                    if (datas.length > 4) {
                        for (String data : datas) {
                            if (data.contains(code)) {
                                String[] strings = data.split(" ");
                                stationMap.put("stationValue", String.valueOf(Double.valueOf(strings[4])));
                            }
                        }
                    }
                    result.add(stationMap);
                }
                //查询其余站点信息
                String otherStations = "";
                String[] otherCodes;
                if (type.equals("reservoir_area")) {
                    otherStations = ConfigHelper.getProperty("small_watershed");
                    Map obj = JSON.parseObject(otherStations);
                    otherCodes = String.valueOf(obj.get("永宁江流域")).split(",");
                } else {
                    otherStations = ConfigHelper.getProperty("reservoir_area");
                    Map obj = JSON.parseObject(otherStations);
                    otherCodes = String.valueOf(obj.get("库区")).split(",");
                }
                for (String otherCode : otherCodes) {
                    if (!otherCode.equals("K8204")) {
                        Map<String, String> stationMap = new HashMap<>();
                        Tabstation tabstation = tabstationService.geTabstationBy(otherCode);
                        stationMap.put("type", "2");
                        stationMap.put("stcd", tabstation.getStationName());
                        stationMap.put("name", tabstation.getStationName());
                        stationMap.put("station", tabstation.getStationName());
                        stationMap.put("lon", String.valueOf(Double.valueOf(tabstation.getEeeee()) / 100));
                        stationMap.put("lat", String.valueOf(Double.valueOf(tabstation.getNnnn()) / 100));
                        stationMap.put("value", String.valueOf(new BigDecimal(total / codes.length).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue()));
                        if (datas.length > 4) {
                            for (String data : datas) {
                                if (data.contains(otherCode)) {
                                    String[] strings = data.split(" ");
                                    stationMap.put("stationValue", String.valueOf(Double.valueOf(strings[4])));
                                }
                            }
                        }
                        result.add(stationMap);
                    }
                }
            } else {
                //对比色标
                for (int i = 1; i < colors.length; i++) {
                    String[] strs = colors[i].split(" ");
                    double value = new BigDecimal(total / codes.length).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                    if (i == colors.length - 1) {
                        if (Double.valueOf(strs[0]) <= value) {
                            stringMap.put("color", strs[1]);
                            break;
                        }
                    } else {
                        String[] strs1 = colors[i + 1].split(" ");
                        if (Double.valueOf(strs[0]) <= value && Double.valueOf(strs1[0]) > value) {
                            stringMap.put("color", strs[1]);
                            break;
                        }
                    }
                }
                stringMap.put("value", String.format("%.1f", total / codes.length));
                stringMap.put("name", key);
                stringMap.put("color_code", color_code);
                result.add(stringMap);
            }
        }
        //根据面雨量进行排序
        result.sort((a, b) -> {
            Double rainfall1 = Double.valueOf(a.get("value"));
            Double rainfall2 = Double.valueOf(b.get("value"));
            return rainfall2.compareTo(rainfall1);
        });
        return result;
    }

    public static void main(String[] args) {
        String color_path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\config\\Rainbow\\").substring(6);
        System.out.println(color_path);
    }

}
