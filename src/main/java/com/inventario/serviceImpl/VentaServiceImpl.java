package com.inventario.serviceImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.inventario.dao.IVentaDAO;
import com.inventario.modelo.DetalleVenta;
import com.inventario.modelo.Venta;
import com.inventario.service.IVentaService;

@Named
public class VentaServiceImpl implements IVentaService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IVentaDAO dao;
	
	@Override
	public Integer registrar(Venta venta) throws Exception {
		return dao.registrar(venta);
	}

	@Override
	public Integer modificar(Venta venta) throws Exception {
		return dao.modificar(venta);
	}

	@Override
	public Integer eliminar(Venta venta) throws Exception {
		return dao.eliminar(venta);
	}

	@Override
	public List<Venta> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Venta listarPorId(Venta venta) throws Exception {
		return dao.listarPorId(venta);
	}

	

	

}
