package com.inventario.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@Column (name = "usNombre", nullable = false, length = 50)
	private String usNombre;
	
	@Column(name = "usIdentificacion", nullable = false, length = 13)
	private String usIdentificacion;
	
	@Column(name = "usUsuario", nullable = false, length = 20)
	private String usUsuario;
	
	@Column(name = "usClave", nullable = false, length = 80)
	private String usclave;
	
	@Column(name = "usRol", nullable = false, length = 2)
	private String usRol;
	
	@Column(name = "usEstado", nullable = false, length = 1)
	private String usEstado;
	
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsNombre() {
		return usNombre;
	}

	public void setUsNombre(String usNombre) {
		this.usNombre = usNombre;
	}

	public String getUsIdentificacion() {
		return usIdentificacion;
	}

	public void setUsIdentificacion(String usIdentificacion) {
		this.usIdentificacion = usIdentificacion;
	}

	public String getUsUsuario() {
		return usUsuario;
	}

	public void setUsUsuario(String usUsuario) {
		this.usUsuario = usUsuario;
	}

	public String getUsclave() {
		return usclave;
	}

	public void setUsclave(String usclave) {
		this.usclave = usclave;
	}

	public String getUsRol() {
		return usRol;
	}

	public void setUsRol(String usRol) {
		this.usRol = usRol;
	}

	public String getUsEstado() {
		return usEstado;
	}

	public void setUsEstado(String usEstado) {
		this.usEstado = usEstado;
	}
	
	
	
}
