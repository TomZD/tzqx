package cn.movinginfo.tztf.sev.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sev.domain.Emperson;
import cn.movinginfo.tztf.sev.domain.EmpersonExample;
import cn.movinginfo.tztf.sev.domain.EmpersonExample.Criteria;
import cn.movinginfo.tztf.sev.mapper.AreaResult;
import cn.movinginfo.tztf.sev.mapper.EmpersonMapper;

@Service
public class EmPersonService {
	
	@Autowired
	private EmpersonMapper mapper;

	public List<Emperson> getAllValid(String keyword, String sex) {
		EmpersonExample empersonExample = new EmpersonExample();
		Criteria criteria = empersonExample.createCriteria();
		criteria.andValidEqualTo(1);
		if(StringUtils.isNotEmpty(keyword)) {
			criteria.andNameLike("%" + keyword + "%");
		}
		if(StringUtils.isNotBlank(sex)) {
			criteria.andSexEqualTo(sex);
		}
		return mapper.selectByExample(empersonExample);
	}

	public void save(Emperson p) {
		mapper.insertSelective(p);
	}

	public void deleteById(int id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void update(Emperson p) {
		mapper.updateByPrimaryKeySelective(p);
	}

	public Emperson findById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

	public List<Emperson> getEmpersons(Long deptId) {
		EmpersonExample empersonExample = new EmpersonExample();
		Criteria criteria = empersonExample.createCriteria();
		criteria.andValidEqualTo(1);
		criteria.andAreaIdEqualTo(deptId.toString());
		return mapper.selectByExample(empersonExample);
	}

	public void deleteByIds(List<Integer> ids) {
		EmpersonExample empersonExample = new EmpersonExample();
		Criteria criteria = empersonExample.createCriteria();
		criteria.andIdIn(ids);
		List<Emperson> list = mapper.selectByExample(empersonExample);
		for (Emperson emperson : list) {
			emperson.setValid(0);
			mapper.updateByExampleSelective(emperson, empersonExample);
		}
	//	mapper.deleteByExample(empersonExample);
	}

	public List<AreaResult> getDistinctAreaId() {
		return mapper.getDisntictArea();
	}

	public AreaResult getByDeptId(Long deptId) {
		return mapper.getByDeptId(deptId);
	}

	public List<Emperson> getByAreaId(String areaId, String keyword, String sex) {
		EmpersonExample empersonExample = new EmpersonExample();
		Criteria criteria = empersonExample.createCriteria();
		criteria.andValidEqualTo(1);
		criteria.andAreaIdEqualTo(areaId);
		if(StringUtils.isNotEmpty(keyword)) {
			criteria.andNameLike("%" + keyword + "%");
		}
		if(StringUtils.isNotBlank(sex)) {
			criteria.andSexEqualTo(sex);
		}
		return mapper.selectByExample(empersonExample);
	}

	public List<Emperson> getSameDeptId(Long deptId) {
		EmpersonExample empersonExample = new EmpersonExample();
		Criteria criteria = empersonExample.createCriteria();
		criteria.andDepartmentidEqualTo(deptId);
		criteria.andValidEqualTo(1);
		return mapper.selectByExample(empersonExample);
	}

	public List<Emperson> getByIds(List<Integer> ids) {
		EmpersonExample empersonExample = new EmpersonExample();
		Criteria criteria = empersonExample.createCriteria();
		criteria.andIdIn(ids);
		return mapper.selectByExample(empersonExample);
	}

}
