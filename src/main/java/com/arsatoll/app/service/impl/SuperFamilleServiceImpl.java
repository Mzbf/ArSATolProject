package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.SuperFamilleService;
import com.arsatoll.app.domain.SuperFamille;
import com.arsatoll.app.repository.SuperFamilleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SuperFamille.
 */
@Service
@Transactional
public class SuperFamilleServiceImpl implements SuperFamilleService {

    private final Logger log = LoggerFactory.getLogger(SuperFamilleServiceImpl.class);

    private final SuperFamilleRepository superFamilleRepository;

    public SuperFamilleServiceImpl(SuperFamilleRepository superFamilleRepository) {
        this.superFamilleRepository = superFamilleRepository;
    }

    /**
     * Save a superFamille.
     *
     * @param superFamille the entity to save
     * @return the persisted entity
     */
    @Override
    public SuperFamille save(SuperFamille superFamille) {
        log.debug("Request to save SuperFamille : {}", superFamille);
        return superFamilleRepository.save(superFamille);
    }

    /**
     * Get all the superFamilles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SuperFamille> findAll() {
        log.debug("Request to get all SuperFamilles");
        return superFamilleRepository.findAll();
    }


    /**
     * Get one superFamille by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SuperFamille> findOne(Long id) {
        log.debug("Request to get SuperFamille : {}", id);
        return superFamilleRepository.findById(id);
    }

    /**
     * Delete the superFamille by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SuperFamille : {}", id);        superFamilleRepository.deleteById(id);
    }
}
