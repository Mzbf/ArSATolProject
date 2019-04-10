package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ImageCultureService;
import com.arsatoll.app.domain.ImageCulture;
import com.arsatoll.app.repository.ImageCultureRepository;
import com.arsatoll.app.service.dto.ImageCultureDTO;
import com.arsatoll.app.service.mapper.ImageCultureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImageCulture.
 */
@Service
@Transactional
public class ImageCultureServiceImpl implements ImageCultureService {

    private final Logger log = LoggerFactory.getLogger(ImageCultureServiceImpl.class);

    private final ImageCultureRepository imageCultureRepository;

    private final ImageCultureMapper imageCultureMapper;

    public ImageCultureServiceImpl(ImageCultureRepository imageCultureRepository, ImageCultureMapper imageCultureMapper) {
        this.imageCultureRepository = imageCultureRepository;
        this.imageCultureMapper = imageCultureMapper;
    }

    /**
     * Save a imageCulture.
     *
     * @param imageCultureDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImageCultureDTO save(ImageCultureDTO imageCultureDTO) {
        log.debug("Request to save ImageCulture : {}", imageCultureDTO);
        ImageCulture imageCulture = imageCultureMapper.toEntity(imageCultureDTO);
        imageCulture = imageCultureRepository.save(imageCulture);
        return imageCultureMapper.toDto(imageCulture);
    }

    /**
     * Get all the imageCultures.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImageCultureDTO> findAll() {
        log.debug("Request to get all ImageCultures");
        return imageCultureRepository.findAll().stream()
            .map(imageCultureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one imageCulture by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImageCultureDTO> findOne(Long id) {
        log.debug("Request to get ImageCulture : {}", id);
        return imageCultureRepository.findById(id)
            .map(imageCultureMapper::toDto);
    }

    /**
     * Delete the imageCulture by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageCulture : {}", id);        imageCultureRepository.deleteById(id);
    }
}
