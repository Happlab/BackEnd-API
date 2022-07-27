package co.edu.unicauca.APIHappLab.DTO;

import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.APIHappLab.model.seccion;

public class seccion_dto {
	private Double id;
	private String titulo_seccion;
	private String url;
	private MultipartFile contenido;
	private String descripcion;
	private Object auxiliar;
	public seccion_dto(String titulo_seccion, String url, MultipartFile contenido, String descripcion,
			Object auxiliar) {
		super();
		this.titulo_seccion = titulo_seccion;
		this.url = url;
		this.contenido = contenido;
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
	public seccion to_seccion() {
		return new seccion(id, titulo_seccion, url, contenido.getOriginalFilename(), descripcion, auxiliar);
	}
	
	
}
