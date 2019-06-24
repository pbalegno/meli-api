package com.meli.solr.api.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.domain.enumeration.WeatherType;
import com.meli.solr.api.service.ForecastService;
import com.meli.solr.api.service.MeasureService;
import com.meli.solr.api.service.SolrSysService;

@Service
public class SolrSysServiceImpl implements SolrSysService {

	@Autowired
	private MeasureService measureService;

	@Autowired
	private ForecastService forecastService;

    private final Logger log = LoggerFactory.getLogger(SolrSysServiceImpl.class);

    
    public Map<WeatherType, Integer> getReport() {
		return report;
	}


	private static Map<WeatherType, Integer> report = new HashMap<WeatherType, Integer>();
	static {
		report.put(WeatherType.Drought,0);
		report.put(WeatherType.OptimalTemperature,0);
		report.put(WeatherType.HeavyRain,0);
		report.put(WeatherType.Rain,0);
		report.put(WeatherType.Other,0);
	}
    
	public void init() {
		StopWatch watch = new StopWatch();
		watch.start();
		int days = 360;
		int years = 10;
		for (int day = 1; day <= years * days; day++) {
			Measure measure = forecastService.getMeasure(day);
			measureService.save(measure);
			updateReport(measure);
		}
		
		watch.stop();
		log.debug("Seeding total time " + watch.getTotalTimeSeconds() +" seconds.");
		log.debug("=======REPORT=====");
		report.forEach((k,v) -> log.debug("Weather: " + k + " ---> total: " + v));
//		log.debug("Heavy Rain day is: " +  forecastService.getHeavyRainMaxDay());
		
	}

	private void updateReport(Measure measure) {
		Integer count = report.get(measure.getWeather());
		report.put(measure.getWeather(), count + 1);
	}
	
	

}
