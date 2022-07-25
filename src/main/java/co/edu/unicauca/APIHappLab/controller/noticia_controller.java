package co.edu.unicauca.APIHappLab.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.APIHappLab.DTO.noticia_dto;
import co.edu.unicauca.APIHappLab.model.noticia;
import co.edu.unicauca.APIHappLab.service.noticia_service;

@RestController
@RequestMapping("/noticia")
@CrossOrigin(origins= {"http://localhost:8080","http://localhost:3000"})
public class noticia_controller {
	@Autowired
	private noticia_service service;
	
	@GetMapping("/")
	public ResponseEntity<List<noticia>> readAll(){
		try {
			List<noticia> respuesta = service.findAll();
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/{noticia_id}")
	public ResponseEntity<Optional<noticia>> findbyId(@PathVariable String noticia_id){
		try {
			Optional<noticia> respuesta = service.findById(noticia_id);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping("/create")
	public ResponseEntity<noticia> create(@Valid @RequestBody noticia_dto body_noticia) {
		try {
			noticia respuesta = service.create(body_noticia.to_noticia());
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	@PutMapping("/Update")
	public ResponseEntity<noticia> updateRate(@Valid @RequestBody noticia body_noticia) {
		try {
			noticia respuesta = service.update(body_noticia);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/delete/{id_noticia}")
	public ResponseEntity<String> delete(@Valid @PathVariable String id_noticia){
		try {
			service.delete(id_noticia);
			return ResponseEntity.ok().body("message: borrado con exito");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
}
