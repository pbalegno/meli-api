package com.meli.solr.api.web.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.domain.enumeration.PlanetType;
import com.meli.solr.api.domain.enumeration.WeatherType;
import com.meli.solr.api.service.MeasureService;
import com.meli.solr.api.service.SolrSysService;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.meli.solr.api.domain.Measure}.
 */
@RestController
@RequestMapping("/api")
public class MeasureResource {

    private final Logger log = LoggerFactory.getLogger(MeasureResource.class);

    @Autowired
    private MeasureService measureService;

    @Autowired
    private SolrSysService solrSysService;

	/**
     * {@code GET  /measures} : get all the measures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of measures in body.
     */
    @GetMapping("/measures")
    public ResponseEntity<List<Measure>> getAllMeasures(Pageable pageable) {
        log.debug("REST request to get a page of Measures");
        Page<Measure> page = measureService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /measures/search/?:day} : get the day measure.
     *
     * @param day the day of the measure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the measure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/measures/search")
    public ResponseEntity<Measure> getMeasure( @RequestParam (value = "day") Integer day) {
        log.debug("REST request to get Measure for day: {}", day);
        Optional<Measure> measure = measureService.findOne(day);
        return ResponseUtil.wrapOrNotFound(measure);
    }
    

    /**
     * {@code GET  /weather/getReport}
     * Report by Weather, you can see preliminary totals 
     */
    @GetMapping("/measures/getReport")
    ResponseEntity<Map<String, Map<WeatherType, Long>>> getReport() {
    	return ResponseEntity.ok().body(Collections.singletonMap("report", solrSysService.getReport()));
    }  
    
    
    
    public MeasureResource(MeasureService measureService) {
    	this.measureService = measureService;
	}
    

}
