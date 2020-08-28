package cn.movinginfo.tztf.sen.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.Elementinfo;
import tk.mybatis.mapper.common.Mapper;

/**
* @description:
* @author: autoCode
* @history:
*/
public interface ElementinfoMapper extends Mapper<Elementinfo>{
	
	@Select("Select * from bj_elementinfo where element_id = #{elementId}")
	Elementinfo getElementByElementId(@Param("elementId")String elementId);
}
