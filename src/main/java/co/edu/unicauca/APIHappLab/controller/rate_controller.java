package co.edu.unicauca.APIHappLab.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.APIHappLab.DTO.rate_dto;
import co.edu.unicauca.APIHappLab.model.contenido;
import co.edu.unicauca.APIHappLab.model.rate;
import co.edu.unicauca.APIHappLab.service.contenido_service;
import co.edu.unicauca.APIHappLab.service.persona_service;
import co.edu.unicauca.APIHappLab.service.rate_service;

@RestController
@RequestMapping("/rate")
@CrossOrigin({"http://localhost:8080","http://localhost:3000"})
public class rate_controller {
	
	@Autowired
	private rate_service service_rate;
	@Autowired
	private persona_service service_persona;
	@Autowired
	private contenido_service service_contenido;
	
	@GetMapping("/")
	public List<rate> readAll(){
		return service_rate.findAll();
	}
	@GetMapping("/{rate_id}")
	public Optional<rate> findbyId(@PathVariable String rate_id){
		return service_rate.findbyId(rate_id);
	}
	@PostMapping("/create")
	public rate create(@Validated @RequestBody rate_dto dto_rate) {
		rate rt = dto_rate.to_rate();
		contenido obj_contenido=service_contenido.findbyId(dto_rate.getId_contenido()).get();
		rt.setId_persona(service_persona.findPersonaByEmail(dto_rate.getEmail_persona()));
		Long numComentarios = service_rate.count_by_contenido_id(obj_contenido.getId_contenido());
		if (numComentarios!=0) {
			obj_contenido.setValoracion_general(((obj_contenido.getValoracion_general()*numComentarios)+dto_rate.getValoracion())/(numComentarios+1));
		}else {
			obj_contenido.setValoracion_general(dto_rate.getValoracion());
		}
		rt.setId_contenido(obj_contenido);
		rt.setFecha_calificacion(new Date());
		return service_rate.create(rt);
	}
	@PutMapping("/Update")
	public rate updateRate(@Validated @RequestBody rate body_rate) {
		return service_rate.update(body_rate);
	}
}
