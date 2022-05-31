package co.edu.unicauca.APIHappLab.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contenido")
public class contenido {
	@Id
	private String id_contenido;
	@DBRef
	private persona id_autor;
	private Date fecha_subida;
	private String link;
	private String resumen;
	private ArrayList<String> autores;
	private ArrayList<String> tags;
	private String valoracion_general;
	
	public contenido() {
		// TODO Auto-generated constructor stub
	}

	public contenido(String id_contenido, persona id_autor, Date fecha_subida, String link, String resumen,
			ArrayList<String> autores, ArrayList<String> tags, String valoracion_general,
			ArrayList<rate> calificaciones) {
		super();
		this.id_contenido = id_contenido;
		this.id_autor = id_autor;
		this.fecha_subida = fecha_subida;
		this.link = link;
		this.resumen = resumen;
		this.autores = autores;
		this.tags = tags;
		this.valoracion_general = valoracion_general;
	}

	public String getId_contenido() {
		return id_contenido;
	}

	public void setId_contenido(String id_contenido) {
		this.id_contenido = id_contenido;
	}

	public persona getId_autor() {
		return id_autor;
	}

	public void setId_autor(persona id_autor) {
		this.id_autor = id_autor;
	}

	public Date getFecha_subida() {
		return fecha_subida;
	}

	public void setFecha_subida(Date fecha_subida) {
		this.fecha_subida = fecha_subida;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
