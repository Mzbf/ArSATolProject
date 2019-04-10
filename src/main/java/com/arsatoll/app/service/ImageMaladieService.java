package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.ImageMaladieDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ImageMaladie.
 */
public interface ImageMaladieService {

    /**
     * Save a imageMaladie.
     *
     * @param imageMaladieDTO the entity to save
     * @return the persisted entity
     */
    ImageMaladieDTO save(ImageMaladieDTO imageMaladieDTO);

    /**
     * Get all the imageMaladies.
     *
     * @return the list of entities
     */
    List<ImageMaladieDTO> findAll();


    /**
     * Get the "id" imageMaladie.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ImageMaladieDTO> findOne(Long id);

    /**
     * Delete the "id" imageMaladie.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
