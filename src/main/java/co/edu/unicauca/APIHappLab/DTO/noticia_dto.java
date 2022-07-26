package co.edu.unicauca.APIHappLab.DTO;

import org.springframework.web.multipart.MultipartFile;

import co.edu.unicauca.APIHappLab.model.noticia;

public class noticia_dto {
	private String titulo_noticia;
	private String url_noticia;
	private MultipartFile imagen;
	private boolean visible;
	public noticia_dto() {
		// TODO Auto-generated constructor stub
	}
	public noticia_dto(String titulo_noticia, String url_noticia, MultipartFile imagen, boolean visible) {
		super();
		this.titulo_noticia = titulo_noticia;
		this.url_noticia = url_noticia;
		this.imagen = imagen;
		this.visible = visible;
	}
	public noticia to_noticia() {
		return new noticia(this.titulo_noticia, this.url_noticia, this.imagen.getOriginalFilename(), this.visible);
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
	
	public MultipartFile getImagen() {
		return imagen;
	}
	public void setImagen(MultipartFile imagen) {
		this.imagen = imagen;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
