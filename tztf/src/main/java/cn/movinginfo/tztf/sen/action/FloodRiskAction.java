package cn.movinginfo.tztf.sen.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.FileUtil;
import cn.movinginfo.tztf.sen.domain.DangerAlarm;
import cn.movinginfo.tztf.sen.domain.Elementinfo;
import cn.movinginfo.tztf.sen.service.DangerAlarmService;
import cn.movinginfo.tztf.sen.service.ElementinfoService;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 小流域山洪风险
 */
@Controller
@RequestMapping("/sen/floodRisk")
public class FloodRiskAction {

    @Resource
    private DangerAlarmService dangerAlarmService;

    @Resource
    private ElementinfoService elementinfoService;

    /**
     * 获取小流域山洪风险预警
     *
     * @param outType 二级菜单
     * @param lowType 三级菜单
     * @return 列表
     */
    @RequestMapping("/getFloodRisk")
    @ResponseBody
    public List<DangerAlarm> getFloodRisk(String outType, String lowType) {
        List<DangerAlarm> result = new ArrayList<>();
        //获取色标文件
        String color_path = ((Thread.currentThread().getContextClassLoader().getResource("/")).toString() + "\\config\\Rainbow\\flood_risk.clv").substring(6).replace("%20", " ");
        String desc = FileUtil.readFileToString(color_path);
        //获取所有色标
        assert desc != null;
        String color_code = desc.replace("小流域山洪风险\r\n", "");
        String[] colors = desc.split("\\r?\\n");
        //判断是实况风险还是预报风险
        if (outType.equals("live_situation")) { //实况风险
            Integer hour = Integer.valueOf(lowType.replace("ls_rain", ""));
            result = dangerAlarmService.getLiveSituation(hour);
        } else { //预报风险
            //查出要素
            Elementinfo elementinfo = elementinfoService.findByElementId(lowType);
            result = dangerAlarmService.getPrediction(elementinfo.getId().intValue(), elementinfo.getForecastHour());
        }
        //添加色标属性
        for (DangerAlarm dangerAlarm : result) {
            dangerAlarm.setColor_code(color_code);
            for (int i = 1; i < colors.length; i++) {
                String[] strs = colors[i].split(" ");
                if (Integer.valueOf(strs[0]).equals(dangerAlarm.getLevel())) {
                    dangerAlarm.setColor(strs[1]);
                }
            }
        }
        return result;
    }

    /**
     * 获取灾害区域划分图片
     *
     * @param low_type 灾害类型
     * @return 图片路径
     */
    @RequestMapping(value = "getDisasterImage")
    @ResponseBody
    public String getDisasterImage(String low_type) {
        // 从配置文件获取图片路径
        String disaster_path = ConfigHelper.getProperty("disaster_path");
        File file = new File(disaster_path + low_type);
        File[] files = file.listFiles();
        String path = "";
        if (files != null && files.length != 0) {
            path = "/disasterImage/" + low_type + "/" + files[0].getName();
        }
        return path;
    }

}
