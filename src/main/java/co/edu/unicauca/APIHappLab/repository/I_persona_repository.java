package co.edu.unicauca.APIHappLab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.APIHappLab.model.persona;
@Repository
public interface I_persona_repository extends MongoRepository<persona,String>{
	
	@Query("{ 'email' : ?0 }")
	persona findByEmail(String Email);
}
