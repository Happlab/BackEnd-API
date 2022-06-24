package co.edu.unicauca.APIHappLab.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.APIHappLab.model.persona;
@Repository
public interface I_persona_repository extends MongoRepository<persona,ObjectId>{
	persona findByEmail(String username);
}
