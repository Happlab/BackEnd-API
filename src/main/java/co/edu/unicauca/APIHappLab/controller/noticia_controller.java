package co.edu.unicauca.APIHappLab.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.APIHappLab.DTO.noticia_dto;
import co.edu.unicauca.APIHappLab.model.noticia;
import co.edu.unicauca.APIHappLab.service.noticia_service;
import java.util.Date;

@RestController
@RequestMapping("/noticia")
public class noticia_controller {
	@Autowired
	private noticia_service service;
	private Path carpeta_root = Paths.get(new FileSystemResource("").getFile().getAbsolutePath()+"\\Noticias");
	@GetMapping("/")
	public ResponseEntity<?> readAll(){
		try {
			List<noticia> respuesta = service.findAll();
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/{noticia_id}")
	public ResponseEntity<?> findbyId(@PathVariable String noticia_id){
		try {
			Optional<noticia> respuesta = service.findById(noticia_id);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/img/{link_contenido}")
	public ResponseEntity<?> getImg(@PathVariable String link_contenido){
		Path archivo;
		Resource resource;
		try {
			archivo = carpeta_root.resolve(link_contenido);
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
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @ModelAttribute noticia_dto dto_noticia) {
		noticia obj_noticia = dto_noticia.to_noticia();
		try {
			obj_noticia.setFecha_creacion(LocalDate.now());
			MultipartFile img = dto_noticia.getImagen();
			String nombre_archivo = LocalTime.now().getNano() + img.getOriginalFilename();
			Files.copy(img.getInputStream(), this.carpeta_root.resolve(nombre_archivo));
			obj_noticia.setLink_contenido(nombre_archivo);
			noticia respuesta = service.create(obj_noticia);
			return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message: Error en la creacion de noticia "+ e.getMessage());
		}
	}
	@PutMapping("/Update/{Link_contenido}")
	public ResponseEntity<?> updateNoticia(@Valid @ModelAttribute noticia_dto dto_noticia,@PathVariable String Link_contenido) {
		noticia obj_noticia_db = service.findByLink_contenido(Link_contenido).get();
		noticia obj_noticia_pv = dto_noticia.to_noticia();
		obj_noticia_pv.setId_noticia(obj_noticia_db.getId_noticia());
		obj_noticia_pv.setFecha_creacion(obj_noticia_db.getFecha_creacion());
		try {
			Files.deleteIfExists(carpeta_root.resolve(obj_noticia_db.getLink_contenido()));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message: error al elminar archivo antiguo "+e.getMessage());
		}
		try {
                    Date date = new Date();
                    MultipartFile archivo = dto_noticia.getImagen();
                    String nombre_archivo =date.getTime()+archivo.getOriginalFilename();
                    Files.copy(archivo.getInputStream(), this.carpeta_root.resolve(nombre_archivo));
                    obj_noticia_pv.setLink_contenido(nombre_archivo);
                    noticia respuesta = service.update(obj_noticia_pv);
                    return ResponseEntity.ok(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body("message:Error en actualizar noticia: " + e.getMessage());
		}
	}
	
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