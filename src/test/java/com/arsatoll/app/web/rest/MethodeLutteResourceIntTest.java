package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.MethodeLutte;
import com.arsatoll.app.repository.MethodeLutteRepository;
import com.arsatoll.app.service.MethodeLutteService;
import com.arsatoll.app.service.dto.MethodeLutteDTO;
import com.arsatoll.app.service.mapper.MethodeLutteMapper;
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
 * Test class for the MethodeLutteResource REST controller.
 *
 * @see MethodeLutteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class MethodeLutteResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_METHODE_CULTURALE = "AAAAAAAAAA";
    private static final String UPDATED_METHODE_CULTURALE = "BBBBBBBBBB";

    private static final String DEFAULT_TRAITEMENT = "AAAAAAAAAA";
    private static final String UPDATED_TRAITEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_ML = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_ML = "BBBBBBBBBB";

    private static final String DEFAULT_VIDEO = "AAAAAAAAAA";
    private static final String UPDATED_VIDEO = "BBBBBBBBBB";

    @Autowired
    private MethodeLutteRepository methodeLutteRepository;

    @Autowired
    private MethodeLutteMapper methodeLutteMapper;

    @Autowired
    private MethodeLutteService methodeLutteService;

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

    private MockMvc restMethodeLutteMockMvc;

    private MethodeLutte methodeLutte;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MethodeLutteResource methodeLutteResource = new MethodeLutteResource(methodeLutteService);
        this.restMethodeLutteMockMvc = MockMvcBuilders.standaloneSetup(methodeLutteResource)
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
    public static MethodeLutte createEntity(EntityManager em) {
        MethodeLutte methodeLutte = new MethodeLutte()
            .type(DEFAULT_TYPE)
            .methodeCulturale(DEFAULT_METHODE_CULTURALE)
            .traitement(DEFAULT_TRAITEMENT)
            .imageML(DEFAULT_IMAGE_ML)
            .video(DEFAULT_VIDEO);
        return methodeLutte;
    }

    @Before
    public void initTest() {
        methodeLutte = createEntity(em);
    }

    @Test
    @Transactional
    public void createMethodeLutte() throws Exception {
        int databaseSizeBeforeCreate = methodeLutteRepository.findAll().size();

        // Create the MethodeLutte
        MethodeLutteDTO methodeLutteDTO = methodeLutteMapper.toDto(methodeLutte);
        restMethodeLutteMockMvc.perform(post("/api/methode-luttes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(methodeLutteDTO)))
            .andExpect(status().isCreated());

        // Validate the MethodeLutte in the database
        List<MethodeLutte> methodeLutteList = methodeLutteRepository.findAll();
        assertThat(methodeLutteList).hasSize(databaseSizeBeforeCreate + 1);
        MethodeLutte testMethodeLutte = methodeLutteList.get(methodeLutteList.size() - 1);
        assertThat(testMethodeLutte.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMethodeLutte.getMethodeCulturale()).isEqualTo(DEFAULT_METHODE_CULTURALE);
        assertThat(testMethodeLutte.getTraitement()).isEqualTo(DEFAULT_TRAITEMENT);
        assertThat(testMethodeLutte.getImageML()).isEqualTo(DEFAULT_IMAGE_ML);
        assertThat(testMethodeLutte.getVideo()).isEqualTo(DEFAULT_VIDEO);
    }

    @Test
    @Transactional
    public void createMethodeLutteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = methodeLutteRepository.findAll().size();

        // Create the MethodeLutte with an existing ID
        methodeLutte.setId(1L);
        MethodeLutteDTO methodeLutteDTO = methodeLutteMapper.toDto(methodeLutte);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMethodeLutteMockMvc.perform(post("/api/methode-luttes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(methodeLutteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MethodeLutte in the database
        List<MethodeLutte> methodeLutteList = methodeLutteRepository.findAll();
        assertThat(methodeLutteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMethodeLuttes() throws Exception {
        // Initialize the database
        methodeLutteRepository.saveAndFlush(methodeLutte);

        // Get all the methodeLutteList
        restMethodeLutteMockMvc.perform(get("/api/methode-luttes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(methodeLutte.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].methodeCulturale").value(hasItem(DEFAULT_METHODE_CULTURALE.toString())))
            .andExpect(jsonPath("$.[*].traitement").value(hasItem(DEFAULT_TRAITEMENT.toString())))
            .andExpect(jsonPath("$.[*].imageML").value(hasItem(DEFAULT_IMAGE_ML.toString())))
            .andExpect(jsonPath("$.[*].video").value(hasItem(DEFAULT_VIDEO.toString())));
    }
    
    @Test
    @Transactional
    public void getMethodeLutte() throws Exception {
        // Initialize the database
        methodeLutteRepository.saveAndFlush(methodeLutte);

        // Get the methodeLutte
        restMethodeLutteMockMvc.perform(get("/api/methode-luttes/{id}", methodeLutte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(methodeLutte.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.methodeCulturale").value(DEFAULT_METHODE_CULTURALE.toString()))
            .andExpect(jsonPath("$.traitement").value(DEFAULT_TRAITEMENT.toString()))
            .andExpect(jsonPath("$.imageML").value(DEFAULT_IMAGE_ML.toString()))
            .andExpect(jsonPath("$.video").value(DEFAULT_VIDEO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMethodeLutte() throws Exception {
        // Get the methodeLutte
        restMethodeLutteMockMvc.perform(get("/api/methode-luttes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMethodeLutte() throws Exception {
        // Initialize the database
        methodeLutteRepository.saveAndFlush(methodeLutte);

        int databaseSizeBeforeUpdate = methodeLutteRepository.findAll().size();

        // Update the methodeLutte
        MethodeLutte updatedMethodeLutte = methodeLutteRepository.findById(methodeLutte.getId()).get();
        // Disconnect from session so that the updates on updatedMethodeLutte are not directly saved in db
        em.detach(updatedMethodeLutte);
        updatedMethodeLutte
            .type(UPDATED_TYPE)
            .methodeCulturale(UPDATED_METHODE_CULTURALE)
            .traitement(UPDATED_TRAITEMENT)
            .imageML(UPDATED_IMAGE_ML)
            .video(UPDATED_VIDEO);
        MethodeLutteDTO methodeLutteDTO = methodeLutteMapper.toDto(updatedMethodeLutte);

        restMethodeLutteMockMvc.perform(put("/api/methode-luttes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(methodeLutteDTO)))
            .andExpect(status().isOk());

        // Validate the MethodeLutte in the database
        List<MethodeLutte> methodeLutteList = methodeLutteRepository.findAll();
        assertThat(methodeLutteList).hasSize(databaseSizeBeforeUpdate);
        MethodeLutte testMethodeLutte = methodeLutteList.get(methodeLutteList.size() - 1);
        assertThat(testMethodeLutte.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMethodeLutte.getMethodeCulturale()).isEqualTo(UPDATED_METHODE_CULTURALE);
        assertThat(testMethodeLutte.getTraitement()).isEqualTo(UPDATED_TRAITEMENT);
        assertThat(testMethodeLutte.getImageML()).isEqualTo(UPDATED_IMAGE_ML);
        assertThat(testMethodeLutte.getVideo()).isEqualTo(UPDATED_VIDEO);
    }

    @Test
    @Transactional
    public void updateNonExistingMethodeLutte() throws Exception {
        int databaseSizeBeforeUpdate = methodeLutteRepository.findAll().size();

        // Create the MethodeLutte
        MethodeLutteDTO methodeLutteDTO = methodeLutteMapper.toDto(methodeLutte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMethodeLutteMockMvc.perform(put("/api/methode-luttes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(methodeLutteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MethodeLutte in the database
        List<MethodeLutte> methodeLutteList = methodeLutteRepository.findAll();
        assertThat(methodeLutteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMethodeLutte() throws Exception {
        // Initialize the database
        methodeLutteRepository.saveAndFlush(methodeLutte);

        int databaseSizeBeforeDelete = methodeLutteRepository.findAll().size();

        // Delete the methodeLutte
        restMethodeLutteMockMvc.perform(delete("/api/methode-luttes/{id}", methodeLutte.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MethodeLutte> methodeLutteList = methodeLutteRepository.findAll();
        assertThat(methodeLutteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MethodeLutte.class);
        MethodeLutte methodeLutte1 = new MethodeLutte();
        methodeLutte1.setId(1L);
        MethodeLutte methodeLutte2 = new MethodeLutte();
        methodeLutte2.setId(methodeLutte1.getId());
        assertThat(methodeLutte1).isEqualTo(methodeLutte2);
        methodeLutte2.setId(2L);
        assertThat(methodeLutte1).isNotEqualTo(methodeLutte2);
        methodeLutte1.setId(null);
        assertThat(methodeLutte1).isNotEqualTo(methodeLutte2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MethodeLutteDTO.class);
        MethodeLutteDTO methodeLutteDTO1 = new MethodeLutteDTO();
        methodeLutteDTO1.setId(1L);
        MethodeLutteDTO methodeLutteDTO2 = new MethodeLutteDTO();
        assertThat(methodeLutteDTO1).isNotEqualTo(methodeLutteDTO2);
        methodeLutteDTO2.setId(methodeLutteDTO1.getId());
        assertThat(methodeLutteDTO1).isEqualTo(methodeLutteDTO2);
        methodeLutteDTO2.setId(2L);
        assertThat(methodeLutteDTO1).isNotEqualTo(methodeLutteDTO2);
        methodeLutteDTO1.setId(null);
        assertThat(methodeLutteDTO1).isNotEqualTo(methodeLutteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(methodeLutteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(methodeLutteMapper.fromId(null)).isNull();
    }
}
