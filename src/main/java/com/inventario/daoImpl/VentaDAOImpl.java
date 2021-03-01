package com.inventario.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import com.inventario.dao.IVentaDAO;
import com.inventario.modelo.DetalleVenta;
import com.inventario.modelo.Venta;

@Stateless
public class VentaDAOImpl implements IVentaDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "inventarioPU")
	private EntityManager em;

	@Override
	public Integer registrar(Venta venta) throws Exception {
		em.persist(venta);
		return venta.getIdVenta();
	}

	@Override
	public Integer modificar(Venta venta) throws Exception {
		em.merge(venta);
		return venta.getIdVenta();
	}

	@Override
	public Integer eliminar(Venta venta) throws Exception {
		em.remove(em.merge(venta));
		return 1;
	}

	@Override
	public List<Venta> listar() throws Exception {
		Query query = em.createQuery("SELECT v FROM Venta v");
		List<Venta> lista = (List<Venta>) query.getResultList();
		return lista;
	}

	@Override
	public Venta listarPorId(Venta venta) throws Exception {
		Query q = em.createQuery("FROM Venta v WHERE v.idVenta = ?1");
		q.setParameter(1, venta.getIdVenta());	
		List<Venta> lista = (List<Venta>) q.getResultList();
		return lista != null && !lista.isEmpty() ? lista.get(0) : new Venta();
	}
	
	public void registrarVenta(Venta venta, List<DetalleVenta> listarDetalleVenta) {
		em.persist(venta);
		for (DetalleVenta detalleVenta : listarDetalleVenta) {
			em.persist(detalleVenta);
		}
	}

	

}
