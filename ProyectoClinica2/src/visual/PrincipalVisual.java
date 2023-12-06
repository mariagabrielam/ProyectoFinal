package visual;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cita;
import logico.Enfermedad;
import logico.Hospital;
import logico.Paciente;
import logico.Vacuna;

public class PrincipalVisual extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9104132929861447962L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JMenuItem mnitHistorialPaciente;
	private JMenu mnAdmin;
	private JMenu mnCita;
	private JMenuItem mnitHistorialClinica;
	private JTable tblCitas;
	private static DefaultTableModel model;
	private static Object[] row;
	private JTextField txtCasos;
	private JTextField txtContagiados;
	private JTextField txtVacunados;
	private JTextField txtPorVac;
	private Enfermedad enfermedad = null;
	private Vacuna vacuna = null;
	private JComboBox<String> cbxVacuna;
	private JComboBox<String> cbxEnfermedades;
	private JMenu mnHistorial;
	private JMenu mnPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalVisual frame = new PrincipalVisual();
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
	public PrincipalVisual() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream empresa2;
				ObjectOutputStream empresaWrite;
				try {
					empresa2 = new  FileOutputStream("hospital.dat");
					empresaWrite = new ObjectOutputStream(empresa2);
					empresaWrite.writeObject(Hospital.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 502);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnHistorial = new JMenu("Historial Cl\u00EDnico");
		menuBar.add(mnHistorial);
		
		mnitHistorialPaciente = new JMenuItem("Historial Paciente");
		mnHistorial.add(mnitHistorialPaciente);
		
		mnitHistorialClinica = new JMenuItem("Historial Cl\u00EDnica");
		mnHistorial.add(mnitHistorialClinica);
		mnitHistorialClinica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarHistorial mostrarHistorial = new MostrarHistorial();
				mostrarHistorial.setModal(true);
				mostrarHistorial.setVisible(true);
			}
		});
		
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
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Listar Cita");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCita listCita =  new ListarCita();
				listCita.setModal(true);
				listCita.setVisible(true);
			}
		});
		mnCita.add(mntmNewMenuItem_4);
		
		mnPaciente = new JMenu("Paciente");
		menuBar.add(mnPaciente);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listar Pacientes");
		mnPaciente.add(mntmNewMenuItem);
		
		mnAdmin = new JMenu("Administraci\u00F3n");
		menuBar.add(mnAdmin);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Crear Usuario");
		mnAdmin.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar Enfermedad");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedad regEnfermedad = new RegEnfermedad(null);
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
				RegVacuna regVacuna = new RegVacuna(null);
				regVacuna.setModal(true);
				regVacuna.setVisible(true);
			}
		});
		mnAdmin.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar Doctores");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarDoctor doc = new ListarDoctor();
				doc.setModal(true);
				doc.setVisible(true);
			}
		});
		mnAdmin.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Administrar Viviendas");
		mnAdmin.add(mntmNewMenuItem_2);
		
		JButton btnCerraSesion = new JButton("Cerrar Sesi\u00F3n");
		btnCerraSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setModal(true);
				login.setVisible(true);
				dispose();
			}
		});
		menuBar.add(btnCerraSesion);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Citas de Hoy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(12, 164, 391, 251);
		contentPane.add(scrollPane);
		
		
		tblCitas = new JTable();
		String[] header = {"Código","Hora de Inicio", "Doctor", "Paciente"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		loadCitasHoy();
		scrollPane.setViewportView(tblCitas);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(417, 13, 317, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		load();
		
		cbxEnfermedades = new JComboBox<String>();
		cbxEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedEnfermedad();
				txtCasos.setText(String.valueOf(getCasos(enfermedad)));
				txtContagiados.setText(String.valueOf(getContagiados(enfermedad)));
			}
		});
		cbxEnfermedades.setBounds(40, 29, 241, 22);
		panel.add(cbxEnfermedades);
		
		JLabel lblNewLabel = new JLabel("Enfermedad");
		lblNewLabel.setBounds(120, 13, 91, 16);
		panel.add(lblNewLabel);
		
		JLabel lblVacuna = new JLabel("Vacuna");
		lblVacuna.setBounds(132, 95, 91, 16);
		panel.add(lblVacuna);
		
		cbxVacuna = new JComboBox<String>();
		cbxVacuna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedVacuna();
				txtVacunados.setText(String.valueOf(getVacunados(vacuna)));
				txtPorVac.setText(String.valueOf(getPorVac(vacuna)));
			}
		});
		cbxVacuna.setBounds(40, 115, 241, 22);
		panel.add(cbxVacuna);
		
		JLabel lblNewLabel_1 = new JLabel("Casos:");
		lblNewLabel_1.setBounds(23, 66, 56, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("% Contagiado:");
		lblNewLabel_2.setBounds(139, 66, 98, 16);
		panel.add(lblNewLabel_2);
		
		txtCasos = new JTextField();
		txtCasos.setEditable(false);
		txtCasos.setBounds(73, 64, 34, 22);
		panel.add(txtCasos);
		txtCasos.setColumns(10);
		
		txtContagiados = new JTextField();
		txtContagiados.setEditable(false);
		txtContagiados.setColumns(10);
		txtContagiados.setBounds(231, 64, 34, 22);
		panel.add(txtContagiados);
		
		JLabel lblVacunados = new JLabel("Vacunados:");
		lblVacunados.setBounds(24, 152, 67, 16);
		panel.add(lblVacunados);
		
		txtVacunados = new JTextField();
		txtVacunados.setEditable(false);
		txtVacunados.setColumns(10);
		txtVacunados.setBounds(94, 150, 34, 22);
		panel.add(txtVacunados);
		
		JLabel lblVacunado = new JLabel("% Vacunado:");
		lblVacunado.setBounds(140, 152, 98, 16);
		panel.add(lblVacunado);
		
		txtPorVac = new JTextField();
		txtPorVac.setEditable(false);
		txtPorVac.setColumns(10);
		txtPorVac.setBounds(232, 150, 34, 22);
		panel.add(txtPorVac);
		
		mostrarPrivilegios();
	}
	
	private void mostrarPrivilegios() {
		if (Hospital.getLoginUser().esPrivilegiado()) {
			mnAdmin.setVisible(false);
		}else if(Hospital.getLoginUser().esBasic())
		{
			mnAdmin.setVisible(false);
			mnHistorial.setVisible(false);
			mnPaciente.setVisible(false);
		}
	}
	
	private int getCasos(Enfermedad enfermedad) {
		int count = 0;
		for(Paciente aux:Hospital.getInstance().getMisPacientes())
		{
			if(aux.estaEnfermo(enfermedad))
				count++;
		}
		return count;
	}
	private int getContagiados(Enfermedad enfermedad) {
		return getCasos(enfermedad)/(Hospital.getCodigoPaciente()-1);
	}
	private int getVacunados(Vacuna vacuna) {
		int count = 0;
		for(Paciente aux:Hospital.getInstance().getMisPacientes())
		{
			if(aux.isVacunado(vacuna))
				count++;
		}
		return count;
	}
	private int getPorVac(Vacuna vacuna) {
		return getVacunados(vacuna)/(Hospital.getCodigoPaciente()-1);
	}
	
	public void loadCitasHoy()
	{
		Date fechaHoy = new Date();
		model.setRowCount(0);
		row =new Object[model.getColumnCount()];
		for(Cita aux:Hospital.getInstance().getMisCitas())
		{
			if(aux.getFchProgramada()==fechaHoy)
			{
				row[0] = aux.getId();
				row[1] = aux.getFchProgramada().getTime();
				row[2] = aux.getMiDoctor().getNombre();
				row[3] = aux.getProxPaciente().getNombre();
				model.addRow(row);
			}
		}
	}
	private void loadVacunas() {
		cbxVacuna.addItem("<Seleccione>");
		for(Vacuna aux:Hospital.getInstance().getMisVacunas())
		{
			cbxVacuna.addItem(aux.getNombre());
			
		}
	}
	private void loadEnfermedades() {
		cbxEnfermedades.addItem("<Seleccione>");
		for(Enfermedad aux:Hospital.getInstance().getMisEnfermedades())
		{
			cbxEnfermedades.addItem(aux.getNombre());
		}
	}
	private void load()
	{
		loadVacunas();
		loadEnfermedades();
		selectedVacuna();
		selectedEnfermedad();
		txtVacunados.setText(String.valueOf(getVacunados(vacuna)));
		txtPorVac.setText(String.valueOf(getPorVac(vacuna)));
		txtCasos.setText(String.valueOf(getCasos(enfermedad)));
		txtContagiados.setText(String.valueOf(getContagiados(enfermedad)));
		
	}
	private void selectedVacuna()
	{
		vacuna = Hospital.getInstance().buscarVacunaByNombre(cbxVacuna.getSelectedItem().toString());
	}
	private void selectedEnfermedad()
	{
		enfermedad = Hospital.getInstance().buscarEnfermedadByNombre(cbxEnfermedades.getSelectedItem().toString());
	}
}
