package co.edu.unicauca.APIHappLab.DTO;

import co.edu.unicauca.APIHappLab.model.persona;

public class persona_dto {
	private String email;
	private String password;
	private Long cedula;
	private String nombres;
	private String apellidos;
	private String rol;
	private int tokens;
	private boolean activo;
	
	public persona_dto() {
		// TODO Auto-generated constructor stub
	}
	
	public persona_dto(String email, String password, Long cedula, String nombres, String apellidos, String rol, int tokens) {
		super();
		this.email = email;
		this.password = password;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.rol = rol;
		this.tokens = tokens;
	}
	public persona to_persona() {
		return new persona(email, password, cedula, nombres, apellidos, rol, tokens,activo);
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
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
