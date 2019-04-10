package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.OrdreService;
import com.arsatoll.app.domain.Ordre;
import com.arsatoll.app.repository.OrdreRepository;
import com.arsatoll.app.service.dto.OrdreDTO;
import com.arsatoll.app.service.mapper.OrdreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Ordre.
 */
@Service
@Transactional
public class OrdreServiceImpl implements OrdreService {

    private final Logger log = LoggerFactory.getLogger(OrdreServiceImpl.class);

    private final OrdreRepository ordreRepository;

    private final OrdreMapper ordreMapper;

    public OrdreServiceImpl(OrdreRepository ordreRepository, OrdreMapper ordreMapper) {
        this.ordreRepository = ordreRepository;
        this.ordreMapper = ordreMapper;
    }

    /**
     * Save a ordre.
     *
     * @param ordreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrdreDTO save(OrdreDTO ordreDTO) {
        log.debug("Request to save Ordre : {}", ordreDTO);
        Ordre ordre = ordreMapper.toEntity(ordreDTO);
        ordre = ordreRepository.save(ordre);
        return ordreMapper.toDto(ordre);
    }

    /**
     * Get all the ordres.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrdreDTO> findAll() {
        log.debug("Request to get all Ordres");
        return ordreRepository.findAll().stream()
            .map(ordreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ordre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrdreDTO> findOne(Long id) {
        log.debug("Request to get Ordre : {}", id);
        return ordreRepository.findById(id)
            .map(ordreMapper::toDto);
    }

    /**
     * Delete the ordre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Ordre : {}", id);        ordreRepository.deleteById(id);
    }
}
