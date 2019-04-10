package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.TypeCultureDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TypeCulture.
 */
public interface TypeCultureService {

    /**
     * Save a typeCulture.
     *
     * @param typeCultureDTO the entity to save
     * @return the persisted entity
     */
    TypeCultureDTO save(TypeCultureDTO typeCultureDTO);

    /**
     * Get all the typeCultures.
     *
     * @return the list of entities
     */
    List<TypeCultureDTO> findAll();


    /**
     * Get the "id" typeCulture.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeCultureDTO> findOne(Long id);

    /**
     * Delete the "id" typeCulture.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
