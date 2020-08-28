package cn.movinginfo.tztf.sev.mapper;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Temperson;
import tk.mybatis.mapper.common.Mapper;

public interface TempersonMapper extends Mapper<Temperson>{
	@Select("select * from sev_t_temperson where valid = 1 and id != #{param1} and phone=#{param2}")
	public Temperson findTempersonByNotIdAndPhone(Long id,String phone);
}
