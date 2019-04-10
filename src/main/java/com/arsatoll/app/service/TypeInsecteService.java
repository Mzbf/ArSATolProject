package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.TypeInsecteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TypeInsecte.
 */
public interface TypeInsecteService {

    /**
     * Save a typeInsecte.
     *
     * @param typeInsecteDTO the entity to save
     * @return the persisted entity
     */
    TypeInsecteDTO save(TypeInsecteDTO typeInsecteDTO);

    /**
     * Get all the typeInsectes.
     *
     * @return the list of entities
     */
    List<TypeInsecteDTO> findAll();


    /**
     * Get the "id" typeInsecte.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TypeInsecteDTO> findOne(Long id);

    /**
     * Delete the "id" typeInsecte.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
