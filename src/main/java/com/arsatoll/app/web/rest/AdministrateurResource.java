package com.arsatoll.app.web.rest;
import com.arsatoll.app.domain.Administrateur;
import com.arsatoll.app.repository.AdministrateurRepository;
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
 * REST controller for managing Administrateur.
 */
@RestController
@RequestMapping("/api")
public class AdministrateurResource {

    private final Logger log = LoggerFactory.getLogger(AdministrateurResource.class);

    private static final String ENTITY_NAME = "administrateur";

    private final AdministrateurRepository administrateurRepository;

    public AdministrateurResource(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;
    }

    /**
     * POST  /administrateurs : Create a new administrateur.
     *
     * @param administrateur the administrateur to create
     * @return the ResponseEntity with status 201 (Created) and with body the new administrateur, or with status 400 (Bad Request) if the administrateur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/administrateurs")
    public ResponseEntity<Administrateur> createAdministrateur(@RequestBody Administrateur administrateur) throws URISyntaxException {
        log.debug("REST request to save Administrateur : {}", administrateur);
        if (administrateur.getId() != null) {
            throw new BadRequestAlertException("A new administrateur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Administrateur result = administrateurRepository.save(administrateur);
        return ResponseEntity.created(new URI("/api/administrateurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /administrateurs : Updates an existing administrateur.
     *
     * @param administrateur the administrateur to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated administrateur,
     * or with status 400 (Bad Request) if the administrateur is not valid,
     * or with status 500 (Internal Server Error) if the administrateur couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/administrateurs")
    public ResponseEntity<Administrateur> updateAdministrateur(@RequestBody Administrateur administrateur) throws URISyntaxException {
        log.debug("REST request to update Administrateur : {}", administrateur);
        if (administrateur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Administrateur result = administrateurRepository.save(administrateur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, administrateur.getId().toString()))
            .body(result);
    }

    /**
     * GET  /administrateurs : get all the administrateurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of administrateurs in body
     */
    @GetMapping("/administrateurs")
    public List<Administrateur> getAllAdministrateurs() {
        log.debug("REST request to get all Administrateurs");
        return administrateurRepository.findAll();
    }

    /**
     * GET  /administrateurs/:id : get the "id" administrateur.
     *
     * @param id the id of the administrateur to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the administrateur, or with status 404 (Not Found)
     */
    @GetMapping("/administrateurs/{id}")
    public ResponseEntity<Administrateur> getAdministrateur(@PathVariable Long id) {
        log.debug("REST request to get Administrateur : {}", id);
        Optional<Administrateur> administrateur = administrateurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(administrateur);
    }

    /**
     * DELETE  /administrateurs/:id : delete the "id" administrateur.
     *
     * @param id the id of the administrateur to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/administrateurs/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        log.debug("REST request to delete Administrateur : {}", id);
        administrateurRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
