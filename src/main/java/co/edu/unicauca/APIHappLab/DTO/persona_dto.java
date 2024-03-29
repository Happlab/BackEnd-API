package co.edu.unicauca.APIHappLab.DTO;

import co.edu.unicauca.APIHappLab.model.persona;

public class persona_dto {
	private String email;
	private String password;
	private Long cedula;
	private String nombres;
	private String apellidos;
	private String tipo_docente;
	private int tokens;
	private boolean activo;
	private boolean pendiente;

	public persona_dto(String email, String password, Long cedula, String nombres, String apellidos, String tipo_docente,
			int tokens, boolean activo, boolean pendiente) {
		super();
		this.email = email;
		this.password = password;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipo_docente = tipo_docente;
		this.tokens = tokens;
		this.activo = activo;
		this.pendiente = pendiente;
	}


	public persona to_persona() {
		return new persona(email, password, cedula, nombres, apellidos, tipo_docente, tokens,activo,pendiente);
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

	public String getTipoDocente() {
		return tipo_docente;
	}

	public void setTipoDocente(String tipo_docente) {
		this.tipo_docente = tipo_docente;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
}