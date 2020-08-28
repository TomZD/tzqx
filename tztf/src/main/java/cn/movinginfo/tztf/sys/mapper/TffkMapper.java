package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Sensitive;
import cn.movinginfo.tztf.sys.domain.Tffk;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
public interface TffkMapper extends Mapper<Tffk> {

    @Select("select * from tffk where user_name = #{userName}")
    public Tffk selectByUserName(String userName);

    @Select("select * from tffk where user_id = #{userId} and valid = 1")
    public List<Tffk> selectByUserId(Long userId);
}
