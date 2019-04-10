package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.ImageEnvoyeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ImageEnvoye.
 */
public interface ImageEnvoyeService {

    /**
     * Save a imageEnvoye.
     *
     * @param imageEnvoyeDTO the entity to save
     * @return the persisted entity
     */
    ImageEnvoyeDTO save(ImageEnvoyeDTO imageEnvoyeDTO);

    /**
     * Get all the imageEnvoyes.
     *
     * @return the list of entities
     */
    List<ImageEnvoyeDTO> findAll();


    /**
     * Get the "id" imageEnvoye.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ImageEnvoyeDTO> findOne(Long id);

    /**
     * Delete the "id" imageEnvoye.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
