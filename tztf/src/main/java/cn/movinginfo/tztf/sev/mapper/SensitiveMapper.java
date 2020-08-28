package cn.movinginfo.tztf.sev.mapper;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sev.domain.Sensitive;
import tk.mybatis.mapper.common.Mapper;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
public interface SensitiveMapper extends Mapper<Sensitive> {

    //根据敏感词查询
    @Select("select * from sev_t_sensitive where word = #{word}")
    public Sensitive selectByWord(String word);
}
