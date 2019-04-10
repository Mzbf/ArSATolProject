package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.MethodeLutteService;
import com.arsatoll.app.domain.MethodeLutte;
import com.arsatoll.app.repository.MethodeLutteRepository;
import com.arsatoll.app.service.dto.MethodeLutteDTO;
import com.arsatoll.app.service.mapper.MethodeLutteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MethodeLutte.
 */
@Service
@Transactional
public class MethodeLutteServiceImpl implements MethodeLutteService {

    private final Logger log = LoggerFactory.getLogger(MethodeLutteServiceImpl.class);

    private final MethodeLutteRepository methodeLutteRepository;

    private final MethodeLutteMapper methodeLutteMapper;

    public MethodeLutteServiceImpl(MethodeLutteRepository methodeLutteRepository, MethodeLutteMapper methodeLutteMapper) {
        this.methodeLutteRepository = methodeLutteRepository;
        this.methodeLutteMapper = methodeLutteMapper;
    }

    /**
     * Save a methodeLutte.
     *
     * @param methodeLutteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MethodeLutteDTO save(MethodeLutteDTO methodeLutteDTO) {
        log.debug("Request to save MethodeLutte : {}", methodeLutteDTO);
        MethodeLutte methodeLutte = methodeLutteMapper.toEntity(methodeLutteDTO);
        methodeLutte = methodeLutteRepository.save(methodeLutte);
        return methodeLutteMapper.toDto(methodeLutte);
    }

    /**
     * Get all the methodeLuttes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MethodeLutteDTO> findAll() {
        log.debug("Request to get all MethodeLuttes");
        return methodeLutteRepository.findAll().stream()
            .map(methodeLutteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one methodeLutte by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MethodeLutteDTO> findOne(Long id) {
        log.debug("Request to get MethodeLutte : {}", id);
        return methodeLutteRepository.findById(id)
            .map(methodeLutteMapper::toDto);
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
