package com.arsatoll.app.service.impl;

import com.arsatoll.app.service.ZoneGeoService;
import com.arsatoll.app.domain.ZoneGeo;
import com.arsatoll.app.repository.ZoneGeoRepository;
import com.arsatoll.app.service.dto.ZoneGeoDTO;
import com.arsatoll.app.service.mapper.ZoneGeoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ZoneGeo.
 */
@Service
@Transactional
public class ZoneGeoServiceImpl implements ZoneGeoService {

    private final Logger log = LoggerFactory.getLogger(ZoneGeoServiceImpl.class);

    private final ZoneGeoRepository zoneGeoRepository;

    private final ZoneGeoMapper zoneGeoMapper;

    public ZoneGeoServiceImpl(ZoneGeoRepository zoneGeoRepository, ZoneGeoMapper zoneGeoMapper) {
        this.zoneGeoRepository = zoneGeoRepository;
        this.zoneGeoMapper = zoneGeoMapper;
    }

    /**
     * Save a zoneGeo.
     *
     * @param zoneGeoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ZoneGeoDTO save(ZoneGeoDTO zoneGeoDTO) {
        log.debug("Request to save ZoneGeo : {}", zoneGeoDTO);
        ZoneGeo zoneGeo = zoneGeoMapper.toEntity(zoneGeoDTO);
        zoneGeo = zoneGeoRepository.save(zoneGeo);
        return zoneGeoMapper.toDto(zoneGeo);
    }

    /**
     * Get all the zoneGeos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ZoneGeoDTO> findAll() {
        log.debug("Request to get all ZoneGeos");
        return zoneGeoRepository.findAll().stream()
            .map(zoneGeoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one zoneGeo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ZoneGeoDTO> findOne(Long id) {
        log.debug("Request to get ZoneGeo : {}", id);
        return zoneGeoRepository.findById(id)
            .map(zoneGeoMapper::toDto);
    }

    /**
     * Delete the zoneGeo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ZoneGeo : {}", id);        zoneGeoRepository.deleteById(id);
    }
}
