package Pruebas;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import DAO.HuespedesDAO;
import DAO.ReservasDAO;
import Datos.Huespedes;
import Datos.Reservas;
import Utils.JPAUtils;

public class PruebaConexiónMysql {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtils.getEntityManager();
		
		HuespedesDAO huespedesDao =  new HuespedesDAO(em);
		ReservasDAO reservasDao = new ReservasDAO(em);
		
		Reservas reserva = new Reservas(LocalDate.parse("2023-04-28"), LocalDate.parse("2023-05-01"), 7.5, "Debito");
		Huespedes huespedes = new Huespedes(reserva);
		huespedes.agregarItems("Leonel", "Messi", LocalDate.parse("1989-06-16"), "Argentina", "30567892435");
		
		
		em.getTransaction().begin();
		
		reservasDao.guardar(reserva);
		
		huespedesDao.guardar(huespedes);
		
		em.getTransaction().commit();
		
		//em.close();
		
	}
}
