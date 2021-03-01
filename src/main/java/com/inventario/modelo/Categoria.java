package com.inventario.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategoria;
	
	@Column(name = "catNombre", nullable = false, length = 25)
	private String catNombre;
	
	@Column(name = "catEstado", nullable = false, length = 1)
	private String catEstado;
	
	@OneToMany(mappedBy = "categoria", targetEntity = Producto.class)
	private List<Producto> producto = new ArrayList<Producto>();
	
	public Categoria() {
		
	}
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getCatNombre() {
		return catNombre;
	}
	public void setCatNombre(String catNombre) {
		this.catNombre = catNombre;
	}

	public List<Producto> getProducto() {
		return producto;
	}

	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}

	public String getCatEstado() {
		return catEstado;
	}

	public void setCatEstado(String catEstado) {
		this.catEstado = catEstado;
	}
	
	
}
