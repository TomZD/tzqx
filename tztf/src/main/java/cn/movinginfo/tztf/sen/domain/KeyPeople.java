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
@Table(name = "sen_t_key_people")
public class KeyPeople extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //名称
    @Column(name = "name")
    private String name;

    //单位职位
    @Column(name = "job")
    private String job;

    //地址
    @Column(name = "address")
    private String address;

    //电话
    @Column(name = "phone")
    private String phone;

    //类型
    @Column(name = "person_type_id")
    private Long personTypeId;
    
    //单位
    @Column(name = "depart")
    private String depart;
    
    //经度
    @Column(name = "longitude")
    private String longitude;
    
    //纬度
    @Column(name = "latitude")
    private String latitude;
    
    //职称
    @Column(name = "position")
    private String position;
    
    //常驻地址
    @Column(name = "home_address")
    private String homeAddress;
    
    //省
    @Column(name="province")
    private String province;
    
    //市
    @Column(name="city")
    private String city;
    
    //县
    @Column(name="county")
    private String county;
    
    //街道
    @Column(name="town")
    private String town;
    
    //区域编号
    @Column(name="area_code")
    private String areaCode;
    
    //单位代码
    @Column(name="depart_code")
    private String departCode;
    
    //领域
    @Column(name ="territory")
    private String territory;
    
    //居委会
    @Column(name="committee")
    private String committee;
    
    //类别
    @Column(name="type")
    private String type;
    
    //行政编码
    @Column(name = "administration_code")
    private String administrationCode;
    
    //网络名称
    @Column(name="network_name")
    private String networkName;
    
    @Transient
    private String personType;
    
    @Transient
    private String departmentName;
    
    @Transient
    private List<PersonType> personTypeList;
    
    @Transient
    private List<DefenseDepartment> defenseDepartmentList;
    
    @Transient
    private String icon;
    
    @Transient
    private String dataType;
    
    
	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getAdministrationCode() {
		return administrationCode;
	}

	public void setAdministrationCode(String administrationCode) {
		this.administrationCode = administrationCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCommittee() {
		return committee;
	}

	public void setCommittee(String committee) {
		this.committee = committee;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public List<PersonType> getPersonTypeList() {
		return personTypeList;
	}

	public void setPersonTypeList(List<PersonType> personTypeList) {
		this.personTypeList = personTypeList;
	}

	public List<DefenseDepartment> getDefenseDepartmentList() {
		return defenseDepartmentList;
	}

	public void setDefenseDepartmentList(List<DefenseDepartment> defenseDepartmentList) {
		this.defenseDepartmentList = defenseDepartmentList;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setJob(String job){
        this.job = job;
    }

    public String getJob(){
        return job;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

	public Long getPersonTypeId() {
		return personTypeId;
	}

	public void setPersonTypeId(Long personTypeId) {
		this.personTypeId = personTypeId;
	}

    
}
