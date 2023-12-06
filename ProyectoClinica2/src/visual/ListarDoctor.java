package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Doctor;
import logico.Hospital;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;

public class ListarDoctor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7937591526581336190L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblDoctores;
	private JComboBox<Object> cbxEspecialidad;
	private JComboBox<Object> cbxSexo;
	private JButton okButton;
	private ArrayList<Doctor> misDoctores = Hospital.getInstance().getMisDoctores();
	private ArrayList<Doctor> doctoresFiltrado = new ArrayList<>();
	private Doctor selectedDoctor = null;
	private static DefaultTableModel model;
	private static Object[] row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarDoctor dialog = new ListarDoctor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarDoctor() {
		setTitle("Lista de Doctores");
		setBounds(100, 100, 624, 444);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel_Doctores = new JPanel();
			panel_Doctores.setBorder(new TitledBorder(null, "Doctores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_Doctores.setBounds(163, 11, 435, 350);
			contentPanel.add(panel_Doctores);
			panel_Doctores.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 21, 415, 318);
			panel_Doctores.add(scrollPane);
			
			String[] header = {"Id" , "Nombre" , "Cedula" ,"Especialidad"};
			model = new DefaultTableModel();
			model.setColumnIdentifiers(header);
			tblDoctores = new JTable();
			tblDoctores.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					habilitarBoton();
					int index = tblDoctores.getSelectedRow();
					if(index > 0)
					{
						selectedDoctor = Hospital.getInstance().buscarDoctorById(tblDoctores.getValueAt(index, 0).toString());
					}
				}
			});
			tblDoctores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(tblDoctores);
			tblDoctores.setModel(model);
		}
		
		JPanel panel_Filtros = new JPanel();
		panel_Filtros.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Filtros.setBounds(10, 188, 154, 173);
		contentPanel.add(panel_Filtros);
		panel_Filtros.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Especialidad: ");
		lblNewLabel.setBounds(10, 28, 72, 14);
		panel_Filtros.add(lblNewLabel);
		
		String[] especialidades = buscarEspecialidades();
		cbxEspecialidad = new JComboBox<Object>(especialidades);
		cbxEspecialidad.setBounds(10, 44, 134, 20);
		panel_Filtros.add(cbxEspecialidad);
		
		JLabel lblNewLabel_1 = new JLabel("Sexo: ");
		lblNewLabel_1.setBounds(10, 75, 46, 14);
		panel_Filtros.add(lblNewLabel_1);
		
		cbxSexo = new JComboBox<Object>();
		cbxSexo.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Selecione>", "Masculino", "Femenino"}));
		cbxSexo.setBounds(10, 93, 134, 20);
		panel_Filtros.add(cbxSexo);
		
		JButton btnBorrarFiltros = new JButton("Borrar Filtros");
		btnBorrarFiltros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbxEspecialidad.setSelectedIndex(0);
				cbxSexo.setSelectedIndex(0);
				
				imprimirDoctores(misDoctores);
			}
		});
		btnBorrarFiltros.setBounds(10, 124, 134, 23);
		panel_Filtros.add(btnBorrarFiltros);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ordenar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 154, 166);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JButton btnAlfa = new JButton("Alfabetico");
		btnAlfa.setBounds(10, 24, 134, 23);
		panel.add(btnAlfa);
		
		JButton btnId = new JButton("Identificaci\u00F3n");
		btnId.setBounds(10, 71, 134, 23);
		panel.add(btnId);
		
		JButton btnEspecialidad = new JButton("Especialidad");
		btnEspecialidad.setBounds(10, 118, 134, 23);
		panel.add(btnEspecialidad);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Modificar");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		imprimirDoctores(misDoctores);
	}
	
	private void imprimirDoctores(ArrayList<Doctor> doctores)
	{
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		for (Doctor aux : doctores) {
			row[0] = aux.getId();
			row[1] = aux.getNombre();
			row[2] = aux.getCedula();
			row[3] = aux.getEspecialidad();
			model.addRow(row);
		}
	}
	
	private void habilitarBoton()
	{
		if(selectedDoctor!= null)
		{
			okButton.setEnabled(true);
		}else {
			okButton.setEnabled(false);
		}
	}
	private String[] buscarEspecialidades()
	{
		HashSet<String> especialidadSinRepetir = new HashSet<>();
		especialidadSinRepetir.add("<Selecione>");
			for (Doctor aux : misDoctores) {
				especialidadSinRepetir.add(aux.getEspecialidad());
			}
		String[] especialidades = new String [especialidadSinRepetir.size()];
		especialidadSinRepetir.toArray(especialidades);
		return especialidades;
	}
}
