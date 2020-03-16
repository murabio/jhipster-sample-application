package it.hcp.hcp4i.ms2.service.impl;

import it.hcp.hcp4i.ms2.service.FormSchemaService;
import it.hcp.hcp4i.ms2.domain.FormSchema;
import it.hcp.hcp4i.ms2.repository.FormSchemaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FormSchema}.
 */
@Service
public class FormSchemaServiceImpl implements FormSchemaService {

    private final Logger log = LoggerFactory.getLogger(FormSchemaServiceImpl.class);

    private final FormSchemaRepository formSchemaRepository;

    public FormSchemaServiceImpl(FormSchemaRepository formSchemaRepository) {
        this.formSchemaRepository = formSchemaRepository;
    }

    /**
     * Save a formSchema.
     *
     * @param formSchema the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FormSchema save(FormSchema formSchema) {
        log.debug("Request to save FormSchema : {}", formSchema);
        return formSchemaRepository.save(formSchema);
    }

    /**
     * Get all the formSchemas.
     *
     * @return the list of entities.
     */
    @Override
    public List<FormSchema> findAll() {
        log.debug("Request to get all FormSchemas");
        return formSchemaRepository.findAll();
    }

    /**
     * Get one formSchema by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<FormSchema> findOne(String id) {
        log.debug("Request to get FormSchema : {}", id);
        return formSchemaRepository.findById(id);
    }

    /**
     * Delete the formSchema by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete FormSchema : {}", id);
        formSchemaRepository.deleteById(id);
    }
}
