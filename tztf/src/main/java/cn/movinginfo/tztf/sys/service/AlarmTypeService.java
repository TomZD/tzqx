package cn.movinginfo.tztf.sys.service;

import net.ryian.orm.service.BaseService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.movinginfo.tztf.sys.domain.AlarmType;
import cn.movinginfo.tztf.sys.mapper.AlarmTypeMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Component
public class AlarmTypeService extends BaseService<AlarmType, AlarmTypeMapper> {

	/**
	 * @Description: TODO 获取部门设置的气象预警类型名称
	 * @Class: cn.movinginfo.tztf.sys.service.AlarmTypeService
	 * @Title: getDistinctAlarmNameType
	 * @param deptId
	 * @return List<AlarmType>
	 * @author: zhangdi
	 * @createTime: 2020-8-30 15:39:53
	 * @updateTime: 
	 * @throws 
	 */
	public  List<AlarmType> getDistinctAlarmNameType(Long deptId) {
		List<AlarmType> list = mapper.getDistinctAlarmNameType(deptId);
		return list;
	}
	
	/**
	 * 根据条件查询分页
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<AlarmType> query(Map<String, String> paramMap) {
		Example example = new Example(AlarmType.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("valid", "1");
		String code = paramMap.get("code");
		if (!StringUtils.isEmpty(code)) {
			criteria.andLike("code", "%" + code + "%");
		}
		return mapper.selectByExample(example);
	}
	
	public List<AlarmType> getDistinctAlarmType(){
		List<AlarmType> list = mapper.getDistinctList();
		return list;
	}
	
	public AlarmType getByCaption(String Caption){
		AlarmType list = mapper.getByCaption(Caption);
		return list;
	}
}
