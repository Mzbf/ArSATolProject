package com.arsatoll.app.web.rest;
import com.arsatoll.app.domain.SuperFamille;
import com.arsatoll.app.service.SuperFamilleService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
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
 * REST controller for managing SuperFamille.
 */
@RestController
@RequestMapping("/api")
public class SuperFamilleResource {

    private final Logger log = LoggerFactory.getLogger(SuperFamilleResource.class);

    private static final String ENTITY_NAME = "superFamille";

    private final SuperFamilleService superFamilleService;

    public SuperFamilleResource(SuperFamilleService superFamilleService) {
        this.superFamilleService = superFamilleService;
    }

    /**
     * POST  /super-familles : Create a new superFamille.
     *
     * @param superFamille the superFamille to create
     * @return the ResponseEntity with status 201 (Created) and with body the new superFamille, or with status 400 (Bad Request) if the superFamille has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/super-familles")
    public ResponseEntity<SuperFamille> createSuperFamille(@RequestBody SuperFamille superFamille) throws URISyntaxException {
        log.debug("REST request to save SuperFamille : {}", superFamille);
        if (superFamille.getId() != null) {
            throw new BadRequestAlertException("A new superFamille cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SuperFamille result = superFamilleService.save(superFamille);
        return ResponseEntity.created(new URI("/api/super-familles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /super-familles : Updates an existing superFamille.
     *
     * @param superFamille the superFamille to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated superFamille,
     * or with status 400 (Bad Request) if the superFamille is not valid,
     * or with status 500 (Internal Server Error) if the superFamille couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/super-familles")
    public ResponseEntity<SuperFamille> updateSuperFamille(@RequestBody SuperFamille superFamille) throws URISyntaxException {
        log.debug("REST request to update SuperFamille : {}", superFamille);
        if (superFamille.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SuperFamille result = superFamilleService.save(superFamille);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, superFamille.getId().toString()))
            .body(result);
    }

    /**
     * GET  /super-familles : get all the superFamilles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of superFamilles in body
     */
    @GetMapping("/super-familles")
    public List<SuperFamille> getAllSuperFamilles() {
        log.debug("REST request to get all SuperFamilles");
        return superFamilleService.findAll();
    }

    /**
     * GET  /super-familles/:id : get the "id" superFamille.
     *
     * @param id the id of the superFamille to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the superFamille, or with status 404 (Not Found)
     */
    @GetMapping("/super-familles/{id}")
    public ResponseEntity<SuperFamille> getSuperFamille(@PathVariable Long id) {
        log.debug("REST request to get SuperFamille : {}", id);
        Optional<SuperFamille> superFamille = superFamilleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(superFamille);
    }

    /**
     * DELETE  /super-familles/:id : delete the "id" superFamille.
     *
     * @param id the id of the superFamille to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/super-familles/{id}")
    public ResponseEntity<Void> deleteSuperFamille(@PathVariable Long id) {
        log.debug("REST request to delete SuperFamille : {}", id);
        superFamilleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
