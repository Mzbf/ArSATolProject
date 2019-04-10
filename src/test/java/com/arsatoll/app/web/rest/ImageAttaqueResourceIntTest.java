package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.ImageAttaque;
import com.arsatoll.app.repository.ImageAttaqueRepository;
import com.arsatoll.app.service.ImageAttaqueService;
import com.arsatoll.app.service.dto.ImageAttaqueDTO;
import com.arsatoll.app.service.mapper.ImageAttaqueMapper;
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
 * Test class for the ImageAttaqueResource REST controller.
 *
 * @see ImageAttaqueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class ImageAttaqueResourceIntTest {

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    @Autowired
    private ImageAttaqueRepository imageAttaqueRepository;

    @Autowired
    private ImageAttaqueMapper imageAttaqueMapper;

    @Autowired
    private ImageAttaqueService imageAttaqueService;

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

    private MockMvc restImageAttaqueMockMvc;

    private ImageAttaque imageAttaque;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImageAttaqueResource imageAttaqueResource = new ImageAttaqueResource(imageAttaqueService);
        this.restImageAttaqueMockMvc = MockMvcBuilders.standaloneSetup(imageAttaqueResource)
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
    public static ImageAttaque createEntity(EntityManager em) {
        ImageAttaque imageAttaque = new ImageAttaque()
            .imageUrl(DEFAULT_IMAGE_URL);
        return imageAttaque;
    }

    @Before
    public void initTest() {
        imageAttaque = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageAttaque() throws Exception {
        int databaseSizeBeforeCreate = imageAttaqueRepository.findAll().size();

        // Create the ImageAttaque
        ImageAttaqueDTO imageAttaqueDTO = imageAttaqueMapper.toDto(imageAttaque);
        restImageAttaqueMockMvc.perform(post("/api/image-attaques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageAttaqueDTO)))
            .andExpect(status().isCreated());

        // Validate the ImageAttaque in the database
        List<ImageAttaque> imageAttaqueList = imageAttaqueRepository.findAll();
        assertThat(imageAttaqueList).hasSize(databaseSizeBeforeCreate + 1);
        ImageAttaque testImageAttaque = imageAttaqueList.get(imageAttaqueList.size() - 1);
        assertThat(testImageAttaque.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
    }

    @Test
    @Transactional
    public void createImageAttaqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageAttaqueRepository.findAll().size();

        // Create the ImageAttaque with an existing ID
        imageAttaque.setId(1L);
        ImageAttaqueDTO imageAttaqueDTO = imageAttaqueMapper.toDto(imageAttaque);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageAttaqueMockMvc.perform(post("/api/image-attaques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageAttaqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageAttaque in the database
        List<ImageAttaque> imageAttaqueList = imageAttaqueRepository.findAll();
        assertThat(imageAttaqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllImageAttaques() throws Exception {
        // Initialize the database
        imageAttaqueRepository.saveAndFlush(imageAttaque);

        // Get all the imageAttaqueList
        restImageAttaqueMockMvc.perform(get("/api/image-attaques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageAttaque.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getImageAttaque() throws Exception {
        // Initialize the database
        imageAttaqueRepository.saveAndFlush(imageAttaque);

        // Get the imageAttaque
        restImageAttaqueMockMvc.perform(get("/api/image-attaques/{id}", imageAttaque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(imageAttaque.getId().intValue()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingImageAttaque() throws Exception {
        // Get the imageAttaque
        restImageAttaqueMockMvc.perform(get("/api/image-attaques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageAttaque() throws Exception {
        // Initialize the database
        imageAttaqueRepository.saveAndFlush(imageAttaque);

        int databaseSizeBeforeUpdate = imageAttaqueRepository.findAll().size();

        // Update the imageAttaque
        ImageAttaque updatedImageAttaque = imageAttaqueRepository.findById(imageAttaque.getId()).get();
        // Disconnect from session so that the updates on updatedImageAttaque are not directly saved in db
        em.detach(updatedImageAttaque);
        updatedImageAttaque
            .imageUrl(UPDATED_IMAGE_URL);
        ImageAttaqueDTO imageAttaqueDTO = imageAttaqueMapper.toDto(updatedImageAttaque);

        restImageAttaqueMockMvc.perform(put("/api/image-attaques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageAttaqueDTO)))
            .andExpect(status().isOk());

        // Validate the ImageAttaque in the database
        List<ImageAttaque> imageAttaqueList = imageAttaqueRepository.findAll();
        assertThat(imageAttaqueList).hasSize(databaseSizeBeforeUpdate);
        ImageAttaque testImageAttaque = imageAttaqueList.get(imageAttaqueList.size() - 1);
        assertThat(testImageAttaque.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingImageAttaque() throws Exception {
        int databaseSizeBeforeUpdate = imageAttaqueRepository.findAll().size();

        // Create the ImageAttaque
        ImageAttaqueDTO imageAttaqueDTO = imageAttaqueMapper.toDto(imageAttaque);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageAttaqueMockMvc.perform(put("/api/image-attaques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageAttaqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageAttaque in the database
        List<ImageAttaque> imageAttaqueList = imageAttaqueRepository.findAll();
        assertThat(imageAttaqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageAttaque() throws Exception {
        // Initialize the database
        imageAttaqueRepository.saveAndFlush(imageAttaque);

        int databaseSizeBeforeDelete = imageAttaqueRepository.findAll().size();

        // Delete the imageAttaque
        restImageAttaqueMockMvc.perform(delete("/api/image-attaques/{id}", imageAttaque.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImageAttaque> imageAttaqueList = imageAttaqueRepository.findAll();
        assertThat(imageAttaqueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageAttaque.class);
        ImageAttaque imageAttaque1 = new ImageAttaque();
        imageAttaque1.setId(1L);
        ImageAttaque imageAttaque2 = new ImageAttaque();
        imageAttaque2.setId(imageAttaque1.getId());
        assertThat(imageAttaque1).isEqualTo(imageAttaque2);
        imageAttaque2.setId(2L);
        assertThat(imageAttaque1).isNotEqualTo(imageAttaque2);
        imageAttaque1.setId(null);
        assertThat(imageAttaque1).isNotEqualTo(imageAttaque2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageAttaqueDTO.class);
        ImageAttaqueDTO imageAttaqueDTO1 = new ImageAttaqueDTO();
        imageAttaqueDTO1.setId(1L);
        ImageAttaqueDTO imageAttaqueDTO2 = new ImageAttaqueDTO();
        assertThat(imageAttaqueDTO1).isNotEqualTo(imageAttaqueDTO2);
        imageAttaqueDTO2.setId(imageAttaqueDTO1.getId());
        assertThat(imageAttaqueDTO1).isEqualTo(imageAttaqueDTO2);
        imageAttaqueDTO2.setId(2L);
        assertThat(imageAttaqueDTO1).isNotEqualTo(imageAttaqueDTO2);
        imageAttaqueDTO1.setId(null);
        assertThat(imageAttaqueDTO1).isNotEqualTo(imageAttaqueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(imageAttaqueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(imageAttaqueMapper.fromId(null)).isNull();
    }
}
