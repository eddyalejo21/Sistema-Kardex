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
import com.inventario.modelo.Producto;
import com.inventario.service.ICategoriaService;
import com.inventario.service.IProductoService;

@Named
@ViewScoped
public class AdministrarProductoControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IProductoService productoService;

	@Inject
	private ICategoriaService categoriaService;

	private Producto producto;
	private Producto productoSeleccion;
	private Categoria categoria;
	private Categoria categoriaSeleccion;
	private List<Producto> listarProducto;
	private List<Categoria> listarCategoria;

	@PostConstruct
	public void init() {
		this.producto = new Producto();
		this.categoria = new Categoria();
		this.cargarCategoria();
		this.cargarProducto();
	}

	public void cargarProducto() {
		try {
			this.listarProducto = productoService.listarProducto();
		} catch (Exception e) {
			Logger.getLogger(AdministrarProductoControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void cargarCategoria() {
		try {
			this.listarCategoria = categoriaService.listar();
		} catch (Exception e) {
			Logger.getLogger(AdministrarProductoControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void seleccionarProducto() {
		this.producto = this.productoSeleccion;
		System.out.println(producto.getIdProducto());
	}

	public void mostrarData(Producto producto) {
		this.producto = producto;
	}

	public void guardarRegistroProducto() throws Exception {
		producto.setProdEstado("A");
		producto.setProdBarcode(producto.getProdBarcode().toUpperCase());
		producto.setProdDescripcion(producto.getProdDescripcion().toUpperCase());
		producto.setProdMarca(producto.getProdMarca().toUpperCase());
		producto.setProdObservacion(producto.getProdObservacion().toUpperCase());
		productoService.registrar(producto);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro guardado"));
		this.cargarProducto();
		this.limpiarDialogoNuevo();
	}

	public void modificarProducto() {
		try {
			if (producto.getIdProducto() != null) {
				producto.setProdBarcode(producto.getProdBarcode().toUpperCase());
				producto.setProdDescripcion(producto.getProdDescripcion().toUpperCase());
				producto.setProdMarca(producto.getProdMarca().toUpperCase());
				producto.setProdObservacion(producto.getProdObservacion().toUpperCase());
				productoService.modificar(producto);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro modificado"));
				this.cargarProducto();
				this.limpiarDialogoNuevo();
			}
		} catch (Exception e) {
			Logger.getLogger(AdministrarProductoControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void eliminarProducto() {
		try {
			if (producto.getIdProducto() != null) {
				producto.setProdEstado("D");
				productoService.modificar(producto);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro eliminado"));
				this.init();
				System.out.println("elimino");
			} else {
				this.producto = new Producto();
				System.out.println("no elimina");
			}
		} catch (Exception e) {
			System.out.println("error");
			Logger.getLogger(AdministrarCategoriaControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void limpiarDialogoNuevo() {
		this.producto = new Producto();
		this.categoria = new Categoria();
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Producto getProductoSeleccion() {
		return productoSeleccion;
	}

	public void setProductoSeleccion(Producto productoSeleccion) {
		this.productoSeleccion = productoSeleccion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria getCategoriaSeleccion() {
		return categoriaSeleccion;
	}

	public void setCategoriaSeleccion(Categoria categoriaSeleccion) {
		this.categoriaSeleccion = categoriaSeleccion;
	}

	public List<Producto> getListarProducto() {
		return listarProducto;
	}

	public void setListarProducto(List<Producto> listarProducto) {
		this.listarProducto = listarProducto;
	}

	public List<Categoria> getListarCategoria() {
		return listarCategoria;
	}

	public void setListarCategoria(List<Categoria> listarCategoria) {
		this.listarCategoria = listarCategoria;
	}

}
