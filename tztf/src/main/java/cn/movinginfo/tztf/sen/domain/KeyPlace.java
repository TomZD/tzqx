package cn.movinginfo.tztf.sen.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;

@Table(name="sen_t_key_place")
public class KeyPlace extends BaseDomain{
	private static final long serialVersionUID = 1L;
	
	//名称
	@Column(name="name")
	private String name;
	
	//地址
	@Column(name="location")
	private String location;
	
	//经度
	@Column(name="longitude")
	private String longitude;
	
	//纬度
	@Column(name="latitude")
	private String latitude;
	
	//单位
	@Column(name="depart")
	private String depart;
	
	//联系人
	@Column(name="peoson")
	private String peoson;
	
	//电话
	@Column(name="phone")
	private String phone;
	
	//类型id
	@Column(name="type_id")
	private Long typeId;
	
	//职务
	@Column(name="position")
	private String position;

	//市
	@Column(name = "city")
	private String city;
	
	//县
	@Column(name="county")
	private String county;
	
	//街道
	@Column(name="town")
	private String town;
	
	//医院等级
	@Column(name="hospital_level")
	private String hospitalLevel;
	
	//医院床位
	@Column(name="hospital_bed")
	private String hospitalBed;
	
	//类别
	@Column(name="type")
	private String type;
	
	//学校规模
	@Column(name="school_size")
	private String schoolSize;
	
	//景点级别
	@Column(name="rank")
	private String rank;
	
	//总库容
	@Column(name="total_reservoir_capacity")
	private String totalReservoirCapacity;
	
	//单位代码
	@Column(name="depart_code")
	private String departCode;
	
	//经营范围
	@Column(name = "scope_business")
	private String scopeBusiness;
	
	//河流名称
	@Column(name = "rivers_name")
	private String riversName;
	
	//终点经度
	@Column(name="end_longitude")
	private String endLongitude;
	
	//终点纬度
	@Column(name = "end_latitude")
	private String endLatitude;
	
	//堤坝长度
	@Column(name="dike_length")
	private String dikeLength;
	
	//堤坝高度
	@Column(name="dike_height")
	private String dikeHeight;
	
	//段名
	@Column(name="paragraph_name")
	private String paragraphName;
	
	//资料时间
	@Column(name ="data_time")
	private String dataTime;
	
	@Transient
	private String pointlist;
	
	@Transient
	private List<PlaceType> placeTypeList;
	
	@Transient
	private String icon;
	
	@Transient
	private String dataType;
	
	@Transient
	private String lon;
	
	@Transient
	private String lat;
	

	public String getPointlist() {
		return pointlist;
	}

	public void setPointlist(String pointlist) {
		this.pointlist = pointlist;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getParagraphName() {
		return paragraphName;
	}

	public void setParagraphName(String paragraphName) {
		this.paragraphName = paragraphName;
	}

	public String getRiversName() {
		return riversName;
	}

	public void setRiversName(String riversName) {
		this.riversName = riversName;
	}

	public String getEndLongitude() {
		return endLongitude;
	}

	public void setEndLongitude(String endLongitude) {
		this.endLongitude = endLongitude;
	}

	public String getEndLatitude() {
		return endLatitude;
	}

	public void setEndLatitude(String endLatitude) {
		this.endLatitude = endLatitude;
	}

	public String getDikeLength() {
		return dikeLength;
	}

	public void setDikeLength(String dikeLength) {
		this.dikeLength = dikeLength;
	}

	public String getDikeHeight() {
		return dikeHeight;
	}

	public void setDikeHeight(String dikeHeight) {
		this.dikeHeight = dikeHeight;
	}

	public String getScopeBusiness() {
		return scopeBusiness;
	}

	public void setScopeBusiness(String scopeBusiness) {
		this.scopeBusiness = scopeBusiness;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getTotalReservoirCapacity() {
		return totalReservoirCapacity;
	}

	public void setTotalReservoirCapacity(String totalReservoirCapacity) {
		this.totalReservoirCapacity = totalReservoirCapacity;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getSchoolSize() {
		return schoolSize;
	}

	public void setSchoolSize(String schoolSize) {
		this.schoolSize = schoolSize;
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getHospitalLevel() {
		return hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	public String getHospitalBed() {
		return hospitalBed;
	}

	public void setHospitalBed(String hospitalBed) {
		this.hospitalBed = hospitalBed;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public KeyPlace() {
		super();
	}

	public KeyPlace(String name, String location, String longitude, String latitude, String depart, String peoson,
			String phone, Long typeId) {
		super();
		this.name = name;
		this.location = location;
		this.longitude = longitude;
		this.latitude = latitude;
		this.depart = depart;
		this.peoson = peoson;
		this.phone = phone;
		this.typeId = typeId;
	}
	
	

	public List<PlaceType> getPlaceTypeList() {
		return placeTypeList;
	}

	public void setPlaceTypeList(List<PlaceType> placeTypeList) {
		this.placeTypeList = placeTypeList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getPeoson() {
		return peoson;
	}

	public void setPeoson(String peoson) {
		this.peoson = peoson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "KeyPlace [name=" + name + ", location=" + location + ", longitude=" + longitude + ", latitude="
				+ latitude + ", depart=" + depart + ", peoson=" + peoson + ", phone=" + phone + ", typeId=" + typeId + "]";
	}
	
	

}
