package com.inventario.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.inventario.modelo.Categoria;
import com.inventario.service.ICategoriaService;

@Named
@ViewScoped
public class AdministrarDashboardControlador  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ICategoriaService categoriaService;
	
	private Categoria categoria;
	private List<Categoria> listarCategoria;
	
	@PostConstruct
	public void init() {
		this.categoria = new Categoria();
		this.cargarCategoria();
	}
	
	public void cargarCategoria() {
		try {
			this.listarCategoria = categoriaService.listarRecienAgregados();
		} catch (Exception e) {
			
		}
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
	
	
}
