package cn.movinginfo.tztf.sen.model;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @Author: baolonghui
 * @Description
 * @Data Created in 14:16 2018/6/21
 * @Modified By:
 */
public class TyphoonInfoItem {
    private String direction;
    private String time;
    private String lon;
    private String lat;
    private String windPower;
    private String windVelocity;
    private String lv7;
    private String lv10;
    private String lv12;
    private String pressure;
    private String moveVelocity;
    private String type;
    private String distance;
    private List<TyphoonItemForecastInfo> forecasts;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public String getLv7() {
        return lv7;
    }

    public void setLv7(String lv7) {
        this.lv7 = lv7;
    }

    public String getLv10() {
        return lv10;
    }

    public void setLv10(String lv10) {
        this.lv10 = lv10;
    }

    public String getLv12() {
        return lv12;
    }

    public void setLv12(String lv12) {
        this.lv12 = lv12;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getMoveVelocity() {
        return moveVelocity;
    }

    public void setMoveVelocity(String moveVelocity) {
        this.moveVelocity = moveVelocity;
    }

    public List<TyphoonItemForecastInfo> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<TyphoonItemForecastInfo> forecasts) {
        this.forecasts = forecasts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
   			this.distance = getSimi(Double.parseDouble(xianzaijindu),Double.parseDouble(xianzaiweidu),120.5,28.8);
   		}
		
	}
    
}
