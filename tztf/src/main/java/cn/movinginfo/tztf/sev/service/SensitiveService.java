package cn.movinginfo.tztf.sev.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sev.domain.Sensitive;
import cn.movinginfo.tztf.sev.mapper.SensitiveMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Component
public class SensitiveService extends BaseService<Sensitive, SensitiveMapper> {

    public List<Sensitive> query(Map<String, String> paramMap) {
        Example example = new Example(Sensitive.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String word = paramMap.get("word");
        if (!StringUtils.isEmpty(word)) {
            criteria.andLike("word", "%" + word + "%");
        }
        example.setOrderByClause("id desc");
        return mapper.selectByExample(example);
    }

    public List<Sensitive> getKeyPeopleByPersonId(int id) {
        Example example = new Example(Sensitive.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return mapper.selectByExample(example);
    }
    
    
    public List<Sensitive> getAll() {
        Example example = new Example(Sensitive.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        return mapper.selectByExample(example);
    }

    public Sensitive selectByWord(String word){
        return mapper.selectByWord(word);
    }

}
