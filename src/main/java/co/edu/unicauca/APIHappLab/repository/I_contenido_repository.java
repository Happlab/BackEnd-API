package co.edu.unicauca.APIHappLab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.APIHappLab.model.contenido;
@Repository
public interface I_contenido_repository extends MongoRepository<contenido,String>{
	@Query("{ 'link' : ?0 }")
	contenido findByContenido_link(String contenido_link);
}
