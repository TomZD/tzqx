package cn.movinginfo.tztf.sen.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TownOCF extends TownOCFKey implements Serializable {
    private BigDecimal pr03;

    private BigDecimal pr06;

    private BigDecimal pr12;

    private BigDecimal pr24;

    private BigDecimal rh;

    private BigDecimal t;

    private BigDecimal tmax;

    private BigDecimal tmin;

    private BigDecimal cloud;

    private BigDecimal windD;

    private BigDecimal windS;

    private BigDecimal snowf03;

    private BigDecimal snowf06;

    private BigDecimal snowf12;

    private BigDecimal snowf24;

    private BigDecimal snowd;

    private String ww3;

    private String ww6;

    private String ww12;

    private BigDecimal aqi;

    private BigDecimal pm25;

    private static final long serialVersionUID = 1L;

    public BigDecimal getPr03() {
        return pr03;
    }

    public void setPr03(BigDecimal pr03) {
        this.pr03 = pr03;
    }

    public BigDecimal getPr06() {
        return pr06;
    }

    public void setPr06(BigDecimal pr06) {
        this.pr06 = pr06;
    }

    public BigDecimal getPr12() {
        return pr12;
    }

    public void setPr12(BigDecimal pr12) {
        this.pr12 = pr12;
    }

    public BigDecimal getPr24() {
        return pr24;
    }

    public void setPr24(BigDecimal pr24) {
        this.pr24 = pr24;
    }

    public BigDecimal getRh() {
        return rh;
    }

    public void setRh(BigDecimal rh) {
        this.rh = rh;
    }

    public BigDecimal getT() {
        return t;
    }

    public void setT(BigDecimal t) {
        this.t = t;
    }

    public BigDecimal getTmax() {
        return tmax;
    }

    public void setTmax(BigDecimal tmax) {
        this.tmax = tmax;
    }

    public BigDecimal getTmin() {
        return tmin;
    }

    public void setTmin(BigDecimal tmin) {
        this.tmin = tmin;
    }

    public BigDecimal getCloud() {
        return cloud;
    }

    public void setCloud(BigDecimal cloud) {
        this.cloud = cloud;
    }

    public BigDecimal getWindD() {
        return windD;
    }

    public void setWindD(BigDecimal windD) {
        this.windD = windD;
    }

    public BigDecimal getWindS() {
        return windS;
    }

    public void setWindS(BigDecimal windS) {
        this.windS = windS;
    }

    public BigDecimal getSnowf03() {
        return snowf03;
    }

    public void setSnowf03(BigDecimal snowf03) {
        this.snowf03 = snowf03;
    }

    public BigDecimal getSnowf06() {
        return snowf06;
    }

    public void setSnowf06(BigDecimal snowf06) {
        this.snowf06 = snowf06;
    }

    public BigDecimal getSnowf12() {
        return snowf12;
    }

    public void setSnowf12(BigDecimal snowf12) {
        this.snowf12 = snowf12;
    }

    public BigDecimal getSnowf24() {
        return snowf24;
    }

    public void setSnowf24(BigDecimal snowf24) {
        this.snowf24 = snowf24;
    }

    public BigDecimal getSnowd() {
        return snowd;
    }

    public void setSnowd(BigDecimal snowd) {
        this.snowd = snowd;
    }

    public String getWw3() {
        return ww3;
    }

    public void setWw3(String ww3) {
        this.ww3 = ww3 == null ? null : ww3.trim();
    }

    public String getWw6() {
        return ww6;
    }

    public void setWw6(String ww6) {
        this.ww6 = ww6 == null ? null : ww6.trim();
    }

    public String getWw12() {
        return ww12;
    }

    public void setWw12(String ww12) {
        this.ww12 = ww12 == null ? null : ww12.trim();
    }

    public BigDecimal getAqi() {
        return aqi;
    }

    public void setAqi(BigDecimal aqi) {
        this.aqi = aqi;
    }

    public BigDecimal getPm25() {
        return pm25;
    }

    public void setPm25(BigDecimal pm25) {
        this.pm25 = pm25;
    }
}