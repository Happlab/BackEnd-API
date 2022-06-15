package co.edu.unicauca.APIHappLab.model;

import java.util.ArrayList;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
	@Indexed(unique=true, direction=IndexDirection.DESCENDING, dropDups=true)
	@NotNull(message="campo cedula obligatorio")
	private Long cedula;
	@NotBlank(message="campo nombres obligatorio")
	@NotEmpty(message="campo nombres obligatorio")
	private String nombres;
	@NotBlank(message="campo nombres obligatorio")
	@NotEmpty(message="campo nombres obligatorio")
	private String apellidos;
	@NotBlank(message="campo rol obligatorio")
	@NotEmpty(message="campo rol obligatorio")
	private String rol;
	@NotNull
	private int tokens;
	
	public persona() {
		
	}

	public persona(String id_usuario, String email, String password, Long cedula, String nombres, String apellidos,
			String rol, ArrayList<contenido> contenidos, int tokens) {
		super();
		this.id_usuario = id_usuario;
		this.email = email;
		this.password = password;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.rol = rol;
		this.tokens = tokens;
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}


}
