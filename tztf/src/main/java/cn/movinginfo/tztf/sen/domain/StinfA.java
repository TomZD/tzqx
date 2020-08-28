package cn.movinginfo.tztf.sen.domain;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;


/**
* @description:
* @author: autoCode
* @history:
*/
@Table(name = "ST_STINF_A")
public class StinfA  {

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "STCD")
    private String stcd;

    //
    @Column(name = "STNM")
    private String stnm;

    //
    @Column(name = "ESLO")
    private BigDecimal eslo;

    //
    @Column(name = "NTLA")
    private BigDecimal ntla;

    //
    @Column(name = "BASE")
    private String base;

    //
    @Column(name = "STTP")
    private String sttp;

    //
    @Column(name = "City")
    private String city;

    //
    @Column(name = "County")
    private String county;

    //
    @Column(name = "Stage_Typhoon")
    private Integer stageTyphoon;

    //
    @Column(name = "Stage_Rain")
    private Integer stageRain;

    //
    @Column(name = "Height_Bar")
    private Integer heightBar;

    //
    @Column(name = "Stage_Alert")
    private Integer stageAlert;

    public void setStcd(String stcd){
        this.stcd = stcd;
    }

    public String getStcd(){
        return stcd;
    }

    public void setStnm(String stnm){
        this.stnm = stnm;
    }

    public String getStnm(){
        return stnm;
    }

    public void setEslo(BigDecimal eslo){
        this.eslo = eslo;
    }

    public BigDecimal getEslo(){
        return eslo;
    }

    public void setNtla(BigDecimal ntla){
        this.ntla = ntla;
    }

    public BigDecimal getNtla(){
        return ntla;
    }

    public void setBase(String base){
        this.base = base;
    }

    public String getBase(){
        return base;
    }

    public void setSttp(String sttp){
        this.sttp = sttp;
    }

    public String getSttp(){
        return sttp;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }

    public void setCounty(String county){
        this.county = county;
    }

    public String getCounty(){
        return county;
    }

    public void setStageTyphoon(Integer stageTyphoon){
        this.stageTyphoon = stageTyphoon;
    }

    public Integer getStageTyphoon(){
        return stageTyphoon;
    }

    public void setStageRain(Integer stageRain){
        this.stageRain = stageRain;
    }

    public Integer getStageRain(){
        return stageRain;
    }

    public void setHeightBar(Integer heightBar){
        this.heightBar = heightBar;
    }

    public Integer getHeightBar(){
        return heightBar;
    }

    public void setStageAlert(Integer stageAlert){
        this.stageAlert = stageAlert;
    }

    public Integer getStageAlert(){
        return stageAlert;
    }

	@Override
	public String toString() {
		return "StinfA [stcd=" + stcd + ", stnm=" + stnm + ", eslo=" + eslo + ", ntla=" + ntla + ", base=" + base
				+ ", sttp=" + sttp + ", city=" + city + ", county=" + county + ", stageTyphoon=" + stageTyphoon
				+ ", stageRain=" + stageRain + ", heightBar=" + heightBar + ", stageAlert=" + stageAlert + "]";
	}
    
    
}
