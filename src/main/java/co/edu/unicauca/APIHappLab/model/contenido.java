package co.edu.unicauca.APIHappLab.model;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import co.edu.unicauca.APIHappLab.DTO.contenido_dto;

@Document(collection = "contenido")
public class contenido {
	@Id
	private String id_contenido;
	@NotBlank(message="campo link obligatorio")
	@NotEmpty(message="campo link obligatorio")
	private String titulo;
	@DBRef
	private persona id_autor;
	@NotNull
	private LocalDate fecha_subida;
	@NotBlank(message="campo link obligatorio")
	@NotEmpty(message="campo link obligatorio")
	@Indexed(unique=true, direction=IndexDirection.DESCENDING, dropDups=true)
	private String link;
	@NotBlank(message="campo resumen obligatorio")
	@NotEmpty(message="campo resumen obligatorio")
	private String resumen;
	@NotEmpty(message="campo autores obligatorio")
	private ArrayList<String> autores;
	private ArrayList<String> tags;
	@NotNull
	@Min(value=0)
	@Max(value=5)
	private Double valoracion_general;
	private boolean visible;
	private boolean pendiente;
	@NotNull
	private ArrayList<rate> comentarios;
	
	public contenido(persona id_autor, LocalDate fecha_subida, String link, String resumen,
			ArrayList<String> autores, ArrayList<String> tags,String titulo) {
		super();
		this.id_autor = id_autor;
		this.fecha_subida = fecha_subida;
		this.link = link;
		this.resumen = resumen;
		this.autores = autores;
		this.tags = tags;
		this.comentarios = new ArrayList<rate>();
		this.titulo=titulo;
	}
	public contenido_dto to_contenido_dto(){
		return new contenido_dto( id_autor.getEmail() , null , resumen, autores, tags,comentarios,titulo);
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
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

	public LocalDate getFecha_subida() {
		return fecha_subida;
	}

	public void setFecha_subida(LocalDate fecha_subida) {
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

	public Double getValoracion_general() {
		return valoracion_general;
	}

	public void setValoracion_general(Double valoracion_general) {
		this.valoracion_general = valoracion_general;
	}
	
	public void addComentario(rate Comentario) {
		this.comentarios.add(Comentario);
	}
	public ArrayList<rate> getComentarios() {
		return comentarios;
	}
	public void setComentarios(ArrayList<rate> comentarios) {
		this.comentarios = comentarios;
	}
}
