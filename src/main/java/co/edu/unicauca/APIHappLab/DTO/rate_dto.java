package co.edu.unicauca.APIHappLab.DTO;

import java.util.Date;

import co.edu.unicauca.APIHappLab.model.rate;


public class rate_dto {
	private String email_persona;
	private String id_contenido;
	private Double valoracion;
	private String comentarios;
	public rate_dto() {
	}
	public rate_dto(String email_persona, String id_contenido, Double valoracion, String comentarios,
			Date fecha_calificacion) {
		super();
		this.email_persona = email_persona;
		this.id_contenido = id_contenido;
		this.valoracion = valoracion;
		this.comentarios = comentarios;
	}
	public rate to_rate() {
		return new rate(null , null, this.valoracion,this.comentarios);
	}
	public String getEmail_persona() {
		return email_persona;
	}
	public void setEmail_persona(String email_persona) {
		this.email_persona = email_persona;
	}
	public String getId_contenido() {
		return id_contenido;
	}
	public void setId_contenido(String id_contenido) {
		this.id_contenido = id_contenido;
	}
	public Double getValoracion() {
		return valoracion;
	}
	public void setValoracion(Double valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}


}
