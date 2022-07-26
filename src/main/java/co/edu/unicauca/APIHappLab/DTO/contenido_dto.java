package co.edu.unicauca.APIHappLab.DTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.APIHappLab.model.contenido;
import co.edu.unicauca.APIHappLab.model.rate;
public class contenido_dto {

	private String email_autor;
	private MultipartFile archivo;
	private String resumen;
	private ArrayList<String> autores;
	private ArrayList<String> tags;
	private ArrayList<rate> comentarios;
	
	public contenido_dto(String email_autor, MultipartFile archivo, String resumen, ArrayList<String> autores,
			ArrayList<String> tags,ArrayList<rate> comentarios) {
		super();
		this.email_autor = email_autor;
		this.archivo = archivo;
		this.resumen = resumen;
		this.autores = autores;
		this.tags = tags;
		this.comentarios = comentarios;
	}
	
	public List<rate> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<rate> comentarios) {
		this.comentarios = comentarios;
	}

	public contenido to_contenido(){
		return new contenido( null ,  null, archivo.getOriginalFilename(), resumen, autores, tags);
	}
	
	public String getEmail_autor() {
		return email_autor;
	}
	public void setEmail_autor(String email_autor) {
		this.email_autor = email_autor;
	}
	public MultipartFile getArchivo() {
		return archivo;
	}
	public void setArchivo(MultipartFile archivo) {
		this.archivo = archivo;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public ArrayList<String> getAutores() {
		return autores;
	}
	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

}
