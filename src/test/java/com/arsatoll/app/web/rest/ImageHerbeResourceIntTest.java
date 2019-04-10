package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.ImageHerbe;
import com.arsatoll.app.repository.ImageHerbeRepository;
import com.arsatoll.app.service.ImageHerbeService;
import com.arsatoll.app.service.dto.ImageHerbeDTO;
import com.arsatoll.app.service.mapper.ImageHerbeMapper;
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
 * Test class for the ImageHerbeResource REST controller.
 *
 * @see ImageHerbeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class ImageHerbeResourceIntTest {

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    @Autowired
    private ImageHerbeRepository imageHerbeRepository;

    @Autowired
    private ImageHerbeMapper imageHerbeMapper;

    @Autowired
    private ImageHerbeService imageHerbeService;

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

    private MockMvc restImageHerbeMockMvc;

    private ImageHerbe imageHerbe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImageHerbeResource imageHerbeResource = new ImageHerbeResource(imageHerbeService);
        this.restImageHerbeMockMvc = MockMvcBuilders.standaloneSetup(imageHerbeResource)
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
    public static ImageHerbe createEntity(EntityManager em) {
        ImageHerbe imageHerbe = new ImageHerbe()
            .imageUrl(DEFAULT_IMAGE_URL);
        return imageHerbe;
    }

    @Before
    public void initTest() {
        imageHerbe = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageHerbe() throws Exception {
        int databaseSizeBeforeCreate = imageHerbeRepository.findAll().size();

        // Create the ImageHerbe
        ImageHerbeDTO imageHerbeDTO = imageHerbeMapper.toDto(imageHerbe);
        restImageHerbeMockMvc.perform(post("/api/image-herbes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageHerbeDTO)))
            .andExpect(status().isCreated());

        // Validate the ImageHerbe in the database
        List<ImageHerbe> imageHerbeList = imageHerbeRepository.findAll();
        assertThat(imageHerbeList).hasSize(databaseSizeBeforeCreate + 1);
        ImageHerbe testImageHerbe = imageHerbeList.get(imageHerbeList.size() - 1);
        assertThat(testImageHerbe.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
    }

    @Test
    @Transactional
    public void createImageHerbeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageHerbeRepository.findAll().size();

        // Create the ImageHerbe with an existing ID
        imageHerbe.setId(1L);
        ImageHerbeDTO imageHerbeDTO = imageHerbeMapper.toDto(imageHerbe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageHerbeMockMvc.perform(post("/api/image-herbes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageHerbeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageHerbe in the database
        List<ImageHerbe> imageHerbeList = imageHerbeRepository.findAll();
        assertThat(imageHerbeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllImageHerbes() throws Exception {
        // Initialize the database
        imageHerbeRepository.saveAndFlush(imageHerbe);

        // Get all the imageHerbeList
        restImageHerbeMockMvc.perform(get("/api/image-herbes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageHerbe.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getImageHerbe() throws Exception {
        // Initialize the database
        imageHerbeRepository.saveAndFlush(imageHerbe);

        // Get the imageHerbe
        restImageHerbeMockMvc.perform(get("/api/image-herbes/{id}", imageHerbe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(imageHerbe.getId().intValue()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingImageHerbe() throws Exception {
        // Get the imageHerbe
        restImageHerbeMockMvc.perform(get("/api/image-herbes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageHerbe() throws Exception {
        // Initialize the database
        imageHerbeRepository.saveAndFlush(imageHerbe);

        int databaseSizeBeforeUpdate = imageHerbeRepository.findAll().size();

        // Update the imageHerbe
        ImageHerbe updatedImageHerbe = imageHerbeRepository.findById(imageHerbe.getId()).get();
        // Disconnect from session so that the updates on updatedImageHerbe are not directly saved in db
        em.detach(updatedImageHerbe);
        updatedImageHerbe
            .imageUrl(UPDATED_IMAGE_URL);
        ImageHerbeDTO imageHerbeDTO = imageHerbeMapper.toDto(updatedImageHerbe);

        restImageHerbeMockMvc.perform(put("/api/image-herbes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageHerbeDTO)))
            .andExpect(status().isOk());

        // Validate the ImageHerbe in the database
        List<ImageHerbe> imageHerbeList = imageHerbeRepository.findAll();
        assertThat(imageHerbeList).hasSize(databaseSizeBeforeUpdate);
        ImageHerbe testImageHerbe = imageHerbeList.get(imageHerbeList.size() - 1);
        assertThat(testImageHerbe.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingImageHerbe() throws Exception {
        int databaseSizeBeforeUpdate = imageHerbeRepository.findAll().size();

        // Create the ImageHerbe
        ImageHerbeDTO imageHerbeDTO = imageHerbeMapper.toDto(imageHerbe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageHerbeMockMvc.perform(put("/api/image-herbes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageHerbeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageHerbe in the database
        List<ImageHerbe> imageHerbeList = imageHerbeRepository.findAll();
        assertThat(imageHerbeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageHerbe() throws Exception {
        // Initialize the database
        imageHerbeRepository.saveAndFlush(imageHerbe);

        int databaseSizeBeforeDelete = imageHerbeRepository.findAll().size();

        // Delete the imageHerbe
        restImageHerbeMockMvc.perform(delete("/api/image-herbes/{id}", imageHerbe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImageHerbe> imageHerbeList = imageHerbeRepository.findAll();
        assertThat(imageHerbeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageHerbe.class);
        ImageHerbe imageHerbe1 = new ImageHerbe();
        imageHerbe1.setId(1L);
        ImageHerbe imageHerbe2 = new ImageHerbe();
        imageHerbe2.setId(imageHerbe1.getId());
        assertThat(imageHerbe1).isEqualTo(imageHerbe2);
        imageHerbe2.setId(2L);
        assertThat(imageHerbe1).isNotEqualTo(imageHerbe2);
        imageHerbe1.setId(null);
        assertThat(imageHerbe1).isNotEqualTo(imageHerbe2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageHerbeDTO.class);
        ImageHerbeDTO imageHerbeDTO1 = new ImageHerbeDTO();
        imageHerbeDTO1.setId(1L);
        ImageHerbeDTO imageHerbeDTO2 = new ImageHerbeDTO();
        assertThat(imageHerbeDTO1).isNotEqualTo(imageHerbeDTO2);
        imageHerbeDTO2.setId(imageHerbeDTO1.getId());
        assertThat(imageHerbeDTO1).isEqualTo(imageHerbeDTO2);
        imageHerbeDTO2.setId(2L);
        assertThat(imageHerbeDTO1).isNotEqualTo(imageHerbeDTO2);
        imageHerbeDTO1.setId(null);
        assertThat(imageHerbeDTO1).isNotEqualTo(imageHerbeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(imageHerbeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(imageHerbeMapper.fromId(null)).isNull();
    }
}
