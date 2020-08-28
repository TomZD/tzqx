package cn.movinginfo.tztf.sev.domain;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.constants.DictKeys;
import cn.movinginfo.tztf.common.domain.BaseDomain;
import cn.movinginfo.tztf.sys.utils.json.Dict;

@Table(name ="sys_p_group")
public class PGroup extends BaseDomain {
    private static final long serialVersionUID = 1L;
    // 部门ID
    @Column(name = "dept_id")
    private Long deptId;

    // 发布编号
    @Column(name = "pub_no")
    private String pubNo;

    @Column(name = "kettle")
    private String kettle;

    @Column(name = "alarm_id")
    private Long alarmId;

    //是否转发0：待转发 1：转发
    @Column(name = "is_relay")
    private Integer isRelay;


    @Column(name = "sms_group")
    private String smsGroup;

    @Column(name = "depart_fax")
    private String departFax;



    @Dict(key = DictKeys.AREA_ID)
    @Column(name="area_id")
    private String areaId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getPubNo() {
        return pubNo;
    }

    public void setPubNo(String pubNo) {
        this.pubNo = pubNo;
    }

    public String getKettle() {
        return kettle;
    }

    public void setKettle(String kettle) {
        this.kettle = kettle;
    }

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getIsRelay() {
        return isRelay;
    }

    public void setIsRelay(Integer isRelay) {
        this.isRelay = isRelay;
    }

    public String getSmsGroup() {
        return smsGroup;
    }

    public void setSmsGroup(String smsGroup) {
        this.smsGroup = smsGroup;
    }

    public String getDepartFax() {
        return departFax;
    }

    public void setDepartFax(String departFax) {
        this.departFax = departFax;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    //渠道id
    @Transient
    private String channelId;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

}
