package com.meli.solr.api.service;

import java.util.Map;

import com.meli.solr.api.domain.enumeration.WeatherType;

public interface SolrSysService {

	void init();

	Map<WeatherType, Long> getReport();

}
