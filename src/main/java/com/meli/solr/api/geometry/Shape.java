package com.meli.solr.api.geometry;

import org.springframework.data.geo.Point;
import org.springframework.util.Assert;


public abstract class Shape implements IShape {
	
	private Point pA, pB, pC;

	public Shape(Point pA, Point pB, Point pC) {
		Assert.notNull(pA, "Source pA must not be null!");
		Assert.notNull(pB, "Source pB must not be null!");
		Assert.notNull(pC, "Source pC must not be null!");
		this.pA = pA;
		this.pB = pB;
		this.pC = pC;
	}
	
	public Point getPointA() {
		return pA;
	}

	public void setpA(Point pA) {
		this.pA = pA;
	}

	public Point getPointB() {
		return pB;
	}

	public void setpB(Point pB) {
		this.pB = pB;
	}

	public Point getPointC() {
		return pC;
	}

	public void setpC(Point pC) {
		this.pC = pC;
	}
	


}
