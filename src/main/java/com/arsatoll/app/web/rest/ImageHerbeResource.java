package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.ImageHerbeService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.ImageHerbeDTO;
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
 * REST controller for managing ImageHerbe.
 */
@RestController
@RequestMapping("/api")
public class ImageHerbeResource {

    private final Logger log = LoggerFactory.getLogger(ImageHerbeResource.class);

    private static final String ENTITY_NAME = "imageHerbe";

    private final ImageHerbeService imageHerbeService;

    public ImageHerbeResource(ImageHerbeService imageHerbeService) {
        this.imageHerbeService = imageHerbeService;
    }

    /**
     * POST  /image-herbes : Create a new imageHerbe.
     *
     * @param imageHerbeDTO the imageHerbeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imageHerbeDTO, or with status 400 (Bad Request) if the imageHerbe has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/image-herbes")
    public ResponseEntity<ImageHerbeDTO> createImageHerbe(@RequestBody ImageHerbeDTO imageHerbeDTO) throws URISyntaxException {
        log.debug("REST request to save ImageHerbe : {}", imageHerbeDTO);
        if (imageHerbeDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageHerbe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageHerbeDTO result = imageHerbeService.save(imageHerbeDTO);
        return ResponseEntity.created(new URI("/api/image-herbes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /image-herbes : Updates an existing imageHerbe.
     *
     * @param imageHerbeDTO the imageHerbeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imageHerbeDTO,
     * or with status 400 (Bad Request) if the imageHerbeDTO is not valid,
     * or with status 500 (Internal Server Error) if the imageHerbeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/image-herbes")
    public ResponseEntity<ImageHerbeDTO> updateImageHerbe(@RequestBody ImageHerbeDTO imageHerbeDTO) throws URISyntaxException {
        log.debug("REST request to update ImageHerbe : {}", imageHerbeDTO);
        if (imageHerbeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageHerbeDTO result = imageHerbeService.save(imageHerbeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, imageHerbeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /image-herbes : get all the imageHerbes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imageHerbes in body
     */
    @GetMapping("/image-herbes")
    public List<ImageHerbeDTO> getAllImageHerbes() {
        log.debug("REST request to get all ImageHerbes");
        return imageHerbeService.findAll();
    }

    /**
     * GET  /image-herbes/:id : get the "id" imageHerbe.
     *
     * @param id the id of the imageHerbeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imageHerbeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/image-herbes/{id}")
    public ResponseEntity<ImageHerbeDTO> getImageHerbe(@PathVariable Long id) {
        log.debug("REST request to get ImageHerbe : {}", id);
        Optional<ImageHerbeDTO> imageHerbeDTO = imageHerbeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageHerbeDTO);
    }

    /**
     * DELETE  /image-herbes/:id : delete the "id" imageHerbe.
     *
     * @param id the id of the imageHerbeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/image-herbes/{id}")
    public ResponseEntity<Void> deleteImageHerbe(@PathVariable Long id) {
        log.debug("REST request to delete ImageHerbe : {}", id);
        imageHerbeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
