package cn.movinginfo.tztf.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sys.domain.Feedback;
import cn.movinginfo.tztf.sys.mapper.FeedbackMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  FeedbackService extends BaseService<Feedback,FeedbackMapper> {

    @Resource
    private FeedbackMapper feedbackMapper;

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<Feedback> query(Map<String, String> paramMap) {
        Example example = new Example(Feedback.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String alarmId = paramMap.get("alarmId");
        if (!StringUtils.isEmpty(alarmId)) {
            criteria.andLike("alarmId", "%" + alarmId+ "%");
        }
	    example.setOrderByClause("id");
	    return mapper.selectByExample(example);
    }

    public PageInfo<Alarm> selectTownAlarm(String townId, String areaId, Long deptId, Timestamp startDate, Timestamp endDate, String page){
        PageHelper.startPage(Integer.parseInt(page), 10);
        List<Alarm> list = feedbackMapper.selectTownAlarm(townId, areaId, deptId, startDate, endDate);
        return new PageInfo<>(list);
    }

    public PageInfo<Alarm> selectHistoryAlarm(String townId, String areaId, Long deptId, Timestamp startDate, Timestamp endDate, String page){
        PageHelper.startPage(Integer.parseInt(page), 10);
        List<Alarm> list = feedbackMapper.selectHistoryAlarm(townId, areaId, deptId, startDate, endDate);
        return new PageInfo<>(list);
    }
}
