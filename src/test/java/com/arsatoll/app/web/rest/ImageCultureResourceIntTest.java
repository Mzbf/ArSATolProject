package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.ImageCulture;
import com.arsatoll.app.repository.ImageCultureRepository;
import com.arsatoll.app.service.ImageCultureService;
import com.arsatoll.app.service.dto.ImageCultureDTO;
import com.arsatoll.app.service.mapper.ImageCultureMapper;
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
 * Test class for the ImageCultureResource REST controller.
 *
 * @see ImageCultureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class ImageCultureResourceIntTest {

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    @Autowired
    private ImageCultureRepository imageCultureRepository;

    @Autowired
    private ImageCultureMapper imageCultureMapper;

    @Autowired
    private ImageCultureService imageCultureService;

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

    private MockMvc restImageCultureMockMvc;

    private ImageCulture imageCulture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImageCultureResource imageCultureResource = new ImageCultureResource(imageCultureService);
        this.restImageCultureMockMvc = MockMvcBuilders.standaloneSetup(imageCultureResource)
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
    public static ImageCulture createEntity(EntityManager em) {
        ImageCulture imageCulture = new ImageCulture()
            .imageUrl(DEFAULT_IMAGE_URL);
        return imageCulture;
    }

    @Before
    public void initTest() {
        imageCulture = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageCulture() throws Exception {
        int databaseSizeBeforeCreate = imageCultureRepository.findAll().size();

        // Create the ImageCulture
        ImageCultureDTO imageCultureDTO = imageCultureMapper.toDto(imageCulture);
        restImageCultureMockMvc.perform(post("/api/image-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageCultureDTO)))
            .andExpect(status().isCreated());

        // Validate the ImageCulture in the database
        List<ImageCulture> imageCultureList = imageCultureRepository.findAll();
        assertThat(imageCultureList).hasSize(databaseSizeBeforeCreate + 1);
        ImageCulture testImageCulture = imageCultureList.get(imageCultureList.size() - 1);
        assertThat(testImageCulture.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
    }

    @Test
    @Transactional
    public void createImageCultureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageCultureRepository.findAll().size();

        // Create the ImageCulture with an existing ID
        imageCulture.setId(1L);
        ImageCultureDTO imageCultureDTO = imageCultureMapper.toDto(imageCulture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageCultureMockMvc.perform(post("/api/image-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageCultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageCulture in the database
        List<ImageCulture> imageCultureList = imageCultureRepository.findAll();
        assertThat(imageCultureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllImageCultures() throws Exception {
        // Initialize the database
        imageCultureRepository.saveAndFlush(imageCulture);

        // Get all the imageCultureList
        restImageCultureMockMvc.perform(get("/api/image-cultures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageCulture.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getImageCulture() throws Exception {
        // Initialize the database
        imageCultureRepository.saveAndFlush(imageCulture);

        // Get the imageCulture
        restImageCultureMockMvc.perform(get("/api/image-cultures/{id}", imageCulture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(imageCulture.getId().intValue()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingImageCulture() throws Exception {
        // Get the imageCulture
        restImageCultureMockMvc.perform(get("/api/image-cultures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageCulture() throws Exception {
        // Initialize the database
        imageCultureRepository.saveAndFlush(imageCulture);

        int databaseSizeBeforeUpdate = imageCultureRepository.findAll().size();

        // Update the imageCulture
        ImageCulture updatedImageCulture = imageCultureRepository.findById(imageCulture.getId()).get();
        // Disconnect from session so that the updates on updatedImageCulture are not directly saved in db
        em.detach(updatedImageCulture);
        updatedImageCulture
            .imageUrl(UPDATED_IMAGE_URL);
        ImageCultureDTO imageCultureDTO = imageCultureMapper.toDto(updatedImageCulture);

        restImageCultureMockMvc.perform(put("/api/image-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageCultureDTO)))
            .andExpect(status().isOk());

        // Validate the ImageCulture in the database
        List<ImageCulture> imageCultureList = imageCultureRepository.findAll();
        assertThat(imageCultureList).hasSize(databaseSizeBeforeUpdate);
        ImageCulture testImageCulture = imageCultureList.get(imageCultureList.size() - 1);
        assertThat(testImageCulture.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingImageCulture() throws Exception {
        int databaseSizeBeforeUpdate = imageCultureRepository.findAll().size();

        // Create the ImageCulture
        ImageCultureDTO imageCultureDTO = imageCultureMapper.toDto(imageCulture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageCultureMockMvc.perform(put("/api/image-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageCultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageCulture in the database
        List<ImageCulture> imageCultureList = imageCultureRepository.findAll();
        assertThat(imageCultureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageCulture() throws Exception {
        // Initialize the database
        imageCultureRepository.saveAndFlush(imageCulture);

        int databaseSizeBeforeDelete = imageCultureRepository.findAll().size();

        // Delete the imageCulture
        restImageCultureMockMvc.perform(delete("/api/image-cultures/{id}", imageCulture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImageCulture> imageCultureList = imageCultureRepository.findAll();
        assertThat(imageCultureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageCulture.class);
        ImageCulture imageCulture1 = new ImageCulture();
        imageCulture1.setId(1L);
        ImageCulture imageCulture2 = new ImageCulture();
        imageCulture2.setId(imageCulture1.getId());
        assertThat(imageCulture1).isEqualTo(imageCulture2);
        imageCulture2.setId(2L);
        assertThat(imageCulture1).isNotEqualTo(imageCulture2);
        imageCulture1.setId(null);
        assertThat(imageCulture1).isNotEqualTo(imageCulture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageCultureDTO.class);
        ImageCultureDTO imageCultureDTO1 = new ImageCultureDTO();
        imageCultureDTO1.setId(1L);
        ImageCultureDTO imageCultureDTO2 = new ImageCultureDTO();
        assertThat(imageCultureDTO1).isNotEqualTo(imageCultureDTO2);
        imageCultureDTO2.setId(imageCultureDTO1.getId());
        assertThat(imageCultureDTO1).isEqualTo(imageCultureDTO2);
        imageCultureDTO2.setId(2L);
        assertThat(imageCultureDTO1).isNotEqualTo(imageCultureDTO2);
        imageCultureDTO1.setId(null);
        assertThat(imageCultureDTO1).isNotEqualTo(imageCultureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(imageCultureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(imageCultureMapper.fromId(null)).isNull();
    }
}
