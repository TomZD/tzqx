package cn.movinginfo.tztf.dd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.dd.domain.Disaster;
import cn.movinginfo.tztf.dd.domain.DisasterExample;

public interface DisasterMapper {
    long countByExample(DisasterExample example);

    int deleteByExample(DisasterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Disaster record);

    int insertSelective(Disaster record);

    List<Disaster> selectByExample(DisasterExample example);

    Disaster selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Disaster record, @Param("example") DisasterExample example);

    int updateByExample(@Param("record") Disaster record, @Param("example") DisasterExample example);

    int updateByPrimaryKeySelective(Disaster record);

    int updateByPrimaryKey(Disaster record);
    
    List<Disaster> getListByAreaId(String areaId);

	List<Disaster> getListIsPublishing(String areaId);
	
	public Disaster findDisasterById(Long id);
}