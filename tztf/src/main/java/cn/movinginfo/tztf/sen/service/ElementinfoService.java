package cn.movinginfo.tztf.sen.service;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.sen.domain.Elementinfo;
import cn.movinginfo.tztf.sen.mapper.ElementinfoMapper;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.service.DictService;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  ElementinfoService extends BaseService<Elementinfo,ElementinfoMapper> {
	@Autowired
	private DictService dictService;

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<Elementinfo> query(Map<String, String> paramMap) {
        Example example = new Example(Elementinfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String elementId = paramMap.get("elementId");
        if (!StringUtils.isEmpty(elementId)) {
            criteria.andLike("elementId", "%" + elementId+ "%");
        }
	    example.setOrderByClause("id desc");
	    List<Elementinfo> list = mapper.selectByExample(example);
	    for(Elementinfo ele : list) {
	    	String isdec = ele.getIsDec();
	    	if("1".equals(isdec)) {
	    		ele.setDecName("是");
	    	}else if("0".equals(isdec)){
	    		ele.setDecName("否");
	    	}
	    	String isForecast = ele.getIsForecast();
	    	if("1".equals(isForecast)) {
	    		ele.setForecastName("预报");
	    	}else if("0".equals(isForecast)) {
	    		ele.setForecastName("实况");
	    	}
	    	String type=ele.getElementType();
	    	String typeName=dictService.getContent("element_type", type);
	    	ele.setTypeName(typeName);
	    }
	    return list;
    }
    
    
    public Elementinfo findByElementId(String elementId) {
    	Elementinfo ele = new Elementinfo();
    	ele.setValid(1);
    	ele.setElementId(elementId);
		return mapper.selectOne(ele);
	}
    
    public Elementinfo findByElementName(String elementName) {
    	Elementinfo ele = new Elementinfo();
    	ele.setValid(1);
    	ele.setElementName(elementName);
		return mapper.selectOne(ele);
	}
    
    public Elementinfo getElementByElementId(String elementId) {
    	return mapper.getElementByElementId(elementId);
    }
}
