package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ImageEnvoyeService;
import com.arsatoll.app.domain.ImageEnvoye;
import com.arsatoll.app.repository.ImageEnvoyeRepository;
import com.arsatoll.app.service.dto.ImageEnvoyeDTO;
import com.arsatoll.app.service.mapper.ImageEnvoyeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImageEnvoye.
 */
@Service
@Transactional
public class ImageEnvoyeServiceImpl implements ImageEnvoyeService {

    private final Logger log = LoggerFactory.getLogger(ImageEnvoyeServiceImpl.class);

    private final ImageEnvoyeRepository imageEnvoyeRepository;

    private final ImageEnvoyeMapper imageEnvoyeMapper;

    public ImageEnvoyeServiceImpl(ImageEnvoyeRepository imageEnvoyeRepository, ImageEnvoyeMapper imageEnvoyeMapper) {
        this.imageEnvoyeRepository = imageEnvoyeRepository;
        this.imageEnvoyeMapper = imageEnvoyeMapper;
    }

    /**
     * Save a imageEnvoye.
     *
     * @param imageEnvoyeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImageEnvoyeDTO save(ImageEnvoyeDTO imageEnvoyeDTO) {
        log.debug("Request to save ImageEnvoye : {}", imageEnvoyeDTO);
        ImageEnvoye imageEnvoye = imageEnvoyeMapper.toEntity(imageEnvoyeDTO);
        imageEnvoye = imageEnvoyeRepository.save(imageEnvoye);
        return imageEnvoyeMapper.toDto(imageEnvoye);
    }

    /**
     * Get all the imageEnvoyes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImageEnvoyeDTO> findAll() {
        log.debug("Request to get all ImageEnvoyes");
        return imageEnvoyeRepository.findAll().stream()
            .map(imageEnvoyeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one imageEnvoye by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImageEnvoyeDTO> findOne(Long id) {
        log.debug("Request to get ImageEnvoye : {}", id);
        return imageEnvoyeRepository.findById(id)
            .map(imageEnvoyeMapper::toDto);
    }

    /**
     * Delete the imageEnvoye by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageEnvoye : {}", id);        imageEnvoyeRepository.deleteById(id);
    }
}
