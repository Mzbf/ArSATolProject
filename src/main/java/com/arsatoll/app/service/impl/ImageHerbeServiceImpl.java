package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ImageHerbeService;
import com.arsatoll.app.domain.ImageHerbe;
import com.arsatoll.app.repository.ImageHerbeRepository;
import com.arsatoll.app.service.dto.ImageHerbeDTO;
import com.arsatoll.app.service.mapper.ImageHerbeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImageHerbe.
 */
@Service
@Transactional
public class ImageHerbeServiceImpl implements ImageHerbeService {

    private final Logger log = LoggerFactory.getLogger(ImageHerbeServiceImpl.class);

    private final ImageHerbeRepository imageHerbeRepository;

    private final ImageHerbeMapper imageHerbeMapper;

    public ImageHerbeServiceImpl(ImageHerbeRepository imageHerbeRepository, ImageHerbeMapper imageHerbeMapper) {
        this.imageHerbeRepository = imageHerbeRepository;
        this.imageHerbeMapper = imageHerbeMapper;
    }

    /**
     * Save a imageHerbe.
     *
     * @param imageHerbeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImageHerbeDTO save(ImageHerbeDTO imageHerbeDTO) {
        log.debug("Request to save ImageHerbe : {}", imageHerbeDTO);
        ImageHerbe imageHerbe = imageHerbeMapper.toEntity(imageHerbeDTO);
        imageHerbe = imageHerbeRepository.save(imageHerbe);
        return imageHerbeMapper.toDto(imageHerbe);
    }

    /**
     * Get all the imageHerbes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImageHerbeDTO> findAll() {
        log.debug("Request to get all ImageHerbes");
        return imageHerbeRepository.findAll().stream()
            .map(imageHerbeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one imageHerbe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImageHerbeDTO> findOne(Long id) {
        log.debug("Request to get ImageHerbe : {}", id);
        return imageHerbeRepository.findById(id)
            .map(imageHerbeMapper::toDto);
    }

    /**
     * Delete the imageHerbe by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageHerbe : {}", id);        imageHerbeRepository.deleteById(id);
    }
}
