package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.MaladieDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Maladie.
 */
public interface MaladieService {

    /**
     * Save a maladie.
     *
     * @param maladieDTO the entity to save
     * @return the persisted entity
     */
    MaladieDTO save(MaladieDTO maladieDTO);

    /**
     * Get all the maladies.
     *
     * @return the list of entities
     */
    List<MaladieDTO> findAll();


    /**
     * Get the "id" maladie.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MaladieDTO> findOne(Long id);

    /**
     * Delete the "id" maladie.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
