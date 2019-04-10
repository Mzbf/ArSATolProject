package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.AttaqueService;
import com.arsatoll.app.domain.Attaque;
import com.arsatoll.app.repository.AttaqueRepository;
import com.arsatoll.app.service.dto.AttaqueDTO;
import com.arsatoll.app.service.mapper.AttaqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Attaque.
 */
@Service
@Transactional
public class AttaqueServiceImpl implements AttaqueService {

    private final Logger log = LoggerFactory.getLogger(AttaqueServiceImpl.class);

    private final AttaqueRepository attaqueRepository;

    private final AttaqueMapper attaqueMapper;

    public AttaqueServiceImpl(AttaqueRepository attaqueRepository, AttaqueMapper attaqueMapper) {
        this.attaqueRepository = attaqueRepository;
        this.attaqueMapper = attaqueMapper;
    }

    /**
     * Save a attaque.
     *
     * @param attaqueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AttaqueDTO save(AttaqueDTO attaqueDTO) {
        log.debug("Request to save Attaque : {}", attaqueDTO);
        Attaque attaque = attaqueMapper.toEntity(attaqueDTO);
        attaque = attaqueRepository.save(attaque);
        return attaqueMapper.toDto(attaque);
    }

    /**
     * Get all the attaques.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttaqueDTO> findAll() {
        log.debug("Request to get all Attaques");
        return attaqueRepository.findAll().stream()
            .map(attaqueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one attaque by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AttaqueDTO> findOne(Long id) {
        log.debug("Request to get Attaque : {}", id);
        return attaqueRepository.findById(id)
            .map(attaqueMapper::toDto);
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
