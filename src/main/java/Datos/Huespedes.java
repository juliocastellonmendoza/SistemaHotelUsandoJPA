package Datos;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import views.ReservasView;


@Entity
@Table(name="huespedes")
public class Huespedes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idhuespedes;
	
	private String nombre;
	private String apellido;
	private LocalDate fecha_de_nacimiento;
	private String nacionalidad;
	private String telefono;
	
	@ManyToOne
	@JoinColumn(name="idreserva")
	private Reservas reserva;
	
	public Huespedes() {
	
	}
	
	public Huespedes(Reservas reserva) {
		this.reserva=reserva;
	}
	
	public void agregarItems(String nombre, String apellido, LocalDate fecha_de_nacimiento,
			String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public void agregarItemss(String nombre, String apellido, LocalDate fecha_de_nacimiento,
			String nacionalidad, String telefono,Reservas reserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reserva=reserva;
	}

	public int getIdhuespedes() {
		return idhuespedes;
	}

	public void setIdhuespedes(int idhuespedes) {
		this.idhuespedes = idhuespedes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(LocalDate fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Reservas getReserva() {
		return reserva;
	}

	public void setReserva(Reservas reserva) {
		this.reserva = reserva;
	}
	
	public int getIdreserva() {
		return reserva.getIdreserva();
	}
	
	public void setIdreserva(int id) {
		this.reserva.getIdreserva();
	}
}
