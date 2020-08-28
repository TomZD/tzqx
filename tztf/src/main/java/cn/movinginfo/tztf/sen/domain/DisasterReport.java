package cn.movinginfo.tztf.sen.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;
@Table(name="sen_t_disaster")
public class DisasterReport extends BaseDomain{
	private static final long serialVersionUID = 1L;
	
	 @Column(name = "disaster_description")	
	 private String disasterDescription;
	
	 @Column(name="disaster_type")
	 private String disasterType;

	 @Column(name="disaster_time")
	 private Date disasterTime;

	 @Column(name="disaster_location")
	 private String disasterLocation;

	 @Column(name="disaster_person")
	 private String disasterPerson;

	 @Column(name="status")
	 private String status;

	 @Column(name="lon")
	 private String lon;

	 @Column(name="lat")
	 private String lat;

	 @Column(name="reporting_time")
	 private Date reportingTime;

	 @Column(name="day_year")
	 private String dayYear;

	 @Column(name="images")
	 private String images;
	 
	 @Transient
	 private String disTime;
	 
	 @Transient
	 private String[] imageList;
	 

	public String getDisTime() {
		return disTime;
	}

	public void setDisTime(String disTime) {
		this.disTime = disTime;
	}

	public String[] getImageList() {
		return imageList;
	}

	public void setImageList(String[] imageList) {
		this.imageList = imageList;
	}

	public String getDisasterDescription() {
		return disasterDescription;
	}

	public void setDisasterDescription(String disasterDescription) {
		this.disasterDescription = disasterDescription;
	}

	public String getDisasterType() {
		return disasterType;
	}

	public void setDisasterType(String disasterType) {
		this.disasterType = disasterType;
	}

	public Date getDisasterTime() {
		return disasterTime;
	}

	public void setDisasterTime(Date disasterTime) {
		this.disasterTime = disasterTime;
	}

	public String getDisasterLocation() {
		return disasterLocation;
	}

	public void setDisasterLocation(String disasterLocation) {
		this.disasterLocation = disasterLocation;
	}

	public String getDisasterPerson() {
		return disasterPerson;
	}

	public void setDisasterPerson(String disasterPerson) {
		this.disasterPerson = disasterPerson;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
	}

	public String getDayYear() {
		return dayYear;
	}

	public void setDayYear(String dayYear) {
		this.dayYear = dayYear;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "DisasterReport [disasterDescription=" + disasterDescription + ", disasterType=" + disasterType
				+ ", disasterTime=" + disasterTime + ", disasterLocation=" + disasterLocation + ", disasterPerson="
				+ disasterPerson + ", status=" + status + ", lon=" + lon + ", lat=" + lat + ", reportingTime="
				+ reportingTime + ", dayYear=" + dayYear + ", images=" + images + "]";
	}
	 
	 
}
