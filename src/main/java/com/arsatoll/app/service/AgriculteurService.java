package com.arsatoll.app.service;

import com.arsatoll.app.domain.Agriculteur;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Agriculteur.
 */
public interface AgriculteurService {

    /**
     * Save a agriculteur.
     *
     * @param agriculteur the entity to save
     * @return the persisted entity
     */
    Agriculteur save(Agriculteur agriculteur);

    /**
     * Get all the agriculteurs.
     *
     * @return the list of entities
     */
    List<Agriculteur> findAll();


    /**
     * Get the "id" agriculteur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Agriculteur> findOne(Long id);

    /**
     * Delete the "id" agriculteur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
