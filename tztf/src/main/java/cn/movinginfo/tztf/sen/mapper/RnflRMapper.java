package cn.movinginfo.tztf.sen.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.RnflR;
import cn.movinginfo.tztf.sen.domain.Stinf;
import tk.mybatis.mapper.common.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
public interface RnflRMapper extends Mapper<RnflR> {

    @Select("Select * from st_rnfl_r where stcd=#{stcd} order by ymdhm desc limit 0,1")
    public RnflR getFirstRnflR(@Param("stcd") String stcd);

    //查询水文站数据时间轴
    @Select("SELECT DISTINCT YMDHM FROM (SELECT DISTINCT YMDHM FROM st_rnfl_r ORDER BY YMDHM DESC limit 0,#{timeAxisNum}) a ORDER BY a.YMDHM")
    public List<Timestamp> getTimeAxis(@Param("timeAxisNum") Integer timeAxisNum);

    //查询某一站点降水量
    @Select({"<script>" +
            "SELECT a.stcd as stcd,a.stnm as name,a.longitude as lon,a.latitude as lat,SUM(DTRN) as `value` \n" +
            "FROM sen_t_stinf a LEFT JOIN st_rnfl_r r ON a.stcd=r.STCD " +
            "WHERE r.YMDHM IN " +
            "<foreach collection = 'timeStamps' separator = ',' open = '(' close = ')' item = 'item'>  " +
            "#{item}" +
            "</foreach> " +
            " GROUP BY a.stcd" +
            "</script>"})
    public List<Stinf> selectDtrn(@Param("timeStamps") List<Timestamp> timeStamps);

    //查询单个站点24小时降水
    @Select("SELECT * FROM st_rnfl_r WHERE YMDHM<=#{time} AND STCD=#{stcd} ORDER BY YMDHM DESC limit 0,24")
    public List<RnflR> selectRnfirList(@Param("time") Timestamp time, @Param("stcd") String stcd);

    //查询最新的时间
    @Select("SELECT MAX(YMDHM) FROM st_rnfl_r")
    public Timestamp selectTime();

    //批量插入最新数据
    @Insert({"<script>" +
            "INSERT INTO st_rnfl_r (STCD,YMDHM,DTRN) VALUES" +
            "<foreach collection = 'rnflRs' separator = ',' item = 'item'>" +
            "(" +
            "#{item.stcd}," +
            "#{item.ymdhm}," +
            "#{item.dtrn}"
            + ")"
            + "</foreach> "
            + "</script>"})
    public void addList(@Param("rnflRs") List<RnflR> rnflRs);

    //删除30天前的数据
    @Delete("DELETE FROM st_rnfl_r WHERE YMDHM < #{time}")
    public void deleteAfterMouth(Timestamp time);

}
