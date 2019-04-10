package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.ZoneGeoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ZoneGeo.
 */
public interface ZoneGeoService {

    /**
     * Save a zoneGeo.
     *
     * @param zoneGeoDTO the entity to save
     * @return the persisted entity
     */
    ZoneGeoDTO save(ZoneGeoDTO zoneGeoDTO);

    /**
     * Get all the zoneGeos.
     *
     * @return the list of entities
     */
    List<ZoneGeoDTO> findAll();


    /**
     * Get the "id" zoneGeo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ZoneGeoDTO> findOne(Long id);

    /**
     * Delete the "id" zoneGeo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
