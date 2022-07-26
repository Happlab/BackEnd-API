package co.edu.unicauca.APIHappLab.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.APIHappLab.model.noticia;

@Repository
public interface I_noticia_repository extends MongoRepository<noticia,String>{
	
	@Query("{ 'link_contenido' : ?0 }")
	Optional<noticia> findByLink_contenido(String link_contenido);
}
