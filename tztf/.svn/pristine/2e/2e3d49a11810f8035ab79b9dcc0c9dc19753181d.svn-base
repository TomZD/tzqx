package cn.movinginfo.tztf.common.typhoon;

import java.awt.geom.Point2D;

import cn.movinginfo.tztf.sen.model.Point;



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
	/// ARCGIS 中墨卡托空间参考值
	/// </summary>
	public static final int MercatorSparialReference = 102113;

	/// <summary>
	/// 地球半径(公里)
	/// </summary>
	public static final double EarthRadius = 6378.137;

	/// <summary>
	/// 经纬度 -> 墨卡托
	/// </summary>
	/// <param name="lngLat"></param>
	/// <returns></returns>
	public static Point2D location2Mercator(Point2D lngLat) {
		Point2D mercator = new Point2D.Float();

		float x = (float) (lngLat.getX() * 20037508.34 / 180);
		float y = (float) (Math.log(Math.tan((90 + lngLat.getY()) * Math.PI / 360)) / (Math.PI / 180));
		y = (float) (y * 20037508.34 / 180);
		mercator.setLocation(x, y);
		return mercator;
	}

	/// <summary>
	/// 墨卡托 -> 经纬度
	/// </summary>
	/// <param name="mercator"></param>
	/// <returns></returns>
	public static Point2D mercator2Location(Point2D mercator) {
		Point2D lonLat = new Point2D.Float();
		float x = (float) (mercator.getX() / 20037508.34 * 180);
		float y = (float) (mercator.getY() / 20037508.34 * 180);
		y = (float) (180 / Math.PI * (2 * Math.atan(Math.exp(y * Math.PI / 180)) - Math.PI / 2));
		lonLat.setLocation(x, y);
		return lonLat;
	}

	/// <summary>
	/// 计算墨卡托两点距离(公里)
	/// </summary>
	/// <param name="mercatorStart">起始点</param>
	/// <param name="mercatorEnd">结束点</param>
	/// <returns>两点间距离（公里）</returns>
	public static double GetMercatorDistance(Point2D mercatorStart, Point2D mercatorEnd) {
		Point2D latLngStart = mercator2Location(mercatorStart);
		Point2D latLngEnd = mercator2Location(mercatorEnd);

		return GetDistance(latLngStart, latLngEnd);
	}

	/// <summary>
	/// 计算两点距离(公里)
	/// <see cref="GetDistance(double,double,double,double)" />
	/// </summary>
	/// <param name="start">起始点</param>
	/// <param name="end">结束点</param>
	/// <returns>两点间距离（公里）</returns>
	public static double GetDistance(Point2D start, Point2D end) {
		return GetDistance(start.getY(), start.getX(), end.getY(), end.getX());
	}

	/// <summary>
	/// 计算两点距离(公里)
	/// </summary>
	/// <param name="startLat">纬度1</param>
	/// <param name="startLng">经度1</param>
	/// <param name="endLat">纬度2</param>
	/// <param name="endLng">经度2</param>
	/// <returns>两点间距离（公里）</returns>
	public static double GetDistance(double startLat, double startLng, double endLat, double endLng) {
		double radStartLat = Angle2Radian(startLat);
		double radEndLat = Angle2Radian(endLat);
		double radStartLng = Angle2Radian(startLng);
		double radEndLng = Angle2Radian(endLng);

		double latDiff = radStartLat - radEndLat;
		double lngDiff = radStartLng - radEndLng;

		double result = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latDiff / 2), 2)
				+ Math.cos(radStartLat) * Math.cos(radEndLat) * Math.pow(Math.sin(lngDiff / 2), 2)));

		return Math.round(result * EarthRadius * 10000.0) / 10000.0;
	}

	/// <summary>
	/// 角度转换为弧度
	/// </summary>
	/// <param name="angle"></param>
	/// <returns></returns>
	public static double Angle2Radian(double angle) {
		return angle * Math.PI / 180.0;
	}

	/// <summary>
	/// 弧度转换为角度
	/// </summary>
	/// <param name="radian"></param>
	/// <returns></returns>
	public static double Radian2Angle(double radian) {
		return radian * 180.0 / Math.PI;
	}

	/**
	 * 判断两条线是否相交 a 线段1起点坐标 b 线段1终点坐标 c 线段2起点坐标 d 线段2终点坐标 intersection 相交点坐标
	 * reutrn 是否相交: 0 : 两线平行 -1 : 不平行且未相交 1 : 两线相交
	 */
	public static int segIntersect(Point A, Point B, Point C, Point D) {
		Point intersection = new Point();
		if (Math.abs(B.getY() - A.getY()) + Math.abs(B.getX() - A.getX()) + Math.abs(D.getY() - C.getY())
				+ Math.abs(D.getX() - C.getX()) == 0) {
//			if ((C.getX() - A.getX()) + (C.getY() - A.getY()) == 0) {
//				System.out.println("ABCD是同一个点！");
//			} else {
//				System.out.println("AB是一个点，CD是一个点，且AC不同！");
//			}
			return 0;
		}
		if (Math.abs(B.getY() - A.getY()) + Math.abs(B.getX() - A.getX()) == 0) {
//			if ((A.getX() - D.getX()) * (C.getY() - D.getY()) - (A.getY() - D.getY()) * (C.getX() - D.getX()) == 0) {
//				System.out.println("A、B是一个点，且在CD线段上！");
//			} else {
//				System.out.println("A、B是一个点，且不在CD线段上！");
//			}
			return 0;
		}
		if (Math.abs(D.getY() - C.getY()) + Math.abs(D.getX() - C.getX()) == 0) {
//			if ((D.getX() - B.getX()) * (A.getY() - B.getY()) - (D.getY() - B.getY()) * (A.getX() - B.getX()) == 0) {
//				System.out.println("C、D是一个点，且在AB线段上！");
//			} else {
//				System.out.println("C、D是一个点，且不在AB线段上！");
//			}
			return 0;
		}
		if ((B.getY() - A.getY()) * (C.getX() - D.getX()) - (B.getX() - A.getX()) * (C.getY() - D.getY()) == 0) {
			// System.out.println("线段平行，无交点！");
			return 0;
		}

		intersection.setX(((B.getX() - A.getX()) * (C.getX() - D.getX()) * (C.getY() - A.getY())
				- C.getX() * (B.getX() - A.getX()) * (C.getY() - D.getY())
				+ A.getX() * (B.getY() - A.getY()) * (C.getX() - D.getX()))
				/ ((B.getY() - A.getY()) * (C.getX() - D.getX()) - (B.getX() - A.getX()) * (C.getY() - D.getY())));
		intersection.setY(((B.getY() - A.getY()) * (C.getY() - D.getY()) * (C.getX() - A.getX())
				- C.getY() * (B.getY() - A.getY()) * (C.getX() - D.getX())
				+ A.getY() * (B.getX() - A.getX()) * (C.getY() - D.getY()))
				/ ((B.getX() - A.getX()) * (C.getY() - D.getY()) - (B.getY() - A.getY()) * (C.getX() - D.getX())));
		if ((intersection.getX() - A.getX()) * (intersection.getX() - B.getX()) <= 0
				&& (intersection.getX() - C.getX()) * (intersection.getX() - D.getX()) <= 0
				&& (intersection.getY() - A.getY()) * (intersection.getY() - B.getY()) <= 0
				&& (intersection.getY() - C.getY()) * (intersection.getY() - D.getY()) <= 0) {
			if ((A.getX() == C.getX() && A.getY() == C.getY()) || (A.getX() == D.getX() && A.getY() == D.getY())
					|| (B.getX() == C.getX() && B.getY() == C.getY())
					|| (B.getX() == D.getX() && B.getY() == D.getY())) {

//				System.out.println("线段相交于端点上");
				return 2;

			} else {
//				System.out.println("线段相交于点(" + intersection.getX() + "," + intersection.getY() + ")！");
				return 1; // '相交
			}

		} else {
			// System.out.println("线段相交于虚交点(" + intersection.getX() + ","
			// + intersection.getY() + ")！");
			return -1; // '相交但不在线段上
		}
	}
	
	public static void main(String[] args) {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(10, 10);
		Point p3 = new Point(3, 4);
		Point p4 = new Point(4, 3);
		
		System.out.println(segIntersect(p1, p2, p3, p4));
	}
}
