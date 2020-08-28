package cn.movinginfo.tztf.sev.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import cn.movinginfo.tztf.sev.domain.EmergencyPlan;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.provider.ExampleProvider;

public interface EmergencyPlanMapper  extends Mapper<EmergencyPlan>{

}
