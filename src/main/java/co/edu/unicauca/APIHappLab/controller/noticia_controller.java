package co.edu.unicauca.APIHappLab.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.APIHappLab.DTO.noticia_dto;
import co.edu.unicauca.APIHappLab.model.noticia;
import co.edu.unicauca.APIHappLab.service.noticia_service;

@RestController
@RequestMapping("/noticia")
@CrossOrigin(origins= {"http://localhost:8080","http://localhost:3000"})
public class noticia_controller {
	@Autowired
	private noticia_service service;
	private Path carpeta_root = Paths.get(new FileSystemResource("").getFile().getAbsolutePath()+"\\Noticias");
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
	public ResponseEntity<?> create(@Valid @ModelAttribute noticia_dto dto_noticia) {
		noticia obj_noticia = dto_noticia.to_noticia();
		try {
			obj_noticia.setFecha_creacion(new Date());
			MultipartFile img = dto_noticia.getImagen();
			String nombre_archivo = obj_noticia.getFecha_creacion().getTime() + img.getOriginalFilename();
			Files.copy(img.getInputStream(), this.carpeta_root.resolve(nombre_archivo));
			obj_noticia.setLink_contenido(nombre_archivo);
			noticia respuesta = service.create(obj_noticia);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message: Error en la creacion de noticia "+ e.getMessage());
		}
	}
	@PutMapping("/Update")
	public ResponseEntity<?> updateNoticia(@Valid @RequestBody noticia body_noticia) {
		try {
			noticia respuesta = service.update(body_noticia);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message:Error en actualizar noticia: " + e.getMessage());
		}
	}
	
/*	@DeleteMapping("/delete/{id_noticia}")
	public ResponseEntity<?> delete(@Valid @PathVariable String id_noticia){
		try {
			service.delete(id_noticia);
			Files.deleteIfExists(carpeta_root.resolve(contenido_link));
			return ResponseEntity.ok().body("message: borrado con exito");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}*/
	@DeleteMapping("/delete/{link_contenido}")
	public ResponseEntity<?> deleteByLinkContenido(@Valid @PathVariable String link_contenido){
		try {
			noticia obj_noticia = service.findByLink_contenido(link_contenido).get();
			service.delete(obj_noticia.getId_noticia());
			Files.deleteIfExists(carpeta_root.resolve(link_contenido));
			return ResponseEntity.ok().body("message: borrado con exito");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message:error al eliminar contenido debido a "+ e.getMessage());
		}
	}

}
