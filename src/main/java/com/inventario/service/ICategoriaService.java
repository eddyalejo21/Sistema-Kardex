package com.inventario.service;

import java.util.List;

import com.inventario.modelo.Categoria;

public interface ICategoriaService extends IService<Categoria> {
	List<Categoria> listarRecienAgregados();
}
