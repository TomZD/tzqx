package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.Communication;
import cn.movinginfo.tztf.sem.domain.Government;
import cn.movinginfo.tztf.sem.domain.GovernmentExample;

public interface GovernmentMapper {
    long countByExample(GovernmentExample example);

    int deleteByExample(GovernmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Government record);

    int insertSelective(Government record);

    List<Government> selectByExample(GovernmentExample example);

    Government selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Government record, @Param("example") GovernmentExample example);

    int updateByExample(@Param("record") Government record, @Param("example") GovernmentExample example);

    int updateByPrimaryKeySelective(Government record);

    int updateByPrimaryKey(Government record);
    
    public Government findGovernmentById(@Param("id")Long id);

	public List<Government> getOneThousandData();
	
	public List<Government> getVideos(@Param("longitude")String lon1, @Param("latitude")String lat1,@Param("longitude1")String lon2,@Param("latitude1")String lat2);
}