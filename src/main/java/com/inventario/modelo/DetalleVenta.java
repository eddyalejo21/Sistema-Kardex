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
@Table(name = "detalle_venta")
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalleVenta;
	
	@ManyToOne
	@JoinColumn (name = "idVenta", nullable = false)
	private Venta venta;
	
	@Column(name = "detProducto", nullable = false, length = 70)
	private String detProducto;
	
	@Column(name = "detValorUnitario", nullable = false)
	private Double detValorUnitario;
	
	@Column(name = "detCantidad", nullable = false)
	private Integer detCantidad;
	
	@Column(name = "detTotal", nullable = false)
	private Double detTotal;

	public int getIdDetalleVenta() {
		return idDetalleVenta;
	}

	public void setIdDetalleVenta(int idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public String getDetProducto() {
		return detProducto;
	}

	public void setDetProducto(String detProducto) {
		this.detProducto = detProducto;
	}

	public Double getDetValorUnitario() {
		return detValorUnitario;
	}

	public void setDetValorUnitario(Double detValorUnitario) {
		this.detValorUnitario = detValorUnitario;
	}

	public Integer getDetCantidad() {
		return detCantidad;
	}

	public void setDetCantidad(Integer detCantidad) {
		this.detCantidad = detCantidad;
	}

	public Double getDetTotal() {
		return detTotal;
	}

	public void setDetTotal(Double detTotal) {
		this.detTotal = detTotal;
	}
	
	
}
