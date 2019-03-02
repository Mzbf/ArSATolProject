package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.Administrateur;
import com.arsatoll.app.repository.AdministrateurRepository;
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
 * Test class for the AdministrateurResource REST controller.
 *
 * @see AdministrateurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class AdministrateurResourceIntTest {

    @Autowired
    private AdministrateurRepository administrateurRepository;

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

    private MockMvc restAdministrateurMockMvc;

    private Administrateur administrateur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdministrateurResource administrateurResource = new AdministrateurResource(administrateurRepository);
        this.restAdministrateurMockMvc = MockMvcBuilders.standaloneSetup(administrateurResource)
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
    public static Administrateur createEntity(EntityManager em) {
        Administrateur administrateur = new Administrateur();
        return administrateur;
    }

    @Before
    public void initTest() {
        administrateur = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdministrateur() throws Exception {
        int databaseSizeBeforeCreate = administrateurRepository.findAll().size();

        // Create the Administrateur
        restAdministrateurMockMvc.perform(post("/api/administrateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(administrateur)))
            .andExpect(status().isCreated());

        // Validate the Administrateur in the database
        List<Administrateur> administrateurList = administrateurRepository.findAll();
        assertThat(administrateurList).hasSize(databaseSizeBeforeCreate + 1);
        Administrateur testAdministrateur = administrateurList.get(administrateurList.size() - 1);
    }

    @Test
    @Transactional
    public void createAdministrateurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = administrateurRepository.findAll().size();

        // Create the Administrateur with an existing ID
        administrateur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdministrateurMockMvc.perform(post("/api/administrateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(administrateur)))
            .andExpect(status().isBadRequest());

        // Validate the Administrateur in the database
        List<Administrateur> administrateurList = administrateurRepository.findAll();
        assertThat(administrateurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAdministrateurs() throws Exception {
        // Initialize the database
        administrateurRepository.saveAndFlush(administrateur);

        // Get all the administrateurList
        restAdministrateurMockMvc.perform(get("/api/administrateurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(administrateur.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getAdministrateur() throws Exception {
        // Initialize the database
        administrateurRepository.saveAndFlush(administrateur);

        // Get the administrateur
        restAdministrateurMockMvc.perform(get("/api/administrateurs/{id}", administrateur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(administrateur.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAdministrateur() throws Exception {
        // Get the administrateur
        restAdministrateurMockMvc.perform(get("/api/administrateurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdministrateur() throws Exception {
        // Initialize the database
        administrateurRepository.saveAndFlush(administrateur);

        int databaseSizeBeforeUpdate = administrateurRepository.findAll().size();

        // Update the administrateur
        Administrateur updatedAdministrateur = administrateurRepository.findById(administrateur.getId()).get();
        // Disconnect from session so that the updates on updatedAdministrateur are not directly saved in db
        em.detach(updatedAdministrateur);

        restAdministrateurMockMvc.perform(put("/api/administrateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdministrateur)))
            .andExpect(status().isOk());

        // Validate the Administrateur in the database
        List<Administrateur> administrateurList = administrateurRepository.findAll();
        assertThat(administrateurList).hasSize(databaseSizeBeforeUpdate);
        Administrateur testAdministrateur = administrateurList.get(administrateurList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingAdministrateur() throws Exception {
        int databaseSizeBeforeUpdate = administrateurRepository.findAll().size();

        // Create the Administrateur

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdministrateurMockMvc.perform(put("/api/administrateurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(administrateur)))
            .andExpect(status().isBadRequest());

        // Validate the Administrateur in the database
        List<Administrateur> administrateurList = administrateurRepository.findAll();
        assertThat(administrateurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdministrateur() throws Exception {
        // Initialize the database
        administrateurRepository.saveAndFlush(administrateur);

        int databaseSizeBeforeDelete = administrateurRepository.findAll().size();

        // Delete the administrateur
        restAdministrateurMockMvc.perform(delete("/api/administrateurs/{id}", administrateur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Administrateur> administrateurList = administrateurRepository.findAll();
        assertThat(administrateurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Administrateur.class);
        Administrateur administrateur1 = new Administrateur();
        administrateur1.setId(1L);
        Administrateur administrateur2 = new Administrateur();
        administrateur2.setId(administrateur1.getId());
        assertThat(administrateur1).isEqualTo(administrateur2);
        administrateur2.setId(2L);
        assertThat(administrateur1).isNotEqualTo(administrateur2);
        administrateur1.setId(null);
        assertThat(administrateur1).isNotEqualTo(administrateur2);
    }
}
