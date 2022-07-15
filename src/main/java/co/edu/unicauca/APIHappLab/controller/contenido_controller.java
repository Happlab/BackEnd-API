package co.edu.unicauca.APIHappLab.controller;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.APIHappLab.DTO.contenido_dto;
import co.edu.unicauca.APIHappLab.model.contenido;
import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.service.contenido_service;
import co.edu.unicauca.APIHappLab.service.persona_service;

@RestController
@RequestMapping("/contenido")
@CrossOrigin(origins= "http://localhost:8080")
public class contenido_controller {
		
		private Path carpeta_root = Paths.get("C:\\Users\\DAAC\\Documents\\GitHub\\BackEnd-API\\Files");
		@Autowired
		private contenido_service service;
		@Autowired
		private persona_service p_service;
		@GetMapping("/")
		public List<contenido> readAll(){
			return service.findAll();
		}
		@GetMapping("/{contenido_id}")
		public Optional<contenido> findbyId(@PathVariable String contenido_id){
			return service.findbyId(contenido_id);
		}
		@GetMapping("/download/{contenido_link}")
		public ResponseEntity<Resource> downloadbyId(@PathVariable String contenido_link) throws MalformedURLException{
			try {
				Path archivo = carpeta_root.resolve(contenido_link);
				Resource resource = new UrlResource(archivo.toUri());
				if(resource.isFile() || resource.isReadable()) {
					return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).
							header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"").body(resource);	
				}
				return ResponseEntity.notFound().build();
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.notFound().build();
				}
		}
		@PostMapping("/create")
		public contenido create(@ModelAttribute contenido_dto dto) {
			contenido obj_contenido = dto.to_contenido();
			obj_contenido.setFecha_subida(new Date());
			persona auth;
			try {
				auth = p_service.findPersonaByEmail(dto.getEmail_autor());	
				obj_contenido.setId_autor(auth);
			}catch(Exception ex){
				return null;
			}
			MultipartFile archivo = dto.getArchivo();
			try {
				String nombre_archivo = ""+obj_contenido.getId_autor().getId_usuario()+obj_contenido.getFecha_subida().getTime()+archivo.getOriginalFilename();
				Files.copy(archivo.getInputStream(), this.carpeta_root.resolve(nombre_archivo));
				obj_contenido.setLink(nombre_archivo);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return service.create(obj_contenido);
		}
		@PutMapping("/Update")
		public contenido update(@Validated @RequestBody contenido body_contenido) {
			return service.update(body_contenido);
		}
}
