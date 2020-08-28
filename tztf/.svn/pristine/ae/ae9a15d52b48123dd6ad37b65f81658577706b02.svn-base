package cn.movinginfo.tztf.sys.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;


/**
 * @description:
 * @author: autoCode
 * @history:
 */
@Table(name="sys_t_department")
public class Depart extends BaseDomain{
    private static final long serialVersionUID = 1L;

    @Transient
    private ReleaseChannel releaseChannel;


    @Column(name="NAME")
    private String name;  //部门名称

    @Column(name="PHONE")
    private String phone;//电话

    @Column(name="FAX")
    private String fax;//传真

    @Column(name="LINKER")
    private String linker; //

    @Column(name="ADDRESS")
    private String address; //地址

    @Column(name="CODE")
    private String code;//部门缩写

    // 2016-08-23 # 701 # by 朱潜 - 新增一个NationalCode属性，存储国家编码值，生成国突平台xml文件使用
    @Column(name="national_unit_code")
    private String nationalUnitCode;//国家单位编码

    @Column(name="channel_id")
    private Long channelId;//发布渠道ID

    @Column(name="token")
    private String token;
    @Column(name="town_id")
    private String townId;

    
    @Transient
    private String townName;

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLinker() {
        return linker;
    }

    public void setLinker(String linker) {
        this.linker = linker;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNationalUnitCode() {
        return nationalUnitCode;
    }

    public void setNationalUnitCode(String nationalUnitCode) {
        this.nationalUnitCode = nationalUnitCode;
    }

    // 2017-8-25，zhangd - 增加"部门类型字段"，用于登录时区分
    // 部门类型
    @Dict(key = DictKeys.DEPT_TYPE)
    @Column(name="dept_type")
    private String deptType;
    
    @Transient
    private String typeName;

    @Dict(key = DictKeys.AREA_ID)
    @Column(name="area_id")
    private String areaId;
    
    @Transient List<Area> areaList;
    

    public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public String getDeptType() {
        return deptType;
    }


    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Override
    public String toString() {
        return "Depart [name=" + name + ", phone=" + phone + ", fax=" + fax
                + ", linker=" + linker + ", address=" + address + ", code="
                + code + ", nationalUnitCode=" + nationalUnitCode
                + ", deptType=" + deptType + ",areaId="+areaId+"]";
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    public ReleaseChannel getReleaseChannel() {
        return releaseChannel;
    }

    public void setReleaseChannel(ReleaseChannel releaseChannel) {
        this.releaseChannel = releaseChannel;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }


}
