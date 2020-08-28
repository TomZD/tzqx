package cn.movinginfo.tztf.sys.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sys.domain.Dict;
import cn.movinginfo.tztf.sys.mapper.DictMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by allenwc on 15/9/8.
 */
@Component
public class DictService extends BaseService<Dict,DictMapper> {


    @Override
    public List<Dict> query(Map<String,String> paramMap) {
        Example example = new Example(Dict.class);
        Example.Criteria criteria = example.createCriteria();
        String keyName = paramMap.get("keyName");
        if (!StringUtils.isEmpty(keyName)) {
            criteria.andLike("keyName","%"+keyName+"%");
        }
        String value = paramMap.get("value");
        if(!StringUtils.isEmpty(value)) {
            criteria.andLike("value","%"+value+"%");
        }
        return mapper.selectByExample(example);
    }

    /**
     * 根据数据字典类型获取相应数据字典项
     * @param key
     * @return
     */
    @Cacheable(value = "sys",key = "#key")
    public Map<String ,Dict> getDictsByKey(String key){
        Map<String ,Dict> dicMap = new TreeMap<String,Dict>();
        Dict d = new Dict();
        d.setKeyName(key);
        d.setValid(1);
        List<Dict> dicts = mapper.select(d);
        for(Dict dict : dicts) {
            dicMap.put(dict.getValue(), dict);
        }
        return dicMap;
    }

    public List<String> getKeyNames() {
        return mapper.getKeyNames();
    }

    public List<Dict> getDicList(String key_name) {
    	 Dict d = new Dict();
    	 d.setValid(1);
    	 d.setKeyName(key_name);
    	 List<Dict> dicts = mapper.select(d);
    	 return dicts;
    }
    
    
    public String getContent(String key_name,String value) {
   	 
   	   return mapper.getContent(key_name,value);
   }
    
    public String getContentNotValid(String key_name,String value) {
    	return mapper.getContentNotValid(key_name, value);
    }
    
    public String getValue(String key_name,String content) {
      	 
    	   return mapper.getValue(key_name,content);
    }
    
    public String getMemo(String key_name, String value) {
    	return mapper.getMemo(key_name, value);
    }
    
    public List<Dict> getElementTypeByNotValue(String value) {
    	Example example = new Example(Dict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("keyName", "element_type");
        criteria.andEqualTo("valid", 1);
        criteria.andNotEqualTo("value", value);
        return mapper.selectByExample(example);
    }

	public Dict findByCond(String areaId) {
        Example example = new Example(Dict.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("keyName", "area_id");
        criteria.andEqualTo("valid", 1);
        criteria.andEqualTo("value", areaId);
		return mapper.selectByExample(example).get(0);
	}
	
	public List<Dict> getAllAreas(){
		Example example = new Example(Dict.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("keyName", "area_id");
		criteria.andEqualTo("valid", 1);
		return mapper.selectByExample(example);
	}
}
