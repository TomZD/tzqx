package cn.movinginfo.tztf.sys.domain;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;

import java.util.List;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sys_emergency_information")
public class EmergencyInformation extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //名称
    @Column(name = "name")
    private String name;

    //单位
    @Column(name = "depart")
    private String depart;

    //单位职位
    @Column(name = "job")
    private String job;

    //地址
    @Column(name = "address")
    private String address;

    //电话
    @Column(name = "phone")
    private String phone;

    //省
    @Column(name = "province")
    private String province;

    //市
    @Column(name = "city")
    private String city;

    //县
    @Column(name = "county")
    private String county;

    //街道
    @Column(name = "town")
    private String town;


    //群组id
    @Column(name = "group_id")
    private Long groupId;


    @Column(name = "depart_id")
    private Long departid;


    @Transient
    private String groupName;

    @Transient
    private String departName;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    @Transient
    private List<Depart> departList;
    @Transient
    private List<PeopleGroup> peopleGroupList;

    public List<Depart> getDepartList() {
        return departList;
    }

    public void setDepartList(List<Depart> departList) {
        this.departList = departList;
    }

    public Long getGroupId() {
		return groupId;
	}

    public List<PeopleGroup> getPeopleGroupList() {
        return peopleGroupList;
    }

    public void setPeopleGroupList(List<PeopleGroup> peopleGroupList) {
        this.peopleGroupList = peopleGroupList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getDepartid() {
        return departid;
    }

    public void setDepartid(Long departid) {
        this.departid = departid;
    }

    public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDepart(String depart){
        this.depart = depart;
    }

    public String getDepart(){
        return depart;
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

    public void setProvince(String province){
        this.province = province;
    }

    public String getProvince(){
        return province;
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

    public void setTown(String town){
        this.town = town;
    }

    public String getTown(){
        return town;
    }
}
