package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.AttaqueService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.AttaqueDTO;
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
 * REST controller for managing Attaque.
 */
@RestController
@RequestMapping("/api")
public class AttaqueResource {

    private final Logger log = LoggerFactory.getLogger(AttaqueResource.class);

    private static final String ENTITY_NAME = "attaque";

    private final AttaqueService attaqueService;

    public AttaqueResource(AttaqueService attaqueService) {
        this.attaqueService = attaqueService;
    }

    /**
     * POST  /attaques : Create a new attaque.
     *
     * @param attaqueDTO the attaqueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new attaqueDTO, or with status 400 (Bad Request) if the attaque has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attaques")
    public ResponseEntity<AttaqueDTO> createAttaque(@RequestBody AttaqueDTO attaqueDTO) throws URISyntaxException {
        log.debug("REST request to save Attaque : {}", attaqueDTO);
        if (attaqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new attaque cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AttaqueDTO result = attaqueService.save(attaqueDTO);
        return ResponseEntity.created(new URI("/api/attaques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attaques : Updates an existing attaque.
     *
     * @param attaqueDTO the attaqueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated attaqueDTO,
     * or with status 400 (Bad Request) if the attaqueDTO is not valid,
     * or with status 500 (Internal Server Error) if the attaqueDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attaques")
    public ResponseEntity<AttaqueDTO> updateAttaque(@RequestBody AttaqueDTO attaqueDTO) throws URISyntaxException {
        log.debug("REST request to update Attaque : {}", attaqueDTO);
        if (attaqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AttaqueDTO result = attaqueService.save(attaqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attaqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attaques : get all the attaques.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of attaques in body
     */
    @GetMapping("/attaques")
    public List<AttaqueDTO> getAllAttaques() {
        log.debug("REST request to get all Attaques");
        return attaqueService.findAll();
    }

    /**
     * GET  /attaques/:id : get the "id" attaque.
     *
     * @param id the id of the attaqueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the attaqueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/attaques/{id}")
    public ResponseEntity<AttaqueDTO> getAttaque(@PathVariable Long id) {
        log.debug("REST request to get Attaque : {}", id);
        Optional<AttaqueDTO> attaqueDTO = attaqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attaqueDTO);
    }

    /**
     * DELETE  /attaques/:id : delete the "id" attaque.
     *
     * @param id the id of the attaqueDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attaques/{id}")
    public ResponseEntity<Void> deleteAttaque(@PathVariable Long id) {
        log.debug("REST request to delete Attaque : {}", id);
        attaqueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
