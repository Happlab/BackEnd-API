package co.edu.unicauca.APIHappLab.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import co.edu.unicauca.APIHappLab.DTO.persona_dto;
import co.edu.unicauca.APIHappLab.enums.Role;

@Document(collection = "persona")
public class persona {
	@Id
	private String id_usuario;
	@Indexed(unique=true, direction=IndexDirection.DESCENDING, dropDups=true)
	@Email
	@NotBlank(message="campo email obligatorio")
	@NotEmpty(message="campo email obligatorio")
	private String email;
	@NotBlank(message="campo password obligatorio")
	@NotEmpty(message="campo password obligatorio")
	private String password;
	@Min(value=0)
	@Indexed(unique=true, direction=IndexDirection.DESCENDING, dropDups=true)
	@NotNull(message="campo cedula obligatorio")
	private Long cedula;
	@NotBlank(message="campo nombres obligatorio")
	@NotEmpty(message="campo nombres obligatorio")
	private String nombres;
	@NotBlank(message="campo apellidos obligatorio")
	@NotEmpty(message="campo apellidos obligatorio")
	private String apellidos;
	@NotBlank(message="campo tipo docente obligatorio")
	@NotEmpty(message="campo tipo docente obligatorio")
	private String tipo_docente;
	
	private List<Role> rol;
	@NotNull
	@Min(value=0)
	private int tokens;
	@NotNull
	private boolean activo;
	@NotNull
	private boolean pendiente;

	public persona(String email,String password,Long cedula,String nombres,String apellidos,String tipo_docente, int tokens, boolean activo, boolean pendiente) {
		super();
		this.email = email;
		this.password = password;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipo_docente = tipo_docente;
		this.rol = new ArrayList<Role>();
		this.tokens = tokens;
		this.activo = activo;
		this.pendiente = pendiente;
	}
	public persona_dto to_persona_dto() {
		return new persona_dto(this.email,this.password,this.cedula,this.nombres,this.apellidos,this.tipo_docente,this.tokens,this.activo,this.pendiente);
	}
	public void addRol(Role rol) {
		this.rol.add(rol);
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getCedula() {
		return cedula;
	}
	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTipo_docente() {
		return tipo_docente;
	}
	public void setTipo_docente(String tipo_docente) {
		this.tipo_docente = tipo_docente;
	}
	public List<Role> getRol() {
		return rol;
	}
	public void setRol(List<Role> rol) {
		this.rol = rol;
	}
	public int getTokens() {
		return tokens;
	}
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public boolean isPendiente() {
		return pendiente;
	}
	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
	
}
