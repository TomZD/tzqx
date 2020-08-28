package cn.movinginfo.tztf.sen.service;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.sen.mapper.TownOCFMapper;
import cn.movinginfo.tztf.sen.model.TownOCF;
import cn.movinginfo.tztf.sen.model.TownOCFExample;
import net.ryian.orm.service.support.datasource.annotation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TownOCFService {
    @Resource
    private TownOCFMapper townOCFMapper;
//    @Resource
//    private ForecastModuleMapper forecastModuleMapper;
//    @Resource
//    private ForecastFieldDataService forecastFieldDataService;
//    @Resource
//    private ForecastTemplateService forecastTemplateService;

    //录入解析数据
    public boolean insertOrUpdateData(List<TownOCF> fieldDataList) {
        boolean result = false;
        int successCount = 0;
        for (TownOCF f : fieldDataList) {
            TownOCFExample example = new TownOCFExample();
            example.createCriteria().andDateXEqualTo(f.getDateX()).andFhEqualTo(f.getFh()).andTownCodeEqualTo(f.getTownCode());
            long count = townOCFMapper.countByExample(example);
            if (count == 0) {
                successCount += townOCFMapper.insertSelective(f);
            } else {
                successCount += townOCFMapper.updateByExampleSelective(f, example);
            }
        }
        if (successCount == fieldDataList.size())
            result = true;
        return result;
    }

    /**
     * 设置乡镇预报ocf数据到ForecastTemplate
     *
     * @param forecastTemplateList 模板字段列表
     * @return 返回模板字段列表
     */
//    public List<ForecastTemplate> setForecastTemplateData(List<ForecastTemplate> forecastTemplateList, String townInfo, String workTime) {
//        List<ForecastTemplate> result = null;
//        //获取预报时间 上午为今天  下午为明天
//        Date date = new Date();
//        String hour = workTime.split(":")[0];
//        //如果是上午  分别获取预报时间
//        //获取昨天晚上20点的Timestamp类型日期
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_MONTH, -1);
//        calendar.set(Calendar.HOUR_OF_DAY, 20);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        int h = Integer.valueOf(hour);
//        if (h > 12) {
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        //第一天温度的起始时间
//        Timestamp date1 = new Timestamp(calendar.getTimeInMillis());
//        //第一天的天气第一个预报时间
//        calendar.add(Calendar.HOUR_OF_DAY, 12);
//        Timestamp date2 = new Timestamp(calendar.getTimeInMillis());
//        //第一天的天气第二个预报时间 第一天温度的结束时间 第二天温度开始时间
//        calendar.add(Calendar.HOUR_OF_DAY, 12);
//        Timestamp date3 = new Timestamp(calendar.getTimeInMillis());
//        //第二天的天气第一个预报时间
//        calendar.add(Calendar.HOUR_OF_DAY, 12);
//        Timestamp date4 = new Timestamp(calendar.getTimeInMillis());
//        //第二天的天气第二个预报时间 第二天温度的结束时间 第三天温度开始时间
//        calendar.add(Calendar.HOUR_OF_DAY, 12);
//        Timestamp date5 = new Timestamp(calendar.getTimeInMillis());
//        //第三天的天气第一个预报时间
//        calendar.add(Calendar.HOUR_OF_DAY, 12);
//        Timestamp date6 = new Timestamp(calendar.getTimeInMillis());
//        //第三天的天气第二个预报时间 第三天温度的结束时间
//        calendar.add(Calendar.HOUR_OF_DAY, 12);
//        Timestamp date7 = new Timestamp(calendar.getTimeInMillis());
//        //获取所有站号
////        String townInfo = ConfigHelper.getProperty("TownInfo");
//        Map map = JSON.parseObject(townInfo);
//        //从配置文件获取天况列表
//        String weather = ConfigHelper.getProperty("Weather");
//        String[] wea = weather.split("\\|");
//        String day = DateUtil.format(new Date(), "yyyy-MM-dd");
//        //设置内陆风力值
//        String desc = "";
//        //默认获取预报稿风力数据
//        //获取发布内容
//        ForecastModuleExample example = new ForecastModuleExample();
//        example.createCriteria().andModulecodeEqualTo("ShortForecast");
//        List<ForecastModule> forecastModules = forecastModuleMapper.selectByExample(example);
//        if (forecastModules != null && forecastModules.size() != 0) {
//            for (ForecastModule module : forecastModules) {
//                List<ForecastTemplate> list = forecastTemplateService.selectByIdName(module.getFormoduleid(), "内陆风力", day);
//                if (list != null && list.size() != 0) {
//                    for (ForecastTemplate template : list) {
//                        if (template != null) {
//                            if (template.getTemfielddesc() != null && !template.getTemfielddesc().isEmpty()) {
//                                desc = template.getTemfielddesc();
//                            }
//                        }
//                        if (!desc.equals("")) {
//                            break;
//                        }
//                    }
//                }
//                if (!desc.equals("")) {
//                    break;
//                }
//            }
//        }
//        if (desc.equals("")) {
//            //第一天风向 风速
//            String windD1 = getWindD(date1, date3, "K8201");
//            desc = desc + (!windD1.equals("") ? "今天" + windD1 : "");
//            Map<String, BigDecimal> map1 = townOCFMapper.getWindS(date1, date3, "K8201");
//            if (map1 != null) {
//                Integer minW = WindHelper.calculateWindRank(map1.get("minW").doubleValue());
//                Integer maxW = WindHelper.calculateWindRank(map1.get("maxW").doubleValue());
//                if (minW.equals(maxW)) {
//                    desc = desc + maxW + "级,";
//                } else {
//                    desc = desc + minW + "级到" + maxW + "级，";
//                }
//            }
//            //第二天风向 风速
//            String windD2 = getWindD(date3, date5, "K8201");
//            desc = desc + (!windD2.equals("") ? "明天" + windD2 : "");
//            Map<String, BigDecimal> map2 = townOCFMapper.getWindS(date3, date5, "K8201");
//            if (map2 != null) {
//                Integer minW = WindHelper.calculateWindRank(map2.get("minW").doubleValue());
//                Integer maxW = WindHelper.calculateWindRank(map2.get("maxW").doubleValue());
//                if (minW.equals(maxW)) {
//                    desc = desc + maxW + "级,";
//                } else {
//                    desc = desc + minW + "级到" + maxW + "级，";
//                }
//            }
//            //第三天风向 风速
//            String windD3 = getWindD(date5, date7, "K8201");
//            desc = desc + (!windD3.equals("") ? "后天" + windD3 : "");
//            Map<String, BigDecimal> map3 = townOCFMapper.getWindS(date5, date7, "K8201");
//            if (map3 != null) {
//                Integer minW = WindHelper.calculateWindRank(map3.get("minW").doubleValue());
//                Integer maxW = WindHelper.calculateWindRank(map3.get("maxW").doubleValue());
//                if (minW.equals(maxW)) {
//                    desc = desc + maxW + "级。";
//                } else {
//                    desc = desc + minW + "级到" + maxW + "级。";
//                }
//            }
//        }
//        Optional<ForecastTemplate> wind = forecastTemplateList.stream().filter(a -> "内陆地区风力".equals(a.getTemfieldname())).findFirst();
//        if (wind.isPresent()) {
//            wind.get().setTemfielddesc(desc);
//        }
//        int state = 1;
//        //遍历模板字段并设置值
//        for (Object key : map.keySet()) {
//            //设置第一天的天况
//            Optional<ForecastTemplate> firstWeather = forecastTemplateList.stream().filter(a -> key.equals(a.getTemfieldname()) && "第一天天气".equals(a.getTemfieldcolumnname())).findFirst();
//            if (firstWeather.isPresent()) {
//                TownOCF townOCF = townOCFMapper.getTownOCF(date2, String.valueOf(map.get(key)));
//                TownOCF townOCF1 = townOCFMapper.getTownOCF(date3, String.valueOf(map.get(key)));
//                if (townOCF != null && townOCF1 != null) {
//                    if (h < 12) {
//                        if (!townOCF1.getWw12().trim().equals("-999")) {
//                            firstWeather.get().setTemfielddesc(wea[Integer.valueOf(townOCF1.getWw12().trim())]);
//                        }
//                    } else {
//                        if (!townOCF.getWw12().trim().equals("-999") && !townOCF1.getWw12().trim().equals("-999")) {
//                            if (!townOCF.getWw12().equals(townOCF1.getWw12())) {
//                                firstWeather.get().setTemfielddesc(wea[Integer.valueOf(townOCF.getWw12().trim())] + "转" + wea[Integer.valueOf(townOCF1.getWw12().trim())]);
//                            } else {
//                                firstWeather.get().setTemfielddesc(wea[Integer.valueOf(townOCF.getWw12().trim())]);
//                            }
//                        }
//                    }
//                }
//                if (townOCF == null) {
//                    if (key == "东城" || key == "通州溪") {
//                        state = 0;
//                    }
//                }
//            }
//            //设置第一天温度最大值和最小值
//            Optional<ForecastTemplate> firstTemperature = forecastTemplateList.stream().filter(a -> key.equals(a.getTemfieldname()) && "第一天温度".equals(a.getTemfieldcolumnname())).findFirst();
//            if (firstTemperature.isPresent()) {
//                Map<String, BigDecimal> map1 = townOCFMapper.getTemperature(date1, date3, String.valueOf(map.get(key)));
//                if (map1 != null) {
//                    firstTemperature.get().setMinvalue(map1.get("minT").longValue());
//                    firstTemperature.get().setMaxvalue(map1.get("maxT").longValue());
//                    firstTemperature.get().setTemfielddesc(map1.get("minT").longValue() + "～" + map1.get("maxT").longValue());
//                }
//            }
//            //设置第二天天况
//            Optional<ForecastTemplate> secondWeather = forecastTemplateList.stream().filter(a -> key.equals(a.getTemfieldname()) && "第二天天气".equals(a.getTemfieldcolumnname())).findFirst();
//            if (secondWeather.isPresent()) {
//                TownOCF townOCF = townOCFMapper.getTownOCF(date4, String.valueOf(map.get(key)));
//                TownOCF townOCF1 = townOCFMapper.getTownOCF(date5, String.valueOf(map.get(key)));
//                if (townOCF != null && townOCF1 != null) {
//                    if (!townOCF.getWw12().trim().equals("-999") && !townOCF1.getWw12().trim().equals("-999")) {
//                        if (!townOCF.getWw12().equals(townOCF1.getWw12())) {
//                            secondWeather.get().setTemfielddesc(wea[Integer.valueOf(townOCF.getWw12().trim())] + "转" + wea[Integer.valueOf(townOCF1.getWw12().trim())]);
//                        } else {
//                            secondWeather.get().setTemfielddesc(wea[Integer.valueOf(townOCF.getWw12().trim())]);
//                        }
//                    }
//                }
//            }
//            //设置第二天温度最大值和最小值
//            Optional<ForecastTemplate> secondTemperature = forecastTemplateList.stream().filter(a -> key.equals(a.getTemfieldname()) && "第二天温度".equals(a.getTemfieldcolumnname())).findFirst();
//            if (secondTemperature.isPresent()) {
//                Map<String, BigDecimal> map2 = townOCFMapper.getTemperature(date3, date5, String.valueOf(map.get(key)));
//                if (map2 != null) {
//                    secondTemperature.get().setMinvalue(map2.get("minT").longValue());
//                    secondTemperature.get().setMaxvalue(map2.get("maxT").longValue());
//                    secondTemperature.get().setTemfielddesc(map2.get("minT").longValue() + "～" + map2.get("maxT").longValue());
//                }
//            }
//            //设置第三天天况
//            Optional<ForecastTemplate> thirdWeather = forecastTemplateList.stream().filter(a -> key.equals(a.getTemfieldname()) && "第三天天气".equals(a.getTemfieldcolumnname())).findFirst();
//            if (thirdWeather.isPresent()) {
//                TownOCF townOCF = townOCFMapper.getTownOCF(date6, String.valueOf(map.get(key)));
//                TownOCF townOCF1 = townOCFMapper.getTownOCF(date7, String.valueOf(map.get(key)));
//                if (townOCF != null && townOCF1 != null) {
//                    if (!townOCF.getWw12().trim().equals("-999") && !townOCF1.getWw12().trim().equals("-999")) {
//                        if (!townOCF.getWw12().equals(townOCF1.getWw12())) {
//                            thirdWeather.get().setTemfielddesc(wea[Integer.valueOf(townOCF.getWw12().trim())] + "转" + wea[Integer.valueOf(townOCF1.getWw12().trim())]);
//                        } else {
//                            thirdWeather.get().setTemfielddesc(wea[Integer.valueOf(townOCF.getWw12().trim())]);
//                        }
//                    }
//                }
//            }
//            //设置第三天温度最大值和最小值
//            Optional<ForecastTemplate> thirdTemperature = forecastTemplateList.stream().filter(a -> key.equals(a.getTemfieldname()) && "第三天温度".equals(a.getTemfieldcolumnname())).findFirst();
//            if (thirdTemperature.isPresent()) {
//                Map<String, BigDecimal> map3 = townOCFMapper.getTemperature(date5, date7, String.valueOf(map.get(key)));
//                if (map3 != null) {
//                    thirdTemperature.get().setMinvalue(map3.get("minT").longValue());
//                    thirdTemperature.get().setMaxvalue(map3.get("maxT").longValue());
//                    thirdTemperature.get().setTemfielddesc(map3.get("minT").longValue() + "～" + map3.get("maxT").longValue());
//                }
//            }
//        }
//        if (state != 0) {
//            result = forecastTemplateList;
//        }
//        return result;
//    }
//
//    /**
//     * 获取当天风向 出现最多的一个
//     *
//     * @param startDate
//     * @param endDate
//     * @param townCode
//     * @return
//     */
//    public String getWindD(Timestamp startDate, Timestamp endDate, String townCode) {
//        List<BigDecimal> list = townOCFMapper.getWindD(startDate, endDate, townCode);
//        Map<String, Integer> map = new HashMap<>();
//        String desc = "";
//        if (list.size() != 0) {
//            for (BigDecimal windD : list) {
//                if (!windD.toString().equals("-999.0")) {
//                    desc = WindHelper.getWindDirect(windD.intValue()) + "风";
//                    if (map.containsKey(desc)) {
//                        map.put(desc, map.get(desc) + 1);
//                    } else {
//                        map.put(desc, 1);
//                    }
//                }
//            }
//        }
//        if (map != null) {
//            int maxCount = 0;
//            Collection<Integer> count = map.values();
//            if (count.size() != 0) {
//                maxCount = Collections.max(count);
//                for (String key : map.keySet()) {
//                    if (maxCount == map.get(key)) {
//                        desc = key;
//                    }
//                }
//            }
//        }
//        return desc;
//    }
//
//    public Map<String, BigDecimal> getWindS(Timestamp startDate, Timestamp endDate, String townCode) {
//        return townOCFMapper.getWindS(startDate, endDate, townCode);
//    }

}
