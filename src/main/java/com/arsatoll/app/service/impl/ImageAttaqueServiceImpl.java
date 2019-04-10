package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ImageAttaqueService;
import com.arsatoll.app.domain.ImageAttaque;
import com.arsatoll.app.repository.ImageAttaqueRepository;
import com.arsatoll.app.service.dto.ImageAttaqueDTO;
import com.arsatoll.app.service.mapper.ImageAttaqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImageAttaque.
 */
@Service
@Transactional
public class ImageAttaqueServiceImpl implements ImageAttaqueService {

    private final Logger log = LoggerFactory.getLogger(ImageAttaqueServiceImpl.class);

    private final ImageAttaqueRepository imageAttaqueRepository;

    private final ImageAttaqueMapper imageAttaqueMapper;

    public ImageAttaqueServiceImpl(ImageAttaqueRepository imageAttaqueRepository, ImageAttaqueMapper imageAttaqueMapper) {
        this.imageAttaqueRepository = imageAttaqueRepository;
        this.imageAttaqueMapper = imageAttaqueMapper;
    }

    /**
     * Save a imageAttaque.
     *
     * @param imageAttaqueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImageAttaqueDTO save(ImageAttaqueDTO imageAttaqueDTO) {
        log.debug("Request to save ImageAttaque : {}", imageAttaqueDTO);
        ImageAttaque imageAttaque = imageAttaqueMapper.toEntity(imageAttaqueDTO);
        imageAttaque = imageAttaqueRepository.save(imageAttaque);
        return imageAttaqueMapper.toDto(imageAttaque);
    }

    /**
     * Get all the imageAttaques.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImageAttaqueDTO> findAll() {
        log.debug("Request to get all ImageAttaques");
        return imageAttaqueRepository.findAll().stream()
            .map(imageAttaqueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one imageAttaque by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImageAttaqueDTO> findOne(Long id) {
        log.debug("Request to get ImageAttaque : {}", id);
        return imageAttaqueRepository.findById(id)
            .map(imageAttaqueMapper::toDto);
    }

    /**
     * Delete the imageAttaque by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageAttaque : {}", id);        imageAttaqueRepository.deleteById(id);
    }
}
