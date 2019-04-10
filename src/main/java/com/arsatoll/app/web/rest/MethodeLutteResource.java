package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.MethodeLutteService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.MethodeLutteDTO;
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
 * REST controller for managing MethodeLutte.
 */
@RestController
@RequestMapping("/api")
public class MethodeLutteResource {

    private final Logger log = LoggerFactory.getLogger(MethodeLutteResource.class);

    private static final String ENTITY_NAME = "methodeLutte";

    private final MethodeLutteService methodeLutteService;

    public MethodeLutteResource(MethodeLutteService methodeLutteService) {
        this.methodeLutteService = methodeLutteService;
    }

    /**
     * POST  /methode-luttes : Create a new methodeLutte.
     *
     * @param methodeLutteDTO the methodeLutteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new methodeLutteDTO, or with status 400 (Bad Request) if the methodeLutte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/methode-luttes")
    public ResponseEntity<MethodeLutteDTO> createMethodeLutte(@RequestBody MethodeLutteDTO methodeLutteDTO) throws URISyntaxException {
        log.debug("REST request to save MethodeLutte : {}", methodeLutteDTO);
        if (methodeLutteDTO.getId() != null) {
            throw new BadRequestAlertException("A new methodeLutte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MethodeLutteDTO result = methodeLutteService.save(methodeLutteDTO);
        return ResponseEntity.created(new URI("/api/methode-luttes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /methode-luttes : Updates an existing methodeLutte.
     *
     * @param methodeLutteDTO the methodeLutteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated methodeLutteDTO,
     * or with status 400 (Bad Request) if the methodeLutteDTO is not valid,
     * or with status 500 (Internal Server Error) if the methodeLutteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/methode-luttes")
    public ResponseEntity<MethodeLutteDTO> updateMethodeLutte(@RequestBody MethodeLutteDTO methodeLutteDTO) throws URISyntaxException {
        log.debug("REST request to update MethodeLutte : {}", methodeLutteDTO);
        if (methodeLutteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MethodeLutteDTO result = methodeLutteService.save(methodeLutteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, methodeLutteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /methode-luttes : get all the methodeLuttes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of methodeLuttes in body
     */
    @GetMapping("/methode-luttes")
    public List<MethodeLutteDTO> getAllMethodeLuttes() {
        log.debug("REST request to get all MethodeLuttes");
        return methodeLutteService.findAll();
    }

    /**
     * GET  /methode-luttes/:id : get the "id" methodeLutte.
     *
     * @param id the id of the methodeLutteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the methodeLutteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/methode-luttes/{id}")
    public ResponseEntity<MethodeLutteDTO> getMethodeLutte(@PathVariable Long id) {
        log.debug("REST request to get MethodeLutte : {}", id);
        Optional<MethodeLutteDTO> methodeLutteDTO = methodeLutteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(methodeLutteDTO);
    }

    /**
     * DELETE  /methode-luttes/:id : delete the "id" methodeLutte.
     *
     * @param id the id of the methodeLutteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/methode-luttes/{id}")
    public ResponseEntity<Void> deleteMethodeLutte(@PathVariable Long id) {
        log.debug("REST request to delete MethodeLutte : {}", id);
        methodeLutteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
