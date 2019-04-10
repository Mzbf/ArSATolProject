package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.HerbeService;
import com.arsatoll.app.domain.Herbe;
import com.arsatoll.app.repository.HerbeRepository;
import com.arsatoll.app.service.dto.HerbeDTO;
import com.arsatoll.app.service.mapper.HerbeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Herbe.
 */
@Service
@Transactional
public class HerbeServiceImpl implements HerbeService {

    private final Logger log = LoggerFactory.getLogger(HerbeServiceImpl.class);

    private final HerbeRepository herbeRepository;

    private final HerbeMapper herbeMapper;

    public HerbeServiceImpl(HerbeRepository herbeRepository, HerbeMapper herbeMapper) {
        this.herbeRepository = herbeRepository;
        this.herbeMapper = herbeMapper;
    }

    /**
     * Save a herbe.
     *
     * @param herbeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HerbeDTO save(HerbeDTO herbeDTO) {
        log.debug("Request to save Herbe : {}", herbeDTO);
        Herbe herbe = herbeMapper.toEntity(herbeDTO);
        herbe = herbeRepository.save(herbe);
        return herbeMapper.toDto(herbe);
    }

    /**
     * Get all the herbes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<HerbeDTO> findAll() {
        log.debug("Request to get all Herbes");
        return herbeRepository.findAll().stream()
            .map(herbeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one herbe by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HerbeDTO> findOne(Long id) {
        log.debug("Request to get Herbe : {}", id);
        return herbeRepository.findById(id)
            .map(herbeMapper::toDto);
    }

    /**
     * Delete the herbe by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Herbe : {}", id);        herbeRepository.deleteById(id);
    }
}
