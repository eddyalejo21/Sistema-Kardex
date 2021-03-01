package com.inventario.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.inventario.dao.IDetalleVentaDAO;
import com.inventario.modelo.DetalleVenta;

@Stateless
public class DetalleVentaDAOImpl implements IDetalleVentaDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "inventarioPU")
	private EntityManager em;

	@Override
	public Integer registrar(DetalleVenta detalle) throws Exception {
		em.persist(detalle);
		return detalle.getIdDetalleVenta();
	}

	@Override
	public Integer modificar(DetalleVenta categoria) throws Exception {
		em.merge(categoria);
		return categoria.getIdDetalleVenta();
	}

	@Override
	public Integer eliminar(DetalleVenta categoria) throws Exception {
		em.remove(em.merge(categoria));
		return 1;
	}

	@Override
	public List<DetalleVenta> listar() throws Exception {
		Query query = em.createQuery("SELECT d FROM DetalleVenta d");
		List<DetalleVenta> lista = (List<DetalleVenta>) query.getResultList();
		return lista;
	}

	@Override
	public DetalleVenta listarPorId(DetalleVenta producto) throws Exception {
		Query q = em.createQuery("FROM DetalleVenta d WHERE d.idDetalleVenta = ?1");
		q.setParameter(1, producto.getIdDetalleVenta());	
		List<DetalleVenta> lista = (List<DetalleVenta>) q.getResultList();
		
		return lista != null && !lista.isEmpty() ? lista.get(0) : new DetalleVenta();
	}
	
	@Override
	public List<DetalleVenta> listarVentaPorFecha(java.util.Date fechaConsulta) {
		List<DetalleVenta> listarVentas = new ArrayList<DetalleVenta>();
		try {
			Query query = em.createQuery("SELECT d FROM DetalleVenta d "
					+ "WHERE cast(d.venta.veFecha as date) = ?1 "
					+ "order by d.venta.veFecha desc");
			
			System.out.println(query);
			query.setParameter(1, fechaConsulta, TemporalType.DATE);
			System.out.println("consulto exitosamente");
			listarVentas = (List<DetalleVenta>) query.getResultList();
		} catch (Exception e) {
			throw e;
		}
		return listarVentas;
	
	}

}
