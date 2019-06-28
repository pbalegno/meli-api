package com.meli.solr.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.domain.enumeration.WeatherType;


/**
 * Spring Data  repository for the Measure entity.
 */
@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {

	@Query("SELECT m.weather as weather, COUNT(m) FROM Measure m GROUP BY m.weather")
	List<Object[]> getReport();

	Page<Measure> findAllByWeatherEquals(Pageable pageable, WeatherType weather);

}
