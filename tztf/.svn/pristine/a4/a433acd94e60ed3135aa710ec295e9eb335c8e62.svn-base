package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.RnflR;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;
import java.util.List;

public interface RnflRSourceMapper extends Mapper<RnflR> {

    //获取需要同步的数据
    @Select("SELECT * FROM ST_RNFL_R WHERE YMDHM > #{time}")
    public List<RnflR> selectRnflRs(Timestamp time);

}
