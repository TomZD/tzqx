package cn.movinginfo.tztf.sys.service;

import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sys.domain.FooterContact;
import cn.movinginfo.tztf.sys.mapper.FooterContactMapper;
import net.ryian.orm.service.BaseService;

@Service
public class FooterContactService extends BaseService<FooterContact, FooterContactMapper> {
	
	/**
	 * 返回valid为1的唯一数据，理论上表里只能有一条
	 * @param areaId 
	 * @return
	 */
	public FooterContact getFootValue(String areaId){
		FooterContact record = new FooterContact();
		record.setAreaId(areaId);
		return mapper.selectOne(record);
	}
}
