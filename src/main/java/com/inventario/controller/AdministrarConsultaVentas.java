package com.inventario.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.inventario.modelo.DetalleVenta;
import com.inventario.modelo.Venta;
import com.inventario.service.IDetalleVentaService;
import com.inventario.service.IVentaService;

@Named
@ViewScoped
public class AdministrarConsultaVentas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IVentaService ventaService;

	@Inject
	private IDetalleVentaService detalleVentaService;

	private Venta venta;
	private DetalleVenta detalleVenta;
	private List<Venta> listarVenta;
	private List<DetalleVenta> listarDetalleVenta;

	private Date fechaConsulta;
	private Double sumaTotal;

	@PostConstruct
	public void init() {
		this.venta = new Venta();
		this.detalleVenta = new DetalleVenta();
	}

	public void buscarVentaPorFecha() {
		try {
			this.listarDetalleVenta = detalleVentaService.listarVentaPorFecha(fechaConsulta);
			
			sumaTotal = 0.00;

			for (DetalleVenta suma : listarDetalleVenta) {
				sumaTotal += suma.getDetTotal();
			}
			
		} catch (Exception e) {
			Logger.getLogger(AdministrarConsultaVentas.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public DetalleVenta getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(DetalleVenta detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	public List<Venta> getListarVenta() {
		return listarVenta;
	}

	public void setListarVenta(List<Venta> listarVenta) {
		this.listarVenta = listarVenta;
	}

	public List<DetalleVenta> getListarDetalleVenta() {
		return listarDetalleVenta;
	}

	public void setListarDetalleVenta(List<DetalleVenta> listarDetalleVenta) {
		this.listarDetalleVenta = listarDetalleVenta;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public Double getSumaTotal() {
		return sumaTotal;
	}

	public void setSumaTotal(Double sumaTotal) {
		this.sumaTotal = sumaTotal;
	}

}
