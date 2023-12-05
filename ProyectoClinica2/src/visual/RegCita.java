package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;

import logico.Doctor;
import logico.Hospital;
import logico.Persona;

import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JCalendar calendar;
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtTelefono;
	private Persona miPersona = null;
	private Doctor selected = null;
	private static ArrayList<Doctor> misDoctores = Hospital.getInstance().getMisDoctores();
	private static ArrayList<Doctor> doctoresDisponibles = new ArrayList<>();
	private static DefaultTableModel model;
	private static Object[] row;
	private JTextField txtId;

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
	 * @throws ParseException 
	 */
	public RegCita() throws ParseException {
		
		setTitle("Agendar una Cita");
		Date fchActual = new Date();
		Calendar fchActualMas30 = Calendar.getInstance();
		fchActualMas30.setTime(fchActual);
		fchActualMas30.add(Calendar.MINUTE, 30);
		
		setBounds(100, 100, 571, 523);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_Pasciente = new JPanel();
		panel_Pasciente.setBorder(new TitledBorder(null, "Datos del Pasciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		txtNombre.setColumns(10);
		txtNombre.setBounds(68, 48, 187, 20);
		panel_Pasciente.add(txtNombre);
		
		txtDireccion = new JTextField();
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
					if(txtCedula.getText().length() == 13) {
						miPersona = Hospital.getInstance().buscarPersonaByCedula(txtCedula.getText());
						if(miPersona!=null)
						{
							cargarPersona(miPersona);
						}
					}else {
						borrarCampos();
					}
					
				}
			});
			txtCedula.setBounds(68, 20, 187, 20);
			panel_Pasciente.add(txtCedula);
			
			MaskFormatter formatterTelefono = new MaskFormatter("###-###-####");
			txtTelefono = new JFormattedTextField(formatterTelefono);
			txtTelefono.setBounds(68, 73, 187, 20);
			panel_Pasciente.add(txtTelefono);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JPanel panel_Fecha = new JPanel();
		panel_Fecha.setBorder(new TitledBorder(null, "Fecha de la Cita", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Fecha.setBounds(10, 170, 535, 270);
		contentPanel.add(panel_Fecha);
		panel_Fecha.setLayout(null);
		
		calendar = new JCalendar();
		calendar.setBounds(10, 28, 366, 219);
		panel_Fecha.add(calendar);
		
		JPanel panel_Inicio = new JPanel();
		panel_Inicio.setBorder(new TitledBorder(null, "Hora de Inicio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Inicio.setBounds(386, 58, 139, 93);
		panel_Fecha.add(panel_Inicio);
		panel_Inicio.setLayout(null);
		
		spnHoraInicio = new JSpinner();
		spnHoraInicio.setModel(new SpinnerDateModel(fchActual, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnHoraInicio = new JSpinner.DateEditor(spnHoraInicio, "h:mm a");
		spnHoraInicio.setEditor(de_spnHoraInicio);
		
		spnHoraInicio.setBounds(10, 37, 119, 20);
		panel_Inicio.add(spnHoraInicio);
		
		JPanel panel_Fin = new JPanel();
		panel_Fin.setBorder(new TitledBorder(null, "Hora de Finalizaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Fin.setBounds(386, 152, 139, 93);
		panel_Fecha.add(panel_Fin);
		panel_Fin.setLayout(null);
		
		spnHoraFin = new JSpinner();
		spnHoraFin.setModel(new SpinnerDateModel(fchActualMas30.getTime(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnHoraFin = new JSpinner.DateEditor(spnHoraFin, "h:mm a");
		spnHoraFin.setEditor(de_spnHoraFin);
		
		spnHoraFin.setBounds(10, 42, 119, 20);
		panel_Fin.add(spnHoraFin);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "C\u00F3digo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(388, 13, 135, 49);
		panel_Fecha.add(panel);
		panel.setLayout(null);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(12, 16, 116, 22);
		panel.add(txtId);
		txtId.setColumns(10);
		txtId.setText("Cita-"+Hospital.getInstance().getCodigoCita());
		
		JPanel panel_Doctor = new JPanel();
		panel_Doctor.setBorder(new TitledBorder(null, "Doctores Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
				if(index>=0) {
					selected = Hospital.getInstance().buscarDoctorById(tblDoctores.getValueAt(index, 0).toString());
				}
			}
		});
		tblDoctores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblDoctores);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
	private void cargarPersona(Persona aux ){
		txtNombre.setText(aux.getNombre());
		txtTelefono.setText(aux.getTelefono());
		txtDireccion.setText(aux.getDireccion());
		
		if(aux.esMasculino()) {
			rbtnMasculino.setSelected(true);
			rbtnMasculino.setSelected(false);
			return;
		}
		rbtnMasculino.setSelected(false);
		rbtnMasculino.setSelected(true);
	}
	private void borrarCampos()
	{
		txtNombre.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		rbtnMasculino.setSelected(true);
		rbtnFemenino.setSelected(false);
	}
	private ArrayList<Doctor> cargarDoctoresDisponibles() {
		ArrayList<Doctor> doctoresDisponibles = new ArrayList<>();
			for (Doctor aux : misDoctores) {
				if(true) {
					doctoresDisponibles.add(aux);
				}
			}
		return doctoresDisponibles;
	}
	private static void imprimirDoctores(ArrayList<Doctor> losDoctores)
	{
		model.setRowCount(0);
		row =new Object[model.getColumnCount()];
		for (Doctor aux: losDoctores)
		{
			row[0] = aux.getId();
			row[1] = aux.getNombre();
			model.addRow(row);
		}
	}
}
