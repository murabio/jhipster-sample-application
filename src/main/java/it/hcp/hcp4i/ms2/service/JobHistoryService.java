package it.hcp.hcp4i.ms2.service;

import it.hcp.hcp4i.ms2.domain.JobHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link JobHistory}.
 */
public interface JobHistoryService {

    /**
     * Save a jobHistory.
     *
     * @param jobHistory the entity to save.
     * @return the persisted entity.
     */
    JobHistory save(JobHistory jobHistory);

    /**
     * Get all the jobHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JobHistory> findAll(Pageable pageable);

    /**
     * Get the "id" jobHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<JobHistory> findOne(String id);

    /**
     * Delete the "id" jobHistory.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
