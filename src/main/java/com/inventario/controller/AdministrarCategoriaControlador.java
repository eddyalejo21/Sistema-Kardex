package com.inventario.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.inventario.modelo.Categoria;
import com.inventario.service.ICategoriaService;

@Named
@ViewScoped
public class AdministrarCategoriaControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private ICategoriaService categoriaService;

	private Categoria categoria;
	private Categoria categoriaSeleccion;
	private List<Categoria> listarCategoria;
	private Boolean deshabilitar;

	@PostConstruct
	public void init() {
		this.categoria = new Categoria();
		this.cargarCategoria();
	}

	public void cargarCategoria() {
		try {
			this.listarCategoria = categoriaService.listar();
		} catch (Exception e) {
			Logger.getLogger(AdministrarCategoriaControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void seleccionarCategoria() {
		this.categoria = this.categoriaSeleccion;
		this.deshabilitar = true;
	}

	public void guardarCategoria() {
		try {
			if (categoria.getIdCategoria() == null) {
				categoria.setCatEstado("A");
				categoria.setCatNombre(categoria.getCatNombre().toUpperCase());
				categoriaService.registrar(categoria);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro guardado"));
				this.cargarCategoria();
				this.nuevo();
			}
		} catch (Exception e) {
			Logger.getLogger(AdministrarCategoriaControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void modificarCategoria() {
		try {
			if (categoria.getIdCategoria() != null) {
				categoria.setCatNombre(categoria.getCatNombre().toUpperCase());
				categoriaService.modificar(categoria);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro modificado"));
				this.init();
				this.deshabilitar = false;
			}
		} catch (Exception e) {
			Logger.getLogger(AdministrarCategoriaControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void eliminarCategoria() {
		try {
			if (categoria.getIdCategoria() != null) {
				categoria.setCatEstado("D");
				categoriaService.modificar(categoria);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro eliminado"));
				this.init();
				this.deshabilitar = false;
			}
		} catch (Exception e) {
			Logger.getLogger(AdministrarCategoriaControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void nuevo() {
		this.categoria = new Categoria();
		this.deshabilitar = false;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getListarCategoria() {
		return listarCategoria;
	}

	public void setListarCategoria(List<Categoria> listarCategoria) {
		this.listarCategoria = listarCategoria;
	}

	public Categoria getCategoriaSeleccion() {
		return categoriaSeleccion;
	}

	public void setCategoriaSeleccion(Categoria categoriaSeleccion) {
		this.categoriaSeleccion = categoriaSeleccion;
	}

	public Boolean getDeshabilitar() {
		return deshabilitar;
	}

	public void setDeshabilitar(Boolean deshabilitar) {
		this.deshabilitar = deshabilitar;
	}

}
