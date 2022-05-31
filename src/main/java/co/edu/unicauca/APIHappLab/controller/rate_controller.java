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

import co.edu.unicauca.APIHappLab.model.rate;
import co.edu.unicauca.APIHappLab.service.rate_service;

@RestController
@RequestMapping("/rate")
public class rate_controller {
	
	@Autowired
	private rate_service service;
	//@Autowired
	//private rate_service service_persona;

	
	@GetMapping("/")
	public List<rate> readAll(){
		return service.findAll();
	}
	@GetMapping("/{rate_id}")
	public Optional<rate> findbyId(@PathVariable Long rate_id){
		return service.findbyId(rate_id);
	}
	@PostMapping("/create")
	public rate create(@Validated @RequestBody rate body_rate) {
		return service.create(body_rate);
	}
	@PutMapping("/Update")
	public rate updateRate(@Validated @RequestBody rate body_rate) {
		return service.update(body_rate);
	}
}
