package com.meli.solr.api.geometry;

import org.springframework.data.geo.Point;

public class GeoUtils {

	
	public static double distance(Point pos1, Point pos2) {
		//sqrt(x2 +y2) without intermediate overflow or underflow
//		OR Math.sqrt(Math.pow((pos2.getX()-pos1.getX()),2)+Math.pow((pos2.getY()-pos1.getY()),2));
		return Math.hypot(pos2.getX() - pos1.getX(), pos2.getY() - pos1.getY());
	}
}
