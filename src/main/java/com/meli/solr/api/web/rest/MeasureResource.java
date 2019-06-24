package com.meli.solr.api.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.meli.solr.api.domain.Measure;
import com.meli.solr.api.service.MeasureService;

import io.github.jhipster.web.util.PaginationUtil;
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
    

	/**
     * {@code GET  /measures} : get all the measures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of measures in body.
     */
    @GetMapping("/measures")
    public ResponseEntity<List<Measure>> getAllMeasures(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of Measures");
        Page<Measure> page = measureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /measures/:day} : get the "id" measure.
     *
     * @param day the day of the measure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the measure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/measures/{day}")
    public ResponseEntity<Measure> getMeasure(@PathVariable Integer day) {
        log.debug("REST request to get Measure for day: {}", day);
        Optional<Measure> measure = measureService.findOne(day);
        return ResponseUtil.wrapOrNotFound(measure);
    }
    
    
    public MeasureResource(MeasureService measureService) {
    	this.measureService = measureService;
	}
    

}
