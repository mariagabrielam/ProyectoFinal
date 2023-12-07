package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import logico.Cita;
import logico.Doctor;
import logico.Hospital;
import java.awt.Toolkit;

public class ListarCita extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 91411914069182076L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblAgenda;
	private JComboBox<Object> cbxDoctor;
	private JCalendar calendar;
	private Cita selectedCita = null;
	private JButton btnOk;
	private Doctor selectedDoctor = null;
	private static DefaultTableModel model;
	private static Object[] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarCita dialog = new ListarCita();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarCita() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarCita.class.getResource("/Iconos/clipboardIcon.png")));
		setTitle("Agenda de Citas");
		setBounds(100, 100, 897, 406);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_Doctor = new JPanel();
			panel_Doctor
					.setBorder(new TitledBorder(null, "Doctor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_Doctor.setBounds(10, 11, 276, 76);
			contentPanel.add(panel_Doctor);
			panel_Doctor.setLayout(null);

			cbxDoctor = new JComboBox<Object>();
			loadDoctores();
			cbxDoctor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = cbxDoctor.getSelectedIndex();
					if (index > 0) {
						selectedDoctor = Hospital.getInstance().getMisDoctores().get(index);
					} else {
						selectedDoctor = null;
					}
					loadCitas(selectedDoctor);
					habilitarBoton();
				}

			});
			cbxDoctor.setBounds(10, 26, 256, 27);
			panel_Doctor.add(cbxDoctor);
		}

		JPanel panel_Dia = new JPanel();
		panel_Dia.setBorder(
				new TitledBorder(null, "Calendario de Citas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Dia.setBounds(10, 98, 276, 225);
		contentPanel.add(panel_Dia);
		panel_Dia.setLayout(null);

		calendar = new JCalendar();
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				selectedCita = null;
				habilitarBoton();

			}
		});
		calendar.setBounds(10, 25, 256, 189);
		panel_Dia.add(calendar);

		JPanel panel_Horas = new JPanel();
		panel_Horas.setBorder(new TitledBorder(null, "Agenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Horas.setBounds(296, 11, 564, 312);
		contentPanel.add(panel_Horas);
		panel_Horas.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 21, 544, 280);
		panel_Horas.add(scrollPane);

		model = new DefaultTableModel();
		tblAgenda = new JTable();
		tblAgenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tblAgenda.getSelectedRow();
				if (index > 0) {
					selectedCita = Hospital.getInstance().buscarCitaById(tblAgenda.getValueAt(index, 0).toString());
				}

			}
		});
		tblAgenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAgenda.setModel(model);
		scrollPane.setViewportView(tblAgenda);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("Registrar Consulta");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegConsulta regConsulta = new RegConsulta(selectedCita);
						regConsulta.setModal(true);
						regConsulta.setVisible(true);
					}
				});
				btnOk.setEnabled(false);
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
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
		loadCitas(selectedDoctor);
	}

	private void habilitarBoton() {
		if (cbxDoctor.getSelectedIndex() > 0 && selectedCita != null) {
			btnOk.setEnabled(true);
		} else {
			btnOk.setEnabled(false);
		}
	}

	private void loadDoctores() {
		cbxDoctor.addItem("<Seleccione>");
		for (Doctor aux : Hospital.getInstance().getMisDoctores()) {
			cbxDoctor.addItem(aux.getNombre());
		}
	}

	private void loadCitas(Doctor doctor) {
		model.setRowCount(0);
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy h:mm aa");

		if (doctor != null) // Tiene doctor
		{
			String[] header = { "Código", "Hora de Inicio", "Paciente" };
			model.setColumnIdentifiers(header);
			row = new Object[model.getColumnCount()];
			for (Cita aux : Hospital.getInstance().getMisCitas()) {
				if (aux.getMiDoctor().getId().equalsIgnoreCase(doctor.getId())) {
					row[0] = aux.getId();
					row[1] = String.valueOf(formatoFecha.format(aux.getFchProgramada()));
					row[2] = aux.getProxPaciente().getNombre();
					model.addRow(row);
				}
			}
		} else // No tiene
		{
			String[] header = { "Código", "Hora de Inicio", "Doctor", "Paciente" };
			model.setColumnIdentifiers(header);
			row = new Object[model.getColumnCount()];
			for (Cita aux : Hospital.getInstance().getMisCitas()) {
				row[0] = aux.getId();
				row[1] = String.valueOf(formatoFecha.format(aux.getFchProgramada()));
				row[2] = aux.getMiDoctor().getNombre();
				row[3] = aux.getProxPaciente().getNombre();
				model.addRow(row);
			}

		}
	}
}