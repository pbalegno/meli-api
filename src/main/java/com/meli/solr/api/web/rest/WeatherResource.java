package com.meli.solr.api.web.rest;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.solr.api.domain.enumeration.WeatherType;
import com.meli.solr.api.service.SolrSysService;

/**
 * REST controller for managing {@link com.meli.solr.api.domain.Measure}.
 */
@RestController
@RequestMapping("/api")
public class WeatherResource {

    private final Logger log = LoggerFactory.getLogger(WeatherResource.class);


    @Autowired
    private SolrSysService solrSysService;


    /**
     * {@code GET  /weather/seed}
     * Is the database initialization proces
     */
    @GetMapping("/weather/seed")
    public ResponseEntity<String> seeding() {
        log.debug("REST request to seeding weather");
        solrSysService.init();
        return ResponseEntity.ok().body("Initialize BD...");
    }
    

    /**
     * {@code GET  /weather/report}
     * Report by Weather, you can see preliminary totals 
     */
    @GetMapping("/weather/report")
    ResponseEntity<Map<String, Map<WeatherType, Integer>>> getReport() {
    	return ResponseEntity.ok().body(Collections.singletonMap("report", solrSysService.getReport()));
    }  
    

}
