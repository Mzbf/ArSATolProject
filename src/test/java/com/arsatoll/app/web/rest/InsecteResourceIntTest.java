package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.Insecte;
import com.arsatoll.app.repository.InsecteRepository;
import com.arsatoll.app.service.InsecteService;
import com.arsatoll.app.service.dto.InsecteDTO;
import com.arsatoll.app.service.mapper.InsecteMapper;
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

    private static final String DEFAULT_INSECTE_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_INSECTE_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CYCLE_BIO = "AAAAAAAAAA";
    private static final String UPDATED_CYCLE_BIO = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_PLANTE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_PLANTE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_CYCLE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_CYCLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_VALIDATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_VALIDATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_AJOUT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_AJOUT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private InsecteRepository insecteRepository;

    @Autowired
    private InsecteMapper insecteMapper;

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
        final InsecteResource insecteResource = new InsecteResource(insecteService,insecteRepository);
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
            .insecteImage(DEFAULT_INSECTE_IMAGE)
            .description(DEFAULT_DESCRIPTION)
            .cycleBio(DEFAULT_CYCLE_BIO)
            .autrePlante(DEFAULT_AUTRE_PLANTE)
            .imageCycle(DEFAULT_IMAGE_CYCLE)
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
        InsecteDTO insecteDTO = insecteMapper.toDto(insecte);
        restInsecteMockMvc.perform(post("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecteDTO)))
            .andExpect(status().isCreated());

        // Validate the Insecte in the database
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeCreate + 1);
        Insecte testInsecte = insecteList.get(insecteList.size() - 1);
        assertThat(testInsecte.getNomInsecte()).isEqualTo(DEFAULT_NOM_INSECTE);
        assertThat(testInsecte.getNomScienInsecte()).isEqualTo(DEFAULT_NOM_SCIEN_INSECTE);
        assertThat(testInsecte.getInsecteImage()).isEqualTo(DEFAULT_INSECTE_IMAGE);
        assertThat(testInsecte.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInsecte.getCycleBio()).isEqualTo(DEFAULT_CYCLE_BIO);
        assertThat(testInsecte.getAutrePlante()).isEqualTo(DEFAULT_AUTRE_PLANTE);
        assertThat(testInsecte.getImageCycle()).isEqualTo(DEFAULT_IMAGE_CYCLE);
        assertThat(testInsecte.getDateValidation()).isEqualTo(DEFAULT_DATE_VALIDATION);
        assertThat(testInsecte.getDateAjout()).isEqualTo(DEFAULT_DATE_AJOUT);
    }

    @Test
    @Transactional
    public void createInsecteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insecteRepository.findAll().size();

        // Create the Insecte with an existing ID
        insecte.setId(1L);
        InsecteDTO insecteDTO = insecteMapper.toDto(insecte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsecteMockMvc.perform(post("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Insecte in the database
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomInsecteIsRequired() throws Exception {
        int databaseSizeBeforeTest = insecteRepository.findAll().size();
        // set the field null
        insecte.setNomInsecte(null);

        // Create the Insecte, which fails.
        InsecteDTO insecteDTO = insecteMapper.toDto(insecte);

        restInsecteMockMvc.perform(post("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecteDTO)))
            .andExpect(status().isBadRequest());

        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomScienInsecteIsRequired() throws Exception {
        int databaseSizeBeforeTest = insecteRepository.findAll().size();
        // set the field null
        insecte.setNomScienInsecte(null);

        // Create the Insecte, which fails.
        InsecteDTO insecteDTO = insecteMapper.toDto(insecte);

        restInsecteMockMvc.perform(post("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecteDTO)))
            .andExpect(status().isBadRequest());

        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeTest);
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
            .andExpect(jsonPath("$.[*].insecteImage").value(hasItem(DEFAULT_INSECTE_IMAGE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].cycleBio").value(hasItem(DEFAULT_CYCLE_BIO.toString())))
            .andExpect(jsonPath("$.[*].autrePlante").value(hasItem(DEFAULT_AUTRE_PLANTE.toString())))
            .andExpect(jsonPath("$.[*].imageCycle").value(hasItem(DEFAULT_IMAGE_CYCLE.toString())))
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
            .andExpect(jsonPath("$.insecteImage").value(DEFAULT_INSECTE_IMAGE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.cycleBio").value(DEFAULT_CYCLE_BIO.toString()))
            .andExpect(jsonPath("$.autrePlante").value(DEFAULT_AUTRE_PLANTE.toString()))
            .andExpect(jsonPath("$.imageCycle").value(DEFAULT_IMAGE_CYCLE.toString()))
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
        insecteRepository.saveAndFlush(insecte);

        int databaseSizeBeforeUpdate = insecteRepository.findAll().size();

        // Update the insecte
        Insecte updatedInsecte = insecteRepository.findById(insecte.getId()).get();
        // Disconnect from session so that the updates on updatedInsecte are not directly saved in db
        em.detach(updatedInsecte);
        updatedInsecte
            .nomInsecte(UPDATED_NOM_INSECTE)
            .nomScienInsecte(UPDATED_NOM_SCIEN_INSECTE)
            .insecteImage(UPDATED_INSECTE_IMAGE)
            .description(UPDATED_DESCRIPTION)
            .cycleBio(UPDATED_CYCLE_BIO)
            .autrePlante(UPDATED_AUTRE_PLANTE)
            .imageCycle(UPDATED_IMAGE_CYCLE)
            .dateValidation(UPDATED_DATE_VALIDATION)
            .dateAjout(UPDATED_DATE_AJOUT);
        InsecteDTO insecteDTO = insecteMapper.toDto(updatedInsecte);

        restInsecteMockMvc.perform(put("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecteDTO)))
            .andExpect(status().isOk());

        // Validate the Insecte in the database
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeUpdate);
        Insecte testInsecte = insecteList.get(insecteList.size() - 1);
        assertThat(testInsecte.getNomInsecte()).isEqualTo(UPDATED_NOM_INSECTE);
        assertThat(testInsecte.getNomScienInsecte()).isEqualTo(UPDATED_NOM_SCIEN_INSECTE);
        assertThat(testInsecte.getInsecteImage()).isEqualTo(UPDATED_INSECTE_IMAGE);
        assertThat(testInsecte.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInsecte.getCycleBio()).isEqualTo(UPDATED_CYCLE_BIO);
        assertThat(testInsecte.getAutrePlante()).isEqualTo(UPDATED_AUTRE_PLANTE);
        assertThat(testInsecte.getImageCycle()).isEqualTo(UPDATED_IMAGE_CYCLE);
        assertThat(testInsecte.getDateValidation()).isEqualTo(UPDATED_DATE_VALIDATION);
        assertThat(testInsecte.getDateAjout()).isEqualTo(UPDATED_DATE_AJOUT);
    }

    @Test
    @Transactional
    public void updateNonExistingInsecte() throws Exception {
        int databaseSizeBeforeUpdate = insecteRepository.findAll().size();

        // Create the Insecte
        InsecteDTO insecteDTO = insecteMapper.toDto(insecte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsecteMockMvc.perform(put("/api/insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(insecteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Insecte in the database
        List<Insecte> insecteList = insecteRepository.findAll();
        assertThat(insecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsecte() throws Exception {
        // Initialize the database
        insecteRepository.saveAndFlush(insecte);

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

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InsecteDTO.class);
        InsecteDTO insecteDTO1 = new InsecteDTO();
        insecteDTO1.setId(1L);
        InsecteDTO insecteDTO2 = new InsecteDTO();
        assertThat(insecteDTO1).isNotEqualTo(insecteDTO2);
        insecteDTO2.setId(insecteDTO1.getId());
        assertThat(insecteDTO1).isEqualTo(insecteDTO2);
        insecteDTO2.setId(2L);
        assertThat(insecteDTO1).isNotEqualTo(insecteDTO2);
        insecteDTO1.setId(null);
        assertThat(insecteDTO1).isNotEqualTo(insecteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(insecteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(insecteMapper.fromId(null)).isNull();
    }
}
