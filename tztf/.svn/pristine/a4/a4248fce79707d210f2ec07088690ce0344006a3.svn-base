package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.PointDangertype;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
public interface PointDangertypeMapper extends Mapper<PointDangertype> {
    //查询隐患点所有隐患要素
    @Select("SELECT * FROM sen_t_point_dangertype WHERE point_id = #{point_id}")
    public List<PointDangertype> selectByPointId(@Param("point_id") Long point_id);
}
