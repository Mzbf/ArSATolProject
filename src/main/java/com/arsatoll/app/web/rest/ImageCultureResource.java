package com.arsatoll.app.web.rest;
import com.arsatoll.app.service.ImageCultureService;
import com.arsatoll.app.service.dto.ImageInsecteDTO;
import com.arsatoll.app.web.rest.errors.BadRequestAlertException;
import com.arsatoll.app.web.rest.util.HeaderUtil;
import com.arsatoll.app.service.dto.ImageCultureDTO;
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
 * REST controller for managing ImageCulture.
 */
@RestController
@RequestMapping("/api")
public class ImageCultureResource {

    private final Logger log = LoggerFactory.getLogger(ImageCultureResource.class);

    private static final String ENTITY_NAME = "imageCulture";

    private final ImageCultureService imageCultureService;

    public ImageCultureResource(ImageCultureService imageCultureService) {
        this.imageCultureService = imageCultureService;
    }

    /**
     * POST  /image-cultures : Create a new imageCulture.
     *
     * @param imageCultureDTO the imageCultureDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new imageCultureDTO, or with status 400 (Bad Request) if the imageCulture has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/image-cultures")
    public ResponseEntity<ImageCultureDTO> createImageCulture(@RequestBody ImageCultureDTO imageCultureDTO) throws URISyntaxException {
        log.debug("REST request to save ImageCulture : {}", imageCultureDTO);
        if (imageCultureDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageCulture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageCultureDTO result = imageCultureService.save(imageCultureDTO);
        return ResponseEntity.created(new URI("/api/image-cultures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    @PostMapping(value = "/imageCulture")
    public ResponseEntity<ImageCultureDTO> ImageInsecteSave(@RequestParam("file") MultipartFile file, @RequestParam("culture") String culture) throws IOException, URISyntaxException {


        ImageCultureDTO image = new ObjectMapper().readValue(culture,ImageCultureDTO.class);
        if (image.getId() != null) {
            throw new BadRequestAlertException("A new Image cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String nomImage = file.getOriginalFilename();
        String nomImageModife = FilenameUtils.getBaseName(nomImage)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(nomImage);
        File path = new File("/home/mzbf/arsatoll/arsatollservice/image/ImageInsecte"+ nomImageModife);

        FileUtils.writeByteArrayToFile(path,file.getBytes());

        image.setImageUrl(nomImageModife);
        ImageCultureDTO famdb = imageCultureService.save(image);
        return ResponseEntity.created(new URI("/api/cultures/" + famdb.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, famdb.getId().toString()))
            .body(famdb);
    }

    /**
     * PUT  /image-cultures : Updates an existing imageCulture.
     *
     * @param imageCultureDTO the imageCultureDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated imageCultureDTO,
     * or with status 400 (Bad Request) if the imageCultureDTO is not valid,
     * or with status 500 (Internal Server Error) if the imageCultureDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/image-cultures")
    public ResponseEntity<ImageCultureDTO> updateImageCulture(@RequestBody ImageCultureDTO imageCultureDTO) throws URISyntaxException {
        log.debug("REST request to update ImageCulture : {}", imageCultureDTO);
        if (imageCultureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageCultureDTO result = imageCultureService.save(imageCultureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, imageCultureDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /image-cultures : get all the imageCultures.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of imageCultures in body
     */
    @GetMapping("/image-cultures")
    public List<ImageCultureDTO> getAllImageCultures() {
        log.debug("REST request to get all ImageCultures");
        return imageCultureService.findAll();
    }

    /**
     * GET  /image-cultures/:id : get the "id" imageCulture.
     *
     * @param id the id of the imageCultureDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the imageCultureDTO, or with status 404 (Not Found)
     */
    @GetMapping("/image-cultures/{id}")
    public ResponseEntity<ImageCultureDTO> getImageCulture(@PathVariable Long id) {
        log.debug("REST request to get ImageCulture : {}", id);
        Optional<ImageCultureDTO> imageCultureDTO = imageCultureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageCultureDTO);
    }

    /**
     * DELETE  /image-cultures/:id : delete the "id" imageCulture.
     *
     * @param id the id of the imageCultureDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/image-cultures/{id}")
    public ResponseEntity<Void> deleteImageCulture(@PathVariable Long id) {
        log.debug("REST request to delete ImageCulture : {}", id);
        imageCultureService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
