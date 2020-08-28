package cn.movinginfo.tztf.sen.model;

/**
 * @Author: baolonghui
 * @Description
 * @Data Created in 16:32 2018/4/24
 * @Modified By:
 */
public class TyphoonItem {
    private String NO;
    private String Publisher;//发布者
    private String NameCn;
    private String Type;
    private String Time;

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    private String ForecastHours;
    private String TimeString;
    private String Longitude;
    private String Latitude;
    private String ForecastLongitude;
    private String ForecastLatitude;
    private String ForecastWindPower;
    private String ForecastAirPressure;
    private String AirPressure;
    private String WindVelocity;
    private String WindPower;
    private String Level7Distance;
    private String Level10Distance;
    private String MoveVelocity;

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getNameCn() {
        return NameCn;
    }

    public void setNameCn(String NameCn) {
        NameCn = NameCn;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getForecastHours() {
        return ForecastHours;
    }

    public void setForecastHours(String forecastHours) {
        ForecastHours = forecastHours;
    }

    public String getTimeString() {
        return TimeString;
    }

    public void setTimeString(String timeString) {
        TimeString = timeString;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getForecastLongitude() {
        return ForecastLongitude;
    }

    public void setForecastLongitude(String forecastLongitude) {
        ForecastLongitude = forecastLongitude;
    }

    public String getForecastLatitude() {
        return ForecastLatitude;
    }

    public void setForecastLatitude(String forecastLatitude) {
        ForecastLatitude = forecastLatitude;
    }

    public String getForecastWindPower() {
        return ForecastWindPower;
    }

    public void setForecastWindPower(String forecastWindPower) {
        ForecastWindPower = forecastWindPower;
    }

    public String getForecastAirPressure() {
        return ForecastAirPressure;
    }

    public void setForecastAirPressure(String forecastAirPressure) {
        ForecastAirPressure = forecastAirPressure;
    }

    public String getAirPressure() {
        return AirPressure;
    }

    public void setAirPressure(String airPressure) {
        AirPressure = airPressure;
    }

    public String getWindVelocity() {
        return WindVelocity;
    }

    public void setWindVelocity(String windVelocity) {
        WindVelocity = windVelocity;
    }

    public String getWindPower() {
        return WindPower;
    }

    public void setWindPower(String windPower) {
        WindPower = windPower;
    }

    public String getLevel7Distance() {
        return Level7Distance;
    }

    public void setLevel7Distance(String level7Distance) {
        Level7Distance = level7Distance;
    }

    public String getLevel10Distance() {
        return Level10Distance;
    }

    public void setLevel10Distance(String level10Distance) {
        Level10Distance = level10Distance;
    }

    public String getMoveVelocity() {
        return MoveVelocity;
    }

    public void setMoveVelocity(String moveVelocity) {
        MoveVelocity = moveVelocity;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    private String Direction;

}
