package co.edu.unicauca.APIHappLab.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.service.persona_service;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins= "http://localhost:8080")
public class usuario_controller {
	@Autowired
	private persona_service service;
	
	@GetMapping("/")
	public List<persona> readAll(){
		return service.findAll();
	}/*
	@GetMapping("/{Email}")
	public persona findbyEmail(@PathVariable String Email){
		return service.findPersonaByEmail(Email);
	}*/
	@PostMapping("/registro")
	public persona create(@Valid @RequestBody persona body_persona) {
		return service.create(body_persona);
	}
	@PutMapping("/Update")
	public persona updatePersona(@Valid @RequestBody persona body_persona) {
		return service.update(body_persona);
	}
	
	@GetMapping("/Login/{Email}&{Contraseña}")
	public persona login(@PathVariable String Email,@PathVariable String Contraseña) {
		return service.login(Email, Contraseña);
	}
}
