package com.arsatoll.app.service.impl;

import com.arsatoll.app.repository.InsecteRepository;
import com.arsatoll.app.service.InsecteService;
import com.arsatoll.app.domain.Insecte;
import com.arsatoll.app.service.dto.InsecteDTO;
import com.arsatoll.app.service.mapper.InsecteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Insecte.
 */
@Service
@Transactional
public class InsecteServiceImpl implements InsecteService {

    private final Logger log = LoggerFactory.getLogger(InsecteServiceImpl.class);

    private final InsecteRepository insecteRepository;

    private final InsecteMapper insecteMapper;

    public InsecteServiceImpl(InsecteRepository insecteRepository, InsecteMapper insecteMapper) {
        this.insecteRepository = insecteRepository;
        this.insecteMapper = insecteMapper;
    }

    /**
     * Save a insecte.
     *
     * @param insecteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InsecteDTO save(InsecteDTO insecteDTO) {
        log.debug("Request to save Insecte : {}", insecteDTO);
        Insecte insecte = insecteMapper.toEntity(insecteDTO);
        insecte = insecteRepository.save(insecte);
        return insecteMapper.toDto(insecte);
    }

    /**
     * Get all the insectes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<InsecteDTO> findAll() {
        log.debug("Request to get all Insectes");
        return insecteRepository.findAll().stream()
            .map(insecteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one insecte by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InsecteDTO> findOne(Long id) {
        log.debug("Request to get Insecte : {}", id);
        return insecteRepository.findById(id)
            .map(insecteMapper::toDto);
    }

    /**
     * Delete the insecte by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Insecte : {}", id);        insecteRepository.deleteById(id);
    }
}
