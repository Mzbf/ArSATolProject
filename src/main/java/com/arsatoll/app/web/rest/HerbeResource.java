package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.HerbeService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.HerbeDTO;
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
 * REST controller for managing Herbe.
 */
@RestController
@RequestMapping("/api")
public class HerbeResource {

    private final Logger log = LoggerFactory.getLogger(HerbeResource.class);

    private static final String ENTITY_NAME = "herbe";

    private final HerbeService herbeService;

    public HerbeResource(HerbeService herbeService) {
        this.herbeService = herbeService;
    }

    /**
     * POST  /herbes : Create a new herbe.
     *
     * @param herbeDTO the herbeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new herbeDTO, or with status 400 (Bad Request) if the herbe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/herbes")
    public ResponseEntity<HerbeDTO> createHerbe(@RequestBody HerbeDTO herbeDTO) throws URISyntaxException {
        log.debug("REST request to save Herbe : {}", herbeDTO);
        if (herbeDTO.getId() != null) {
            throw new BadRequestAlertException("A new herbe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HerbeDTO result = herbeService.save(herbeDTO);
        return ResponseEntity.created(new URI("/api/herbes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /herbes : Updates an existing herbe.
     *
     * @param herbeDTO the herbeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated herbeDTO,
     * or with status 400 (Bad Request) if the herbeDTO is not valid,
     * or with status 500 (Internal Server Error) if the herbeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/herbes")
    public ResponseEntity<HerbeDTO> updateHerbe(@RequestBody HerbeDTO herbeDTO) throws URISyntaxException {
        log.debug("REST request to update Herbe : {}", herbeDTO);
        if (herbeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HerbeDTO result = herbeService.save(herbeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, herbeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /herbes : get all the herbes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of herbes in body
     */
    @GetMapping("/herbes")
    public List<HerbeDTO> getAllHerbes() {
        log.debug("REST request to get all Herbes");
        return herbeService.findAll();
    }

    /**
     * GET  /herbes/:id : get the "id" herbe.
     *
     * @param id the id of the herbeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the herbeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/herbes/{id}")
    public ResponseEntity<HerbeDTO> getHerbe(@PathVariable Long id) {
        log.debug("REST request to get Herbe : {}", id);
        Optional<HerbeDTO> herbeDTO = herbeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(herbeDTO);
    }

    /**
     * DELETE  /herbes/:id : delete the "id" herbe.
     *
     * @param id the id of the herbeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/herbes/{id}")
    public ResponseEntity<Void> deleteHerbe(@PathVariable Long id) {
        log.debug("REST request to delete Herbe : {}", id);
        herbeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
