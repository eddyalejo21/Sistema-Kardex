package com.inventario.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.inventario.dao.ICategoriaDAO;
import com.inventario.modelo.Categoria;
import com.inventario.service.ICategoriaService;

@Named
public class CategoriaServiceImpl implements ICategoriaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ICategoriaDAO dao;

	@Override
	public Integer registrar(Categoria categoria) throws Exception {
		return dao.registrar(categoria);
	}

	@Override
	public Integer modificar(Categoria categoria) throws Exception {
		return dao.modificar(categoria);
	}

	@Override
	public Integer eliminar(Categoria categoria) throws Exception {
		return dao.eliminar(categoria);
	}

	@Override
	public List<Categoria> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Categoria listarPorId(Categoria categoria) throws Exception {
		return dao.listarPorId(categoria);
	}

	@Override
	public List<Categoria> listarRecienAgregados() {
		return dao.listarRecienAgregados();
	}
	
}
