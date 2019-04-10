package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.AgriculteurDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Agriculteur.
 */
public interface AgriculteurService {

    /**
     * Save a agriculteur.
     *
     * @param agriculteurDTO the entity to save
     * @return the persisted entity
     */
    AgriculteurDTO save(AgriculteurDTO agriculteurDTO);

    /**
     * Get all the agriculteurs.
     *
     * @return the list of entities
     */
    List<AgriculteurDTO> findAll();


    /**
     * Get the "id" agriculteur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AgriculteurDTO> findOne(Long id);

    /**
     * Delete the "id" agriculteur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
