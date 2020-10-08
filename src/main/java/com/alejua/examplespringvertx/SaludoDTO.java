package com.alejua.examplespringvertx;

public class SaludoDTO {

	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public SaludoDTO(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
}
