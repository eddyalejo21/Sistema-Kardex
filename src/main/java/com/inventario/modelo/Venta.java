package com.inventario.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "venta")
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;
		
	@Column(name = "venTotal", nullable = true)
	private double venTotal;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "venFecha", nullable = true )
	private Date veFecha;
	
	@ManyToOne
	@JoinColumn (name = "idUsuario", nullable = true)
	private Usuario usuario;
	
	@OneToMany(mappedBy = "venta",  cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<DetalleVenta> detalleVenta;

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public double getVenTotal() {
		return venTotal;
	}

	public void setVenTotal(double venTotal) {
		this.venTotal = venTotal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getVeFecha() {
		return veFecha;
	}

	public void setVeFecha(Date veFecha) {
		this.veFecha = veFecha;
	}
	
	

}
