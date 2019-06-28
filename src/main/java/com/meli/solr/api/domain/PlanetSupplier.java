package com.meli.solr.api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.geo.Point;

import com.meli.solr.api.domain.enumeration.PlanetType;

public class PlanetSupplier {

	private static List<PlanetProvider> planets = new ArrayList<>();
	static {
		planets.add(new PlanetProvider(PlanetType.Ferengi));
		planets.add(new PlanetProvider(PlanetType.Vulcano));
		planets.add(new PlanetProvider(PlanetType.Betasoide));
	}
	
	public static List<PlanetProvider> getPlanets() {
		return planets;
	}

	public static List<Point> getPoints(Integer day) {
		return planets.stream().map(p -> p.getPoint(day)).collect(Collectors.toList());
	}
	
}
