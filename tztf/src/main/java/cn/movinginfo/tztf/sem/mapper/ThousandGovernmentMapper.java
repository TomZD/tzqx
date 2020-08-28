package cn.movinginfo.tztf.sem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sem.domain.ThousandGovernment;
import cn.movinginfo.tztf.sem.domain.ThousandGovernmentExample;

public interface ThousandGovernmentMapper {
    long countByExample(ThousandGovernmentExample example);

    int deleteByExample(ThousandGovernmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ThousandGovernment record);

    int insertSelective(ThousandGovernment record);

    List<ThousandGovernment> selectByExample(ThousandGovernmentExample example);

    ThousandGovernment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ThousandGovernment record, @Param("example") ThousandGovernmentExample example);

    int updateByExample(@Param("record") ThousandGovernment record, @Param("example") ThousandGovernmentExample example);

    int updateByPrimaryKeySelective(ThousandGovernment record);

    int updateByPrimaryKey(ThousandGovernment record);
    
    public ThousandGovernment findThousandGovernmentById(@Param("id")Long id);
}