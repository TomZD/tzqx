package cn.movinginfo.tztf.sm.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sm.domain.RDepartmentEnventType;
import cn.movinginfo.tztf.sm.mapper.RDepartmentEnventTypeMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  RDepartmentEnventTypeService extends BaseService<RDepartmentEnventType,RDepartmentEnventTypeMapper> {

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<RDepartmentEnventType> query(Map<String, String> paramMap) {
        Example example = new Example(RDepartmentEnventType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String departmentId = paramMap.get("departmentId");
        if (!StringUtils.isEmpty(departmentId)) {
            criteria.andLike("departmentId", "%" + departmentId+ "%");
        }
return mapper.selectByExample(example);
    }

    /**
     * 根据部门编号获取所有对应信息
     * @param parseLong
     * @return
     */
	public List<RDepartmentEnventType> getByDepartmentId(String departmentId) {
		Example example = new Example(RDepartmentEnventType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        if (!StringUtils.isEmpty(departmentId)) {
            criteria.andEqualTo("departmentId", departmentId);
        }
        return mapper.selectByExample(example);
	}

	/**
	 * 根据部门编号和类别编号获取指定的关联数据
	 * @param departmentId
	 * @param typeId
	 * @return
	 */
	public RDepartmentEnventType getByDeptIdAndType(String departmentId, String typeId) {
		RDepartmentEnventType rDepartmentEnventType = new RDepartmentEnventType();
		if(!StringUtils.isEmpty(departmentId)&&!StringUtils.isEmpty(typeId)){
			rDepartmentEnventType.setDepartmentId(Long.parseLong(departmentId));
			rDepartmentEnventType.setEventTypeId(Long.parseLong(typeId));
			return mapper.selectOne(rDepartmentEnventType);
		}else{
			return null;
		}
	}

	
	public void deleteById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
}
