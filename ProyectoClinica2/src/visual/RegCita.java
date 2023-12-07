package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JCalendar;

import logico.Cita;
import logico.Doctor;
import logico.Hospital;
import logico.Paciente;
import logico.Persona;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class RegCita extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9146270308849717444L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblDoctores;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JRadioButton rbtnMasculino;
	private JRadioButton rbtnFemenino;
	private JSpinner spnHoraInicio;
	private JSpinner spnHoraFin;
	private JCalendar calendario;
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtTelefono;
	private Persona miPersona = null;
	private Doctor selected = null;
	private static ArrayList<Cita> misCitas = Hospital.getInstance().getMisCitas();
	private static ArrayList<Doctor> misDoctores = Hospital.getInstance().getMisDoctores();
	private static ArrayList<Doctor> doctoresDisponibles = new ArrayList<>();
	private static DefaultTableModel model;
	private static Object[] row;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegCita dialog = new RegCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws ParseException
	 */
	public RegCita() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegCita.class.getResource("/Iconos/clipboardIcon.png")));

		setResizable(false);
		setLocationRelativeTo(null); // Pantalla en el centro
		setTitle("Cita No." + Hospital.getCodigoCita());
		Calendar calenda = Calendar.getInstance();
		Date fchActual = calenda.getTime();
		Calendar fchActualMas30 = Calendar.getInstance();
		fchActualMas30.setTime(fchActual);
		fchActualMas30.add(Calendar.MINUTE, 30);

		setBounds(100, 100, 564, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel_Pasciente = new JPanel();
		panel_Pasciente.setBorder(
				new TitledBorder(null, "Datos del Pasciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Pasciente.setBounds(10, 11, 280, 160);
		contentPanel.add(panel_Pasciente);
		panel_Pasciente.setLayout(null);

		JLabel lblNewLabel = new JLabel("C\u00E9dula: ");
		lblNewLabel.setBounds(10, 23, 75, 14);
		panel_Pasciente.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setBounds(10, 48, 75, 14);
		panel_Pasciente.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Telefono: ");
		lblNewLabel_2.setBounds(10, 73, 62, 14);
		panel_Pasciente.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Direcci\u00F3n: ");
		lblNewLabel_3.setBounds(10, 98, 62, 14);
		panel_Pasciente.add(lblNewLabel_3);

		rbtnMasculino = new JRadioButton("M");
		rbtnMasculino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnMasculino.setSelected(true);
				rbtnFemenino.setSelected(false);
			}
		});
		rbtnMasculino.setSelected(true);
		rbtnMasculino.setBounds(110, 119, 47, 23);
		panel_Pasciente.add(rbtnMasculino);

		rbtnFemenino = new JRadioButton("F");
		rbtnFemenino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnFemenino.setSelected(true);
				rbtnMasculino.setSelected(false);
			}
		});
		rbtnFemenino.setBounds(171, 119, 47, 23);
		panel_Pasciente.add(rbtnFemenino);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBoton();
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(68, 48, 187, 20);
		panel_Pasciente.add(txtNombre);

		txtDireccion = new JTextField();
		txtDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				habilitarBoton();
			}
		});
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(68, 98, 187, 20);
		panel_Pasciente.add(txtDireccion);

		JLabel lblNewLabel_4 = new JLabel("Sexo: ");
		lblNewLabel_4.setBounds(10, 123, 47, 14);
		panel_Pasciente.add(lblNewLabel_4);
		try {
			MaskFormatter formatterCedula = new MaskFormatter("###-#######-#");
			txtCedula = new JFormattedTextField(formatterCedula);
			txtCedula.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

					if (txtCedula.getText().length() == 13) {
						miPersona = Hospital.getInstance().buscarPersonaByCedula(txtCedula.getText());
						if (miPersona != null) {
							cargarPersona(miPersona);
						}
					} else {
						borrarCampos();
					}
					habilitarBoton();
				}
			});
			txtCedula.setBounds(68, 20, 187, 20);
			panel_Pasciente.add(txtCedula);

			MaskFormatter formatterTelefono = new MaskFormatter("###-###-####");
			txtTelefono = new JFormattedTextField(formatterTelefono);
			txtTelefono.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					habilitarBoton();
				}
			});
			txtTelefono.setBounds(68, 73, 187, 20);
			panel_Pasciente.add(txtTelefono);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JPanel panel_Fecha = new JPanel();
		panel_Fecha.setBorder(
				new TitledBorder(null, "Fecha de la Cita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Fecha.setBounds(10, 182, 535, 258);
		contentPanel.add(panel_Fecha);
		panel_Fecha.setLayout(null);

		calendario = new JCalendar();
		calendario.getDayChooser().setWeekOfYearVisible(false);
		calendario.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				doctoresDisponibles = cargarDoctoresDisponibles(calendario.getDate(), (Date) spnHoraInicio.getValue(),
						(Date) spnHoraFin.getValue());
				imprimirDoctores(doctoresDisponibles);
			}
		});
		calendario.setBounds(10, 28, 366, 219);
		panel_Fecha.add(calendario);

		JPanel panel_Inicio = new JPanel();
		panel_Inicio.setBorder(
				new TitledBorder(null, "Hora de Inicio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Inicio.setBounds(386, 23, 139, 93);
		panel_Fecha.add(panel_Inicio);
		panel_Inicio.setLayout(null);

		spnHoraInicio = new JSpinner();
		spnHoraInicio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				doctoresDisponibles = cargarDoctoresDisponibles(calendario.getDate(), (Date) spnHoraInicio.getValue(),
						(Date) spnHoraFin.getValue());
				imprimirDoctores(doctoresDisponibles);
			}
		});
		spnHoraInicio.setModel(new SpinnerDateModel(fchActual, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnHoraInicio = new JSpinner.DateEditor(spnHoraInicio, "h:mm a");
		spnHoraInicio.setEditor(de_spnHoraInicio);

		spnHoraInicio.setBounds(10, 37, 119, 20);
		panel_Inicio.add(spnHoraInicio);

		JPanel panel_Fin = new JPanel();
		panel_Fin.setBorder(new TitledBorder(null, "Hora de Finalizaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_Fin.setBounds(386, 140, 139, 93);
		panel_Fecha.add(panel_Fin);
		panel_Fin.setLayout(null);

		spnHoraFin = new JSpinner();
		spnHoraFin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				doctoresDisponibles = cargarDoctoresDisponibles(calendario.getDate(), (Date) spnHoraInicio.getValue(),
						(Date) spnHoraFin.getValue());
				imprimirDoctores(doctoresDisponibles);
			}
		});
		spnHoraFin.setModel(new SpinnerDateModel(fchActualMas30.getTime(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnHoraFin = new JSpinner.DateEditor(spnHoraFin, "h:mm a");
		spnHoraFin.setEditor(de_spnHoraFin);

		spnHoraFin.setBounds(10, 42, 119, 20);
		panel_Fin.add(spnHoraFin);

		JPanel panel_Doctor = new JPanel();
		panel_Doctor.setBorder(
				new TitledBorder(null, "Doctores Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Doctor.setBounds(300, 11, 245, 160);
		contentPanel.add(panel_Doctor);
		panel_Doctor.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 225, 126);
		panel_Doctor.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		String[] header = { "Id", "Nombre" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(header);
		tblDoctores = new JTable();
		tblDoctores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblDoctores.getSelectedRow();
				if (index >= 0) {
					selected = Hospital.getInstance().buscarDoctorById(tblDoctores.getValueAt(index, 0).toString());
				}
				habilitarBoton();
			}
		});
		tblDoctores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDoctores.setModel(model);
		scrollPane.setViewportView(tblDoctores);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setSelectedIcon(new ImageIcon(RegCita.class.getResource("/Iconos/plus.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (miPersona == null) {
							miPersona = new Paciente(null, txtCedula.getText(), txtNombre.getText(),
									txtTelefono.getText(), txtDireccion.getText(), determimarSexo());
						}
						Date fchProgramada = determinarFecha(calendario.getDate(), (Date) spnHoraInicio.getValue());
						Cita nuevaCita = new Cita("C-" + Hospital.getCodigoCita(), miPersona, selected, fchProgramada);
						System.out.println("NOMBRE "+miPersona.getNombre()+" Fecha "+fchProgramada);
						Hospital.getInstance().addCita(nuevaCita);
						txtCedula.setText("");
						borrarCampos();
					}
				});
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		imprimirDoctores(doctoresDisponibles);
	}

	private void habilitarBoton() {
		if (txtCedula.getText().charAt(0) != ' ' && !txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty()
				&& txtTelefono.getText().charAt(0) != ' ' && selected != null) {
			okButton.setEnabled(true);
		} else {
			okButton.setEnabled(false);
		}
	}

	private static Date determinarFecha(Date fecha, Date hora) {
		if (fecha == null || hora == null) {
			return null;
		}
		Calendar calFecha = Calendar.getInstance();
		calFecha.setTime(fecha);

		Calendar calHora = Calendar.getInstance();
		calHora.setTime(hora);

		// Configurar la hora de la fecha
		calFecha.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY));
		calFecha.set(Calendar.MINUTE, calHora.get(Calendar.MINUTE));
		calFecha.set(Calendar.SECOND, calHora.get(Calendar.SECOND));
		calFecha.set(Calendar.MILLISECOND, calHora.get(Calendar.MILLISECOND));
		return calFecha.getTime();
	}

	private void cargarPersona(Persona aux) {
		txtNombre.setText(aux.getNombre());
		txtTelefono.setText(aux.getTelefono());
		txtDireccion.setText(aux.getDireccion());

		if (aux.esMasculino()) {
			rbtnMasculino.setSelected(true);
			rbtnMasculino.setSelected(false);
			return;
		}
		rbtnMasculino.setSelected(false);
		rbtnMasculino.setSelected(true);
	}

	private void borrarCampos() {
		txtNombre.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		rbtnMasculino.setSelected(true);
		rbtnFemenino.setSelected(false);
	}

	private ArrayList<Doctor> cargarDoctoresDisponibles(Date fechaConsulta, Date horaInicio, Date horaFin) {
		ArrayList<Doctor> doctoresDisponibles = new ArrayList<>();
		/*
		 * Calendar calFecha = Calendar.getInstance(); Calendar calHoraInicio =
		 * Calendar.getInstance(); Calendar calHoraFin = Calendar.getInstance();
		 * calFecha.setTime(calendario.getDate()); calHoraInicio.setTime((Date)
		 * spnHoraInicio.getValue()); calHoraFin.setTime((Date) spnHoraFin.getValue());
		 */
		for (Doctor miDoctor : misDoctores) {
			boolean ocupado = false;
			for (Cita miCita : misCitas) {
				if (miCita.getMiDoctor().equals(miDoctor) && miCita.getFchProgramada().equals(fechaConsulta)
						&& (miCita.getFchProgramada().after(horaInicio) || miCita.getFchProgramada().equals(horaInicio))
						&& (miCita.getFchProgramada().before(horaFin) || miCita.getFchProgramada().equals(horaFin))) {
					ocupado = true;
					break;
				}
			}
			if (!ocupado) {
				doctoresDisponibles.add(miDoctor);
			}
		}
		selected = null;
		return doctoresDisponibles;
	}

	private static void imprimirDoctores(ArrayList<Doctor> losDoctores) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Doctor aux : losDoctores) {
			row[0] = aux.getId();
			row[1] = aux.getNombre();
			model.addRow(row);
		}
	}

	private String determimarSexo() {
		if (rbtnFemenino.isSelected()) {
			return "Femenino";
		}
		return "Masculino";
	}

}
