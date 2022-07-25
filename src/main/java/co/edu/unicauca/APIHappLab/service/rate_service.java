package co.edu.unicauca.APIHappLab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.APIHappLab.model.rate;
import co.edu.unicauca.APIHappLab.repository.I_rate_repository;


@Service
public class rate_service {
	@Autowired
	private I_rate_repository repo;
	
	public rate create(rate par_rate) {
		return repo.insert(par_rate);	
	}
	public List<rate> findAll() {
		return repo.findAll();
	}
	public Optional<rate> findbyId(String id_rate) {
		return repo.findById(id_rate);
	}
	public rate update(rate body_rate) {
		return repo.save(body_rate);
	}
}
