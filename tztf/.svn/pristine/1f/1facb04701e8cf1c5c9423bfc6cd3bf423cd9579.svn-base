package cn.movinginfo.tztf.sen.service;



import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.Tabstation;
import cn.movinginfo.tztf.sen.mapper.TabstationMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  TabstationService extends BaseService<Tabstation,TabstationMapper> {

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<Tabstation> query(Map<String, String> paramMap) {
        Example example = new Example(Tabstation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String iiiii = paramMap.get("iiiii");
        if (!StringUtils.isEmpty(iiiii)) {
            criteria.andLike("iiiii", "%" + iiiii+ "%");
        }
	    example.setOrderByClause("id desc");
	    return mapper.selectByExample(example);
    }
    
    public Tabstation findTabStationinfoByIiiii(String iiiii) {
    	Tabstation tab = new Tabstation();
    	tab.setValid(1);
    	tab.setIiiii(iiiii);
    	return mapper.selectOne(tab);
    }
    
    public Tabstation geTabstationBy (String iiiii) {
        return mapper.getTabstation(iiiii);
    }
}
