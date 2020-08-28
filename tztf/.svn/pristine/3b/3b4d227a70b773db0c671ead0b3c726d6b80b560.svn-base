package cn.movinginfo.tztf.sen.service;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;
import java.util.*;

import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.DangerAlarm;
import cn.movinginfo.tztf.sen.domain.DangerAlarmDetail;
import cn.movinginfo.tztf.sen.mapper.DangerAlarmMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;


/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class DangerAlarmService extends BaseService<DangerAlarm, DangerAlarmMapper> {

    @Resource
    private DangerAlarmMapper dangerAlarmMapper;

    /**
     * 根据条件查询分页
     *
     * @param paramMap
     * @return
     */
    public List<DangerAlarm> query(Map<String, String> paramMap) {
        Example example = new Example(DangerAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        String pointId = paramMap.get("pointId");
        if (!StringUtils.isEmpty(pointId)) {
            criteria.andLike("pointId", "%" + pointId + "%");
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public int deleteDangerAlarmByTime(Date date) {
        Example example = new Example(DangerAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        criteria.andLessThanOrEqualTo("alarmTime", date);
        return mapper.deleteByExample(example);
    }

    public DangerAlarm findDangerAlarmByTimeAndPointId(Date date, Long pointId) {
        DangerAlarm alarm = new DangerAlarm();
        alarm.setAlarmTime(date);
        alarm.setPointId(pointId);
        return mapper.selectOne(alarm);
    }

    public DangerAlarm findDangerAlarmByTimeAndAreaId(Date date, Long areaId) {
        DangerAlarm alarm = new DangerAlarm();
        alarm.setAlarmTime(date);
        alarm.setAreaId(areaId);
        return mapper.selectOne(alarm);
    }

    /**
     * 地质灾害 当前风险
     */
    public List<DangerAlarmDetail> getCurrentRisk(String currentTime, String before10) {
        List<DangerAlarmDetail> list = mapper.getCurrentRisk(currentTime, before10);
        return list;
    }


    /**
     * 地质灾害 12时风险
     */
    public List<DangerAlarmDetail> getTimeOf12Risk(String timeOf12) {
        List<DangerAlarmDetail> list = mapper.getTimeOf12Risk(timeOf12);
        return list;
    }

    /**
     * 地质灾害 24时风险
     */
    public List<DangerAlarmDetail> getTimeOf24Risk(String timeOf24) {
        List<DangerAlarmDetail> list = mapper.getTimeOf24Risk(timeOf24);
        return list;
    }

    /**
     * 地质灾害 最近两小时风险
     */
    public List<DangerAlarmDetail> getRecentRisk(String before120, String currentTime) {
        List<DangerAlarmDetail> list = mapper.getRecentRisk(before120, currentTime);
        return list;
    }

    /**
     * 获取实况风险数据
     *
     * @return 列表
     */
    public List<DangerAlarm> getLiveSituation(Integer hour) {
        //获取当前时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp time = new Timestamp(calendar.getTimeInMillis());
        return dangerAlarmMapper.getLiveSituation(time);
    }

    /**
     * 获取预报风险数据
     *
     * @param element_id 风险要素id
     */
    public List<DangerAlarm> getPrediction(Integer element_id, Integer hour) {
        //获取当前时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Timestamp minTime = new Timestamp(calendar.getTimeInMillis());
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        Timestamp maxTime = new Timestamp(calendar.getTimeInMillis());
        return dangerAlarmMapper.getPrediction(element_id, minTime, maxTime);
    }

    /**
     * 当前风险 动态获取时间轴
     */
    public List<DangerAlarmDetail> getAlarmTime() {
        List<DangerAlarmDetail> list = mapper.getAlarmTime();
        return list;
    }

}
