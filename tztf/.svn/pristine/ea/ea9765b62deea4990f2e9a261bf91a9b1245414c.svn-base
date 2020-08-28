package cn.movinginfo.tztf.sen.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.movinginfo.tztf.common.utils.PointInAreaUtil;
import cn.movinginfo.tztf.sen.domain.Thunder;
import cn.movinginfo.tztf.sen.mapper.ThunderMapper;
import net.ryian.orm.service.BaseService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Component
public class ThunderService extends BaseService<Thunder,ThunderMapper>{

	public String getLdData() {
		return null;
	}
	
	public  List<String> readTxtFile(String filePath){ 
		List<String> list = new ArrayList<>();
	    try { 
	        String encoding="GBK"; 
	        File file=new File(filePath); 
	        if(file.exists() && file.isFile()){ //判断文件是否存在 
	          InputStreamReader read = new InputStreamReader( 
	          new FileInputStream(file),encoding);//考虑到编码格式 
	          BufferedReader bufferedReader = new BufferedReader(read); 
	          String lineTxt = null; 
	          while((lineTxt = bufferedReader.readLine()) != null){
	        	  list.add(lineTxt);
	            //System.out.println(lineTxt); 
	          } 
	          read.close(); 
	    }else{ 
	      System.out.println("找不到指定的文件"); 
	    } 
	    } catch (Exception e) { 
	      System.out.println("读取文件内容出错"); 
	      e.printStackTrace(); 
	    } 
	   return list;
	  }
	
	public void insertData(String data,double[] LngCollotions,double[] LatCollotions) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] datas = data.split("\\s+");//以空格分割
		List<Thunder> thunderList = getThunderByNum(datas[0]);
		if(thunderList.size() == 0) {
			Thunder thunder = new Thunder();
			thunder.setNum(datas[0]);//编号
			String time = datas[1] +" "+datas[2];//时间
			Date date = sdf.parse(time);
			thunder.setTime(date);
			thunder.setCreateDate(new Date());
			thunder.setLat(datas[3].split("=").length !=1?datas[3].split("=")[1]: "");//纬度
			thunder.setLon(datas[4].split("=").length !=1?datas[4].split("=")[1]: "");//经度
			thunder.setPower(datas[5].split("=").length !=1?datas[5].split("=")[1]: "");//强度
			thunder.setSteep(datas[6].split("=").length !=1?datas[6].split("=")[1]: "");//陡度
			thunder.setError(datas[7].split("=").length !=1?datas[7].split("=")[1]: "");//误差
			thunder.setModel(datas[8].split(":").length !=1?datas[8].split(":")[1]: "");//定位方式
			thunder.setProvince(datas[9].split(":").length !=1?datas[9].split(":")[1]: "");//省
			thunder.setCity(datas[10].split(":").length !=1?datas[10].split(":")[1]: "");//市
			thunder.setCounty(datas[11].split(":").length !=1?datas[11].split(":")[1]: "");//县
			if(PointInAreaUtil.JudgeInOutFirst(LngCollotions, LatCollotions, Double.parseDouble(datas[4].split("=")[1]), Double.parseDouble(datas[3].split("=")[1]))) {
				mapper.insertSelective(thunder);
			}
		}else {
			Thunder thunder =thunderList.get(0);
			String time = datas[1] +" "+datas[2];//时间
			Date date = sdf.parse(time);
			thunder.setTime(date);
			thunder.setUpdateDate(new Date());
			thunder.setLat(datas[3].split("=").length !=1?datas[3].split("=")[1]: "");//纬度
			thunder.setLon(datas[4].split("=").length !=1?datas[4].split("=")[1]: "");//经度
			thunder.setPower(datas[5].split("=").length !=1?datas[5].split("=")[1]: "");//强度
			thunder.setSteep(datas[6].split("=").length !=1?datas[6].split("=")[1]: "");//陡度
			thunder.setError(datas[7].split("=").length !=1?datas[7].split("=")[1]: "");//误差
			thunder.setModel(datas[8].split(":").length !=1?datas[8].split(":")[1]: "");//定位方式
			thunder.setProvince(datas[9].split(":").length !=1?datas[9].split(":")[1]: "");//省
			thunder.setCity(datas[10].split(":").length !=1?datas[10].split(":")[1]: "");//市
			thunder.setCounty(datas[11].split(":").length !=1?datas[11].split(":")[1]: "");//县
			if(PointInAreaUtil.JudgeInOutFirst(LngCollotions, LatCollotions, Double.parseDouble(datas[4].split("=")[1]), Double.parseDouble(datas[3].split("=")[1]))) {
			mapper.updateByPrimaryKeySelective(thunder);
			}
		}
		
		
	}
	
	public List<Thunder> getThunderByNum(String num) {
		Example example = new Example(Thunder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("num",num);
		return mapper.selectByExample(example);
	}
	
	public List<Thunder> getAllData(Date date1,Date date2) {
		Example example = new Example(Thunder.class);
		Criteria criteria = example.createCriteria();
		criteria.andBetween("time", date1, date2);
		return mapper.selectByExample(example);
	}
	
	public static String readFileToString(String filePath){
		String str="";
		File file=new File(filePath);
		try {
		 FileInputStream in=new FileInputStream(file);
		 // size 为字串的长度 ，这里一次性读完
		 int size=in.available();
		 byte[] buffer=new byte[size];
		 in.read(buffer);
		 in.close();
		 str=new String(buffer,"utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return str;
	}

}
