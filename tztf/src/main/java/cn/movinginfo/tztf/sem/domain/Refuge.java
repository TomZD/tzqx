package cn.movinginfo.tztf.sem.domain;

import java.io.Serializable;

public class Refuge implements Serializable {
    private Integer id;      // 序号
    
    private String name;	 // 名称

    private String address;  // 地址

    private String type;    // 类型

    private String longitude;  // 经度

    private String latitude;  // 纬度

    private String company; // 主管单位

    private String area;  // 行政区域

    private String rank;  // 级别

    private String secret;  // 密级

    private String accommodateMan;  // 可容纳人数

    private String chargeMan;  // 负责人

    private String officeTel;  // 负责人办公电话

    private String phone;  // 移动电话

    private String homeTel;  // 住宅电话

    private String fax;  // 传真

    private String mail;  // 邮箱

    private String linkman; // 联系人

    private String linkmanOfficeTel;  //联系人办公电话

    private String linkmanPhone;  // 联系人移动电话

    private String linkmanHomeTel;  // 联系人住宅电话
 
    private String linkmanFax;  // 联系人传真

    private String linkmanMail;  // 联系人邮件

    private String linkmanEmergency;  // 联系人应急通信

    private String userTime;  // 投入使用时间

    private String userYear;  // 使用年限

    private String aseismicStrength;  //抗震强度

    private String square; // 面积

    private String basicMessage;  // 基本情况
    
    private String dataType;

    public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    public String getAccommodateMan() {
        return accommodateMan;
    }

    public void setAccommodateMan(String accommodateMan) {
        this.accommodateMan = accommodateMan == null ? null : accommodateMan.trim();
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan == null ? null : chargeMan.trim();
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel == null ? null : officeTel.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel = homeTel == null ? null : homeTel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getLinkmanOfficeTel() {
        return linkmanOfficeTel;
    }

    public void setLinkmanOfficeTel(String linkmanOfficeTel) {
        this.linkmanOfficeTel = linkmanOfficeTel == null ? null : linkmanOfficeTel.trim();
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone == null ? null : linkmanPhone.trim();
    }

    public String getLinkmanHomeTel() {
        return linkmanHomeTel;
    }

    public void setLinkmanHomeTel(String linkmanHomeTel) {
        this.linkmanHomeTel = linkmanHomeTel == null ? null : linkmanHomeTel.trim();
    }

    public String getLinkmanFax() {
        return linkmanFax;
    }

    public void setLinkmanFax(String linkmanFax) {
        this.linkmanFax = linkmanFax == null ? null : linkmanFax.trim();
    }

    public String getLinkmanMail() {
        return linkmanMail;
    }

    public void setLinkmanMail(String linkmanMail) {
        this.linkmanMail = linkmanMail == null ? null : linkmanMail.trim();
    }

    public String getLinkmanEmergency() {
        return linkmanEmergency;
    }

    public void setLinkmanEmergency(String linkmanEmergency) {
        this.linkmanEmergency = linkmanEmergency == null ? null : linkmanEmergency.trim();
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime == null ? null : userTime.trim();
    }

    public String getUserYear() {
        return userYear;
    }

    public void setUserYear(String userYear) {
        this.userYear = userYear == null ? null : userYear.trim();
    }

    public String getAseismicStrength() {
        return aseismicStrength;
    }

    public void setAseismicStrength(String aseismicStrength) {
        this.aseismicStrength = aseismicStrength == null ? null : aseismicStrength.trim();
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square == null ? null : square.trim();
    }

    public String getBasicMessage() {
        return basicMessage;
    }

    public void setBasicMessage(String basicMessage) {
        this.basicMessage = basicMessage == null ? null : basicMessage.trim();
    }
}