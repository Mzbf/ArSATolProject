package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.TypeCulture;
import com.arsatoll.app.repository.TypeCultureRepository;
import com.arsatoll.app.service.TypeCultureService;
import com.arsatoll.app.service.dto.TypeCultureDTO;
import com.arsatoll.app.service.mapper.TypeCultureMapper;
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
 * Test class for the TypeCultureResource REST controller.
 *
 * @see TypeCultureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class TypeCultureResourceIntTest {

    private static final String DEFAULT_NOM_TYPE_CULTURE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TYPE_CULTURE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TypeCultureRepository typeCultureRepository;

    @Autowired
    private TypeCultureMapper typeCultureMapper;

    @Autowired
    private TypeCultureService typeCultureService;

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

    private MockMvc restTypeCultureMockMvc;

    private TypeCulture typeCulture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeCultureResource typeCultureResource = new TypeCultureResource(typeCultureService);
        this.restTypeCultureMockMvc = MockMvcBuilders.standaloneSetup(typeCultureResource)
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
    public static TypeCulture createEntity(EntityManager em) {
        TypeCulture typeCulture = new TypeCulture()
            .nomTypeCulture(DEFAULT_NOM_TYPE_CULTURE)
            .description(DEFAULT_DESCRIPTION);
        return typeCulture;
    }

    @Before
    public void initTest() {
        typeCulture = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeCulture() throws Exception {
        int databaseSizeBeforeCreate = typeCultureRepository.findAll().size();

        // Create the TypeCulture
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(typeCulture);
        restTypeCultureMockMvc.perform(post("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeCulture in the database
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeCreate + 1);
        TypeCulture testTypeCulture = typeCultureList.get(typeCultureList.size() - 1);
        assertThat(testTypeCulture.getNomTypeCulture()).isEqualTo(DEFAULT_NOM_TYPE_CULTURE);
        assertThat(testTypeCulture.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTypeCultureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeCultureRepository.findAll().size();

        // Create the TypeCulture with an existing ID
        typeCulture.setId(1L);
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(typeCulture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeCultureMockMvc.perform(post("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeCulture in the database
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTypeCultures() throws Exception {
        // Initialize the database
        typeCultureRepository.saveAndFlush(typeCulture);

        // Get all the typeCultureList
        restTypeCultureMockMvc.perform(get("/api/type-cultures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeCulture.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomTypeCulture").value(hasItem(DEFAULT_NOM_TYPE_CULTURE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeCulture() throws Exception {
        // Initialize the database
        typeCultureRepository.saveAndFlush(typeCulture);

        // Get the typeCulture
        restTypeCultureMockMvc.perform(get("/api/type-cultures/{id}", typeCulture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeCulture.getId().intValue()))
            .andExpect(jsonPath("$.nomTypeCulture").value(DEFAULT_NOM_TYPE_CULTURE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeCulture() throws Exception {
        // Get the typeCulture
        restTypeCultureMockMvc.perform(get("/api/type-cultures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeCulture() throws Exception {
        // Initialize the database
        typeCultureRepository.saveAndFlush(typeCulture);

        int databaseSizeBeforeUpdate = typeCultureRepository.findAll().size();

        // Update the typeCulture
        TypeCulture updatedTypeCulture = typeCultureRepository.findById(typeCulture.getId()).get();
        // Disconnect from session so that the updates on updatedTypeCulture are not directly saved in db
        em.detach(updatedTypeCulture);
        updatedTypeCulture
            .nomTypeCulture(UPDATED_NOM_TYPE_CULTURE)
            .description(UPDATED_DESCRIPTION);
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(updatedTypeCulture);

        restTypeCultureMockMvc.perform(put("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isOk());

        // Validate the TypeCulture in the database
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeUpdate);
        TypeCulture testTypeCulture = typeCultureList.get(typeCultureList.size() - 1);
        assertThat(testTypeCulture.getNomTypeCulture()).isEqualTo(UPDATED_NOM_TYPE_CULTURE);
        assertThat(testTypeCulture.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeCulture() throws Exception {
        int databaseSizeBeforeUpdate = typeCultureRepository.findAll().size();

        // Create the TypeCulture
        TypeCultureDTO typeCultureDTO = typeCultureMapper.toDto(typeCulture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeCultureMockMvc.perform(put("/api/type-cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeCultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeCulture in the database
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeCulture() throws Exception {
        // Initialize the database
        typeCultureRepository.saveAndFlush(typeCulture);

        int databaseSizeBeforeDelete = typeCultureRepository.findAll().size();

        // Delete the typeCulture
        restTypeCultureMockMvc.perform(delete("/api/type-cultures/{id}", typeCulture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeCulture> typeCultureList = typeCultureRepository.findAll();
        assertThat(typeCultureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeCulture.class);
        TypeCulture typeCulture1 = new TypeCulture();
        typeCulture1.setId(1L);
        TypeCulture typeCulture2 = new TypeCulture();
        typeCulture2.setId(typeCulture1.getId());
        assertThat(typeCulture1).isEqualTo(typeCulture2);
        typeCulture2.setId(2L);
        assertThat(typeCulture1).isNotEqualTo(typeCulture2);
        typeCulture1.setId(null);
        assertThat(typeCulture1).isNotEqualTo(typeCulture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeCultureDTO.class);
        TypeCultureDTO typeCultureDTO1 = new TypeCultureDTO();
        typeCultureDTO1.setId(1L);
        TypeCultureDTO typeCultureDTO2 = new TypeCultureDTO();
        assertThat(typeCultureDTO1).isNotEqualTo(typeCultureDTO2);
        typeCultureDTO2.setId(typeCultureDTO1.getId());
        assertThat(typeCultureDTO1).isEqualTo(typeCultureDTO2);
        typeCultureDTO2.setId(2L);
        assertThat(typeCultureDTO1).isNotEqualTo(typeCultureDTO2);
        typeCultureDTO1.setId(null);
        assertThat(typeCultureDTO1).isNotEqualTo(typeCultureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeCultureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeCultureMapper.fromId(null)).isNull();
    }
}
