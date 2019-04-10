package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.ImageEnvoye;
import com.arsatoll.app.repository.ImageEnvoyeRepository;
import com.arsatoll.app.service.ImageEnvoyeService;
import com.arsatoll.app.service.dto.ImageEnvoyeDTO;
import com.arsatoll.app.service.mapper.ImageEnvoyeMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.arsatoll.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ImageEnvoyeResource REST controller.
 *
 * @see ImageEnvoyeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class ImageEnvoyeResourceIntTest {

    private static final String DEFAULT_URL_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_URL_IMAGE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_D_AJOUT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_D_AJOUT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_VALIDATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_VALIDATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_FLAG = false;
    private static final Boolean UPDATED_FLAG = true;

    @Autowired
    private ImageEnvoyeRepository imageEnvoyeRepository;

    @Autowired
    private ImageEnvoyeMapper imageEnvoyeMapper;

    @Autowired
    private ImageEnvoyeService imageEnvoyeService;

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

    private MockMvc restImageEnvoyeMockMvc;

    private ImageEnvoye imageEnvoye;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImageEnvoyeResource imageEnvoyeResource = new ImageEnvoyeResource(imageEnvoyeService);
        this.restImageEnvoyeMockMvc = MockMvcBuilders.standaloneSetup(imageEnvoyeResource)
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
    public static ImageEnvoye createEntity(EntityManager em) {
        ImageEnvoye imageEnvoye = new ImageEnvoye()
            .urlImage(DEFAULT_URL_IMAGE)
            .dateDAjout(DEFAULT_DATE_D_AJOUT)
            .dateValidation(DEFAULT_DATE_VALIDATION)
            .flag(DEFAULT_FLAG);
        return imageEnvoye;
    }

    @Before
    public void initTest() {
        imageEnvoye = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageEnvoye() throws Exception {
        int databaseSizeBeforeCreate = imageEnvoyeRepository.findAll().size();

        // Create the ImageEnvoye
        ImageEnvoyeDTO imageEnvoyeDTO = imageEnvoyeMapper.toDto(imageEnvoye);
        restImageEnvoyeMockMvc.perform(post("/api/image-envoyes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageEnvoyeDTO)))
            .andExpect(status().isCreated());

        // Validate the ImageEnvoye in the database
        List<ImageEnvoye> imageEnvoyeList = imageEnvoyeRepository.findAll();
        assertThat(imageEnvoyeList).hasSize(databaseSizeBeforeCreate + 1);
        ImageEnvoye testImageEnvoye = imageEnvoyeList.get(imageEnvoyeList.size() - 1);
        assertThat(testImageEnvoye.getUrlImage()).isEqualTo(DEFAULT_URL_IMAGE);
        assertThat(testImageEnvoye.getDateDAjout()).isEqualTo(DEFAULT_DATE_D_AJOUT);
        assertThat(testImageEnvoye.getDateValidation()).isEqualTo(DEFAULT_DATE_VALIDATION);
        assertThat(testImageEnvoye.isFlag()).isEqualTo(DEFAULT_FLAG);
    }

    @Test
    @Transactional
    public void createImageEnvoyeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageEnvoyeRepository.findAll().size();

        // Create the ImageEnvoye with an existing ID
        imageEnvoye.setId(1L);
        ImageEnvoyeDTO imageEnvoyeDTO = imageEnvoyeMapper.toDto(imageEnvoye);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageEnvoyeMockMvc.perform(post("/api/image-envoyes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageEnvoyeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageEnvoye in the database
        List<ImageEnvoye> imageEnvoyeList = imageEnvoyeRepository.findAll();
        assertThat(imageEnvoyeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllImageEnvoyes() throws Exception {
        // Initialize the database
        imageEnvoyeRepository.saveAndFlush(imageEnvoye);

        // Get all the imageEnvoyeList
        restImageEnvoyeMockMvc.perform(get("/api/image-envoyes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageEnvoye.getId().intValue())))
            .andExpect(jsonPath("$.[*].urlImage").value(hasItem(DEFAULT_URL_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].dateDAjout").value(hasItem(DEFAULT_DATE_D_AJOUT.toString())))
            .andExpect(jsonPath("$.[*].dateValidation").value(hasItem(DEFAULT_DATE_VALIDATION.toString())))
            .andExpect(jsonPath("$.[*].flag").value(hasItem(DEFAULT_FLAG.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getImageEnvoye() throws Exception {
        // Initialize the database
        imageEnvoyeRepository.saveAndFlush(imageEnvoye);

        // Get the imageEnvoye
        restImageEnvoyeMockMvc.perform(get("/api/image-envoyes/{id}", imageEnvoye.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(imageEnvoye.getId().intValue()))
            .andExpect(jsonPath("$.urlImage").value(DEFAULT_URL_IMAGE.toString()))
            .andExpect(jsonPath("$.dateDAjout").value(DEFAULT_DATE_D_AJOUT.toString()))
            .andExpect(jsonPath("$.dateValidation").value(DEFAULT_DATE_VALIDATION.toString()))
            .andExpect(jsonPath("$.flag").value(DEFAULT_FLAG.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingImageEnvoye() throws Exception {
        // Get the imageEnvoye
        restImageEnvoyeMockMvc.perform(get("/api/image-envoyes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageEnvoye() throws Exception {
        // Initialize the database
        imageEnvoyeRepository.saveAndFlush(imageEnvoye);

        int databaseSizeBeforeUpdate = imageEnvoyeRepository.findAll().size();

        // Update the imageEnvoye
        ImageEnvoye updatedImageEnvoye = imageEnvoyeRepository.findById(imageEnvoye.getId()).get();
        // Disconnect from session so that the updates on updatedImageEnvoye are not directly saved in db
        em.detach(updatedImageEnvoye);
        updatedImageEnvoye
            .urlImage(UPDATED_URL_IMAGE)
            .dateDAjout(UPDATED_DATE_D_AJOUT)
            .dateValidation(UPDATED_DATE_VALIDATION)
            .flag(UPDATED_FLAG);
        ImageEnvoyeDTO imageEnvoyeDTO = imageEnvoyeMapper.toDto(updatedImageEnvoye);

        restImageEnvoyeMockMvc.perform(put("/api/image-envoyes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageEnvoyeDTO)))
            .andExpect(status().isOk());

        // Validate the ImageEnvoye in the database
        List<ImageEnvoye> imageEnvoyeList = imageEnvoyeRepository.findAll();
        assertThat(imageEnvoyeList).hasSize(databaseSizeBeforeUpdate);
        ImageEnvoye testImageEnvoye = imageEnvoyeList.get(imageEnvoyeList.size() - 1);
        assertThat(testImageEnvoye.getUrlImage()).isEqualTo(UPDATED_URL_IMAGE);
        assertThat(testImageEnvoye.getDateDAjout()).isEqualTo(UPDATED_DATE_D_AJOUT);
        assertThat(testImageEnvoye.getDateValidation()).isEqualTo(UPDATED_DATE_VALIDATION);
        assertThat(testImageEnvoye.isFlag()).isEqualTo(UPDATED_FLAG);
    }

    @Test
    @Transactional
    public void updateNonExistingImageEnvoye() throws Exception {
        int databaseSizeBeforeUpdate = imageEnvoyeRepository.findAll().size();

        // Create the ImageEnvoye
        ImageEnvoyeDTO imageEnvoyeDTO = imageEnvoyeMapper.toDto(imageEnvoye);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageEnvoyeMockMvc.perform(put("/api/image-envoyes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageEnvoyeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageEnvoye in the database
        List<ImageEnvoye> imageEnvoyeList = imageEnvoyeRepository.findAll();
        assertThat(imageEnvoyeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageEnvoye() throws Exception {
        // Initialize the database
        imageEnvoyeRepository.saveAndFlush(imageEnvoye);

        int databaseSizeBeforeDelete = imageEnvoyeRepository.findAll().size();

        // Delete the imageEnvoye
        restImageEnvoyeMockMvc.perform(delete("/api/image-envoyes/{id}", imageEnvoye.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ImageEnvoye> imageEnvoyeList = imageEnvoyeRepository.findAll();
        assertThat(imageEnvoyeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageEnvoye.class);
        ImageEnvoye imageEnvoye1 = new ImageEnvoye();
        imageEnvoye1.setId(1L);
        ImageEnvoye imageEnvoye2 = new ImageEnvoye();
        imageEnvoye2.setId(imageEnvoye1.getId());
        assertThat(imageEnvoye1).isEqualTo(imageEnvoye2);
        imageEnvoye2.setId(2L);
        assertThat(imageEnvoye1).isNotEqualTo(imageEnvoye2);
        imageEnvoye1.setId(null);
        assertThat(imageEnvoye1).isNotEqualTo(imageEnvoye2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageEnvoyeDTO.class);
        ImageEnvoyeDTO imageEnvoyeDTO1 = new ImageEnvoyeDTO();
        imageEnvoyeDTO1.setId(1L);
        ImageEnvoyeDTO imageEnvoyeDTO2 = new ImageEnvoyeDTO();
        assertThat(imageEnvoyeDTO1).isNotEqualTo(imageEnvoyeDTO2);
        imageEnvoyeDTO2.setId(imageEnvoyeDTO1.getId());
        assertThat(imageEnvoyeDTO1).isEqualTo(imageEnvoyeDTO2);
        imageEnvoyeDTO2.setId(2L);
        assertThat(imageEnvoyeDTO1).isNotEqualTo(imageEnvoyeDTO2);
        imageEnvoyeDTO1.setId(null);
        assertThat(imageEnvoyeDTO1).isNotEqualTo(imageEnvoyeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(imageEnvoyeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(imageEnvoyeMapper.fromId(null)).isNull();
    }
}
