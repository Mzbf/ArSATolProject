package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.CultureService;
import com.arsatoll.app.domain.Culture;
import com.arsatoll.app.repository.CultureRepository;
import com.arsatoll.app.service.dto.CultureDTO;
import com.arsatoll.app.service.mapper.CultureMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Culture.
 */
@Service
@Transactional
public class CultureServiceImpl implements CultureService {

    private final Logger log = LoggerFactory.getLogger(CultureServiceImpl.class);

    private final CultureRepository cultureRepository;

    private final CultureMapper cultureMapper;

    public CultureServiceImpl(CultureRepository cultureRepository, CultureMapper cultureMapper) {
        this.cultureRepository = cultureRepository;
        this.cultureMapper = cultureMapper;
    }

    /**
     * Save a culture.
     *
     * @param cultureDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CultureDTO save(CultureDTO cultureDTO) {
        log.debug("Request to save Culture : {}", cultureDTO);
        Culture culture = cultureMapper.toEntity(cultureDTO);
        culture = cultureRepository.save(culture);
        return cultureMapper.toDto(culture);
    }

    /**
     * Get all the cultures.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CultureDTO> findAll() {
        log.debug("Request to get all Cultures");
        return cultureRepository.findAllWithEagerRelationships().stream()
            .map(cultureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Culture with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<CultureDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cultureRepository.findAllWithEagerRelationships(pageable).map(cultureMapper::toDto);
    }
    

    /**
     * Get one culture by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CultureDTO> findOne(Long id) {
        log.debug("Request to get Culture : {}", id);
        return cultureRepository.findOneWithEagerRelationships(id)
            .map(cultureMapper::toDto);
    }

    /**
     * Delete the culture by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Culture : {}", id);        cultureRepository.deleteById(id);
    }
}
