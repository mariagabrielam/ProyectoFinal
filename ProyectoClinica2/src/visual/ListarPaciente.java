package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Hospital;
import logico.Paciente;
import logico.Vacuna;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblPacientes;
	private static DefaultTableModel model;
	private static Object[] row;
	private static DefaultTableModel model1;
	private static Object[] row1;
	private JTextField txtNHC;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtPeso;
	private JTextField txtSangre;
	private JTextField txtEstatura;
	private Paciente selected = null;
	private JTable tblVacunas;
	private JButton btnSelect;
	private JButton btnVacuna;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPaciente dialog = new ListarPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPaciente() {
		setTitle("Lista de Pacientes");
		setBounds(100, 100, 511, 529);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportBorder(new TitledBorder(null, "Pacientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scrollPane.setBounds(0, 0, 193, 443);
			contentPanel.add(scrollPane);
			{
				tblPacientes = new JTable();
				tblPacientes.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
							int index = tblPacientes.getSelectedRow();
							if(index>0)
							{
								selected = Hospital.getInstance().buscarPacienteByNHC(tblPacientes.getValueAt(index, 0).toString());
								btnSelect.setEnabled(true);
								btnVacuna.setEnabled(true);
							}					
						}
				});
				scrollPane.setViewportView(tblPacientes);
				String[] header = {"Código","Cédula","Nombre"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(header);
			}
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos del Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(203, 13, 278, 430);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(17, 76, 56, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NHC:");
		lblNewLabel_1.setBounds(17, 24, 56, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("C\u00E9dula:");
		lblNewLabel_2.setBounds(17, 51, 56, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Peso (kg):");
		lblNewLabel_3.setBounds(13, 182, 87, 16);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Estatura (m):");
		lblNewLabel_4.setBounds(13, 207, 87, 16);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo de Sangre:");
		lblNewLabel_5.setBounds(13, 155, 105, 16);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tel\u00E9fono:");
		lblNewLabel_6.setBounds(17, 100, 56, 16);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Direcci\u00F3n:");
		lblNewLabel_7.setBounds(17, 129, 69, 16);
		panel.add(lblNewLabel_7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Vacunas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(12, 235, 258, 188);
		panel.add(scrollPane);
		
		tblVacunas = new JTable();
		tblVacunas.setRowSelectionAllowed(false);
		scrollPane.setViewportView(tblVacunas);
		String[] header = {"Nombre"};
		model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(header);
		
		txtNHC = new JTextField();
		txtNHC.setEditable(false);
		txtNHC.setBounds(88, 21, 116, 22);
		panel.add(txtNHC);
		txtNHC.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		txtCedula.setColumns(10);
		txtCedula.setBounds(88, 46, 116, 22);
		panel.add(txtCedula);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(88, 71, 116, 22);
		panel.add(txtNombre);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(88, 96, 116, 22);
		panel.add(txtTelefono);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(88, 124, 116, 22);
		panel.add(txtDireccion);
		
		txtPeso = new JTextField();
		txtPeso.setEditable(false);
		txtPeso.setColumns(10);
		txtPeso.setBounds(112, 178, 116, 22);
		panel.add(txtPeso);
		
		txtSangre = new JTextField();
		txtSangre.setEditable(false);
		txtSangre.setColumns(10);
		txtSangre.setBounds(112, 151, 116, 22);
		panel.add(txtSangre);
		
		txtEstatura = new JTextField();
		txtEstatura.setEditable(false);
		txtEstatura.setColumns(10);
		txtEstatura.setBounds(112, 204, 116, 22);
		panel.add(txtEstatura);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSelect = new JButton("Seleccionar");
				btnSelect.setEnabled(false);
				btnSelect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int index = tblPacientes.getSelectedRow();
						if(index>0)
						{
							loadDatosPaciente();
							btnSelect.setEnabled(false);
						}
						
					}
				});
				
				btnVacuna = new JButton("Ingresar Vacuna");
				btnVacuna.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnVacuna.setEnabled(false);
				buttonPane.add(btnVacuna);
				btnSelect.setActionCommand("OK");
				buttonPane.add(btnSelect);
				getRootPane().setDefaultButton(btnSelect);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void loadPacientes() {
		model.setRowCount(0);
		row =new Object[model.getColumnCount()];
		for(Paciente aux:Hospital.getInstance().getMisPacientes())
		{
			row[0] = aux.getNhc();
			row[1] = aux.getCedula();
			row[2] = aux.getNombre();
			model.addRow(row);
		}
	}
	private void loadDatosPaciente() {
		txtNHC.setText(selected.getNhc());
		txtCedula.setText(selected.getCedula());
		txtNombre.setText(selected.getNombre());
		txtTelefono.setText(selected.getTelefono());
		txtDireccion.setText(selected.getDireccion());
		txtPeso.setText(String.valueOf(selected.getPeso()));
		txtSangre.setText(selected.getTipoSangre());
		txtEstatura.setText(String.valueOf(selected.getEstatura()));
		loadVacunas();
		
	}
	private void loadVacunas() {
		model1.setRowCount(0);
		row1 =new Object[model1.getColumnCount()];
		for(Vacuna aux:selected.getMisVacunas())
		{
			row1[0] = aux.getNombre();
			model1.addRow(row1);
		}
	}
}
