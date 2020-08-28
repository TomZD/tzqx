package cn.movinginfo.tztf.sev.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.sev.domain.EmergencyPlan;
import cn.movinginfo.tztf.sev.mapper.EmergencyPlanMapper;
import cn.movinginfo.tztf.sys.domain.Depart;
import cn.movinginfo.tztf.sys.domain.User;
import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;

@Component
public class EmergencyPlanService extends BaseService<EmergencyPlan, EmergencyPlanMapper>{

	public List<EmergencyPlan> query(Map<String, String> paramMap) {
		String statement = "queryEmergencyPlanPaged";
		List<EmergencyPlan> result = sqlSession.selectList(statement, paramMap);
        return result;
    }
	
	public PageInfo<EmergencyPlan> queryEmergencyPlanPaged(Map<String, String> parameterMap) {
		String rowsStr = parameterMap.get("rows") == null ? null
                : (String) parameterMap.get("rows");
        String pageStr = parameterMap.get("page") == null ? null
                : (String) parameterMap.get("page");
    	// 第几页
        int page = 1;
        // 每页显示数量
        int rows = 10;
        try {
            page = pageStr != null ? Integer.valueOf(pageStr) : page;
            rows = rowsStr != null ? Integer.valueOf(rowsStr) : rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageHelper.startPage(page, rows);
        String statement = "queryEmergencyPlanPaged";
		List<EmergencyPlan> list = sqlSession.selectList(statement, parameterMap);
        return new PageInfo(list);
	}
}
