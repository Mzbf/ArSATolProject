package com.arsatoll.app.service;

import com.arsatoll.app.domain.Insecte;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Insecte.
 */
public interface InsecteService {

    /**
     * Save a insecte.
     *
     * @param insecte the entity to save
     * @return the persisted entity
     */
    Insecte save(Insecte insecte);

    /**
     * Get all the insectes.
     *
     * @return the list of entities
     */
    List<Insecte> findAll();


    /**
     * Get the "id" insecte.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Insecte> findOne(Long id);

    /**
     * Delete the "id" insecte.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
