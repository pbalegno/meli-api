package com.meli.solr.api.service;

import com.meli.solr.api.domain.Location;
import com.meli.solr.api.domain.enumeration.PlanetType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Location}.
 */
public interface LocationService {

    /**
     * Save a location.
     *
     * @param location the entity to save.
     * @return the persisted entity.
     */
    Location save(Location location);

    /**
     * Get all the locations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Location> findAll(Pageable pageable);


    /**
     * Get the "id" location.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Location> findOne(Long id);

    /**
     * Delete the "id" location.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

	Page<Location> findAllByPlanet(Pageable pageable, PlanetType planet);
}
