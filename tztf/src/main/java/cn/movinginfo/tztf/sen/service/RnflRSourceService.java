package cn.movinginfo.tztf.sen.service;

import net.ryian.orm.service.support.datasource.annotation.DataSource;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sen.domain.RnflR;
import cn.movinginfo.tztf.sen.mapper.RnflRSourceMapper;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 水文站数据源
 */
@DataSource("hyswSource")
@Component
public class RnflRSourceService {

    @Resource
    private RnflRSourceMapper rnflRSourceMapper;

    public List<RnflR> selectRnflRs(Timestamp time){
        return rnflRSourceMapper.selectRnflRs(time);
    }

}
