package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.FamilleService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.FamilleDTO;
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
 * REST controller for managing Famille.
 */
@RestController
@RequestMapping("/api")
public class FamilleResource {

    private final Logger log = LoggerFactory.getLogger(FamilleResource.class);

    private static final String ENTITY_NAME = "famille";

    private final FamilleService familleService;

    public FamilleResource(FamilleService familleService) {
        this.familleService = familleService;
    }

    /**
     * POST  /familles : Create a new famille.
     *
     * @param familleDTO the familleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new familleDTO, or with status 400 (Bad Request) if the famille has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/familles")
    public ResponseEntity<FamilleDTO> createFamille(@RequestBody FamilleDTO familleDTO) throws URISyntaxException {
        log.debug("REST request to save Famille : {}", familleDTO);
        if (familleDTO.getId() != null) {
            throw new BadRequestAlertException("A new famille cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FamilleDTO result = familleService.save(familleDTO);
        return ResponseEntity.created(new URI("/api/familles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /familles : Updates an existing famille.
     *
     * @param familleDTO the familleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated familleDTO,
     * or with status 400 (Bad Request) if the familleDTO is not valid,
     * or with status 500 (Internal Server Error) if the familleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/familles")
    public ResponseEntity<FamilleDTO> updateFamille(@RequestBody FamilleDTO familleDTO) throws URISyntaxException {
        log.debug("REST request to update Famille : {}", familleDTO);
        if (familleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FamilleDTO result = familleService.save(familleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, familleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /familles : get all the familles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of familles in body
     */
    @GetMapping("/familles")
    public List<FamilleDTO> getAllFamilles() {
        log.debug("REST request to get all Familles");
        return familleService.findAll();
    }

    /**
     * GET  /familles/:id : get the "id" famille.
     *
     * @param id the id of the familleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the familleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/familles/{id}")
    public ResponseEntity<FamilleDTO> getFamille(@PathVariable Long id) {
        log.debug("REST request to get Famille : {}", id);
        Optional<FamilleDTO> familleDTO = familleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(familleDTO);
    }

    /**
     * DELETE  /familles/:id : delete the "id" famille.
     *
     * @param id the id of the familleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/familles/{id}")
    public ResponseEntity<Void> deleteFamille(@PathVariable Long id) {
        log.debug("REST request to delete Famille : {}", id);
        familleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
