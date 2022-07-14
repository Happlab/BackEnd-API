package co.edu.unicauca.APIHappLab.DTO;



public class noticia_dto {
	private String titulo_noticia;
	private String url_noticia;
	private String link_contenido;
	private boolean visible;
	public noticia_dto() {
		// TODO Auto-generated constructor stub
	}
	public noticia_dto(String titulo_noticia, String url_noticia, String link_contenido, boolean visible) {
		super();
		this.titulo_noticia = titulo_noticia;
		this.url_noticia = url_noticia;
		this.link_contenido = link_contenido;
		this.visible = visible;
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
