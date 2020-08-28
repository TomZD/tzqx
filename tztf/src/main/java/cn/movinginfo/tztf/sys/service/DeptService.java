package cn.movinginfo.tztf.sys.service;

import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.common.utils.ConfigHelper;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.mapper.DeptMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class  DeptService extends BaseService<Depart,DeptMapper> {
	
	
	/**
	 * 列表页面获取数据库信息
	 */
	public List<Depart> query(Map<String, String> paramMap) {
        Example example = new Example(Depart.class);
        int id=Integer.parseInt(paramMap.get("id"));
        example.setOrderByClause("locate('"+id+"',id) desc, area_id asc");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String name = paramMap.get("name");
        String nationalUnitCode = paramMap.get("nationalUnitCode");
        String linker = paramMap.get("linker");
        String areaId = paramMap.get("areaId");
        if(!StringUtils.isEmpty(name)){
        	 criteria.andLike("name", "%" + name+ "%");
        }
        if(!StringUtils.isEmpty(areaId)){
       	 criteria.andEqualTo("areaId", areaId);
       }
        if(!StringUtils.isEmpty(nationalUnitCode)){
       	 criteria.andLike("nationalUnitCode", "%" + nationalUnitCode+ "%");
       }
        if(!StringUtils.isEmpty(linker)){
          	 criteria.andLike("linker", "%" + linker+ "%");
          }
        return mapper.selectByExample(example);
    }

	/**
	 * 根据条件查询分页
	 * @param
	 * @return
	 */
	public List<Depart> queryList() {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
	}
	public List<Depart> queryListByType(String type) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andNotEqualTo("deptType", 2);
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
	}
	
	public List<Depart> queryListByAreaId(String areaId) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andNotEqualTo("areaId", areaId);
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
	}
	
	public List<Depart> findListByAreaId(String areaId) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("areaId", areaId);
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
	}
	
	public List<Depart> queryListByAreaAndType(String area,String type) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("areaId",area);
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
	}
	
	public List<Depart> queryListByAreaAndTypeAndId(String area,String type,Long id) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andNotEqualTo("deptType", 2);
		criteria.andEqualTo("id",id);
		criteria.andEqualTo("areaId",area);
		criteria.andEqualTo("valid",1);
		return mapper.selectByExample(example);
	}

	public Depart findByName(String departName) {
		Depart depa = new Depart();
		depa.setValid(1);
		depa.setName(departName);
		return mapper.selectOne(depa);
	}

	public Depart findByTownId(String townid) {
		Depart depa = new Depart();
		depa.setValid(1);
		depa.setTownId(townid);
		return mapper.selectOne(depa);
	}
	
	public Depart findByCode(String code) {
		Depart depa = new Depart();
		depa.setValid(1);
		depa.setCode(code);
		return mapper.selectOne(depa);
	}
	
	public Depart findById(Long id) {
		Depart depa = new Depart();
		depa.setValid(1);
		depa.setId(id);
		return mapper.selectOne(depa);
	}
	
	public Depart getDeptById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public Depart getPublisher(String areaId){
		return mapper.getPublisher(areaId);
	}
	
	/**
	 * 根据发布渠道channelId获取部门
	 * @param channelId
	 * @return
	 */
	public Depart getPublishDept(Long channelId){
		return mapper.getPublishDept(channelId);
	}

	public List<Depart> getZtList() {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		example.setOrderByClause("ID asc");
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("deptType",1);
		return mapper.selectByExample(example);
	}

	public List<Depart> getdwList() {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("deptType",3);
		return mapper.selectByExample(example);
	}

	public List<Depart> getZxList() {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("deptType",2);
		return mapper.selectByExample(example);
	}

	public List<Depart>  getByUsername(String username) {
        Example example = new Example(Depart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",username);
        List<Depart> list = mapper.selectByExample(example);
        return  list;
	}

	public List<Depart> getAllDepaet() {
        Example example = new Example(Depart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid",1);
        //criteria.andEqualTo("areaId",244);
        List<Depart> list = mapper.selectByExample(example);
        return list;
	}
	
	public List<Depart> getDepartNotId(Long id) {
		Example example = new Example(Depart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid",1);
        criteria.andEqualTo("deptType",1);
        criteria.andNotEqualTo("id", id);
        return mapper.selectByExample(example);
	}

	public Depart getByDepartmentId(Long departmentId) {
        Example example = new Example(Depart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid",1);
        criteria.andEqualTo("id",departmentId);
        List<Depart> list = mapper.selectByExample(example);
        if(list.size() !=0) {
        	return list.get(0);
        }
		return null;
	}

	public List<Depart> getYjbList() {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("deptType",4);
		return mapper.selectByExample(example);
	}

	public List<Depart> getByArea(String area) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("areaId", area);
		criteria.andEqualTo("deptType", 2);
		return mapper.selectByExample(example);
	}
	public List<Depart> getByAreas(String area) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("areaId", area);
		return mapper.selectByExample(example);
	}

	public List<Depart> queryListAllVlue() {
		Example example = new Example(Depart.class);
		return mapper.selectByExample(example);
	}
	
	public List<Depart> getAllValueByDeptName(String name, Long id){
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("name", name);
		criteria.andNotEqualTo("id", id);
		return  mapper.selectByExample(example);
	}
	
	public List<Depart> getAllValueByCode(String code, Long id){
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("code", code);
		criteria.andNotEqualTo("id", id);
		return  mapper.selectByExample(example);
	}

	public List<Depart> getSelfDepts(Long deptId) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid",1);
		criteria.andEqualTo("id", deptId);
		return  mapper.selectByExample(example);
	}

	public List<Depart> getAllArea() {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);	
		return mapper.selectByExample(example);
	}
	
	public Depart getDepartByAreaId(String areaId) {
		return mapper.getDepartByAreaId(areaId);
	}
	
	public List<Depart> getDepartByNotId(Long id) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andNotEqualTo("id", id);
		return mapper.selectByExample(example);
	}
	
	public List<Depart> getDepartByNotIdAndCenterId(Long id) {
		Example example = new Example(Depart.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", 1);
		criteria.andNotEqualTo("id", id);
		criteria.andEqualTo("deptType",1);//发布主体
		return mapper.selectByExample(example);
	}
	
	public List<String> getIdByname(String name) {
		return mapper.getIdByName(name);
	}

	public List<Depart> getDepartAll() {
	      Depart depart = new Depart();
	      depart.setValid(1);
		return mapper.getDepartAll();
	}

	public Depart getDepart(Long id){
		return mapper.getDepart(id);
	}

	public List<Depart> getDepartName( ){
		return mapper.getDepartName();
	}
}
