package com.meli.solr.api.geo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;

import com.meli.solr.api.geometry.Shape;
import com.meli.solr.api.geometry.ShapeFactory;

public class GeoTest {


	@Test
	public void testAreaTriangle() {
		List<Point> points = new ArrayList<>(3);
		points.add(new Point(1, 1));
		points.add(new Point(2, 2));
		points.add(new Point(3, 3));
		Shape shape = ShapeFactory.getShape(points);
		Assert.assertEquals(false, shape.isInside(new Point(0,0)));
	}
	
	@Test
	public void testAlign3Points() {
		List<Point> points = new ArrayList<>(3);
		points.add(new Point(1, 1));
		points.add(new Point(2, 2));
		points.add(new Point(3, 3));
		Shape shape = ShapeFactory.getShape(points);
		Assert.assertEquals(true, shape.isAligned());
	}
	
	@Test
	public void testAlign3PointsWithSun() {
		List<Point> points = new ArrayList<>(3);
		points.add(new Point(1, 1));
		points.add(new Point(2, 2));
		points.add(new Point(3, 3));
		Shape shape = ShapeFactory.getShape(points);
		Assert.assertEquals(false, shape.isAlignedWith(new Point(0,0)));
		

	}


}
