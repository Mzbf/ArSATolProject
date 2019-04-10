package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.AgriculteurService;
import com.arsatoll.app.domain.Agriculteur;
import com.arsatoll.app.repository.AgriculteurRepository;
import com.arsatoll.app.service.dto.AgriculteurDTO;
import com.arsatoll.app.service.mapper.AgriculteurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Agriculteur.
 */
@Service
@Transactional
public class AgriculteurServiceImpl implements AgriculteurService {

    private final Logger log = LoggerFactory.getLogger(AgriculteurServiceImpl.class);

    private final AgriculteurRepository agriculteurRepository;

    private final AgriculteurMapper agriculteurMapper;

    public AgriculteurServiceImpl(AgriculteurRepository agriculteurRepository, AgriculteurMapper agriculteurMapper) {
        this.agriculteurRepository = agriculteurRepository;
        this.agriculteurMapper = agriculteurMapper;
    }

    /**
     * Save a agriculteur.
     *
     * @param agriculteurDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AgriculteurDTO save(AgriculteurDTO agriculteurDTO) {
        log.debug("Request to save Agriculteur : {}", agriculteurDTO);
        Agriculteur agriculteur = agriculteurMapper.toEntity(agriculteurDTO);
        agriculteur = agriculteurRepository.save(agriculteur);
        return agriculteurMapper.toDto(agriculteur);
    }

    /**
     * Get all the agriculteurs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AgriculteurDTO> findAll() {
        log.debug("Request to get all Agriculteurs");
        return agriculteurRepository.findAll().stream()
            .map(agriculteurMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one agriculteur by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AgriculteurDTO> findOne(Long id) {
        log.debug("Request to get Agriculteur : {}", id);
        return agriculteurRepository.findById(id)
            .map(agriculteurMapper::toDto);
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
