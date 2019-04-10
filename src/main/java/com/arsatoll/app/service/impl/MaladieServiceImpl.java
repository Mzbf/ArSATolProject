package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.MaladieService;
import com.arsatoll.app.domain.Maladie;
import com.arsatoll.app.repository.MaladieRepository;
import com.arsatoll.app.service.dto.MaladieDTO;
import com.arsatoll.app.service.mapper.MaladieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Maladie.
 */
@Service
@Transactional
public class MaladieServiceImpl implements MaladieService {

    private final Logger log = LoggerFactory.getLogger(MaladieServiceImpl.class);

    private final MaladieRepository maladieRepository;

    private final MaladieMapper maladieMapper;

    public MaladieServiceImpl(MaladieRepository maladieRepository, MaladieMapper maladieMapper) {
        this.maladieRepository = maladieRepository;
        this.maladieMapper = maladieMapper;
    }

    /**
     * Save a maladie.
     *
     * @param maladieDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MaladieDTO save(MaladieDTO maladieDTO) {
        log.debug("Request to save Maladie : {}", maladieDTO);
        Maladie maladie = maladieMapper.toEntity(maladieDTO);
        maladie = maladieRepository.save(maladie);
        return maladieMapper.toDto(maladie);
    }

    /**
     * Get all the maladies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MaladieDTO> findAll() {
        log.debug("Request to get all Maladies");
        return maladieRepository.findAll().stream()
            .map(maladieMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one maladie by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MaladieDTO> findOne(Long id) {
        log.debug("Request to get Maladie : {}", id);
        return maladieRepository.findById(id)
            .map(maladieMapper::toDto);
    }

    /**
     * Delete the maladie by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Maladie : {}", id);        maladieRepository.deleteById(id);
    }
}
