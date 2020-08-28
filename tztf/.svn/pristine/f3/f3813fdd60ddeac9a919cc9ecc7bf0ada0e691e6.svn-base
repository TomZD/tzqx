package cn.movinginfo.tztf.sys.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.User;
import cn.movinginfo.tztf.sys.domain.UserXt;
import cn.movinginfo.tztf.sys.mapper.UserMapper;
import cn.movinginfo.tztf.sys.mapper.UserXtMapper;
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
 * Author: ZhangKX
 * Created by Administrator on 2019/10/29.
 * Reamrk:
 */
@Component
public class UserXtService extends BaseService <UserXt, UserXtMapper>{

//    public UserXt findUserByUserName(String userName) {
//        UserXt userXt = new UserXt();
//        userXt.setValid(1);
//        userXt.setUserName(userName);
//        return mapper.selectOne(userXt);
//    }

//    public UserXt findUserById(Long id) {
//        UserXt userXt = new UserXt();
//        userXt.setId(id);
//        return mapper.selectOne(userXt);
//    }

    public List<UserXt> getUserXtByNotId(Long id) {
        Example example = new Example(UserXt.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", 1);
        criteria.andNotEqualTo("id", id);
        return mapper.selectByExample(example);
    }

    /**
     * 根据条件查询分页
     * @param paramMap
     * @return
     */
    public List<UserXt> query(Map<String, String> paramMap) {
        Assert.notNull(paramMap);
        Example example = new Example(UserXt.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String name = paramMap.get("name");
        String areaId =paramMap.get("areaId");
        String id = paramMap.get("id");
        if(!StringUtils.isEmpty(areaId)) {
            criteria.andEqualTo("areaId", areaId);
        }
        if(!StringUtils.isEmpty(id)) {
            criteria.andEqualTo("id", id);
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

    public UserXt findUserByUserName(String userName) {
        UserXt user = new UserXt();
        user.setValid(1);
        user.setUserName(userName);
        return mapper.selectOne(user);
    }

    public UserXt findUserByDepartment(Long departmentId) {
        UserXt user = new UserXt();
        user.setValid(1);
        //user.setIsUse("1");
        //user.setId(id);
        //user.setDepartmentId(departmentId);
        return mapper.selectOne(user);
    }

    @Override
    public Long saveOrUpdate(UserXt user) {
        if (user.getId() == null) {
            if(StringUtils.isEmpty(user.getPassword())) {
                user.encryptUserPassword(SystemConfig.INSTANCE.getValue("DEFAULT_PASSWORD"));
            }
        }
        return super.saveOrUpdate(user);
    }

    public UserXt validatePwd(String userName,String pwd) {
        UserXt user = findUserByUserName(userName);
        if(user == null)
            return null;
        byte[] hashPassword = DigestUtils.sha1(pwd.getBytes(), EncodeUtils.decodeHex(user.getSalt()), UserXt.HASH_INTERATIONS);
        if(EncodeUtils.encodeHex(hashPassword).equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public UserXt validateByDepa(Long departmentId,String pwd) {
        UserXt user = findUserByDepartment(departmentId);
        if(user == null)
            return null;
        byte[] hashPassword = DigestUtils.sha1(pwd.getBytes(), EncodeUtils.decodeHex(user.getSalt()), UserXt.HASH_INTERATIONS);
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
    public PageInfo<List<UserXt>> queryUserPaged(Map<String, String> parameterMap) {
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
        List<UserXt> list = sqlSession.selectList(statement, parameterMap);
        return new PageInfo(list);
    }

    /**
     * 根据部门编号获取所有该部门人员
     * @param departmentId
     * @return
     */
    public List<UserXt> getByDepartmentId(Long departmentId) {
        Example example = new Example(UserXt.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        if(departmentId != null){
            criteria.andEqualTo("departmentId", departmentId);
        }
        return mapper.selectByExample(example);
    }


    public PageInfo<List<UserXt>> queryPageds(Map<String, String> parameterMap) {
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
        List<UserXt> list = query(parameterMap);
        return new PageInfo(list);
    }



    public List<UserXt> getByDepartmentId1(Long departmentId) {
        Example example = new Example(UserXt.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        if(departmentId != null){
            criteria.andEqualTo("departmentId", departmentId);
        }
        return mapper.selectByExample(example);
    }

    public UserXt findUserById(Long id) {
        UserXt user = new UserXt();
        user.setValid(1);
        //user.setIsUse("1");
        user.setId(id);
        return mapper.selectOne(user);
    }

    public UserXt findUserById2(Long id) {
        UserXt user = new UserXt();
        user.setValid(1);
        //user.setIsUse("1");
        user.setId(id);
        return mapper.selectOne(user);
    }

    public int addUser(UserXt user) {
        user.setValid(1);
        //user.setIsUse("1");
        return mapper.insert(user);

    }

    public UserXt findUserByName(String name) {
        UserXt user = new UserXt();
        user.setValid(1);
        user.setUserName(name);
        return mapper.selectOne(user);
    }



    public void deleteByName(String name){
        Example example = new Example(UserXt.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", name);
        criteria.andEqualTo("valid","1");
        List<UserXt> list = mapper.selectByExample(example);
        UserXt u = list.size() > 0 ? list.get(0) : null;
        if(u != null ) {
            u.setValid(0);
            mapper.updateByPrimaryKeySelective(u);
        }

    }

    public void updateByUser(UserXt user) {
        mapper.updateByPrimaryKeySelective(user);
    }

    public UserXt findByUserId(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

//    public Long findDepartmentIdByUserId(Long id) {
//        return mapper.selectByPrimaryKey(id).getDepartmentId();
//    }

    public void delete(String id) {
        Example example = new Example(UserXt.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("departmentId", Integer.parseInt(id));
        List<UserXt> userlist = mapper.selectByExample(example);
        for (UserXt user : userlist) {
            user.setValid(0);
            mapper.updateByPrimaryKeySelective(user);
        }

    }


    public UserXt selectUserByName(String name) {
        return mapper.selectUserByName(name);
    }

    public UserXt selectUserByUserName(String name) {
        return mapper.selectUserByUserName(name);
    }

    public Long selectDepartmentId(Long id){
        return mapper.selectDepartmentId(id);
    }


}
