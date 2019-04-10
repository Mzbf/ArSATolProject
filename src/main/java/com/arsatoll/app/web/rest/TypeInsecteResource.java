package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.TypeInsecteService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.TypeInsecteDTO;
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
 * REST controller for managing TypeInsecte.
 */
@RestController
@RequestMapping("/api")
public class TypeInsecteResource {

    private final Logger log = LoggerFactory.getLogger(TypeInsecteResource.class);

    private static final String ENTITY_NAME = "typeInsecte";

    private final TypeInsecteService typeInsecteService;

    public TypeInsecteResource(TypeInsecteService typeInsecteService) {
        this.typeInsecteService = typeInsecteService;
    }

    /**
     * POST  /type-insectes : Create a new typeInsecte.
     *
     * @param typeInsecteDTO the typeInsecteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new typeInsecteDTO, or with status 400 (Bad Request) if the typeInsecte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/type-insectes")
    public ResponseEntity<TypeInsecteDTO> createTypeInsecte(@RequestBody TypeInsecteDTO typeInsecteDTO) throws URISyntaxException {
        log.debug("REST request to save TypeInsecte : {}", typeInsecteDTO);
        if (typeInsecteDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeInsecte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeInsecteDTO result = typeInsecteService.save(typeInsecteDTO);
        return ResponseEntity.created(new URI("/api/type-insectes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /type-insectes : Updates an existing typeInsecte.
     *
     * @param typeInsecteDTO the typeInsecteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated typeInsecteDTO,
     * or with status 400 (Bad Request) if the typeInsecteDTO is not valid,
     * or with status 500 (Internal Server Error) if the typeInsecteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/type-insectes")
    public ResponseEntity<TypeInsecteDTO> updateTypeInsecte(@RequestBody TypeInsecteDTO typeInsecteDTO) throws URISyntaxException {
        log.debug("REST request to update TypeInsecte : {}", typeInsecteDTO);
        if (typeInsecteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeInsecteDTO result = typeInsecteService.save(typeInsecteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, typeInsecteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /type-insectes : get all the typeInsectes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of typeInsectes in body
     */
    @GetMapping("/type-insectes")
    public List<TypeInsecteDTO> getAllTypeInsectes() {
        log.debug("REST request to get all TypeInsectes");
        return typeInsecteService.findAll();
    }

    /**
     * GET  /type-insectes/:id : get the "id" typeInsecte.
     *
     * @param id the id of the typeInsecteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the typeInsecteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/type-insectes/{id}")
    public ResponseEntity<TypeInsecteDTO> getTypeInsecte(@PathVariable Long id) {
        log.debug("REST request to get TypeInsecte : {}", id);
        Optional<TypeInsecteDTO> typeInsecteDTO = typeInsecteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeInsecteDTO);
    }

    /**
     * DELETE  /type-insectes/:id : delete the "id" typeInsecte.
     *
     * @param id the id of the typeInsecteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/type-insectes/{id}")
    public ResponseEntity<Void> deleteTypeInsecte(@PathVariable Long id) {
        log.debug("REST request to delete TypeInsecte : {}", id);
        typeInsecteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
