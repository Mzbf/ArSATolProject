package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.Culture;
import com.arsatoll.app.repository.CultureRepository;
import com.arsatoll.app.service.CultureService;
import com.arsatoll.app.service.dto.CultureDTO;
import com.arsatoll.app.service.mapper.CultureMapper;
import com.arsatoll.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.arsatoll.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CultureResource REST controller.
 *
 * @see CultureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class CultureResourceIntTest {

    private static final String DEFAULT_NOM_CULTURE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_CULTURE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_CULTURE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_CULTURE = "BBBBBBBBBB";

    @Autowired
    private CultureRepository cultureRepository;

    @Mock
    private CultureRepository cultureRepositoryMock;

    @Autowired
    private CultureMapper cultureMapper;

    @Mock
    private CultureService cultureServiceMock;

    @Autowired
    private CultureService cultureService;

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

    private MockMvc restCultureMockMvc;

    private Culture culture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CultureResource cultureResource = new CultureResource(cultureService);
        this.restCultureMockMvc = MockMvcBuilders.standaloneSetup(cultureResource)
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
    public static Culture createEntity(EntityManager em) {
        Culture culture = new Culture()
            .nomCulture(DEFAULT_NOM_CULTURE)
            .imageCulture(DEFAULT_IMAGE_CULTURE);
        return culture;
    }

    @Before
    public void initTest() {
        culture = createEntity(em);
    }

    @Test
    @Transactional
    public void createCulture() throws Exception {
        int databaseSizeBeforeCreate = cultureRepository.findAll().size();

        // Create the Culture
        CultureDTO cultureDTO = cultureMapper.toDto(culture);
        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isCreated());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeCreate + 1);
        Culture testCulture = cultureList.get(cultureList.size() - 1);
        assertThat(testCulture.getNomCulture()).isEqualTo(DEFAULT_NOM_CULTURE);
        assertThat(testCulture.getImageCulture()).isEqualTo(DEFAULT_IMAGE_CULTURE);
    }

    @Test
    @Transactional
    public void createCultureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cultureRepository.findAll().size();

        // Create the Culture with an existing ID
        culture.setId(1L);
        CultureDTO cultureDTO = cultureMapper.toDto(culture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomCultureIsRequired() throws Exception {
        int databaseSizeBeforeTest = cultureRepository.findAll().size();
        // set the field null
        culture.setNomCulture(null);

        // Create the Culture, which fails.
        CultureDTO cultureDTO = cultureMapper.toDto(culture);

        restCultureMockMvc.perform(post("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isBadRequest());

        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCultures() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        // Get all the cultureList
        restCultureMockMvc.perform(get("/api/cultures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(culture.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomCulture").value(hasItem(DEFAULT_NOM_CULTURE.toString())))
            .andExpect(jsonPath("$.[*].imageCulture").value(hasItem(DEFAULT_IMAGE_CULTURE.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllCulturesWithEagerRelationshipsIsEnabled() throws Exception {
        CultureResource cultureResource = new CultureResource(cultureServiceMock);
        when(cultureServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCultureMockMvc = MockMvcBuilders.standaloneSetup(cultureResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCultureMockMvc.perform(get("/api/cultures?eagerload=true"))
        .andExpect(status().isOk());

        verify(cultureServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllCulturesWithEagerRelationshipsIsNotEnabled() throws Exception {
        CultureResource cultureResource = new CultureResource(cultureServiceMock);
            when(cultureServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCultureMockMvc = MockMvcBuilders.standaloneSetup(cultureResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCultureMockMvc.perform(get("/api/cultures?eagerload=true"))
        .andExpect(status().isOk());

            verify(cultureServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCulture() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        // Get the culture
        restCultureMockMvc.perform(get("/api/cultures/{id}", culture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(culture.getId().intValue()))
            .andExpect(jsonPath("$.nomCulture").value(DEFAULT_NOM_CULTURE.toString()))
            .andExpect(jsonPath("$.imageCulture").value(DEFAULT_IMAGE_CULTURE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCulture() throws Exception {
        // Get the culture
        restCultureMockMvc.perform(get("/api/cultures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCulture() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        int databaseSizeBeforeUpdate = cultureRepository.findAll().size();

        // Update the culture
        Culture updatedCulture = cultureRepository.findById(culture.getId()).get();
        // Disconnect from session so that the updates on updatedCulture are not directly saved in db
        em.detach(updatedCulture);
        updatedCulture
            .nomCulture(UPDATED_NOM_CULTURE)
            .imageCulture(UPDATED_IMAGE_CULTURE);
        CultureDTO cultureDTO = cultureMapper.toDto(updatedCulture);

        restCultureMockMvc.perform(put("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isOk());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeUpdate);
        Culture testCulture = cultureList.get(cultureList.size() - 1);
        assertThat(testCulture.getNomCulture()).isEqualTo(UPDATED_NOM_CULTURE);
        assertThat(testCulture.getImageCulture()).isEqualTo(UPDATED_IMAGE_CULTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingCulture() throws Exception {
        int databaseSizeBeforeUpdate = cultureRepository.findAll().size();

        // Create the Culture
        CultureDTO cultureDTO = cultureMapper.toDto(culture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCultureMockMvc.perform(put("/api/cultures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cultureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Culture in the database
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCulture() throws Exception {
        // Initialize the database
        cultureRepository.saveAndFlush(culture);

        int databaseSizeBeforeDelete = cultureRepository.findAll().size();

        // Delete the culture
        restCultureMockMvc.perform(delete("/api/cultures/{id}", culture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Culture> cultureList = cultureRepository.findAll();
        assertThat(cultureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Culture.class);
        Culture culture1 = new Culture();
        culture1.setId(1L);
        Culture culture2 = new Culture();
        culture2.setId(culture1.getId());
        assertThat(culture1).isEqualTo(culture2);
        culture2.setId(2L);
        assertThat(culture1).isNotEqualTo(culture2);
        culture1.setId(null);
        assertThat(culture1).isNotEqualTo(culture2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CultureDTO.class);
        CultureDTO cultureDTO1 = new CultureDTO();
        cultureDTO1.setId(1L);
        CultureDTO cultureDTO2 = new CultureDTO();
        assertThat(cultureDTO1).isNotEqualTo(cultureDTO2);
        cultureDTO2.setId(cultureDTO1.getId());
        assertThat(cultureDTO1).isEqualTo(cultureDTO2);
        cultureDTO2.setId(2L);
        assertThat(cultureDTO1).isNotEqualTo(cultureDTO2);
        cultureDTO1.setId(null);
        assertThat(cultureDTO1).isNotEqualTo(cultureDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cultureMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cultureMapper.fromId(null)).isNull();
    }
}
