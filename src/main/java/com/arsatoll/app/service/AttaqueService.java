package com.arsatoll.app.service;

import com.arsatoll.app.domain.Attaque;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Attaque.
 */
public interface AttaqueService {

    /**
     * Save a attaque.
     *
     * @param attaque the entity to save
     * @return the persisted entity
     */
    Attaque save(Attaque attaque);

    /**
     * Get all the attaques.
     *
     * @return the list of entities
     */
    List<Attaque> findAll();


    /**
     * Get the "id" attaque.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Attaque> findOne(Long id);

    /**
     * Delete the "id" attaque.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
