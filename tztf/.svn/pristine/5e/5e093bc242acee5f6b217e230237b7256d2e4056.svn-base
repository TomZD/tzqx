package cn.movinginfo.tztf.sen.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.Alertrule;
import cn.movinginfo.tztf.sen.domain.AreaAlarm;
import cn.movinginfo.tztf.sen.domain.DangerPoint;
import cn.movinginfo.tztf.sen.domain.Tabstation;
import cn.movinginfo.tztf.sen.mapper.AreaalarmMapper;
import cn.movinginfo.tztf.sen.model.TownInfoExample.Criteria;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  AreaalarmService extends BaseService<AreaAlarm,AreaalarmMapper> {
	
	@Autowired
	private TabstationService tabstationService;
	
	@Autowired
	private AlertruleService alertruleService;

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<AreaAlarm> query(Map<String, String> paramMap) {
        Example example = new Example(AreaAlarm.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String town = paramMap.get("town");
        if (!StringUtils.isEmpty(town)) {
            criteria.andLike("town", "%" + town+ "%");
        }
	    example.setOrderByClause("id desc");
	    List<AreaAlarm> list = mapper.selectByExample(example);
	    for(AreaAlarm area : list) {
	    	String alertId = area.getAlertruleId();
	    	Alertrule alertrule = alertruleService.get(Long.parseLong(alertId));
	    	area.setAlertName(alertrule.getRuleExpr());
	    	String alertNameTwo = "";
			String alertTwoId = area.getAlertruleTwoId();
			if (!StringUtils.isEmpty(alertTwoId)) {
				Alertrule alertTwo = alertruleService.get(Long.parseLong(alertTwoId));
				alertNameTwo = alertTwo.getRuleExpr();
			}
			area.setAlertNameTwo(alertNameTwo);
			String alertNameThree="";
			String alertThreeId = area.getAlertruleThreeId();
			if (!StringUtils.isEmpty(alertThreeId)) {
				Alertrule alertThree = alertruleService.get(Long.parseLong(alertThreeId));
				alertNameThree = alertThree.getRuleExpr();
			}
			area.setAlertNameThree(alertNameThree);
			String relStation = area.getRelStation();
	    	String[] rels = relStation.split(",");
	    	String stationIiiii = "";
	    	for(String r : rels) {
	    		if(!StringUtils.isEmpty(r)) {
	    			String iiiii = tabstationService.get(Long.parseLong(r)).getIiiii();
		    		stationIiiii += iiiii+";";
	    		}
	    	}
	    	area.setIiiii(stationIiiii);
	    }
	    return list;
    }
    
    public List<AreaAlarm> getAreaAlarmByRelStation(String relStation) {
    	return mapper.getAreaAlarmByRelStation(relStation);
    }
    
    
    public void insertSelective(AreaAlarm areaAlarm) {
    	mapper.insertSelective(areaAlarm);
    }

    public void update(AreaAlarm areaAlarm) {
    	mapper.updateByPrimaryKeySelective(areaAlarm);
    }
    
    public AreaAlarm findAreaAlarmByTown(String town) {
    	AreaAlarm area = new AreaAlarm();
    	area.setValid(1);
    	area.setTown(town);
    	return mapper.selectOne(area);
    }
}
