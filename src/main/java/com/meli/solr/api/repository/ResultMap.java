package com.meli.solr.api.repository;

import com.meli.solr.api.domain.enumeration.WeatherType;

public class ResultMap {

	private WeatherType type;
	private Long count;

	public ResultMap(WeatherType type, Long count) {
		this.type = type;
		this.count = count;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public WeatherType getType() {
		return type;
	}

	public void setType(WeatherType type) {
		this.type = type;
	}

}
