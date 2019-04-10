package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.Maladie;
import com.arsatoll.app.repository.MaladieRepository;
import com.arsatoll.app.service.MaladieService;
import com.arsatoll.app.service.dto.MaladieDTO;
import com.arsatoll.app.service.mapper.MaladieMapper;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.arsatoll.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MaladieResource REST controller.
 *
 * @see MaladieResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class MaladieResourceIntTest {

    private static final String DEFAULT_NOM_MALADIE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MALADIE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGES_MALADIE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGES_MALADIE = "BBBBBBBBBB";

    @Autowired
    private MaladieRepository maladieRepository;

    @Autowired
    private MaladieMapper maladieMapper;

    @Autowired
    private MaladieService maladieService;

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

    private MockMvc restMaladieMockMvc;

    private Maladie maladie;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MaladieResource maladieResource = new MaladieResource(maladieService);
        this.restMaladieMockMvc = MockMvcBuilders.standaloneSetup(maladieResource)
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
    public static Maladie createEntity(EntityManager em) {
        Maladie maladie = new Maladie()
            .nomMaladie(DEFAULT_NOM_MALADIE)
            .description(DEFAULT_DESCRIPTION)
            .imagesMaladie(DEFAULT_IMAGES_MALADIE);
        return maladie;
    }

    @Before
    public void initTest() {
        maladie = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaladie() throws Exception {
        int databaseSizeBeforeCreate = maladieRepository.findAll().size();

        // Create the Maladie
        MaladieDTO maladieDTO = maladieMapper.toDto(maladie);
        restMaladieMockMvc.perform(post("/api/maladies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maladieDTO)))
            .andExpect(status().isCreated());

        // Validate the Maladie in the database
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeCreate + 1);
        Maladie testMaladie = maladieList.get(maladieList.size() - 1);
        assertThat(testMaladie.getNomMaladie()).isEqualTo(DEFAULT_NOM_MALADIE);
        assertThat(testMaladie.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMaladie.getImagesMaladie()).isEqualTo(DEFAULT_IMAGES_MALADIE);
    }

    @Test
    @Transactional
    public void createMaladieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maladieRepository.findAll().size();

        // Create the Maladie with an existing ID
        maladie.setId(1L);
        MaladieDTO maladieDTO = maladieMapper.toDto(maladie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaladieMockMvc.perform(post("/api/maladies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maladieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Maladie in the database
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMaladies() throws Exception {
        // Initialize the database
        maladieRepository.saveAndFlush(maladie);

        // Get all the maladieList
        restMaladieMockMvc.perform(get("/api/maladies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maladie.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomMaladie").value(hasItem(DEFAULT_NOM_MALADIE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].imagesMaladie").value(hasItem(DEFAULT_IMAGES_MALADIE.toString())));
    }
    
    @Test
    @Transactional
    public void getMaladie() throws Exception {
        // Initialize the database
        maladieRepository.saveAndFlush(maladie);

        // Get the maladie
        restMaladieMockMvc.perform(get("/api/maladies/{id}", maladie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(maladie.getId().intValue()))
            .andExpect(jsonPath("$.nomMaladie").value(DEFAULT_NOM_MALADIE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.imagesMaladie").value(DEFAULT_IMAGES_MALADIE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMaladie() throws Exception {
        // Get the maladie
        restMaladieMockMvc.perform(get("/api/maladies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaladie() throws Exception {
        // Initialize the database
        maladieRepository.saveAndFlush(maladie);

        int databaseSizeBeforeUpdate = maladieRepository.findAll().size();

        // Update the maladie
        Maladie updatedMaladie = maladieRepository.findById(maladie.getId()).get();
        // Disconnect from session so that the updates on updatedMaladie are not directly saved in db
        em.detach(updatedMaladie);
        updatedMaladie
            .nomMaladie(UPDATED_NOM_MALADIE)
            .description(UPDATED_DESCRIPTION)
            .imagesMaladie(UPDATED_IMAGES_MALADIE);
        MaladieDTO maladieDTO = maladieMapper.toDto(updatedMaladie);

        restMaladieMockMvc.perform(put("/api/maladies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maladieDTO)))
            .andExpect(status().isOk());

        // Validate the Maladie in the database
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeUpdate);
        Maladie testMaladie = maladieList.get(maladieList.size() - 1);
        assertThat(testMaladie.getNomMaladie()).isEqualTo(UPDATED_NOM_MALADIE);
        assertThat(testMaladie.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMaladie.getImagesMaladie()).isEqualTo(UPDATED_IMAGES_MALADIE);
    }

    @Test
    @Transactional
    public void updateNonExistingMaladie() throws Exception {
        int databaseSizeBeforeUpdate = maladieRepository.findAll().size();

        // Create the Maladie
        MaladieDTO maladieDTO = maladieMapper.toDto(maladie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaladieMockMvc.perform(put("/api/maladies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maladieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Maladie in the database
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaladie() throws Exception {
        // Initialize the database
        maladieRepository.saveAndFlush(maladie);

        int databaseSizeBeforeDelete = maladieRepository.findAll().size();

        // Delete the maladie
        restMaladieMockMvc.perform(delete("/api/maladies/{id}", maladie.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Maladie> maladieList = maladieRepository.findAll();
        assertThat(maladieList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Maladie.class);
        Maladie maladie1 = new Maladie();
        maladie1.setId(1L);
        Maladie maladie2 = new Maladie();
        maladie2.setId(maladie1.getId());
        assertThat(maladie1).isEqualTo(maladie2);
        maladie2.setId(2L);
        assertThat(maladie1).isNotEqualTo(maladie2);
        maladie1.setId(null);
        assertThat(maladie1).isNotEqualTo(maladie2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaladieDTO.class);
        MaladieDTO maladieDTO1 = new MaladieDTO();
        maladieDTO1.setId(1L);
        MaladieDTO maladieDTO2 = new MaladieDTO();
        assertThat(maladieDTO1).isNotEqualTo(maladieDTO2);
        maladieDTO2.setId(maladieDTO1.getId());
        assertThat(maladieDTO1).isEqualTo(maladieDTO2);
        maladieDTO2.setId(2L);
        assertThat(maladieDTO1).isNotEqualTo(maladieDTO2);
        maladieDTO1.setId(null);
        assertThat(maladieDTO1).isNotEqualTo(maladieDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(maladieMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(maladieMapper.fromId(null)).isNull();
    }
}
