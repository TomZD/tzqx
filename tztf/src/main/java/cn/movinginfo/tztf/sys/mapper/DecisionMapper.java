package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.Decision;
import tk.mybatis.mapper.common.Mapper;

public interface DecisionMapper extends Mapper<Decision>{
	
	@Select("select * from sys_decision where valid = 1 and id != #{param1} and phone=#{param2}")
	public Decision findDecisionByNotIdAndPhone(Long id,String phone);

}
