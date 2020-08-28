package cn.movinginfo.tztf.sen.domain;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.movinginfo.tztf.common.domain.BaseDomain;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "sen_t_danger_alarm")
public class DangerAlarm extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "point_id")
    private Long pointId;
    
    @Column(name = "area_id")
    private Long areaId;

    //
    @Column(name = "value")
    private String value;

    //
    @Column(name = "rule_expr")
    private String ruleExpr;
    
    @Column(name="level")
    private Integer level;
    
    @Column(name="is_forecast")
    private String isForecast;

    //
    @Column(name = "alarm_time")
    private java.util.Date alarmTime;
    
    @Column(name="danger_type_id")
    private Long dangerTypeId;

    @Column(name="elementinfo_ids")
    private String elementinfoIds;

    //区域名称
    @Transient
    private String areaName;

    @Transient
    private String color;

    @Transient
    private String color_code;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getIsForecast() {
		return isForecast;
	}

	public void setIsForecast(String isForecast) {
		this.isForecast = isForecast;
	}

	public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getElementinfoIds() {
		return elementinfoIds;
	}

	public void setElementinfoIds(String elementinfoIds) {
		this.elementinfoIds = elementinfoIds;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getDangerTypeId() {
		return dangerTypeId;
	}

	public void setDangerTypeId(Long dangerTypeId) {
		this.dangerTypeId = dangerTypeId;
	}

	public void setPointId(Long pointId){
        this.pointId = pointId;
    }

    public Long getPointId(){
        return pointId;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public void setRuleExpr(String ruleExpr){
        this.ruleExpr = ruleExpr;
    }

    public String getRuleExpr(){
        return ruleExpr;
    }

    public void setAlarmTime(java.util.Date alarmTime){
        this.alarmTime = alarmTime;
    }

    public java.util.Date getAlarmTime(){
        return alarmTime;
    }
}
