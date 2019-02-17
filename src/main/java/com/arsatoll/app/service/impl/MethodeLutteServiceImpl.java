package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.MethodeLutteService;
import com.arsatoll.app.domain.MethodeLutte;
import com.arsatoll.app.repository.MethodeLutteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing MethodeLutte.
 */
@Service
@Transactional
public class MethodeLutteServiceImpl implements MethodeLutteService {

    private final Logger log = LoggerFactory.getLogger(MethodeLutteServiceImpl.class);

    private final MethodeLutteRepository methodeLutteRepository;

    public MethodeLutteServiceImpl(MethodeLutteRepository methodeLutteRepository) {
        this.methodeLutteRepository = methodeLutteRepository;
    }

    /**
     * Save a methodeLutte.
     *
     * @param methodeLutte the entity to save
     * @return the persisted entity
     */
    @Override
    public MethodeLutte save(MethodeLutte methodeLutte) {
        log.debug("Request to save MethodeLutte : {}", methodeLutte);
        return methodeLutteRepository.save(methodeLutte);
    }

    /**
     * Get all the methodeLuttes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MethodeLutte> findAll() {
        log.debug("Request to get all MethodeLuttes");
        return methodeLutteRepository.findAll();
    }


    /**
     * Get one methodeLutte by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MethodeLutte> findOne(Long id) {
        log.debug("Request to get MethodeLutte : {}", id);
        return methodeLutteRepository.findById(id);
    }

    /**
     * Delete the methodeLutte by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MethodeLutte : {}", id);        methodeLutteRepository.deleteById(id);
    }
}
