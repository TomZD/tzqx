package cn.movinginfo.tztf.sev.domain;
import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sev_t_led")
public class Led extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //部署单位
    @Column(name = "name")
    private String name;

    //管理员
    @Column(name = "linker")
    private String linker;

    //电话
    @Column(name = "phone")
    private String phone;

    //区域
    @Column(name = "area")
    private String area;

    //地址
    @Column(name = "address")
    private String address;

    //经度
    @Column(name = "lon")
    private Double  lon;

    //纬度
    @Column(name = "lat")
    private Double lat;
    
    //类型
    @Column(name = "type")
    private String type;
    
    //控制方式
    @Column(name = "control")
    private String control;
    
    //设备编号
    @Column(name = "equipmentCode")
    private String equipmentCode;
    
    //网卡编号
    @Column(name = "cardCode")
    private String cardCode;

    //是否安装：1是；0否
    @Column(name = "is_install")
    private Integer isInstall;

    //连接状态：1未连接，2已链接，3异常
    @Dict(key = DictKeys.CONNECT_STATUS)
    @Column(name = "connect_status")
    private Integer connectStatus;
    
    //LED类型
    @Dict(key = DictKeys.LED_TYPE)
    @Column(name = "led_type")
    private String ledType;
    
	public String getLedType() {
		return ledType;
	}

	public void setLedType(String ledType) {
		this.ledType = ledType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public Integer getIsInstall() {
		return isInstall;
	}

	public void setIsInstall(Integer isInstall) {
		this.isInstall = isInstall;
	}

	public Integer getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(Integer connectStatus) {
		this.connectStatus = connectStatus;
	}

	

	
    
    

} 
