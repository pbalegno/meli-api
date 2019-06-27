package com.meli.solr.api.service.impl;

import static com.meli.solr.api.domain.Constants.DAYS;
import static com.meli.solr.api.domain.Constants.YEARS;

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

@Service
public class SolrSysServiceImpl implements SolrSysService {

	@Autowired
	private MeasureService measureService;

	@Autowired
	private ForecastService forecastService;

	private AtomicBoolean initialized = new AtomicBoolean(false);

	private final Logger log = LoggerFactory.getLogger(SolrSysServiceImpl.class);

	

	
	@Async("taskExecutor")
	public void init() {
		initialize();
	}

	private void initialize() {
		if (initialized.compareAndSet(false, true)) {
			StopWatch watch = new StopWatch();
			watch.start();
			seedingBD();
			watch.stop();
			log.debug("Seeding total time " + watch.getTotalTimeSeconds() + " seconds.");
			initialized.set(false);
		}
	}

	private void seedingBD() {
		for (int day = 1; day <= YEARS * DAYS; day++) {			
			saveMeasure(day);
		}
	}

	private Measure saveMeasure(int day) {
		Measure measure = forecastService.getMeasure(day);
		measureService.save(measure);
		return measure;
	}

	public AtomicBoolean getInitialized() {
		return initialized;
	}

	public void setInitialized(AtomicBoolean initialized) {
		this.initialized = initialized;
	}

	public Map<WeatherType, Long> getReport() {
		if (!initialized.get()) {
			return measureService.getReport();
		}	
		return null;
	}

}
