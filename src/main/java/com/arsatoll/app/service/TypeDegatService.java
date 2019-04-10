package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.TypeDegatDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TypeDegat.
 */
public interface TypeDegatService {

    /**
     * Save a typeDegat.
     *
     * @param typeDegatDTO the entity to save
     * @return the persisted entity
     */
    TypeDegatDTO save(TypeDegatDTO typeDegatDTO);

    /**
     * Get all the typeDegats.
     *
     * @return the list of entities
     */
    List<TypeDegatDTO> findAll();


    /**
     * Get the "id" typeDegat.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeDegatDTO> findOne(Long id);

    /**
     * Delete the "id" typeDegat.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
