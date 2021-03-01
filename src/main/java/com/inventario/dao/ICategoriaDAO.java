package com.inventario.dao;

import java.util.List;

import javax.ejb.Local;

import com.inventario.modelo.Categoria;

@Local
public interface ICategoriaDAO extends ICRUD<Categoria>{
	List<Categoria> listarRecienAgregados();
}
