package co.edu.unicauca.APIHappLab.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "noticia")
public class noticia {
	@Id
	private String id_noticia;
	@NotBlank(message="campo titulo_noticia obligatorio")
	@NotEmpty(message="campo titulo_noticia obligatorio")
	private String titulo_noticia;
	@NotBlank(message="campo url_noticia obligatorio")
	@NotEmpty(message="campo url_noticia obligatorio")
	private String url_noticia;
	@NotBlank(message="campo link obligatorio")
	@NotEmpty(message="campo link obligatorio")
	private String link_contenido;
	@NotNull
	private boolean visible;
	
	public noticia() {

	}

	public noticia(String id_noticia,String titulo_noticia, String url_noticia, String link_contenido, boolean visible) {
		super();
		this.id_noticia = id_noticia;
		this.titulo_noticia = titulo_noticia;
		this.url_noticia = url_noticia;
		this.link_contenido = link_contenido;
		this.visible = visible;
	}

	public String getId_noticia() {
		return id_noticia;
	}

	public void setId_noticia(String id_noticia) {
		this.id_noticia = id_noticia;
	}

	public String getTitulo_noticia() {
		return titulo_noticia;
	}

	public void setTitulo_noticia(String titulo_noticia) {
		this.titulo_noticia = titulo_noticia;
	}

	public String getUrl_noticia() {
		return url_noticia;
	}

	public void setUrl_noticia(String url_noticia) {
		this.url_noticia = url_noticia;
	}

	public String getLink_contenido() {
		return link_contenido;
	}

	public void setLink_contenido(String link_contenido) {
		this.link_contenido = link_contenido;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
