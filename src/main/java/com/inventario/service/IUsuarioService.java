package com.inventario.service;

import com.inventario.modelo.Usuario;

public interface IUsuarioService extends IService<Usuario>{
	Usuario iniciarSesion(Usuario usr);
}
