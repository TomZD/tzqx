package cn.movinginfo.tztf.sen.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sen_t_stinf")
public class Stinf{

	@Column(name = "id")
	private Long id;
    //
    @Column(name = "type")
    private String type;

    //
    @Column(name = "city")
    private String city;

    //
    @Column(name = "county")
    private String county;

    //站号
    @Column(name = "stcd")
    private String stcd;

    //站名
    @Column(name = "stnm")
    private String stnm;

    //
    @Column(name = "longitude")
    private String longitude;

    //
    @Column(name = "latitude")
    private String latitude;

    //
    @Column(name = "data_source")
    private String dataSource;
    
    @Transient
    private BigDecimal dtrn;

    @Transient
    private String name;

    @Transient
    private String lon;

    @Transient
    private String lat;

    @Transient
    private BigDecimal value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getDtrn() {
		return dtrn;
	}

	public void setDtrn(BigDecimal dtrn) {
		this.dtrn = dtrn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }

    public void setCounty(String county){
        this.county = county;
    }

    public String getCounty(){
        return county;
    }

    public void setStcd(String stcd){
        this.stcd = stcd;
    }

    public String getStcd(){
        return stcd;
    }

    public void setStnm(String stnm){
        this.stnm = stnm;
    }

    public String getStnm(){
        return stnm;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getLongitude(){
        return longitude;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public String getLatitude(){
        return latitude;
    }

    public void setDataSource(String dataSource){
        this.dataSource = dataSource;
    }

    public String getDataSource(){
        return dataSource;
    }

	@Override
	public String toString() {
		return "Stinf [type=" + type + ", city=" + city + ", county=" + county + ", stcd=" + stcd + ", stnm=" + stnm
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", dataSource=" + dataSource + "]";
	}
    
    
}
