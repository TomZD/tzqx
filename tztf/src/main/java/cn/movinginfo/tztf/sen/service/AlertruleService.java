package cn.movinginfo.tztf.sen.service;




import net.ryian.orm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.Alertrule;
import cn.movinginfo.tztf.sen.mapper.AlertruleMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  AlertruleService extends BaseService<Alertrule,AlertruleMapper> {
	@Autowired
	private ElementinfoService elementinfoService;

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<Alertrule> query(Map<String, String> paramMap) {
        Example example = new Example(Alertrule.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
	    example.setOrderByClause("id desc");
	    List<Alertrule> list = mapper.selectByExample(example);
	    for(Alertrule alert : list) {
	    	String elementIds = alert.getElementIds();
	    	String[] eleIds = elementIds.split(",");
	    	String elementName ="";
	    	for(String ele : eleIds) {
	    		 elementName += elementinfoService.get(Long.parseLong(ele)).getElementName()+";";
	    	}
	    	alert.setElementName(elementName.substring(0, elementName.length()-1));
	    } 
	    return list;
    }
    
    public Alertrule findByruleExpr(String ruleExpr) {
    	Alertrule alert = new Alertrule();
    	alert.setValid(1);
    	alert.setRuleExpr(ruleExpr);
		return mapper.selectOne(alert);
	}
    
    public List<Alertrule> getRuleListByNotId(Long id) {
    	Example example = new Example(Alertrule.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andNotEqualTo("id", id);
        return mapper.selectByExample(example);
    }
    
    public void delete(Long id) {
    	mapper.deleteByPrimaryKey(id);
    }
    
    public List<Alertrule> getAlertruleByElementId(String elementId) {
    	return mapper.getAlertruleByElementId(elementId);
    }
}
