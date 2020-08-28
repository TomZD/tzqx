package cn.movinginfo.tztf.sen.service;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.common.task.HiddenDangerJob;
import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.KeyPlace;
import cn.movinginfo.tztf.sen.mapper.KeyPlaceMapper;
import cn.movinginfo.tztf.sen.mapper.TownInfoMapper;
import cn.movinginfo.tztf.sen.mapper.TownJXHMapper;
import cn.movinginfo.tztf.sen.model.TownForecast;
import cn.movinginfo.tztf.sen.model.TownInfo;
import cn.movinginfo.tztf.sen.model.TownJXH;
import cn.movinginfo.tztf.sen.model.TownJXHExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class TownJXHService {
    private final Logger logger = LoggerFactory.getLogger(TownJXHService.class);

    @Resource
    private TownJXHMapper townJXHMapper;

    @Resource
    private TownInfoMapper townInfoMapper;

    @Resource
    private KeyPlaceMapper keyPlaceMapper;


    public boolean insertOrUpateData(List<TownJXH> townJXHList) {
        boolean result = false;
        int successCount = 0;
        for (TownJXH t : townJXHList) {
            TownJXHExample example = new TownJXHExample();
            example.createCriteria().andDateForecastEqualTo(t.getDateForecast()).andTownCodeEqualTo(t.getTownCode());
            long count = townJXHMapper.countByExample(example);
            if (count == 0) {
                successCount += townJXHMapper.insertSelective(t);
            } else {
                successCount += townJXHMapper.updateByExampleSelective(t, example);
            }
        }
        if (successCount == townJXHList.size())
            result = true;
        return result;
    }


    public void getTownJxhData(String code, String time) {
        List<TownJXH> list = townJXHMapper.getTownJXHByTimeAndCode(time, code);
        BigDecimal size = new BigDecimal(list.size());
        BigDecimal Pr03 = list.stream().map(TownJXH::getPr03).reduce(BigDecimal.ZERO, BigDecimal::add);
        double avgPr03 = Pr03.divide(size, 2, BigDecimal.ROUND_HALF_UP).doubleValue();//降水
        BigDecimal RH = list.stream().map(TownJXH::getRh).reduce(BigDecimal.ZERO, BigDecimal::add);
        double avgRH = RH.divide(size, 2, BigDecimal.ROUND_HALF_UP).doubleValue();//湿度
        BigDecimal T = list.stream().map(TownJXH::getT).reduce(BigDecimal.ZERO, BigDecimal::add);
        double avgT = T.divide(size, 2, BigDecimal.ROUND_HALF_UP).doubleValue();//温度
//    	BigDecimal Wind_D = list.stream().map(TownJXH::getWindD).reduce(BigDecimal.ZERO,BigDecimal::add);
//    	double avgWind_D=Wind_D.divide(size, 2,BigDecimal.ROUND_HALF_UP).doubleValue();//风向
        BigDecimal Wind_S = list.stream().map(TownJXH::getWindS).reduce(BigDecimal.ZERO, BigDecimal::add);
        double avgWind_S = Wind_S.divide(size, 2, BigDecimal.ROUND_HALF_UP).doubleValue();//风速
    }

    /**
     * 计算风向
     *
     * @param num
     * @return
     */
    public static String getWindDirect(int num) {
        String[] windDirects = new String[]{"北", "东北偏北", "东北", "东北偏东", "东", "东南偏东", "东南", "东南偏南", "南", "西南偏南", "西南",
                "西南偏西", "西", "西北偏西", "西北", "西北偏北", "北"};
        String str = windDirects[(int) ((num + 11.24) / 22.5)];// 北风348.76-11.25
        return str;
    }

    /**
     * 计算风力等级
     *
     * @param windValue
     * @return
     */
    public static int calculateWindRank(double windValue) {
        int result = 0;
        // 获取风速等级表
        String[] winds = ConfigHelper.getProperty("WindRank").split("\\|");
        for (int i = 0; i < winds.length; i++) {
            String[] ranges = winds[i].split("-");
            if (windValue >= Double.parseDouble(ranges[0]) && windValue <= Double.parseDouble(ranges[1])) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * 获取当天风向 出现最多的一个
     *
     * @param startDate
     * @param endDate
     * @param townCode
     * @return
     */
    public String getWindD(Timestamp startDate, Timestamp endDate, String townCode) {
        List<BigDecimal> list = townJXHMapper.getWindD(startDate, endDate, townCode);
        Map<String, Integer> map = new HashMap<>();
        String desc = "";
        if (list.size() != 0) {
            for (BigDecimal windD : list) {
                if (!windD.toString().equals("-999.0")) {
                    desc = getWindDirect(windD.intValue()) + "风";
                    if (map.containsKey(desc)) {
                        map.put(desc, map.get(desc) + 1);
                    } else {
                        map.put(desc, 1);
                    }
                }
            }
        }
        if (map != null) {
            int maxCount = 0;
            Collection<Integer> count = map.values();
            if (count.size() != 0) {
                maxCount = Collections.max(count);
                for (String key : map.keySet()) {
                    if (maxCount == map.get(key)) {
                        desc = key;
                    }
                }
            }
        }
        return desc;
    }

    /**
     * 获取乡镇精细化预报
     *
     * @return 各乡镇七天精细化预报数据
     */
    public List<TownForecast> getTownForecast(String name) {
        if (name.contains("东城") || name.contains("西城") || name.contains("南城") || name.contains("北城")) {
            name = "城区";
        }
        name = name.replaceAll("街道", "").replaceAll("镇", "").replaceAll("乡", "");
        List<TownForecast> result = new ArrayList<>();
        //从配置文件获取天况列表
        String weather = ConfigHelper.getProperty("Weather");
        String[] weathers = weather.split("\\|");

        List<Map<String, Timestamp>> dayList = DateUtil.getDate();
        //每天的预报数据    //获取预报日期
        for (Map<String, Timestamp> map : dayList) {
            TownForecast forecast = new TownForecast();
            //获取街道信息
            TownInfo town = townInfoMapper.selectByName(name);
            if (town != null) {
                String code = town.getIiiii();
                forecast.setStationName(town.getStationname());
                String day = DateUtil.format(map.get("today8"), "MM月dd日");
                forecast.setTime(day);
                DecimalFormat df1 = new DecimalFormat("#,#0.0#");
                DecimalFormat df2 = new DecimalFormat("#,#0.00#");
                //查询最高温 最低温 相对湿度平均值
                Map<String, BigDecimal> decimalMap = townJXHMapper.getTownJXH(map.get("yesterday20"), map.get("today20"), code);
                if (decimalMap != null) {
                    forecast.setTemperatureMin(decimalMap.get("minT"));
                    forecast.setTemperatureMax(decimalMap.get("maxT"));
                    forecast.setRelativeHumidity(df2.format(decimalMap.get("avgRh").setScale(2, BigDecimal.ROUND_HALF_UP)));
                }
                //查询天气和降水  第一次预报
                TownJXH first = townJXHMapper.getWeather(map.get("today8"), code);
                //查询第一次风向
//                String windS1 = getWindD(map.get("yesterday20"), map.get("today8"), code);
                //查询第一次风速
                Map<String, BigDecimal> map1 = townJXHMapper.getWindS(map.get("yesterday20"), map.get("today8"), code);
                int min1 = calculateWindRank(map1.get("minS").doubleValue());
                int max1 = calculateWindRank(map1.get("maxS").doubleValue());
//                if (min1 == max1) {
//                    forecast.setFirstWind(windS1 + min1 + "级");
//                } else {
//                    forecast.setFirstWind(windS1 + min1 + "-" + max1 + "级");
//                }
                if (first != null) {
                    forecast.setFirstWeather(weathers[Integer.valueOf(first.getWw12().trim())]);
                    forecast.setPrecipitation(first.getPr24() == null ? df1.format(new BigDecimal(0)) : df1.format(first.getPr24()));
                }
                //第二次预报
                TownJXH second = townJXHMapper.getWeather(map.get("today20"), code);
                //查询第二次风向
//                String windS2 = getWindD(map.get("today8"), map.get("today20"), code);
                //查询第二次风速
                Map<String, BigDecimal> map2 = townJXHMapper.getWindS(map.get("today8"), map.get("today20"), code);
                int min2 = calculateWindRank(map2.get("minS").doubleValue());
                int max2 = calculateWindRank(map2.get("maxS").doubleValue());
//                if (min2 == max2) {
//                    forecast.setSecondWind(windS2 + min2 + "级");
//                } else {
//                    forecast.setSecondWind(windS2 + min2 + "-" + max2 + "级");
//                }
                if (second != null) {
                    forecast.setSecondWeather(weathers[Integer.valueOf(second.getWw12().trim())]);
                    forecast.setSecondPrecipitation(second.getPr24() == null ? df1.format(new BigDecimal(0)) : df1.format(second.getPr24()));
                }
                result.add(forecast);
            }
        }
        return result;
    }

    /**
     * 查询旅游景点7天天气
     * @param name 景点名称
     * @return 景点天气
     */
    public List<TownForecast> getScenicForecast(String name) {
        List<TownForecast> result = new ArrayList<>();
        //从配置文件获取天况列表
        String weather = ConfigHelper.getProperty("Weather");
        String[] weathers = weather.split("\\|");
        //从配置文件获取站号
        String scenicStation = ConfigHelper.getProperty("scenic_station");
        Map object = JSON.parseObject(scenicStation);
        List<Map<String, Timestamp>> dayList = DateUtil.getDate();
        //每天的预报数据    //获取预报日期
        for (Map<String, Timestamp> map : dayList) {
            TownForecast forecast = new TownForecast();
            //获取街道信息
            TownInfo town = townInfoMapper.selectByPrimaryKey(String.valueOf(object.get(name)));
            if (town != null) {
                String code = town.getIiiii();
                forecast.setStationName(town.getStationname());
                String day = DateUtil.format(map.get("today8"), "MM月dd日");
                forecast.setTime(day);
                DecimalFormat df1 = new DecimalFormat("#,#0.0#");
                DecimalFormat df2 = new DecimalFormat("#,#0.00#");
                //查询最高温 最低温 相对湿度平均值
                Map<String, BigDecimal> decimalMap = townJXHMapper.getTownJXH(map.get("yesterday20"), map.get("today20"), code);
                if (decimalMap != null) {
                    forecast.setTemperatureMin(decimalMap.get("minT"));
                    forecast.setTemperatureMax(decimalMap.get("maxT"));
                    forecast.setRelativeHumidity(df2.format(decimalMap.get("avgRh").setScale(2, BigDecimal.ROUND_HALF_UP)));
                }
                //查询天气和降水  第一次预报
                TownJXH first = townJXHMapper.getWeather(map.get("today8"), code);
                if (first != null) {
                    forecast.setFirstWeather(weathers[Integer.valueOf(first.getWw12().trim())]);
                    forecast.setPrecipitation(first.getPr24() == null ? df1.format(new BigDecimal(0)) : df1.format(first.getPr24()));
                }
                //第二次预报
                TownJXH second = townJXHMapper.getWeather(map.get("today20"), code);
                if (second != null) {
                    forecast.setSecondWeather(weathers[Integer.valueOf(second.getWw12().trim())]);
                    forecast.setSecondPrecipitation(second.getPr24() == null ? df1.format(new BigDecimal(0)) : df1.format(second.getPr24()));
                }
                result.add(forecast);
            }
        }
        return result;
    }

    /**
     * 获取全部乡镇当天天气预报
     *
     * @return 天气对象数组
     */
    public List<TownForecast> getWeather() {
        List<TownForecast> result = new ArrayList<>();
        //从配置文件获取天况列表
        String weather = ConfigHelper.getProperty("Weather");
        String[] weathers = weather.split("\\|");
        //从配置文件获取所有站号
        String townInfo = ConfigHelper.getProperty("TownInfo");
        Map map = JSON.parseObject(townInfo);
        //获取预报日期
        List<Map<String, Timestamp>> dayList = DateUtil.getDate();
        //第一天的预报时间
        Map<String, Timestamp> times = dayList.get(0);
        //获取各个乡镇天气
        for (Object key : map.keySet()) {
            TownForecast forecast = new TownForecast();
            //获取街道信息
            TownInfo town = townInfoMapper.selectByPrimaryKey(String.valueOf(map.get(key)));
            forecast.setStationName(town.getStationname());
            forecast.setLon(town.getLon());
            forecast.setLat(town.getLat());
            forecast.setCode(town.getIiiii());
            //第一次预报
            TownJXH first = townJXHMapper.getWeather(times.get("today8"), String.valueOf(map.get(key)));
            TownJXH second = townJXHMapper.getWeather(times.get("today20"), String.valueOf(map.get(key)));
            if (first != null) {
                forecast.setFirstWeather(weathers[Integer.valueOf(first.getWw12().trim())]);
            }
            if (second != null) {
                forecast.setSecondWeather(weathers[Integer.valueOf(second.getWw12().trim())]);
            }
            result.add(forecast);
        }
        return result;
    }

}
