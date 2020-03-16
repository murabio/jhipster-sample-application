package it.hcp.hcp4i.ms2.service.impl;

import it.hcp.hcp4i.ms2.service.FormDataService;
import it.hcp.hcp4i.ms2.domain.FormData;
import it.hcp.hcp4i.ms2.repository.FormDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FormData}.
 */
@Service
public class FormDataServiceImpl implements FormDataService {

    private final Logger log = LoggerFactory.getLogger(FormDataServiceImpl.class);

    private final FormDataRepository formDataRepository;

    public FormDataServiceImpl(FormDataRepository formDataRepository) {
        this.formDataRepository = formDataRepository;
    }

    /**
     * Save a formData.
     *
     * @param formData the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FormData save(FormData formData) {
        log.debug("Request to save FormData : {}", formData);
        return formDataRepository.save(formData);
    }

    /**
     * Get all the formData.
     *
     * @return the list of entities.
     */
    @Override
    public List<FormData> findAll() {
        log.debug("Request to get all FormData");
        return formDataRepository.findAll();
    }

    /**
     * Get one formData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<FormData> findOne(String id) {
        log.debug("Request to get FormData : {}", id);
        return formDataRepository.findById(id);
    }

    /**
     * Delete the formData by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete FormData : {}", id);
        formDataRepository.deleteById(id);
    }
}
