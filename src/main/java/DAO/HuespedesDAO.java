package DAO;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import Datos.Huespedes;

public class HuespedesDAO {
	
	private EntityManager em;

	public HuespedesDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Huespedes huespedes) {
		this.em.persist(huespedes);
	}
	
	public void actualizar(Huespedes huespedes) {
		this.em.merge(huespedes);
	}
	
	public void remover(Huespedes huespedes) {
		//huespedes=em.merge(huespedes);
		this.em.remove(huespedes);
	}
	
	public Huespedes consultaPorId(Huespedes h) {
		return em.find(Huespedes.class, h);
	}
	
	public List<Huespedes> consultarTodos(String buscar) {
		
		String jpql = "SELECT H FROM Huespedes AS H WHERE H.idhuespedes like '%"+buscar+"%'order by idhuespedes desc";
		
		return em.createQuery(jpql,Huespedes.class).getResultList();
	}
	
	public List<Huespedes> consultaPorNombre(String nombre) {
		
		String jpql = "SELECT H FROM Huespedes AS H WHERE H.nombre=:nombre"; // si quiero agregar mas parametros es con AND
		
		return em.createQuery(jpql,Huespedes.class).setParameter("nombre",nombre).getResultList();
	}
	
	public List<Huespedes> consultaPorApellido(String apellido) {
		
		String jpql = "SELECT H FROM Huespedes AS H WHERE H.apellido=:apellido";
		
		return em.createQuery(jpql,Huespedes.class).setParameter("apellido",apellido).getResultList();
	}
	
	public List<Huespedes> consultaPorFecha(LocalDate fecha_de_nacimiento) {
		
		String jpql = "SELECT H FROM Huespedes AS H WHERE H.fecha_de_nacimiento=:fecha_de_nacimiento";
		
		return em.createQuery(jpql,Huespedes.class).setParameter("fecha_de_nacimiento",fecha_de_nacimiento).getResultList();
	}
	
	public List<Huespedes> consultaPorNacionalidad(String nacionalidad) {
		
		String jpql = "SELECT H FROM Huespedes AS H WHERE H.nacionalidad=:nacionalidad";
		
		return em.createQuery(jpql,Huespedes.class).setParameter("nacionalidad",nacionalidad).getResultList();
	}
	
	public List<Huespedes> consultaPorTelefono(String telefono) {
		
		String jpql = "SELECT H FROM Huespedes AS H WHERE H.telefono=:telefono";
		
		return em.createQuery(jpql,Huespedes.class).setParameter("telefono",telefono).getResultList();
	}
	
	public List<Huespedes> consultaPorIdreserva(int idreserva) {
		
		String jpql = "SELECT H FROM Producto AS H WHERE H.idreserva.idreserva=:idreserva";
		
		return em.createQuery(jpql,Huespedes.class).setParameter("idreserva",idreserva).getResultList();
	}
	
	public String consultaNombrePorIdreserva(int idreserva) {
		
		String jpql = "SELECT H.nombre FROM Huespedes AS H WHERE H.idreserva=:idreserva";
		
		return em.createQuery(jpql,String.class).setParameter("idreserva",idreserva).getSingleResult();
	}
	
	public int edit(Integer idhuespedes) {
		
		String jpql = "UPDATE Huespedes SET nombre=?,apellido=?,fecha_de_nacimiento=?,nacionalidad=?,telefono=?,"
				+ "idreserva=? WHERE idhuespedes=?";
		
		return em.createQuery(jpql,Integer.class).setParameter("idhuespedes",idhuespedes).getSingleResult();
	}
	
}
