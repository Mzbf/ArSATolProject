package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.AgriculteurService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.AgriculteurDTO;
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
 * REST controller for managing Agriculteur.
 */
@RestController
@RequestMapping("/api")
public class AgriculteurResource {

    private final Logger log = LoggerFactory.getLogger(AgriculteurResource.class);

    private static final String ENTITY_NAME = "agriculteur";

    private final AgriculteurService agriculteurService;

    public AgriculteurResource(AgriculteurService agriculteurService) {
        this.agriculteurService = agriculteurService;
    }

    /**
     * POST  /agriculteurs : Create a new agriculteur.
     *
     * @param agriculteurDTO the agriculteurDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agriculteurDTO, or with status 400 (Bad Request) if the agriculteur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agriculteurs")
    public ResponseEntity<AgriculteurDTO> createAgriculteur(@RequestBody AgriculteurDTO agriculteurDTO) throws URISyntaxException {
        log.debug("REST request to save Agriculteur : {}", agriculteurDTO);
        if (agriculteurDTO.getId() != null) {
            throw new BadRequestAlertException("A new agriculteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgriculteurDTO result = agriculteurService.save(agriculteurDTO);
        return ResponseEntity.created(new URI("/api/agriculteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agriculteurs : Updates an existing agriculteur.
     *
     * @param agriculteurDTO the agriculteurDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agriculteurDTO,
     * or with status 400 (Bad Request) if the agriculteurDTO is not valid,
     * or with status 500 (Internal Server Error) if the agriculteurDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/agriculteurs")
    public ResponseEntity<AgriculteurDTO> updateAgriculteur(@RequestBody AgriculteurDTO agriculteurDTO) throws URISyntaxException {
        log.debug("REST request to update Agriculteur : {}", agriculteurDTO);
        if (agriculteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgriculteurDTO result = agriculteurService.save(agriculteurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, agriculteurDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /agriculteurs : get all the agriculteurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of agriculteurs in body
     */
    @GetMapping("/agriculteurs")
    public List<AgriculteurDTO> getAllAgriculteurs() {
        log.debug("REST request to get all Agriculteurs");
        return agriculteurService.findAll();
    }

    /**
     * GET  /agriculteurs/:id : get the "id" agriculteur.
     *
     * @param id the id of the agriculteurDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agriculteurDTO, or with status 404 (Not Found)
     */
    @GetMapping("/agriculteurs/{id}")
    public ResponseEntity<AgriculteurDTO> getAgriculteur(@PathVariable Long id) {
        log.debug("REST request to get Agriculteur : {}", id);
        Optional<AgriculteurDTO> agriculteurDTO = agriculteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agriculteurDTO);
    }

    /**
     * DELETE  /agriculteurs/:id : delete the "id" agriculteur.
     *
     * @param id the id of the agriculteurDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/agriculteurs/{id}")
    public ResponseEntity<Void> deleteAgriculteur(@PathVariable Long id) {
        log.debug("REST request to delete Agriculteur : {}", id);
        agriculteurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
