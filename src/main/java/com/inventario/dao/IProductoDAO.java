package com.inventario.dao;

import java.util.List;

import javax.ejb.Local;

import com.inventario.modelo.Producto;

@Local
public interface IProductoDAO extends ICRUD<Producto>{
	List<Producto> listarProducto();
	Producto mostrarProductoById(String prodBarcode);
}
