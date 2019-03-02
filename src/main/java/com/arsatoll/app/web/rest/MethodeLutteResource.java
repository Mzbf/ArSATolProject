package com.arsatoll.app.web.rest;
import com.arsatoll.app.domain.MethodeLutte;
import com.arsatoll.app.service.MethodeLutteService;
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
     * @param methodeLutte the methodeLutte to create
     * @return the ResponseEntity with status 201 (Created) and with body the new methodeLutte, or with status 400 (Bad Request) if the methodeLutte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/methode-luttes")
    public ResponseEntity<MethodeLutte> createMethodeLutte(@RequestBody MethodeLutte methodeLutte) throws URISyntaxException {
        log.debug("REST request to save MethodeLutte : {}", methodeLutte);
        if (methodeLutte.getId() != null) {
            throw new BadRequestAlertException("A new methodeLutte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MethodeLutte result = methodeLutteService.save(methodeLutte);
        return ResponseEntity.created(new URI("/api/methode-luttes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /methode-luttes : Updates an existing methodeLutte.
     *
     * @param methodeLutte the methodeLutte to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated methodeLutte,
     * or with status 400 (Bad Request) if the methodeLutte is not valid,
     * or with status 500 (Internal Server Error) if the methodeLutte couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/methode-luttes")
    public ResponseEntity<MethodeLutte> updateMethodeLutte(@RequestBody MethodeLutte methodeLutte) throws URISyntaxException {
        log.debug("REST request to update MethodeLutte : {}", methodeLutte);
        if (methodeLutte.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MethodeLutte result = methodeLutteService.save(methodeLutte);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, methodeLutte.getId().toString()))
            .body(result);
    }

    /**
     * GET  /methode-luttes : get all the methodeLuttes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of methodeLuttes in body
     */
    @GetMapping("/methode-luttes")
    public List<MethodeLutte> getAllMethodeLuttes() {
        log.debug("REST request to get all MethodeLuttes");
        return methodeLutteService.findAll();
    }

    /**
     * GET  /methode-luttes/:id : get the "id" methodeLutte.
     *
     * @param id the id of the methodeLutte to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the methodeLutte, or with status 404 (Not Found)
     */
    @GetMapping("/methode-luttes/{id}")
    public ResponseEntity<MethodeLutte> getMethodeLutte(@PathVariable Long id) {
        log.debug("REST request to get MethodeLutte : {}", id);
        Optional<MethodeLutte> methodeLutte = methodeLutteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(methodeLutte);
    }

    /**
     * DELETE  /methode-luttes/:id : delete the "id" methodeLutte.
     *
     * @param id the id of the methodeLutte to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/methode-luttes/{id}")
    public ResponseEntity<Void> deleteMethodeLutte(@PathVariable Long id) {
        log.debug("REST request to delete MethodeLutte : {}", id);
        methodeLutteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
