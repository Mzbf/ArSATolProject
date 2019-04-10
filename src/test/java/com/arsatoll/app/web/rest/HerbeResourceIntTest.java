package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.Herbe;
import com.arsatoll.app.repository.HerbeRepository;
import com.arsatoll.app.service.HerbeService;
import com.arsatoll.app.service.dto.HerbeDTO;
import com.arsatoll.app.service.mapper.HerbeMapper;
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
 * Test class for the HerbeResource REST controller.
 *
 * @see HerbeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class HerbeResourceIntTest {

    private static final String DEFAULT_NOM_HERBE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_HERBE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGES_HERBE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGES_HERBE = "BBBBBBBBBB";

    @Autowired
    private HerbeRepository herbeRepository;

    @Autowired
    private HerbeMapper herbeMapper;

    @Autowired
    private HerbeService herbeService;

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

    private MockMvc restHerbeMockMvc;

    private Herbe herbe;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HerbeResource herbeResource = new HerbeResource(herbeService);
        this.restHerbeMockMvc = MockMvcBuilders.standaloneSetup(herbeResource)
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
    public static Herbe createEntity(EntityManager em) {
        Herbe herbe = new Herbe()
            .nomHerbe(DEFAULT_NOM_HERBE)
            .description(DEFAULT_DESCRIPTION)
            .imagesHerbe(DEFAULT_IMAGES_HERBE);
        return herbe;
    }

    @Before
    public void initTest() {
        herbe = createEntity(em);
    }

    @Test
    @Transactional
    public void createHerbe() throws Exception {
        int databaseSizeBeforeCreate = herbeRepository.findAll().size();

        // Create the Herbe
        HerbeDTO herbeDTO = herbeMapper.toDto(herbe);
        restHerbeMockMvc.perform(post("/api/herbes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(herbeDTO)))
            .andExpect(status().isCreated());

        // Validate the Herbe in the database
        List<Herbe> herbeList = herbeRepository.findAll();
        assertThat(herbeList).hasSize(databaseSizeBeforeCreate + 1);
        Herbe testHerbe = herbeList.get(herbeList.size() - 1);
        assertThat(testHerbe.getNomHerbe()).isEqualTo(DEFAULT_NOM_HERBE);
        assertThat(testHerbe.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testHerbe.getImagesHerbe()).isEqualTo(DEFAULT_IMAGES_HERBE);
    }

    @Test
    @Transactional
    public void createHerbeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = herbeRepository.findAll().size();

        // Create the Herbe with an existing ID
        herbe.setId(1L);
        HerbeDTO herbeDTO = herbeMapper.toDto(herbe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHerbeMockMvc.perform(post("/api/herbes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(herbeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Herbe in the database
        List<Herbe> herbeList = herbeRepository.findAll();
        assertThat(herbeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHerbes() throws Exception {
        // Initialize the database
        herbeRepository.saveAndFlush(herbe);

        // Get all the herbeList
        restHerbeMockMvc.perform(get("/api/herbes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(herbe.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomHerbe").value(hasItem(DEFAULT_NOM_HERBE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].imagesHerbe").value(hasItem(DEFAULT_IMAGES_HERBE.toString())));
    }
    
    @Test
    @Transactional
    public void getHerbe() throws Exception {
        // Initialize the database
        herbeRepository.saveAndFlush(herbe);

        // Get the herbe
        restHerbeMockMvc.perform(get("/api/herbes/{id}", herbe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(herbe.getId().intValue()))
            .andExpect(jsonPath("$.nomHerbe").value(DEFAULT_NOM_HERBE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.imagesHerbe").value(DEFAULT_IMAGES_HERBE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHerbe() throws Exception {
        // Get the herbe
        restHerbeMockMvc.perform(get("/api/herbes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHerbe() throws Exception {
        // Initialize the database
        herbeRepository.saveAndFlush(herbe);

        int databaseSizeBeforeUpdate = herbeRepository.findAll().size();

        // Update the herbe
        Herbe updatedHerbe = herbeRepository.findById(herbe.getId()).get();
        // Disconnect from session so that the updates on updatedHerbe are not directly saved in db
        em.detach(updatedHerbe);
        updatedHerbe
            .nomHerbe(UPDATED_NOM_HERBE)
            .description(UPDATED_DESCRIPTION)
            .imagesHerbe(UPDATED_IMAGES_HERBE);
        HerbeDTO herbeDTO = herbeMapper.toDto(updatedHerbe);

        restHerbeMockMvc.perform(put("/api/herbes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(herbeDTO)))
            .andExpect(status().isOk());

        // Validate the Herbe in the database
        List<Herbe> herbeList = herbeRepository.findAll();
        assertThat(herbeList).hasSize(databaseSizeBeforeUpdate);
        Herbe testHerbe = herbeList.get(herbeList.size() - 1);
        assertThat(testHerbe.getNomHerbe()).isEqualTo(UPDATED_NOM_HERBE);
        assertThat(testHerbe.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testHerbe.getImagesHerbe()).isEqualTo(UPDATED_IMAGES_HERBE);
    }

    @Test
    @Transactional
    public void updateNonExistingHerbe() throws Exception {
        int databaseSizeBeforeUpdate = herbeRepository.findAll().size();

        // Create the Herbe
        HerbeDTO herbeDTO = herbeMapper.toDto(herbe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHerbeMockMvc.perform(put("/api/herbes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(herbeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Herbe in the database
        List<Herbe> herbeList = herbeRepository.findAll();
        assertThat(herbeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHerbe() throws Exception {
        // Initialize the database
        herbeRepository.saveAndFlush(herbe);

        int databaseSizeBeforeDelete = herbeRepository.findAll().size();

        // Delete the herbe
        restHerbeMockMvc.perform(delete("/api/herbes/{id}", herbe.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Herbe> herbeList = herbeRepository.findAll();
        assertThat(herbeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Herbe.class);
        Herbe herbe1 = new Herbe();
        herbe1.setId(1L);
        Herbe herbe2 = new Herbe();
        herbe2.setId(herbe1.getId());
        assertThat(herbe1).isEqualTo(herbe2);
        herbe2.setId(2L);
        assertThat(herbe1).isNotEqualTo(herbe2);
        herbe1.setId(null);
        assertThat(herbe1).isNotEqualTo(herbe2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HerbeDTO.class);
        HerbeDTO herbeDTO1 = new HerbeDTO();
        herbeDTO1.setId(1L);
        HerbeDTO herbeDTO2 = new HerbeDTO();
        assertThat(herbeDTO1).isNotEqualTo(herbeDTO2);
        herbeDTO2.setId(herbeDTO1.getId());
        assertThat(herbeDTO1).isEqualTo(herbeDTO2);
        herbeDTO2.setId(2L);
        assertThat(herbeDTO1).isNotEqualTo(herbeDTO2);
        herbeDTO1.setId(null);
        assertThat(herbeDTO1).isNotEqualTo(herbeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(herbeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(herbeMapper.fromId(null)).isNull();
    }
}
