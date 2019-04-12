package com.arsatoll.app.service;

import com.arsatoll.app.domain.enumeration.Localisation;
import com.arsatoll.app.service.dto.AttaqueDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Attaque.
 */
public interface AttaqueService {

    /**
     * Save a attaque.
     *
     * @param attaqueDTO the entity to save
     * @return the persisted entity
     */
    AttaqueDTO save(AttaqueDTO attaqueDTO);

    /**
     * Get all the attaques.
     *
     * @return the list of entities
     */
    List<AttaqueDTO> findAll();


    /**
     * Get the "id" attaque.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AttaqueDTO> findOne(Long id);

    /**
     * Delete the "id" attaque.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<AttaqueDTO> findAttaque(Long id, Localisation local);
}
