package com.meli.solr.api.geometry;

import static com.meli.solr.api.geometry.GeoUtils.*;

import org.springframework.data.geo.Point;

public class Line extends Shape {

	public Line(Point pA, Point pB, Point pC) {
		super(pA, pB, pC);
	}

	public boolean isAligned() {
		double result = distance(getPointA(), getPointC());
		return (result == sumPoints());
	}

	public boolean isAlignedWith(Point point) {
		double result = distance(point, getPointC());
		double AB = distance(point, getPointA());
		return (result == AB + sumPoints());
	}

	private double sumPoints() {
		double AB = distance(getPointA(), getPointB());
		double BC = distance(getPointB(), getPointC());
		return AB + BC;
	} 

}
