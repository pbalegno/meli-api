package com.meli.solr.api.geometry;

import org.springframework.data.geo.Point;

public interface IShape {

	public boolean isAligned();

	public boolean isAlignedWith(Point point);
	
	public boolean isInside(Point point);
	
	public double getPerimeter();

}
