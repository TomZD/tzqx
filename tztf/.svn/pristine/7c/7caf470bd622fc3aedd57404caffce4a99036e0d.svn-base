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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import cn.movinginfo.tztf.sev.domain.Alarm;
import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;
import cn.movinginfo.tztf.sev.service.AlarmService;
import cn.movinginfo.tztf.sev.service.ReleaseChannelInstanceService;
import cn.movinginfo.tztf.webserviceClient.alarmFile.CreateAlarmFileService;
import cn.movinginfo.tztf.webserviceClient.alarmFile.CreateAlarmFileServiceService;
import net.ryian.core.utils.SpringContextUtil;

/**
 * 显示屏发布渠道接口
 * @author: zhangd
 */
public class LedScreenChannel implements Channel {

	private static final Logger log = Logger.getLogger(LedScreenChannel.class);

	// 渠道发布内容
	private ReleaseChannelInstance channelInstance;

	private ReleaseChannelInstanceService releaseChannelInstanceService = SpringContextUtil.getBean("releaseChannelInstanceService");
	
	private AlarmService alarmService = SpringContextUtil.getBean(AlarmService.class);

//	// LED大屏，数据库链接
//	protected JdbcTemplate ledJdbcTemplate = SpringContextUtil.getBean("ledJdbcTemplate");
	
	// 日期格式化参数
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static final Map<String,String> msgNumMap = new HashMap<String,String>(){
		{
			put("台风", "a");
			put("暴雨", "b");
			put("暴雪", "c");
			put("寒潮", "d");
			put("大风", "e");
			put("大雾", "f");
			put("雷电", "g");
			put("冰雹", "h");
			put("霜冻", "i");
			put("高温", "j");
			put("干旱", "k");
			put("道路结冰", "l");
			put("霾", "m");
		}
	};
	
	/**
	 * 获取LED大屏编号列表<p>
	 * @return
	 * @author: zhangd
	 * @createTime: 2017年9月4日 上午9:26:37
	 * @updateTime: 
	 */
	public List<String> getLedNumList() {
		String sql = "SELECT * FROM RF_LEDUser";
//		String sql = "SELECT * FROM RF_LEDUser WHERE ID=36";// 测试SQL，只测试编号为“LED035”的LED大屏
		List<String> list = new ArrayList<String>();
//		list = ledJdbcTemplate.query(sql, new RowMapper<String>() {
//			@Override
//			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//				String ledNum = rs.getString("Led_Num");
//				return ledNum;
//			}
//		});
		return list;
	}
	
	/**
	 * 发送LED大屏，向LED大屏信息表中插入一条数据，并返回生成的主键ID<p>
	 * @param ledNum LED大屏编号
	 * @param msg 信息内容
	 * @param msgNum 信息编号
	 * @return 主键ID
	 * @author: zhangd
	 * @createTime: 2017年9月4日 上午10:06:31
	 * @updateTime: 
	 */
	public Long sendLed(String ledNum, String msg, String msgNum) {
		String sql = "UPDATE LEDScreen_Msg SET Switch=0 WHERE Screen_Num=''{0}'' AND Msg_Num=''{2}'' AND Switch=1; INSERT INTO LEDScreen_Msg(Screen_Num, District, Msg_Num, Msg, inTime, outTime) VALUES (''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'')";
		Date date = new Date();
		String intime = sdf.format(date);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		String outTime = sdf.format(date);
		Object[] o = {ledNum, "58457", msgNum, msg, intime, outTime};
		final String isql = MessageFormat.format(sql, o);
		
		// 执行插入语句后，获取自动生成的主键
		KeyHolder keyHolder = new GeneratedKeyHolder();
		Long autoIncId = 0L;
//		ledJdbcTemplate.update(new PreparedStatementCreator() {
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement ps = con.prepareStatement(isql, PreparedStatement.RETURN_GENERATED_KEYS);
//				return ps;
//			}
//		}, keyHolder);
//		autoIncId = keyHolder.getKey().longValue();
		
		return autoIncId;
	}
	
	/**
	 * 根据名称获取信息类型<p>
	 * @param name
	 * @return
	 * @author: zhangd
	 * @createTime: 2017年9月4日 上午11:33:18
	 * @updateTime: 
	 */
	public String getMsgNum(String name) {
		String msgNum = null;
		for (Map.Entry<String, String> entry : msgNumMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (name.indexOf(key)!=-1) {
				msgNum = value;
				break;
			}
		}
		return msgNum;
	}
	
	@Override
	public void doRelease(String content, MiddleObject midObj) throws ChannelReleaseException {
		System.out.println("显示屏渠道开始");
		System.out.println(channelInstance.getContent());
	
		int result=alarmService.createFile(42);
		if(result==1){
			channelInstance.setReleaseState("4");
			channelInstance.setFeedbackMessage("文件生成成功");
		}else{
			channelInstance.setReleaseState("3");
			channelInstance.setFeedbackMessage("文件生成失败");
		}
		System.out.println("显示屏渠道结束");
	}

	@Override
	public void doReleaseFile(String filePath, MiddleObject midObj) throws ChannelReleaseException {
		// TODO Auto-generated method stub
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

}
