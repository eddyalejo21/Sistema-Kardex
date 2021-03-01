package com.inventario.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.inventario.modelo.DetalleVenta;
import com.inventario.modelo.Venta;

@Local
public interface IVentaDAO extends ICRUD<Venta> {
	
}
