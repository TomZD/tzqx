package cn.movinginfo.tztf.common.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil extends DateUtils {
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**格式yyyy-MM-dd,这个格式固定，不要改*/
	public static final String YMD_FORMAT = "yyyy-MM-dd";

	public static final String YM_FORMAT = "yyyy-MM";

	public static final String NO_SECOND_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static final String YMDHM_FORMAT = "yyyyMMddHHmm";
	
	public static long SENCOD_IN_MILLS = 1000L;
	public static long MINUTE_IN_MILLS = SENCOD_IN_MILLS * 60;
	public static long HOUR_IN_MILLS = MINUTE_IN_MILLS * 60;
	public static long DAY_IN_MILLS = HOUR_IN_MILLS * 24;
	
	private static final String[] parsePatterns = new String[] {
        "yyyy-MM-dd HH:mm:ss",
        "yyyy-MM-dd HH:mm",
        "yyyyMMdd",
        "yyyy-MM-dd",
        "yyyy-MM",
        "HH:mm",
        "MM/dd/yyyy HH:mm:ss",
        "MM/dd/yyyy",
		"yyyy/MM/dd",
        "yyyy/MM/dd HH:mm",
		"yyyy年MM月dd日",
		"M月d日",
		"yyyyMMddHHmm",
		"yyyyMMdd_HHmm",
		"yyyyMMddHH"
        // 这里可以增加更多的日期格式，用得多的放在前面
    };
	
	public static String format(Date date, String format) {
		if(date == null) return "";
		
		SimpleDateFormat df = new SimpleDateFormat(StringUtils.defaultIfEmpty(
				format, DEFAULT_FORMAT));
		return df.format(date);
	}
	
	public static String format(Date date) {
		return format(date, DEFAULT_FORMAT);
	}

	public static Date parse(String date) {
		if (Objects.isNull(date)) {
			return null;
		}
		try {
			return parseDate(date, parsePatterns);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parse(String date, String format) {
		if(StringUtils.isBlank(date)) {
			return null;
		}
		try {
			return parseDate(date, format);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean canParseTime(String time)
	{
		try {
			parseDate(time, parsePatterns);
			return true;
		}
		catch (Exception ex)
		{
			return  false;
		}
	}

	public static Date defaultParse(String date) {
		return parse(date, DEFAULT_FORMAT);
	}
	
	/**
	* 判断是否是昨天的某个时间
	 */
	public static boolean isYesterdaySomeTime(Date date) {
    	return isSomeTimeInDay(date, -1);
    }
	
	public static boolean isSomeTimeInDay(Date date, int dayIndex) {
    	if(date == null) return false;
    	
    	Date d2 = addDays(new Date(), dayIndex);
    	return isSameDay(date, d2);
    }
	
	
	/**
	 * 获取某一天及其前后几天的零点
	 */
	public static Date getZeroTime(Date date, int n){
		if (Objects.isNull(date)) {
			return null;
		}
		return addDays(truncate(date, Calendar.DAY_OF_MONTH), n);
	}
	
	/**
	 *判断两个日期是否是同一天
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if(date1 == null || date2 == null) return false;
		
		return truncatedEquals(date1, date2, Calendar.DAY_OF_MONTH);
	}
	
	/**
	 *
	 * @param early
	 * @param late
	 * @param type {@link Calendar#HOUR_OF_DAY} etc..
	 * @return
	 */
	public static int timeBetween(Date early, Date late, int type) {
		long v = 1L;
		switch (type) {
		case Calendar.DAY_OF_MONTH:
			v = DAY_IN_MILLS;
			break;
		case Calendar.HOUR:
		case Calendar.HOUR_OF_DAY:
			v = HOUR_IN_MILLS;
			break;
		case Calendar.MINUTE:
			v = MINUTE_IN_MILLS;
			break;
		case Calendar.SECOND:
			v = SENCOD_IN_MILLS;
			break;
		case Calendar.MILLISECOND:
			// default 1
			break;
		default:
			throw new RuntimeException("Unsupported type of:" + type);
		}
		
		return (int)((late.getTime() - early.getTime()) / v);
	}

	
	/**
	 * 数据库定义 1 SOLAR 阳历; 2 LUNAR 阴历; 增加START 占位enum
	 * @author iacdp
	 *
	 */
	public enum Type {
		START(""), SOLAR("阳历"), LUNAR("阴历");
		
		private String description;

		private Type(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}

	/**
	 * 判断当前日期是星期几
	 *
	 * @param dateTime 要判断的时间
	 * @return dayForWeek 判断结果
	 */
	public static String dayForWeek (Date dateTime ) throws Exception
	{
		Calendar c = Calendar.getInstance ( );
		c.setTime ( dateTime );
		int dayForWeek = 0;
		if ( c.get ( Calendar.DAY_OF_WEEK ) == 1 )
		{
			dayForWeek = 7;
		}
		else
		{
			dayForWeek = c.get ( Calendar.DAY_OF_WEEK ) - 1;
		}
		return String.valueOf(dayForWeek);
	}




	/**
	 * 获取当前日期所在月份的所有日期
	 * @param date 当前日期
	 * @return List<Date>
	 */
	public static List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}

	/**
	 * 获取指定日期是周几
	 * @param date 指定日期
	 * @return 指定日期对应的周几
	 */
	public static String getDayOfWeekByDate(Date date) {
		String dayOfweek = "-1";
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = myFormatter.parse(myFormatter.format(date));
			SimpleDateFormat formatter = new SimpleDateFormat("E", Locale.CHINA);
			String str = formatter.format(myDate);
			dayOfweek = str.replace("星期", "周");

		} catch (Exception e) {
			System.out.println("错误!");
		}
		return dayOfweek;
	}

	/**
	 * 比较两个日期大小
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return
	 */
	public static int compareTo(Date date1, Date date2) {
		if (Objects.isNull(date1) || Objects.isNull(date2)) {
			return 0;
		}
		return truncatedCompareTo(date1, date2, Calendar.SECOND);
	}

	/**
	 * 比较日期1是否小于日期2
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return
	 */
	public static boolean lessThan(Date date1, Date date2) {
		return compareTo(date1, date2) == -1;
	}

	/**
	 * 比较日期1是否大于日期2
	 * @param date1 日期1
	 * @param date2 日期2
	 * @return
	 */
	public static boolean moreThan(Date date1, Date date2) {
		return compareTo(date1, date2) == 1;
	}
	
	/**
	 * 获取输入日期的23:59:59秒
	 * @param date
	 * @return
	 */
	public static Date getLastSecondByDate(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return addMilliseconds(truncate(date, Calendar.DAY_OF_MONTH), (int) DAY_IN_MILLS - 1);
	}
	
	/**
	 * 获取当天的23:59:59
	 * @return
	 */
	public static Date getLastSecondOfToDay() {
		return getLastSecondByDate(new Date());
	}
	
	/**
	 * 获取输入日期的月份的第一天
	 * @param date date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}

	/**
	 * 获取两个时间差
	 * @param date1 时间一
	 * @param date2 时间二
	 * @param field 参数 天
	 * @return
	 */
	public static long getDateDiff(Date date1 , Date date2, int field) {
		if (Objects.isNull(date1) || Objects.isNull(date2)) {
			return -1;
		}
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long diffTime = time1 - time2;
		long diff = diffTime > 0 ? diffTime : - diffTime;
		switch (field) {
			case Calendar.DAY_OF_YEAR:
				return diff/DAY_IN_MILLS;
			case Calendar.HOUR:
				return  diff/HOUR_IN_MILLS;
			case Calendar.MINUTE:
				return diff/MINUTE_IN_MILLS;
			case Calendar.SECOND:
				return diff/SENCOD_IN_MILLS;
			default:
				return -1;
		}
	}
	
	public static long getNowDiff(Date date, int field ) {
		return getDateDiff(date, new Date(), field);
	}
	
	/**
	 * 当前日期加一天
	 * @param date 输入日期
	 * @return 返回当前日期加一天
	 */
	public static Date addOneDay(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return addDays(date, 1);
	}

	/**
	 * 当前日期减一天
	 * @param date 输入日期
	 * @return 返回当前日期减一天
	 */
	public static Date  minusOneDay(Date date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return addDays(date, -1);
	}
	
	
	public static Date toDate(String time1){
		Date t1 = null;

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			t1 = sdf.parse(time1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return t1;
	}
	
	public static Date toHourMinute(Date time){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String format = sdf.format(time);
	    Date result = DateUtil.toDate(format);
	    return result;
	}
	
    /**
     * 判断时间是不是今天
     * @param date
     * @return    是返回true，不是返回false
     */
    public static boolean isNow(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
         
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }
    
    /**
     * @param workTime
     * @return
     */
    public static String strTime(String workTime){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String pubtime = sf.format(new Date()) + " " + workTime;
        return pubtime;
    }
    
    public static String minHourTime(){
    	 SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	 return sf1.format(new Date());
    }
    
    public static String minTime(String str){
    	if(str.length() < 12){
    		String r = str + " 00:00";
    		return r;
    	}
    	return str;
    }
    
    public static String maxTime(String str){
    	if(str.length() < 12){
    		String r = str + " 23:59";
    		return r;
    	}
    	return str;
    }	
    public static Boolean ifSameDay(String str , Date now){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String date = sf.format(now);
        return date.equals(str);
    }
    
    public static Boolean nowDay(String str , Date now){
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	String date = sf.format(now);
    	return date.equals(str);
    }

	public static Date addTwoHours(String hour, Date now) throws ParseException {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd ");
		String string = sf1.format(now)+hour;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sf.parse(string);
	    long curren = date.getTime();
        curren += 2 * 60 * 60 * 1000 ;
        Date da = new Date(curren);
		return da;
	}

	public static Date addFifteenMinutes(String minute, Date now) throws ParseException {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd ");
		String string = sf1.format(now)+minute;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sf.parse(string);
	    long curren = date.getTime();
        curren += 15 * 60 * 1000 ;
        Date da = new Date(curren);
		return da;
	}
	
	public static String getMonthAndDay(int day){
		Date date = new Date();
		Date newDay = addDays(date , day);
		SimpleDateFormat sf1 = new SimpleDateFormat("M月dd日");
		return sf1.format(newDay);
	}
	
	
	//不带时分
	public static Date getYMD(String strdate){
		if(StringUtils.isEmpty(strdate)) {
			return null;
		}
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sf1.parse(strdate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//带时分
	public static Date getYMDHM(String strdate){
		if(StringUtils.isEmpty(strdate)) {
			return null;
		}
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = sf1.parse(strdate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean hasPublished(String forecasttime, Boolean b, Date date) {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		String now = sf1.format(new Date());
		forecasttime = forecasttime.substring(0, 10);
		if(!b){
			return false;
		}
		return now.equals(forecasttime);
	} 
	
	// 获取指定当天指定时间  如：10：00  返回 2018-07-10 10 ：00     传参  10：00 
	public static Date getSpecifiedTime(String time) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pubTime = DateUtil.strTime(time);
		Date specifiedTime = sf.parse(pubTime);
		return specifiedTime;
	}
	public static String getYMDHMstr(Date date){
		SimpleDateFormat sf = new SimpleDateFormat(YMDHM_FORMAT);
		String result = sf.format(date);
		return result;
		
	}
	public static Date getYesterdayTwenty(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,20);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		calendar.add(Calendar.DAY_OF_YEAR,-1);
		Date date = calendar.getTime(); 
		return date;
	}
	public static Date getBeforeTen(){
		Calendar calendar = Calendar.getInstance();
		//减去10分钟，分钟个位数取0
		calendar.add(Calendar.MINUTE, -5);
		//获取分钟为5的倍数
		int minuteNum = calendar.get(Calendar.MINUTE) / 5;
		int minute=minuteNum * 5;
		calendar.set(Calendar.MINUTE,minute);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		Date date = calendar.getTime();
		return date;
	}
	
	public static int daysBetween(String stime , String etime){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = format.parse(stime);
			date2 = format.parse(etime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int a = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
		return a;
	}

 	/**
	 * 判断一个时间是否在一个时间段之间
	 * 日期格式：xxxx-xx-xx 2018-05-26
	 * @param m 为目标时间  
	 * @param st 为起始时间
	 * @param et 为结束时间
	 * @return if st<=m<=et true else false
	 */
	public static boolean getInDate(String m,String st,String et) {
		
		boolean a =false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	    Date startDate=new Date();
	    Date endDate=new Date();
	    Date d = new Date();
		try {
			startDate = sdf.parse(st);
			endDate = sdf.parse(et);
			d = sdf.parse(m);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		String mDt = sdf.format(d);
		
	    if ((d.after(startDate) && (d.before(endDate)))
	        || (mDt.equals(sdf.format(startDate)) || mDt.equals(sdf
	            .format(endDate)))) {
	      a=true;
	    } else {
	      a=false;
	    }
	    return a;
	}

	/**
	 * 获取七天预报时间方法
	 * @return 预报时间map
	 */
	public static List<Map<String, Timestamp>> getDate() {
		List<Map<String, Timestamp>> list = new ArrayList<>();
		Date date = new Date();
		//获取昨天晚上20点的Timestamp类型日期
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 20);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		for(int i=1; i<=7; i++){
			Map<String, Timestamp> map = new HashMap<>();
			//当天温度的起始时间
			Timestamp yesterday20 = new Timestamp(calendar.getTimeInMillis());
			//当天的天气第一个预报时间
			calendar.add(Calendar.HOUR_OF_DAY, 12);
			Timestamp today8 = new Timestamp(calendar.getTimeInMillis());
			//当天天的天气第二个预报时间 第二天温度的结束时间
			calendar.add(Calendar.HOUR_OF_DAY, 12);
			Timestamp today20 = new Timestamp(calendar.getTimeInMillis());
			map.put("yesterday20",yesterday20);
			map.put("today8",today8);
			map.put("today20",today20);
			list.add(map);
		}
		return list;
	}

}












