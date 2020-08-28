package cn.movinginfo.tztf.sev.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sev.domain.Share;
import cn.movinginfo.tztf.sev.mapper.ShareMapper;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

@Component
public class ShareService extends BaseService<Share, ShareMapper> {

    public List<Share> query(Map<String, String> paramMap) {

        String groupName = paramMap.get("sharePath");//获取前台传过来的参数
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        if(!StringUtils.isEmpty(groupName)) {
            criteria.andLike("sharePath","%"+groupName+"%");//模糊查询
        }
        return mapper.selectByExample(example);
    }
    /**
     * 获取所有群组
     * @return
     */
    public List<Share> getAllGroup()
    {
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        return mapper.selectByExample(example);
    }
}
