package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.ZoneGeoService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.ZoneGeoDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ZoneGeo.
 */
@RestController
@RequestMapping("/api")
public class ZoneGeoResource {

    private final Logger log = LoggerFactory.getLogger(ZoneGeoResource.class);

    private static final String ENTITY_NAME = "zoneGeo";

    private final ZoneGeoService zoneGeoService;

    public ZoneGeoResource(ZoneGeoService zoneGeoService) {
        this.zoneGeoService = zoneGeoService;
    }

    /**
     * POST  /zone-geos : Create a new zoneGeo.
     *
     * @param zoneGeoDTO the zoneGeoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new zoneGeoDTO, or with status 400 (Bad Request) if the zoneGeo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/zone-geos")
    public ResponseEntity<ZoneGeoDTO> createZoneGeo(@RequestBody ZoneGeoDTO zoneGeoDTO) throws URISyntaxException {
        log.debug("REST request to save ZoneGeo : {}", zoneGeoDTO);
        if (zoneGeoDTO.getId() != null) {
            throw new BadRequestAlertException("A new zoneGeo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ZoneGeoDTO result = zoneGeoService.save(zoneGeoDTO);
        return ResponseEntity.created(new URI("/api/zone-geos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /zone-geos : Updates an existing zoneGeo.
     *
     * @param zoneGeoDTO the zoneGeoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated zoneGeoDTO,
     * or with status 400 (Bad Request) if the zoneGeoDTO is not valid,
     * or with status 500 (Internal Server Error) if the zoneGeoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/zone-geos")
    public ResponseEntity<ZoneGeoDTO> updateZoneGeo(@RequestBody ZoneGeoDTO zoneGeoDTO) throws URISyntaxException {
        log.debug("REST request to update ZoneGeo : {}", zoneGeoDTO);
        if (zoneGeoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ZoneGeoDTO result = zoneGeoService.save(zoneGeoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, zoneGeoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /zone-geos : get all the zoneGeos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of zoneGeos in body
     */
    @GetMapping("/zone-geos")
    public List<ZoneGeoDTO> getAllZoneGeos() {
        log.debug("REST request to get all ZoneGeos");
        return zoneGeoService.findAll();
    }

    /**
     * GET  /zone-geos/:id : get the "id" zoneGeo.
     *
     * @param id the id of the zoneGeoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the zoneGeoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/zone-geos/{id}")
    public ResponseEntity<ZoneGeoDTO> getZoneGeo(@PathVariable Long id) {
        log.debug("REST request to get ZoneGeo : {}", id);
        Optional<ZoneGeoDTO> zoneGeoDTO = zoneGeoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(zoneGeoDTO);
    }

    /**
     * DELETE  /zone-geos/:id : delete the "id" zoneGeo.
     *
     * @param id the id of the zoneGeoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/zone-geos/{id}")
    public ResponseEntity<Void> deleteZoneGeo(@PathVariable Long id) {
        log.debug("REST request to delete ZoneGeo : {}", id);
        zoneGeoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
