package com.meli.solr.api.domain;

import static com.meli.solr.api.domain.Constants.DEGREE;

import org.springframework.data.geo.Point;

import com.meli.solr.api.domain.enumeration.DirectionType;
import com.meli.solr.api.domain.enumeration.PlanetType;

public class PlanetProvider {

	private PlanetType planet;

	public PlanetProvider(PlanetType planet) {
		this.planet = planet;
	}

	// https://www.fisicalab.com/apartado/magnitudes-angulares#posicion_angular
    // https://www.universoformulas.com/fisica/cinematica/posicion-movimiento-circular/
	/**
	 * @param Integer day
	 * @return the angle of the planet in radians
	 */
	
	public double getAngularPosition(Integer day) {
		/*que arrancan alineados a los 0°*/
	    //los planetas comienzan en un ángulo de 0 grados respecto del eje X.
		double degree = (day * getPlanet().getAngularSpeed()) % DEGREE;
		if (isClockWise() && degree > 0) {
			degree = DEGREE - degree;
		}
		return Math.toRadians(degree);
	}
	
	
	private boolean isClockWise() {
		return getPlanet().getOrbitDirection() == DirectionType.ClockWise;
	}

	/**
	 * @param Integer day
	 * @return the point in the plane X,Y
	 */
	public Point getPoint(Integer day) {
		double radians = getAngularPosition(day);
		double x = Math.cos(radians) * planet.getRadius();
		double y = Math.sin(radians) * planet.getRadius();
		return new Point(x, y);
	}

	public PlanetType getPlanet() {
		return planet;
	}

	public void setPlanet(PlanetType planet) {
		this.planet = planet;
	}

}
