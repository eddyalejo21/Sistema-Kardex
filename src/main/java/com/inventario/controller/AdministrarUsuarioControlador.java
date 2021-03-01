package com.inventario.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.mindrot.jbcrypt.BCrypt;

import com.inventario.modelo.Producto;
import com.inventario.modelo.Usuario;
import com.inventario.service.IUsuarioService;

@Named
@ViewScoped
public class AdministrarUsuarioControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IUsuarioService usuarioService;

	private Usuario usuario;
	private List<Usuario> listarUsuario;
	private List<SelectItem> listarRoles;
	private String nombre, apellido;

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
		this.cargarUsuarios();
		this.cargarRoles();
	}

	public void cargarRoles() {
		this.listarRoles = new ArrayList<SelectItem>();

		this.listarRoles.add(new SelectItem('A', "Administrador"));
		this.listarRoles.add(new SelectItem('V', "Vendedor"));
	}

	public void cargarUsuarios() {
		try {
			this.listarUsuario = usuarioService.listar();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Transactional
	public void guardarUsuario() {
		try {
			String textoSinEncriptar = usuario.getUsclave();
			String textoEncriptado = DigestUtils.md5Hex(textoSinEncriptar);		
//			String clave = this.usuario.getUsclave();
//			String claveHash = BCrypt.hashpw(clave, BCrypt.gensalt());
			this.usuario.setUsclave(textoEncriptado);
			this.usuario.setUsEstado("A");
			this.usuario.setUsNombre(this.apellido.toUpperCase() + " " + this.nombre.toUpperCase());
			this.usuario.setUsUsuario(usuario.getUsUsuario().toUpperCase());
			this.usuarioService.registrar(this.usuario);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Registro guardado"));
			this.cargarRoles();
			this.cargarUsuarios();
			this.limpiarDialogoNuevo();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void modificarUsuario() {
		try {
			this.usuario.setUsNombre(usuario.getUsNombre().toUpperCase());
			this.usuario.setUsUsuario(usuario.getUsUsuario().toUpperCase());
			this.usuarioService.modificar(usuario);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Registro modificado"));
			this.cargarRoles();
			this.cargarUsuarios();
			this.limpiarDialogoNuevo();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void eliminarUsuario() {
		try {
			this.usuario.setUsEstado("D");
			this.usuarioService.modificar(usuario);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "CORRECTO", "Registro eliminado"));
			this.cargarRoles();
			this.cargarUsuarios();
			this.limpiarDialogoNuevo();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void mostrarData(Usuario usuario) {
		this.usuario = usuario;
	}

	public void limpiarDialogoNuevo() {
		this.usuario = new Usuario();
		this.nombre = null;
		this.apellido = null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListarUsuario() {
		return listarUsuario;
	}

	public void setListarUsuario(List<Usuario> listarUsuario) {
		this.listarUsuario = listarUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<SelectItem> getListarRoles() {
		return listarRoles;
	}

	public void setListarRoles(List<SelectItem> listarRoles) {
		this.listarRoles = listarRoles;
	}

}
