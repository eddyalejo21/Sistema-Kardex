package com.inventario.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.inventario.dao.IDetalleVentaDAO;
import com.inventario.modelo.DetalleVenta;
import com.inventario.service.IDetalleVentaService;

@Named
public class DetalleVentaServiceImpl implements IDetalleVentaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IDetalleVentaDAO dao;

	@Override
	public Integer registrar(DetalleVenta detalle) throws Exception {
		return dao.registrar(detalle);
	}

	@Override
	public Integer modificar(DetalleVenta detalle) throws Exception {
		return dao.modificar(detalle);
	}

	@Override
	public Integer eliminar(DetalleVenta detalle) throws Exception {
		return dao.eliminar(detalle);
	}

	@Override
	public List<DetalleVenta> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public DetalleVenta listarPorId(DetalleVenta detalle) throws Exception {
		return dao.listarPorId(detalle);
	}
	
	@Override
	public List<DetalleVenta> listarVentaPorFecha(java.util.Date fechaConsulta) {
		return dao.listarVentaPorFecha(fechaConsulta);
	}
	

}
