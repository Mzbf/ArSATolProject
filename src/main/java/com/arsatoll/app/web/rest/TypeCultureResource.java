package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.TypeCultureService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.TypeCultureDTO;
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
 * REST controller for managing TypeCulture.
 */
@RestController
@RequestMapping("/api")
public class TypeCultureResource {

    private final Logger log = LoggerFactory.getLogger(TypeCultureResource.class);

    private static final String ENTITY_NAME = "typeCulture";

    private final TypeCultureService typeCultureService;

    public TypeCultureResource(TypeCultureService typeCultureService) {
        this.typeCultureService = typeCultureService;
    }

    /**
     * POST  /type-cultures : Create a new typeCulture.
     *
     * @param typeCultureDTO the typeCultureDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeCultureDTO, or with status 400 (Bad Request) if the typeCulture has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-cultures")
    public ResponseEntity<TypeCultureDTO> createTypeCulture(@RequestBody TypeCultureDTO typeCultureDTO) throws URISyntaxException {
        log.debug("REST request to save TypeCulture : {}", typeCultureDTO);
        if (typeCultureDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeCulture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeCultureDTO result = typeCultureService.save(typeCultureDTO);
        return ResponseEntity.created(new URI("/api/type-cultures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-cultures : Updates an existing typeCulture.
     *
     * @param typeCultureDTO the typeCultureDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeCultureDTO,
     * or with status 400 (Bad Request) if the typeCultureDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeCultureDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-cultures")
    public ResponseEntity<TypeCultureDTO> updateTypeCulture(@RequestBody TypeCultureDTO typeCultureDTO) throws URISyntaxException {
        log.debug("REST request to update TypeCulture : {}", typeCultureDTO);
        if (typeCultureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeCultureDTO result = typeCultureService.save(typeCultureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeCultureDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-cultures : get all the typeCultures.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of typeCultures in body
     */
    @GetMapping("/type-cultures")
    public List<TypeCultureDTO> getAllTypeCultures() {
        log.debug("REST request to get all TypeCultures");
        return typeCultureService.findAll();
    }

    /**
     * GET  /type-cultures/:id : get the "id" typeCulture.
     *
     * @param id the id of the typeCultureDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeCultureDTO, or with status 404 (Not Found)
     */
    @GetMapping("/type-cultures/{id}")
    public ResponseEntity<TypeCultureDTO> getTypeCulture(@PathVariable Long id) {
        log.debug("REST request to get TypeCulture : {}", id);
        Optional<TypeCultureDTO> typeCultureDTO = typeCultureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeCultureDTO);
    }

    /**
     * DELETE  /type-cultures/:id : delete the "id" typeCulture.
     *
     * @param id the id of the typeCultureDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-cultures/{id}")
    public ResponseEntity<Void> deleteTypeCulture(@PathVariable Long id) {
        log.debug("REST request to delete TypeCulture : {}", id);
        typeCultureService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
