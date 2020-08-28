package cn.movinginfo.tztf.sen.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;
@Table(name = "sen_t_thunder")
public class Thunder extends BaseDomain{
	 private static final long serialVersionUID = 1L;
	
	 
	 	@Column(name="num")
		private String num;
		
		@Column(name="time")
		private Date time;
		
		@Column(name="lat")
		private String lat;
		
		@Column(name="lon")
		private String lon;
		
		@Column(name="power")
		private String power;
		
		@Column(name="steep")
		private String steep;
		
		@Column(name="error")
		private String error;
		
		@Column(name="model")
		private String model;
		
		@Column(name="province")
		private String province;
		
		@Column(name= "city")
		private String city;
		
		@Column(name="county")
		private String county;
		
		@Transient
		private String type;//正电为1，负电为-1
		
		@Transient
		private String date;
		
		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Thunder() {
			super();
		}

		public Thunder(String num, Date time, String lat, String lon, String power, String steep, String error,
				String model, String province, String city, String county) {
			super();
			this.num = num;
			this.time = time;
			this.lat = lat;
			this.lon = lon;
			this.power = power;
			this.steep = steep;
			this.error = error;
			this.model = model;
			this.province = province;
			this.city = city;
			this.county = county;
		}

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
		}

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

		public String getLon() {
			return lon;
		}

		public void setLon(String lon) {
			this.lon = lon;
		}

		public String getPower() {
			return power;
		}

		public void setPower(String power) {
			this.power = power;
		}

		public String getSteep() {
			return steep;
		}

		public void setSteep(String steep) {
			this.steep = steep;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCounty() {
			return county;
		}

		public void setCounty(String county) {
			this.county = county;
		}

		@Override
		public String toString() {
			return "Thunder [num=" + num + ", time=" + time + ", lat=" + lat + ", lon=" + lon + ", power=" + power
					+ ", steep=" + steep + ", error=" + error + ", model=" + model + ", province=" + province
					+ ", city=" + city + ", county=" + county + "]";
		}
		
		

}
