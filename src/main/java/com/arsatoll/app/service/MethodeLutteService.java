package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.MethodeLutteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MethodeLutte.
 */
public interface MethodeLutteService {

    /**
     * Save a methodeLutte.
     *
     * @param methodeLutteDTO the entity to save
     * @return the persisted entity
     */
    MethodeLutteDTO save(MethodeLutteDTO methodeLutteDTO);

    /**
     * Get all the methodeLuttes.
     *
     * @return the list of entities
     */
    List<MethodeLutteDTO> findAll();


    /**
     * Get the "id" methodeLutte.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MethodeLutteDTO> findOne(Long id);

    /**
     * Delete the "id" methodeLutte.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
