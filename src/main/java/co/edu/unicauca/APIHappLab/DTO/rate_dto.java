package co.edu.unicauca.APIHappLab.DTO;

import java.util.Date;

import co.edu.unicauca.APIHappLab.model.rate;


public class rate_dto {
	private String email_persona;
	private double valoracion;
	private String comentario;
	public rate_dto() {
	}
	public rate_dto(String email_persona, double valoracion, String comentario,
			Date fecha_calificacion) {
		super();
		this.email_persona = email_persona;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}
	public rate to_rate() {
		return new rate(null , this.valoracion,this.comentario);
	}
	public String getEmail_persona() {
		return email_persona;
	}
	public void setEmail_persona(String email_persona) {
		this.email_persona = email_persona;
	}
	public double getValoracion() {
		return valoracion;
	}
	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


}
