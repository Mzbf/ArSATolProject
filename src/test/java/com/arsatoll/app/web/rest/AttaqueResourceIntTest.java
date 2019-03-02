package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.Attaque;
import com.arsatoll.app.repository.AttaqueRepository;
import com.arsatoll.app.service.AttaqueService;
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
 * Test class for the AttaqueResource REST controller.
 *
 * @see AttaqueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class AttaqueResourceIntTest {

    private static final String DEFAULT_LOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCALISATION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FLAG = false;
    private static final Boolean UPDATED_FLAG = true;

    private static final Instant DEFAULT_DATE_VALIDATION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_VALIDATION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_AJOUT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_AJOUT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private AttaqueRepository attaqueRepository;

    @Autowired
    private AttaqueService attaqueService;

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

    private MockMvc restAttaqueMockMvc;

    private Attaque attaque;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AttaqueResource attaqueResource = new AttaqueResource(attaqueService);
        this.restAttaqueMockMvc = MockMvcBuilders.standaloneSetup(attaqueResource)
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
    public static Attaque createEntity(EntityManager em) {
        Attaque attaque = new Attaque()
            .localisation(DEFAULT_LOCALISATION)
            .description(DEFAULT_DESCRIPTION)
            .flag(DEFAULT_FLAG)
            .dateValidation(DEFAULT_DATE_VALIDATION)
            .dateAjout(DEFAULT_DATE_AJOUT);
        return attaque;
    }

    @Before
    public void initTest() {
        attaque = createEntity(em);
    }

    @Test
    @Transactional
    public void createAttaque() throws Exception {
        int databaseSizeBeforeCreate = attaqueRepository.findAll().size();

        // Create the Attaque
        restAttaqueMockMvc.perform(post("/api/attaques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attaque)))
            .andExpect(status().isCreated());

        // Validate the Attaque in the database
        List<Attaque> attaqueList = attaqueRepository.findAll();
        assertThat(attaqueList).hasSize(databaseSizeBeforeCreate + 1);
        Attaque testAttaque = attaqueList.get(attaqueList.size() - 1);
        assertThat(testAttaque.getLocalisation()).isEqualTo(DEFAULT_LOCALISATION);
        assertThat(testAttaque.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAttaque.isFlag()).isEqualTo(DEFAULT_FLAG);
        assertThat(testAttaque.getDateValidation()).isEqualTo(DEFAULT_DATE_VALIDATION);
        assertThat(testAttaque.getDateAjout()).isEqualTo(DEFAULT_DATE_AJOUT);
    }

    @Test
    @Transactional
    public void createAttaqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = attaqueRepository.findAll().size();

        // Create the Attaque with an existing ID
        attaque.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAttaqueMockMvc.perform(post("/api/attaques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attaque)))
            .andExpect(status().isBadRequest());

        // Validate the Attaque in the database
        List<Attaque> attaqueList = attaqueRepository.findAll();
        assertThat(attaqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAttaques() throws Exception {
        // Initialize the database
        attaqueRepository.saveAndFlush(attaque);

        // Get all the attaqueList
        restAttaqueMockMvc.perform(get("/api/attaques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(attaque.getId().intValue())))
            .andExpect(jsonPath("$.[*].localisation").value(hasItem(DEFAULT_LOCALISATION.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].flag").value(hasItem(DEFAULT_FLAG.booleanValue())))
            .andExpect(jsonPath("$.[*].dateValidation").value(hasItem(DEFAULT_DATE_VALIDATION.toString())))
            .andExpect(jsonPath("$.[*].dateAjout").value(hasItem(DEFAULT_DATE_AJOUT.toString())));
    }
    
    @Test
    @Transactional
    public void getAttaque() throws Exception {
        // Initialize the database
        attaqueRepository.saveAndFlush(attaque);

        // Get the attaque
        restAttaqueMockMvc.perform(get("/api/attaques/{id}", attaque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(attaque.getId().intValue()))
            .andExpect(jsonPath("$.localisation").value(DEFAULT_LOCALISATION.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.flag").value(DEFAULT_FLAG.booleanValue()))
            .andExpect(jsonPath("$.dateValidation").value(DEFAULT_DATE_VALIDATION.toString()))
            .andExpect(jsonPath("$.dateAjout").value(DEFAULT_DATE_AJOUT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAttaque() throws Exception {
        // Get the attaque
        restAttaqueMockMvc.perform(get("/api/attaques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAttaque() throws Exception {
        // Initialize the database
        attaqueService.save(attaque);

        int databaseSizeBeforeUpdate = attaqueRepository.findAll().size();

        // Update the attaque
        Attaque updatedAttaque = attaqueRepository.findById(attaque.getId()).get();
        // Disconnect from session so that the updates on updatedAttaque are not directly saved in db
        em.detach(updatedAttaque);
        updatedAttaque
            .localisation(UPDATED_LOCALISATION)
            .description(UPDATED_DESCRIPTION)
            .flag(UPDATED_FLAG)
            .dateValidation(UPDATED_DATE_VALIDATION)
            .dateAjout(UPDATED_DATE_AJOUT);

        restAttaqueMockMvc.perform(put("/api/attaques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAttaque)))
            .andExpect(status().isOk());

        // Validate the Attaque in the database
        List<Attaque> attaqueList = attaqueRepository.findAll();
        assertThat(attaqueList).hasSize(databaseSizeBeforeUpdate);
        Attaque testAttaque = attaqueList.get(attaqueList.size() - 1);
        assertThat(testAttaque.getLocalisation()).isEqualTo(UPDATED_LOCALISATION);
        assertThat(testAttaque.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAttaque.isFlag()).isEqualTo(UPDATED_FLAG);
        assertThat(testAttaque.getDateValidation()).isEqualTo(UPDATED_DATE_VALIDATION);
        assertThat(testAttaque.getDateAjout()).isEqualTo(UPDATED_DATE_AJOUT);
    }

    @Test
    @Transactional
    public void updateNonExistingAttaque() throws Exception {
        int databaseSizeBeforeUpdate = attaqueRepository.findAll().size();

        // Create the Attaque

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAttaqueMockMvc.perform(put("/api/attaques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(attaque)))
            .andExpect(status().isBadRequest());

        // Validate the Attaque in the database
        List<Attaque> attaqueList = attaqueRepository.findAll();
        assertThat(attaqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAttaque() throws Exception {
        // Initialize the database
        attaqueService.save(attaque);

        int databaseSizeBeforeDelete = attaqueRepository.findAll().size();

        // Delete the attaque
        restAttaqueMockMvc.perform(delete("/api/attaques/{id}", attaque.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Attaque> attaqueList = attaqueRepository.findAll();
        assertThat(attaqueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Attaque.class);
        Attaque attaque1 = new Attaque();
        attaque1.setId(1L);
        Attaque attaque2 = new Attaque();
        attaque2.setId(attaque1.getId());
        assertThat(attaque1).isEqualTo(attaque2);
        attaque2.setId(2L);
        assertThat(attaque1).isNotEqualTo(attaque2);
        attaque1.setId(null);
        assertThat(attaque1).isNotEqualTo(attaque2);
    }
}
