package com.meli.solr.api.geo;

import static com.meli.solr.api.geo.GeoUtils.distance;

import org.springframework.data.geo.Point;


	public class Triangle extends Shape {

		public Triangle(Point pA, Point pB, Point pC) {
			super(pA, pB, pC);
		}

//		https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/
		/*
		 * A utility function to calculate area of triangle formed by (x1, y1) (x2, y2)
		 * and (x3, y3)
		 */
		private double area(Point pA, Point pB, Point pC) {
			return Math.abs((pA.getX() * (pB.getY() - pC.getY()) + pB.getX() * (pC.getY() - pA.getY())
					+ pC.getX() * (pB.getX() - pB.getY())) / 2.0);
		}

		/*
		 * A function to check whether point P(x, y) lies inside the triangle formed by
		 * A(x1, y1), B(x2, y2) and C(x3, y3)
		 */
		public boolean isInside(Point point) {
			/* Calculate area of triangle ABC */
			double A = area(getPointA(), getPointB(), getPointC());
			/* Calculate area of triangle PBC */
			double A1 = area(point, getPointB(), getPointC());
			/* Calculate area of triangle APC */
			double A2 = area(getPointA(), point, getPointC());
			/* Calculate area of triangle ABP */
			double A3 = area(getPointA(), getPointB(), point);
			/* Check if sum of A1, A2 and A3 is same as A */
			return (A == A1 + A2 + A3);
		}


       // returns the perimeter of the triangle
		public double perimeter() {
			double d1 = distance(getPointA(), getPointB());
			double d3 = distance(getPointB(), getPointC());
			double d2 = distance(getPointC(), getPointA());
			return d1 + d2 + d3;
		}
		
	
}
