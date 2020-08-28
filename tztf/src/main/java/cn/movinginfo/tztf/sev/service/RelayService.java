package cn.movinginfo.tztf.sev.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sev.domain.Relay;
import cn.movinginfo.tztf.sev.mapper.RelayMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class RelayService extends BaseService<Relay, RelayMapper> {

    /**
     * 根据条件查询分页
     *
     * @param paramMap
     * @return
     */
    public List<Relay> query(Map<String, String> paramMap) {
        Example example = new Example(Relay.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        String alarmId = paramMap.get("alarmId");
        if (!StringUtils.isEmpty(alarmId)) {
            criteria.andLike("alarmId", "%" + alarmId + "%");
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public Relay selectByAlarmTown(Integer alarm_id, Integer town_id) {
        return mapper.selectByAlarmTown(alarm_id, town_id);
    }

    public List<Relay> selectByAlarmId(Integer alarm_id) {
        return mapper.selectByAlarmId(alarm_id);
    }
}
