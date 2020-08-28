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
@Table(name = "bj_alertrule")
public class Alertrule extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "alert_level")
    private Integer alertLevel;

    //
    @Column(name = "rule_expr")
    private String ruleExpr;

    //
    @Column(name = "text_format")
    private String textFormat;

    //
    @Column(name = "is_forecast")
    private Integer isForecast;

    //
    @Column(name = "is_continued")
    private Integer isContinued;

    //
    @Column(name = "element_ids")
    private String elementIds;

    //
    @Column(name = "and_rule")
    private Integer andRule;
    
    @Transient
    private String elementName;
    
    @Column(name="logic")
    private String logic;
    
    @Column(name="threshold_value")
    private String thresholdValue;
    
    @Column(name="relative")
    private String relative;
    
    @Column(name="type")
    private String type;
    

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public String getRelative() {
		return relative;
	}

	public void setRelative(String relative) {
		this.relative = relative;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

    public void setAlertLevel(Integer alertLevel){
        this.alertLevel = alertLevel;
    }

    public Integer getAlertLevel(){
        return alertLevel;
    }

    public void setRuleExpr(String ruleExpr){
        this.ruleExpr = ruleExpr;
    }

    public String getRuleExpr(){
        return ruleExpr;
    }

    public void setTextFormat(String textFormat){
        this.textFormat = textFormat;
    }

    public String getTextFormat(){
        return textFormat;
    }

    public void setIsForecast(Integer isForecast){
        this.isForecast = isForecast;
    }

    public Integer getIsForecast(){
        return isForecast;
    }

    public void setIsContinued(Integer isContinued){
        this.isContinued = isContinued;
    }

    public Integer getIsContinued(){
        return isContinued;
    }

    public void setElementIds(String elementIds){
        this.elementIds = elementIds;
    }

    public String getElementIds(){
        return elementIds;
    }

    public void setAndRule(Integer andRule){
        this.andRule = andRule;
    }

    public Integer getAndRule(){
        return andRule;
    }

	@Override
	public String toString() {
		return "Alertrule [alertLevel=" + alertLevel + ", ruleExpr=" + ruleExpr
				+ ", textFormat=" + textFormat + ", isForecast=" + isForecast + ", isContinued=" + isContinued
				+ ", elementIds=" + elementIds + ", andRule=" + andRule + "]";
	}
    
    
}
