package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.Agriculteur;
import com.arsatoll.app.repository.AgriculteurRepository;
import com.arsatoll.app.service.AgriculteurService;
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
 * Test class for the AgriculteurResource REST controller.
 *
 * @see AgriculteurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class AgriculteurResourceIntTest {

    private static final String DEFAULT_LOCALISTION = "AAAAAAAAAA";
    private static final String UPDATED_LOCALISTION = "BBBBBBBBBB";

    @Autowired
    private AgriculteurRepository agriculteurRepository;

    @Autowired
    private AgriculteurService agriculteurService;

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

    private MockMvc restAgriculteurMockMvc;

    private Agriculteur agriculteur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AgriculteurResource agriculteurResource = new AgriculteurResource(agriculteurService);
        this.restAgriculteurMockMvc = MockMvcBuilders.standaloneSetup(agriculteurResource)
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
    public static Agriculteur createEntity(EntityManager em) {
        Agriculteur agriculteur = new Agriculteur()
            .localistion(DEFAULT_LOCALISTION);
        return agriculteur;
    }

    @Before
    public void initTest() {
        agriculteur = createEntity(em);
    }

    @Test
    @Transactional
    public void createAgriculteur() throws Exception {
        int databaseSizeBeforeCreate = agriculteurRepository.findAll().size();

        // Create the Agriculteur
        restAgriculteurMockMvc.perform(post("/api/agriculteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agriculteur)))
            .andExpect(status().isCreated());

        // Validate the Agriculteur in the database
        List<Agriculteur> agriculteurList = agriculteurRepository.findAll();
        assertThat(agriculteurList).hasSize(databaseSizeBeforeCreate + 1);
        Agriculteur testAgriculteur = agriculteurList.get(agriculteurList.size() - 1);
        assertThat(testAgriculteur.getLocalistion()).isEqualTo(DEFAULT_LOCALISTION);
    }

    @Test
    @Transactional
    public void createAgriculteurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = agriculteurRepository.findAll().size();

        // Create the Agriculteur with an existing ID
        agriculteur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgriculteurMockMvc.perform(post("/api/agriculteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agriculteur)))
            .andExpect(status().isBadRequest());

        // Validate the Agriculteur in the database
        List<Agriculteur> agriculteurList = agriculteurRepository.findAll();
        assertThat(agriculteurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAgriculteurs() throws Exception {
        // Initialize the database
        agriculteurRepository.saveAndFlush(agriculteur);

        // Get all the agriculteurList
        restAgriculteurMockMvc.perform(get("/api/agriculteurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agriculteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].localistion").value(hasItem(DEFAULT_LOCALISTION.toString())));
    }
    
    @Test
    @Transactional
    public void getAgriculteur() throws Exception {
        // Initialize the database
        agriculteurRepository.saveAndFlush(agriculteur);

        // Get the agriculteur
        restAgriculteurMockMvc.perform(get("/api/agriculteurs/{id}", agriculteur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(agriculteur.getId().intValue()))
            .andExpect(jsonPath("$.localistion").value(DEFAULT_LOCALISTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAgriculteur() throws Exception {
        // Get the agriculteur
        restAgriculteurMockMvc.perform(get("/api/agriculteurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAgriculteur() throws Exception {
        // Initialize the database
        agriculteurService.save(agriculteur);

        int databaseSizeBeforeUpdate = agriculteurRepository.findAll().size();

        // Update the agriculteur
        Agriculteur updatedAgriculteur = agriculteurRepository.findById(agriculteur.getId()).get();
        // Disconnect from session so that the updates on updatedAgriculteur are not directly saved in db
        em.detach(updatedAgriculteur);
        updatedAgriculteur
            .localistion(UPDATED_LOCALISTION);

        restAgriculteurMockMvc.perform(put("/api/agriculteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAgriculteur)))
            .andExpect(status().isOk());

        // Validate the Agriculteur in the database
        List<Agriculteur> agriculteurList = agriculteurRepository.findAll();
        assertThat(agriculteurList).hasSize(databaseSizeBeforeUpdate);
        Agriculteur testAgriculteur = agriculteurList.get(agriculteurList.size() - 1);
        assertThat(testAgriculteur.getLocalistion()).isEqualTo(UPDATED_LOCALISTION);
    }

    @Test
    @Transactional
    public void updateNonExistingAgriculteur() throws Exception {
        int databaseSizeBeforeUpdate = agriculteurRepository.findAll().size();

        // Create the Agriculteur

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgriculteurMockMvc.perform(put("/api/agriculteurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(agriculteur)))
            .andExpect(status().isBadRequest());

        // Validate the Agriculteur in the database
        List<Agriculteur> agriculteurList = agriculteurRepository.findAll();
        assertThat(agriculteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAgriculteur() throws Exception {
        // Initialize the database
        agriculteurService.save(agriculteur);

        int databaseSizeBeforeDelete = agriculteurRepository.findAll().size();

        // Delete the agriculteur
        restAgriculteurMockMvc.perform(delete("/api/agriculteurs/{id}", agriculteur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Agriculteur> agriculteurList = agriculteurRepository.findAll();
        assertThat(agriculteurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Agriculteur.class);
        Agriculteur agriculteur1 = new Agriculteur();
        agriculteur1.setId(1L);
        Agriculteur agriculteur2 = new Agriculteur();
        agriculteur2.setId(agriculteur1.getId());
        assertThat(agriculteur1).isEqualTo(agriculteur2);
        agriculteur2.setId(2L);
        assertThat(agriculteur1).isNotEqualTo(agriculteur2);
        agriculteur1.setId(null);
        assertThat(agriculteur1).isNotEqualTo(agriculteur2);
    }
}
