package co.edu.unicauca.APIHappLab.controller;

import java.util.List;
import java.util.Optional;

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

import co.edu.unicauca.APIHappLab.DTO.noticia_dto;
import co.edu.unicauca.APIHappLab.model.noticia;
import co.edu.unicauca.APIHappLab.service.noticia_service;

@RestController
@RequestMapping("/noticia")
@CrossOrigin(origins= "http://localhost:8080")
public class noticia_controller {
	@Autowired
	private noticia_service service;
	
	@GetMapping("/")
	public List<noticia> readAll(){
		return service.findAll();
	}
	@GetMapping("/{noticia_id}")
	public Optional<noticia> findbyId(@PathVariable String noticia_id){
		return service.findById(noticia_id);
	}
	@PostMapping("/create")
	public noticia create(@Valid @RequestBody noticia_dto body_noticia) {
		return service.create(body_noticia.to_noticia());
	}
	@PutMapping("/Update")
	public noticia updateRate(@Valid @RequestBody noticia body_noticia) {
		return service.update(body_noticia);
	}

}
