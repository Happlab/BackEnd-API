package co.edu.unicauca.APIHappLab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.repository.I_persona_repository;

@Service
public class persona_service{
	
	@Autowired
	private I_persona_repository repo;
	private BCryptPasswordEncoder encoder;
	
	public persona_service() {
		super();
		this.encoder = new BCryptPasswordEncoder();
	}
	public Optional<persona> findById(String id_persona){
		return repo.findById(id_persona);
	}
	public persona create(persona par_persona) {
		par_persona.setPassword(encoder.encode(par_persona.getPassword()));
		return repo.insert(par_persona);
	}

	public persona findPersonaByEmail(String Email) {
		return repo.findByEmail(Email);
	}
	public persona update(persona par_persona) {
		final persona customer = repo.findByEmail(par_persona.getEmail());
		if (encoder.matches(par_persona.getPassword(), customer.getPassword())) {
			par_persona.setPassword(customer.getPassword());
		} else {
			par_persona.setPassword(encoder.encode(par_persona.getPassword()));
		}
		return repo.save(par_persona);
		
	}
	public List<persona> findAll(){
		return this.repo.findAll();
	}
	public boolean deleteByEmail(String email) {
		try {
			persona usr = repo.findByEmail(email);
			if(usr==null) return false;
			repo.deleteById(usr.getId_usuario());
			return true;
		}catch (Exception ex) {
			return false;
		}

	}
	public persona login(String email,String password) {
		persona customer=null;
		try {
		 customer = repo.findByEmail(email);
		}catch(Exception e){
			return customer;
		}
		if (encoder.matches(password, customer.getPassword()) && (customer.isActivo())) {
			return customer;
		}
		return customer;
	}
}
