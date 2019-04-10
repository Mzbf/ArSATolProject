package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.DemoService;
import com.arsatoll.app.domain.Demo;
import com.arsatoll.app.repository.DemoRepository;
import com.arsatoll.app.service.dto.DemoDTO;
import com.arsatoll.app.service.mapper.DemoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Demo.
 */
@Service
@Transactional
public class DemoServiceImpl implements DemoService {

    private final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

    private final DemoRepository demoRepository;

    private final DemoMapper demoMapper;

    public DemoServiceImpl(DemoRepository demoRepository, DemoMapper demoMapper) {
        this.demoRepository = demoRepository;
        this.demoMapper = demoMapper;
    }

    /**
     * Save a demo.
     *
     * @param demoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DemoDTO save(DemoDTO demoDTO) {
        log.debug("Request to save Demo : {}", demoDTO);
        Demo demo = demoMapper.toEntity(demoDTO);
        demo = demoRepository.save(demo);
        return demoMapper.toDto(demo);
    }

    /**
     * Get all the demos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DemoDTO> findAll() {
        log.debug("Request to get all Demos");
        return demoRepository.findAll().stream()
            .map(demoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one demo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemoDTO> findOne(Long id) {
        log.debug("Request to get Demo : {}", id);
        return demoRepository.findById(id)
            .map(demoMapper::toDto);
    }

    /**
     * Delete the demo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Demo : {}", id);        demoRepository.deleteById(id);
    }
}
