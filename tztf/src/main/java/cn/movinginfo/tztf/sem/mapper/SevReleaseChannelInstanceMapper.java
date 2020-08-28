package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.SevReleaseChannelInstance;
import cn.movinginfo.tztf.sem.domain.SevReleaseChannelInstanceExample;

public interface SevReleaseChannelInstanceMapper {
    long countByExample(SevReleaseChannelInstanceExample example);

    int deleteByExample(SevReleaseChannelInstanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SevReleaseChannelInstance record);

    int insertSelective(SevReleaseChannelInstance record);

    List<SevReleaseChannelInstance> selectByExample(SevReleaseChannelInstanceExample example);

    SevReleaseChannelInstance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SevReleaseChannelInstance record, @Param("example") SevReleaseChannelInstanceExample example);

    int updateByExample(@Param("record") SevReleaseChannelInstance record, @Param("example") SevReleaseChannelInstanceExample example);

    int updateByPrimaryKeySelective(SevReleaseChannelInstance record);

    int updateByPrimaryKey(SevReleaseChannelInstance record);
}