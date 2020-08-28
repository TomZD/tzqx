package cn.movinginfo.tztf.sen.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.domain.FileName;
import cn.movinginfo.tztf.sen.domain.Menu;
import cn.movinginfo.tztf.sen.domain.RnflR;
import cn.movinginfo.tztf.sen.domain.Stinf;
import cn.movinginfo.tztf.sen.service.MenuService;
import cn.movinginfo.tztf.sen.service.RnflRService;
import cn.movinginfo.tztf.sen.service.StinfAService;
import cn.movinginfo.tztf.sen.service.StinfService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @description:Stinf action
 * @author: autoCode
 * @history:
 */
@Controller
@RequestMapping("/sen/stinf")
public class StinfAction {

    @Autowired
    private StinfAService stinfAService;
    @Autowired
    private StinfService stinfService;
    @Autowired
    private RnflRService rnflRService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("update")
    @ResponseBody
    public void update() {
        List<Stinf> list = stinfService.getAll();
        for (Stinf l : list) {
            stinfService.update(l);
//			List<StinfA> sList = stinfAService.getStinfAByStnm(stnm);
//			if(sList.size() != 0) {
//				List<Stinf> lists = stinfService.getAll();
//				l.setStcd(sList.get(0).getStcd());
//				stinfService.update(l);
//			}

        }
    }

    /**
     * 获取黄岩水文站的数据
     *
     * @return
     */
    @RequestMapping("/getStinf")
    @ResponseBody
    public List<Stinf> getStinf() {
        List<Stinf> stinfList = stinfService.getAll();
        for (Stinf stinf : stinfList) {
            String stcd = stinf.getStcd();
            if (stcd != null) {
//				RnflR rnflR = rnflRService.getRnflRByStcd(stcd).get(0);
                RnflR rnflR = rnflRService.getFirstRnflRByStcd(stcd);
                stinf.setDtrn(rnflR.getDtrn());
            }
        }
        return stinfList;
    }

    /**
     * 水文站时间轴
     *
     * @return 时间轴列表
     */
    @RequestMapping("/getTimeAxis")
    @ResponseBody
    public List<FileName> getTimeAxis(String type) {
        List<FileName> result = new ArrayList<>();
        //从数据库读取时间轴个数
        Integer timeAxisNum = Integer.parseInt(ConfigHelper.getProperty("time_axis_num"));
        List<Timestamp> list = rnflRService.getTimeAxis(timeAxisNum);
        Menu menu = menuService.selectByType(type);
        for (Timestamp timestamp : list) {
            FileName fileName = new FileName();
            Date date = null;
            try {
                date = timestamp;
            } catch (Exception e) {
                System.out.println("日期转换失败");
            }
            String time = DateUtil.format(date, "yyyy年MM月dd日 HH时mm分");
            fileName.setTime(time);
            fileName.setFileName(time);
            fileName.setTitle(menu.getName() + time);
            result.add(fileName);
        }
        return result;
    }

    /**
     * 水文站地图数据
     *
     * @param type 查询类型 1过去一小时降水 2过去两小时降水 3过去三小时降水
     * @param time 水文站最新时间
     * @return Stinf对象按降水倒序排列
     */
    @RequestMapping("/getRnflData")
    @ResponseBody
    public List<Stinf> getRnflData(String type, String time) {
        //查询降水量小时数
        int num = Integer.valueOf(type.replace("hour_R_H", ""));
        //获取要查询的时间轴列表
        List<Timestamp> timeStamps = new ArrayList<>();
        Date date = DateUtil.parse(time, "yyyy年MM月dd日 HH时mm分");
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            for (int i = 0; i < num; i++) {
                Timestamp hour = new Timestamp(calendar.getTimeInMillis());
                timeStamps.add(hour);
                calendar.add(Calendar.HOUR_OF_DAY, -1);
            }
        }
        //查询每个站点降水量
        List<Stinf> stinfs = rnflRService.selectDtrn(timeStamps);
        //按降水量倒叙排列
        stinfs.sort((a, b) -> {
            BigDecimal dtrn1 = a.getValue();
            BigDecimal dtrn2 = b.getValue();
            return dtrn2.compareTo(dtrn1);
        });
        return stinfs;
    }

    /**
     * 查询单个站点24小时的数据
     *
     * @param stcd 站点
     * @param time 时间
     * @return 返回RnflR集合
     */
    @RequestMapping("/getStcdData")
    @ResponseBody
    public List<Map<String, Object>> getStcdData(String stcd, String time) {
        List<Map<String, Object>> result = new ArrayList<>();
        Date date = DateUtil.parse(time, "yyyy年MM月dd日 HH时mm分");
        Calendar calendar = Calendar.getInstance();
        assert date != null;
        calendar.setTime(date);
        Timestamp day = new Timestamp(calendar.getTimeInMillis());
        //将数据放入map中
        List<RnflR> rnflRS = rnflRService.selectRnfirList(day, stcd);
        Stinf stinf = stinfService.selectByStcd(stcd);
        for (RnflR rnflR : rnflRS) {
            Map<String, Object> map = new HashMap<>();
            String hour = DateUtil.format(rnflR.getYmdhm(), "HH");
            map.put("time", Integer.valueOf(hour)+"时");
            map.put("code", stinf.getStnm());
            map.put("value", rnflR.getDtrn());
            result.add(map);
        }
        //将数组倒过来 按时间顺序排列
        Collections.reverse(result);
        return result;
    }

}
