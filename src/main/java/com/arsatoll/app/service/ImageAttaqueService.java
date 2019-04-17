package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.ImageAttaqueDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ImageAttaque.
 */
public interface ImageAttaqueService {

    /**
     * Save a imageAttaque.
     *
     * @param imageAttaqueDTO the entity to save
     * @return the persisted entity
     */
    ImageAttaqueDTO save(ImageAttaqueDTO imageAttaqueDTO);

    /**
     * Get all the imageAttaques.
     *
     * @return the list of entities
     */
    List<ImageAttaqueDTO> findAll();


    /**
     * Get the "id" imageAttaque.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ImageAttaqueDTO> findOne(Long id);

    /**
     * Delete the "id" imageAttaque.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<ImageAttaqueDTO> listImageAttaque(Long id);
}
