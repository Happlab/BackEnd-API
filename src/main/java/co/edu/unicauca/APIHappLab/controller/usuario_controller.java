package co.edu.unicauca.APIHappLab.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.APIHappLab.DTO.persona_dto;
import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.service.persona_service;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins= "http://localhost:3000")
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
	public persona_dto create(@Valid @RequestBody persona_dto dto,BindingResult b_result) {
		return service.create(dto.to_persona()).to_persona_dto();
	}
	@PutMapping("/update")
	public persona updatePersona(@Valid @RequestBody persona_dto dto, BindingResult bindingResult) {
		persona obj_persona = dto.to_persona();
		obj_persona.setId_usuario(service.findPersonaByEmail(dto.getEmail()).getId_usuario());
		return service.update(obj_persona);
	}
	@DeleteMapping("/desactivar/{Email}")
	public void desactivar(@PathVariable String Email) {
		persona obj_persona = service.findPersonaByEmail(Email);
		obj_persona.setActivo(false);
		service.update(obj_persona);
	}
	@DeleteMapping("/delete/{Email}")
	public void delete(@PathVariable String Email) {
		service.deleteByEmail(Email);
	}
	@GetMapping("/Login/{Email}&{Contraseña}")
	public persona login(@PathVariable String Email,@PathVariable String Contraseña) {
		return service.login(Email, Contraseña);
	}
}
