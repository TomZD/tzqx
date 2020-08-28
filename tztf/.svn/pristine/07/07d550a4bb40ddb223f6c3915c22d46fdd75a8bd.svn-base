package cn.movinginfo.tztf.sen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sen.domain.Menu;
import tk.mybatis.mapper.common.Mapper;

public interface MenuMapper extends Mapper<Menu> {
    //根据pid查询
    @Select("SELECT * FROM sen_t_menu WHERE pid=#{pid} and valid=1")
    @Results(value = {
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "fileURL", column = "file_url"),
            @Result(property = "showTime", column = "show_time"),
            @Result(property = "dataType", column = "data_type"),
            @Result(property = "typeId", column = "type_id")
    })
    public List<Menu> selectByPid(Integer pid);

    //根据type查询
    @Select("SELECT * FROM sen_t_menu WHERE type=#{type} and valid=1")
    @Results(value = {
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "fileURL", column = "file_url"),
            @Result(property = "showTime", column = "show_time"),
            @Result(property = "dataType", column = "data_type"),
            @Result(property = "typeId", column = "type_id")
    })
    public Menu selectByType(String type);

    //根据value排序
    @Select("SELECT * FROM sen_t_menu WHERE pid=#{pid} and valid=1 order by value asc")
    @Results(value = {
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "fileURL", column = "file_url"),
            @Result(property = "showTime", column = "show_time"),
            @Result(property = "dataType", column = "data_type"),
            @Result(property = "typeId", column = "type_id")
    })
    public List<Menu> selectMenus(Long pid);
    
    
    @Select("select * from sen_t_menu where binary type = #{type} and valid=1")
    @Results(value = {
            @Result(property = "menuId", column = "menu_id"),
            @Result(property = "fileURL", column = "file_url"),
            @Result(property = "showTime", column = "show_time"),
            @Result(property = "dataType", column = "data_type"),
            @Result(property = "typeId", column = "type_id")
    })
    public List<Menu> getMenuByType(@Param("type")String type);
}
