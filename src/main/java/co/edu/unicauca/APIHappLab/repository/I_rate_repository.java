package co.edu.unicauca.APIHappLab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.APIHappLab.model.rate;
@Repository
public interface I_rate_repository extends MongoRepository<rate, Long>{}
