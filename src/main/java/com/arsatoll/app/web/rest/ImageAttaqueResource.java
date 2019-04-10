package com.arsatoll.app.web.rest;
import com.arsatoll.app.domain.ImageAttaque;
import com.arsatoll.app.service.ImageAttaqueService;
import com.arsatoll.app.service.dto.ImageInsecteDTO;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.ImageAttaqueDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ImageAttaque.
 */
@RestController
@RequestMapping("/api")
public class ImageAttaqueResource {

    private final Logger log = LoggerFactory.getLogger(ImageAttaqueResource.class);

    private static final String ENTITY_NAME = "imageAttaque";

    private final ImageAttaqueService imageAttaqueService;

    public ImageAttaqueResource(ImageAttaqueService imageAttaqueService) {
        this.imageAttaqueService = imageAttaqueService;
    }

    /**
     * POST  /image-attaques : Create a new imageAttaque.
     *
     * @param imageAttaqueDTO the imageAttaqueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imageAttaqueDTO, or with status 400 (Bad Request) if the imageAttaque has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/image-attaques")
    public ResponseEntity<ImageAttaqueDTO> createImageAttaque(@RequestBody ImageAttaqueDTO imageAttaqueDTO) throws URISyntaxException {
        log.debug("REST request to save ImageAttaque : {}", imageAttaqueDTO);
        if (imageAttaqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageAttaque cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageAttaqueDTO result = imageAttaqueService.save(imageAttaqueDTO);
        return ResponseEntity.created(new URI("/api/image-attaques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping(value = "/imageAttaque")
    public ResponseEntity<ImageAttaqueDTO> ImageAttaqueSave(@RequestParam("file") MultipartFile file, @RequestParam("attaque") String insecte) throws IOException, URISyntaxException {


        ImageAttaqueDTO image = new ObjectMapper().readValue(insecte,ImageAttaqueDTO.class);
        if (image.getId() != null) {
            throw new BadRequestAlertException("A new Image cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String nomImage = file.getOriginalFilename();
        String nomImageModife = FilenameUtils.getBaseName(nomImage)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(nomImage);
        File path = new File("/home/mzbf/arsatoll/arsatollservice/image/ImageInsecte"+ nomImageModife);

        FileUtils.writeByteArrayToFile(path,file.getBytes());

        image.setImageUrl(nomImageModife);
        ImageAttaqueDTO imageAttaqueDTO = imageAttaqueService.save(image);
        return ResponseEntity.created(new URI("/api/attaques/" + imageAttaqueDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, imageAttaqueDTO.getId().toString()))
            .body(imageAttaqueDTO);
    }

    /**
     * PUT  /image-attaques : Updates an existing imageAttaque.
     *
     * @param imageAttaqueDTO the imageAttaqueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imageAttaqueDTO,
     * or with status 400 (Bad Request) if the imageAttaqueDTO is not valid,
     * or with status 500 (Internal Server Error) if the imageAttaqueDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/image-attaques")
    public ResponseEntity<ImageAttaqueDTO> updateImageAttaque(@RequestBody ImageAttaqueDTO imageAttaqueDTO) throws URISyntaxException {
        log.debug("REST request to update ImageAttaque : {}", imageAttaqueDTO);
        if (imageAttaqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageAttaqueDTO result = imageAttaqueService.save(imageAttaqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, imageAttaqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /image-attaques : get all the imageAttaques.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imageAttaques in body
     */
    @GetMapping("/image-attaques")
    public List<ImageAttaqueDTO> getAllImageAttaques() {
        log.debug("REST request to get all ImageAttaques");
        return imageAttaqueService.findAll();
    }

    /**
     * GET  /image-attaques/:id : get the "id" imageAttaque.
     *
     * @param id the id of the imageAttaqueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imageAttaqueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/image-attaques/{id}")
    public ResponseEntity<ImageAttaqueDTO> getImageAttaque(@PathVariable Long id) {
        log.debug("REST request to get ImageAttaque : {}", id);
        Optional<ImageAttaqueDTO> imageAttaqueDTO = imageAttaqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageAttaqueDTO);
    }

    /**
     * DELETE  /image-attaques/:id : delete the "id" imageAttaque.
     *
     * @param id the id of the imageAttaqueDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/image-attaques/{id}")
    public ResponseEntity<Void> deleteImageAttaque(@PathVariable Long id) {
        log.debug("REST request to delete ImageAttaque : {}", id);
        imageAttaqueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
