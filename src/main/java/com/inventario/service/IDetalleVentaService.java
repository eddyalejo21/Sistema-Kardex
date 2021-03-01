package com.inventario.service;

import java.util.List;

import com.inventario.modelo.DetalleVenta;

public interface IDetalleVentaService extends IService<DetalleVenta> {
	List<DetalleVenta> listarVentaPorFecha(java.util.Date fechaConsulta);
}
