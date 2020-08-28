package cn.movinginfo.tztf.sev.service;

import net.ryian.orm.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sev.domain.Personroup;
import cn.movinginfo.tztf.sev.domain.PersonroupExample;
import cn.movinginfo.tztf.sev.domain.PersonroupExample.Criteria;
import cn.movinginfo.tztf.sev.mapper.PersonroupMapper;
import cn.movinginfo.tztf.sys.domain.PeopleGroup;
import cn.movinginfo.tztf.sys.mapper.PeopleGroupMapper;

import java.util.List;

@Service
public class PersonGroupService extends BaseService<PeopleGroup, PeopleGroupMapper> {

	@Autowired
	private PersonroupMapper personroupMapper;
	
	public void insert(Personroup p) {
		
		personroupMapper.insertSelective(p);
	}

	public void updatePersonGroup(Personroup p) {
		
		personroupMapper.updateByPrimaryKeySelective(p);
	}

	public Personroup getById(int id) {
		
		return personroupMapper.selectByPrimaryKey(id);
	}

	public List<Personroup> findByDeptId(Long deptId) {
		PersonroupExample example = new PersonroupExample();
		Criteria criteria = example.createCriteria();
		criteria.andDeptIdEqualTo(deptId);
		example.setOrderByClause("create_time desc");
		return personroupMapper.selectByExample(example);
		
	}
	
	public List<Personroup> selectAll() {
		PersonroupExample example = new PersonroupExample();
		Criteria criteria = example.createCriteria();
		criteria.andValidEqualTo(1);
		return personroupMapper.selectByExample(example);
	}

	public void deleteById(int id) {
		personroupMapper.deleteByPrimaryKey(id);
		
	}





}
