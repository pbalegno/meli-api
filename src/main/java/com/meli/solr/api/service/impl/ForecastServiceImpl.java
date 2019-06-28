package com.meli.solr.api.service.impl;

import static com.meli.solr.api.domain.Constants.SUN;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.domain.PlanetSupplier;
import com.meli.solr.api.domain.enumeration.WeatherType;
import com.meli.solr.api.geometry.Shape;
import com.meli.solr.api.geometry.ShapeFactory;
import com.meli.solr.api.service.ForecastService;

/**
 * Service Implementation for managing Forecast
 */
@Service
public class ForecastServiceImpl implements ForecastService {

	private final Logger log = LoggerFactory.getLogger(ForecastServiceImpl.class);
	private double HEAVY_RAIN_MAX_PER = 0;

	public Measure getMeasure(Integer day) {
		log.debug("Getting measure for day: {}", day);
		return evaluate(day);

	}

	private Measure createMeasure(Integer day, WeatherType type) {
		Measure measure = new Measure();
		measure.setDay(day);
		measure.setWeather(type);
		log.debug("Measure for day: {}, is a weather: {}", day, type);
		return measure;
	}
	
	
	private Measure evaluate(Integer day) {
		Shape shape = ShapeFactory.getShape(PlanetSupplier.getPoints(day));
		// if all the planets and the sun are aligned, then the solar system experiments a period of drought
		if (shape.isAlignedWith(SUN)) {
			return createMeasure(day, WeatherType.Drought);
		}
		if (shape.isAligned()) {
			return createMeasure(day, WeatherType.OptimalTemperature); 
		}
		// when the three planets are not aligned, they form a triangle
		// if the sun is inside the triangle, the solar system experiences a period of rain
		if (shape.isInside(SUN)) {
			// Heavy rain when the perimeter of the triangle is at its maximum
			// the first rain is the maximum...
			double perimeter = shape.getPerimeter();
			if (perimeter > HEAVY_RAIN_MAX_PER) {
				HEAVY_RAIN_MAX_PER = perimeter;
				return createMeasure(day,WeatherType.HeavyRain);
			}
			
			return createMeasure(day, WeatherType.Rain);
			
		}
		return createMeasure(day, WeatherType.Other);
	}


}
