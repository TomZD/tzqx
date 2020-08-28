package cn.movinginfo.tztf.sen.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.sen.mapper.TyphoonDataMapper;
import cn.movinginfo.tztf.sen.mapper.TyphoonNewMapper;
import cn.movinginfo.tztf.sen.model.TyphoonData;
import cn.movinginfo.tztf.sen.model.TyphoonDataExample;
import cn.movinginfo.tztf.sen.model.TyphoonNew;
import cn.movinginfo.tztf.sen.model.TyphoonNewExample;



@Service
public class TyphoonNewService {
    @Resource
    private TyphoonNewMapper typhoonNewMapper;

	public void saveData(List<TyphoonNew> list) {
		String year="";
		List<String> years=new ArrayList();
		for (TyphoonNew t : list) {
			if(!t.getBianhao().equals(year)){
				year=t.getBianhao();
				years.add(year);
			}
		}
		TyphoonNewExample example=new TyphoonNewExample();
		example.createCriteria().andBianhaoIn(years);
		typhoonNewMapper.deleteByExample(example);
		typhoonNewMapper.insertList(list);
		
	}


    

}
