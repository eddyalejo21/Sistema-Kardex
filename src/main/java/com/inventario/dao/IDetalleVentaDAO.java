package com.inventario.dao;

import java.util.List;

import javax.ejb.Local;

import com.inventario.modelo.DetalleVenta;

@Local
public interface IDetalleVentaDAO extends ICRUD<DetalleVenta> {
	List<DetalleVenta> listarVentaPorFecha(java.util.Date fechaConsulta);
}
