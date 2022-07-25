package co.edu.unicauca.APIHappLab.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import co.edu.unicauca.APIHappLab.DTO.rate_dto;

@Document(collection = "rate")
public class rate {
	@Id
	private String id_rate;
	@NotNull
	@DBRef(lazy=true)
	private persona id_persona;
	@NotNull
	@Min(value=0)
	@Max(value=5)
	private int valoracion;
	@NotBlank(message="campo password obligatorio")
	@NotEmpty(message="campo password obligatorio")
	private String comentario;
	@NotNull
	private Date fecha_calificacion;
	
	public rate() {
	}

	public rate(persona id_persona, int valoracion, String comentario) {
		super();
		this.id_persona = id_persona;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}
	
	public rate_dto to_rate_dto() {
		return new rate_dto(this.id_persona.getEmail(), this.valoracion, this.comentario, this.fecha_calificacion);
		
	}
	
	public Date getFecha_calificacion() {
		return fecha_calificacion;
	}

	public void setFecha_calificacion(Date fecha_calificacion) {
		this.fecha_calificacion = fecha_calificacion;
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
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getComentarios() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	
}
