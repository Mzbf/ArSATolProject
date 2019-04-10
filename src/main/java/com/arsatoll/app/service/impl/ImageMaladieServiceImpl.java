package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ImageMaladieService;
import com.arsatoll.app.domain.ImageMaladie;
import com.arsatoll.app.repository.ImageMaladieRepository;
import com.arsatoll.app.service.dto.ImageMaladieDTO;
import com.arsatoll.app.service.mapper.ImageMaladieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImageMaladie.
 */
@Service
@Transactional
public class ImageMaladieServiceImpl implements ImageMaladieService {

    private final Logger log = LoggerFactory.getLogger(ImageMaladieServiceImpl.class);

    private final ImageMaladieRepository imageMaladieRepository;

    private final ImageMaladieMapper imageMaladieMapper;

    public ImageMaladieServiceImpl(ImageMaladieRepository imageMaladieRepository, ImageMaladieMapper imageMaladieMapper) {
        this.imageMaladieRepository = imageMaladieRepository;
        this.imageMaladieMapper = imageMaladieMapper;
    }

    /**
     * Save a imageMaladie.
     *
     * @param imageMaladieDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImageMaladieDTO save(ImageMaladieDTO imageMaladieDTO) {
        log.debug("Request to save ImageMaladie : {}", imageMaladieDTO);
        ImageMaladie imageMaladie = imageMaladieMapper.toEntity(imageMaladieDTO);
        imageMaladie = imageMaladieRepository.save(imageMaladie);
        return imageMaladieMapper.toDto(imageMaladie);
    }

    /**
     * Get all the imageMaladies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImageMaladieDTO> findAll() {
        log.debug("Request to get all ImageMaladies");
        return imageMaladieRepository.findAll().stream()
            .map(imageMaladieMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one imageMaladie by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImageMaladieDTO> findOne(Long id) {
        log.debug("Request to get ImageMaladie : {}", id);
        return imageMaladieRepository.findById(id)
            .map(imageMaladieMapper::toDto);
    }

    /**
     * Delete the imageMaladie by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageMaladie : {}", id);        imageMaladieRepository.deleteById(id);
    }
}
