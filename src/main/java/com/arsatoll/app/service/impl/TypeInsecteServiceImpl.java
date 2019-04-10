package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.TypeInsecteService;
import com.arsatoll.app.domain.TypeInsecte;
import com.arsatoll.app.repository.TypeInsecteRepository;
import com.arsatoll.app.service.dto.TypeInsecteDTO;
import com.arsatoll.app.service.mapper.TypeInsecteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TypeInsecte.
 */
@Service
@Transactional
public class TypeInsecteServiceImpl implements TypeInsecteService {

    private final Logger log = LoggerFactory.getLogger(TypeInsecteServiceImpl.class);

    private final TypeInsecteRepository typeInsecteRepository;

    private final TypeInsecteMapper typeInsecteMapper;

    public TypeInsecteServiceImpl(TypeInsecteRepository typeInsecteRepository, TypeInsecteMapper typeInsecteMapper) {
        this.typeInsecteRepository = typeInsecteRepository;
        this.typeInsecteMapper = typeInsecteMapper;
    }

    /**
     * Save a typeInsecte.
     *
     * @param typeInsecteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeInsecteDTO save(TypeInsecteDTO typeInsecteDTO) {
        log.debug("Request to save TypeInsecte : {}", typeInsecteDTO);
        TypeInsecte typeInsecte = typeInsecteMapper.toEntity(typeInsecteDTO);
        typeInsecte = typeInsecteRepository.save(typeInsecte);
        return typeInsecteMapper.toDto(typeInsecte);
    }

    /**
     * Get all the typeInsectes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeInsecteDTO> findAll() {
        log.debug("Request to get all TypeInsectes");
        return typeInsecteRepository.findAll().stream()
            .map(typeInsecteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one typeInsecte by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeInsecteDTO> findOne(Long id) {
        log.debug("Request to get TypeInsecte : {}", id);
        return typeInsecteRepository.findById(id)
            .map(typeInsecteMapper::toDto);
    }

    /**
     * Delete the typeInsecte by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeInsecte : {}", id);        typeInsecteRepository.deleteById(id);
    }
}
