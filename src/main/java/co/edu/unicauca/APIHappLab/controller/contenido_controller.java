package co.edu.unicauca.APIHappLab.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import co.edu.unicauca.APIHappLab.DTO.contenido_dto;
import co.edu.unicauca.APIHappLab.DTO.rate_dto;
import co.edu.unicauca.APIHappLab.model.contenido;
import co.edu.unicauca.APIHappLab.model.persona;
import co.edu.unicauca.APIHappLab.model.rate;
import co.edu.unicauca.APIHappLab.service.contenido_service;
import co.edu.unicauca.APIHappLab.service.persona_service;

@RestController
@RequestMapping("/contenido")
@CrossOrigin({"http://localhost:8080","http://localhost:3000"})
public class contenido_controller {
	
		private Path carpeta_root = Paths.get(new FileSystemResource("").getFile().getAbsolutePath()+"\\Files");
		@Autowired
		private contenido_service service;
		@Autowired
		private persona_service p_service;
		@GetMapping("/")
		public List<contenido> readAll(){
			return service.findAll();
		}
		@GetMapping("/{contenido_link}")
		public Optional<contenido> findby_contenido_link(@PathVariable String contenido_link){
			return service.findby_contenido_link(contenido_link);
		}
		@GetMapping("/download/{contenido_link}")
		public ResponseEntity<?> downloadbyId(@PathVariable String contenido_link) throws MalformedURLException{
			try {
				Path archivo = carpeta_root.resolve(contenido_link);
				Resource resource = new UrlResource(archivo.toUri());
				if(resource.isFile() || resource.isReadable()) {
					return ResponseEntity.ok().contentType(MediaType.MULTIPART_FORM_DATA).
							header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"").body(resource);	
				}
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ExeptionMessage:No es archivo o el archivo no pudo ser leido");
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.notFound().build();
				}
		}
		@PostMapping("/create")
		public contenido create(@ModelAttribute contenido_dto dto) {
			contenido obj_contenido = dto.to_contenido();
			persona auth;
			try {
				auth = p_service.findPersonaByEmail(dto.getEmail_autor());
				obj_contenido.setId_autor(auth);
				obj_contenido.setFecha_subida(new Date());
				obj_contenido.setPendiente(true);
				obj_contenido.setVisible(false);
				obj_contenido.setValoracion_general(0.0);
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
		@PutMapping("/update")
		public ResponseEntity<?> update(@Validated @RequestBody contenido body_contenido) {
			try {
				contenido respuesta = service.update(body_contenido);
				return ResponseEntity.ok(respuesta);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.noContent().build();
			}
		}
		
		@DeleteMapping("/delete/{contenido_link}")
		public ResponseEntity<?> delete(@PathVariable String contenido_link){
			try {
				Files.deleteIfExists(carpeta_root.resolve(contenido_link));
				service.delete(contenido_link);
				return ResponseEntity.ok("message: contenido borrado");
			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body("message: error al eliminar archivo");
			}
		}
		
		@PostMapping("/comentar/{contenido_link}")
		public ResponseEntity<?> comentar(@PathVariable String contenido_link,@RequestBody rate_dto dto_rate){
			contenido obj_contenido;
			try {
				obj_contenido = service.findby_contenido_link(contenido_link).get();
			} catch (Exception e1) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Find Contenido:"+e1.getMessage());
			}
			rate obj_rate = dto_rate.to_rate();
			obj_rate.setId_persona(p_service.findPersonaByEmail(dto_rate.getEmail_persona()));
			obj_rate.setFecha_calificacion(new Date());
			if(obj_contenido.getComentarios().isEmpty()) {
				obj_contenido.setValoracion_general(obj_rate.getValoracion());
			}else {
				obj_contenido.setValoracion_general(((obj_contenido.getValoracion_general()*obj_contenido.getComentarios().size())+(obj_rate.getValoracion()))/(obj_contenido.getComentarios().size()+1));
			}
			obj_contenido.addComentario(obj_rate);
			try {
				obj_contenido = service.update(obj_contenido);
				return ResponseEntity.ok(obj_contenido);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body("message: Error al Actualizar contenido en comentar "+e.getMessage());
			}
		}
}