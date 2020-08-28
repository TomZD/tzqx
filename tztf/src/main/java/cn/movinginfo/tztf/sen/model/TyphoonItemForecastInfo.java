package cn.movinginfo.tztf.sen.model;

import java.util.List;

/**
 * @Author: baolonghui
 * @Description
 * @Data Created in 14:20 2018/6/21
 * @Modified By:
 */
public class TyphoonItemForecastInfo {
    private String name;
    private List<TyphoonItemForecastPoint> points;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TyphoonItemForecastPoint> getPoints() {
        return points;
    }

    public void setPoints(List<TyphoonItemForecastPoint> points) {
        this.points = points;
    }
}
