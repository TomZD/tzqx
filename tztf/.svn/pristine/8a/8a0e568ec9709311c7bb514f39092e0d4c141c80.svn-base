package cn.movinginfo.tztf.sen.service;

import net.ryian.orm.service.support.datasource.annotation.DataSource;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.RnflR;
import cn.movinginfo.tztf.sen.domain.Stinf;
import cn.movinginfo.tztf.sen.mapper.RnflRMapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


/**
 * @description:
 * @author: autoCode
 * @history:
 */
@DataSource("tztf")
@Component
public class RnflRService {
    @Resource
    private RnflRMapper mapper;

    public List<RnflR> getRnflRByStcd(String stcd) {
        Example example = new Example(RnflR.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("stcd", stcd);
        example.setOrderByClause("ymdhm desc");
        return mapper.selectByExample(example);
    }

    public RnflR getFirstRnflRByStcd(String stcd) {
        return mapper.getFirstRnflR(stcd);
    }

    public List<Timestamp> getTimeAxis(Integer timeAxisNum) {
        return mapper.getTimeAxis(timeAxisNum);
    }

    public List<Stinf> selectDtrn(List<Timestamp> timeStamps) {
        return mapper.selectDtrn(timeStamps);
    }

    public List<RnflR> selectRnfirList(Timestamp time, String stcd) {
        return mapper.selectRnfirList(time, stcd);
    }

    public Timestamp selectTime() {
        return mapper.selectTime();
    }


    public void addList(List<RnflR> rnflRs) {
        mapper.addList(rnflRs);
    }

    public void deleteAfterMouth(Timestamp time){
        mapper.deleteAfterMouth(time);
    }

}
