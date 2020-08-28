package cn.movinginfo.tztf.sen.domain;

public class Station {
	private String name;
	
	private String lon;
	
	private String lat;
	
	private String code;

	//风速
	private String value;
	
	private String path;

	//风力等级
	private int rank;

	//是否超出6级
	private String isRed;


	private boolean isAlarm;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getIsRed() {
		return isRed;
	}

	public void setIsRed(String isRed) {
		this.isRed = isRed;
	}

	public boolean isAlarm() {
		return isAlarm;
	}

	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}

	@Override
	public String toString() {
		return "Station [name=" + name + ", lon=" + lon + ", lat=" + lat + ", value=" + value + "]";
	}
	

}
