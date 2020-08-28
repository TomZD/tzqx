package cn.movinginfo.tztf.sen.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;

/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Table(name = "bj_areaalarm")
public class AreaAlarm extends BaseDomain {

	private static final long serialVersionUID = 1L;

	//
	@Column(name = "city")
	private String city;

	//
	@Column(name = "country")
	private String country;

	//
	@Column(name = "town")
	private String town;

	//
	@Column(name = "rel_station")
	private String relStation;

	//
	@Column(name = "province")
	private String province;

	//
	@Column(name = "alertrule_id")
	private String alertruleId;

	@Column(name = "alertruleTwo_id")
	private String alertruleTwoId;

	@Column(name = "alertruleThree_id")
	private String alertruleThreeId;

	@Column(name = "coords")
	private String coords;
	
	@Transient
	private String coordString;

	@Transient
	private String iiiii;

	@Transient
	private String alertName;

	@Transient
	private String alertNameTwo;

	@Transient
	private String alertNameThree;

	@Transient
	private String alertLevel;
	
	@Transient
    private String logic;
    
    @Transient 
    private String thresholdValue;
    
    @Transient
    private String meteorologicalId;
    
    @Transient
    private String relative;
    
	
	public String getRelative() {
		return relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public String getMeteorologicalId() {
		return meteorologicalId;
	}

	public void setMeteorologicalId(String meteorologicalId) {
		this.meteorologicalId = meteorologicalId;
	}

	public String getCoordString() {
		return coordString;
	}

	public void setCoordString(String coordString) {
		this.coordString = coordString;
	}

	public String getAlertruleId() {
		return alertruleId;
	}

	public void setAlertruleId(String alertruleId) {
		this.alertruleId = alertruleId;
	}

	public String getAlertruleTwoId() {
		return alertruleTwoId;
	}

	public void setAlertruleTwoId(String alertruleTwoId) {
		this.alertruleTwoId = alertruleTwoId;
	}

	public String getAlertruleThreeId() {
		return alertruleThreeId;
	}

	public void setAlertruleThreeId(String alertruleThreeId) {
		this.alertruleThreeId = alertruleThreeId;
	}

	public String getAlertNameTwo() {
		return alertNameTwo;
	}

	public void setAlertNameTwo(String alertNameTwo) {
		this.alertNameTwo = alertNameTwo;
	}

	public String getAlertNameThree() {
		return alertNameThree;
	}

	public void setAlertNameThree(String alertNameThree) {
		this.alertNameThree = alertNameThree;
	}

	public String getCoords() {
		return coords;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	public String getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getIiiii() {
		return iiiii;
	}

	public void setIiiii(String iiiii) {
		this.iiiii = iiiii;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getTown() {
		return town;
	}

	public void setRelStation(String relStation) {
		this.relStation = relStation;
	}

	public String getRelStation() {
		return relStation;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return province;
	}
}
