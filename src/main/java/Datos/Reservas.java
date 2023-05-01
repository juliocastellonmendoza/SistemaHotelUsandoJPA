package Datos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import views.ReservasView;

@Entity
@Table(name="reservas")
public class Reservas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idreserva;
	
	private LocalDate fecha_entrada;
	private LocalDate fecha_salida;
	private double valor;
	private String forma_pago;
	
	public Reservas() {
	
	}
	
	public Reservas(int idreserva) {
		this.idreserva = idreserva;
	}
	
	public Reservas(LocalDate fecha_entrada, LocalDate fecha_salida, double valor, String forma_pago) {
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}
	
	public LocalDate getFecha_entrada() {
		return fecha_entrada;
	}
	public void setFecha_entrada(LocalDate fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}
	public LocalDate getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(LocalDate fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public int getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}
	
}
