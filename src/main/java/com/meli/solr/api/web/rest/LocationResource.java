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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meli.solr.api.domain.Location;
import com.meli.solr.api.domain.enumeration.PlanetType;
import com.meli.solr.api.service.LocationService;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.meli.solr.api.domain.Location}.
 */
@RestController
@RequestMapping("/api")
public class LocationResource {

    private final Logger log = LoggerFactory.getLogger(LocationResource.class);

    @Autowired
    private LocationService locationService;
    
  
	/**
     * {@code GET  /locations} : get all the locations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of locations in body.
     */
    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations(Pageable pageable, @RequestParam (value = "planet") PlanetType planet) {
        log.debug("REST request to get a page of Locations");
        Page<Location> page = locationService.findAllByPlanet(pageable,planet);
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-elements", page.getTotalElements()+"");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /locations/:id} : get the "id" location.
     *
     * @param id the id of the location to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the location, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id) {
        log.debug("REST request to get Location : {}", id);
        Optional<Location> location = locationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(location);
    }
    
    
    
    public LocationResource(LocationService locationService) {
		this.locationService = locationService;
	}
    
   
}
