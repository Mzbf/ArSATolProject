package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ChercheurService;
import com.arsatoll.app.domain.Chercheur;
import com.arsatoll.app.repository.ChercheurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Chercheur.
 */
@Service
@Transactional
public class ChercheurServiceImpl implements ChercheurService {

    private final Logger log = LoggerFactory.getLogger(ChercheurServiceImpl.class);

    private final ChercheurRepository chercheurRepository;

    public ChercheurServiceImpl(ChercheurRepository chercheurRepository) {
        this.chercheurRepository = chercheurRepository;
    }

    /**
     * Save a chercheur.
     *
     * @param chercheur the entity to save
     * @return the persisted entity
     */
    @Override
    public Chercheur save(Chercheur chercheur) {
        log.debug("Request to save Chercheur : {}", chercheur);
        return chercheurRepository.save(chercheur);
    }

    /**
     * Get all the chercheurs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Chercheur> findAll() {
        log.debug("Request to get all Chercheurs");
        return chercheurRepository.findAll();
    }


    /**
     * Get one chercheur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Chercheur> findOne(Long id) {
        log.debug("Request to get Chercheur : {}", id);
        return chercheurRepository.findById(id);
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
