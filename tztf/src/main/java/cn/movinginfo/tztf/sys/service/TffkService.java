package cn.movinginfo.tztf.sys.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sev.domain.Sensitive;
import cn.movinginfo.tztf.sev.mapper.SensitiveMapper;
import cn.movinginfo.tztf.sys.domain.Tffk;
import cn.movinginfo.tztf.sys.mapper.TffkMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Component
public class TffkService extends BaseService<Tffk, TffkMapper> {

    public List<Tffk> query(Map<String, String> paramMap) {
        Example example = new Example(Tffk.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        String userName = paramMap.get("userName");
        String userId = paramMap.get("userId");

        if (!StringUtils.isEmpty(userName)) {
            criteria.andLike("userName", "%" + userName + "%");
        }
        if (!StringUtils.isEmpty(userId)) {
            criteria.andEqualTo("userId", userId);
        }
        example.setOrderByClause("create_Date desc");
        return mapper.selectByExample(example);
    }

    public List<Tffk> getTffkByUserIdTemp(Long id) {
        return mapper.selectByUserId(id);
    }

    public List<Tffk> getTffkByUserId(Long id) {
        Example example = new Example(Tffk.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", id);
        criteria.andEqualTo("valid", "1");
        return mapper.selectByExample(example);
    }


    public List<Tffk> getAll() {
        Example example = new Example(Tffk.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        return mapper.selectByExample(example);
    }

    public Tffk selectByUserName(String userName) {
        return mapper.selectByUserName(userName);
    }

}
