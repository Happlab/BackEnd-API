package co.edu.unicauca.APIHappLab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import co.edu.unicauca.APIHappLab.model.seccion;

@Repository
public interface I_seccion_repository extends MongoRepository<seccion,Integer>{
}
