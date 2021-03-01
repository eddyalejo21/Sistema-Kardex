package com.inventario.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.inventario.modelo.DetalleVenta;
import com.inventario.modelo.Producto;
import com.inventario.modelo.Venta;
import com.inventario.service.IDetalleVentaService;
import com.inventario.service.IProductoService;
import com.inventario.service.IVentaService;

@Named
@ViewScoped
public class AdministrarVentaControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private IVentaService ventaService;

	@Inject
	private IProductoService productoService;

	@Inject
	private IDetalleVentaService detalleVentaService;

	private Venta venta;
	private Producto producto;
	private DetalleVenta detalleVenta;
	private List<Venta> listarVenta;
	private List<Producto> mostrarProducto;
	private List<DetalleVenta> listarDetalleVenta;
	private String codigoProducto;
	private Integer cantidad;
	private Double totalVenta;
	private Double totalFactura;
	private Double valorAnteriorDeCompra;
	private Date horaFinal;

	@PostConstruct
	public void init() {
		this.venta = new Venta();
		this.producto = new Producto();
		this.detalleVenta = new DetalleVenta();
		this.mostrarProducto = new ArrayList<Producto>();
		this.listarDetalleVenta = new ArrayList<>();
		this.valorAnteriorDeCompra = 0.00;
	}

	public void cargarProductoById() {
		try {

			this.producto = productoService.mostrarProductoById(producto.getProdBarcode());
			if (producto.getProdBarcode() == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "ALERTA", "El código no está asignado"));
			} else {
				this.cantidad = 1;
				this.totalVenta = producto.getProdPrecio() * this.getCantidad();
			}

		} catch (Exception e) {
			Logger.getLogger(AdministrarVentaControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void calcularTotalDetalle() {
		
		this.totalVenta = producto.getProdPrecio() * this.getCantidad();
		DecimalFormat formato = new DecimalFormat("0.00");
		formato.format(totalVenta);
		System.out.println(totalVenta);
		System.out.println(formato.format(totalVenta));
		
	}

	public void agregarProducto() {
		try {
			this.detalleVenta.setDetProducto(producto.getProdDescripcion());
			this.detalleVenta.setDetCantidad(cantidad);
			this.detalleVenta.setDetValorUnitario(producto.getProdPrecio());
			this.detalleVenta.setDetTotal(totalVenta);
			this.totalFactura = valorAnteriorDeCompra + totalVenta;
			this.valorAnteriorDeCompra = this.totalFactura;
			System.out.println(valorAnteriorDeCompra);
			this.listarDetalleVenta.add(detalleVenta);
			this.detalleVenta = new DetalleVenta();

		} catch (Exception e) {
			Logger.getLogger(AdministrarVentaControlador.class.getName()).log(Level.SEVERE, null, e);
		}

	}
	
	public void colocarFecha() {
		Date horaSalida = new Date();
		setHoraFinal(horaSalida);
	}

	@Transactional
	public void guardarFactura() {
		try {
			
			Date horaSalida = new Date();
			setHoraFinal(horaSalida);

			this.venta.setVenTotal(this.totalFactura);
			this.venta.setVeFecha(this.horaFinal);
			this.detalleVenta.setVenta(this.venta);
			this.ventaService.registrar(venta);
			
			for (DetalleVenta detalleVenta : listarDetalleVenta) {
//				detalleVenta.setDetProducto(this.producto);
//				detalleVenta.setDetCantidad(this.cantidad);
//				detalleVenta.setDetValorUnitario(this.);
//				detalleVenta.setDetTotal(this.totalVenta);
				System.out.println(detalleVenta.getDetProducto() +" --> "+ detalleVenta.getDetCantidad());
				detalleVenta.setVenta(this.venta);
				detalleVentaService.registrar(detalleVenta);
			}
			
			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Venta Exitosa"));
			this.limpiarDialogoNuevo();
			this.init();
			
		} catch (Exception e) {
			Logger.getLogger(AdministrarVentaControlador.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public void limpiarDialogoNuevo() {
		this.producto = new Producto();
		this.cantidad = null;
		this.totalVenta = null;
		this.totalFactura = 0.00;
		this.codigoProducto = null;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public List<Venta> getListarVenta() {
		return listarVenta;
	}

	public void setListarVenta(List<Venta> listarVenta) {
		this.listarVenta = listarVenta;
	}

	public String getcodigoProducto() {
		return codigoProducto;
	}

	public void setcodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getMostrarProducto() {
		return mostrarProducto;
	}

	public void setMostrarProducto(List<Producto> mostrarProducto) {
		this.mostrarProducto = mostrarProducto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotalVenta() {
		return totalVenta;
	}

	public void setTotalVenta(Double totalVenta) {
		this.totalVenta = totalVenta;
	}

	public DetalleVenta getDetalleVenta() {
		return detalleVenta;
	}

	public void setDetalleVenta(DetalleVenta detalleVenta) {
		this.detalleVenta = detalleVenta;
	}

	public List<DetalleVenta> getListarDetalleVenta() {
		return listarDetalleVenta;
	}

	public void setListarDetalleVenta(List<DetalleVenta> listarDetalleVenta) {
		this.listarDetalleVenta = listarDetalleVenta;
	}

	public Double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(Double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public Double getValorAnteriorDeCompra() {
		return valorAnteriorDeCompra;
	}

	public void setValorAnteriorDeCompra(Double valorAnteriorDeCompra) {
		this.valorAnteriorDeCompra = valorAnteriorDeCompra;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

}
