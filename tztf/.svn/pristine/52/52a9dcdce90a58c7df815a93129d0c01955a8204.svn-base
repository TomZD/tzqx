package cn.movinginfo.tztf.sev.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.movinginfo.tztf.sev.domain.Emperson;
import cn.movinginfo.tztf.sev.domain.EmpersonExample;

public interface EmpersonMapper {
    long countByExample(EmpersonExample example);

    int deleteByExample(EmpersonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Emperson record);

    int insertSelective(Emperson record);

    List<Emperson> selectByExample(EmpersonExample example);

    Emperson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Emperson record, @Param("example") EmpersonExample example);

    int updateByExample(@Param("record") Emperson record, @Param("example") EmpersonExample example);

    int updateByPrimaryKeySelective(Emperson record);

    int updateByPrimaryKey(Emperson record);
    
	List<AreaResult> getDisntictArea();

	AreaResult getByDeptId(@Param("id") Long deptId);
}