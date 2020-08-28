package cn.movinginfo.tztf.sys.mapper;

import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.Dict;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by allenwc on 15/8/12.
 */
public interface DictMapper extends Mapper<Dict>{

    @Select("select distinct(key_name) from sys_dict where valid='1'")
    public List<String> getKeyNames();
    
    
    @Select("select content from sys_dict where key_name =#{param1} and value =#{param2} and valid='1'")
    public String getContent(String key_name,String value);
    
    @Select("select content from sys_dict where key_name =#{param1} and value =#{param2}")
    public String getContentNotValid(String key_name,String value);

    @Select("select value from sys_dict where key_name =#{param1} and content =#{param2} and valid='1'")
    public String getValue(String key_name,String content);
    
    @Select("select memo from sys_dict where key_name =#{param1} and value =#{param2} and valid='1'")
    public String getMemo(String key_name,String value);
}
