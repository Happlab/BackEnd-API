package co.edu.unicauca.APIHappLab.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.APIHappLab.model.seccion;
import co.edu.unicauca.APIHappLab.repository.I_seccion_repository;

@Service
public class seccion_service {
	@Autowired
	I_seccion_repository repo;

	public seccion create(seccion prm_seccion) {
		return repo.insert(prm_seccion);
	}
	public Optional<seccion> findById(Double prm_id) {
		return repo.findById(prm_id);
	}
	public List<seccion> findAll() {
		return repo.findAll();
	}
	public seccion update(seccion prm_seccion) {
		return repo.save(prm_seccion);
	}
	public boolean delete(Double prm_id_seccion) {
		try {
			repo.deleteById(prm_id_seccion);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
