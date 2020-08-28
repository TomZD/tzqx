package cn.movinginfo.tztf.stb.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.stb.model.Stb;
import cn.movinginfo.tztf.stb.model.StbExample;

public interface StbMapper {
    long countByExample(StbExample example);

    int deleteByExample(StbExample example);

    int deleteByPrimaryKey(String equipmentcode);

    int insert(Stb record);

    int insertSelective(Stb record);

    List<Stb> selectByExample(StbExample example);

    Stb selectByPrimaryKey(String equipmentcode);

    int updateByExampleSelective(@Param("record") Stb record, @Param("example") StbExample example);

    int updateByExample(@Param("record") Stb record, @Param("example") StbExample example);

    int updateByPrimaryKeySelective(Stb record);

    int updateByPrimaryKey(Stb record);
}