package co.edu.unicauca.APIHappLab.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.repository.I_persona_repository;

@Service
public class persona_service implements UserDetailsService{
	private Logger logger = LoggerFactory.getLogger(persona_service.class);
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
		try {
		 persona customer = repo.findByEmail(email);
		 if(customer==null) {return null;}
		 if (encoder.matches(password, customer.getPassword()) && (customer.isActivo()) && !(customer.isPendiente())) {
			return customer;
		 }
		 return null;
		}catch(Exception e){
			return null;
		}
	}
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		persona usuario = repo.findByEmail(email);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+email+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+email+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = usuario.getRol()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.name()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getEmail(), usuario.getPassword(), usuario.isActivo(), true, true, true, authorities);
	}
}
