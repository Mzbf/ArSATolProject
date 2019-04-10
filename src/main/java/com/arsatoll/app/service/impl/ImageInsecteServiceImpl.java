package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ImageInsecteService;
import com.arsatoll.app.domain.ImageInsecte;
import com.arsatoll.app.repository.ImageInsecteRepository;
import com.arsatoll.app.service.dto.ImageInsecteDTO;
import com.arsatoll.app.service.mapper.ImageInsecteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImageInsecte.
 */
@Service
@Transactional
public class ImageInsecteServiceImpl implements ImageInsecteService {

    private final Logger log = LoggerFactory.getLogger(ImageInsecteServiceImpl.class);

    private final ImageInsecteRepository imageInsecteRepository;

    private final ImageInsecteMapper imageInsecteMapper;

    public ImageInsecteServiceImpl(ImageInsecteRepository imageInsecteRepository, ImageInsecteMapper imageInsecteMapper) {
        this.imageInsecteRepository = imageInsecteRepository;
        this.imageInsecteMapper = imageInsecteMapper;
    }

    /**
     * Save a imageInsecte.
     *
     * @param imageInsecteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImageInsecteDTO save(ImageInsecteDTO imageInsecteDTO) {
        log.debug("Request to save ImageInsecte : {}", imageInsecteDTO);
        ImageInsecte imageInsecte = imageInsecteMapper.toEntity(imageInsecteDTO);
        imageInsecte = imageInsecteRepository.save(imageInsecte);
        return imageInsecteMapper.toDto(imageInsecte);
    }

    /**
     * Get all the imageInsectes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImageInsecteDTO> findAll() {
        log.debug("Request to get all ImageInsectes");
        return imageInsecteRepository.findAll().stream()
            .map(imageInsecteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one imageInsecte by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImageInsecteDTO> findOne(Long id) {
        log.debug("Request to get ImageInsecte : {}", id);
        return imageInsecteRepository.findById(id)
            .map(imageInsecteMapper::toDto);
    }

    /**
     * Delete the imageInsecte by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageInsecte : {}", id);        imageInsecteRepository.deleteById(id);
    }
}
