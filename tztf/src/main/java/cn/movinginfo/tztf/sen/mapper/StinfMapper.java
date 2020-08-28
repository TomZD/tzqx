package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.movinginfo.tztf.sen.domain.Stinf;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
public interface StinfMapper extends Mapper<Stinf> {

	@Update("update sen_t_stinf set stcd =#{stcd},stnm=#{stnm} where id=#{id}")
	public void updateStinf(@Param("stcd") String stcd, @Param("id") Long id, @Param("stnm")
			String stnm);

	//查询所有站点站号
	@Select("SELECT stcd as stcd,stcd as name,latitude as lat,longitude as lon FROM hytf.sen_t_stinf")
	public List<Stinf> selectList();

	//根据站号查询站点信息
	@Select("SELECT * FROM sen_t_stinf WHERE stcd =#{stcd}")
	public Stinf selectByStcd(@Param("stcd") String stcd);

}