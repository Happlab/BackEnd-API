package co.edu.unicauca.APIHappLab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.APIHappLab.DTO.persona_dto;
import co.edu.unicauca.APIHappLab.config.JwtUtilService;
import co.edu.unicauca.APIHappLab.enums.Role;
import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.service.persona_service;

@RestController
@RequestMapping("/persona")
public class usuario_controller {
	@Autowired
	private persona_service service;
	@Autowired
	private JwtUtilService jwtUtilService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public ResponseEntity<?> readAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{Email}")
	public ResponseEntity<?> findbyEmail(@PathVariable String Email) {
		persona obj_persona;
		try {
			obj_persona = service.findPersonaByEmail(Email);
			if (obj_persona == null)
				return ResponseEntity.notFound().build();
			return ResponseEntity.ok(obj_persona);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("Error al buscar persona " + e.getMessage());
		}

	}

	@PostMapping("/registro")
	public ResponseEntity<?> create(@Valid @RequestBody persona_dto dto, BindingResult b_result) {
		persona rta;
		persona obj_persona = dto.to_persona();
		obj_persona.setPendiente(true);
		obj_persona.addRol(Role.USER);
		try {
			rta = service.create(obj_persona);
			return ResponseEntity.ok().body(rta);
		} catch (Exception ex) {
			if (b_result.hasErrors()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(b_result.getAllErrors());
			}
			return ResponseEntity.internalServerError().body("message error en: " + ex.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updatePersona(@Valid @RequestBody persona_dto dto, BindingResult bindingResult) {
		persona obj_persona_dto = dto.to_persona();
		persona obj_persona_db;
		try {
			obj_persona_db = service.findPersonaByEmail(dto.getEmail());
		} catch (Exception e1) {
			e1.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: Persona no encontrada");
		}
		obj_persona_dto.setId_usuario(obj_persona_db.getId_usuario());
		obj_persona_dto.setRol(obj_persona_db.getRol());
		persona respuesta;
		try {
			respuesta = service.update(obj_persona_dto);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message: error al actualizar persona " + e.getMessage());
		}

	}

	@DeleteMapping("/desactivar/{Email}")
	public ResponseEntity<?> desactivar(@PathVariable String Email) {
		persona obj_persona;
		try {
			obj_persona = service.findPersonaByEmail(Email);
			if (obj_persona == null)
				return ResponseEntity.notFound().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no se encontreo la persona " + e.getMessage());
		}
		obj_persona.setActivo(false);
		try {
			service.update(obj_persona);
			return ResponseEntity.ok("message: se desactivo con exito ");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{Email}")
	public ResponseEntity<?> delete(@PathVariable String Email) {
		if (service.deleteByEmail(Email)) {
			return ResponseEntity.ok("message: Borrado con exito");
		}
		return ResponseEntity.internalServerError().body("message: usuario no encontrado");
	}

	@GetMapping("/auth/")
	public ResponseEntity<?> authenticate(@RequestParam String Email, @RequestParam String Contraseña) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(Email, Contraseña));
		
		UserDetails userDetails;
		try {
			userDetails = service.loadUserByUsername(Email);
	//		final String jwt = "Token: " + jwtUtilService.generateToken(userDetails);
			final String jwt = "Token: " + jwtUtilService.generateTokenWithContent(userDetails, service.findPersonaByEmail(Email));
			return ResponseEntity.ok(jwt);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("message:usuario o contraseña incorrecto"+e.getMessage());
		}
	}
}