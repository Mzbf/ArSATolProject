package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.ImageMaladie;
import com.arsatoll.app.repository.ImageMaladieRepository;
import com.arsatoll.app.service.ImageMaladieService;
import com.arsatoll.app.service.dto.ImageMaladieDTO;
import com.arsatoll.app.service.mapper.ImageMaladieMapper;
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
 * Test class for the ImageMaladieResource REST controller.
 *
 * @see ImageMaladieResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class ImageMaladieResourceIntTest {

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    @Autowired
    private ImageMaladieRepository imageMaladieRepository;

    @Autowired
    private ImageMaladieMapper imageMaladieMapper;

    @Autowired
    private ImageMaladieService imageMaladieService;

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

    private MockMvc restImageMaladieMockMvc;

    private ImageMaladie imageMaladie;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImageMaladieResource imageMaladieResource = new ImageMaladieResource(imageMaladieService);
        this.restImageMaladieMockMvc = MockMvcBuilders.standaloneSetup(imageMaladieResource)
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
    public static ImageMaladie createEntity(EntityManager em) {
        ImageMaladie imageMaladie = new ImageMaladie()
            .imageUrl(DEFAULT_IMAGE_URL);
        return imageMaladie;
    }

    @Before
    public void initTest() {
        imageMaladie = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageMaladie() throws Exception {
        int databaseSizeBeforeCreate = imageMaladieRepository.findAll().size();

        // Create the ImageMaladie
        ImageMaladieDTO imageMaladieDTO = imageMaladieMapper.toDto(imageMaladie);
        restImageMaladieMockMvc.perform(post("/api/image-maladies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageMaladieDTO)))
            .andExpect(status().isCreated());

        // Validate the ImageMaladie in the database
        List<ImageMaladie> imageMaladieList = imageMaladieRepository.findAll();
        assertThat(imageMaladieList).hasSize(databaseSizeBeforeCreate + 1);
        ImageMaladie testImageMaladie = imageMaladieList.get(imageMaladieList.size() - 1);
        assertThat(testImageMaladie.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
    }

    @Test
    @Transactional
    public void createImageMaladieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageMaladieRepository.findAll().size();

        // Create the ImageMaladie with an existing ID
        imageMaladie.setId(1L);
        ImageMaladieDTO imageMaladieDTO = imageMaladieMapper.toDto(imageMaladie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageMaladieMockMvc.perform(post("/api/image-maladies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageMaladieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageMaladie in the database
        List<ImageMaladie> imageMaladieList = imageMaladieRepository.findAll();
        assertThat(imageMaladieList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllImageMaladies() throws Exception {
        // Initialize the database
        imageMaladieRepository.saveAndFlush(imageMaladie);

        // Get all the imageMaladieList
        restImageMaladieMockMvc.perform(get("/api/image-maladies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageMaladie.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getImageMaladie() throws Exception {
        // Initialize the database
        imageMaladieRepository.saveAndFlush(imageMaladie);

        // Get the imageMaladie
        restImageMaladieMockMvc.perform(get("/api/image-maladies/{id}", imageMaladie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(imageMaladie.getId().intValue()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingImageMaladie() throws Exception {
        // Get the imageMaladie
        restImageMaladieMockMvc.perform(get("/api/image-maladies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageMaladie() throws Exception {
        // Initialize the database
        imageMaladieRepository.saveAndFlush(imageMaladie);

        int databaseSizeBeforeUpdate = imageMaladieRepository.findAll().size();

        // Update the imageMaladie
        ImageMaladie updatedImageMaladie = imageMaladieRepository.findById(imageMaladie.getId()).get();
        // Disconnect from session so that the updates on updatedImageMaladie are not directly saved in db
        em.detach(updatedImageMaladie);
        updatedImageMaladie
            .imageUrl(UPDATED_IMAGE_URL);
        ImageMaladieDTO imageMaladieDTO = imageMaladieMapper.toDto(updatedImageMaladie);

        restImageMaladieMockMvc.perform(put("/api/image-maladies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageMaladieDTO)))
            .andExpect(status().isOk());

        // Validate the ImageMaladie in the database
        List<ImageMaladie> imageMaladieList = imageMaladieRepository.findAll();
        assertThat(imageMaladieList).hasSize(databaseSizeBeforeUpdate);
        ImageMaladie testImageMaladie = imageMaladieList.get(imageMaladieList.size() - 1);
        assertThat(testImageMaladie.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingImageMaladie() throws Exception {
        int databaseSizeBeforeUpdate = imageMaladieRepository.findAll().size();

        // Create the ImageMaladie
        ImageMaladieDTO imageMaladieDTO = imageMaladieMapper.toDto(imageMaladie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageMaladieMockMvc.perform(put("/api/image-maladies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageMaladieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageMaladie in the database
        List<ImageMaladie> imageMaladieList = imageMaladieRepository.findAll();
        assertThat(imageMaladieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageMaladie() throws Exception {
        // Initialize the database
        imageMaladieRepository.saveAndFlush(imageMaladie);

        int databaseSizeBeforeDelete = imageMaladieRepository.findAll().size();

        // Delete the imageMaladie
        restImageMaladieMockMvc.perform(delete("/api/image-maladies/{id}", imageMaladie.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImageMaladie> imageMaladieList = imageMaladieRepository.findAll();
        assertThat(imageMaladieList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageMaladie.class);
        ImageMaladie imageMaladie1 = new ImageMaladie();
        imageMaladie1.setId(1L);
        ImageMaladie imageMaladie2 = new ImageMaladie();
        imageMaladie2.setId(imageMaladie1.getId());
        assertThat(imageMaladie1).isEqualTo(imageMaladie2);
        imageMaladie2.setId(2L);
        assertThat(imageMaladie1).isNotEqualTo(imageMaladie2);
        imageMaladie1.setId(null);
        assertThat(imageMaladie1).isNotEqualTo(imageMaladie2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageMaladieDTO.class);
        ImageMaladieDTO imageMaladieDTO1 = new ImageMaladieDTO();
        imageMaladieDTO1.setId(1L);
        ImageMaladieDTO imageMaladieDTO2 = new ImageMaladieDTO();
        assertThat(imageMaladieDTO1).isNotEqualTo(imageMaladieDTO2);
        imageMaladieDTO2.setId(imageMaladieDTO1.getId());
        assertThat(imageMaladieDTO1).isEqualTo(imageMaladieDTO2);
        imageMaladieDTO2.setId(2L);
        assertThat(imageMaladieDTO1).isNotEqualTo(imageMaladieDTO2);
        imageMaladieDTO1.setId(null);
        assertThat(imageMaladieDTO1).isNotEqualTo(imageMaladieDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(imageMaladieMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(imageMaladieMapper.fromId(null)).isNull();
    }
}
