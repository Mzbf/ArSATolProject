package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.ImageEnvoyeService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.ImageEnvoyeDTO;
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
 * REST controller for managing ImageEnvoye.
 */
@RestController
@RequestMapping("/api")
public class ImageEnvoyeResource {

    private final Logger log = LoggerFactory.getLogger(ImageEnvoyeResource.class);

    private static final String ENTITY_NAME = "imageEnvoye";

    private final ImageEnvoyeService imageEnvoyeService;

    public ImageEnvoyeResource(ImageEnvoyeService imageEnvoyeService) {
        this.imageEnvoyeService = imageEnvoyeService;
    }

    /**
     * POST  /image-envoyes : Create a new imageEnvoye.
     *
     * @param imageEnvoyeDTO the imageEnvoyeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imageEnvoyeDTO, or with status 400 (Bad Request) if the imageEnvoye has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/image-envoyes")
    public ResponseEntity<ImageEnvoyeDTO> createImageEnvoye(@RequestBody ImageEnvoyeDTO imageEnvoyeDTO) throws URISyntaxException {
        log.debug("REST request to save ImageEnvoye : {}", imageEnvoyeDTO);
        if (imageEnvoyeDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageEnvoye cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageEnvoyeDTO result = imageEnvoyeService.save(imageEnvoyeDTO);
        return ResponseEntity.created(new URI("/api/image-envoyes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /image-envoyes : Updates an existing imageEnvoye.
     *
     * @param imageEnvoyeDTO the imageEnvoyeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imageEnvoyeDTO,
     * or with status 400 (Bad Request) if the imageEnvoyeDTO is not valid,
     * or with status 500 (Internal Server Error) if the imageEnvoyeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/image-envoyes")
    public ResponseEntity<ImageEnvoyeDTO> updateImageEnvoye(@RequestBody ImageEnvoyeDTO imageEnvoyeDTO) throws URISyntaxException {
        log.debug("REST request to update ImageEnvoye : {}", imageEnvoyeDTO);
        if (imageEnvoyeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageEnvoyeDTO result = imageEnvoyeService.save(imageEnvoyeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, imageEnvoyeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /image-envoyes : get all the imageEnvoyes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imageEnvoyes in body
     */
    @GetMapping("/image-envoyes")
    public List<ImageEnvoyeDTO> getAllImageEnvoyes() {
        log.debug("REST request to get all ImageEnvoyes");
        return imageEnvoyeService.findAll();
    }

    /**
     * GET  /image-envoyes/:id : get the "id" imageEnvoye.
     *
     * @param id the id of the imageEnvoyeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imageEnvoyeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/image-envoyes/{id}")
    public ResponseEntity<ImageEnvoyeDTO> getImageEnvoye(@PathVariable Long id) {
        log.debug("REST request to get ImageEnvoye : {}", id);
        Optional<ImageEnvoyeDTO> imageEnvoyeDTO = imageEnvoyeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageEnvoyeDTO);
    }

    /**
     * DELETE  /image-envoyes/:id : delete the "id" imageEnvoye.
     *
     * @param id the id of the imageEnvoyeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/image-envoyes/{id}")
    public ResponseEntity<Void> deleteImageEnvoye(@PathVariable Long id) {
        log.debug("REST request to delete ImageEnvoye : {}", id);
        imageEnvoyeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
