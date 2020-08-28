package cn.movinginfo.tztf.sen.domain;


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
@Table(name = "bj_elementinfo")
public class Elementinfo extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "element_id")
    private String elementId;

    //
    @Column(name = "element_name")
    private String elementName;

    //
    @Dict(key = DictKeys.ELEMENT_TYPE)
    @Column(name = "element_type")
    private String elementType;

    //
    @Column(name = "data_unit")
    private String dataUnit;

    //
    @Column(name = "is_dec")
    private String isDec;
    
    @Column(name="file_path")
    private String filePath;
    
    @Column(name="is_forecast")
    private String isForecast;
    
    @Column(name="forecast_hour")
    private Integer forecastHour;
    
    @Transient
    private String forecastName;
    
    @Transient
    private String decName;
    
    @Transient
    private String typeName;
    
    @Transient
    private List<cn.movinginfo.tztf.sys.domain.Dict> typeList;
    
    
    public Integer getForecastHour() {
		return forecastHour;
	}

	public void setForecastHour(Integer forecastHour) {
		this.forecastHour = forecastHour;
	}

	public String getForecastName() {
		return forecastName;
	}

	public void setForecastName(String forecastName) {
		this.forecastName = forecastName;
	}

	public String getIsForecast() {
		return isForecast;
	}

	public void setIsForecast(String isForecast) {
		this.isForecast = isForecast;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public List<cn.movinginfo.tztf.sys.domain.Dict> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<cn.movinginfo.tztf.sys.domain.Dict> typeList) {
		this.typeList = typeList;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDecName() {
		return decName;
	}

	public void setDecName(String decName) {
		this.decName = decName;
	}

	public void setElementId(String elementId){
        this.elementId = elementId;
    }

    public String getElementId(){
        return elementId;
    }

    public void setElementName(String elementName){
        this.elementName = elementName;
    }

    public String getElementName(){
        return elementName;
    }

    public void setElementType(String elementType){
        this.elementType = elementType;
    }

    public String getElementType(){
        return elementType;
    }

    public void setDataUnit(String dataUnit){
        this.dataUnit = dataUnit;
    }

    public String getDataUnit(){
        return dataUnit;
    }

    public void setIsDec(String isDec){
        this.isDec = isDec;
    }

    public String getIsDec(){
        return isDec;
    }
}
