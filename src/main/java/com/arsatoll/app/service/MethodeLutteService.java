package com.arsatoll.app.service;

import com.arsatoll.app.domain.MethodeLutte;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MethodeLutte.
 */
public interface MethodeLutteService {

    /**
     * Save a methodeLutte.
     *
     * @param methodeLutte the entity to save
     * @return the persisted entity
     */
    MethodeLutte save(MethodeLutte methodeLutte);

    /**
     * Get all the methodeLuttes.
     *
     * @return the list of entities
     */
    List<MethodeLutte> findAll();


    /**
     * Get the "id" methodeLutte.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MethodeLutte> findOne(Long id);

    /**
     * Delete the "id" methodeLutte.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
