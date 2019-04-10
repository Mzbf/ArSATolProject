package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.ImageMaladieService;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.ImageMaladieDTO;
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
 * REST controller for managing ImageMaladie.
 */
@RestController
@RequestMapping("/api")
public class ImageMaladieResource {

    private final Logger log = LoggerFactory.getLogger(ImageMaladieResource.class);

    private static final String ENTITY_NAME = "imageMaladie";

    private final ImageMaladieService imageMaladieService;

    public ImageMaladieResource(ImageMaladieService imageMaladieService) {
        this.imageMaladieService = imageMaladieService;
    }

    /**
     * POST  /image-maladies : Create a new imageMaladie.
     *
     * @param imageMaladieDTO the imageMaladieDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imageMaladieDTO, or with status 400 (Bad Request) if the imageMaladie has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/image-maladies")
    public ResponseEntity<ImageMaladieDTO> createImageMaladie(@RequestBody ImageMaladieDTO imageMaladieDTO) throws URISyntaxException {
        log.debug("REST request to save ImageMaladie : {}", imageMaladieDTO);
        if (imageMaladieDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageMaladie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageMaladieDTO result = imageMaladieService.save(imageMaladieDTO);
        return ResponseEntity.created(new URI("/api/image-maladies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /image-maladies : Updates an existing imageMaladie.
     *
     * @param imageMaladieDTO the imageMaladieDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imageMaladieDTO,
     * or with status 400 (Bad Request) if the imageMaladieDTO is not valid,
     * or with status 500 (Internal Server Error) if the imageMaladieDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/image-maladies")
    public ResponseEntity<ImageMaladieDTO> updateImageMaladie(@RequestBody ImageMaladieDTO imageMaladieDTO) throws URISyntaxException {
        log.debug("REST request to update ImageMaladie : {}", imageMaladieDTO);
        if (imageMaladieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageMaladieDTO result = imageMaladieService.save(imageMaladieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, imageMaladieDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /image-maladies : get all the imageMaladies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imageMaladies in body
     */
    @GetMapping("/image-maladies")
    public List<ImageMaladieDTO> getAllImageMaladies() {
        log.debug("REST request to get all ImageMaladies");
        return imageMaladieService.findAll();
    }

    /**
     * GET  /image-maladies/:id : get the "id" imageMaladie.
     *
     * @param id the id of the imageMaladieDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imageMaladieDTO, or with status 404 (Not Found)
     */
    @GetMapping("/image-maladies/{id}")
    public ResponseEntity<ImageMaladieDTO> getImageMaladie(@PathVariable Long id) {
        log.debug("REST request to get ImageMaladie : {}", id);
        Optional<ImageMaladieDTO> imageMaladieDTO = imageMaladieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageMaladieDTO);
    }

    /**
     * DELETE  /image-maladies/:id : delete the "id" imageMaladie.
     *
     * @param id the id of the imageMaladieDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/image-maladies/{id}")
    public ResponseEntity<Void> deleteImageMaladie(@PathVariable Long id) {
        log.debug("REST request to delete ImageMaladie : {}", id);
        imageMaladieService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
