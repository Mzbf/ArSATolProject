package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.InsecteService;
import com.arsatoll.app.domain.Insecte;
import com.arsatoll.app.repository.InsecteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Insecte.
 */
@Service
@Transactional
public class InsecteServiceImpl implements InsecteService {

    private final Logger log = LoggerFactory.getLogger(InsecteServiceImpl.class);

    private final InsecteRepository insecteRepository;

    public InsecteServiceImpl(InsecteRepository insecteRepository) {
        this.insecteRepository = insecteRepository;
    }

    /**
     * Save a insecte.
     *
     * @param insecte the entity to save
     * @return the persisted entity
     */
    @Override
    public Insecte save(Insecte insecte) {
        log.debug("Request to save Insecte : {}", insecte);
        return insecteRepository.save(insecte);
    }

    /**
     * Get all the insectes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Insecte> findAll() {
        log.debug("Request to get all Insectes");
        return insecteRepository.findAll();
    }


    /**
     * Get one insecte by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Insecte> findOne(Long id) {
        log.debug("Request to get Insecte : {}", id);
        return insecteRepository.findById(id);
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
