package com.meli.solr.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.solr.api.domain.Location;
import com.meli.solr.api.domain.enumeration.PlanetType;


/**
 * Spring Data  repository for the Location entity.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
	Page<Location> findAllByPlanetEquals(PlanetType planet, Pageable pageable);

}
