package cn.movinginfo.tztf.ser.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.ser.domain.HuasuHotel;
import cn.movinginfo.tztf.ser.domain.HuasuHotelExample;

public interface HuasuHotelMapper {
    long countByExample(HuasuHotelExample example);

    int deleteByExample(HuasuHotelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HuasuHotel record);

    int insertSelective(HuasuHotel record);

    List<HuasuHotel> selectByExample(HuasuHotelExample example);

    HuasuHotel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HuasuHotel record, @Param("example") HuasuHotelExample example);

    int updateByExample(@Param("record") HuasuHotel record, @Param("example") HuasuHotelExample example);

    int updateByPrimaryKeySelective(HuasuHotel record);

    int updateByPrimaryKey(HuasuHotel record);
}