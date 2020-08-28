package cn.movinginfo.tztf.sen.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.common.utils.ConfigExportHelper;
import cn.movinginfo.tztf.sen.domain.PersonType;
import cn.movinginfo.tztf.sen.mapper.PersonTypeMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  PersonTypeService extends BaseService<PersonType,PersonTypeMapper> {

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<PersonType> query(Map<String, String> paramMap) {
        Example example = new Example(PersonType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String name = paramMap.get("name");
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name+ "%");
        }
	    example.setOrderByClause("id");
	    return mapper.selectByExample(example);
    }
    
    public List<PersonType> getAllPersonType() {
    	 Example example = new Example(PersonType.class);
         Example.Criteria criteria = example.createCriteria();
         criteria.andEqualTo("valid","1");
         return mapper.selectByExample(example);
    }
    
    public List<PersonType> getPersonTypeNotId(Long id) {
    	Example example = new Example(PersonType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andNotEqualTo("id",id);
        return mapper.selectByExample(example);
    }
    
    public List<PersonType> getTypeInId() {
    	String typeIds = ConfigExportHelper.getProperty("typeIds");
    	String[] types = typeIds.split(",");
    	List list=Arrays.asList(types);
    	Example example = new Example(PersonType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andIn("id", list);
        return mapper.selectByExample(example);
    }
    
    public List<PersonType> getTypeInIdNotId(String id) {
    	String typeIds = ConfigExportHelper.getProperty("typeIds");
    	typeIds = (","+typeIds + ",").replace(","+id+",", ",");
    	String[] types = typeIds.split(",");
    	List list=Arrays.asList(types);
    	Example example = new Example(PersonType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andIn("id", list);
        return mapper.selectByExample(example);
    }
}
