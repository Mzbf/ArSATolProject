package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.OrdreDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Ordre.
 */
public interface OrdreService {

    /**
     * Save a ordre.
     *
     * @param ordreDTO the entity to save
     * @return the persisted entity
     */
    OrdreDTO save(OrdreDTO ordreDTO);

    /**
     * Get all the ordres.
     *
     * @return the list of entities
     */
    List<OrdreDTO> findAll();


    /**
     * Get the "id" ordre.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrdreDTO> findOne(Long id);

    /**
     * Delete the "id" ordre.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
