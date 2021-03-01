package com.inventario.serviceImpl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;


import com.inventario.dao.IUsuarioDAO;
import com.inventario.modelo.Usuario;
import com.inventario.service.IUsuarioService;

@Named
public class UsuarioServiceImpl implements IUsuarioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@EJB
	private IUsuarioDAO dao;

	@Override
	public Integer registrar(Usuario usuario) throws Exception {
		return dao.registrar(usuario);
	}

	@Override
	public Integer modificar(Usuario usuario) throws Exception {
		return dao.modificar(usuario);
	}

	@Override
	public Integer eliminar(Usuario usuario) throws Exception {
		return dao.eliminar(usuario);
	}

	@Override
	public List<Usuario> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Usuario listarPorId(Usuario usuario) throws Exception {
		return dao.listarPorId(usuario);
	}

	@Override
	public Usuario iniciarSesion(Usuario usr) {
		return dao.iniciarSesion(usr);
	}


}
