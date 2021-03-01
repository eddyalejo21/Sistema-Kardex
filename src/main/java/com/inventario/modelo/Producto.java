package com.inventario.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	@Column(name = "prodBarcode",nullable = true, length = 60)
	private String prodBarcode;
	
	@Column(name = "prodDescripcion", nullable = false, length = 100)
	private String prodDescripcion;
	
	@ManyToOne
	@JoinColumn(name = "idCategoria", nullable = false)
	private Categoria categoria;
	
	@Column(name = "prodMarca", nullable = true, length = 30)
	private String prodMarca;
	
	@Column(name = "prodPrecio", nullable = false)
	private Double prodPrecio;
	
	@Column(name = "prodCosto", nullable = false)
	private Double prodCosto;
	
	@Column(name = "prodStock", nullable = false)
	private Integer prodStock;
	
	@Column(name = "prodStockMin", nullable = false)
	private Integer prodStockMin;
	
	@Column(name = "prodObservacion", nullable = true, length = 60)
	private String prodObservacion;
	
	@Column(name = "prodEstado", nullable = false, length = 1)
	private String prodEstado;
	
	public Producto() {
		this.categoria = new Categoria();
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getProdBarcode() {
		return prodBarcode;
	}
	public void setProdBarcode(String prodBarcode) {
		this.prodBarcode = prodBarcode;
	}
	public String getProdDescripcion() {
		return prodDescripcion;
	}
	public void setProdDescripcion(String prodDescripcion) {
		this.prodDescripcion = prodDescripcion;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getProdMarca() {
		return prodMarca;
	}
	public void setProdMarca(String prodMarca) {
		this.prodMarca = prodMarca;
	}
	public Double getProdPrecio() {
		return prodPrecio;
	}
	public void setProdPrecio(Double prodPrecio) {
		this.prodPrecio = prodPrecio;
	}
	public Double getProdCosto() {
		return prodCosto;
	}
	public void setProdCosto(Double prodCosto) {
		this.prodCosto = prodCosto;
	}
	public Integer getProdStock() {
		return prodStock;
	}
	public void setProdStock(Integer prodStock) {
		this.prodStock = prodStock;
	}
	public Integer getProdStockMin() {
		return prodStockMin;
	}
	public void setProdStockMin(Integer prodStockMin) {
		this.prodStockMin = prodStockMin;
	}
	public String getProdObservacion() {
		return prodObservacion;
	}
	public void setProdObservacion(String prodObservacion) {
		this.prodObservacion = prodObservacion;
	}
	public String getProdEstado() {
		return prodEstado;
	}
	public void setProdEstado(String prodEstado) {
		this.prodEstado = prodEstado;
	}
	
}
