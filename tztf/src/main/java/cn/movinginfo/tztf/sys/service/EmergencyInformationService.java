package cn.movinginfo.tztf.sys.service;

import net.ryian.orm.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.EmergencyInformation;
import cn.movinginfo.tztf.sys.mapper.DeptMapper;
import cn.movinginfo.tztf.sys.mapper.EmergencyInformationMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  EmergencyInformationService extends BaseService<EmergencyInformation,EmergencyInformationMapper> {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private UserService userService;

    /**
     * 根据条件查询分页
     *
     * @param paramMap
     * @return
     */
    public List<EmergencyInformation> query(Map<String, String> paramMap) {
        Example example = new Example(EmergencyInformation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        String name = paramMap.get("name");
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
//        criteria.andEqualTo("departid","DepartmentId");
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public EmergencyInformation getEmergencyInfoByname(String name) {
        EmergencyInformation ei = new EmergencyInformation();
        ei.setName(name);
        ei.setValid(1);
        return mapper.selectOne(ei);
    }

    public List<Depart> getDepartAll() {
        List<Depart> depart = deptMapper.getDepartAll();
        return depart;
    }

    /**
     * 列表页面获取数据库信息
     */
    public List<EmergencyInformation> list(Map<String, String> paramMap) {
        Example example = new Example(EmergencyInformation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        String name = paramMap.get("name");
        String depart = paramMap.get("depart");
        String job = paramMap.get("job");
        String address = paramMap.get("address");
        String phone = paramMap.get("phone");

        if (!org.springframework.util.StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        if (!org.springframework.util.StringUtils.isEmpty(depart)) {
            criteria.andEqualTo("depart", depart);
        }
        if (!org.springframework.util.StringUtils.isEmpty(phone)) {
            criteria.andEqualTo("phone", phone);
        }
        if (!org.springframework.util.StringUtils.isEmpty(address)) {
            criteria.andEqualTo("deptId", address);
        }
        if (!org.springframework.util.StringUtils.isEmpty(job)) {
            criteria.andEqualTo("job", job);
        }
        List<EmergencyInformation> list = mapper.selectByExample(example);
        for (EmergencyInformation ei : list) {
            String departer = ei.getDepart();
            //Depart dept = deptService.get(Long.parseLong(departId));
            ei.setDepart(departer);
        }
        return list;
    }

    public List<EmergencyInformation> getListGroup(Long departid) {

        return mapper.getListGroup(departid);

    }


    public List<EmergencyInformation> getEmergencyInfo(Long departid) {
        return mapper.getEmergencyInfo(departid);

    }

    public List<EmergencyInformation> getEmergencyInfoAll(Long groupId) {
        return mapper.getEmergencyInfoAll(groupId);
    }

    public void deleteByEmergencyId(Long id) {
        Example example = new Example(EmergencyInformation.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        criteria.andEqualTo("id", id);
        List<EmergencyInformation> list = mapper.selectByExample(example);
        if (list.size() != 0) {
            for (EmergencyInformation pd : list) {
                pd.setValid(0);
                mapper.updateByPrimaryKeySelective(pd);
            }
        }
    }

    public EmergencyInformation deleteEmergencyInfo(Long id) {
       return  mapper.deleteEmergencyInfo(id);
    }

}
