package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sen.model.TownJXH;
import cn.movinginfo.tztf.sen.model.TownJXHExample;
import cn.movinginfo.tztf.sen.model.TownJXHKey;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface TownJXHMapper {
    long countByExample(TownJXHExample example);

    int deleteByExample(TownJXHExample example);

    int deleteByPrimaryKey(TownJXHKey key);

    int insert(TownJXH record);

    int insertSelective(TownJXH record);

    List<TownJXH> selectByExample(TownJXHExample example);

    TownJXH selectByPrimaryKey(TownJXHKey key);

    int updateByExampleSelective(@Param("record") TownJXH record, @Param("example") TownJXHExample example);

    int updateByExample(@Param("record") TownJXH record, @Param("example") TownJXHExample example);

    int updateByPrimaryKeySelective(TownJXH record);

    int updateByPrimaryKey(TownJXH record);

    List<TownJXH> getTownJXHByTimeAndCode(@Param("time") String time, @Param("code") String code);

    //查询乡镇精细化数据  最高温 最低温 相对湿度平均值
    Map<String, BigDecimal> getTownJXH(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("townCode") String townCode);

    //查询最高风速最低风速
    Map<String, BigDecimal> getWindS(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate, @Param("townCode") String townCode);

    //查询天况和降水
    TownJXH getWeather(@Param("date") Timestamp date, @Param("townCode") String townCode);

    //查询当天风向列表
    List<BigDecimal> getWindD(@Param("startDate")Timestamp startDate, @Param("endDate") Timestamp endDate,@Param("townCode") String townCode);
}