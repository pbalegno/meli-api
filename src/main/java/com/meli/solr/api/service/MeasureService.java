package com.meli.solr.api.service;

import com.meli.solr.api.domain.Measure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Measure}.
 */
public interface MeasureService {

    /**
     * Save a measure.
     *
     * @param measure the entity to save.
     * @return the persisted entity.
     */
    Measure save(Measure measure);

    /**
     * Get all the measures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Measure> findAll(Pageable pageable);


    /**
     * Get the "id" measure.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Measure> findOne(Integer id);

    /**
     * Delete the "id" measure.
     *
     * @param id the id of the entity.
     */
    void delete(Integer id);
}
