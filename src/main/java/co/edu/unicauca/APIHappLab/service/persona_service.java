package co.edu.unicauca.APIHappLab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.repository.I_persona_repository;

@Service
public class persona_service implements UserDetailsService{
	
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
		return repo.save(par_persona);
		
	}
	public List<persona> findAll(){
		return this.repo.findAll();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final persona customer = repo.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(customer.getEmail()).password(customer.getPassword()).authorities("USER").build();
        return user;
	}

	
}
