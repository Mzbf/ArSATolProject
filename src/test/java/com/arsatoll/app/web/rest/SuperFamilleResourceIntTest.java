package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.SuperFamille;
import com.arsatoll.app.repository.SuperFamilleRepository;
import com.arsatoll.app.service.SuperFamilleService;
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
 * Test class for the SuperFamilleResource REST controller.
 *
 * @see SuperFamilleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class SuperFamilleResourceIntTest {

    @Autowired
    private SuperFamilleRepository superFamilleRepository;

    @Autowired
    private SuperFamilleService superFamilleService;

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

    private MockMvc restSuperFamilleMockMvc;

    private SuperFamille superFamille;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SuperFamilleResource superFamilleResource = new SuperFamilleResource(superFamilleService);
        this.restSuperFamilleMockMvc = MockMvcBuilders.standaloneSetup(superFamilleResource)
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
    public static SuperFamille createEntity(EntityManager em) {
        SuperFamille superFamille = new SuperFamille();
        return superFamille;
    }

    @Before
    public void initTest() {
        superFamille = createEntity(em);
    }

    @Test
    @Transactional
    public void createSuperFamille() throws Exception {
        int databaseSizeBeforeCreate = superFamilleRepository.findAll().size();

        // Create the SuperFamille
        restSuperFamilleMockMvc.perform(post("/api/super-familles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(superFamille)))
            .andExpect(status().isCreated());

        // Validate the SuperFamille in the database
        List<SuperFamille> superFamilleList = superFamilleRepository.findAll();
        assertThat(superFamilleList).hasSize(databaseSizeBeforeCreate + 1);
        SuperFamille testSuperFamille = superFamilleList.get(superFamilleList.size() - 1);
    }

    @Test
    @Transactional
    public void createSuperFamilleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = superFamilleRepository.findAll().size();

        // Create the SuperFamille with an existing ID
        superFamille.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSuperFamilleMockMvc.perform(post("/api/super-familles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(superFamille)))
            .andExpect(status().isBadRequest());

        // Validate the SuperFamille in the database
        List<SuperFamille> superFamilleList = superFamilleRepository.findAll();
        assertThat(superFamilleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSuperFamilles() throws Exception {
        // Initialize the database
        superFamilleRepository.saveAndFlush(superFamille);

        // Get all the superFamilleList
        restSuperFamilleMockMvc.perform(get("/api/super-familles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(superFamille.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getSuperFamille() throws Exception {
        // Initialize the database
        superFamilleRepository.saveAndFlush(superFamille);

        // Get the superFamille
        restSuperFamilleMockMvc.perform(get("/api/super-familles/{id}", superFamille.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(superFamille.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSuperFamille() throws Exception {
        // Get the superFamille
        restSuperFamilleMockMvc.perform(get("/api/super-familles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSuperFamille() throws Exception {
        // Initialize the database
        superFamilleService.save(superFamille);

        int databaseSizeBeforeUpdate = superFamilleRepository.findAll().size();

        // Update the superFamille
        SuperFamille updatedSuperFamille = superFamilleRepository.findById(superFamille.getId()).get();
        // Disconnect from session so that the updates on updatedSuperFamille are not directly saved in db
        em.detach(updatedSuperFamille);

        restSuperFamilleMockMvc.perform(put("/api/super-familles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSuperFamille)))
            .andExpect(status().isOk());

        // Validate the SuperFamille in the database
        List<SuperFamille> superFamilleList = superFamilleRepository.findAll();
        assertThat(superFamilleList).hasSize(databaseSizeBeforeUpdate);
        SuperFamille testSuperFamille = superFamilleList.get(superFamilleList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingSuperFamille() throws Exception {
        int databaseSizeBeforeUpdate = superFamilleRepository.findAll().size();

        // Create the SuperFamille

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSuperFamilleMockMvc.perform(put("/api/super-familles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(superFamille)))
            .andExpect(status().isBadRequest());

        // Validate the SuperFamille in the database
        List<SuperFamille> superFamilleList = superFamilleRepository.findAll();
        assertThat(superFamilleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSuperFamille() throws Exception {
        // Initialize the database
        superFamilleService.save(superFamille);

        int databaseSizeBeforeDelete = superFamilleRepository.findAll().size();

        // Delete the superFamille
        restSuperFamilleMockMvc.perform(delete("/api/super-familles/{id}", superFamille.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SuperFamille> superFamilleList = superFamilleRepository.findAll();
        assertThat(superFamilleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SuperFamille.class);
        SuperFamille superFamille1 = new SuperFamille();
        superFamille1.setId(1L);
        SuperFamille superFamille2 = new SuperFamille();
        superFamille2.setId(superFamille1.getId());
        assertThat(superFamille1).isEqualTo(superFamille2);
        superFamille2.setId(2L);
        assertThat(superFamille1).isNotEqualTo(superFamille2);
        superFamille1.setId(null);
        assertThat(superFamille1).isNotEqualTo(superFamille2);
    }
}
