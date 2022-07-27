package co.edu.unicauca.APIHappLab.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.APIHappLab.DTO.seccion_dto;
import co.edu.unicauca.APIHappLab.model.seccion;
import co.edu.unicauca.APIHappLab.service.seccion_service;

@RestController
@RequestMapping("/seccion")
@CrossOrigin({"http://localhost:8080","http://localhost:3000"})
public class seccion_controller {

	private Path carpeta_root = Paths.get(new FileSystemResource("").getFile().getAbsolutePath()+"\\Files");
	@Autowired
	private seccion_service service;
	
	@GetMapping("/")
	public List<seccion> readAll(){
		return service.findAll();
	}
	
	@PutMapping("/update")
	public seccion update(@ModelAttribute seccion_dto pathVar_seccion) {
		return service.update(pathVar_seccion.to_seccion());
	}
}
