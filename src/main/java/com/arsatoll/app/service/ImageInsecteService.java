package com.arsatoll.app.service;

import com.arsatoll.app.service.dto.ImageInsecteDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ImageInsecte.
 */
public interface ImageInsecteService {

    /**
     * Save a imageInsecte.
     *
     * @param imageInsecteDTO the entity to save
     * @return the persisted entity
     */
    ImageInsecteDTO save(ImageInsecteDTO imageInsecteDTO);

    /**
     * Get all the imageInsectes.
     *
     * @return the list of entities
     */
    List<ImageInsecteDTO> findAll();


    /**
     * Get the "id" imageInsecte.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ImageInsecteDTO> findOne(Long id);

    /**
     * Delete the "id" imageInsecte.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

}
