package co.edu.unicauca.APIHappLab.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<persona>> readAll(){
		return ResponseEntity.ok(service.findAll());
	}
	@GetMapping("/{Email}")
	public ResponseEntity<persona> findbyEmail(@PathVariable String Email){
		persona obj_persona = service.findPersonaByEmail(Email);
		if (obj_persona==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(obj_persona);
	}
	@PostMapping("/registro")
	public ResponseEntity<persona_dto> create(@Valid @RequestBody persona_dto dto,BindingResult b_result) {
		persona_dto rta;
		dto.setPendiente(true);
		try {
			rta = service.create(dto.to_persona()).to_persona_dto();
		}catch (Exception ex) {
			if (b_result.hasErrors()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(rta);
	}
	@PutMapping("/update")
	public persona_dto updatePersona(@Valid @RequestBody persona_dto dto, BindingResult bindingResult) {
		persona obj_persona = dto.to_persona();
		obj_persona.setId_usuario(service.findPersonaByEmail(dto.getEmail()).getId_usuario());
		return service.update(obj_persona).to_persona_dto();
	}
	@DeleteMapping("/desactivar/{Email}")
	public ResponseEntity<String> desactivar(@PathVariable String Email) {
		persona obj_persona = service.findPersonaByEmail(Email);
		if (obj_persona==null) return ResponseEntity.notFound().build();
		obj_persona.setActivo(false);
		try {
		service.update(obj_persona);
		return ResponseEntity.ok("message: se desactivo con exito ");
		}catch (Exception e){
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	@DeleteMapping("/delete/{Email}")
	public ResponseEntity<String> delete(@PathVariable String Email) {
		if(service.deleteByEmail(Email)) {
			return ResponseEntity.ok("message: Borrado con exito");
		}
		return ResponseEntity.internalServerError().body("message: usuario no encontrado");
	}
	@GetMapping("/Login/{Email}&{Contraseña}")
	public ResponseEntity<persona> login(@PathVariable String Email,@PathVariable String Contraseña) {
		persona obj_persona = service.login(Email, Contraseña);
		if(obj_persona==null) return ResponseEntity.internalServerError().header("").build();
		return ResponseEntity.ok(obj_persona);
	}
}
