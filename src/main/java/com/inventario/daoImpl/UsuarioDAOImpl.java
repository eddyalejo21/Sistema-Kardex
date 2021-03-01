package com.inventario.daoImpl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;

import com.inventario.dao.IUsuarioDAO;
import com.inventario.modelo.Usuario;
import com.inventario.util.IniciarSesionController;


@Stateless
public class UsuarioDAOImpl implements IUsuarioDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "inventarioPU")
	private EntityManager em;

	@Override
	public Integer registrar(Usuario usuario) throws Exception {
		em.persist(usuario);
		return usuario.getIdUsuario();
	}

	@Override
	public Integer modificar(Usuario usuario) throws Exception {
		em.merge(usuario);
		return usuario.getIdUsuario();
	}

	@Override
	public Integer eliminar(Usuario usuario) throws Exception {
		em.remove(em.merge(usuario));
		return 1;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		Query query = em.createQuery("SELECT u FROM Usuario u where u.usEstado='A' order by idUsuario desc");
		List<Usuario> lista = (List<Usuario>) query.getResultList();
		return lista;
	}

	@Override
	public Usuario listarPorId(Usuario usuario) throws Exception {
		Query q = em.createQuery("FROM Usuario u WHERE u.idUsuario = ?1");
		q.setParameter(1, usuario.getIdUsuario());	
		List<Usuario> lista = (List<Usuario>) q.getResultList();
		return lista != null && !lista.isEmpty() ? lista.get(0) : new Usuario();
	}

	@Override
	public Usuario iniciarSesion(Usuario usr) {
		Usuario usuario = null;
		String consulta;
		String encriptar = usr.getUsclave();
		String encriptado = DigestUtils.md5Hex(encriptar);

		try {
			consulta = "FROM Usuario u where u.usUsuario = ?1 and u.usclave = ?2";
			Query query = em.createQuery(consulta);
			query.setParameter(1, usr.getUsUsuario().toUpperCase());
			query.setParameter(2, encriptado);

			List<Usuario> lista = query.getResultList();
			if (!lista.isEmpty()) {
				usuario = lista.get(0);
			}
		} catch (Exception e) {
			Logger.getLogger(IniciarSesionController.class.getName()).log(
			Level.SEVERE, null, e);
		}

		return usuario;
	}

	
}
