package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.ChercheurDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Chercheur.
 */
public interface ChercheurService {

    /**
     * Save a chercheur.
     *
     * @param chercheurDTO the entity to save
     * @return the persisted entity
     */
    ChercheurDTO save(ChercheurDTO chercheurDTO);

    /**
     * Get all the chercheurs.
     *
     * @return the list of entities
     */
    List<ChercheurDTO> findAll();


    /**
     * Get the "id" chercheur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ChercheurDTO> findOne(Long id);

    /**
     * Delete the "id" chercheur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
