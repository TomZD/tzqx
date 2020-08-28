package cn.movinginfo.tztf.sen.model;

import java.text.DecimalFormat;



/**
 * @Author: baolonghui
 * @Description
 * @Data Created in 14:22 2018/6/21
 * @Modified By:
 */
public class TyphoonItemForecastPoint {
    private String time;
    private String lon;
    private String lat;
    private String windPower;
    private String windVelocity;
    private String pressure;
    
    private String distance;

   	public String getDistance() {
   		return distance;
   	}

   	public void setDistance(String distance) {
   		this.distance = distance;
   	}
   	
   	public String getSimi(double lat,double lon,double lat1,double lon1){

   		 DecimalFormat df=new DecimalFormat("########0.00");
   		
   		    double radLat1 = lat*Math.PI/180.0;
   			double radLat2 = lat1*Math.PI/180.0;
   			double a = radLat1 - radLat2;
   			double b = lon*Math.PI/180.0 - lon1*Math.PI/180.0;		
   			double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
   					+ Math.cos(radLat1) * Math.cos(radLat2)
   					* Math.pow(Math.sin(b / 2), 2)));
   			s = s * 6378;
   			s = Math.round(s * 10000d) / 10000d;
   			return df.format(s);
   		 
   	
   	}

   	public void setDistance(String xianzaijindu, String xianzaiweidu) {
   		
   		if(xianzaijindu.indexOf("*")>-1||xianzaiweidu.indexOf("*")>-1||"".equals(xianzaijindu)||"".equals(xianzaiweidu)){
   			this.distance = "0";
   		}else{
   			this.distance = getSimi(Double.parseDouble(xianzaijindu),Double.parseDouble(xianzaiweidu),120.5,30.48);
   		}
   		
   		
   	}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWindVelocity() {
        return windVelocity;
    }

    public void setWindVelocity(String windVelocity) {
        this.windVelocity = windVelocity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
