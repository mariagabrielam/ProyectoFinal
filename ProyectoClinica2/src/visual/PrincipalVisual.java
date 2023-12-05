package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Doctor;
import logico.Paciente;
import logico.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class PrincipalVisual extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9104132929861447962L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JMenuItem mnitMiHistorial;
	private JMenuItem mnitHistorialPaciente;
	private JMenu mnAdmin;
	private JMenu mnCita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalVisual frame = new PrincipalVisual(null);
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
	public PrincipalVisual(Usuario user) {
		setVisibleByUser(user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 502);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_2 = new JMenu("Historial Cl\u00EDnico");
		menuBar.add(mnNewMenu_2);
		
		mnitMiHistorial = new JMenuItem("Mi Historial");
		mnitMiHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarHistorial mostrarHistorial = new MostrarHistorial();
				mostrarHistorial.setModal(true);
				mostrarHistorial.setVisible(true);
			}
		});
		mnNewMenu_2.add(mnitMiHistorial);
		
		mnitHistorialPaciente = new JMenuItem("Historial Paciente");
		mnNewMenu_2.add(mnitHistorialPaciente);
		
		mnCita = new JMenu("Cita");
		menuBar.add(mnCita);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Registrar Cita");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCita regCita;
				try {
					regCita = new RegCita();
					regCita.setModal(true);
					regCita.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCita.add(mntmNewMenuItem_6);
		
		mnAdmin = new JMenu("Administraci\u00F3n");
		menuBar.add(mnAdmin);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Crear Usuario");
		mnAdmin.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar Enfermedad");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedad regEnfermedad = new RegEnfermedad();
				regEnfermedad.setModal(true);
				regEnfermedad.setVisible(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar Doctor");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RegDoctor regDoctor = new RegDoctor();
					regDoctor.setModal(true);
					regDoctor.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnAdmin.add(mntmNewMenuItem_3);
		mnAdmin.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Registrar Vacuna");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna regVacuna = new RegVacuna();
				regVacuna.setModal(true);
				regVacuna.setVisible(true);
			}
		});
		mnAdmin.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listar Pacientes");
		mnAdmin.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar Empleados");
		mnAdmin.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Historial Cl\u00EDnica");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarHistorial mostrarHistorial = new MostrarHistorial();
				mostrarHistorial.setModal(true);
				mostrarHistorial.setVisible(true);
			}
		});
		mnAdmin.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Administrar Viviendas");
		mnAdmin.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void setVisibleByUser(Usuario user)
	{
		if(user.getPersona() instanceof Doctor) {
			mnitHistorialPaciente.setVisible(true);
			mnitMiHistorial.setVisible(false);
			mnAdmin.setVisible(false);
			mnCita.setVisible(true);
		}
		else if(user.getPersona() instanceof Paciente) {
			mnitMiHistorial.setVisible(true);
			mnAdmin.setVisible(false);
			mnitHistorialPaciente.setVisible(false);
			mnCita.setVisible(false);
		}
		else {
			mnCita.setVisible(false);
			mnAdmin.setVisible(true);
			mnitMiHistorial.setVisible(false);
		}
	}

}
