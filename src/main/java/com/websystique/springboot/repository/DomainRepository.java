package com.websystique.springboot.repository;

import com.websystique.springboot.model.Domain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DomainRepository extends MongoRepository<Domain, Long> {

}
