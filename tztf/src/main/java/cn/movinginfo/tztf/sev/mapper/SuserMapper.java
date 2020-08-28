package cn.movinginfo.tztf.sev.mapper;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Suser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface SuserMapper extends Mapper<Suser>{

    @Select("select DISTINCT suser from sev_t_road where no in (select road_no from sev_r_road_forecast_spot where spot_no=#{value})")
    public List<Long> getForecastSusers(String spotNo);

}
