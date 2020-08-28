package cn.movinginfo.tztf.sen.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import net.ryian.orm.service.support.datasource.annotation.DataSource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.Stinf;
import cn.movinginfo.tztf.sen.mapper.StinfMapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  StinfService{
	@Resource
	private StinfMapper mapper;
    
    public List<Stinf> getAll() {
    	Example example = new Example(Stinf.class);
        return mapper.selectByExample(example);
    }
    
    public void update(Stinf stinf) {
    	mapper.updateStinf(stinf.getStcd().trim(), stinf.getId(),stinf.getStnm().trim());
    }

    public List<Stinf> selectList(){
        return mapper.selectList();
    }

    public Stinf selectByStcd(String stcd) {
        return mapper.selectByStcd(stcd);
    }
}
