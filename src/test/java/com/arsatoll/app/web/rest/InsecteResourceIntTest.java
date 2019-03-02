package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.Insecte;
import com.arsatoll.app.repository.InsecteRepository;
import com.arsatoll.app.service.InsecteService;
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
 * Test class for the InsecteResource REST controller.
 *
 * @see InsecteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class InsecteResourceIntTest {

    private static final String DEFAULT_NOM_INSECTE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_INSECTE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_SCIEN_INSECTE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SCIEN_INSECTE = "BBBBBBBBBB";

    private static final String DEFAULT_CYCLE_BIO = "AAAAAAAAAA";
    private static final String UPDATED_CYCLE_BIO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FLAG = false;
    private static final Boolean UPDATED_FLAG = true;

    private static final Instant DEFAULT_DATE_VALIDATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_VALIDATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_AJOUT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_AJOUT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private InsecteRepository insecteRepository;

    @Autowired
    private InsecteService insecteService;

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

    private MockMvc restInsecteMockMvc;

    private Insecte insecte;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InsecteResource insecteResource = new InsecteResource(insecteService);
        this.restInsecteMockMvc = MockMvcBuilders.standaloneSetup(insecteResource)
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
    public static Insecte createEntity(EntityManager em) {
        Insecte insecte = new Insecte()
            .nomInsecte(DEFAULT_NOM_INSECTE)
            .nomScienInsecte(DEFAULT_NOM_SCIEN_INSECTE)
            .cycleBio(DEFAULT_CYCLE_BIO)
            .flag(DEFAULT_FLAG)
            .dateValidation(DEFAULT_DATE_VALIDATION)
            .dateAjout(DEFAULT_DATE_AJOUT);
        return insecte;
    }

    @Before
    public void initTest() {
        insecte = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsecte() throws Exception {
        int databaseSizeBeforeCreate = insecteRepository.findAll().size();

        // Create the Insecte
        restInsecteMockMvc.perform(post("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecte)))
            .andExpect(status().isCreated());

        // Validate the Insecte in the database
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeCreate + 1);
        Insecte testInsecte = insecteList.get(insecteList.size() - 1);
        assertThat(testInsecte.getNomInsecte()).isEqualTo(DEFAULT_NOM_INSECTE);
        assertThat(testInsecte.getNomScienInsecte()).isEqualTo(DEFAULT_NOM_SCIEN_INSECTE);
        assertThat(testInsecte.getCycleBio()).isEqualTo(DEFAULT_CYCLE_BIO);
        assertThat(testInsecte.isFlag()).isEqualTo(DEFAULT_FLAG);
        assertThat(testInsecte.getDateValidation()).isEqualTo(DEFAULT_DATE_VALIDATION);
        assertThat(testInsecte.getDateAjout()).isEqualTo(DEFAULT_DATE_AJOUT);
    }

    @Test
    @Transactional
    public void createInsecteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insecteRepository.findAll().size();

        // Create the Insecte with an existing ID
        insecte.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsecteMockMvc.perform(post("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecte)))
            .andExpect(status().isBadRequest());

        // Validate the Insecte in the database
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInsectes() throws Exception {
        // Initialize the database
        insecteRepository.saveAndFlush(insecte);

        // Get all the insecteList
        restInsecteMockMvc.perform(get("/api/insectes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insecte.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomInsecte").value(hasItem(DEFAULT_NOM_INSECTE.toString())))
            .andExpect(jsonPath("$.[*].nomScienInsecte").value(hasItem(DEFAULT_NOM_SCIEN_INSECTE.toString())))
            .andExpect(jsonPath("$.[*].cycleBio").value(hasItem(DEFAULT_CYCLE_BIO.toString())))
            .andExpect(jsonPath("$.[*].flag").value(hasItem(DEFAULT_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].dateValidation").value(hasItem(DEFAULT_DATE_VALIDATION.toString())))
            .andExpect(jsonPath("$.[*].dateAjout").value(hasItem(DEFAULT_DATE_AJOUT.toString())));
    }
    
    @Test
    @Transactional
    public void getInsecte() throws Exception {
        // Initialize the database
        insecteRepository.saveAndFlush(insecte);

        // Get the insecte
        restInsecteMockMvc.perform(get("/api/insectes/{id}", insecte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(insecte.getId().intValue()))
            .andExpect(jsonPath("$.nomInsecte").value(DEFAULT_NOM_INSECTE.toString()))
            .andExpect(jsonPath("$.nomScienInsecte").value(DEFAULT_NOM_SCIEN_INSECTE.toString()))
            .andExpect(jsonPath("$.cycleBio").value(DEFAULT_CYCLE_BIO.toString()))
            .andExpect(jsonPath("$.flag").value(DEFAULT_FLAG.booleanValue()))
            .andExpect(jsonPath("$.dateValidation").value(DEFAULT_DATE_VALIDATION.toString()))
            .andExpect(jsonPath("$.dateAjout").value(DEFAULT_DATE_AJOUT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInsecte() throws Exception {
        // Get the insecte
        restInsecteMockMvc.perform(get("/api/insectes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsecte() throws Exception {
        // Initialize the database
        insecteService.save(insecte);

        int databaseSizeBeforeUpdate = insecteRepository.findAll().size();

        // Update the insecte
        Insecte updatedInsecte = insecteRepository.findById(insecte.getId()).get();
        // Disconnect from session so that the updates on updatedInsecte are not directly saved in db
        em.detach(updatedInsecte);
        updatedInsecte
            .nomInsecte(UPDATED_NOM_INSECTE)
            .nomScienInsecte(UPDATED_NOM_SCIEN_INSECTE)
            .cycleBio(UPDATED_CYCLE_BIO)
            .flag(UPDATED_FLAG)
            .dateValidation(UPDATED_DATE_VALIDATION)
            .dateAjout(UPDATED_DATE_AJOUT);

        restInsecteMockMvc.perform(put("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInsecte)))
            .andExpect(status().isOk());

        // Validate the Insecte in the database
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeUpdate);
        Insecte testInsecte = insecteList.get(insecteList.size() - 1);
        assertThat(testInsecte.getNomInsecte()).isEqualTo(UPDATED_NOM_INSECTE);
        assertThat(testInsecte.getNomScienInsecte()).isEqualTo(UPDATED_NOM_SCIEN_INSECTE);
        assertThat(testInsecte.getCycleBio()).isEqualTo(UPDATED_CYCLE_BIO);
        assertThat(testInsecte.isFlag()).isEqualTo(UPDATED_FLAG);
        assertThat(testInsecte.getDateValidation()).isEqualTo(UPDATED_DATE_VALIDATION);
        assertThat(testInsecte.getDateAjout()).isEqualTo(UPDATED_DATE_AJOUT);
    }

    @Test
    @Transactional
    public void updateNonExistingInsecte() throws Exception {
        int databaseSizeBeforeUpdate = insecteRepository.findAll().size();

        // Create the Insecte

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsecteMockMvc.perform(put("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecte)))
            .andExpect(status().isBadRequest());

        // Validate the Insecte in the database
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsecte() throws Exception {
        // Initialize the database
        insecteService.save(insecte);

        int databaseSizeBeforeDelete = insecteRepository.findAll().size();

        // Delete the insecte
        restInsecteMockMvc.perform(delete("/api/insectes/{id}", insecte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Insecte.class);
        Insecte insecte1 = new Insecte();
        insecte1.setId(1L);
        Insecte insecte2 = new Insecte();
        insecte2.setId(insecte1.getId());
        assertThat(insecte1).isEqualTo(insecte2);
        insecte2.setId(2L);
        assertThat(insecte1).isNotEqualTo(insecte2);
        insecte1.setId(null);
        assertThat(insecte1).isNotEqualTo(insecte2);
    }
}
