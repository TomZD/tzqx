package cn.movinginfo.tztf.sys.domain;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;

@Table(name ="sys_people")
public class People extends BaseDomain{
	private static final long serialVersionUID = 1L;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getIphone() {
		return iphone;
	}

	public void setIphone(String iphone) {
		this.iphone = iphone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMailbox() {
		return mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
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

	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public String getDecision_id() {
		return decision_id;
	}

	public void setDecision_id(String decision_id) {
		this.decision_id = decision_id;
	}

	@Column(name="first_name")//一级组织机构
	private String first_name;
	
	@Column(name="second_name")//二级组织机构
	private String second_name;
	
	@Column(name="user_name")//人员姓名
	private String user_name;
	
	@Column(name="position")//职务
    private String position;
	
    @Column(name="type")//类型
    private String type;
	
	@Column(name="sector")//所属部门
	private String sector;
	
	@Column(name="district_code")//行政区编码
	private String district_code;
	
	@Column(name="district_name")//行政区名字
	private String district_name;
	
	@Column(name="iphone")//手机
	private String iphone;
	
	@Column(name="phone")//电话
    private String phone;
	
    @Column(name="fax")//传真
    private String fax;
	
	@Column(name="mailbox")//邮箱
	private String mailbox;
	
	@Column(name="longitude")//经度
	private String longitude;
		
    @Column(name="latitude")//纬度
    private String latitude;
    
    @Dict(key =DictKeys.AREA_ID)
	@Column(name="area_id")//区域Id
    private String areaId;
    

	@Dict(key = DictKeys.DEPT_TYPE)
    @Column(name="dept_type")
    private String deptType;
	
	@Column(name="decision_id")
	private String decision_id;
    
    public People() {
		super();
	}
   
	public People(String first_name, String second_name, String user_name, String position, String type, String sector,
	        String district_code, String district_name, String iphone, String phone, String fax, String mailbox, 
	        String longitude, String latitude, String areaId, String deptType, String decision_id) {
		super();
		this.first_name= first_name;
		this.second_name = second_name;
		this.user_name = user_name;
		this.position = position;
		this.type = type;
		this.sector = sector;
		this.district_code= district_code;
		this.district_name = district_name;
		this.iphone = iphone;
		this.phone = phone;
		this.fax = fax;
		this.mailbox = mailbox;
		this.longitude = longitude;
		this.latitude = latitude;
		this.areaId = areaId;
		this.deptType = deptType;
		this.decision_id = decision_id;
		
	}


		
	}

	
	
	


	
	
