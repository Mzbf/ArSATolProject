package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.TypeDegatService;
import com.arsatoll.app.domain.TypeDegat;
import com.arsatoll.app.repository.TypeDegatRepository;
import com.arsatoll.app.service.dto.TypeDegatDTO;
import com.arsatoll.app.service.mapper.TypeDegatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TypeDegat.
 */
@Service
@Transactional
public class TypeDegatServiceImpl implements TypeDegatService {

    private final Logger log = LoggerFactory.getLogger(TypeDegatServiceImpl.class);

    private final TypeDegatRepository typeDegatRepository;

    private final TypeDegatMapper typeDegatMapper;

    public TypeDegatServiceImpl(TypeDegatRepository typeDegatRepository, TypeDegatMapper typeDegatMapper) {
        this.typeDegatRepository = typeDegatRepository;
        this.typeDegatMapper = typeDegatMapper;
    }

    /**
     * Save a typeDegat.
     *
     * @param typeDegatDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeDegatDTO save(TypeDegatDTO typeDegatDTO) {
        log.debug("Request to save TypeDegat : {}", typeDegatDTO);
        TypeDegat typeDegat = typeDegatMapper.toEntity(typeDegatDTO);
        typeDegat = typeDegatRepository.save(typeDegat);
        return typeDegatMapper.toDto(typeDegat);
    }

    /**
     * Get all the typeDegats.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeDegatDTO> findAll() {
        log.debug("Request to get all TypeDegats");
        return typeDegatRepository.findAll().stream()
            .map(typeDegatMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one typeDegat by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeDegatDTO> findOne(Long id) {
        log.debug("Request to get TypeDegat : {}", id);
        return typeDegatRepository.findById(id)
            .map(typeDegatMapper::toDto);
    }

    /**
     * Delete the typeDegat by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeDegat : {}", id);        typeDegatRepository.deleteById(id);
    }
}
