package co.edu.unicauca.APIHappLab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.APIHappLab.model.noticia;

@Repository
public interface I_noticia_repository extends MongoRepository<noticia,String>{

}
