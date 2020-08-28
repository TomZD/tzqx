package cn.movinginfo.tztf.sev.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.sev.domain.Suser;
import cn.movinginfo.tztf.sev.mapper.SuserMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  SuserService extends BaseService<Suser,SuserMapper> {

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<Suser> query(Map<String, String> paramMap) {
        Example example = new Example(Suser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String serviceType = paramMap.get("serviceType");
        if (!StringUtils.isEmpty(serviceType)) {
            criteria.andLike("serviceType", "%" + serviceType+ "%");
        }
        return mapper.selectByExample(example);
    }

    public List<Long> getForecastSusers(String spotNo) {
        return mapper.getForecastSusers(spotNo);
    }
    
    /**
     * 根据id查询用户
     * @param id
     * @return 用户列表，实际容器内只有一个元素
     * @author 韩明睿
     */
    public List<Suser> queryById(String id) {
        Example example = new Example(Suser.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(id)) {
            criteria.andLike("id", "%" + id+ "%");
        }
        return mapper.selectByExample(example);
    }
    
    public List<Suser> queryByName(String name) {
        Example example = new Example(Suser.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name+ "%");
        }
        return mapper.selectByExample(example);
    }

    
   
}