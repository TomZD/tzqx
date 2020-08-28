package cn.movinginfo.tztf.sev.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sev.domain.DingDingGroup;
import cn.movinginfo.tztf.sev.domain.TVFax;
import cn.movinginfo.tztf.sev.mapper.TVFaxMapper;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

@Component
public class TVFaxService extends BaseService<TVFax, TVFaxMapper> {

    public List<TVFax> query(Map<String, String> paramMap) {

        String tvName = paramMap.get("tvName");//获取前台传过来的参数
        Example example = new Example(TVFax.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        if(!StringUtils.isEmpty(tvName)) {
            criteria.andLike("tvName","%"+tvName+"%");//模糊查询
        }
        return mapper.selectByExample(example);
    }

    public TVFax getDepartFaxByName(String tvName) {
    	TVFax tv = new TVFax();
    	tv.setTvName(tvName);
    	tv.setValid(1);
    	return mapper.selectOne(tv);
    }
    /**
     * 获取所有传真
     * @return
     */
    public List<TVFax> getAll()
    {
        Example example = new Example(TVFax.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        return mapper.selectByExample(example);
    }
}
