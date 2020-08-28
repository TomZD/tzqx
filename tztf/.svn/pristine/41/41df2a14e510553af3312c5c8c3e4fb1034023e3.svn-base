package cn.movinginfo.tztf.sen.service;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.SenPermission;
import cn.movinginfo.tztf.sen.mapper.SenPermissionMapper;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

@Component
public class SenPermissionService extends BaseService<SenPermission, SenPermissionMapper>{
	
	public List<SenPermission> getOnePermission() {
		Example example = new Example(SenPermission.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("code","1");
		return mapper.selectByExample(example);
	}
	
	public List<SenPermission> getPermissionByNotId(Long id) {
		Example example = new Example(SenPermission.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andNotEqualTo("id", id);
		criteria.andEqualTo("code","1");
		return mapper.selectByExample(example);
	}
	
	public List<SenPermission> getPermissionByPid(int pid) {
		Example example = new Example(SenPermission.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("pid",pid);
		return mapper.selectByExample(example);
	}
	
	public List<SenPermission> getPermissionInId(List<Object> list) {
		Example example = new Example(SenPermission.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andIn("id", list);
		return mapper.selectByExample(example);
	}
}
