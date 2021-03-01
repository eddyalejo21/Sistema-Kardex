package com.inventario.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.inventario.dao.IProductoDAO;
import com.inventario.modelo.Producto;
import com.inventario.service.IProductoService;

@Named
public class ProductoServiceImpl implements IProductoService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IProductoDAO dao;
	
	@Override
	public Integer registrar(Producto producto) throws Exception {
		return dao.registrar(producto);
	}

	@Override
	public Integer modificar(Producto producto) throws Exception {
		return dao.modificar(producto);
	}

	@Override
	public Integer eliminar(Producto producto) throws Exception {
		return dao.eliminar(producto);
	}

	@Override
	public List<Producto> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Producto listarPorId(Producto producto) throws Exception {
		return dao.listarPorId(producto);
	}

	@Override
	public List<Producto> listarProducto() {
		return dao.listarProducto();
	}

	@Override
	public Producto mostrarProductoById(String prodBarcode) {
		return dao.mostrarProductoById(prodBarcode);
	}
	

}
