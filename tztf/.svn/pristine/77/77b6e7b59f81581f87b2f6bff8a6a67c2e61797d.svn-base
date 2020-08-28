package cn.movinginfo.tztf.common.utils;

import java.awt.geom.Point2D;

/**
 * Created by 涛 on 2015/3/25.
 */
public class GISHelper {
    public static final double MinX = -180;
    public static final double MinY = -85;
    public static final double MaxX = 180;
    public static final double MaxY = 85;

    public static final double MercatorMinX = -20037508.342787;
    public static final double MercatorMinY = -20037508.342787;
    public static final double MercatorMaxX = 20037508.342787;
    public static final double MercatorMaxY = 20037508.342787;

    /// <summary>
    ///     ARCGIS 中墨卡托空间参考值
    /// </summary>
    public static final int MercatorSparialReference = 102113;

    /// <summary>
    ///     地球半径(公里)
    /// </summary>
    public static final double EarthRadius = 6378.137;

    /// <summary>
    ///     经纬度 -> 墨卡托
    /// </summary>
    /// <param name="lngLat"></param>
    /// <returns></returns>
    public static Point2D location2Mercator(Point2D lngLat)
    {
        Point2D mercator = new Point2D.Float();

        float x = (float) (lngLat.getX()*20037508.34/180);
        float y = (float) (Math.log(Math.tan((90 + lngLat.getY()) * Math.PI / 360))/(Math.PI/180));
        y = (float) (y*20037508.34/180);
        mercator.setLocation(x,y);
        return mercator;
    }

    /// <summary>
    ///     墨卡托 -> 经纬度
    /// </summary>
    /// <param name="mercator"></param>
    /// <returns></returns>
    public static Point2D mercator2Location(Point2D mercator)
    {
        Point2D lonLat = new Point2D.Float();
        float x = (float) (mercator.getX()/20037508.34*180);
        float y = (float) (mercator.getY()/20037508.34*180);
        y = (float) (180/Math.PI*(2*Math.atan(Math.exp(y*Math.PI/180)) - Math.PI/2));
        lonLat.setLocation(x,y);
        return lonLat;
    }
}
