package com.arsatoll.app.web.rest;
import com.arsatoll.app.domain.Chercheur;
import com.arsatoll.app.service.ChercheurService;
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
 * REST controller for managing Chercheur.
 */
@RestController
@RequestMapping("/api")
public class ChercheurResource {

    private final Logger log = LoggerFactory.getLogger(ChercheurResource.class);

    private static final String ENTITY_NAME = "chercheur";

    private final ChercheurService chercheurService;

    public ChercheurResource(ChercheurService chercheurService) {
        this.chercheurService = chercheurService;
    }

    /**
     * POST  /chercheurs : Create a new chercheur.
     *
     * @param chercheur the chercheur to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chercheur, or with status 400 (Bad Request) if the chercheur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chercheurs")
    public ResponseEntity<Chercheur> createChercheur(@RequestBody Chercheur chercheur) throws URISyntaxException {
        log.debug("REST request to save Chercheur : {}", chercheur);
        if (chercheur.getId() != null) {
            throw new BadRequestAlertException("A new chercheur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Chercheur result = chercheurService.save(chercheur);
        return ResponseEntity.created(new URI("/api/chercheurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chercheurs : Updates an existing chercheur.
     *
     * @param chercheur the chercheur to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chercheur,
     * or with status 400 (Bad Request) if the chercheur is not valid,
     * or with status 500 (Internal Server Error) if the chercheur couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chercheurs")
    public ResponseEntity<Chercheur> updateChercheur(@RequestBody Chercheur chercheur) throws URISyntaxException {
        log.debug("REST request to update Chercheur : {}", chercheur);
        if (chercheur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Chercheur result = chercheurService.save(chercheur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chercheur.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chercheurs : get all the chercheurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of chercheurs in body
     */
    @GetMapping("/chercheurs")
    public List<Chercheur> getAllChercheurs() {
        log.debug("REST request to get all Chercheurs");
        return chercheurService.findAll();
    }

    /**
     * GET  /chercheurs/:id : get the "id" chercheur.
     *
     * @param id the id of the chercheur to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chercheur, or with status 404 (Not Found)
     */
    @GetMapping("/chercheurs/{id}")
    public ResponseEntity<Chercheur> getChercheur(@PathVariable Long id) {
        log.debug("REST request to get Chercheur : {}", id);
        Optional<Chercheur> chercheur = chercheurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chercheur);
    }

    /**
     * DELETE  /chercheurs/:id : delete the "id" chercheur.
     *
     * @param id the id of the chercheur to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chercheurs/{id}")
    public ResponseEntity<Void> deleteChercheur(@PathVariable Long id) {
        log.debug("REST request to delete Chercheur : {}", id);
        chercheurService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
