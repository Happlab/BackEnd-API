package co.edu.unicauca.APIHappLab.DTO;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.APIHappLab.model.contenido;
public class contenido_dto {

	private String email_autor;
	private Date fecha_subida;
	private MultipartFile archivo;
	private String resumen;
	private ArrayList<String> autores;
	private ArrayList<String> tags;
	private String valoracion_general;
	
	public contenido_dto() {}
	
	public contenido_dto(String email_autor, Date fecha_subida, MultipartFile archivo, String resumen, ArrayList<String> autores,
			ArrayList<String> tags, String valoracion_general) {
		super();
		this.email_autor = email_autor;
		this.fecha_subida = fecha_subida;
		this.archivo = archivo;
		this.resumen = resumen;
		this.autores = autores;
		this.tags = tags;
		this.valoracion_general = valoracion_general;
	}
	public contenido to_contenido(){
		return new contenido( null , fecha_subida, archivo.getOriginalFilename(), resumen, autores, tags, valoracion_general);
	}
	
	public String getEmail_autor() {
		return email_autor;
	}
	public void setEmail_autor(String email_autor) {
		this.email_autor = email_autor;
	}
	public Date getFecha_subida() {
		return fecha_subida;
	}
	public void setFecha_subida(Date fecha_subida) {
		this.fecha_subida = fecha_subida;
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
	public String getValoracion_general() {
		return valoracion_general;
	}
	public void setValoracion_general(String valoracion_general) {
		this.valoracion_general = valoracion_general;
	}

}
