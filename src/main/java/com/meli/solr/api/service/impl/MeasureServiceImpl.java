package com.meli.solr.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.domain.enumeration.WeatherType;
import com.meli.solr.api.repository.MeasureRepository;
import com.meli.solr.api.service.MeasureService;


/**
 * Service Implementation for managing {@link Measure}.
 */
@Service
@Transactional
public class MeasureServiceImpl implements MeasureService {

    private final Logger log = LoggerFactory.getLogger(MeasureServiceImpl.class);

    private final MeasureRepository measureRepository;

    public MeasureServiceImpl(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    /**
     * Save a measure.
     *
     * @param measure the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Measure save(Measure measure) {
        log.debug("Request to save Measure : {}", measure);
        return measureRepository.save(measure);
    }

    /**
     * Get all the measures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Measure> findAll(Pageable pageable) {
        log.debug("Request to get all Measures");
        return measureRepository.findAll(pageable);
    }


    /**
     * Get one measure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Measure> findOne(Integer id) {
        log.debug("Request to get Measure : {}", id);
        return measureRepository.findById(id);
    }

    /**
     * Delete the measure by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Measure : {}", id);
        measureRepository.deleteById(id);
    }

	@Override
	@Transactional(readOnly = true)
	@Cacheable(REPORT_CACHE)
	public Map<WeatherType, Long> getReport() {
		List<Object[]> report = measureRepository.getReport();
		return report.stream().collect(Collectors.toMap(x -> (WeatherType)x[0], x-> (Long)x[1]));
	}

	@Override
	public Page<Measure> findAllByWeather(Pageable pageable, WeatherType weather) {
		return measureRepository.findAllByWeatherEquals(pageable,weather);
	}


}
