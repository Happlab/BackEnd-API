package co.edu.unicauca.APIHappLab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.APIHappLab.model.contenido;
import co.edu.unicauca.APIHappLab.repository.I_contenido_repository;

@Service
public class contenido_service {
	@Autowired
	private I_contenido_repository repo;
	
	public contenido_service() {	}
	
	public contenido create(contenido par_contenido) {
		return repo.insert(par_contenido);
	}
	public List<contenido> findAll() {
		return repo.findAll();
	}
	public Optional<contenido> findbyId(String contenido_id) {
		return repo.findById(contenido_id);
	}

	public contenido update(contenido body_contenido) {
		return repo.save(body_contenido);
	}
}
