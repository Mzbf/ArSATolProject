package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ChercheurService;
import com.arsatoll.app.domain.Chercheur;
import com.arsatoll.app.repository.ChercheurRepository;
import com.arsatoll.app.service.dto.ChercheurDTO;
import com.arsatoll.app.service.mapper.ChercheurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Chercheur.
 */
@Service
@Transactional
public class ChercheurServiceImpl implements ChercheurService {

    private final Logger log = LoggerFactory.getLogger(ChercheurServiceImpl.class);

    private final ChercheurRepository chercheurRepository;

    private final ChercheurMapper chercheurMapper;

    public ChercheurServiceImpl(ChercheurRepository chercheurRepository, ChercheurMapper chercheurMapper) {
        this.chercheurRepository = chercheurRepository;
        this.chercheurMapper = chercheurMapper;
    }

    /**
     * Save a chercheur.
     *
     * @param chercheurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ChercheurDTO save(ChercheurDTO chercheurDTO) {
        log.debug("Request to save Chercheur : {}", chercheurDTO);
        Chercheur chercheur = chercheurMapper.toEntity(chercheurDTO);
        chercheur = chercheurRepository.save(chercheur);
        return chercheurMapper.toDto(chercheur);
    }

    /**
     * Get all the chercheurs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ChercheurDTO> findAll() {
        log.debug("Request to get all Chercheurs");
        return chercheurRepository.findAll().stream()
            .map(chercheurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one chercheur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ChercheurDTO> findOne(Long id) {
        log.debug("Request to get Chercheur : {}", id);
        return chercheurRepository.findById(id)
            .map(chercheurMapper::toDto);
    }

    /**
     * Delete the chercheur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Chercheur : {}", id);        chercheurRepository.deleteById(id);
    }
}
