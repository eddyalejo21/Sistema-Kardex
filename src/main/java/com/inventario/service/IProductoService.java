package com.inventario.service;

import java.util.List;

import com.inventario.modelo.Producto;

public interface IProductoService extends IService<Producto>{
	List<Producto> listarProducto();
	Producto mostrarProductoById(String prodBarcode);
}
