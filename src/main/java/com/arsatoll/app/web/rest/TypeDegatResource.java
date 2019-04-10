package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.TypeDegatService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.TypeDegatDTO;
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
 * REST controller for managing TypeDegat.
 */
@RestController
@RequestMapping("/api")
public class TypeDegatResource {

    private final Logger log = LoggerFactory.getLogger(TypeDegatResource.class);

    private static final String ENTITY_NAME = "typeDegat";

    private final TypeDegatService typeDegatService;

    public TypeDegatResource(TypeDegatService typeDegatService) {
        this.typeDegatService = typeDegatService;
    }

    /**
     * POST  /type-degats : Create a new typeDegat.
     *
     * @param typeDegatDTO the typeDegatDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeDegatDTO, or with status 400 (Bad Request) if the typeDegat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-degats")
    public ResponseEntity<TypeDegatDTO> createTypeDegat(@Valid @RequestBody TypeDegatDTO typeDegatDTO) throws URISyntaxException {
        log.debug("REST request to save TypeDegat : {}", typeDegatDTO);
        if (typeDegatDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeDegat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeDegatDTO result = typeDegatService.save(typeDegatDTO);
        return ResponseEntity.created(new URI("/api/type-degats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-degats : Updates an existing typeDegat.
     *
     * @param typeDegatDTO the typeDegatDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeDegatDTO,
     * or with status 400 (Bad Request) if the typeDegatDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeDegatDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-degats")
    public ResponseEntity<TypeDegatDTO> updateTypeDegat(@Valid @RequestBody TypeDegatDTO typeDegatDTO) throws URISyntaxException {
        log.debug("REST request to update TypeDegat : {}", typeDegatDTO);
        if (typeDegatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeDegatDTO result = typeDegatService.save(typeDegatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeDegatDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-degats : get all the typeDegats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of typeDegats in body
     */
    @GetMapping("/type-degats")
    public List<TypeDegatDTO> getAllTypeDegats() {
        log.debug("REST request to get all TypeDegats");
        return typeDegatService.findAll();
    }

    /**
     * GET  /type-degats/:id : get the "id" typeDegat.
     *
     * @param id the id of the typeDegatDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeDegatDTO, or with status 404 (Not Found)
     */
    @GetMapping("/type-degats/{id}")
    public ResponseEntity<TypeDegatDTO> getTypeDegat(@PathVariable Long id) {
        log.debug("REST request to get TypeDegat : {}", id);
        Optional<TypeDegatDTO> typeDegatDTO = typeDegatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeDegatDTO);
    }

    /**
     * DELETE  /type-degats/:id : delete the "id" typeDegat.
     *
     * @param id the id of the typeDegatDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-degats/{id}")
    public ResponseEntity<Void> deleteTypeDegat(@PathVariable Long id) {
        log.debug("REST request to delete TypeDegat : {}", id);
        typeDegatService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
