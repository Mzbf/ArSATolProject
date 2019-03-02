package com.arsatoll.app.service;

import com.arsatoll.app.domain.SuperFamille;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SuperFamille.
 */
public interface SuperFamilleService {

    /**
     * Save a superFamille.
     *
     * @param superFamille the entity to save
     * @return the persisted entity
     */
    SuperFamille save(SuperFamille superFamille);

    /**
     * Get all the superFamilles.
     *
     * @return the list of entities
     */
    List<SuperFamille> findAll();


    /**
     * Get the "id" superFamille.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SuperFamille> findOne(Long id);

    /**
     * Delete the "id" superFamille.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
