package cn.movinginfo.tztf.sys.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.movinginfo.tztf.common.domain.Serie;
import cn.movinginfo.tztf.sys.domain.ChannelCount;
import cn.movinginfo.tztf.sys.domain.ChannelCountExample;
import cn.movinginfo.tztf.sys.mapper.ChannelCountMapper;



@Component
public class ChannelCountService {
	@Autowired
    protected ChannelCountMapper channelCountMapper;

	public JSONArray getCountList() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy");	
		Calendar nowTime = Calendar.getInstance();
		
		Date month1 = null;
		Date month2 = null;
		Date year1 = null;
		Date year2 = null;
		try {
			month1=format1.parse(format1.format(nowTime.getTime()));
			year1=format2.parse(format2.format(nowTime.getTime()));
			 nowTime.add(Calendar.MONTH, 1);
			 month2=format1.parse(format1.format(nowTime.getTime()));
			 nowTime.add(Calendar.MONTH, 11);
			 year2=format2.parse(format2.format(nowTime.getTime()));
			 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		 
		
		String [] channels={"LED","传真"};
		JSONArray data= new JSONArray();
		for(int i=0;i<channels.length;i++){
			JSONObject o=new JSONObject();
			o.put("name", channels[i]);
			int countMonth=channelCountMapper.getCountMonthByChannel(channels[i],month1,month2);
			o.put("month", countMonth);
			int countYear=channelCountMapper.getCountYearByChannel(channels[i],year1,year2);
			o.put("year", countYear);
			int count=channelCountMapper.getCountByChannel(channels[i]);
			o.put("now", count);
			data.add(o);
		}
		return data;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return channelCountMapper.getCount();
	}

	public int getDxMonth() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy");	
		Calendar nowTime = Calendar.getInstance();
		
		Date month1 = null;
		Date month2 = null;
		Date year1 = null;
		Date year2 = null;
		try {
			month1=format1.parse(format1.format(nowTime.getTime()));
			year1=format2.parse(format2.format(nowTime.getTime()));
			 nowTime.add(Calendar.MONTH, 1);
			 month2=format1.parse(format1.format(nowTime.getTime()));
			 nowTime.add(Calendar.MONTH, 11);
			 year2=format2.parse(format2.format(nowTime.getTime()));
			 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return channelCountMapper.getCountMonthByChannel("全网短信",month1,month2);
	}
	public int getDxYear() {
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy");	
		Calendar nowTime = Calendar.getInstance();
		Date year1 = null;
		Date year2 = null;
		try {
			year1=format2.parse(format2.format(nowTime.getTime()));
			 nowTime.add(Calendar.MONTH, 12);
			 year2=format2.parse(format2.format(nowTime.getTime()));
			 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return channelCountMapper.getCountYearByChannel("全网短信",year1,year2);
	}
	public List<String> getCountByChannel(String channel) {
		String num = String.valueOf(channelCountMapper.getCountByChannel(channel));
		List<String> nums=new ArrayList<String>();
		for(int i=0;i<num.length();i++){
			String str=num.substring(i, i+1);
			nums.add(str);
		}
		
		return  nums;
	}
	
	public List<Serie> getHighcharts1(){
		String [] channels={"微信","微博"};
		List <Serie> series=new ArrayList();
		for(int i=0;i<channels.length;i++){
			Serie serie=new Serie();
			serie.setData(getDataByName(channels[i]));
			serie.setName(channels[i]);
			series.add(serie);
		}
		return series;
		
	}

	public String[][] getHighcharts() {
		String [][] service = new String[4][7];
		service[0]=getDay();
		service[1]=getDataByName("微信");
		service[2]=getDataByName("微博");
		service[3]=getDataByName("杭州天气网");
		
		
		return service;
	}
	private String[] getDataByName(String channel) {
		String [] data = new String[7];
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.DATE, -7);
		for(int i=0;i<7;i++){
			nowTime.add(Calendar.DATE, 1);
			int count;
			try {
				//System.out.println(format1.parse(format1.format(nowTime.getTime())));
				ChannelCount c = channelCountMapper.getCountByChannelAndDate(channel,format1.parse(format1.format(nowTime.getTime())));
				if(c!=null){
					data[i]=String.valueOf(c.getCount());
				}else{
					data[i]="0";
				}
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		return data;
	}

	private String[] getDay() {
		String[][] weekDays = {{  "周一", "周二", "周三", "周四", "周五", "周六" ,"周日" },
				{ "周二", "周三", "周四", "周五", "周六","周日", "周一" },
				{ "周三", "周四", "周五", "周六","周日", "周一", "周二" },
				{"周四", "周五", "周六" ,"周日", "周一", "周二", "周三" },
				{ "周五","周六", "周日", "周一", "周二", "周三", "周四"  },
				{ "周六", "周日", "周一", "周二", "周三", "周四", "周五" },
				{ "周日", "周一", "周二", "周三", "周四", "周五", "周六" }};
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

		if (w < 0)
			w = 0;
		
		return weekDays[w];
	}

	public int getWebCount() {
		// TODO Auto-generated method stub
		return channelCountMapper.getWebCount();
	}
	
	public void saveCount(ChannelCount count) {
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		
		Date countTime=new Date();
		try {
			countTime=df2.parse(df2.format(countTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChannelCount  channelcount=channelCountMapper.getCountByChannelAndDate(count.getChannelName(),countTime);
		if(channelcount==null){
			ChannelCount  newchannel=new ChannelCount();
			newchannel.setChannelName(count.getChannelName());
			newchannel.setDate(countTime);
			newchannel.setCount(count.getCount());
			channelCountMapper.insert(newchannel);
		}else{
			ChannelCount  newchannel=new ChannelCount();
			newchannel.setChannelName(count.getChannelName());
			newchannel.setId(channelcount.getId());
			newchannel.setDate(countTime);
			newchannel.setCount(count.getCount());
			channelCountMapper.updateByPrimaryKey(newchannel);
	}
	
	
	}

}
