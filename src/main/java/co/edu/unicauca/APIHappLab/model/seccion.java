package co.edu.unicauca.APIHappLab.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "seccion")
public class seccion {
	@Id
	private Integer id;
	private String titulo_seccion;
	private String url;
	private String nombre_contenido;
	private String descripcion;
	private List<Double> coordenadas;
	public seccion(String titulo_seccion,  String url, String nombre_contenido, String descripcion,List<Double> coordenadas) {
		super();
		this.titulo_seccion = titulo_seccion;
		this.url = url;
		this.nombre_contenido = nombre_contenido;
		this.descripcion = descripcion;
		this.coordenadas = coordenadas;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public List<Double> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<Double> coordenadas) {
		this.coordenadas = coordenadas;
	}

}
