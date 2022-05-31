package co.edu.unicauca.APIHappLab.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rate")
public class rate {
	@Id
	private String id_rate;
	@DBRef
	private persona id_persona;
	@DBRef
	private contenido id_contenido;
	private int valoracion;
	private String comentarios;
	public rate() {
		// TODO Auto-generated constructor stub
	}
	public rate(String id_rate, persona id_persona, contenido id_contenido, int valoracion, String comentarios) {
		super();
		this.id_rate = id_rate;
		this.id_persona = id_persona;
		this.id_contenido = id_contenido;
		this.valoracion = valoracion;
		this.comentarios = comentarios;
	}
	public String getId_rate() {
		return id_rate;
	}
	public void setId_rate(String id_rate) {
		this.id_rate = id_rate;
	}
	public persona getId_persona() {
		return id_persona;
	}
	public void setId_persona(persona id_persona) {
		this.id_persona = id_persona;
	}
	public contenido getId_contenido() {
		return id_contenido;
	}
	public void setId_contenido(contenido id_contenido) {
		this.id_contenido = id_contenido;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	
}
