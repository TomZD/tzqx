package cn.movinginfo.tztf.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.movinginfo.tztf.sys.domain.ReleaseChannel;
import tk.mybatis.mapper.common.Mapper;

public interface ReleaseChannelMapper extends Mapper<ReleaseChannel>{
	
	 @Select("select * from sys_release_channel where NAME_EN=#{name} and kettle != 1")
	public ReleaseChannel passNameGetMess(String name);
	 
	 @Select("select NAME_EN from sys_release_channel where NAME=#{name} and kettle != 1")
	 public String passCNameToEName(String name);
	 
	 @Select("select * from sys_release_channel where valid = 1 AND PARENT_ID IS NULL and kettle != 1" )
	 public List<ReleaseChannel> getAllParents();
	 
	 
	 @Select("select * from sys_release_channel where valid = 1 AND kettle!=1")
	 @Results(value={
				@Result(property = "name", column = "NAME"),
	            @Result(property = "nameEn", column = "NAME_EN"),
	            @Result(property = "maxLength", column = "MAX_LENGTH"),
				@Result(property = "sortNumber", column = "SORT_NUMBER")
		})
	 public List<ReleaseChannel> getByKettle();
	 
	 @Select("SELECT * FROM sys_release_channel where valid = 1 AND kettle!=1 AND (`name` LIKE '%传真%' OR `name` LIKE '%微博%' OR `name` LIKE '%国突%' OR `name` LIKE '%存档%' OR `name` LIKE '%钉钉%' OR `name` LIKE '%短信%')")
	 @Results(value={
				@Result(property = "name", column = "NAME"),
	            @Result(property = "nameEn", column = "NAME_EN"),
	            @Result(property = "maxLength", column = "MAX_LENGTH"),
				@Result(property = "sortNumber", column = "SORT_NUMBER")
		})
	 public List<ReleaseChannel> getSomeChannel();
	 
	 
	 @Select("select * from sys_release_channel where valid = 1")
	 @Results(value={
				@Result(property = "name", column = "NAME"),
	            @Result(property = "nameEn", column = "NAME_EN"),
	            @Result(property = "maxLength", column = "MAX_LENGTH"),
				@Result(property = "sortNumber", column = "SORT_NUMBER")
		})
	 public List<ReleaseChannel> getByValid();
     
	 @Select("select * from sys_release_channel where channelId=#{channelId}")
	 public String  findNameByChannelId(Long channelId);
	 
}
