package it.hcp.hcp4i.ms2.web.rest;

import it.hcp.hcp4i.ms2.domain.FormData;
import it.hcp.hcp4i.ms2.service.FormDataService;
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
 * REST controller for managing {@link it.hcp.hcp4i.ms2.domain.FormData}.
 */
@RestController
@RequestMapping("/api")
public class FormDataResource {

    private final Logger log = LoggerFactory.getLogger(FormDataResource.class);

    private static final String ENTITY_NAME = "formData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FormDataService formDataService;

    public FormDataResource(FormDataService formDataService) {
        this.formDataService = formDataService;
    }

    /**
     * {@code POST  /form-data} : Create a new formData.
     *
     * @param formData the formData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new formData, or with status {@code 400 (Bad Request)} if the formData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/form-data")
    public ResponseEntity<FormData> createFormData(@RequestBody FormData formData) throws URISyntaxException {
        log.debug("REST request to save FormData : {}", formData);
        if (formData.getId() != null) {
            throw new BadRequestAlertException("A new formData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FormData result = formDataService.save(formData);
        return ResponseEntity.created(new URI("/api/form-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /form-data} : Updates an existing formData.
     *
     * @param formData the formData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated formData,
     * or with status {@code 400 (Bad Request)} if the formData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the formData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/form-data")
    public ResponseEntity<FormData> updateFormData(@RequestBody FormData formData) throws URISyntaxException {
        log.debug("REST request to update FormData : {}", formData);
        if (formData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FormData result = formDataService.save(formData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, formData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /form-data} : get all the formData.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of formData in body.
     */
    @GetMapping("/form-data")
    public List<FormData> getAllFormData() {
        log.debug("REST request to get all FormData");
        return formDataService.findAll();
    }

    /**
     * {@code GET  /form-data/:id} : get the "id" formData.
     *
     * @param id the id of the formData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the formData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/form-data/{id}")
    public ResponseEntity<FormData> getFormData(@PathVariable String id) {
        log.debug("REST request to get FormData : {}", id);
        Optional<FormData> formData = formDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(formData);
    }

    /**
     * {@code DELETE  /form-data/:id} : delete the "id" formData.
     *
     * @param id the id of the formData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/form-data/{id}")
    public ResponseEntity<Void> deleteFormData(@PathVariable String id) {
        log.debug("REST request to delete FormData : {}", id);
        formDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
