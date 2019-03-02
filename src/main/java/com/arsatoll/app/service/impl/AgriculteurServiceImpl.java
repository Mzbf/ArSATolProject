package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.AgriculteurService;
import com.arsatoll.app.domain.Agriculteur;
import com.arsatoll.app.repository.AgriculteurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Agriculteur.
 */
@Service
@Transactional
public class AgriculteurServiceImpl implements AgriculteurService {

    private final Logger log = LoggerFactory.getLogger(AgriculteurServiceImpl.class);

    private final AgriculteurRepository agriculteurRepository;

    public AgriculteurServiceImpl(AgriculteurRepository agriculteurRepository) {
        this.agriculteurRepository = agriculteurRepository;
    }

    /**
     * Save a agriculteur.
     *
     * @param agriculteur the entity to save
     * @return the persisted entity
     */
    @Override
    public Agriculteur save(Agriculteur agriculteur) {
        log.debug("Request to save Agriculteur : {}", agriculteur);
        return agriculteurRepository.save(agriculteur);
    }

    /**
     * Get all the agriculteurs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Agriculteur> findAll() {
        log.debug("Request to get all Agriculteurs");
        return agriculteurRepository.findAll();
    }


    /**
     * Get one agriculteur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Agriculteur> findOne(Long id) {
        log.debug("Request to get Agriculteur : {}", id);
        return agriculteurRepository.findById(id);
    }

    /**
     * Delete the agriculteur by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Agriculteur : {}", id);        agriculteurRepository.deleteById(id);
    }
}
