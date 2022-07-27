package co.edu.unicauca.APIHappLab.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.APIHappLab.DTO.seccion_dto;
import co.edu.unicauca.APIHappLab.model.seccion;
import co.edu.unicauca.APIHappLab.service.seccion_service;

@RestController
@RequestMapping("/seccion")
public class seccion_controller {

	private Path carpeta_root = Paths.get(new FileSystemResource("").getFile().getAbsolutePath()+"\\ContenidoSeccion");
	@Autowired
	private seccion_service service;
	
	@GetMapping("/")
	public ResponseEntity<?> readAll(){
		List<seccion> rta;
		try {
			rta = service.findAll();
			return ResponseEntity.ok(rta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message: Error Al leer imagen: "+e.getMessage());
		}
	}
	
	@GetMapping("/contenido/{nombre_contenido}")
	public ResponseEntity<?> getArchivoContenido(@PathVariable String nombre_contenido){
		Path archivo;
		Resource resource;
		try {
			archivo = carpeta_root.resolve(nombre_contenido);
			resource = new UrlResource(archivo.toUri());
			if(resource.isFile() || resource.isReadable()) {
				return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).body(resource);
			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no es archivo o no se puede leer");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message: Error Al leer imagen: "+e.getMessage());
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@ModelAttribute seccion_dto pathVar_seccion,@PathVariable Integer id) {
		seccion obj_seccion = service.findById(id).get();
		obj_seccion.setTitulo_seccion(pathVar_seccion.getTitulo_seccion());
		if(pathVar_seccion.getContenido()!=null){
			if(!obj_seccion.getNombre_contenido().equals("")) {
				try {
					Files.deleteIfExists(carpeta_root.resolve(obj_seccion.getNombre_contenido()));
					obj_seccion.setNombre_contenido("");
				} catch (IOException e) {
					e.printStackTrace();
					return ResponseEntity.internalServerError().body("message: error al elminar archivo antiguo "+e.getMessage());
				}
			}
			try {
				Date date = new Date();
				MultipartFile archivo = pathVar_seccion.getContenido();
				String nombre_archivo =date.getTime()+archivo.getOriginalFilename();
				Files.copy(archivo.getInputStream(), this.carpeta_root.resolve(nombre_archivo));
				obj_seccion.setNombre_contenido(nombre_archivo);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body("message: error al guardar el archivo "+e.getMessage());
			}
		}
		obj_seccion.setUrl(pathVar_seccion.getUrl());
		obj_seccion.setDescripcion(pathVar_seccion.getDescripcion());
		obj_seccion.setCoordenadas(pathVar_seccion.getCoordenadas());
		seccion rta;
		try {
			rta=service.update(obj_seccion);
			return ResponseEntity.ok(rta);
		} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body("message: error al actualizar "+e.getMessage());
		}
	}
}
