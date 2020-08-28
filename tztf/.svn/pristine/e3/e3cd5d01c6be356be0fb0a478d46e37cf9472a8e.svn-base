package cn.movinginfo.tztf.sen.domain;
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
@Table(name = "sen_t_point_dangerType")
public class PointDangertype extends BaseDomain {

    private static final long serialVersionUID = 1L;


    //
    @Dict(key = DictKeys.METEOROLOGICAL_ID)
    @Column(name = "meteorological_id")
    private String meteorologicalId;

    //
    @Column(name = "point_id")
    private Long pointId;

    //
    @Column(name = "thresholdValue")
    private String thresholdValue;

    //
    @Column(name = "logic")
    private String logic;
    
    

    public String getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public void setMeteorologicalId(String meteorologicalId){
        this.meteorologicalId = meteorologicalId;
    }

    public String getMeteorologicalId(){
        return meteorologicalId;
    }

    public void setPointId(Long pointId){
        this.pointId = pointId;
    }

    public Long getPointId(){
        return pointId;
    }

    public void setLogic(String logic){
        this.logic = logic;
    }

    public String getLogic(){
        return logic;
    }
}
