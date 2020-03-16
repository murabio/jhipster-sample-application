package it.hcp.hcp4i.ms2.repository;

import it.hcp.hcp4i.ms2.domain.FormData;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the FormData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormDataRepository extends MongoRepository<FormData, String> {
}