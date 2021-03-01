package com.inventario.util;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;

import com.inventario.modelo.Usuario;
import com.inventario.service.IUsuarioService;

@Named
@ViewScoped
public class IniciarSesionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IUsuarioService usuarioService;

	private Usuario usuario;
	private String encriptar;
	private String encriptado;

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
	}
	
	

	public String iniciarSesion() {
		Usuario us;
		usuario.getUsUsuario().toUpperCase();
		String redireccion = null;
		encriptar = usuario.getUsclave();
		encriptado = DigestUtils.md5Hex(encriptar);

		try {
			us = usuarioService.iniciarSesion(usuario);
			
			if (us != null) {
				System.out.println("Ingresa al metodo");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
				redireccion = "/protegido/dashboard.xhtml?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
				Logger.getLogger(IniciarSesionController.class.getSimpleName()).log(
						Level.INFO, "*** SE INGRESÓ UN DATO INCORRECTO EN EL INICIO DE SESIÓN");
			}

		} catch (Exception e) {
			Logger.getLogger(IniciarSesionController.class.getName()).log(Level.SEVERE, null, e);
		}
		return redireccion;

	}
	
	public void verificarSesion() {
		try {
			Usuario us = (Usuario) FacesContext.getCurrentInstance()
					.getExternalContext().getSessionMap().get("usuario");

			if (us == null) {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("../login.xhtml");

			}

		} catch (Exception e) {
			Logger.getLogger(IniciarSesionController.class.getName()).log(Level.SEVERE, null, e);
		}

	}
	
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
	}

	public String nombreLogueado() {
		Usuario us = (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get("usuario");
		return us.getUsNombre();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEncriptar() {
		return encriptar;
	}

	public void setEncriptar(String encriptar) {
		this.encriptar = encriptar;
	}

	public String getEncriptado() {
		return encriptado;
	}

	public void setEncriptado(String encriptado) {
		this.encriptado = encriptado;
	}

}
