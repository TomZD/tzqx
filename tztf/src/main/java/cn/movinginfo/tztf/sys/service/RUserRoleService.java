package cn.movinginfo.tztf.sys.service;

import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sys.domain.RUserRole;
import cn.movinginfo.tztf.sys.mapper.RUserRoleMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  RUserRoleService extends BaseService<RUserRole,RUserRoleMapper> {

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<RUserRole> query(Map<String, String> paramMap) {
        Example example = new Example(RUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String userId = paramMap.get("userId");
        if (!StringUtils.isEmpty(userId)) {
            criteria.andLike("userId", "%" + userId+ "%");
        }
        return mapper.selectByExample(example);
    }

    /**
     * 根据人员编号获取数据
     * @param id
     * @return
     */
	public RUserRole getByUserId(int userId) {
		RUserRole rUserRole = new RUserRole();
		rUserRole.setValid(1);
		rUserRole.setUserId(userId);
        return mapper.selectOne(rUserRole);
	}
	
	 /**
     * 新建角色添加权限
     * @param id
     * @return
     */
	public int setByUserId(Long userId,int roleId) {
		RUserRole rUserRole = new RUserRole();
		rUserRole.setValid(1);
		rUserRole.setUserId(userId.intValue());
		rUserRole.setRoleId(roleId);
        return mapper.insert(rUserRole);
	}
}
