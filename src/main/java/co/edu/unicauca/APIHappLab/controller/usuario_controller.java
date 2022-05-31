package co.edu.unicauca.APIHappLab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
public class usuario_controller {
	@Autowired
	private persona_service service;
	
	@GetMapping("/")
	public List<persona> readAll(){
		return service.findAll();
	}
	@GetMapping("/{Email}")
	public persona findbyEmail(@PathVariable String Email){
		return service.findPersonaByEmail(Email);
	}
	@PostMapping("/registro")
	public persona create(@Validated @RequestBody persona body_persona) {
		return service.create(body_persona);
	}
	@PutMapping("/Update")
	public persona updatePersona(@Validated @RequestBody persona body_persona) {
		return service.update(body_persona);
	}
}
