package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.HerbeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Herbe.
 */
public interface HerbeService {

    /**
     * Save a herbe.
     *
     * @param herbeDTO the entity to save
     * @return the persisted entity
     */
    HerbeDTO save(HerbeDTO herbeDTO);

    /**
     * Get all the herbes.
     *
     * @return the list of entities
     */
    List<HerbeDTO> findAll();


    /**
     * Get the "id" herbe.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HerbeDTO> findOne(Long id);

    /**
     * Delete the "id" herbe.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
