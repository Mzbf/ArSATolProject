package com.arsatoll.app.service;

import com.arsatoll.app.domain.Chercheur;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Chercheur.
 */
public interface ChercheurService {

    /**
     * Save a chercheur.
     *
     * @param chercheur the entity to save
     * @return the persisted entity
     */
    Chercheur save(Chercheur chercheur);

    /**
     * Get all the chercheurs.
     *
     * @return the list of entities
     */
    List<Chercheur> findAll();


    /**
     * Get the "id" chercheur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Chercheur> findOne(Long id);

    /**
     * Delete the "id" chercheur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
