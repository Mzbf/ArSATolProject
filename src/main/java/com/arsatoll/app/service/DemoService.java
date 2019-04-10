package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.DemoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Demo.
 */
public interface DemoService {

    /**
     * Save a demo.
     *
     * @param demoDTO the entity to save
     * @return the persisted entity
     */
    DemoDTO save(DemoDTO demoDTO);

    /**
     * Get all the demos.
     *
     * @return the list of entities
     */
    List<DemoDTO> findAll();


    /**
     * Get the "id" demo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DemoDTO> findOne(Long id);

    /**
     * Delete the "id" demo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
