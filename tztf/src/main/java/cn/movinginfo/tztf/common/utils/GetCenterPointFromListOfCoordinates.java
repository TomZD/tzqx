package cn.movinginfo.tztf.common.utils;

import java.util.ArrayList;
import java.util.List;

import cn.movinginfo.tztf.common.domain.GeoCoordinate;

public class GetCenterPointFromListOfCoordinates {
	private static final long serialVersionUID = 1L;
	/**
     *  根据输入的地点坐标计算中心点
     * @param geoCoordinateList
     * @return
     */
    public static GeoCoordinate getCenterPoint(List<GeoCoordinate> geoCoordinateList) {
        int total = geoCoordinateList.size();
        double X = 0, Y = 0, Z = 0;
        for (GeoCoordinate g : geoCoordinateList) {
            double lat, lon, x, y, z;
            lat = g.getLatitude() * Math.PI / 180;
            lon = g.getLongitude() * Math.PI / 180;
            x = Math.cos(lat) * Math.cos(lon);
            y = Math.cos(lat) * Math.sin(lon);
            z = Math.sin(lat);
            X += x;
            Y += y;
            Z += z;
        }

        X = X / total;
        Y = Y / total;
        Z = Z / total;
        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);
        return new GeoCoordinate(Lat * 180 / Math.PI, Lon * 180 / Math.PI);
    }

    /**
     * 根据输入的地点坐标计算中心点（适用于400km以下的场合）
     * @param geoCoordinateList
     * @return
     */
    public static GeoCoordinate getCenterPoint400(List<GeoCoordinate> geoCoordinateList) {
        // 以下为简化方法（400km以内）
        int total = geoCoordinateList.size();
        double lat = 0, lon = 0;
        for (GeoCoordinate g : geoCoordinateList) {
            lat += g.getLatitude() * Math.PI / 180;
            lon += g.getLongitude() * Math.PI / 180;
        }
        lat /= total;
        lon /= total;
        return new GeoCoordinate(lat * 180 / Math.PI, lon * 180 / Math.PI);
    }
    
    
    public static String getPoint() {
    	String[] doc = {"120.336,30.308","120.135,30.235","120.108,30.294","120.197,30.207","120.207,30.282","120.124,30.189","120.224,30.189","120.324,30.189","120.324,30.289",
		        "120.236,30.308","120.436,30.308","120.236,30.408","120.336,30.108"};
	    int index = (int) (Math.random() * doc.length);
		String a = doc[index];
		return a;
    }

    public static void main(String[] args) {
        List<GeoCoordinate> geoCoordinateList = new ArrayList<GeoCoordinate>();
        GeoCoordinate g = new GeoCoordinate();
        g.setLongitude(120.14599609375);
        g.setLatitude(30.312654495239258);
        GeoCoordinate g2 = new GeoCoordinate();
        g2.setLongitude(120.22564697265625);
        g2.setLatitude(30.31147003173828);
        GeoCoordinate g3 = new GeoCoordinate();
        g3.setLongitude(120.17208862304688);
        g3.setLatitude(30.242090225219727);
        GeoCoordinate g4 = new GeoCoordinate();
        g4.setLongitude(120.14599609375);
        g4.setLatitude(30.312654495239258);
        
        GeoCoordinate g5 = new GeoCoordinate();
        g5.setLongitude(120.14599609335);
        g5.setLatitude(30.312258);
        geoCoordinateList.add(g);
        geoCoordinateList.add(g2);
        geoCoordinateList.add(g3);
        geoCoordinateList.add(g4);
        geoCoordinateList.add(g5);
        GeoCoordinate  re = GetCenterPointFromListOfCoordinates.getCenterPoint(geoCoordinateList);
        System.out.println(re.getLongitude() +"   "+ re.getLatitude());
    }
}
