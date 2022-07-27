package co.edu.unicauca.APIHappLab.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seccion")
public class seccion {
	@Id
	private Double id;
	@NotBlank
	@NotEmpty
	private String titulo_seccion;
	private String url;
	private String nombre_contenido;
	private String descripcion;
	private Object auxiliar;
	public seccion(Double id, String titulo_seccion,  String url, String nombre_contenido, String descripcion, Object auxiliar) {
		super();
		this.id = id;
		this.titulo_seccion = titulo_seccion;
		this.url = url;
		this.nombre_contenido = nombre_contenido;
		this.descripcion = descripcion;
		this.auxiliar = auxiliar;
	}
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}
	public String getTitulo_seccion() {
		return titulo_seccion;
	}
	public void setTitulo_seccion(String titulo_seccion) {
		this.titulo_seccion = titulo_seccion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNombre_contenido() {
		return nombre_contenido;
	}
	public void setNombre_contenido(String nombre_contenido) {
		this.nombre_contenido = nombre_contenido;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Object getAuxiliar() {
		return auxiliar;
	}
	public void setAuxiliar(Object auxiliar) {
		this.auxiliar = auxiliar;
	}
}
