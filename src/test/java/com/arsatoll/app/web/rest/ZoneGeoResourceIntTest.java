package com.arsatoll.app.web.rest;

import com.arsatoll.app.ArsatollserviceApp;

import com.arsatoll.app.domain.ZoneGeo;
import com.arsatoll.app.repository.ZoneGeoRepository;
import com.arsatoll.app.service.ZoneGeoService;
import com.arsatoll.app.service.dto.ZoneGeoDTO;
import com.arsatoll.app.service.mapper.ZoneGeoMapper;
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
 * Test class for the ZoneGeoResource REST controller.
 *
 * @see ZoneGeoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArsatollserviceApp.class)
public class ZoneGeoResourceIntTest {

    private static final String DEFAULT_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_PAYS = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    @Autowired
    private ZoneGeoRepository zoneGeoRepository;

    @Autowired
    private ZoneGeoMapper zoneGeoMapper;

    @Autowired
    private ZoneGeoService zoneGeoService;

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

    private MockMvc restZoneGeoMockMvc;

    private ZoneGeo zoneGeo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ZoneGeoResource zoneGeoResource = new ZoneGeoResource(zoneGeoService);
        this.restZoneGeoMockMvc = MockMvcBuilders.standaloneSetup(zoneGeoResource)
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
    public static ZoneGeo createEntity(EntityManager em) {
        ZoneGeo zoneGeo = new ZoneGeo()
            .pays(DEFAULT_PAYS)
            .region(DEFAULT_REGION);
        return zoneGeo;
    }

    @Before
    public void initTest() {
        zoneGeo = createEntity(em);
    }

    @Test
    @Transactional
    public void createZoneGeo() throws Exception {
        int databaseSizeBeforeCreate = zoneGeoRepository.findAll().size();

        // Create the ZoneGeo
        ZoneGeoDTO zoneGeoDTO = zoneGeoMapper.toDto(zoneGeo);
        restZoneGeoMockMvc.perform(post("/api/zone-geos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zoneGeoDTO)))
            .andExpect(status().isCreated());

        // Validate the ZoneGeo in the database
        List<ZoneGeo> zoneGeoList = zoneGeoRepository.findAll();
        assertThat(zoneGeoList).hasSize(databaseSizeBeforeCreate + 1);
        ZoneGeo testZoneGeo = zoneGeoList.get(zoneGeoList.size() - 1);
        assertThat(testZoneGeo.getPays()).isEqualTo(DEFAULT_PAYS);
        assertThat(testZoneGeo.getRegion()).isEqualTo(DEFAULT_REGION);
    }

    @Test
    @Transactional
    public void createZoneGeoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = zoneGeoRepository.findAll().size();

        // Create the ZoneGeo with an existing ID
        zoneGeo.setId(1L);
        ZoneGeoDTO zoneGeoDTO = zoneGeoMapper.toDto(zoneGeo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restZoneGeoMockMvc.perform(post("/api/zone-geos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zoneGeoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ZoneGeo in the database
        List<ZoneGeo> zoneGeoList = zoneGeoRepository.findAll();
        assertThat(zoneGeoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllZoneGeos() throws Exception {
        // Initialize the database
        zoneGeoRepository.saveAndFlush(zoneGeo);

        // Get all the zoneGeoList
        restZoneGeoMockMvc.perform(get("/api/zone-geos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zoneGeo.getId().intValue())))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS.toString())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION.toString())));
    }
    
    @Test
    @Transactional
    public void getZoneGeo() throws Exception {
        // Initialize the database
        zoneGeoRepository.saveAndFlush(zoneGeo);

        // Get the zoneGeo
        restZoneGeoMockMvc.perform(get("/api/zone-geos/{id}", zoneGeo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(zoneGeo.getId().intValue()))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS.toString()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingZoneGeo() throws Exception {
        // Get the zoneGeo
        restZoneGeoMockMvc.perform(get("/api/zone-geos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateZoneGeo() throws Exception {
        // Initialize the database
        zoneGeoRepository.saveAndFlush(zoneGeo);

        int databaseSizeBeforeUpdate = zoneGeoRepository.findAll().size();

        // Update the zoneGeo
        ZoneGeo updatedZoneGeo = zoneGeoRepository.findById(zoneGeo.getId()).get();
        // Disconnect from session so that the updates on updatedZoneGeo are not directly saved in db
        em.detach(updatedZoneGeo);
        updatedZoneGeo
            .pays(UPDATED_PAYS)
            .region(UPDATED_REGION);
        ZoneGeoDTO zoneGeoDTO = zoneGeoMapper.toDto(updatedZoneGeo);

        restZoneGeoMockMvc.perform(put("/api/zone-geos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zoneGeoDTO)))
            .andExpect(status().isOk());

        // Validate the ZoneGeo in the database
        List<ZoneGeo> zoneGeoList = zoneGeoRepository.findAll();
        assertThat(zoneGeoList).hasSize(databaseSizeBeforeUpdate);
        ZoneGeo testZoneGeo = zoneGeoList.get(zoneGeoList.size() - 1);
        assertThat(testZoneGeo.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testZoneGeo.getRegion()).isEqualTo(UPDATED_REGION);
    }

    @Test
    @Transactional
    public void updateNonExistingZoneGeo() throws Exception {
        int databaseSizeBeforeUpdate = zoneGeoRepository.findAll().size();

        // Create the ZoneGeo
        ZoneGeoDTO zoneGeoDTO = zoneGeoMapper.toDto(zoneGeo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZoneGeoMockMvc.perform(put("/api/zone-geos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(zoneGeoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ZoneGeo in the database
        List<ZoneGeo> zoneGeoList = zoneGeoRepository.findAll();
        assertThat(zoneGeoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteZoneGeo() throws Exception {
        // Initialize the database
        zoneGeoRepository.saveAndFlush(zoneGeo);

        int databaseSizeBeforeDelete = zoneGeoRepository.findAll().size();

        // Delete the zoneGeo
        restZoneGeoMockMvc.perform(delete("/api/zone-geos/{id}", zoneGeo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ZoneGeo> zoneGeoList = zoneGeoRepository.findAll();
        assertThat(zoneGeoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZoneGeo.class);
        ZoneGeo zoneGeo1 = new ZoneGeo();
        zoneGeo1.setId(1L);
        ZoneGeo zoneGeo2 = new ZoneGeo();
        zoneGeo2.setId(zoneGeo1.getId());
        assertThat(zoneGeo1).isEqualTo(zoneGeo2);
        zoneGeo2.setId(2L);
        assertThat(zoneGeo1).isNotEqualTo(zoneGeo2);
        zoneGeo1.setId(null);
        assertThat(zoneGeo1).isNotEqualTo(zoneGeo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ZoneGeoDTO.class);
        ZoneGeoDTO zoneGeoDTO1 = new ZoneGeoDTO();
        zoneGeoDTO1.setId(1L);
        ZoneGeoDTO zoneGeoDTO2 = new ZoneGeoDTO();
        assertThat(zoneGeoDTO1).isNotEqualTo(zoneGeoDTO2);
        zoneGeoDTO2.setId(zoneGeoDTO1.getId());
        assertThat(zoneGeoDTO1).isEqualTo(zoneGeoDTO2);
        zoneGeoDTO2.setId(2L);
        assertThat(zoneGeoDTO1).isNotEqualTo(zoneGeoDTO2);
        zoneGeoDTO1.setId(null);
        assertThat(zoneGeoDTO1).isNotEqualTo(zoneGeoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(zoneGeoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(zoneGeoMapper.fromId(null)).isNull();
    }
}
