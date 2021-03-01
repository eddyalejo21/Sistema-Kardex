package com.inventario.daoImpl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.inventario.dao.IProductoDAO;
import com.inventario.modelo.Producto;

@Stateless
public class ProductoDAOImpl implements IProductoDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "inventarioPU")
	private EntityManager em;

	@Override
	public Integer registrar(Producto producto) throws Exception {
		em.persist(producto);
		return producto.getIdProducto();
	}

	@Override
	public Integer modificar(Producto producto) throws Exception {
		em.merge(producto);
		return producto.getIdProducto();				
	}

	@Override
	public Integer eliminar(Producto producto) throws Exception {
		em.remove(em.merge(producto));
		return 1;
	}

	@Override
	public List<Producto> listar() throws Exception {
		Query query = em.createQuery("SELECT p FROM Producto p");
		List<Producto> lista = (List<Producto>) query.getResultList();
		return lista;
	}

	@Override
	public Producto listarPorId(Producto producto) throws Exception {
		Query q = em.createQuery("FROM Producto p WHERE p.prodBarcode = ?1");
		q.setParameter(1, producto.getProdBarcode());	
		List<Producto> lista = (List<Producto>) q.getResultList();
		
		return lista != null && !lista.isEmpty() ? lista.get(0) : new Producto();
	}

	@Override
	public List<Producto> listarProducto() {
		Query query = em.createQuery("SELECT p FROM Producto p where p.prodEstado='A' order by p.idProducto desc");
		List<Producto> lista = (List<Producto>) query.getResultList();
		return lista;
	}

	@Override
	public Producto mostrarProductoById(String prodBarcode) {
		System.out.println("Entra a buscar");
		Query q = em.createQuery("SELECT p FROM Producto p WHERE p.prodBarcode = ?1");
		q.setParameter(1, prodBarcode);	
		List<Producto> lista = (List<Producto>) q.getResultList();
		return lista != null && !lista.isEmpty() ? lista.get(0) : new Producto();
	}

}
