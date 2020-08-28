package cn.movinginfo.tztf.sen.model;

import java.math.BigDecimal;

/**
 * 乡镇精细化预报model
 * sly 2019年7月22日09:39:41
 */
public class TownForecast {
    //预报日期（月日）
    private String time;

    //风向风速1
    private String firstWind;

    //风向风速2
    private String secondWind;

    //降水
    private String precipitation;

    //降水2
    private String secondPrecipitation;

    //相对湿度
    private String relativeHumidity;

    //天气1
    private String firstWeather;

    //天气2
    private String secondWeather;

    //最低温度
    private BigDecimal temperatureMin;

    //最高温度
    private BigDecimal temperatureMax;

    //站点名称
    private String stationName;

    //站号
    private String code;

    //经度
    private Double lon;

    //纬度
    private Double lat;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getFirstWeather() {
        return firstWeather;
    }

    public void setFirstWeather(String firstWeather) {
        this.firstWeather = firstWeather;
    }

    public String getSecondWeather() {
        return secondWeather;
    }

    public void setSecondWeather(String secondWeather) {
        this.secondWeather = secondWeather;
    }

    public BigDecimal getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(BigDecimal temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public BigDecimal getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(BigDecimal temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getFirstWind() {
        return firstWind;
    }

    public void setFirstWind(String firstWind) {
        this.firstWind = firstWind;
    }

    public String getSecondWind() {
        return secondWind;
    }

    public void setSecondWind(String secondWind) {
        this.secondWind = secondWind;
    }

    public String getSecondPrecipitation() {
        return secondPrecipitation;
    }

    public void setSecondPrecipitation(String secondPrecipitation) {
        this.secondPrecipitation = secondPrecipitation;
    }
}
