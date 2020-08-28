package cn.movinginfo.tztf.sys.domain;
import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sys_t_feedback")
public class Feedback extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "alarm_id")
    private Integer alarmId;

    //
    @Column(name = "user_id")
    private Integer userId;

    //
    @Column(name = "message")
    private String message;

    //
    @Column(name = "add_time")
    private String addTime;

    public void setAlarmId(Integer alarmId){
        this.alarmId = alarmId;
    }

    public Integer getAlarmId(){
        return alarmId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setAddTime(String addTime){
        this.addTime = addTime;
    }

    public String getAddTime(){
        return addTime;
    }
}
