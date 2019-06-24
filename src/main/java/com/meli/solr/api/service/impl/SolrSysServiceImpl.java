package com.meli.solr.api.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.domain.enumeration.WeatherType;
import com.meli.solr.api.service.ForecastService;
import com.meli.solr.api.service.MeasureService;
import com.meli.solr.api.service.SolrSysService;

import static com.meli.solr.api.domain.Constants.*;

@Service
public class SolrSysServiceImpl implements SolrSysService {

	@Autowired
	private MeasureService measureService;

	@Autowired
	private ForecastService forecastService;

	private AtomicBoolean initialized = new AtomicBoolean(false);
	private Map<WeatherType, Integer> report = null;	

	private final Logger log = LoggerFactory.getLogger(SolrSysServiceImpl.class);


	@Async("taskExecutor")
	public void init() {
		if (initialized.compareAndSet(false, true)) {
			initialize();
		}
	}

	private void initialize() {
		StopWatch watch = new StopWatch();
		watch.start();
		initializeReport();
		seedingBD();
		log.debug("Seeding total time " + watch.getTotalTimeSeconds() + " seconds.");
		watch.stop();
		initialized.set(false);
	}

	private void initializeReport() {
		log.debug("initialize report...");
		report = new HashMap<WeatherType, Integer>();
		report.put(WeatherType.Drought, 0);
		report.put(WeatherType.OptimalTemperature, 0);
		report.put(WeatherType.HeavyRain, 0);
		report.put(WeatherType.Rain, 0);
		report.put(WeatherType.Other, 0);
	}

	private void seedingBD() {
		for (int day = 1; day <= YEARS * DAYS; day++) {
			Measure measure = saveMeasure(day);
			updateReport(measure);
		}
	}

	private Measure saveMeasure(int day) {
		Measure measure = forecastService.getMeasure(day);
		measureService.save(measure);
		return measure;
	}

	private void updateReport(Measure measure) {
		Integer count = report.get(measure.getWeather());
		report.put(measure.getWeather(), count + 1);
	}

	public AtomicBoolean getInitialized() {
		return initialized;
	}

	public void setInitialized(AtomicBoolean initialized) {
		this.initialized = initialized;
	}

	public Map<WeatherType, Integer> getReport() {
		return report;
	}

}
