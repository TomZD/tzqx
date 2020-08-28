package cn.movinginfo.tztf.sev.service;

import net.ryian.commons.StringUtils;
import net.ryian.orm.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.movinginfo.tztf.sev.domain.Reminder;
import cn.movinginfo.tztf.sev.domain.Suser;
import cn.movinginfo.tztf.sev.mapper.ReminderMapper;
import cn.movinginfo.tztf.sev.mapper.SuserMapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
* @description:
* @author: autoCode
* @history:
*/
@Component
public class  ReminderService extends BaseService<Reminder,ReminderMapper> {
	
	@Autowired
	SuserMapper suserMapper;

    /**
    * 根据条件查询分页
    * @param paramMap
    * @return
    */
    public List<Reminder> query(Map<String, String> paramMap) {
        Example example = new Example(Reminder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid","1");
        String suid = paramMap.get("suid");
        if (!StringUtils.isEmpty(suid)) {
            criteria.andLike("suid", "%" + suid+ "%");
        }
        return mapper.selectByExample(example);
    }
    
    public List<Reminder> queryById(String id) {
        Example example = new Example(Reminder.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(id)) {
            criteria.andLike("id", "%" + id+ "%");
        }
        return mapper.selectByExample(example);
    }
    
    public PageInfo<List<Reminder>> queryReminder(Map<String, String> paramMap) {
		String rowsStr = paramMap.get("rows") == null ? null
                : (String) paramMap.get("rows");
        String pageStr = paramMap.get("page") == null ? null
                : (String) paramMap.get("page");

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
		
        String statement = "cn.movinginfo.tztf.sev.domain.Reminder.reminder";
        List<Reminder> list = sqlSession.selectList(statement, paramMap);
        return new PageInfo(list);
    }
    
    /**
     * 查询所有valid字段值为1的用户
     * @param paramMap request中的参数map
     * @return 查询结果为Suser容器
     * @author 韩明睿
     */
    public List<Suser> querySuser(Map<String, String> paramMap) {
    	Example example = new Example(Suser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("valid", "1");
        return suserMapper.selectByExample(example);
    }
}
