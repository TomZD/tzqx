package cn.movinginfo.tztf.sev.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.sev.domain.Temperson;
import cn.movinginfo.tztf.sev.mapper.TempersonMapper;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

@Component
public class TempersonService extends BaseService<Temperson, TempersonMapper>{
	
	public List<Temperson> query(Map<String, String> paramMap) {
        Example example = new Example(Temperson.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String name = paramMap.get("name");
        String phone = paramMap.get("phone");
        String deptName = paramMap.get("deptName");
        String areaName = paramMap.get("areaName");
        String areaId = paramMap.get("areaId");
        if(!StringUtils.isEmpty(name)){
        	 criteria.andLike("name", "%" + name+ "%");
        }
        if(!StringUtils.isEmpty(phone)){
       	 criteria.andEqualTo("phone", phone);
       }
        if(!StringUtils.isEmpty(areaName)){
         criteria.andEqualTo("areaName",areaName);	
       }
        if(!StringUtils.isEmpty(deptName)){
          	 criteria.andEqualTo("deptName",deptName);
          }
        if(!StringUtils.isEmpty(areaId)){
         	 criteria.andEqualTo("areaId",areaId);
         }
        return mapper.selectByExample(example);
    }
	
	public Temperson findTempersonByPhone(String phone) {
		Temperson tem = new Temperson();
		tem.setValid(1);
		tem.setPhone(phone);
		return mapper.selectOne(tem);
	}
	
	public Temperson findTempersonById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public Temperson findTempersonByNotIdAndPhone(Long id,String phone) {
		return mapper.findTempersonByNotIdAndPhone(id,phone);
	}
}
