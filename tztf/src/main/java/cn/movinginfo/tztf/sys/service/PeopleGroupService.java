package cn.movinginfo.tztf.sys.service;

import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.PeopleGroup;
import cn.movinginfo.tztf.sys.mapper.PeopleGroupMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class PeopleGroupService extends BaseService<PeopleGroup, PeopleGroupMapper> {

    public List<PeopleGroup> query(Map<String, String> paramMap) {
        String name = paramMap.get("name");//获取前台传过来的参数
        Example example = new Example(PeopleGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        if(!StringUtils.isEmpty(name)) {
            criteria.andLike("name","%"+name+"%");//模糊查询
        }
        return mapper.selectByExample(example);
    }

    public List<PeopleGroup> getListGroupAll(){
        return mapper.selectAll();
    }



    public List<PeopleGroup> getDepartNotId(Long id) {
        Example example = new Example(Depart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid",1);
        criteria.andEqualTo("deptType",1);
        criteria.andNotEqualTo("id", id);
        return mapper.selectByExample(example);
    }

    public PeopleGroup getQueryGroup(Long id) {
       return  mapper.getQueryGroup(id);
    }

    public List<PeopleGroup> getQueryGroupAll(){
        return mapper.getQueryGroupAll();
    }

//    public DepartFax getDepartFaxBydepart(String depart) {
//        DepartFax df = new DepartFax();
//        df.setDepart(depart);
//        df.setValid(1);
//        return mapper.selectOne(df);
//    }

    public PeopleGroup getPeopleGroupBy(String name) {
        PeopleGroup pg = new PeopleGroup();
        pg.setName(name);
        pg.setValid(1);
        return mapper.selectOne(pg);
    }
 }
