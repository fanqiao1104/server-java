package com.sleep.server.util;

import java.awt.geom.Point2D;

/**
 * 经纬度工具类
 *
 * @author chendeheng Created on 2020/8/3.
 */
public class DistanceUtils {
    // 地球平均半径,单位：km
    private static final double EARTH_RADIUS = 6371.393;

    /**
     * 通过AB点经纬度获取距离
     *
     * @param lngA A点经度
     * @param latA A点纬度
     * @param lngB B点经度
     * @param latB B点纬度
     * @return 两地之间距离（单位：千米/公里）
     */
    public static double getDistance(String lngA, String latA, String lngB, String latB) {
        if (StringUtils.isEmpty(lngA) || StringUtils.isEmpty(latA)
            || StringUtils.isEmpty(lngB) || StringUtils.isEmpty(latB)) {
            return 0.0;
        }
        Point2D pointA = new Point2D.Double(Double.parseDouble(lngA), Double.parseDouble(latA));
        Point2D pointB = new Point2D.Double(Double.parseDouble(lngB), Double.parseDouble(latB));

        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        // A经弧度
        double radiansAX = Math.toRadians(pointA.getX());
        // A纬弧度
        double radiansAY = Math.toRadians(pointA.getY());
        // B经弧度
        double radiansBX = Math.toRadians(pointB.getX());
        // B纬弧度
        double radiansBY = Math.toRadians(pointB.getY());

        // cosβ1cosβ2cos（α1-α2）+ sinβ1sinβ2
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
            + Math.sin(radiansAY) * Math.sin(radiansBY);
        // 反余弦值
        double acos = Math.acos(cos);
        return EARTH_RADIUS * acos;
    }

}
