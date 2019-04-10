package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.TypeInsecte;
import com.arsatoll.app.repository.TypeInsecteRepository;
import com.arsatoll.app.service.TypeInsecteService;
import com.arsatoll.app.service.dto.TypeInsecteDTO;
import com.arsatoll.app.service.mapper.TypeInsecteMapper;
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
 * Test class for the TypeInsecteResource REST controller.
 *
 * @see TypeInsecteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class TypeInsecteResourceIntTest {

    private static final String DEFAULT_NOM_TYPE_INSECTE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TYPE_INSECTE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private TypeInsecteRepository typeInsecteRepository;

    @Autowired
    private TypeInsecteMapper typeInsecteMapper;

    @Autowired
    private TypeInsecteService typeInsecteService;

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

    private MockMvc restTypeInsecteMockMvc;

    private TypeInsecte typeInsecte;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeInsecteResource typeInsecteResource = new TypeInsecteResource(typeInsecteService);
        this.restTypeInsecteMockMvc = MockMvcBuilders.standaloneSetup(typeInsecteResource)
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
    public static TypeInsecte createEntity(EntityManager em) {
        TypeInsecte typeInsecte = new TypeInsecte()
            .nomTypeInsecte(DEFAULT_NOM_TYPE_INSECTE)
            .description(DEFAULT_DESCRIPTION);
        return typeInsecte;
    }

    @Before
    public void initTest() {
        typeInsecte = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeInsecte() throws Exception {
        int databaseSizeBeforeCreate = typeInsecteRepository.findAll().size();

        // Create the TypeInsecte
        TypeInsecteDTO typeInsecteDTO = typeInsecteMapper.toDto(typeInsecte);
        restTypeInsecteMockMvc.perform(post("/api/type-insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeInsecteDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeInsecte in the database
        List<TypeInsecte> typeInsecteList = typeInsecteRepository.findAll();
        assertThat(typeInsecteList).hasSize(databaseSizeBeforeCreate + 1);
        TypeInsecte testTypeInsecte = typeInsecteList.get(typeInsecteList.size() - 1);
        assertThat(testTypeInsecte.getNomTypeInsecte()).isEqualTo(DEFAULT_NOM_TYPE_INSECTE);
        assertThat(testTypeInsecte.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createTypeInsecteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeInsecteRepository.findAll().size();

        // Create the TypeInsecte with an existing ID
        typeInsecte.setId(1L);
        TypeInsecteDTO typeInsecteDTO = typeInsecteMapper.toDto(typeInsecte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeInsecteMockMvc.perform(post("/api/type-insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeInsecteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeInsecte in the database
        List<TypeInsecte> typeInsecteList = typeInsecteRepository.findAll();
        assertThat(typeInsecteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTypeInsectes() throws Exception {
        // Initialize the database
        typeInsecteRepository.saveAndFlush(typeInsecte);

        // Get all the typeInsecteList
        restTypeInsecteMockMvc.perform(get("/api/type-insectes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeInsecte.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomTypeInsecte").value(hasItem(DEFAULT_NOM_TYPE_INSECTE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeInsecte() throws Exception {
        // Initialize the database
        typeInsecteRepository.saveAndFlush(typeInsecte);

        // Get the typeInsecte
        restTypeInsecteMockMvc.perform(get("/api/type-insectes/{id}", typeInsecte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeInsecte.getId().intValue()))
            .andExpect(jsonPath("$.nomTypeInsecte").value(DEFAULT_NOM_TYPE_INSECTE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeInsecte() throws Exception {
        // Get the typeInsecte
        restTypeInsecteMockMvc.perform(get("/api/type-insectes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeInsecte() throws Exception {
        // Initialize the database
        typeInsecteRepository.saveAndFlush(typeInsecte);

        int databaseSizeBeforeUpdate = typeInsecteRepository.findAll().size();

        // Update the typeInsecte
        TypeInsecte updatedTypeInsecte = typeInsecteRepository.findById(typeInsecte.getId()).get();
        // Disconnect from session so that the updates on updatedTypeInsecte are not directly saved in db
        em.detach(updatedTypeInsecte);
        updatedTypeInsecte
            .nomTypeInsecte(UPDATED_NOM_TYPE_INSECTE)
            .description(UPDATED_DESCRIPTION);
        TypeInsecteDTO typeInsecteDTO = typeInsecteMapper.toDto(updatedTypeInsecte);

        restTypeInsecteMockMvc.perform(put("/api/type-insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeInsecteDTO)))
            .andExpect(status().isOk());

        // Validate the TypeInsecte in the database
        List<TypeInsecte> typeInsecteList = typeInsecteRepository.findAll();
        assertThat(typeInsecteList).hasSize(databaseSizeBeforeUpdate);
        TypeInsecte testTypeInsecte = typeInsecteList.get(typeInsecteList.size() - 1);
        assertThat(testTypeInsecte.getNomTypeInsecte()).isEqualTo(UPDATED_NOM_TYPE_INSECTE);
        assertThat(testTypeInsecte.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeInsecte() throws Exception {
        int databaseSizeBeforeUpdate = typeInsecteRepository.findAll().size();

        // Create the TypeInsecte
        TypeInsecteDTO typeInsecteDTO = typeInsecteMapper.toDto(typeInsecte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeInsecteMockMvc.perform(put("/api/type-insectes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeInsecteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeInsecte in the database
        List<TypeInsecte> typeInsecteList = typeInsecteRepository.findAll();
        assertThat(typeInsecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeInsecte() throws Exception {
        // Initialize the database
        typeInsecteRepository.saveAndFlush(typeInsecte);

        int databaseSizeBeforeDelete = typeInsecteRepository.findAll().size();

        // Delete the typeInsecte
        restTypeInsecteMockMvc.perform(delete("/api/type-insectes/{id}", typeInsecte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeInsecte> typeInsecteList = typeInsecteRepository.findAll();
        assertThat(typeInsecteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeInsecte.class);
        TypeInsecte typeInsecte1 = new TypeInsecte();
        typeInsecte1.setId(1L);
        TypeInsecte typeInsecte2 = new TypeInsecte();
        typeInsecte2.setId(typeInsecte1.getId());
        assertThat(typeInsecte1).isEqualTo(typeInsecte2);
        typeInsecte2.setId(2L);
        assertThat(typeInsecte1).isNotEqualTo(typeInsecte2);
        typeInsecte1.setId(null);
        assertThat(typeInsecte1).isNotEqualTo(typeInsecte2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeInsecteDTO.class);
        TypeInsecteDTO typeInsecteDTO1 = new TypeInsecteDTO();
        typeInsecteDTO1.setId(1L);
        TypeInsecteDTO typeInsecteDTO2 = new TypeInsecteDTO();
        assertThat(typeInsecteDTO1).isNotEqualTo(typeInsecteDTO2);
        typeInsecteDTO2.setId(typeInsecteDTO1.getId());
        assertThat(typeInsecteDTO1).isEqualTo(typeInsecteDTO2);
        typeInsecteDTO2.setId(2L);
        assertThat(typeInsecteDTO1).isNotEqualTo(typeInsecteDTO2);
        typeInsecteDTO1.setId(null);
        assertThat(typeInsecteDTO1).isNotEqualTo(typeInsecteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(typeInsecteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(typeInsecteMapper.fromId(null)).isNull();
    }
}
