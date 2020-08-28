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
@Table(name = "ST_RNFL_R")
public class RnflR{

    private static final long serialVersionUID = 1L;


    //
    @Column(name = "STCD")
    private String stcd;

    //
    @Column(name = "YMDHM")
    private java.util.Date ymdhm;

    //
    @Column(name = "DTRN")
    private BigDecimal dtrn;

    public void setStcd(String stcd){
        this.stcd = stcd;
    }

    public String getStcd(){
        return stcd;
    }

    public void setYmdhm(java.util.Date ymdhm){
        this.ymdhm = ymdhm;
    }

    public java.util.Date getYmdhm(){
        return ymdhm;
    }

    public void setDtrn(BigDecimal dtrn){
        this.dtrn = dtrn;
    }

    public BigDecimal getDtrn(){
        return dtrn;
    }
}
