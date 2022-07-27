package co.edu.unicauca.APIHappLab.model;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import co.edu.unicauca.APIHappLab.DTO.rate_dto;

@Document(collection = "rate")
public class rate {
	@NotNull
	@DBRef
	private persona id_persona;
	@NotNull
	@Min(value=0)
	@Max(value=5)
	private double valoracion;
	@NotBlank(message="campo password obligatorio")
	@NotEmpty(message="campo password obligatorio")
	private String comentario;
	@NotNull
	private LocalDate fecha_calificacion;
	
	public rate() {
	}

	public rate(persona id_persona, double valoracion, String comentario) {
		super();
		this.id_persona = id_persona;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}
	
	public rate_dto to_rate_dto() {
		return new rate_dto(this.id_persona.getEmail(), this.valoracion, this.comentario);
	}
	
	public LocalDate getFecha_calificacion() {
		return fecha_calificacion;
	}

	public void setFecha_calificacion(LocalDate fecha_calificacion) {
		this.fecha_calificacion = fecha_calificacion;
	}

	public persona getId_persona() {
		return id_persona;
	}
	public void setId_persona(persona id_persona) {
		this.id_persona = id_persona;
	}
	public double getValoracion() {
		return valoracion;
	}
	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentarios() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	
}
