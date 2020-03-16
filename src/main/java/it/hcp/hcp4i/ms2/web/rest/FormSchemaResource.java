package it.hcp.hcp4i.ms2.web.rest;

import it.hcp.hcp4i.ms2.domain.FormSchema;
import it.hcp.hcp4i.ms2.service.FormSchemaService;
import it.hcp.hcp4i.ms2.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.hcp.hcp4i.ms2.domain.FormSchema}.
 */
@RestController
@RequestMapping("/api")
public class FormSchemaResource {

    private final Logger log = LoggerFactory.getLogger(FormSchemaResource.class);

    private static final String ENTITY_NAME = "formSchema";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormSchemaService formSchemaService;

    public FormSchemaResource(FormSchemaService formSchemaService) {
        this.formSchemaService = formSchemaService;
    }

    /**
     * {@code POST  /form-schemas} : Create a new formSchema.
     *
     * @param formSchema the formSchema to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formSchema, or with status {@code 400 (Bad Request)} if the formSchema has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-schemas")
    public ResponseEntity<FormSchema> createFormSchema(@RequestBody FormSchema formSchema) throws URISyntaxException {
        log.debug("REST request to save FormSchema : {}", formSchema);
        if (formSchema.getId() != null) {
            throw new BadRequestAlertException("A new formSchema cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormSchema result = formSchemaService.save(formSchema);
        return ResponseEntity.created(new URI("/api/form-schemas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /form-schemas} : Updates an existing formSchema.
     *
     * @param formSchema the formSchema to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formSchema,
     * or with status {@code 400 (Bad Request)} if the formSchema is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formSchema couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-schemas")
    public ResponseEntity<FormSchema> updateFormSchema(@RequestBody FormSchema formSchema) throws URISyntaxException {
        log.debug("REST request to update FormSchema : {}", formSchema);
        if (formSchema.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormSchema result = formSchemaService.save(formSchema);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formSchema.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /form-schemas} : get all the formSchemas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formSchemas in body.
     */
    @GetMapping("/form-schemas")
    public List<FormSchema> getAllFormSchemas() {
        log.debug("REST request to get all FormSchemas");
        return formSchemaService.findAll();
    }

    /**
     * {@code GET  /form-schemas/:id} : get the "id" formSchema.
     *
     * @param id the id of the formSchema to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formSchema, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-schemas/{id}")
    public ResponseEntity<FormSchema> getFormSchema(@PathVariable String id) {
        log.debug("REST request to get FormSchema : {}", id);
        Optional<FormSchema> formSchema = formSchemaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formSchema);
    }

    /**
     * {@code DELETE  /form-schemas/:id} : delete the "id" formSchema.
     *
     * @param id the id of the formSchema to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-schemas/{id}")
    public ResponseEntity<Void> deleteFormSchema(@PathVariable String id) {
        log.debug("REST request to delete FormSchema : {}", id);
        formSchemaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
