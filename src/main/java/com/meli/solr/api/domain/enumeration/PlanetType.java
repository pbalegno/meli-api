package com.meli.solr.api.domain.enumeration;

/**
 * The Planet enumeration.
 */
public enum PlanetType {
  
    /**
	 * 
	 * El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en
	 * sentido horario. Su distancia con respecto al sol es de 500Km. 
	 * 
	 * El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido horario. 
	 * Su distancia con respecto al sol es de 2000Km.
	 *  
	 * El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido anti-horario,
	 * su distancia con respecto al sol es de 1000Km.
	 * 
	 */
	Ferengi(1, 500, DirectionType.ClockWise), 
	Betasoide(3, 2000, DirectionType.ClockWise),
	Vulcano(5, 1000, DirectionType.AntiClockWise);

	private double angularSpeed;
	private double radius;
	private DirectionType orbitDirection;

	public double getAngularSpeed() {
		return angularSpeed;
	}

	public void setAngularSpeed(Integer angularSpeed) {
		this.angularSpeed = angularSpeed;
	}

	public double getRadius() {
		return radius;
	}

	public void setOrbitRadius(double orbitRadius) {
		this.radius = orbitRadius;
	}

	public DirectionType getOrbitDirection() {
		return orbitDirection;
	}

	public void setOrbitDirection(DirectionType orbitDirection) {
		this.orbitDirection = orbitDirection;
	}

	private PlanetType(double angularSpeed, double radius, DirectionType orbitDirection) {
		this.angularSpeed = angularSpeed;
		this.radius = radius;
		this.orbitDirection = orbitDirection;
	}
}
