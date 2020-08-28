package cn.movinginfo.tztf.sev.service;

import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sev.domain.DepartFax;
import cn.movinginfo.tztf.sev.domain.Sensitive;
import cn.movinginfo.tztf.sev.mapper.DepartFaxMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Component
public class DepartFaxService extends BaseService<DepartFax, DepartFaxMapper> {

    public List<DepartFax> query(Map<String, String> paramMap) {

        String depart = paramMap.get("depart");//获取前台传过来的参数
        Example example = new Example(DepartFax.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        if(!StringUtils.isEmpty(depart)) {
            criteria.andLike("depart","%"+depart+"%");//模糊查询
        }
        return mapper.selectByExample(example);
    }

    public DepartFax getDepartFaxBydepart(String depart) {
    	DepartFax df = new DepartFax();
    	df.setDepart(depart);
    	df.setValid(1);
    	return mapper.selectOne(df);
    }

}
