package DAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import Datos.Reservas;

public class ReservasDAO {
	
	private EntityManager em;

	public ReservasDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Reservas reserva) {
		this.em.persist(reserva);
	}
	
	public void actualizar(Reservas reserva) {
		this.em.merge(reserva);
	}
	
	public void remover(Reservas reserva) {
		reserva=this.em.merge(reserva);
		this.em.remove(reserva);
	}
	
	public Reservas consultaPorId(int idreserva) {
		return em.find(Reservas.class, idreserva);
	}
	
	public List<Reservas> consultarTodos(String buscar) {
		
		String jpql = "SELECT R FROM Reservas AS R WHERE R.idreserva like '%"+buscar+"%'order by idreserva desc";
		
		return em.createQuery(jpql,Reservas.class).getResultList();
	}
	
	public List<Reservas> consultaPorFechaEntrada(LocalDate fecha_entrada) {
		
		String jpql = "SELECT R FROM Reservas AS R WHERE R.fecha_entrada=:fecha_entrada"; // si quiero agregar mas parametros es con AND
		
		return em.createQuery(jpql,Reservas.class).setParameter("fecha_entrada",fecha_entrada).getResultList();
	}
	
	public List<Reservas> consultaPorFechaSalida(LocalDate fecha_salida) {
		
		String jpql = "SELECT R FROM Reservas AS R WHERE R.fecha_salida=:fecha_salida"; // si quiero agregar mas parametros es con AND
		
		return em.createQuery(jpql,Reservas.class).setParameter("fecha_salida",fecha_salida).getResultList();
	}
	
	public List<Reservas> consultaPorFormaPago(String forma_pago) {
		
		String jpql = "SELECT R FROM Reservas AS R WHERE R.forma_pago=:forma_pago";
		
		return em.createQuery(jpql,Reservas.class).setParameter("forma_pago",forma_pago).getResultList();
	}
	
	public double consultaPrecioPorIdreserva(int idreserva) {
		
		String jpql = "SELECT R.valor FROM Reservas AS R WHERE R.idreserva=:idreserva";
		
		return em.createQuery(jpql,double.class).setParameter("idreserva",idreserva).getSingleResult();
	}
	
	public int consultaPrecioPorIdreservaa(int idreserva) {
		
		String jpql = "SELECT producto.nombre, SUM(item.cantidad), MAX(pedido.fecha) "
				+ "FROM Pedido pedido JOIN pedido.items item JOIN item.producto producto "
				+ "GROUP BY producto.nombre ORDER BY item.cantidad DESC";
		
		return em.createQuery(jpql,int.class).setParameter("idreserva",idreserva).getSingleResult();
	}
	
	public int edit(int idreserva) {
		
		String jpql = "UPDATE Reservas SET fecha_entrada=?,fecha_salida=?,valor=?,"
				+ "forma_pago=? WHERE idreserva=?";
		
		return em.createQuery(jpql,int.class).setParameter("idreserva", idreserva).getSingleResult();
	}
	
	
	
	
}
