package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.CultureDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Culture.
 */
public interface CultureService {

    /**
     * Save a culture.
     *
     * @param cultureDTO the entity to save
     * @return the persisted entity
     */
    CultureDTO save(CultureDTO cultureDTO);

    /**
     * Get all the cultures.
     *
     * @return the list of entities
     */
    List<CultureDTO> findAll();

    /**
     * Get all the Culture with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<CultureDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" culture.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CultureDTO> findOne(Long id);

    /**
     * Delete the "id" culture.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<Object> cultureImage();
}
