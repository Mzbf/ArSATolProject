package com.arsatoll.app.web.rest;
import com.arsatoll.app.domain.Insecte;
import com.arsatoll.app.domain.enumeration.Localisation;
import com.arsatoll.app.repository.InsecteRepository;
import com.arsatoll.app.service.InsecteService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.InsecteDTO;
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

    private final InsecteRepository insecteRepository;

    public InsecteResource(InsecteService insecteService, InsecteRepository insecteRepository) {
        this.insecteService = insecteService;
        this.insecteRepository = insecteRepository;
    }

    /**
     * POST  /insectes : Create a new insecte.
     *
     * @param insecteDTO the insecteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new insecteDTO, or with status 400 (Bad Request) if the insecte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/insectes")
    public ResponseEntity<InsecteDTO> createInsecte(@Valid @RequestBody InsecteDTO insecteDTO) throws URISyntaxException {
        log.debug("REST request to save Insecte : {}", insecteDTO);
        if (insecteDTO.getId() != null) {
            throw new BadRequestAlertException("A new insecte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsecteDTO result = insecteService.save(insecteDTO);
        return ResponseEntity.created(new URI("/api/insectes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /insectes : Updates an existing insecte.
     *
     * @param insecteDTO the insecteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated insecteDTO,
     * or with status 400 (Bad Request) if the insecteDTO is not valid,
     * or with status 500 (Internal Server Error) if the insecteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/insectes")
    public ResponseEntity<InsecteDTO> updateInsecte(@Valid @RequestBody InsecteDTO insecteDTO) throws URISyntaxException {
        log.debug("REST request to update Insecte : {}", insecteDTO);
        if (insecteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsecteDTO result = insecteService.save(insecteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, insecteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /insectes : get all the insectes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of insectes in body
     */
    @GetMapping("/insectes")
    public List<InsecteDTO> getAllInsectes() {
        log.debug("REST request to get all Insectes");
        return insecteService.findAll();
    }

    /**
     * GET  /insectes/:id : get the "id" insecte.
     *
     * @param id the id of the insecteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the insecteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/insectes/{id}")
    public ResponseEntity<InsecteDTO> getInsecte(@PathVariable Long id) {
        log.debug("REST request to get Insecte : {}", id);
        Optional<InsecteDTO> insecteDTO = insecteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insecteDTO);
    }

    @GetMapping("/ravageur/{id}/{local}")
    public Insecte findRavageur(@PathVariable Long id,@PathVariable Localisation local) {
        log.debug("REST request to get Insecte : {}", id);
        Insecte insecteDTO = insecteRepository.findRavageur(id,local);
        return insecteDTO;
    }

    /**
     * DELETE  /insectes/:id : delete the "id" insecte.
     *
     * @param id the id of the insecteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/insectes/{id}")
    public ResponseEntity<Void> deleteInsecte(@PathVariable Long id) {
        log.debug("REST request to delete Insecte : {}", id);
        insecteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


}
