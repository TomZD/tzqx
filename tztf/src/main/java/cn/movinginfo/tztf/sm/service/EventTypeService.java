package cn.movinginfo.tztf.sm.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sm.domain.EventType;
import cn.movinginfo.tztf.sm.domain.RDepartmentEnventType;
import cn.movinginfo.tztf.sm.mapper.EventTypeMapper;
import cn.movinginfo.tztf.sm.mapper.RDepartmentEnventTypeMapper;
import cn.movinginfo.tztf.sys.domain.RUserRole;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  EventTypeService extends BaseService<EventType,EventTypeMapper> {

	@Autowired
	private RDepartmentEnventTypeService rDepartmentEnventTypeService;
    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<EventType> query(Map<String, String> paramMap) {
        Example example = new Example(EventType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String code = paramMap.get("code");
        if (!StringUtils.isEmpty(code)) {
            criteria.andLike("code", "%" + code+ "%");
        }
        return mapper.selectByExample(example);
    }

    
	public List<EventType> getByOperateType(String operateType) {
		Example example = new Example(EventType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        if (!StringUtils.isEmpty(operateType)) {
            criteria.andEqualTo("operateType", operateType);
        }
        example.setOrderByClause("`national_code` asc");
        return mapper.selectByExample(example);
	}
	
	public List<EventType> getByCode(String code) {
		Example example = new Example(EventType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        if (!StringUtils.isEmpty(code)) {
            criteria.andEqualTo("code", code);
        }
        return mapper.selectByExample(example);
	}
	
	public List<EventType> getDeptEventType(Long deptId,Long eventTypeId) {
		List<RDepartmentEnventType> list = rDepartmentEnventTypeService.getByDepartmentId(String.valueOf(deptId));
		List<EventType> traList = new LinkedList<EventType>();
		for(RDepartmentEnventType de:list){
			Long eventId = de.getEventTypeId();
			EventType et = get(eventId);
			if(et.getPId().equals(eventTypeId)&&et.getValid()==1){
				traList.add(et);
			}
		}
		if(traList.size()==0){
			traList.add(get(eventTypeId));
		}
		return traList;
	}
	
	/**
	 * 获取2级事件
	 * @param deptId
	 * @return
	 */
	public List<EventType> getDeptEventType2(Long deptId) {
		List<RDepartmentEnventType> list = rDepartmentEnventTypeService.getByDepartmentId(String.valueOf(deptId));
		List<EventType> traList = new LinkedList<EventType>();
		for(RDepartmentEnventType de:list){
			Long eventId = de.getEventTypeId();
			EventType et = get(eventId);
			if(et.getOperateType().equals("2")){
				traList.add(et);
			}
		}
		return traList;
	}


	public String getAddOrUpdateTypes(String eventTypeFirsts, String eventTypeSeconds, String eventTypeThirds,String nationalCode) {
		String  data = "" ;
		//判断第三级餐单是否为空
		EventType coding= mapper.selectCode(nationalCode);
		if ( (coding != null && coding.getValid() == 1) || ("19K00".equals(nationalCode))){
			data = "编码重复,请重新添加！";
			return data;
		}
		if(!StringUtils.isEmpty(eventTypeThirds)){
			//2020-4-14 修改 存在同名情况，所以把同名的注释掉
//			EventType eventThird= mapper.selectUpdateTypes(eventTypeThirds);
//			if(eventThird != null && eventThird.getValid() == 1){
//				data = "此类别已存在";
//			}else{
				EventType eventType = new EventType();
				//选择其它突发事件
				if (eventTypeSeconds == null || eventTypeSeconds == "") {
					EventType towType = new EventType();
					towType.setDisplayName(eventTypeFirsts);
					EventType towTypes=mapper.selectOne(towType);
					towType.setNationalCode("19K00");
					towType.setCode(towTypes.getNationalCode());
					towType.setOperateType("2");
					towType.setDisplayName("其它突发");
					towType.setPId(towTypes.getId());
					towType.setPid(towTypes.getId());
					mapper.insertSelective(towType);	
					eventType.setDisplayName("其它突发");
				}else{
					eventType.setDisplayName(eventTypeSeconds);
				}
				//判断餐单中是否有相同灾害
				EventType eventTypes=mapper.selectOne(eventType);
				//eventType.setCreateDate(new Date());
				eventType.setNationalCode(nationalCode);
				eventType.setCode(eventTypes.getNationalCode());
				eventType.setOperateType("3");
				eventType.setDisplayName(eventTypeThirds);
				eventType.setPId(eventTypes.getId());
				eventType.setPid(eventTypes.getId());
				mapper.insertSelective(eventType);	
				data = "插入成功";
//			}	
		}
		return data;
		
		
	}


	public List<EventType> getClickTypes(String eventTypeFirsts) {
		
		 String eventThird = mapper.selectClickTypes(eventTypeFirsts);
//		 EventType eventType = new EventType();
//		 eventType.setCode(eventThird);
		 Example example = new Example(EventType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        if (!StringUtils.isEmpty(eventThird)) {
            criteria.andEqualTo("code", eventThird);
        }
        return mapper.selectByExample(example);
	}
}
