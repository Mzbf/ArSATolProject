package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.ImageHerbeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ImageHerbe.
 */
public interface ImageHerbeService {

    /**
     * Save a imageHerbe.
     *
     * @param imageHerbeDTO the entity to save
     * @return the persisted entity
     */
    ImageHerbeDTO save(ImageHerbeDTO imageHerbeDTO);

    /**
     * Get all the imageHerbes.
     *
     * @return the list of entities
     */
    List<ImageHerbeDTO> findAll();


    /**
     * Get the "id" imageHerbe.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ImageHerbeDTO> findOne(Long id);

    /**
     * Delete the "id" imageHerbe.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
