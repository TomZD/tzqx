package cn.movinginfo.tztf.sys.domain;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;

/**
 * 乡镇预警转发的实体类
 */

@Table(name = "sys_t_forward")
public class ForwardAlarm extends BaseDomain {
    private static final long serialVersionUID = 1L;
    //预警的id
    @Column(name = "alarm_id")
    private Long alarmId ;

    //登录用户的id
    @Column(name = "user_id")
    private Long userId ;

    //短信组的id
    @Column(name = "sms_group")
    private String smsGroup ;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSmsGroup() {
        return smsGroup;
    }

    public void setSmsGroup(String smsGroup) {
        this.smsGroup = smsGroup;
    }
}
