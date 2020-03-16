package it.hcp.hcp4i.ms2.web.rest;

import it.hcp.hcp4i.ms2.JhipsterSampleApplicationApp;
import it.hcp.hcp4i.ms2.config.TestSecurityConfiguration;
import it.hcp.hcp4i.ms2.domain.FormSchema;
import it.hcp.hcp4i.ms2.repository.FormSchemaRepository;
import it.hcp.hcp4i.ms2.service.FormSchemaService;

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
 * Integration tests for the {@link FormSchemaResource} REST controller.
 */
@SpringBootTest(classes = { JhipsterSampleApplicationApp.class, TestSecurityConfiguration.class })

@AutoConfigureMockMvc
@WithMockUser
public class FormSchemaResourceIT {

    private static final String DEFAULT_DEFINITION = "AAAAAAAAAA";
    private static final String UPDATED_DEFINITION = "BBBBBBBBBB";

    @Autowired
    private FormSchemaRepository formSchemaRepository;

    @Autowired
    private FormSchemaService formSchemaService;

    @Autowired
    private MockMvc restFormSchemaMockMvc;

    private FormSchema formSchema;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormSchema createEntity() {
        FormSchema formSchema = new FormSchema()
            .definition(DEFAULT_DEFINITION);
        return formSchema;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormSchema createUpdatedEntity() {
        FormSchema formSchema = new FormSchema()
            .definition(UPDATED_DEFINITION);
        return formSchema;
    }

    @BeforeEach
    public void initTest() {
        formSchemaRepository.deleteAll();
        formSchema = createEntity();
    }

    @Test
    public void createFormSchema() throws Exception {
        int databaseSizeBeforeCreate = formSchemaRepository.findAll().size();

        // Create the FormSchema
        restFormSchemaMockMvc.perform(post("/api/form-schemas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formSchema)))
            .andExpect(status().isCreated());

        // Validate the FormSchema in the database
        List<FormSchema> formSchemaList = formSchemaRepository.findAll();
        assertThat(formSchemaList).hasSize(databaseSizeBeforeCreate + 1);
        FormSchema testFormSchema = formSchemaList.get(formSchemaList.size() - 1);
        assertThat(testFormSchema.getDefinition()).isEqualTo(DEFAULT_DEFINITION);
    }

    @Test
    public void createFormSchemaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formSchemaRepository.findAll().size();

        // Create the FormSchema with an existing ID
        formSchema.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormSchemaMockMvc.perform(post("/api/form-schemas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formSchema)))
            .andExpect(status().isBadRequest());

        // Validate the FormSchema in the database
        List<FormSchema> formSchemaList = formSchemaRepository.findAll();
        assertThat(formSchemaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllFormSchemas() throws Exception {
        // Initialize the database
        formSchemaRepository.save(formSchema);

        // Get all the formSchemaList
        restFormSchemaMockMvc.perform(get("/api/form-schemas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formSchema.getId())))
            .andExpect(jsonPath("$.[*].definition").value(hasItem(DEFAULT_DEFINITION)));
    }
    
    @Test
    public void getFormSchema() throws Exception {
        // Initialize the database
        formSchemaRepository.save(formSchema);

        // Get the formSchema
        restFormSchemaMockMvc.perform(get("/api/form-schemas/{id}", formSchema.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(formSchema.getId()))
            .andExpect(jsonPath("$.definition").value(DEFAULT_DEFINITION));
    }

    @Test
    public void getNonExistingFormSchema() throws Exception {
        // Get the formSchema
        restFormSchemaMockMvc.perform(get("/api/form-schemas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateFormSchema() throws Exception {
        // Initialize the database
        formSchemaService.save(formSchema);

        int databaseSizeBeforeUpdate = formSchemaRepository.findAll().size();

        // Update the formSchema
        FormSchema updatedFormSchema = formSchemaRepository.findById(formSchema.getId()).get();
        updatedFormSchema
            .definition(UPDATED_DEFINITION);

        restFormSchemaMockMvc.perform(put("/api/form-schemas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormSchema)))
            .andExpect(status().isOk());

        // Validate the FormSchema in the database
        List<FormSchema> formSchemaList = formSchemaRepository.findAll();
        assertThat(formSchemaList).hasSize(databaseSizeBeforeUpdate);
        FormSchema testFormSchema = formSchemaList.get(formSchemaList.size() - 1);
        assertThat(testFormSchema.getDefinition()).isEqualTo(UPDATED_DEFINITION);
    }

    @Test
    public void updateNonExistingFormSchema() throws Exception {
        int databaseSizeBeforeUpdate = formSchemaRepository.findAll().size();

        // Create the FormSchema

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormSchemaMockMvc.perform(put("/api/form-schemas").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(formSchema)))
            .andExpect(status().isBadRequest());

        // Validate the FormSchema in the database
        List<FormSchema> formSchemaList = formSchemaRepository.findAll();
        assertThat(formSchemaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteFormSchema() throws Exception {
        // Initialize the database
        formSchemaService.save(formSchema);

        int databaseSizeBeforeDelete = formSchemaRepository.findAll().size();

        // Delete the formSchema
        restFormSchemaMockMvc.perform(delete("/api/form-schemas/{id}", formSchema.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormSchema> formSchemaList = formSchemaRepository.findAll();
        assertThat(formSchemaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
