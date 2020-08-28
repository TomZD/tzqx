package cn.movinginfo.tztf.sev.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sev.domain.Led;
import cn.movinginfo.tztf.sev.mapper.LedMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  LedService extends BaseService<Led,LedMapper> {

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<Led> query(Map<String, String> paramMap) {
        Example example = new Example(Led.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String code = paramMap.get("equipmentCode");
        if (!StringUtils.isEmpty(code)) {
            criteria.andLike("equipmentCode", "%" + code+ "%");
        }
    return mapper.selectByExample(example);
    }
    
    public void updateStatus(String code,String status){
    	Led led = new Led();
    	led.setEquipmentCode(code);
    	led = mapper.selectOne(led);
    	if(led==null){//数据库中不存该编号的记录
    		led = new Led();
    		led.setEquipmentCode(code);
    		led.setConnectStatus(Integer.valueOf(status));
    		led.setIsInstall(1);
    		led.setCreateDate(new Date());
    		mapper.insert(led);
    	}else{//存在该记录则修改这状态
    		mapper.updateStatus(Integer.valueOf(status), code);
    	}
    	
    }
    
    public void updateAllStatus(String[] codes){
    	if(codes!=null&&codes.length>0){
    		for(String code:codes){
    			updateStatus(code,"1");
        	}
    	}
    }
    
    public List<Led> getSevLed() {
    	 Example example = new Example(Led.class);
         Example.Criteria criteria = example.createCriteria();
         criteria.andEqualTo("valid","1");
         return mapper.selectByExample(example);
    }
    
    public List<Led> getTrafficLeds() {
   	 Example example = new Example(Led.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ledType","1");
        criteria.andEqualTo("valid","1");
        return mapper.selectByExample(example);
    }
    
    public List<Led> getBusLeds() {
      	 Example example = new Example(Led.class);
           Example.Criteria criteria = example.createCriteria();
           criteria.andEqualTo("ledType","2");
           criteria.andEqualTo("valid","1");
           return mapper.selectByExample(example);
      }
    
    public List<Led> getMetroLeds() {
      	 Example example = new Example(Led.class);
           Example.Criteria criteria = example.createCriteria();
           criteria.andEqualTo("ledType","3");
           criteria.andEqualTo("valid","1");
           return mapper.selectByExample(example);
      }
    
    public List<Led> getSchoolLeds() {
      	 Example example = new Example(Led.class);
           Example.Criteria criteria = example.createCriteria();
           criteria.andEqualTo("ledType","4");
           criteria.andEqualTo("valid","1");
           return mapper.selectByExample(example);
      }
    
    public List<Led> getCinemaLeds() {
      	 Example example = new Example(Led.class);
           Example.Criteria criteria = example.createCriteria();
           criteria.andEqualTo("ledType","5");
           criteria.andEqualTo("valid","1");
           return mapper.selectByExample(example);
      }
    
    public List<Led> getWeatherLeds() {
      	 Example example = new Example(Led.class);
           Example.Criteria criteria = example.createCriteria();
           criteria.andEqualTo("ledType","6");
           criteria.andEqualTo("valid","1");
           return mapper.selectByExample(example);
      }
    
    public List<Led> getConnectedLed() {
   	    Example example = new Example(Led.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andEqualTo("connectStatus","2");
        return mapper.selectByExample(example);
   }
    
    public List<Led> getUnunitedLed() {
    	Example example = new Example(Led.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        criteria.andEqualTo("connectStatus","1");
        return mapper.selectByExample(example);
    }
}
