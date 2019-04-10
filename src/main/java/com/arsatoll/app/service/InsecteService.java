package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.InsecteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Insecte.
 */
public interface InsecteService {

    /**
     * Save a insecte.
     *
     * @param insecteDTO the entity to save
     * @return the persisted entity
     */
    InsecteDTO save(InsecteDTO insecteDTO);

    /**
     * Get all the insectes.
     *
     * @return the list of entities
     */
    List<InsecteDTO> findAll();


    /**
     * Get the "id" insecte.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InsecteDTO> findOne(Long id);

    /**
     * Delete the "id" insecte.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
