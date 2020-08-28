package cn.movinginfo.tztf.sen.domain;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sen_t_danger_point")
public class DangerPoint extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "name")
    private String name;

    //位置
    @Column(name = "location")
    private String location;

    //
    @Column(name = "longitude")
    private String longitude;

    //
    @Column(name = "latitude")
    private String latitude;

    //单位
    @Column(name = "depart")
    private String depart;

    //联系人
    @Column(name = "linker")
    private String linker;
    
    //电话
    @Column(name = "phone")
    private String phone;
    
    //职务
    @Column(name = "job")
    private String job;
    
    //海拔
    @Column(name = "elevation")
    private String elevation;
    
    //影响村镇
    @Column(name = "affect_town")
    private String affectTown;
    
    //
    @Column(name = "relative")
    private String relative;
    //0为未超阈值，1为超阈值
    @Column(name = "overThreshold")
    private Integer overThreshold;

    //历史灾情
    @Column(name = "historical_disaster")
    private String historicalDisaster;

    //
    @Column(name = "alertrule_id")
    private String alertruleId;
    
    @Column(name="alertruleTwo_id")
    private String alertruleTwoId;
    
    @Column(name="alertruleThree_id")
    private String alertruleThreeId;
    
    @Transient
    private String meteorological;

    //
    @Column(name = "danger_type_id")
    private Long dangerTypeId;
    
    @Transient
    private String dangerTypeName;

	@Transient
	private String elementName;



	//编号
    @Column(name="code")
    private String code;
    
    @Column(name="province")
    private String province;
    
    @Column(name="county")
    private String county;
    
    @Column(name="city")
    private String city;
    
    @Column(name="town")
    private String town;
    
    //关联站点
    @Column(name="rel_station")
    private String relStation;
    
    //流域面积
    @Column(name="catchment_area")
    private String catchmentArea;
    
    //流域人口
    @Column(name="watershed_population")
    private String watershedPopulation;
    
    //河流长度
    @Column(name = "river_length")
    private String riverLength;
    
    //发生时间
    @Column(name="occurrence_time")
    private String occurrenceTime;
    
    //受困人口
    @Column(name="trap_people")
    private String trapPeople;
    
    //死亡人口
    @Column(name="death_people")
    private String deathPeople;
    
    //潜在损失
    @Column(name = "potential_damage")
    private String potentialDamage;
    
    //调查时间
    @Column(name = "investigation_time")
    private String investigationTime;
    
    //是否到场
    @Column(name="is_show")
    private String isShow;
    
    //灾害程度
    @Column(name = "disaster_degree")
    private String disasterDegree;
    
    //描述
    @Column(name="bewrite")
    private String bewrite;
    
    //备注
    @Column(name="remark")
    private String remark;
    
    //完工时间
    @Column(name="finish_time")
    private String finishTime;
    
    //工程造价
    @Column(name="project_cost")
    private String projectCost;
    
    //建筑类型
    @Column(name="building_type")
    private String buildingType;
    
    //建筑面积
    @Column(name="covered_area")
    private String coveredArea;
    
    //灾害类型
    @Column(name="type")
    private String type;
    
    //职称
    @Column(name = "position")
    private String position;
    
    //记录年份
    @Column(name="record_year")
    private String recordYear;
    
    @Transient
    private String threshold;
    
    @Transient
    private List<DangerType> dangerTypeList;
    
    @Transient
    private String icon;
    
    @Transient
    private String dataType;
    
    @Transient
    private String logic;
    
    @Transient 
    private String thresholdValue;
    
    @Transient
    private String alertName;
    
    @Transient
    private String alertNameTwo;
    
    @Transient
    private String alertNameThree;
    
    @Transient
    private String meteorologicalId;
    
    @Transient
    private String stationIiiii;
    
    @Transient
    private String alertLevel;
    //降水
    @Transient
    private String rain;
    //降水值
    @Transient 
    private String rainValue;
    //水位
    @Transient
    private String waterLevel;
    //水位值
    @Transient
    private String waterLevelValue;
    //土壤
    @Transient
    private String ground;
    //土壤值
    @Transient
    private String groundValue;
    
    
	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getProjectCost() {
		return projectCost;
	}

	public void setProjectCost(String projectCost) {
		this.projectCost = projectCost;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getCoveredArea() {
		return coveredArea;
	}

	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRecordYear() {
		return recordYear;
	}

	public void setRecordYear(String recordYear) {
		this.recordYear = recordYear;
	}

	public String getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(String occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	public String getTrapPeople() {
		return trapPeople;
	}

	public void setTrapPeople(String trapPeople) {
		this.trapPeople = trapPeople;
	}

	public String getDeathPeople() {
		return deathPeople;
	}

	public void setDeathPeople(String deathPeople) {
		this.deathPeople = deathPeople;
	}

	public String getPotentialDamage() {
		return potentialDamage;
	}

	public void setPotentialDamage(String potentialDamage) {
		this.potentialDamage = potentialDamage;
	}

	public String getInvestigationTime() {
		return investigationTime;
	}

	public void setInvestigationTime(String investigationTime) {
		this.investigationTime = investigationTime;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getDisasterDegree() {
		return disasterDegree;
	}

	public void setDisasterDegree(String disasterDegree) {
		this.disasterDegree = disasterDegree;
	}

	

	public String getBewrite() {
		return bewrite;
	}

	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getCatchmentArea() {
		return catchmentArea;
	}

	public void setCatchmentArea(String catchmentArea) {
		this.catchmentArea = catchmentArea;
	}

	public String getWatershedPopulation() {
		return watershedPopulation;
	}

	public void setWatershedPopulation(String watershedPopulation) {
		this.watershedPopulation = watershedPopulation;
	}

	public String getRiverLength() {
		return riverLength;
	}

	public void setRiverLength(String riverLength) {
		this.riverLength = riverLength;
	}

	public String getRain() {
		return rain;
	}

	public void setRain(String rain) {
		this.rain = rain;
	}

	public String getRainValue() {
		return rainValue;
	}

	public void setRainValue(String rainValue) {
		this.rainValue = rainValue;
	}

	public String getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(String waterLevel) {
		this.waterLevel = waterLevel;
	}

	public String getWaterLevelValue() {
		return waterLevelValue;
	}

	public void setWaterLevelValue(String waterLevelValue) {
		this.waterLevelValue = waterLevelValue;
	}

	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	public String getGroundValue() {
		return groundValue;
	}

	public void setGroundValue(String groundValue) {
		this.groundValue = groundValue;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAffectTown() {
		return affectTown;
	}

	public void setAffectTown(String affectTown) {
		this.affectTown = affectTown;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAlertNameThree() {
		return alertNameThree;
	}

	public void setAlertNameThree(String alertNameThree) {
		this.alertNameThree = alertNameThree;
	}

	public String getAlertNameTwo() {
		return alertNameTwo;
	}

	public void setAlertNameTwo(String alertNameTwo) {
		this.alertNameTwo = alertNameTwo;
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

	public String getAlertLevel() {
		return alertLevel;
	}

	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}

	public String getStationIiiii() {
		return stationIiiii;
	}

	public void setStationIiiii(String stationIiiii) {
		this.stationIiiii = stationIiiii;
	}

	public String getMeteorologicalId() {
		return meteorologicalId;
	}

	public void setMeteorologicalId(String meteorologicalId) {
		this.meteorologicalId = meteorologicalId;
	}

	public String getRelStation() {
		return relStation;
	}

	public void setRelStation(String relStation) {
		this.relStation = relStation;
	}

	public String getAlertName() {
		return alertName;
	}

	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public Integer getOverThreshold() {
		return overThreshold;
	}

	public void setOverThreshold(Integer overThreshold) {
		this.overThreshold = overThreshold;
	}

	public String getRelative() {
		return relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<DangerType> getDangerTypeList() {
		return dangerTypeList;
	}

	public void setDangerTypeList(List<DangerType> dangerTypeList) {
		this.dangerTypeList = dangerTypeList;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getMeteorological() {
		return meteorological;
	}

	public void setMeteorological(String meteorological) {
		this.meteorological = meteorological;
	}

	public String getDangerTypeName() {
		return dangerTypeName;
	}

	public void setDangerTypeName(String dangerTypeName) {
		this.dangerTypeName = dangerTypeName;
	}

	public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return location;
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

    public void setDepart(String depart){
        this.depart = depart;
    }

    public String getDepart(){
        return depart;
    }

    public void setLinker(String linker){
        this.linker = linker;
    }

    public String getLinker(){
        return linker;
    }

    public void setHistoricalDisaster(String historicalDisaster){
        this.historicalDisaster = historicalDisaster;
    }

    public String getHistoricalDisaster(){
        return historicalDisaster;
    }

    public void setDangerTypeId(Long dangerTypeId){
        this.dangerTypeId = dangerTypeId;
    }

    public Long getDangerTypeId(){
        return dangerTypeId;
    }

	public String getAlertruleId() {
		return alertruleId;
	}

	public void setAlertruleId(String alertruleId) {
		this.alertruleId = alertruleId;
	}

	@Override
	public String toString() {
		return "DangerPoint [name=" + name + ", location=" + location + ", longitude=" + longitude + ", latitude="
				+ latitude + ", depart=" + depart + ", linker=" + linker + ", phone=" + phone + ", job=" + job
				+ ", elevation=" + elevation + ", affectTown=" + affectTown + ", relative=" + relative
				+ ", overThreshold=" + overThreshold + ", historicalDisaster=" + historicalDisaster + ", alertruleId="
				+ alertruleId + ", alertruleTwoId=" + alertruleTwoId + ", alertruleThreeId=" + alertruleThreeId
				+ ", meteorological=" + meteorological + ", dangerTypeId=" + dangerTypeId + ", dangerTypeName="
				+ dangerTypeName + ", code=" + code + ", province=" + province + ", county=" + county + ", city=" + city
				+ ", town=" + town + ", relStation=" + relStation + ", threshold=" + threshold + ", dangerTypeList="
				+ dangerTypeList + ", icon=" + icon + ", dataType=" + dataType + ", logic=" + logic
				+ ", thresholdValue=" + thresholdValue + ", alertName=" + alertName + ", alertNameTwo=" + alertNameTwo
				+ ", alertNameThree=" + alertNameThree + ", meteorologicalId=" + meteorologicalId + ", stationIiiii="
				+ stationIiiii + ", alertLevel=" + alertLevel + "]";
	}

	
    
}
