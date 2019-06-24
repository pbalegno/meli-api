package com.meli.solr.api.domain;

import static com.meli.solr.api.domain.Constants.DEGREE;

import org.springframework.data.geo.Point;

import com.meli.solr.api.domain.enumeration.DirectionType;
import com.meli.solr.api.domain.enumeration.PlanetType;

public class Planet {

	private PlanetType planet;

	
	
	
	public Planet(PlanetType planet) {

		this.planet = planet;
	}

	// returns the angle of the planet in radians
	// https://www.fisicalab.com/apartado/magnitudes-angulares#posicion_angular
	public double getAngularPosition(Integer day) {
		double degree = (day * getPlanet().getAngularSpeed()) % DEGREE;
		// If we don't check for degree > 0, then when the degree is 0 and
		// the direction is clockwise we end up with 360 degrees.
		if (getPlanet().getOrbitDirection() == DirectionType.ClockWise && degree > 0) {
			degree = DEGREE - degree;
		}
		return  Math.toRadians(degree);
	}

	public Point getPoint(Integer day) {
		double radians = getAngularPosition(day);
		double x = Math.cos(radians) * planet.getRadius();
		double y = Math.sin(radians) * planet.getRadius();
		return new Point(x, y);
	}

	public com.meli.solr.api.domain.enumeration.PlanetType getPlanet() {
		return planet;
	}

	public void setPlanet(com.meli.solr.api.domain.enumeration.PlanetType planet) {
		this.planet = planet;
	}

}
