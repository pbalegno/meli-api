package com.meli.solr.api.batch;

import org.springframework.beans.factory.annotation.Autowired;

import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.service.ForecastService;
import com.meli.solr.api.service.MeasureService;

public class PlanetBatch {

	@Autowired
	private MeasureService measureService;

	@Autowired
	private ForecastService forecastService;

	public void init() {
		int days = 360;
		int years = 1;
		for (int day = 1; day < years * days; day++) {
			Measure measure = forecastService.getMeasure(day);
			measureService.save(measure);
		}
	}

}
