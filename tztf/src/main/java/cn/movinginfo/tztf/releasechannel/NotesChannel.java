package cn.movinginfo.tztf.releasechannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import net.ryian.core.utils.SpringContextUtil;

/**
 * 短信全网发布渠道接口
 * @author zhangd
 */
public class NotesChannel implements Channel {

	private static final Logger log = Logger.getLogger(NotesChannel.class);
	
	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;
	
    private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
	
//	// 短信用户，数据库链接
//    protected JdbcTemplate smsAllUserJdbcTemplate = SpringContextUtil.getBean("smsAllUserJdbcTemplate");
//	
//	// 短信内容，数据库链接
//	protected JdbcTemplate smsAllMessageJdbcTemplate = SpringContextUtil.getBean("smsAllMessageJdbcTemplate");
	
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	
	@Override
    public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
    }

    @Override
    public void doReleaseFile(String filePath, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    	Alarm alarm = (Alarm)midObj;
    	Date date = alarm.getCreateDate();
    }

    @Override
    public void doRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doDefaultRemove(String content, MiddleObject midObj) throws ChannelReleaseException {
        // TODO Auto-generated method stub
    }

    @Override
    public void setChannelInstance(ReleaseChannelInstance channelInstance) {
    	this.channelInstance = channelInstance;
    }
    
    public static void main(String[] args) {
    	
	}

}
