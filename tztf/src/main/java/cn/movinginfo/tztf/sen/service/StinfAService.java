package cn.movinginfo.tztf.sen.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.StinfA;
import cn.movinginfo.tztf.sen.mapper.StinfAMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import net.ryian.orm.service.support.datasource.annotation.DataSource;
import tk.mybatis.mapper.entity.Example;


/**
* @description:
* @author: autoCode
* @history:
*/
@DataSource("hyswSource")
@Component
public class  StinfAService {
	@Autowired
	private StinfAMapper mapper;
    
    public List<StinfA> getStinfAByStnm(String stnm) {
    	Example example = new Example(StinfA.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("stnm","%"+stnm+"%");
        criteria.andEqualTo("county","通州");
        return mapper.selectByExample(example);
    }
}
