package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.ImageCultureDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ImageCulture.
 */
public interface ImageCultureService {

    /**
     * Save a imageCulture.
     *
     * @param imageCultureDTO the entity to save
     * @return the persisted entity
     */
    ImageCultureDTO save(ImageCultureDTO imageCultureDTO);

    /**
     * Get all the imageCultures.
     *
     * @return the list of entities
     */
    List<ImageCultureDTO> findAll();


    /**
     * Get the "id" imageCulture.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ImageCultureDTO> findOne(Long id);

    /**
     * Delete the "id" imageCulture.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
