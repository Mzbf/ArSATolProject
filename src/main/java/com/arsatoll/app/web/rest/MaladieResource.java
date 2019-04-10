package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.MaladieService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.MaladieDTO;
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
 * REST controller for managing Maladie.
 */
@RestController
@RequestMapping("/api")
public class MaladieResource {

    private final Logger log = LoggerFactory.getLogger(MaladieResource.class);

    private static final String ENTITY_NAME = "maladie";

    private final MaladieService maladieService;

    public MaladieResource(MaladieService maladieService) {
        this.maladieService = maladieService;
    }

    /**
     * POST  /maladies : Create a new maladie.
     *
     * @param maladieDTO the maladieDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new maladieDTO, or with status 400 (Bad Request) if the maladie has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/maladies")
    public ResponseEntity<MaladieDTO> createMaladie(@RequestBody MaladieDTO maladieDTO) throws URISyntaxException {
        log.debug("REST request to save Maladie : {}", maladieDTO);
        if (maladieDTO.getId() != null) {
            throw new BadRequestAlertException("A new maladie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MaladieDTO result = maladieService.save(maladieDTO);
        return ResponseEntity.created(new URI("/api/maladies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /maladies : Updates an existing maladie.
     *
     * @param maladieDTO the maladieDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated maladieDTO,
     * or with status 400 (Bad Request) if the maladieDTO is not valid,
     * or with status 500 (Internal Server Error) if the maladieDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/maladies")
    public ResponseEntity<MaladieDTO> updateMaladie(@RequestBody MaladieDTO maladieDTO) throws URISyntaxException {
        log.debug("REST request to update Maladie : {}", maladieDTO);
        if (maladieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MaladieDTO result = maladieService.save(maladieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, maladieDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /maladies : get all the maladies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of maladies in body
     */
    @GetMapping("/maladies")
    public List<MaladieDTO> getAllMaladies() {
        log.debug("REST request to get all Maladies");
        return maladieService.findAll();
    }

    /**
     * GET  /maladies/:id : get the "id" maladie.
     *
     * @param id the id of the maladieDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the maladieDTO, or with status 404 (Not Found)
     */
    @GetMapping("/maladies/{id}")
    public ResponseEntity<MaladieDTO> getMaladie(@PathVariable Long id) {
        log.debug("REST request to get Maladie : {}", id);
        Optional<MaladieDTO> maladieDTO = maladieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(maladieDTO);
    }

    /**
     * DELETE  /maladies/:id : delete the "id" maladie.
     *
     * @param id the id of the maladieDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/maladies/{id}")
    public ResponseEntity<Void> deleteMaladie(@PathVariable Long id) {
        log.debug("REST request to delete Maladie : {}", id);
        maladieService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
