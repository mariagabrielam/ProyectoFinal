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
import logico.Doctor;
import logico.Empleado;
import logico.Enfermedad;
import logico.Hospital;
import logico.Paciente;
import logico.Persona;
import logico.Vacuna;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class PrincipalVisual extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9104132929861447962L;
	/**
	 * 
	 */
	private JPanel contentPane;
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
	private JTextField txtABPlus;
	private JTextField txtAPlus;
	private JTextField txtBPlus;
	private JTextField txtOPlus;
	private JTextField txtABMinus;
	private JTextField txtAMinus;
	private JTextField txtBMinus;
	private JTextField txtOMinus;

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
		setResizable(false);
		setTitle("Hospital CIM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalVisual.class.getResource("/Iconos/hospitalIcon.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream hospital2;
				ObjectOutputStream hospitalWrite;
				try {
					hospital2 = new  FileOutputStream("hospital.dat");
					hospitalWrite = new ObjectOutputStream(hospital2);
					hospitalWrite.writeObject(Hospital.getInstance());
					hospital2.close();
					hospitalWrite.close();
					dispose();
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
		
		mnitHistorialClinica = new JMenuItem("Mostrar Historial");
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
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPaciente listPaciente = new ListarPaciente();
				listPaciente.setModal(true);
				listPaciente.setVisible(true);
			}
		});
		mnPaciente.add(mntmNewMenuItem);
		
		mnAdmin = new JMenu("Administraci\u00F3n");
		menuBar.add(mnAdmin);
		
		JMenu mnNewMenu = new JMenu("Usuarios");
		mnAdmin.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Crear Usuario");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuario crear = new CrearUsuario();
				crear.setModal(true);
				crear.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Listar Usuario");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarUsuarios listUser = new ListarUsuarios();
				listUser.setModal(true);
				listUser.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_1 = new JMenu("Empleados");
		mnAdmin.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Registrar Empleado");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar Empleados");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarDoctor doc = new ListarDoctor();
				doc.setModal(true);
				doc.setVisible(true);
			}
		});
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RegDoctor regDoctor = new RegDoctor(null);
					regDoctor.setModal(true);
					regDoctor.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenu mnNewMenu_2 = new JMenu("Vacunas");
		mnAdmin.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Registrar Vacuna");
		mnNewMenu_2.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Listar Vacunas");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarVacuna listVac = new ListarVacuna();
				listVac.setModal(true);
				listVac.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_10);
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna regVacuna = new RegVacuna(null);
				regVacuna.setModal(true);
				regVacuna.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_3 = new JMenu("Enfermedades");
		mnAdmin.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Registrar Enfermedad");
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Listar Enfermedades");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEnfermedades listEnfermedad = new ListarEnfermedades();
				listEnfermedad.setModal(true);
				listEnfermedad.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_11);
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegEnfermedad regEnfermedad = new RegEnfermedad(null);
				regEnfermedad.setModal(true);
				regEnfermedad.setVisible(true);
			}
		});
		
		JMenu mnNewMenu_4 = new JMenu("Viviendas");
		mnAdmin.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Administrar Viviendas");
		mnNewMenu_4.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Listar Viviendas");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarViviendas listViv = new ListarViviendas();
				listViv.setModal(true);
				listViv.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_12);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdministrarVivienda adminViv = new AdministrarVivienda();
				adminViv.setModal(true);
				adminViv.setVisible(true);
			}
		});
		
		JButton btnCerraSesion = new JButton("Cerrar Sesi\u00F3n");
		btnCerraSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hospital.setLoginUser(null);
				dispose();
				Login login = new Login();
				login.setModal(true);
				login.setVisible(true);
				
			}
		});
		menuBar.add(btnCerraSesion);
		
		JButton btnNewButton = new JButton("Generar");
		menuBar.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Hospital.getCodigoDoctor() == 1 && Hospital.getCodigoEmpleado() == 1)
				{
					Persona Doc1 = new Doctor("D-"+Hospital.getCodigoDoctor(), "402-1341389-7", "Isaac", "829-393-1603", "La Vega", "Masculino", "Doctor", "10-5000", "Cardiologo");
				Hospital.getInstance().addPersona(Doc1);
				Persona Doc2 = new Doctor("D-"+Hospital.getCodigoDoctor(), "402-5866688-7", "Juan", "829-393-2020", "La Vega", "Masculino", "Doctor", "10-5888", "Podologo");
				Hospital.getInstance().addPersona(Doc2);
				
				Persona Sec1 = new Empleado("S-"+Hospital.getCodigoEmpleado(), "131-5404855-1", "Juana", "829-555-6666", "La Vega", "Femenino", "Secretario");
				Hospital.getInstance().addPersona(Sec1);
				
				Paciente p1 = new Paciente("1", "402-15325546-8", "Esteban", "809-555-65445", "Santiago", "Masculino");
				Date dia = new Date();
				Cita nuevaCita =  new Cita("C-1", p1, (Doctor)Doc2, dia);
				Hospital.getInstance().addCita(nuevaCita);
				}
				
				
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] header = {"Código","Hora de Inicio", "Doctor", "Paciente","Estado"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		loadCitasHoy();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(437, 13, 297, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		cbxEnfermedades = new JComboBox<String>();
		cbxEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedEnfermedad();
				txtCasos.setText(String.valueOf(getCasos(enfermedad)));
				txtContagiados.setText(String.valueOf(getContagiados(enfermedad)));
				loadEnfermedades();
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
				loadVacunas();
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Citas de Hoy", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 198, 487, 217);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 467, 185);
		panel_1.add(scrollPane);
		
		
		tblCitas = new JTable();
		tblCitas.setModel(model);
		scrollPane.setViewportView(tblCitas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipos de Sangre de los Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(509, 198, 225, 217);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_3 = new JLabel("AB+:");
		lblNewLabel_3.setBounds(48, 30, 32, 16);
		panel_2.add(lblNewLabel_3);
		
		txtABPlus = new JTextField();
		txtABPlus.setEditable(false);
		txtABPlus.setBounds(80, 26, 26, 22);
		panel_2.add(txtABPlus);
		txtABPlus.setColumns(10);
		
		txtAPlus = new JTextField();
		txtAPlus.setEditable(false);
		txtAPlus.setColumns(10);
		txtAPlus.setBounds(80, 72, 26, 22);
		panel_2.add(txtAPlus);
		
		JLabel lblA_1 = new JLabel("A+:");
		lblA_1.setBounds(48, 76, 32, 16);
		panel_2.add(lblA_1);
		
		txtBPlus = new JTextField();
		txtBPlus.setEditable(false);
		txtBPlus.setColumns(10);
		txtBPlus.setBounds(80, 119, 26, 22);
		panel_2.add(txtBPlus);
		
		JLabel lblB_1 = new JLabel("B+:");
		lblB_1.setBounds(48, 122, 32, 16);
		panel_2.add(lblB_1);
		
		txtOPlus = new JTextField();
		txtOPlus.setEditable(false);
		txtOPlus.setColumns(10);
		txtOPlus.setBounds(80, 166, 26, 22);
		panel_2.add(txtOPlus);
		
		JLabel lblO_1 = new JLabel("O+:");
		lblO_1.setBounds(48, 168, 32, 16);
		panel_2.add(lblO_1);
		
		txtABMinus = new JTextField();
		txtABMinus.setEditable(false);
		txtABMinus.setColumns(10);
		txtABMinus.setBounds(154, 26, 26, 22);
		panel_2.add(txtABMinus);
		
		JLabel lblAb = new JLabel("AB- :");
		lblAb.setBounds(122, 30, 38, 16);
		panel_2.add(lblAb);
		
		txtAMinus = new JTextField();
		txtAMinus.setEditable(false);
		txtAMinus.setColumns(10);
		txtAMinus.setBounds(154, 72, 26, 22);
		panel_2.add(txtAMinus);
		
		JLabel lblA = new JLabel("A- :");
		lblA.setBounds(122, 76, 27, 16);
		panel_2.add(lblA);
		
		txtBMinus = new JTextField();
		txtBMinus.setEditable(false);
		txtBMinus.setColumns(10);
		txtBMinus.setBounds(154, 119, 26, 22);
		panel_2.add(txtBMinus);
		
		JLabel lblB = new JLabel("B- :");
		lblB.setBounds(122, 122, 27, 16);
		panel_2.add(lblB);
		
		txtOMinus = new JTextField();
		txtOMinus.setEditable(false);
		txtOMinus.setColumns(10);
		txtOMinus.setBounds(154, 166, 26, 22);
		panel_2.add(txtOMinus);
		
		JLabel lblO = new JLabel("O- :");
		lblO.setBounds(116, 168, 33, 16);
		panel_2.add(lblO);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(33, 15, 403, 156);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Iconos/elRealLogo.png")));
		if(Hospital.getCodigoPaciente()>1) {
			load();
		}
			
		mostrarPrivilegios();
	}
	
	private void mostrarPrivilegios() {
		System.out.println(Hospital.getLoginUser().getUsername() + " " + Hospital.getLoginUser().getTipo());
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
		if(Hospital.getCodigoPaciente() != 1) {
		return getCasos(enfermedad)/(Hospital.getCodigoPaciente()-1);
		}
		return 0;
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
		if(Hospital.getCodigoPaciente() != 1)
		{
			return getVacunados(vacuna)/(Hospital.getCodigoPaciente()-1);
		}
		return 0;
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
				if(aux.isEstado())
					row[4]="Realizado";
				else
					row[4]="Pendiente";
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
		loadSangreStats();
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
	private int loadTipoSangre(String tipo)
	{
		int cant = 0;
		for(Paciente aux:Hospital.getInstance().getMisPacientes())
		{
			if(aux.getTipoSangre().equalsIgnoreCase(tipo))
				cant++;
		}
		return cant;
	}
	private void loadSangreStats()
	{
		txtABPlus.setText(String.valueOf(loadTipoSangre("AB+")));
		txtAPlus.setText(String.valueOf(loadTipoSangre("A+")));
		txtBPlus.setText(String.valueOf(loadTipoSangre("B+")));
		txtOPlus.setText(String.valueOf(loadTipoSangre("O+")));
		txtABMinus.setText(String.valueOf(loadTipoSangre("AB-")));
		txtAMinus.setText(String.valueOf(loadTipoSangre("A-")));
		txtBMinus.setText(String.valueOf(loadTipoSangre("B-")));
		txtOMinus.setText(String.valueOf(loadTipoSangre("O-")));	
		
	}
}
