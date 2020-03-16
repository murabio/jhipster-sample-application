package it.hcp.hcp4i.ms2.service;

import it.hcp.hcp4i.ms2.domain.FormSchema;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormSchema}.
 */
public interface FormSchemaService {

    /**
     * Save a formSchema.
     *
     * @param formSchema the entity to save.
     * @return the persisted entity.
     */
    FormSchema save(FormSchema formSchema);

    /**
     * Get all the formSchemas.
     *
     * @return the list of entities.
     */
    List<FormSchema> findAll();

    /**
     * Get the "id" formSchema.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormSchema> findOne(String id);

    /**
     * Delete the "id" formSchema.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
