package it.hcp.hcp4i.ms2.repository;

import it.hcp.hcp4i.ms2.domain.Authority;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {}
