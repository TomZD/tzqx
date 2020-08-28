package cn.movinginfo.tztf.sen.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.PointDangertype;
import cn.movinginfo.tztf.sen.mapper.PointDangertypeMapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class PointDangertypeService extends BaseService<PointDangertype, PointDangertypeMapper> {

    @Resource
    private PointDangertypeMapper mapper;

    /**
     * 根据条件查询分页
     *
     * @param paramMap
     * @return
     */
    public List<PointDangertype> query(Map<String, String> paramMap) {
        Example example = new Example(PointDangertype.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        String meteorologicalId = paramMap.get("meteorologicalId");
        if (!StringUtils.isEmpty(meteorologicalId)) {
            criteria.andLike("meteorologicalId", "%" + meteorologicalId + "%");
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<PointDangertype> getPointDangertypeByMeteorologicalIdAndPointId(Long meteorologicalId, Long pointId) {
        Example example = new Example(PointDangertype.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        criteria.andEqualTo("meteorologicalId", meteorologicalId);
        criteria.andEqualTo("pointId", pointId);
        return mapper.selectByExample(example);
    }

    public List<PointDangertype> getPointDangerByPointId(Long pointId) {
        Example example = new Example(PointDangertype.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        criteria.andEqualTo("pointId", pointId);
        return mapper.selectByExample(example);
    }


    public void deleteByPointId(Long pointId) {
        Example example = new Example(PointDangertype.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        criteria.andEqualTo("pointId", pointId);
        List<PointDangertype> list = mapper.selectByExample(example);
        if (list.size() != 0) {
            for (PointDangertype pd : list) {
                pd.setValid(0);
                mapper.updateByPrimaryKeySelective(pd);
            }
        }
    }

    public void insertSelective(PointDangertype dangertype) {
        mapper.insertSelective(dangertype);
    }

    public List<PointDangertype> selectByPointId(Long point_id) {
        return mapper.selectByPointId(point_id);
    }
}
