package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.ImageInsecte;
import com.arsatoll.app.repository.ImageInsecteRepository;
import com.arsatoll.app.service.ImageInsecteService;
import com.arsatoll.app.service.dto.ImageInsecteDTO;
import com.arsatoll.app.service.mapper.ImageInsecteMapper;
import com.arsatoll.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.arsatoll.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ImageInsecteResource REST controller.
 *
 * @see ImageInsecteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class ImageInsecteResourceIntTest {

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    @Autowired
    private ImageInsecteRepository imageInsecteRepository;

    @Autowired
    private ImageInsecteMapper imageInsecteMapper;

    @Autowired
    private ImageInsecteService imageInsecteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restImageInsecteMockMvc;

    private ImageInsecte imageInsecte;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImageInsecteResource imageInsecteResource = new ImageInsecteResource(imageInsecteService);
        this.restImageInsecteMockMvc = MockMvcBuilders.standaloneSetup(imageInsecteResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageInsecte createEntity(EntityManager em) {
        ImageInsecte imageInsecte = new ImageInsecte()
            .imageUrl(DEFAULT_IMAGE_URL);
        return imageInsecte;
    }

    @Before
    public void initTest() {
        imageInsecte = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageInsecte() throws Exception {
        int databaseSizeBeforeCreate = imageInsecteRepository.findAll().size();

        // Create the ImageInsecte
        ImageInsecteDTO imageInsecteDTO = imageInsecteMapper.toDto(imageInsecte);
        restImageInsecteMockMvc.perform(post("/api/image-insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageInsecteDTO)))
            .andExpect(status().isCreated());

        // Validate the ImageInsecte in the database
        List<ImageInsecte> imageInsecteList = imageInsecteRepository.findAll();
        assertThat(imageInsecteList).hasSize(databaseSizeBeforeCreate + 1);
        ImageInsecte testImageInsecte = imageInsecteList.get(imageInsecteList.size() - 1);
        assertThat(testImageInsecte.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
    }

    @Test
    @Transactional
    public void createImageInsecteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageInsecteRepository.findAll().size();

        // Create the ImageInsecte with an existing ID
        imageInsecte.setId(1L);
        ImageInsecteDTO imageInsecteDTO = imageInsecteMapper.toDto(imageInsecte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageInsecteMockMvc.perform(post("/api/image-insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageInsecteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageInsecte in the database
        List<ImageInsecte> imageInsecteList = imageInsecteRepository.findAll();
        assertThat(imageInsecteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllImageInsectes() throws Exception {
        // Initialize the database
        imageInsecteRepository.saveAndFlush(imageInsecte);

        // Get all the imageInsecteList
        restImageInsecteMockMvc.perform(get("/api/image-insectes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageInsecte.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getImageInsecte() throws Exception {
        // Initialize the database
        imageInsecteRepository.saveAndFlush(imageInsecte);

        // Get the imageInsecte
        restImageInsecteMockMvc.perform(get("/api/image-insectes/{id}", imageInsecte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(imageInsecte.getId().intValue()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingImageInsecte() throws Exception {
        // Get the imageInsecte
        restImageInsecteMockMvc.perform(get("/api/image-insectes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageInsecte() throws Exception {
        // Initialize the database
        imageInsecteRepository.saveAndFlush(imageInsecte);

        int databaseSizeBeforeUpdate = imageInsecteRepository.findAll().size();

        // Update the imageInsecte
        ImageInsecte updatedImageInsecte = imageInsecteRepository.findById(imageInsecte.getId()).get();
        // Disconnect from session so that the updates on updatedImageInsecte are not directly saved in db
        em.detach(updatedImageInsecte);
        updatedImageInsecte
            .imageUrl(UPDATED_IMAGE_URL);
        ImageInsecteDTO imageInsecteDTO = imageInsecteMapper.toDto(updatedImageInsecte);

        restImageInsecteMockMvc.perform(put("/api/image-insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageInsecteDTO)))
            .andExpect(status().isOk());

        // Validate the ImageInsecte in the database
        List<ImageInsecte> imageInsecteList = imageInsecteRepository.findAll();
        assertThat(imageInsecteList).hasSize(databaseSizeBeforeUpdate);
        ImageInsecte testImageInsecte = imageInsecteList.get(imageInsecteList.size() - 1);
        assertThat(testImageInsecte.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingImageInsecte() throws Exception {
        int databaseSizeBeforeUpdate = imageInsecteRepository.findAll().size();

        // Create the ImageInsecte
        ImageInsecteDTO imageInsecteDTO = imageInsecteMapper.toDto(imageInsecte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageInsecteMockMvc.perform(put("/api/image-insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageInsecteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageInsecte in the database
        List<ImageInsecte> imageInsecteList = imageInsecteRepository.findAll();
        assertThat(imageInsecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageInsecte() throws Exception {
        // Initialize the database
        imageInsecteRepository.saveAndFlush(imageInsecte);

        int databaseSizeBeforeDelete = imageInsecteRepository.findAll().size();

        // Delete the imageInsecte
        restImageInsecteMockMvc.perform(delete("/api/image-insectes/{id}", imageInsecte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImageInsecte> imageInsecteList = imageInsecteRepository.findAll();
        assertThat(imageInsecteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageInsecte.class);
        ImageInsecte imageInsecte1 = new ImageInsecte();
        imageInsecte1.setId(1L);
        ImageInsecte imageInsecte2 = new ImageInsecte();
        imageInsecte2.setId(imageInsecte1.getId());
        assertThat(imageInsecte1).isEqualTo(imageInsecte2);
        imageInsecte2.setId(2L);
        assertThat(imageInsecte1).isNotEqualTo(imageInsecte2);
        imageInsecte1.setId(null);
        assertThat(imageInsecte1).isNotEqualTo(imageInsecte2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageInsecteDTO.class);
        ImageInsecteDTO imageInsecteDTO1 = new ImageInsecteDTO();
        imageInsecteDTO1.setId(1L);
        ImageInsecteDTO imageInsecteDTO2 = new ImageInsecteDTO();
        assertThat(imageInsecteDTO1).isNotEqualTo(imageInsecteDTO2);
        imageInsecteDTO2.setId(imageInsecteDTO1.getId());
        assertThat(imageInsecteDTO1).isEqualTo(imageInsecteDTO2);
        imageInsecteDTO2.setId(2L);
        assertThat(imageInsecteDTO1).isNotEqualTo(imageInsecteDTO2);
        imageInsecteDTO1.setId(null);
        assertThat(imageInsecteDTO1).isNotEqualTo(imageInsecteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(imageInsecteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(imageInsecteMapper.fromId(null)).isNull();
    }
}
