package cn.movinginfo.tztf.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.mapper.UserMapper;
import net.ryian.commons.DigestUtils;
import net.ryian.commons.EncodeUtils;
import net.ryian.core.SystemConfig;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class  UserService extends BaseService<User,UserMapper> {

	/**
	 * 根据条件查询分页
	 * @param paramMap
	 * @return
	 */
	public List<User> query(Map<String, String> paramMap) {
		Assert.notNull(paramMap);
		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid","1");
		String name = paramMap.get("name");
		String areaId =paramMap.get("areaId");
		if(!StringUtils.isEmpty(areaId)) {
			criteria.andEqualTo("areaId", areaId);
		}
		if (!StringUtils.isEmpty(name)) {
			criteria.andLike("name","%"+name+"%");
		}
		String departmentId = paramMap.get("departmentId");
		if (!StringUtils.isEmpty(departmentId)) {
			criteria.andEqualTo("departmentId", departmentId);
		}
		return mapper.selectByExample(example);
	}

	public User findUserByUserName(String userName) {
		User user = new User();
		user.setValid(1);
		user.setUserName(userName);
		return mapper.selectOne(user);
	}
	
	public User findUserByDepartment(Long departmentId) {
		User user = new User();
		user.setValid(1);
		user.setIsUse("1");
		//user.setId(id);
		user.setDepartmentId(departmentId);
		return mapper.selectOne(user);
	}

	@Override
	public Long saveOrUpdate(User user) {
		if (user.getId() == null) {
			if(StringUtils.isEmpty(user.getPassword())) {
				user.encryptUserPassword(SystemConfig.INSTANCE.getValue("DEFAULT_PASSWORD"));
			}
		}
		return super.saveOrUpdate(user);
	}

	public User validatePwd(String userName,String pwd) {
		User user = findUserByUserName(userName);
		if(user == null)
			return null;
		byte[] hashPassword = DigestUtils.sha1(pwd.getBytes(), EncodeUtils.decodeHex(user.getSalt()), User.HASH_INTERATIONS);
		if(EncodeUtils.encodeHex(hashPassword).equals(user.getPassword())) {
			return user;
		} else {
			return null;
		}
	}
	
	public User validateByDepa(Long departmentId,String pwd) {
		User user = findUserByDepartment(departmentId);
		if(user == null)
			return null;
		byte[] hashPassword = DigestUtils.sha1(pwd.getBytes(), EncodeUtils.decodeHex(user.getSalt()), User.HASH_INTERATIONS);
		if(EncodeUtils.encodeHex(hashPassword).equals(user.getPassword())) {
			return user;
		} else {
			return null;
		}
	}

	/**
	 * 人员管理列表页面列表数据
	 * @param parameterMap
	 * @return
	 */
	public PageInfo<List<User>> queryUserPaged(Map<String, String> parameterMap) {
		String rowsStr = parameterMap.get("rows") == null ? null
                : (String) parameterMap.get("rows");
        String pageStr = parameterMap.get("page") == null ? null
                : (String) parameterMap.get("page");
    	// 第几页
        int page = 1;
        // 每页显示数量
        int rows = 10;
        try {
            page = pageStr != null ? Integer.valueOf(pageStr) : page;
            rows = rowsStr != null ? Integer.valueOf(rowsStr) : rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        String statement = "queryUserPaged";
		List<User> list = sqlSession.selectList(statement, parameterMap);
        return new PageInfo(list);
	}

	/**
	 * 根据部门编号获取所有该部门人员
	 * @param departmentId
	 * @return
	 */
	public List<User> getByDepartmentId(Long departmentId) {
		Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        if(departmentId != null){
        	criteria.andEqualTo("departmentId", departmentId);
        }
        return mapper.selectByExample(example);
	}
	
	
	public PageInfo<List<User>> queryPageds(Map<String, String> parameterMap) {
		String rowsStr = parameterMap.get("rows") == null ? null
                : (String) parameterMap.get("rows");
        String pageStr = parameterMap.get("page") == null ? null
                : (String) parameterMap.get("page");
    	// 第几页
        int page = 1;
        // 每页显示数量
        int rows = 10;
        try {
            page = pageStr != null ? Integer.valueOf(pageStr) : page;
            rows = rowsStr != null ? Integer.valueOf(rowsStr) : rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        //String statement = "queryUserPaged";
		List<User> list = query(parameterMap);
        return new PageInfo(list);
	}

	

	public List<User> getByDepartmentId1(Long departmentId) {
		Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        if(departmentId != null){
        	criteria.andEqualTo("departmentId", departmentId);
        }
        return mapper.selectByExample(example);
	}

	public User findUserById(Long id) {
		User user = new User();
		user.setValid(1);
		user.setIsUse("1");
		user.setId(id);
		return mapper.selectOne(user);
	}
	
	public User findUserById2(Long id) {
		User user = new User();
		user.setValid(1);
		//user.setIsUse("1");
		user.setId(id);
		return mapper.selectOne(user);
	}

	public int addUser(User user) {
		user.setValid(1);
		//user.setIsUse("1");
		return mapper.insert(user);
		
	}
	
	public User findUserByName(String name) {
		User user = new User();
		user.setValid(1);
		user.setUserName(name);
		return mapper.selectOne(user);
	}



	public void deleteByName(String name){
		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userName", name);
		criteria.andEqualTo("valid","1");
		List<User> list = mapper.selectByExample(example);
		User u = list.size() > 0 ? list.get(0) : null;
		if(u != null ) {
			u.setValid(0);
			mapper.updateByPrimaryKeySelective(u);
		}
		
	}

	public void updateByUser(User user) {
		mapper.updateByPrimaryKeySelective(user);
	}

	public User findByUserId(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
    public Long findDepartmentIdByUserId(Long id) {
    	return mapper.selectByPrimaryKey(id).getDepartmentId();
    }

	public void delete(String id) {
		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("departmentId", Integer.parseInt(id));
		List<User> userlist = mapper.selectByExample(example);
		for (User user : userlist) {
			user.setValid(0);
			mapper.updateByPrimaryKeySelective(user);
		}
		
	}
	
    
    public User selectUserByName(String name) {
		return mapper.selectUserByName(name);
    }

    public Long selectDepartmentId(Long id){
		return mapper.selectDepartmentId(id);
	}
      
}
