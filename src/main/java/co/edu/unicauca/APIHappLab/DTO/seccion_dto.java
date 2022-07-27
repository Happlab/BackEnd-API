package co.edu.unicauca.APIHappLab.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.APIHappLab.model.seccion;

public class seccion_dto {
	private String titulo_seccion;
	private String url;
	private MultipartFile contenido;
	private String descripcion;
	private List<Double> coordenadas;
	
	public seccion_dto(String titulo_seccion, String url, MultipartFile contenido, String descripcion,
			List<Double> coordenadas) {
		super();
		this.titulo_seccion = titulo_seccion;
		this.url = url;
		this.contenido = contenido;
		this.descripcion = descripcion;
		this.coordenadas = coordenadas;
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

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public MultipartFile getContenido() {
		return contenido;
	}
	public void setContenido(MultipartFile contenido) {
		this.contenido = contenido;
	}
	public List<Double> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<Double> coordenadas) {
		this.coordenadas = coordenadas;
	}
	public seccion to_seccion() {
		return new seccion(titulo_seccion, url, "", descripcion, coordenadas);
	}
	
	
}
