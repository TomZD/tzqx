package cn.movinginfo.tztf.sen.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.DeptPermission;
import cn.movinginfo.tztf.sen.mapper.DeptPermissionMapper;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.UserXt;
import cn.movinginfo.tztf.sys.service.DeptService;
import cn.movinginfo.tztf.sys.service.UserXtService;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  DeptPermissionService extends BaseService<DeptPermission,DeptPermissionMapper> {
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private SenPermissionService permissionService;

	@Autowired
	private UserXtService userXtService;

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<DeptPermission> query(Map<String, String> paramMap) {
        Example example = new Example(DeptPermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String deptId = paramMap.get("deptId");
        if(!StringUtils.isEmpty(deptId)) {
        	List<String> ids =  deptService.getIdByname("%"+deptId + "%");
        	List<Object> obj = (List)ids;
        	System.out.println(ids);
            if (ids.size() != 0) {
                criteria.andIn("deptId", obj);
            }
        }
	    example.setOrderByClause("id desc");
	    List<DeptPermission> list= mapper.selectByExample(example);
	    for(DeptPermission dp : list) {
	    	Long deptID = dp.getDeptId();
	    	Long permissionId = dp.getPermissionId();
			//Depart test = deptService.get(deptID);
			UserXt userXt = userXtService.findUserById(deptID);
			String deptName = userXt.getName();
	    	String menuName = permissionService.get(permissionId).getName();
	    	String twoPermissionId = dp.getTwoPermissionId();
	    	String twoMenuName = "";
	    	if(!StringUtils.isEmpty(twoPermissionId)) {
	    		String[] twoIds = twoPermissionId.split(",");
		    	for(int x=0;x<twoIds.length;x++) {
		    		 twoMenuName += permissionService.get(Long.parseLong(twoIds[x])).getName() + ",";
		    	}
	    	}
	    	dp.setTwoMenuName(twoMenuName==""?"":twoMenuName.substring(0, twoMenuName.length()-1));
	    	dp.setDeptName(deptName);
	    	dp.setMenuName(menuName);
	    }
	    return list;
    }
    
    public List<DeptPermission> getDeptPermissionBydeptId(Long deptId) {
    	Example example = new Example(DeptPermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andEqualTo("deptId",deptId);
        return mapper.selectByExample(example);
    }
    
    public List<DeptPermission> getPermissionByDeptIdAndPermissId(Long deptId,Long permissionId) {
    	Example example = new Example(DeptPermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andEqualTo("deptId",deptId);
        criteria.andEqualTo("permissionId",permissionId);
        return mapper.selectByExample(example);
    }
}
