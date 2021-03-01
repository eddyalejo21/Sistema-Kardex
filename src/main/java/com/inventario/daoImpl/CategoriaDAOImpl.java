package com.inventario.daoImpl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.inventario.dao.ICategoriaDAO;
import com.inventario.modelo.Categoria;

@Stateless
public class CategoriaDAOImpl implements ICategoriaDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "inventarioPU")
	private EntityManager em;

	@Override
	public Integer registrar(Categoria categoria) throws Exception {
		em.persist(categoria);
		return categoria.getIdCategoria();
	}

	@Override
	public Integer modificar(Categoria categoria) throws Exception {
		em.merge(categoria);
		return categoria.getIdCategoria();
	}

	@Override
	public Integer eliminar(Categoria categoria) throws Exception {
		em.remove(em.merge(categoria));
		return 1;
	}

	@Override
	public List<Categoria> listar() throws Exception {
		Query query = em.createQuery("SELECT c FROM Categoria c where c.catEstado='A' order by c.idCategoria desc");
		List<Categoria> lista = (List<Categoria>) query.getResultList();
		return lista;
	}

	@Override
	public Categoria listarPorId(Categoria categoria) throws Exception {
		Query q = em.createQuery("FROM Categoria c WHERE c.idCategoria = ?1");
		q.setParameter(1, categoria.getIdCategoria());	
		List<Categoria> lista = (List<Categoria>) q.getResultList();
		
		return lista != null && !lista.isEmpty() ? lista.get(0) : new Categoria();
	}

	@Override
	public List<Categoria> listarRecienAgregados() {
		Query query = em.createQuery("SELECT c FROM Categoria c limit 3");
		List<Categoria> lista = (List<Categoria>) query.getResultList();
		return lista;
	}

}
