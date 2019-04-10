package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.DemoService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.DemoDTO;
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
 * REST controller for managing Demo.
 */
@RestController
@RequestMapping("/api")
public class DemoResource {

    private final Logger log = LoggerFactory.getLogger(DemoResource.class);

    private static final String ENTITY_NAME = "demo";

    private final DemoService demoService;

    public DemoResource(DemoService demoService) {
        this.demoService = demoService;
    }

    /**
     * POST  /demos : Create a new demo.
     *
     * @param demoDTO the demoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new demoDTO, or with status 400 (Bad Request) if the demo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/demos")
    public ResponseEntity<DemoDTO> createDemo(@RequestBody DemoDTO demoDTO) throws URISyntaxException {
        log.debug("REST request to save Demo : {}", demoDTO);
        if (demoDTO.getId() != null) {
            throw new BadRequestAlertException("A new demo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DemoDTO result = demoService.save(demoDTO);
        return ResponseEntity.created(new URI("/api/demos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /demos : Updates an existing demo.
     *
     * @param demoDTO the demoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated demoDTO,
     * or with status 400 (Bad Request) if the demoDTO is not valid,
     * or with status 500 (Internal Server Error) if the demoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/demos")
    public ResponseEntity<DemoDTO> updateDemo(@RequestBody DemoDTO demoDTO) throws URISyntaxException {
        log.debug("REST request to update Demo : {}", demoDTO);
        if (demoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DemoDTO result = demoService.save(demoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, demoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /demos : get all the demos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of demos in body
     */
    @GetMapping("/demos")
    public List<DemoDTO> getAllDemos() {
        log.debug("REST request to get all Demos");
        return demoService.findAll();
    }

    /**
     * GET  /demos/:id : get the "id" demo.
     *
     * @param id the id of the demoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the demoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/demos/{id}")
    public ResponseEntity<DemoDTO> getDemo(@PathVariable Long id) {
        log.debug("REST request to get Demo : {}", id);
        Optional<DemoDTO> demoDTO = demoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demoDTO);
    }

    /**
     * DELETE  /demos/:id : delete the "id" demo.
     *
     * @param id the id of the demoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/demos/{id}")
    public ResponseEntity<Void> deleteDemo(@PathVariable Long id) {
        log.debug("REST request to delete Demo : {}", id);
        demoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
