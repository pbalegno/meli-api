package com.meli.solr.api.service.impl;

import static com.meli.solr.api.domain.Constants.SUN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.meli.solr.api.domain.Location;
import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.domain.Planet;
import com.meli.solr.api.domain.enumeration.PlanetType;
import com.meli.solr.api.domain.enumeration.WeatherType;
import com.meli.solr.api.geometry.Line;
import com.meli.solr.api.geometry.Shape;
import com.meli.solr.api.geometry.Triangle;
import com.meli.solr.api.service.ForecastService;
import com.meli.solr.api.service.LocationService;

/**
 * Service Implementation for managing Forecast
 */
@Service
public class ForecastServiceImpl implements ForecastService {


	@Autowired
	private LocationService locationService;
	
	private final Logger log = LoggerFactory.getLogger(ForecastServiceImpl.class);
	private static List<Planet> planets = new ArrayList<>();
	static {
		planets.add(new Planet(PlanetType.Betasoide));
		planets.add(new Planet(PlanetType.Ferengi));
		planets.add(new Planet(PlanetType.Vulcano));
	}
	
	private double HEAVY_RAIN_MAX_PER = 0;

	public Measure getMeasure(Integer day) {
		log.debug("Getting measure for day: {}", day);
		Map<PlanetType, Point> points = resolvePoints(day);
		Measure measure = new Measure();
		measure.setDay(day);

		Point point1 = points.get(PlanetType.Ferengi);
		Point point2 = points.get(PlanetType.Vulcano);
		Point point3 = points.get(PlanetType.Betasoide);

		Shape line = new Line(point1, point2, point3);
		// if all the planets and the sun are aligned, then the solar system experiments a period of drought
		if (((Line) line).isAlignedWith(SUN)) {
			measure.setWeather(WeatherType.Drought);
			return measure;
		}

		// solar system experiments optimal temperatures if all the planets are aligned with each other but not with the sun
		if (((Line) line).isAligned()) {
			measure.setWeather(WeatherType.OptimalTemperature);
			return measure;
		}

		// when the three planets are not aligned, they form a triangle
		Shape triangle = new Triangle(point1, point2, point3);

        // when the sun is inside the triangle, the solar system experiences a period of rain
		if (((Triangle) triangle).isInside(SUN)) {
			double perimeter = ((Triangle) triangle).perimeter();
			//Heavy rain when the perimeter of the triangle is at its maximum
			if (perimeter > HEAVY_RAIN_MAX_PER) {
				HEAVY_RAIN_MAX_PER = perimeter;
				measure.setWeather(WeatherType.HeavyRain);
				return measure;
			}
			measure.setWeather(WeatherType.Rain);
			return measure;
		}
		
		measure.setWeather(WeatherType.Other);
		return measure;
		
	}

	public Map<PlanetType, Point> resolvePoints(Integer day) {
		Map<PlanetType, Point> points = new HashMap<>();
		planets.stream().forEach(p-> {
			Point point = p.getPoint(day);
			Location location = createLocation(p, point);
			locationService.save(location);
			points.put(p.getPlanet(), point);
		});
		return points;
	}

	private Location createLocation(Planet planet, Point point) {
		Location location = new Location();
		location.setPlanet(planet.getPlanet());
		location.setX(point.getX());
		location.setY(point.getY());
		return location;
	}

	
}
