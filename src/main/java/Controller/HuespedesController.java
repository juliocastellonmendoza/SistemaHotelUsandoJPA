package Controller;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import DAO.HuespedesDAO;
import DAO.ReservasDAO;
import Datos.Huespedes;
import Datos.Reservas;
import Utils.JPAUtils;

public class HuespedesController {
	
	EntityManager em = JPAUtils.getEntityManager();
	
	HuespedesDAO huespedesDao =  new HuespedesDAO(em);
	ReservasDAO reservasDao = new ReservasDAO(em);
	
	Reservas reserva = new Reservas(LocalDate.parse("2023-04-28"), LocalDate.parse("2023-05-01"), 7.5, "Debito");
	Huespedes huespedes = new Huespedes(reserva);
	
	
//	em.getTransaction().begin();
//	
//	reservasDao.guardar(reserva);
//	
//	huespedesDao.guardar(huespedes);
//	
//	em.getTransaction().commit();
	
	//em.close();

}
