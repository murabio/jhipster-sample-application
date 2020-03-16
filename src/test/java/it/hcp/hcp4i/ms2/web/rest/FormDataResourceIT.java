package it.hcp.hcp4i.ms2.web.rest;

import it.hcp.hcp4i.ms2.JhipsterSampleApplicationApp;
import it.hcp.hcp4i.ms2.config.TestSecurityConfiguration;
import it.hcp.hcp4i.ms2.domain.FormData;
import it.hcp.hcp4i.ms2.repository.FormDataRepository;
import it.hcp.hcp4i.ms2.service.FormDataService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FormDataResource} REST controller.
 */
@SpringBootTest(classes = { JhipsterSampleApplicationApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class FormDataResourceIT {

    private static final String DEFAULT_PAYLOAD = "AAAAAAAAAA";
    private static final String UPDATED_PAYLOAD = "BBBBBBBBBB";

    @Autowired
    private FormDataRepository formDataRepository;

    @Autowired
    private FormDataService formDataService;

    @Autowired
    private MockMvc restFormDataMockMvc;

    private FormData formData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormData createEntity() {
        FormData formData = new FormData()
            .payload(DEFAULT_PAYLOAD);
        return formData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormData createUpdatedEntity() {
        FormData formData = new FormData()
            .payload(UPDATED_PAYLOAD);
        return formData;
    }

    @BeforeEach
    public void initTest() {
        formDataRepository.deleteAll();
        formData = createEntity();
    }

    @Test
    public void createFormData() throws Exception {
        int databaseSizeBeforeCreate = formDataRepository.findAll().size();

        // Create the FormData
        restFormDataMockMvc.perform(post("/api/form-data").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formData)))
            .andExpect(status().isCreated());

        // Validate the FormData in the database
        List<FormData> formDataList = formDataRepository.findAll();
        assertThat(formDataList).hasSize(databaseSizeBeforeCreate + 1);
        FormData testFormData = formDataList.get(formDataList.size() - 1);
        assertThat(testFormData.getPayload()).isEqualTo(DEFAULT_PAYLOAD);
    }

    @Test
    public void createFormDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formDataRepository.findAll().size();

        // Create the FormData with an existing ID
        formData.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormDataMockMvc.perform(post("/api/form-data").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formData)))
            .andExpect(status().isBadRequest());

        // Validate the FormData in the database
        List<FormData> formDataList = formDataRepository.findAll();
        assertThat(formDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllFormData() throws Exception {
        // Initialize the database
        formDataRepository.save(formData);

        // Get all the formDataList
        restFormDataMockMvc.perform(get("/api/form-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formData.getId())))
            .andExpect(jsonPath("$.[*].payload").value(hasItem(DEFAULT_PAYLOAD)));
    }
    
    @Test
    public void getFormData() throws Exception {
        // Initialize the database
        formDataRepository.save(formData);

        // Get the formData
        restFormDataMockMvc.perform(get("/api/form-data/{id}", formData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formData.getId()))
            .andExpect(jsonPath("$.payload").value(DEFAULT_PAYLOAD));
    }

    @Test
    public void getNonExistingFormData() throws Exception {
        // Get the formData
        restFormDataMockMvc.perform(get("/api/form-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFormData() throws Exception {
        // Initialize the database
        formDataService.save(formData);

        int databaseSizeBeforeUpdate = formDataRepository.findAll().size();

        // Update the formData
        FormData updatedFormData = formDataRepository.findById(formData.getId()).get();
        updatedFormData
            .payload(UPDATED_PAYLOAD);

        restFormDataMockMvc.perform(put("/api/form-data").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormData)))
            .andExpect(status().isOk());

        // Validate the FormData in the database
        List<FormData> formDataList = formDataRepository.findAll();
        assertThat(formDataList).hasSize(databaseSizeBeforeUpdate);
        FormData testFormData = formDataList.get(formDataList.size() - 1);
        assertThat(testFormData.getPayload()).isEqualTo(UPDATED_PAYLOAD);
    }

    @Test
    public void updateNonExistingFormData() throws Exception {
        int databaseSizeBeforeUpdate = formDataRepository.findAll().size();

        // Create the FormData

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormDataMockMvc.perform(put("/api/form-data").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formData)))
            .andExpect(status().isBadRequest());

        // Validate the FormData in the database
        List<FormData> formDataList = formDataRepository.findAll();
        assertThat(formDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteFormData() throws Exception {
        // Initialize the database
        formDataService.save(formData);

        int databaseSizeBeforeDelete = formDataRepository.findAll().size();

        // Delete the formData
        restFormDataMockMvc.perform(delete("/api/form-data/{id}", formData.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormData> formDataList = formDataRepository.findAll();
        assertThat(formDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
