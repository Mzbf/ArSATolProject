package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.TypeDegat;
import com.arsatoll.app.repository.TypeDegatRepository;
import com.arsatoll.app.service.TypeDegatService;
import com.arsatoll.app.service.dto.TypeDegatDTO;
import com.arsatoll.app.service.mapper.TypeDegatMapper;
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
 * Test class for the TypeDegatResource REST controller.
 *
 * @see TypeDegatResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class TypeDegatResourceIntTest {

    private static final String DEFAULT_TYPE_DEG = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_DEG = "BBBBBBBBBB";

    @Autowired
    private TypeDegatRepository typeDegatRepository;

    @Autowired
    private TypeDegatMapper typeDegatMapper;

    @Autowired
    private TypeDegatService typeDegatService;

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

    private MockMvc restTypeDegatMockMvc;

    private TypeDegat typeDegat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeDegatResource typeDegatResource = new TypeDegatResource(typeDegatService);
        this.restTypeDegatMockMvc = MockMvcBuilders.standaloneSetup(typeDegatResource)
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
    public static TypeDegat createEntity(EntityManager em) {
        TypeDegat typeDegat = new TypeDegat()
            .typeDeg(DEFAULT_TYPE_DEG);
        return typeDegat;
    }

    @Before
    public void initTest() {
        typeDegat = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeDegat() throws Exception {
        int databaseSizeBeforeCreate = typeDegatRepository.findAll().size();

        // Create the TypeDegat
        TypeDegatDTO typeDegatDTO = typeDegatMapper.toDto(typeDegat);
        restTypeDegatMockMvc.perform(post("/api/type-degats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeDegatDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeDegat in the database
        List<TypeDegat> typeDegatList = typeDegatRepository.findAll();
        assertThat(typeDegatList).hasSize(databaseSizeBeforeCreate + 1);
        TypeDegat testTypeDegat = typeDegatList.get(typeDegatList.size() - 1);
        assertThat(testTypeDegat.getTypeDeg()).isEqualTo(DEFAULT_TYPE_DEG);
    }

    @Test
    @Transactional
    public void createTypeDegatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeDegatRepository.findAll().size();

        // Create the TypeDegat with an existing ID
        typeDegat.setId(1L);
        TypeDegatDTO typeDegatDTO = typeDegatMapper.toDto(typeDegat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeDegatMockMvc.perform(post("/api/type-degats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeDegatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDegat in the database
        List<TypeDegat> typeDegatList = typeDegatRepository.findAll();
        assertThat(typeDegatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeDegIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeDegatRepository.findAll().size();
        // set the field null
        typeDegat.setTypeDeg(null);

        // Create the TypeDegat, which fails.
        TypeDegatDTO typeDegatDTO = typeDegatMapper.toDto(typeDegat);

        restTypeDegatMockMvc.perform(post("/api/type-degats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeDegatDTO)))
            .andExpect(status().isBadRequest());

        List<TypeDegat> typeDegatList = typeDegatRepository.findAll();
        assertThat(typeDegatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeDegats() throws Exception {
        // Initialize the database
        typeDegatRepository.saveAndFlush(typeDegat);

        // Get all the typeDegatList
        restTypeDegatMockMvc.perform(get("/api/type-degats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeDegat.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeDeg").value(hasItem(DEFAULT_TYPE_DEG.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeDegat() throws Exception {
        // Initialize the database
        typeDegatRepository.saveAndFlush(typeDegat);

        // Get the typeDegat
        restTypeDegatMockMvc.perform(get("/api/type-degats/{id}", typeDegat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeDegat.getId().intValue()))
            .andExpect(jsonPath("$.typeDeg").value(DEFAULT_TYPE_DEG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeDegat() throws Exception {
        // Get the typeDegat
        restTypeDegatMockMvc.perform(get("/api/type-degats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeDegat() throws Exception {
        // Initialize the database
        typeDegatRepository.saveAndFlush(typeDegat);

        int databaseSizeBeforeUpdate = typeDegatRepository.findAll().size();

        // Update the typeDegat
        TypeDegat updatedTypeDegat = typeDegatRepository.findById(typeDegat.getId()).get();
        // Disconnect from session so that the updates on updatedTypeDegat are not directly saved in db
        em.detach(updatedTypeDegat);
        updatedTypeDegat
            .typeDeg(UPDATED_TYPE_DEG);
        TypeDegatDTO typeDegatDTO = typeDegatMapper.toDto(updatedTypeDegat);

        restTypeDegatMockMvc.perform(put("/api/type-degats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeDegatDTO)))
            .andExpect(status().isOk());

        // Validate the TypeDegat in the database
        List<TypeDegat> typeDegatList = typeDegatRepository.findAll();
        assertThat(typeDegatList).hasSize(databaseSizeBeforeUpdate);
        TypeDegat testTypeDegat = typeDegatList.get(typeDegatList.size() - 1);
        assertThat(testTypeDegat.getTypeDeg()).isEqualTo(UPDATED_TYPE_DEG);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeDegat() throws Exception {
        int databaseSizeBeforeUpdate = typeDegatRepository.findAll().size();

        // Create the TypeDegat
        TypeDegatDTO typeDegatDTO = typeDegatMapper.toDto(typeDegat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeDegatMockMvc.perform(put("/api/type-degats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeDegatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDegat in the database
        List<TypeDegat> typeDegatList = typeDegatRepository.findAll();
        assertThat(typeDegatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeDegat() throws Exception {
        // Initialize the database
        typeDegatRepository.saveAndFlush(typeDegat);

        int databaseSizeBeforeDelete = typeDegatRepository.findAll().size();

        // Delete the typeDegat
        restTypeDegatMockMvc.perform(delete("/api/type-degats/{id}", typeDegat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeDegat> typeDegatList = typeDegatRepository.findAll();
        assertThat(typeDegatList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeDegat.class);
        TypeDegat typeDegat1 = new TypeDegat();
        typeDegat1.setId(1L);
        TypeDegat typeDegat2 = new TypeDegat();
        typeDegat2.setId(typeDegat1.getId());
        assertThat(typeDegat1).isEqualTo(typeDegat2);
        typeDegat2.setId(2L);
        assertThat(typeDegat1).isNotEqualTo(typeDegat2);
        typeDegat1.setId(null);
        assertThat(typeDegat1).isNotEqualTo(typeDegat2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeDegatDTO.class);
        TypeDegatDTO typeDegatDTO1 = new TypeDegatDTO();
        typeDegatDTO1.setId(1L);
        TypeDegatDTO typeDegatDTO2 = new TypeDegatDTO();
        assertThat(typeDegatDTO1).isNotEqualTo(typeDegatDTO2);
        typeDegatDTO2.setId(typeDegatDTO1.getId());
        assertThat(typeDegatDTO1).isEqualTo(typeDegatDTO2);
        typeDegatDTO2.setId(2L);
        assertThat(typeDegatDTO1).isNotEqualTo(typeDegatDTO2);
        typeDegatDTO1.setId(null);
        assertThat(typeDegatDTO1).isNotEqualTo(typeDegatDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeDegatMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeDegatMapper.fromId(null)).isNull();
    }
}
