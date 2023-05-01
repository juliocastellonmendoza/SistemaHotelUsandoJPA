package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.google.protobuf.TextFormat.ParseException;

import DAO.HuespedesDAO;
import DAO.ReservasDAO;
import Datos.Huespedes;
import Datos.Reservas;
import Utils.JPAUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.persistence.EntityManager;
import javax.sound.sampled.ReverbType;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private HuespedesDAO hdao;
	private ReservasDAO rdao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		
		EntityManager em = JPAUtils.getEntityManager();
		
		this.hdao = new HuespedesDAO(em);
		this.rdao = new ReservasDAO(em);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
				
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		final JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		final JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tbReservas.isEnabled()) {
					mostrarReservas(txtBuscar.getText());
				}
				if(tbHuespedes.isEnabled()) {
					mostrarHuespedes(txtBuscar.getText());
				}

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tbReservas.isFocusOwner()) {
					
					editarReservas();
					modelo.getDataVector().clear();
					mostrarReservas("");
				}
				
				if(tbHuespedes.isFocusOwner()) {
					editarHuespedes();
					modeloHuesped.getDataVector().clear();
					mostrarHuespedes("");
				}
			}
				
		});
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(tbReservas.isFocusOwner()) {
					eliminarReservas();
					modelo.getDataVector().clear();
					mostrarReservas("");
				}
				
				if(tbHuespedes.isFocusOwner()) {
					eliminarHuedpedes();
					modeloHuesped.getDataVector().clear();
					mostrarHuespedes("");
				}
				
			}
		});
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
	public void mostrarHuespedes(String buscar) {
		
		Huespedes h = new Huespedes();
		
		modeloHuesped.getDataVector().clear();
		
		List<Huespedes> huespedes = this.hdao.consultarTodos(buscar);
		
		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getIdhuespedes(),huesped.getNombre(),
				huesped.getApellido(),huesped.getFecha_de_nacimiento(),huesped.getNacionalidad(),huesped.getTelefono(),
				huesped.getIdreserva() }));
	}
	
	public void mostrarReservas(String buscar) {
		
		modelo.getDataVector().clear();
		
		List<Reservas> reservas = this.rdao.consultarTodos(buscar);
		
		reservas.forEach(reserva -> modelo.addRow(new Object[] { reserva.getIdreserva(), reserva.getFecha_entrada(),
				reserva.getFecha_salida(), reserva.getValor(), reserva.getForma_pago()}));	
	}
	
	public boolean tieneFilaElegidaHuespedes() {
		//return tbHuespedes.getRowCount()==0 || tbHuespedes.getColumnCount()==0;
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }
	
	public void editarHuespedes() {
		
		if (tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item editar huespedes 1");
            return;    
        }
		
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
        	
        	Reservas r = new Reservas();
        	Huespedes h = new Huespedes();
        	EntityManager em = JPAUtils.getEntityManager();
        	
        	Integer idhuespedes=Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
    		String nombre=modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
    		String apellido=modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
    		
    		LocalDate fn = LocalDate.parse(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
    		
    		String nacionalidad=modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
    		String telefono=modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
    		Integer idreserva=Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
    		h=em.find(Huespedes.class, idhuespedes);
    		r=em.find(Reservas.class, idreserva);
    		if(h!=null) {
	    		h.setIdhuespedes(idhuespedes);
	    		h.setNombre(nombre);
	    		h.setApellido(apellido);
	    		h.setFecha_de_nacimiento(fn);
	    		h.setNacionalidad(nacionalidad);
	    		h.setTelefono(telefono);
	    		h.setIdreserva(r.getIdreserva());
	    		
	    		em.getTransaction().begin();
	    		
	    		hdao.actualizar(h);
	    		//em.merge(r);
	    		
	    		em.getTransaction().commit();
	    		em.close();
    		}
    		JOptionPane.showMessageDialog(this, String.format("item modificado con exito!"));
            
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item editar huespedes 2"));
	}
	
	public boolean tieneFilaElegidaReservas() {
        return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
    }
	
	public void editarReservas() {
		
		if (tieneFilaElegidaReservas()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item editar reservas 1");
            return;
        }
		
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
		
		
        	Reservas r = new Reservas();
        	EntityManager em = JPAUtils.getEntityManager();
        	
        	Integer idreserva=Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
    		
    		LocalDate fi = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
    		
    		LocalDate ff = LocalDate.parse(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
    		
    		Double valor=Double.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString());
    				
    		String forma_pago=modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
    		
    		r=em.find(Reservas.class, idreserva);
    		if(r!=null) {
	    		r.setIdreserva(idreserva);
	    		r.setFecha_entrada(fi);
	    		r.setFecha_salida(ff);
	    		r.setValor(valor);
	    		r.setForma_pago(forma_pago);
	    		
	    		em.getTransaction().begin();
	    		
	    		rdao.actualizar(r);
	    		//em.merge(r);
	    		
	    		em.getTransaction().commit();
	    		em.close();
    		}
            
            JOptionPane.showMessageDialog(this, String.format("item modificado con exito!"));
            
		
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item editar reservas 2"));
		
	}
	
	private void eliminarHuedpedes() {
		
		
        if (tieneFilaElegidaHuespedes()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item eliminar huespedes 1");
            return;
        }

        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
        
        

                	Huespedes h = new Huespedes();
                	EntityManager em = JPAUtils.getEntityManager();
                	
                	Integer idhuespedes=Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());

                    
                    h=em.find(Huespedes.class, idhuespedes);

            		if(h!=null) {
        	    		h.setIdhuespedes(idhuespedes);
        	    		
        	    		em.getTransaction().begin();
        	    		
        	    		em.remove(h);
        	    		//hdao.remover(h);
        	    		//em.remove(h);
        	    		
        	    		em.getTransaction().commit();
        	    		em.close();
            		}

                    JOptionPane.showMessageDialog(this,String.format("Item eliminado con éxito!"));
                    
        
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item eliminar huespedes 2"));
    }
	
	private void eliminarReservas() {
		
		
        if (tieneFilaElegidaReservas()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item eliminar reserva 1");
            return;
        }

        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
                .ifPresentOrElse(fila -> {
        
        
                	
                	Reservas r = new Reservas();
                	EntityManager em = JPAUtils.getEntityManager();
                	
                	Integer idreserva=Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
            		
            		
            		r=em.find(Reservas.class, idreserva);
            		if(r!=null) {
        	    		r.setIdreserva(idreserva);
        	    		
        	    		em.getTransaction().begin();
        	    		
        	    		//rdao.remover(r);
        	    		em.remove(r);
        	    		
        	    		em.getTransaction().commit();
        	    		em.close();
            		}

                    JOptionPane.showMessageDialog(this,String.format("Item eliminado con éxito!"));
        
                }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item eliminar reserva 2"));
    }
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
