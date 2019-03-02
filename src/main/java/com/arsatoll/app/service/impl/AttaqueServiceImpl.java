package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.AttaqueService;
import com.arsatoll.app.domain.Attaque;
import com.arsatoll.app.repository.AttaqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Attaque.
 */
@Service
@Transactional
public class AttaqueServiceImpl implements AttaqueService {

    private final Logger log = LoggerFactory.getLogger(AttaqueServiceImpl.class);

    private final AttaqueRepository attaqueRepository;

    public AttaqueServiceImpl(AttaqueRepository attaqueRepository) {
        this.attaqueRepository = attaqueRepository;
    }

    /**
     * Save a attaque.
     *
     * @param attaque the entity to save
     * @return the persisted entity
     */
    @Override
    public Attaque save(Attaque attaque) {
        log.debug("Request to save Attaque : {}", attaque);
        return attaqueRepository.save(attaque);
    }

    /**
     * Get all the attaques.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Attaque> findAll() {
        log.debug("Request to get all Attaques");
        return attaqueRepository.findAll();
    }


    /**
     * Get one attaque by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Attaque> findOne(Long id) {
        log.debug("Request to get Attaque : {}", id);
        return attaqueRepository.findById(id);
    }

    /**
     * Delete the attaque by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Attaque : {}", id);        attaqueRepository.deleteById(id);
    }
}
