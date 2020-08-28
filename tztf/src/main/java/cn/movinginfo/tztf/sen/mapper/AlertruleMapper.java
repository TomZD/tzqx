package cn.movinginfo.tztf.sen.mapper;



import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.Alertrule;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface AlertruleMapper extends Mapper<Alertrule>{
	
	@Select("select * from bj_alertrule where CONCAT(',',element_ids,',') LIKE concat('%,',#{elementIds},',%') AND valid=1")
	@Results(value = {
            @Result(property = "alertLevel", column = "alert_level"),
            @Result(property = "thresholdValue", column = "threshold_value"),
            @Result(property = "ruleExpr", column = "rule_expr"),
            @Result(property = "isForecast", column = "is_forecast"),
            @Result(property = "elementIds", column = "element_ids"),
    })
	public List<Alertrule> getAlertruleByElementId(@Param("elementIds")String elementId);



}
