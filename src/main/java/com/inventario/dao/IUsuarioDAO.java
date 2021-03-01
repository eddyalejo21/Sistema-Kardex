package com.inventario.dao;

import javax.ejb.Local;

import com.inventario.modelo.Usuario;

@Local
public interface IUsuarioDAO extends ICRUD<Usuario>{
	Usuario iniciarSesion(Usuario usr);
}
