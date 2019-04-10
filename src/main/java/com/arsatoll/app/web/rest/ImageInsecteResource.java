package com.arsatoll.app.web.rest;
import com.arsatoll.app.domain.ImageInsecte;
import com.arsatoll.app.service.ImageInsecteService;
import com.arsatoll.app.service.dto.FamilleDTO;
import com.arsatoll.app.service.dto.ImageDTO;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.ImageInsecteDTO;
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
 * REST controller for managing ImageInsecte.
 */
@RestController
@RequestMapping("/api")
public class ImageInsecteResource {

    private final Logger log = LoggerFactory.getLogger(ImageInsecteResource.class);

    private static final String ENTITY_NAME = "imageInsecte";

    private final ImageInsecteService imageInsecteService;

    public ImageInsecteResource(ImageInsecteService imageInsecteService) {
        this.imageInsecteService = imageInsecteService;
    }

    /**
     * POST  /image-insectes : Create a new imageInsecte.
     *
     * @param imageInsecteDTO the imageInsecteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imageInsecteDTO, or with status 400 (Bad Request) if the imageInsecte has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/image-insectes")
    public ResponseEntity<ImageInsecteDTO> createImageInsecte(@RequestBody ImageInsecteDTO imageInsecteDTO) throws URISyntaxException {
        log.debug("REST request to save ImageInsecte : {}", imageInsecteDTO);
        if (imageInsecteDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageInsecte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageInsecteDTO result = imageInsecteService.save(imageInsecteDTO);
        return ResponseEntity.created(new URI("/api/image-insectes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping(value = "/imageInsecte")
    public ResponseEntity<ImageInsecteDTO> ImageInsecteSave(@RequestParam("file") MultipartFile file, @RequestParam("insecte") String insecte) throws IOException, URISyntaxException {


        ImageInsecteDTO image = new ObjectMapper().readValue(insecte,ImageInsecteDTO.class);
        if (image.getId() != null) {
            throw new BadRequestAlertException("A new Image cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String nomImage = file.getOriginalFilename();
        String nomImageModife = FilenameUtils.getBaseName(nomImage)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(nomImage);
        File path = new File("/home/mzbf/arsatoll/arsatollservice/image/ImageInsecte"+ nomImageModife);

        FileUtils.writeByteArrayToFile(path,file.getBytes());

        image.setImageUrl(nomImageModife);
        ImageInsecteDTO famdb = imageInsecteService.save(image);
        return ResponseEntity.created(new URI("/api/insctes/" + famdb.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, famdb.getId().toString()))
            .body(famdb);
    }

    /**
     * PUT  /image-insectes : Updates an existing imageInsecte.
     *
     * @param imageInsecteDTO the imageInsecteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imageInsecteDTO,
     * or with status 400 (Bad Request) if the imageInsecteDTO is not valid,
     * or with status 500 (Internal Server Error) if the imageInsecteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/image-insectes")
    public ResponseEntity<ImageInsecteDTO> updateImageInsecte(@RequestBody ImageInsecteDTO imageInsecteDTO) throws URISyntaxException {
        log.debug("REST request to update ImageInsecte : {}", imageInsecteDTO);
        if (imageInsecteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageInsecteDTO result = imageInsecteService.save(imageInsecteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, imageInsecteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /image-insectes : get all the imageInsectes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imageInsectes in body
     */
    @GetMapping("/image-insectes")
    public List<ImageInsecteDTO> getAllImageInsectes() {
        log.debug("REST request to get all ImageInsectes");
        return imageInsecteService.findAll();
    }

    /**
     * GET  /image-insectes/:id : get the "id" imageInsecte.
     *
     * @param id the id of the imageInsecteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imageInsecteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/image-insectes/{id}")
    public ResponseEntity<ImageInsecteDTO> getImageInsecte(@PathVariable Long id) {
        log.debug("REST request to get ImageInsecte : {}", id);
        Optional<ImageInsecteDTO> imageInsecteDTO = imageInsecteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageInsecteDTO);
    }

    /**
     * DELETE  /image-insectes/:id : delete the "id" imageInsecte.
     *
     * @param id the id of the imageInsecteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/image-insectes/{id}")
    public ResponseEntity<Void> deleteImageInsecte(@PathVariable Long id) {
        log.debug("REST request to delete ImageInsecte : {}", id);
        imageInsecteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
