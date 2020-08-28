package cn.movinginfo.tztf.common.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.movinginfo.tztf.common.constants.SystemProperties;
import cn.movinginfo.tztf.common.utils.DateUtil;
import cn.movinginfo.tztf.sen.model.TownInfo;
import cn.movinginfo.tztf.sen.model.TownOCF;
import cn.movinginfo.tztf.sen.service.TownInfoService;
import cn.movinginfo.tztf.sen.service.TownOCFService;



/*
 *@author fengxy
 *@time 2019年4月24日上午11:33:38
 *
 */
@Service("townOCFJob")
public class TownOCFJob {
	
	@Autowired
    private TownOCFService townOCFService;
	@Autowired
    private TownInfoService townInfoService;
	
	public void job() throws NumberFormatException, ParseException {
		System.out.println(new Date() +"-------开始解析OCF数据------");
		townOCFJob();
		System.out.println(new Date() +"-------结束解析OCF数据------");
	}

	private void townOCFJob() throws NumberFormatException, ParseException {
		List<TownOCF> ocfList=new ArrayList();
		List<String> list = new ArrayList();
		List<TownInfo> stationlist=townInfoService.getTownInfo();
		for (TownInfo t:stationlist) {
			list.add(t.getIiiii());	
		}
		
		SimpleDateFormat sdfhour = new SimpleDateFormat("yyyyMMddHH");
//		SimpleDateFormat sdfmonth = new SimpleDateFormat("yyyyMM");
//		String url=ConfigHelper.getProperty("ocfurl");
		String url=SystemProperties.getProperty("ocfurl");
    	url=url.replace("yyyyMM", DateUtil.format(new Date(), "yyyyMM"));
    	String [] files=new File(url).list();
    	if(files!=null){
    		for (int a = 0; a < files.length; a++) {
        		if(a!=files.length-1)continue;
        		String datafile=url+files[a]+"/";
        		String time=files[a].split("_")[0];
//        		Date date=sdfhour.parse(String.valueOf(Integer.valueOf(time)+8));
        		Date date=DateUtil.parse(String.valueOf(Integer.valueOf(time)+8), "yyyyMMddHH");
        		System.out.println(datafile);
        		  File file = new File(datafile);
    		        
    		        
    		        String [] file1=file.list();
    		        
    		        for (int i = 0; i < file1.length; i++) {
    		        	String townCode=file1[i].split("\\.")[2];
    		        	if(list.contains(townCode)){
    		        		String content = txt2String(new File(datafile+file1[i]));
    		        		
    		        		 String[] datas=content.split("\r\n");
    		 		        for (int j = 4; j < datas.length; j++) {
    		 		        	TownOCF town =new TownOCF();
    		 		        	town.setTownCode(townCode);
    		 		        	List<String>  strs=new ArrayList();
    		 		        	String []  arrstrs=datas[j].split(" ");
    		 		        	for (int k = 0; k < arrstrs.length; k++) {
    								if(!arrstrs[k].equals("")){
//    									System.out.println(arrstrs[k]);
    									strs.add(arrstrs[k]);
    								}
    							}
    		 		        	town.setDateX(date);
    		 		        	town.setFh(Short.parseShort(strs.get(0)));
    		 		        	town.setCloud(new BigDecimal(strs.get(1)));
    		 		        	town.setT(new BigDecimal(strs.get(2)));
    		 		        	town.setTmax(new BigDecimal(strs.get(3)));
    		 		        	town.setTmin(new BigDecimal(strs.get(4)));
    		 		        	town.setRh(new BigDecimal(strs.get(5)));
    		 		        	town.setPr03(new BigDecimal(strs.get(6)));
    		 		        	town.setPr06(new BigDecimal(strs.get(7)));
    		 		        	town.setPr12(new BigDecimal(strs.get(8)));
    		 		        	town.setPr24(new BigDecimal(strs.get(9)));
    		 		        	town.setWindD(new BigDecimal(strs.get(10)));
    		 		        	town.setWindS(new BigDecimal(strs.get(11)));
    		 		        	town.setSnowf03(new BigDecimal(strs.get(12)));
    		 		        	town.setSnowf06(new BigDecimal(strs.get(13)));
    		 		        	town.setSnowf12(new BigDecimal(strs.get(14)));
    		 		        	town.setSnowf24(new BigDecimal(strs.get(15)));
    		 		        	town.setWw3(strs.get(16));
    		 		        	town.setWw6(strs.get(17));
    		 		        	town.setWw12(strs.get(18));
    		 		        	ocfList.add(town);
    		 				}
    		        	}
    				}
    		}
        	Boolean result=false;
    		while(!result){
    			result=townOCFService.insertOrUpdateData(ocfList);
    		}
    	}
    	

    }
	    	
	      

		
	
	 public static String txt2String(File file){
	        StringBuilder result = new StringBuilder();
	        try{
	            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	            String s = null;
	            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	                result.append(System.lineSeparator()+s);
	            }
	            br.close();    
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return result.toString();
	    }

}
