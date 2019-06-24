package com.meli.solr.api.service;

import com.meli.solr.api.domain.Measure;

public interface ForecastService {

	Measure getMeasure(Integer day);
//	Integer getHeavyRainMaxDay();

}
