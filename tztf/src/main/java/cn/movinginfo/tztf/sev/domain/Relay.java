package cn.movinginfo.tztf.sev.domain;
import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sev_t_relay")
public class Relay extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "alarm_id")
    private Integer alarmId;

    //
    @Column(name = "town_id")
    private Integer townId;

    //是否转发 0 未转发 1已转发
    @Column(name = "is_relay")
    private Integer isRelay;

    //是否反馈 0 未反馈 1 已反馈
    @Column(name = "is_feedback")
    private Integer isFeedback;

    //反馈信息
    @Column(name = "message")
    private String message;

    public void setAlarmId(Integer alarmId){
        this.alarmId = alarmId;
    }

    public Integer getAlarmId(){
        return alarmId;
    }

    public void setTownId(Integer townId){
        this.townId = townId;
    }

    public Integer getTownId(){
        return townId;
    }

    public void setIsRelay(Integer isRelay){
        this.isRelay = isRelay;
    }

    public Integer getIsRelay(){
        return isRelay;
    }

    public void setIsFeedback(Integer isFeedback){
        this.isFeedback = isFeedback;
    }

    public Integer getIsFeedback(){
        return isFeedback;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
