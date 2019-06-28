package com.meli.solr.api.geometry;

import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.util.Assert;

import com.meli.solr.api.domain.Constants;

public class ShapeFactory {

	public static Shape getShape(List<Point> points) {

		Point pointA = points.get(0);
		Point pointB = points.get(1);
		Point pointC = points.get(2);

		Assert.notNull(pointA, "Source point A must not be null!");
		Assert.notNull(pointB, "Source point B must not be null!");
		Assert.notNull(pointC, "Source point C not be null!");
		Shape shape = new Line(pointA, pointB, pointC);
		if (shape.isAligned() || shape.isAlignedWith(Constants.SUN)) {
			return shape;
		}
		return new Triangle(pointA, pointB, pointC);
	}

}
