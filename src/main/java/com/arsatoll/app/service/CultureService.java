package com.arsatoll.app.service;

import com.arsatoll.app.domain.Culture;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Culture.
 */
public interface CultureService {

    /**
     * Save a culture.
     *
     * @param culture the entity to save
     * @return the persisted entity
     */
    Culture save(Culture culture);

    /**
     * Get all the cultures.
     *
     * @return the list of entities
     */
    List<Culture> findAll();


    /**
     * Get the "id" culture.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Culture> findOne(Long id);

    /**
     * Delete the "id" culture.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
