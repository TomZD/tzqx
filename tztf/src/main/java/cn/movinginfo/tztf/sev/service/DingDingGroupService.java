package cn.movinginfo.tztf.sev.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sev.domain.DingDingGroup;
import cn.movinginfo.tztf.sev.mapper.DingDingGroupMapper;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

@Component
public class DingDingGroupService extends BaseService<DingDingGroup, DingDingGroupMapper> {

    public List<DingDingGroup> query(Map<String, String> paramMap) {

        String groupName = paramMap.get("groupName");//获取前台传过来的参数
        Example example = new Example(DingDingGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        if(!StringUtils.isEmpty(groupName)) {
            criteria.andLike("groupName","%"+groupName+"%");//模糊查询
        }
        return mapper.selectByExample(example);
    }
    /**
     * 根据群名获取群组
     * @param groupName
     * @return
     */
    public DingDingGroup getGroupByGroupName(String groupName) {
    	DingDingGroup dd = new DingDingGroup();
    	dd.setGroupName(groupName);
    	dd.setValid(1);
    	return mapper.selectOne(dd);
    }
    /**
     * 获取所有群组
     * @return
     */
    public List<DingDingGroup> getAllGroup()
    {
        Example example = new Example(DingDingGroup.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        return mapper.selectByExample(example);
    }
}
