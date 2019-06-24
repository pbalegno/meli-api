package com.meli.solr.api.repository;

import com.meli.solr.api.domain.Measure;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Measure entity.
 */
@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {

}
