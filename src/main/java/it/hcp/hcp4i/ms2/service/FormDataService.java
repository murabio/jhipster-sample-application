package it.hcp.hcp4i.ms2.service;

import it.hcp.hcp4i.ms2.domain.FormData;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormData}.
 */
public interface FormDataService {

    /**
     * Save a formData.
     *
     * @param formData the entity to save.
     * @return the persisted entity.
     */
    FormData save(FormData formData);

    /**
     * Get all the formData.
     *
     * @return the list of entities.
     */
    List<FormData> findAll();

    /**
     * Get the "id" formData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormData> findOne(String id);

    /**
     * Delete the "id" formData.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
