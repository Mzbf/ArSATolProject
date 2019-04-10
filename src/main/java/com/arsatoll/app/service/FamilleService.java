package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.FamilleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Famille.
 */
public interface FamilleService {

    /**
     * Save a famille.
     *
     * @param familleDTO the entity to save
     * @return the persisted entity
     */
    FamilleDTO save(FamilleDTO familleDTO);

    /**
     * Get all the familles.
     *
     * @return the list of entities
     */
    List<FamilleDTO> findAll();


    /**
     * Get the "id" famille.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FamilleDTO> findOne(Long id);

    /**
     * Delete the "id" famille.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
