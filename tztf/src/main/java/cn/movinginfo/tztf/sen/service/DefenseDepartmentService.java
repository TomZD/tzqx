package cn.movinginfo.tztf.sen.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.DefenseDepartment;
import cn.movinginfo.tztf.sen.mapper.DefenseDepartmentMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  DefenseDepartmentService extends BaseService<DefenseDepartment,DefenseDepartmentMapper> {

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<DefenseDepartment> query(Map<String, String> paramMap) {
        Example example = new Example(DefenseDepartment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String name = paramMap.get("name");
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name+ "%");
        }
	    example.setOrderByClause("id");
	    return mapper.selectByExample(example);
    }
    
    public List<DefenseDepartment> getAllDefenseDepartment() {
    	Example example = new Example(DefenseDepartment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        return mapper.selectByExample(example);
    }
    
    public List<DefenseDepartment> getDefenseDepartmentNotId(Long id) {
    	Example example = new Example(DefenseDepartment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andNotEqualTo("id", id);
        return mapper.selectByExample(example);
    }
}
