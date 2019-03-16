package com.arsatoll.app.web.rest;
import com.arsatoll.app.domain.Insecte;
import com.arsatoll.app.service.InsecteService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Insecte.
 */
@RestController
@RequestMapping("/api")
public class InsecteResource {

    private final Logger log = LoggerFactory.getLogger(InsecteResource.class);

    private static final String ENTITY_NAME = "insecte";

    private final InsecteService insecteService;

    public InsecteResource(InsecteService insecteService) {
        this.insecteService = insecteService;
    }

    /**
     * POST  /insectes : Create a new insecte.
     *
     * @param insecte the insecte to create
     * @return the ResponseEntity with status 201 (Created) and with body the new insecte, or with status 400 (Bad Request) if the insecte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/insectes")
    public ResponseEntity<Insecte> createInsecte(@Valid @RequestBody Insecte insecte) throws URISyntaxException {
        log.debug("REST request to save Insecte : {}", insecte);
        if (insecte.getId() != null) {
            throw new BadRequestAlertException("A new insecte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Insecte result = insecteService.save(insecte);
        return ResponseEntity.created(new URI("/api/insectes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /insectes : Updates an existing insecte.
     *
     * @param insecte the insecte to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated insecte,
     * or with status 400 (Bad Request) if the insecte is not valid,
     * or with status 500 (Internal Server Error) if the insecte couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/insectes")
    public ResponseEntity<Insecte> updateInsecte(@Valid @RequestBody Insecte insecte) throws URISyntaxException {
        log.debug("REST request to update Insecte : {}", insecte);
        if (insecte.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Insecte result = insecteService.save(insecte);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, insecte.getId().toString()))
            .body(result);
    }

    /**
     * GET  /insectes : get all the insectes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of insectes in body
     */
    @GetMapping("/insectes")
    public List<Insecte> getAllInsectes() {
        log.debug("REST request to get all Insectes");
        return insecteService.findAll();
    }

    /**
     * GET  /insectes/:id : get the "id" insecte.
     *
     * @param id the id of the insecte to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the insecte, or with status 404 (Not Found)
     */
    @GetMapping("/insectes/{id}")
    public ResponseEntity<Insecte> getInsecte(@PathVariable Long id) {
        log.debug("REST request to get Insecte : {}", id);
        Optional<Insecte> insecte = insecteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insecte);
    }

    /**
     * DELETE  /insectes/:id : delete the "id" insecte.
     *
     * @param id the id of the insecte to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/insectes/{id}")
    public ResponseEntity<Void> deleteInsecte(@PathVariable Long id) {
        log.debug("REST request to delete Insecte : {}", id);
        insecteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
