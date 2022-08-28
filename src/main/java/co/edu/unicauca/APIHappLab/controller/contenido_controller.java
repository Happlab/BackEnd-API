package co.edu.unicauca.APIHappLab.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class contenido_controller {
	
		private Path carpeta_root = Paths.get("./Files");
		@Autowired
		private contenido_service service;
		@Autowired
		private persona_service p_service;
		@GetMapping("/")
		public ResponseEntity<?> readAll(){
			try {
				List<contenido> rta=service.findAll();
				return ResponseEntity.ok(rta);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body("message:"+e.getMessage());
			}
		}
		@GetMapping("/{contenido_link}")
		public ResponseEntity<?> findby_contenido_link(@PathVariable String contenido_link){
			try {
				Optional<contenido> rta = service.findby_contenido_link(contenido_link);
				return ResponseEntity.ok(rta);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: no se encontro el archivo"+e.getMessage());
			}
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
		public ResponseEntity<?> create(@ModelAttribute contenido_dto dto) {
			contenido obj_contenido = dto.to_contenido();
			persona auth;
			try {
				auth = p_service.findPersonaByEmail(dto.getEmail_autor());
				obj_contenido.setId_autor(auth);
				obj_contenido.setFecha_subida(LocalDate.now());
				obj_contenido.setPendiente(true);
				obj_contenido.setVisible(false);
				obj_contenido.setValoracion_general(0.0);
			}catch(Exception e){
				return ResponseEntity.internalServerError().body("message: error al llenar obj_persona "+e.getMessage());
			}
			MultipartFile archivo = dto.getArchivo();
			try {
				String nombre_archivo = ""+obj_contenido.getId_autor().getId_usuario() + LocalTime.now().getNano() + archivo.getOriginalFilename();
				Files.copy(archivo.getInputStream(), this.carpeta_root.resolve(nombre_archivo));
				obj_contenido.setLink(nombre_archivo);
			} catch (Exception e) {
				e.printStackTrace();
				ResponseEntity.internalServerError().body("message: error al guardar archivo "+e.getMessage());
			}
			contenido rta;
			try {
				rta = service.create(obj_contenido);
				return ResponseEntity.ok(rta);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().body("message:error al guardar la informacion en bdd "+ e.getMessage());
			}

		}
		@PutMapping("/update/{link_contenido}")
		public ResponseEntity<?> update(@Validated @RequestBody contenido_dto dto_contenido, @PathVariable String link_contenido) {
			contenido obj_contenido_db = service.findby_contenido_link(link_contenido).get();
			contenido obj_contenido_pv = dto_contenido.to_contenido();
			obj_contenido_pv.setId_contenido(obj_contenido_db.getId_contenido());
			if(!dto_contenido.getEmail_autor().equals(obj_contenido_db.getId_autor().getEmail())&&dto_contenido.getEmail_autor().isBlank()){
				obj_contenido_pv.setId_autor(p_service.findPersonaByEmail(dto_contenido.getEmail_autor()));
			}
			if (!dto_contenido.getArchivo().isEmpty()) {
				try {
					Files.deleteIfExists(carpeta_root.resolve(obj_contenido_db.getLink()));
				} catch (IOException e) {
					e.printStackTrace();
					return ResponseEntity.internalServerError()
							.body("message: error al elminar archivo antiguo " + e.getMessage());
				}
				try {
					MultipartFile archivo = dto_contenido.getArchivo();
					String nombre_archivo = ""+obj_contenido_pv.getId_autor().getId_usuario() + LocalTime.now().getNano() + archivo.getOriginalFilename();
					Files.copy(archivo.getInputStream(), this.carpeta_root.resolve(nombre_archivo));
					obj_contenido_pv.setLink(nombre_archivo);
					return ResponseEntity.ok(service.update(obj_contenido_pv));
				} catch (Exception e) {
					e.printStackTrace();
					return ResponseEntity.internalServerError()
							.body("message:Error en actualizar noticia: " + e.getMessage());
				}
			} else {
				obj_contenido_pv.setLink(obj_contenido_db.getLink());
				return ResponseEntity.ok(service.update(obj_contenido_pv));
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
			obj_rate.setFecha_calificacion(LocalDate.now());
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
		@PutMapping("/changeVisible/{link_contenido}")
		public ResponseEntity<?> visible(@PathVariable String link_contenido){
			try {
				contenido obj_contenido_db = service.findby_contenido_link(link_contenido).get();
				obj_contenido_db.setVisible(!obj_contenido_db.isVisible());
				return ResponseEntity.ok(service.update(obj_contenido_db));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: no se encontro el contenido " + e.getMessage());
			}
		}
		@PutMapping("/changePendiente/{link_contenido}")
		public ResponseEntity<?> pendiente(@PathVariable String link_contenido){
			try {
				contenido obj_contenido_db = service.findby_contenido_link(link_contenido).get();
				obj_contenido_db.setPendiente(!obj_contenido_db.isPendiente());
				return ResponseEntity.ok(service.update(obj_contenido_db));
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: no se encontro el contenido " + e.getMessage());
			}
		}
		@GetMapping("buscar/{tags}")
		public ResponseEntity<?> buscar(@PathVariable List<String> tags){
			try {
				ArrayList<contenido> respuesta = service.findByTags(tags);
				return ResponseEntity.ok(respuesta);
			}
			catch(Exception e){
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: 'no se encontro nada'");
			}
		}
}
