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
	@DBRef(lazy=true)
	private contenido id_contenido;
	@NotNull
	@Min(value=0)
	@Max(value=5)
	private Double valoracion;
	@NotBlank(message="campo password obligatorio")
	@NotEmpty(message="campo password obligatorio")
	private String comentarios;
	@NotNull
	private Date fecha_calificacion;
	
	public rate() {
	}

	public rate(persona id_persona, contenido id_contenido, Double valoracion, String comentarios) {
		super();
		this.id_persona = id_persona;
		this.id_contenido = id_contenido;
		this.valoracion = valoracion;
		this.comentarios = comentarios;
	}
	
	public rate_dto to_rate_dto() {
		return new rate_dto(this.id_persona.getEmail(),this.id_contenido.getId_contenido(), this.valoracion, this.comentarios, this.fecha_calificacion);
		
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
	public contenido getId_contenido() {
		return id_contenido;
	}
	public void setId_contenido(contenido id_contenido) {
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
