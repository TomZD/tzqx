package cn.movinginfo.tztf.sen.model;

import java.io.Serializable;

public class TyphoonData implements Serializable {
    private Integer id;

    private String num;

    private String nowtime;

    private String lon;

    private String lat;

    private String moveSpeed;

    private String moveDirection;

    private String pressure;

    private String windLevel;

    private String grade;

    private String dataSource;

    private String foreTime;

    private String publisher;

    private String forePres;

    private String foreLon;

    private String foreLat;

    private String foreWindLevel;

    private String forePressure;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getNowtime() {
        return nowtime;
    }

    public void setNowtime(String nowtime) {
        this.nowtime = nowtime == null ? null : nowtime.trim();
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon == null ? null : lon.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(String moveSpeed) {
        this.moveSpeed = moveSpeed == null ? null : moveSpeed.trim();
    }

    public String getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(String moveDirection) {
        this.moveDirection = moveDirection == null ? null : moveDirection.trim();
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure == null ? null : pressure.trim();
    }

    public String getWindLevel() {
        return windLevel;
    }

    public void setWindLevel(String windLevel) {
        this.windLevel = windLevel == null ? null : windLevel.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public String getForeTime() {
        return foreTime;
    }

    public void setForeTime(String foreTime) {
        this.foreTime = foreTime == null ? null : foreTime.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getForePres() {
        return forePres;
    }

    public void setForePres(String forePres) {
        this.forePres = forePres == null ? null : forePres.trim();
    }

    public String getForeLon() {
        return foreLon;
    }

    public void setForeLon(String foreLon) {
        this.foreLon = foreLon == null ? null : foreLon.trim();
    }

    public String getForeLat() {
        return foreLat;
    }

    public void setForeLat(String foreLat) {
        this.foreLat = foreLat == null ? null : foreLat.trim();
    }

    public String getForeWindLevel() {
        return foreWindLevel;
    }

    public void setForeWindLevel(String foreWindLevel) {
        this.foreWindLevel = foreWindLevel == null ? null : foreWindLevel.trim();
    }

    public String getForePressure() {
        return forePressure;
    }

    public void setForePressure(String forePressure) {
        this.forePressure = forePressure == null ? null : forePressure.trim();
    }
}