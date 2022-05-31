package co.edu.unicauca.APIHappLab.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.APIHappLab.model.contenido;
import co.edu.unicauca.APIHappLab.service.contenido_service;

@RestController
@RequestMapping("/contenido")
public class contenido_controller {
		
		@Autowired
		private contenido_service service;
		
		
		@GetMapping("/")
		public List<contenido> readAll(){
			return service.findAll();
		}
		@GetMapping("/{contenido_id}")
		public Optional<contenido> findbyId(@PathVariable Long contenido_id){
			return service.findbyId(contenido_id);
		}
		@PostMapping("/create")
		public contenido create(@Validated @RequestBody contenido body_contenido) {
			return service.create(body_contenido);
		}
		@PutMapping("/Update")
		public contenido update(@Validated @RequestBody contenido body_contenido) {
			return service.update(body_contenido);
		}
}
